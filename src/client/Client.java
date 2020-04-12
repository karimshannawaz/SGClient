package client;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import client.network.ClientChannel;
import client.network.Session;

public class Client {
	
	// new change
	// new change from website

	public static Session session;
	public static ClientFrame clientFrame;
	
	public static int tableID;

	public static void main(String[] args) {
		Client.start();
	}
	
	public static void start() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				clientFrame = new ClientFrame();
				clientFrame.setVisible(true);
			}
		});
		
		ClientChannel.openChannel();
	}
	
	public static void restart() {
		clientFrame.dispose();
		ClientChannel.shutdown();
		Client.start();
	}
	
	public static void sendMessage(String string) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, string, 
				"Editor", JOptionPane.INFORMATION_MESSAGE);
	}

}
