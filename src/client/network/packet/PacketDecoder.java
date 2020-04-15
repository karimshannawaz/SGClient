package client.network.packet;
import client.Client;
import client.ClientFrame;
import client.ClientSession;
import client.network.Session;
import client.order.MItem;
import client.order.Menu;
import client.utils.JFrameUtils;

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
				if(packetId != 255) {
				System.out.println("Invalid size for PacketId " + packetId
						+ ". Size guessed to be " + length);
				}
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
							String tokens[] = itemToString.split("~");
							String name = tokens[0];
							double price = Double.parseDouble(tokens[1]);
							String desc = tokens[2];
							int calories = Integer.parseInt(tokens[3]);
							String allergens = tokens[4];
							String type = tokens[5];
							String menuType = tokens[6];
							String ingredients = tokens[7];
							MItem item = new MItem(name, price, desc, calories, allergens, type, menuType, ingredients);
							Menu.instance.add(item);
							menuRequestFix = 0;
						}
					}
					break;
					
				// Receiving/sending requests to the server.
				case 4:
					String code = stream.readString();
					switch(code) {
					
						case "email_exists":
							JFrameUtils.showMessage("Rewards Account", 
									"Error: This email already exists. Please try using another email address.");
							break;
							
						case "email_does_not_exist":
							JFrameUtils.showMessage("Rewards Account", 
									"Error: This email does not exist. Please try using an existing email address.");
							break;
							
						case "email_created":
							int paramsLength = stream.readUnsignedByte();
							String email = stream.readString();
							String birthdate = stream.readString();
							String name = stream.readString();
							ClientSession.email = email;
							ClientSession.birthday = birthdate;
							Client.clientFrame.panel.rewardsPanel.finishSignup();
							break;
					}
					break;
					
				// Receiving saved user details from server
				case 5:
					ClientSession.email = stream.readString();
					ClientSession.birthday = stream.readString();
					ClientSession.name = stream.readString();
					ClientSession.visits = stream.readUnsignedShort();
					ClientSession.hasFreeSide = stream.readUnsignedByte() == 1;
					ClientSession.hasBirthdayEntree = stream.readUnsignedByte() == 1;
					ClientSession.hasFreeDessert = stream.readUnsignedByte() == 1;
					ClientSession.rwdsLoggedIn = true;
					Client.clientFrame.panel.rewardsPanel.loginToRewards(true);
					break;
					
				default:
					break;
			}
			
			stream.setOffset(startOffset + length);
		}
	}

	
}
