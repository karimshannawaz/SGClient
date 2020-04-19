package client;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import client.network.ClientChannel;
import client.network.Session;
import client.utils.Constants;
import client.utils.JFrameUtils;
import client.utils.PersistentTimer;
import client.utils.Timers;

/**
 * Holds information for the starting point for the client.
 * 
 * @author Karimshan
 *
 */
public class Client {
	
	public static Session session;
	public static ClientFrame clientFrame;
	
	public static void main(String[] args) {
		Client.start();
	}
	
	public static void start() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				/**
				 * Submits global tasks to persistently execute in the background
				 */
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
						@SuppressWarnings("unused")
						String date = tok[0];
						String time = tok[1];
						String meridiem = tok[2];
						String[] timeToks = time.split(":");
						int hour = Integer.parseInt(timeToks[0]);
						int mins = Integer.parseInt(timeToks[1]);
						/**
						 * This block of code deals with the weekly specials.
						 */
						if(!Constants.DEV_MODE) {
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
						}
						clientFrame.setTitle((ClientSession.tableID > -1 ? ("Seven Guys Table "+
							(ClientSession.tableID + 1)) : "Seven Guys")+" - "+format);
					}
					
				});
			}
		});
		
		ClientChannel.openChannel();
	}
	
	public static void restartChannel() {
		clientFrame.dispose();
		ClientChannel.shutdown();
		Client.start();
	}
	
	public static void sendMessage(String string) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, string, 
				"Editor", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Restart application courtesy of
	 * https://dzone.com/articles/programmatically-restart-java
	 * @param runBeforeRestart
	 */
	public static void restartApplication(Runnable runBeforeRestart) {
		try {
			// java binary
			String java = System.getProperty("java.home") + "/bin/java";
			// vm arguments
			List<String> vmArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
			StringBuffer vmArgsOneLine = new StringBuffer();
			for (String arg : vmArguments) {
				// if it's the agent argument : we ignore it otherwise the
				// address of the old application and the new one will be in conflict
				if (!arg.contains("-agentlib")) {
					vmArgsOneLine.append(arg);
					vmArgsOneLine.append(" ");
				}
			}
			// init the command to execute, add the vm args
			final StringBuffer cmd = new StringBuffer("\"" + java + "\" " + vmArgsOneLine);

			// program main and program arguments
			String[] mainCommand = System.getProperty("sun.java.command").split(" ");
			// program main is a jar
			if (mainCommand[0].endsWith(".jar")) {
				// if it's a jar, add -jar mainJar
				cmd.append("-jar " + new File(mainCommand[0]).getPath());
			} else {
				// else it's a .class, add the classpath and mainClass
				cmd.append("-cp \"" + System.getProperty("java.class.path") + "\" " + mainCommand[0]);
			}
			// finally add program arguments
			for (int i = 1; i < mainCommand.length; i++) {
				cmd.append(" ");
				cmd.append(mainCommand[i]);
			}
			new Thread() {
				public void run() {
					System.gc();
				}
			}.start();
			System.out.println((cmd.toString()));
			// execute the command in a shutdown hook, to be sure that all the
			// resources have been disposed before restarting the application
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					try {
						Runtime.getRuntime().exec(cmd.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			// execute some custom code before restarting
			if (runBeforeRestart != null) {
				runBeforeRestart.run();
			}
			// exit
			System.exit(0);
		} catch (Exception e) {
			// something went wrong
			System.out.println("Error while trying to restart the application: "+e.getMessage());
		}
	}

	/**
	 * Restarts the client application.
	 */
	public static void restart() {
		Client.restartApplication(new Runnable() {

			@Override
			public void run() {
				Client.session.getChannel().close();
			}
			
		});
	}

}
