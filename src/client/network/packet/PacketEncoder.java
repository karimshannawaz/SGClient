package client.network.packet;
import client.ClientSession;
import client.network.Session;
import client.order.CustomerOrder;

public class PacketEncoder extends Encoder {
	
	public PacketEncoder(Session session) {
		super(session);
		sendHandshake();
	}
	
	private void sendHandshake() {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(1);
		stream.endPacketVarShort();
		session.write(stream);
		session.setDecoder(0);
	}
	
	public void sendSecondHandshake() {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(2);
		stream.endPacketVarShort();
		session.write(stream);
	}

	/**
	 * This lets the server portion know that a customer 
	 * has connected to the kiosk and assigns them a table/kiosk ID.
	 */
	public void sendCustomerConnected() {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(3);
		stream.endPacketVarShort();
		session.write(stream);
	}
	
	/**
	 * Sends the server a help request with its
	 * kiosk number.
	 */
	public void sendHelpRequest() {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(4);
		stream.writeByte(ClientSession.tableID);
		stream.endPacketVarShort();
		ClientSession.requestedHelp = true;
		session.write(stream);
	}
	
	/**
	 * Requests the menu from the restaurant
	 */
	public void requestMenu() {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(5);
		stream.endPacketVarShort();
		session.write(stream);
	}

	/**
	 * Requests the server to create a new user with the specified email
	 * and birth-date. If email exists, then client is told that 
	 * this email is already taken.
	 * 
	 * @param email
	 * @param birthdate
	 */
	public void sendCreationRequest(String email, String birthdate, String name) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(6);
		stream.writeString(email);
		stream.writeString(birthdate);
		stream.writeString(name);
		stream.endPacketVarShort();
		session.write(stream);
	}

	/**
	 * Requests the server to login with the specified email
	 * and birth-date. If email does not exist, then client is notified.
	 * 
	 * @param email
	 */
	public void sendLoginRequest(String email) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(7);
		stream.writeString(email);
		stream.endPacketVarShort();
		session.write(stream);
	}
	
	/**
	 * Requests the server to login with the specified id and password.
	 * If either user/password is invalid/does not exist, client
	 * will be notified.
	 * 
	 * @param email
	 */
	public void sendLoginRequest(String id, String password) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(8);
		stream.writeString(id);
		stream.writeString(password);
		stream.endPacketVarShort();
		session.write(stream);
	}
	
	/**
	 * Sends the current order to the server.
	 */
	public void sendOrder(double subtotal) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(9);
		stream.writeByte(ClientSession.tableID);
		stream.writeString(""+subtotal);
		stream.writeByte(CustomerOrder.items.size());
		for(int i = 0; i < CustomerOrder.items.size(); i++) {
			stream.writeString(CustomerOrder.items.get(i).asOrder());
		}
		stream.endPacketVarShort();
		session.write(stream);
	}

	/**
	 * The kitchen requests for a waiter to come and get the order.
	 * @param tableNum
	 * @param row
	 */
	public void sendOrderCompleted(int tableNum) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(10);
		stream.writeByte(tableNum);
		stream.endPacketVarShort();
		session.write(stream);
	}
	
	/**
	 * The waiter marks that they have delivered the order to the table.
	 * @param tableNum
	 * @param row
	 */
	public void sendOrderDelivered(int tableNum) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(11);
		stream.writeByte(tableNum);
		stream.endPacketVarShort();
		session.write(stream);
	}

	/**
	 * Sends the server a refill request with its
	 * kiosk number.
	 */
	public void sendRefillRequest() {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(12);
		stream.writeByte(ClientSession.tableID);
		stream.endPacketVarShort();
		ClientSession.requestedRefill = true;
		session.write(stream);
	}

	/**
	 * Lets the customer know that the waiter is on the way to help
	 * or with their refill.
	 * @param row
	 * @param string
	 */
	public void sendRequestComplete(int tableID, boolean refill) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(13);
		stream.writeByte(refill ? 1 : 0);
		stream.writeByte(tableID);
		stream.endPacketVarShort();
		session.write(stream);
	}
	
}