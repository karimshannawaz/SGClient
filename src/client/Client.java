package client;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import client.network.ClientChannel;
import client.network.Session;
import client.utils.JFrameUtils;
import client.utils.PersistentTimer;
import client.utils.Timers;

public class Client {
	
	// new change
	// new change from website

	public static Session session;
	public static ClientFrame clientFrame;
	
	public static void main(String[] args) {
		Client.start();
	}
	
	public static void start() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Timers.tasks = Executors.newScheduledThreadPool(1);
				clientFrame = new ClientFrame();
				clientFrame.setVisible(true);
				Date date = new Date();
				String format = DateFormat.getInstance().format(date);
				clientFrame.setTitle(clientFrame.getTitle()+" - "+(format));
				// Updates time on title every minute 
				// Notifies customer if the restaurant is closing soon.
				Timers.submit(new PersistentTimer(1) {

					@Override
					public void run() {
						Date fullDate = new Date();
						String format = DateFormat.getInstance().format(fullDate);
						String[] tok = format.split(" ");
						String date = tok[0];
						String time = tok[1];
						String meridiem = tok[2];
						String[] timeToks = time.split(":");
						int hour = Integer.parseInt(timeToks[0]);
						int mins = Integer.parseInt(timeToks[1]);
						if(hour >= 11 && hour != 12 && !ClientSession.closingSoonNoti && meridiem.equals("PM")) {
							ClientSession.closingSoonNoti = true;
							JFrameUtils.showMessage("Seven Guys General Notification", 
								"Please be advised that the restaurant closes soon, at midnight.\n"
								+ "You will not be able to place an order after 11:29 PM. Current time: "+time+" "+meridiem);
						}
						if(hour >= 11 && hour != 12 && mins >= 29 && !ClientSession.ordersStopped && meridiem.equals("PM")) {
							ClientSession.ordersStopped = true;
							if(CustomerStartPage.currentScreen.equals("order")) {
								clientFrame.customerSP.orderPanel.setVisible(false);
								clientFrame.customerSP.rwdsBtn.setVisible(true);
								clientFrame.customerSP.rwdsLbl.setVisible(true);
								clientFrame.customerSP.mainPanel.setVisible(true);
								clientFrame.customerSP.backBtn.setVisible(false);
								CustomerStartPage.currentScreen = "";
							}
							JFrameUtils.showMessage("Seven Guys General Notification", 
								"Sorry, but the restaurant is no longer accepting further orders at this time..\n"
								+ "You can still pay for your current bill if applicable and play\n"
								+ "the lottery game if you wish to do so. Current time: "+time+" "+meridiem);
						}
						clientFrame.setTitle((ClientSession.tableID > -1 ? ("Seven Guys Table "+
							(ClientSession.tableID + 1)) : "Seven Guys")+" - "+format);
					}
					
				});
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
