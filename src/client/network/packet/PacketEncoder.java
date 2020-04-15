package client.network.packet;
import client.Client;
import client.network.Session;

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
		stream.writeByte(Client.tableID);
		stream.endPacketVarShort();
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
	
}