package client.network.packet;
import javax.swing.table.DefaultTableModel;

import client.Client;
import client.ClientFrame;
import client.ClientSession;
import client.network.Session;
import client.order.CustomerOrder;
import client.order.KOrder;
import client.order.MItem;
import client.order.Menu;
import client.order.OrderQueue;
import client.utils.Constants;
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
					ClientSession.tableID = stream.readUnsignedByte();
					ClientFrame.instance.setTitle("Seven Guys Table "+((ClientSession.tableID) + 1));
					System.out.println("Assigned table ID "+ClientSession.tableID);
					
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
						
						// Manager or waitstaff confirms customer's payment.
						case "cash_payment_confirmed":
							Client.clientFrame.customerSP.pay2.openReceiptPanel();
							break;
					
						// Exits the client per the server's request.
						case "terminate":
							System.exit(1);
							break;
							
						// The manager can offer a compensation in the case of customer
						// dissatisfaction.
						case "manager_compensation":
							stream.readUnsignedByte();
							double compensation = Double.parseDouble(stream.readString());
							Client.clientFrame.customerSP.pay2.addManagerDiscount(compensation);
							JFrameUtils.showMessage("Seven Guys", 
								"We apologize for any inconveniences that may have impacted your experience here.\n"
								+ "Please accept a "+(Client.clientFrame.customerSP.pay2.decimalF(compensation))+""
								+ " compensation for your meal on behalf of the manager.\nPlease let us know if"
								+ "there is anything else we can do for you. Thank you!");
							break;
					
						case "waitstaff_on_way_with_request":
							stream.readUnsignedByte();
							String type = stream.readString();
							if(type.equals("help")) {
								ClientSession.requestedHelp = false;
								JFrameUtils.showMessage("Help Request", 
									"We appreciate your patience!\n"
									+ "A manager or member of our waitstaff is on their way to your table!");
							}
							else if(type.equals("refill")) {
								ClientSession.requestedRefill = false;
								JFrameUtils.showMessage("Help Request", 
									"We appreciate your patience!\n"
									+ "A manager or member of our waitstaff is on their way with your refill to your table!");
							}
							break;
					
						case "waitstaff_received_request":
							stream.readUnsignedByte();
							type = stream.readString();
							if(type.equals("help")) {
								JFrameUtils.showMessage("Help Request", 
									"A manager or member of our waitstaff will arrive to your table shortly!");
							}
							else if(type.equals("refill")) {
								JFrameUtils.showMessage("Refill Request", 
									"A manager or member of our waitstaff will arrive to your table\n"
									+ "shortly with the refill that you requested!");
							}
							break;
					
						case "waitstaff_not_available_for_request":
							int paramsLength = stream.readUnsignedByte();
							type = stream.readString();
							if(type.equals("help")) {
								ClientSession.requestedHelp = false;
								JFrameUtils.showMessage("Help Request", 
									"Error: We apologize, but there isn't anyone who can fulfill your help request at this time.");
							}
							else if(type.equals("refill")) {
								ClientSession.requestedRefill = false;
								JFrameUtils.showMessage("Refill Request", 
									"Error: We apologize, but there isn't anyone who can fulfill your refill request at this time.");
							}
							break;
					
						case "on_the_way":
							paramsLength = stream.readUnsignedByte();
							String message = stream.readString();
							JFrameUtils.showMessage("Order Update", message);
							// Do extra stuff here
							Client.clientFrame.customerSP.orderPanel.updateMessage(message);
							break;
							
						case "waiter_delivered":
							Client.clientFrame.customerSP.orderPanel.updateMessage(
								"We hope you enjoy your meal, thank you!!");
							Client.clientFrame.customerSP.pay2.enablePrePaymentPanel();
							break;
							
						case "order_submitted":
							// Do extra stuff here
							Client.clientFrame.customerSP.orderPanel.doPostOrder();
							Client.clientFrame.customerSP.orderPanel.updateMessage("Your order is in the works!");
							break;
							
						case "out_of_stock":
							paramsLength = stream.readUnsignedByte();
							String itemName = stream.readString();
							int itemInOrder = stream.readUnsignedShort();
							CustomerOrder.items.remove(itemInOrder);
							Client.clientFrame.customerSP.orderPanel.refreshOrderTxtArea();
							Client.clientFrame.customerSP.orderPanel.goToFront();
							JFrameUtils.showMessage("Order Request", 
								"Error: We apologize, but "+itemName+" is currently out of stock.");
							break;
					
						case "cannot_process_order":
							JFrameUtils.showMessage("Order Request", 
									"Error: We couldn't process your order. Please try again or call for help.");
							break;
					
						case "nulled_account":
							JFrameUtils.showMessage("Seven Guys Account", 
								"Error: This account is nulled, please try to authenticate again or ask a manager for help.");
							break;
					
						case "email_exists":
							JFrameUtils.showMessage("Rewards Account", 
									"Error: This email already exists. Please try using another email address.");
							break;
							
						case "email_does_not_exist":
							JFrameUtils.showMessage("Rewards Account", 
									"Error: This email does not exist. Please try using an existing email address.");
							break;
							
						case "email_created":
							paramsLength = stream.readUnsignedByte();
							String email = stream.readString();
							String birthdate = stream.readString();
							String name = stream.readString();
							ClientSession.email = email;
							ClientSession.birthday = birthdate;
							Client.clientFrame.customerSP.rewardsPanel.finishSignup();
							break;
							
						// Employee client codes:
						case "incorrect_password":
							JFrameUtils.showMessage("Employee Login", 
								"Error: Incorrect password, please try again.");
							break;
							
						case "employee_id_does_not_exist":
							JFrameUtils.showMessage("Employee Login", 
								"Error: Invalid employee ID entered, please try again.");
							break;
							
						case "no_waitstaff_available":
							JFrameUtils.showMessage("Order Completion", 
								"Error: There is no waitstaff available to grab the order; please try again or\n"
								+ "ask a manager for assistance on how to proceed.");
							break;
							
						case "waitstaff_got_order":
							stream.readUnsignedByte();
							int tableID = stream.readUnsignedShort();
							
							int orderIndex = 0;
							for(KOrder o : OrderQueue.unfulfilledOrders) {
								if(o.getTableID() == tableID) {
									break;
								}
								orderIndex++;
							}
							
							System.out.println("table: "+tableID+" and orderIndex: "+orderIndex+", size: "+OrderQueue.unfulfilledOrders.size());
							
							OrderQueue.unfulfilledOrders.remove(orderIndex);
							
							DefaultTableModel tab = (DefaultTableModel) 
								Client.clientFrame.employeeSP.kitchenPage.table.getModel();
							int row = 0;
							for(int i = 0; i < tab.getRowCount(); i++) {
								if(((int) tab.getValueAt(row, 0) - 1) == tableID)
									break;
								row++;
							}
							tab.removeRow(row);
							break;
							
						// Represents that the customer tried to make a cash payment.
						case "cash_payment":
							stream.readUnsignedByte();
							tableID = stream.readUnsignedShort();
							double totalPayment = Double.parseDouble(stream.readString());
							JFrameUtils.showMessage("Payments", "Table "+(tableID + 1)+" has requested to make a cash payment of "
								+ Constants.decimalF(totalPayment)+"\nPlease mark the payment as complete once you've received it.");
							Client.clientFrame.employeeSP.waitstaffPage.table.getModel().
							setValueAt("O", tableID, 4);
							break;
					}
					break;
					
				// Receiving saved user details from server
				case 5:
					boolean employee = stream.readUnsignedByte() == 1;
					// Getting customer details sent from saved account
					// on the server.
					if(!employee) {
						ClientSession.email = stream.readString();
						ClientSession.birthday = stream.readString();
						ClientSession.name = stream.readString();
						ClientSession.visits = stream.readUnsignedShort();
						ClientSession.hasFreeSide = stream.readUnsignedByte() == 1;
						ClientSession.hasBirthdayEntree = stream.readUnsignedByte() == 1;
						ClientSession.hasFreeDessert = stream.readUnsignedByte() == 1;
						ClientSession.rwdsLoggedIn = true;
						Client.clientFrame.customerSP.pay2.refreshDiscounts();
						Client.clientFrame.customerSP.rewardsPanel.loginToRewards(true);
						if(Client.clientFrame.customerSP.pay2.isVisible())
							Client.clientFrame.customerSP.pay2.setVisible(true);
					}
					// Send employee details to client here.
					else {
						ClientSession.id = stream.readString();
						ClientSession.name = stream.readString();
						ClientSession.role = stream.readString();
						ClientSession.password = stream.readString();
						if(ClientSession.role.equals("waitstaff"))
							Client.clientFrame.employeeSP.waiterLandingPage();
						else if(ClientSession.role.equals("kitchen"))
							Client.clientFrame.employeeSP.kitchenLandingPage();
					}
					break;
					
				// Kitchen Staff receives customer order from server.
				case 6:
					int tableID = stream.readUnsignedByte();
					double subtotal = Double.parseDouble(stream.readString());
					int orderSize = stream.readUnsignedByte();
					KOrder order = new KOrder();
					for(int i = 0; i < orderSize; i++) {
						String mItem = stream.readString();
						String[] tok = mItem.split("~");
						String mItemName = tok[0];
						double price = Double.parseDouble(tok[1]);
						int qty = Integer.parseInt(tok[2]);
						String specReq = tok[3];
						String menuType = tok[4];
						String ing = tok[5];
						order.addItem(mItemName, price, qty, specReq, menuType, ing);
					}
					order.subtotal = subtotal;
					order.setTableID(tableID);
					OrderQueue.unfulfilledOrders.add(order);
					if(ClientSession.isKitchen()) {
						Client.clientFrame.employeeSP.kitchenPage.addToTable(tableID);
						JFrameUtils.showMessage("Order Update", "You have a new order to fulfill for table: "+(tableID + 1));
					}
					break;
					
				// Wait Staff gets customer order and walks over to it
				case 7:
					int tableIDFK = stream.readUnsignedByte();
					
					Client.clientFrame.employeeSP.waitstaffPage.table.getModel().
					setValueAt("O", tableIDFK, 3);
					
					JFrameUtils.showMessage("Order Update", "You have a new order to take to table "+
					(tableIDFK + 1)+".\nPlease mark it as delivered to the table once you've delivered it.");
					break;
					
				// Wait Staff gets customer help or refill request
				case 8:
					boolean refill = stream.readUnsignedByte() == 1;
					int tableIDFServer = stream.readUnsignedByte();
					
					if(refill) {
						Client.clientFrame.employeeSP.waitstaffPage.table.getModel().
						setValueAt("O", tableIDFServer, 1);
						JFrameUtils.showMessage("Refill Update", "You have a new refill to take to table "+
							(tableIDFServer + 1)+".\nPlease tap on it when you are on your way to the table with the refill.");
					} else {
						Client.clientFrame.employeeSP.waitstaffPage.table.getModel().
						setValueAt("O", tableIDFServer, 2);
						JFrameUtils.showMessage("Help Update", "You have a new help request from table "+
							(tableIDFServer + 1)+".\nPlease tap on it when you are on your way to the table.");
					}
					break;
					
				// Server sends client the most popular menu items.
				case 9:
					int size = stream.readUnsignedByte();
					Client.clientFrame.customerSP.orderPanel.popularFM.clear();
					for(int i = 0; i < size; i++) {
						Client.clientFrame.customerSP.orderPanel.popularFM.add(stream.readString());
					}
					break;
					
				default:
					break;
			}
			
			stream.setOffset(startOffset + length);
		}
	}

	
}
