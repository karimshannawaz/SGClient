package client.network.packet;
import client.Client;
import client.ClientFrame;
import client.network.Session;
import client.order.MItem;
import client.order.Menu;

public final class PacketDecoder extends Decoder {
	
	public static int menuRequestFix = 0;
	
	private static final byte[] SIZES = new byte[256];
	
	static {
		loadSizes();
	}

	public static void loadSizes() {
		for (int id = 0; id < SIZES.length; id++)
			SIZES[id] = -4;
		SIZES[1] = 2;
	
	}

	public PacketDecoder(Session session) {
		super(session);
	}

	@Override
	public void decode(InputStream stream) {
		
		while (stream.getRemaining() > 0 && session.getChannel().isConnected()) {
			int packetId = stream.readUnsignedByte();
			if (packetId >= SIZES.length) {
				System.out.println("PacketId " + packetId
						+ " has fake packet id.");
				break;
			}
			
			int length = SIZES[packetId];
			if (length == -1)
				length = stream.readUnsignedByte();
			else if (length == -2)
				length = stream.readUnsignedShort();
			else if (length == -3)
				length = stream.readInt();
			else if (length == -4) {
				length = stream.getRemaining();
				if(packetId != 255)
				System.out.println("Invalid size for PacketId " + packetId
						+ ". Size guessed to be " + length);
			}
			if (length > stream.getRemaining()) {
				length = stream.getRemaining();
				if(packetId != 0)
				System.out.println("PacketId " + packetId
						+ " has fake size. - expected size " + length);
				break;

			}
			
			int startOffset = stream.getOffset();
			
			stream.skip(2);
			switch(packetId) {
			
				// First acknowledgement from server.
				case 1:
					session.getPacketEncoder().sendSecondHandshake();
					System.out.println("Sent second handshake");
					break;
					
				// Customer/table gets assigned a table ID by the server.
				case 2:
					Client.tableID = stream.readUnsignedByte();
					ClientFrame.instance.setTitle("Seven Guys Table "+((Client.tableID) + 1));
					System.out.println("Assigned table ID "+Client.tableID);
					
					break;
					
				// Menu sent from server:
				case 3:
					if(menuRequestFix == 0) {
						menuRequestFix = 1;
					}
					else if(menuRequestFix == 1) {
						Menu.instance.clear();
						int size = stream.readUnsignedByte();
						for(int i = 0; i < size; i++) {
							String itemToString = stream.readString();
							System.out.println("CLIENT SIDE ITEM RECEIVED: "+itemToString);
							String tokens[] = itemToString.split("~");
							int index = Integer.parseInt(tokens[0]);
							String name = tokens[1];
							double price = Double.parseDouble(tokens[2]);
							String desc = tokens[3];
							int calories = Integer.parseInt(tokens[4]);
							String allergens = tokens[5];
							int type = Integer.parseInt(tokens[6]);
							String menuType = tokens[7];
							String ingredients = tokens[8];
							MItem item = new MItem(name, price, desc, calories, allergens, type, menuType, ingredients);
							item.setIndex(index);
							Menu.instance.put(index, item);
							System.out.println("Menu Item: "+Menu.instance.get(index).toString());
							menuRequestFix = 0;
						}
					}
					break;
				default:
					break;
			}
			
			stream.setOffset(startOffset + length);
		}
	}

	
}
