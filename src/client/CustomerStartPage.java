
package client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.games.GamePanel;
import client.games.GuessTheNumber;
import client.games.RockPaperScissors;
import client.order.MenuPanel;
import client.order.PayPanel;
import client.order.Payment;
import client.rewards.RewardsPanel;
import client.utils.JFrameUtils;
import javazoom.jl.player.Player;

/**
 * Credits for the music used goes to: https://www.bensound.com/
 * 
 * @author karim
 *
 */

public class CustomerStartPage extends JPanel {

	private static final long serialVersionUID = -8164412480994553957L;

	// Represents the JLayer music player
	private static Player music;

	public JButton rwdsBtn;
	public JButton helpBtn;
	public JButton backBtn;
	public JButton refillBtn;
	public JButton btnStopMusic;
	public JButton shuffle;
	
	public JPanel utilityPanel;
	public JPanel mainPanel;
	
	public MenuPanel orderPanel;
	public GamePanel gamePanel;
	public PayPanel payPanel;
	public Payment pay2;
	public RewardsPanel rewardsPanel;
	public RockPaperScissors rpsPanel;
	public GuessTheNumber gtnPanel;
	
	public JLabel rwdsLbl;

	public static String currentScreen = "";
	

	public CustomerStartPage() {
		super();
		setLayout(null);
		setBounds(0, 0, 1039, 626);

		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 1039, 522);
		add(mainPanel);
		mainPanel.setLayout(null);

		//a panel that is available regardless of what screen is open in the system
		utilityPanel = new JPanel();
		utilityPanel.setBounds(0, 523, 1039, 104);
		add(utilityPanel);
		utilityPanel.setLayout(null);

		//button for help in utility panel
		helpBtn = new JButton();
		helpBtn.setText("Help");
		helpBtn.setFont(new Font("Haettenschweiler", Font.PLAIN, 25));
		helpBtn.setBounds(480, 5, 120, 74);
		utilityPanel.add(helpBtn);
		
		//if button pressed, help request is sent to waitstaff
		helpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendHelpRequest();
			}
		});

		/*
		ImageIcon icon = new ImageIcon("./src/client/help.png");
		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance(140, 140,  java.awt.Image.SCALE_SMOOTH ) ;  
		icon = new ImageIcon( newimg );
		helpBtn.setIcon(icon);
		 */
		
		//button to request refill in utility panel
		refillBtn = new JButton();
		refillBtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		refillBtn.setText("Refill");
		refillBtn.setBounds(612, 5, 120, 74);
		utilityPanel.add(refillBtn);
		//if pressed, waitstaff gets request for refill
		refillBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendRefillRequest();
			}
		});

		//button to enter rewards login/signup on utility panel
		rwdsBtn = new JButton("Rewards");
		rwdsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openScreen("rewards");
			}
		});
		rwdsBtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		rwdsBtn.setBounds(27, 34, 120, 47);
		utilityPanel.add(rwdsBtn);

		rwdsLbl = new JLabel("Rewards Member? Login to continue for tasty treats and rewards!");
		rwdsLbl.setBounds(27, 0, 441, 37);
		utilityPanel.add(rwdsLbl);

		//button to shuffle music in the system
		shuffle = new JButton();
		shuffle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (music != null) {
					stopMusic();
				}
				new Thread() {
					@Override
					public void run() {
						try {
							File[] musicFiles = new File("./data/music/").listFiles();
							int randomMusicFile = GuessTheNumber.generateNumber(0, musicFiles.length - 1);
							File file = musicFiles[randomMusicFile];
							FileInputStream fip = new FileInputStream(file);
							BufferedInputStream bip = new BufferedInputStream(fip);
							Player player = new Player(bip);
							music = player;
							music.play();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}.start();
			}
		});
		shuffle.setFont(new Font("Haettenschweiler", Font.PLAIN, 25));
		shuffle.setText("Shuffle Music");
		shuffle.setBounds(760, 5, 131, 74);
		utilityPanel.add(shuffle);

		backBtn = new JButton("Back");
		backBtn.setFont(new Font("Haettenschweiler", Font.BOLD, 24));
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back((currentScreen.equals("rps") || currentScreen.equals("gtn")) ? true : false);
			}
		});
		backBtn.setBounds(37, 5, 131, 77);
		utilityPanel.add(backBtn);
		backBtn.setVisible(false);

		btnStopMusic = new JButton();
		btnStopMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopMusic();
			}
		});
		btnStopMusic.setText("Stop Music");
		btnStopMusic.setFont(new Font("Haettenschweiler", Font.PLAIN, 25));
		btnStopMusic.setBounds(894, 5, 120, 74);
		utilityPanel.add(btnStopMusic);
		
		/*
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(770, 92, 120, 28);
		utilityPanel.add(comboBox);
		backBtn.setVisible(false);
		*/

		JLabel lblNewLabel_1 = new JLabel("Welcome to Seven Guys! Click one of the options below to get started.");
		lblNewLabel_1.setFont(new Font("Haettenschweiler", Font.BOLD, 30));
		lblNewLabel_1.setBounds(115, 16, 835, 32);
		mainPanel.add(lblNewLabel_1);

		//when user taps on order
		JButton orderBtn = new JButton("ORDER");
		orderBtn.addActionListener(new ActionListener() {
			//if they didn't receive specials notice, specials for that day are shown
			public void actionPerformed(ActionEvent e) {
				openScreen("order");
				if(!ClientSession.receivedSpecialNoti) {
					ClientSession.checkSpecialsDay();
				}
			}
		});
		orderBtn.setFont(new Font("Haettenschweiler", Font.PLAIN, 89));
		orderBtn.setBounds(0, 64, 346, 444);
		mainPanel.add(orderBtn);

		//when tapped on pay, screen for payment opens
		JButton payBtn = new JButton("PAY");
		payBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openScreen("pay");
			}
		});
		payBtn.setFont(new Font("Haettenschweiler", Font.PLAIN, 89));
		payBtn.setBounds(347, 64, 346, 444);
		mainPanel.add(payBtn);

		//when tapped on play, screen for games opens
		JButton gamesBtn = new JButton("PLAY");
		gamesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openScreen("game");
			}
		});
		gamesBtn.setFont(new Font("Haettenschweiler", Font.PLAIN, 89));
		gamesBtn.setBounds(694, 64, 328, 444);
		mainPanel.add(gamesBtn);

		
		this.orderPanel = new MenuPanel();
		orderPanel.setVisible(false);
		add(orderPanel);

		this.gamePanel = new GamePanel();
		add(gamePanel);
		gamePanel.setVisible(false);

		this.payPanel = new PayPanel();
		add(payPanel);
		payPanel.setVisible(false);
		
		this.pay2 = new Payment();
		add(pay2);
		pay2.setVisible(false);
		
		this.rewardsPanel = new RewardsPanel();
		add(rewardsPanel);
		rewardsPanel.setVisible(false);
		
		this.rpsPanel = new RockPaperScissors();
		add(rpsPanel);
		rpsPanel.setVisible(false);
		
		this.gtnPanel = new GuessTheNumber();
		add(gtnPanel);
		gtnPanel.setVisible(false);
		gtnPanel.randomNum = GuessTheNumber.generateNumber(0, 100);

	}
	
	//if music is playing, stop and set to null
	protected void stopMusic() {
		if (music == null) {
			return;
		}
		music.close();
		music = null;
	}

	protected void back(boolean exception) {
		switch(currentScreen) {
			case "game":
				this.gamePanel.setVisible(false);
				break;
			case "order":
				this.orderPanel.setVisible(false);
				break;
			case "pay":
				//this.payPanel.setVisible(false);
				this.pay2.setVisible(false);
				break;
			case "rewards":
				this.rewardsPanel.rewardsPanel.setVisible(false);
				this.rewardsPanel.setVisible(false);
				break;
			case "rps":
				this.gamePanel.setVisible(true);
				this.rpsPanel.setVisible(false);
				if(this.rpsPanel.music != null)
					this.rpsPanel.music.close();
				currentScreen = "game";
				break;
			case "gtn":
				this.gamePanel.setVisible(true);
				this.gtnPanel.setVisible(false);
				currentScreen = "game";
				break;
		}
		if(!exception) {
			this.rwdsBtn.setVisible(true);
			this.rwdsLbl.setVisible(true);
			this.mainPanel.setVisible(true);
			this.backBtn.setVisible(false);
			currentScreen = "";
		}
	}

	public void openScreen(String type) {
		if(type.equals("order") && ClientSession.ordersStopped) {
			JFrameUtils.showMessage("Seven Guys General Notification", 
				"Sorry, but the restaurant is no longer accepting further orders at this time..\n"
					+ "You can still pay for your current bill if applicable and play\n"
					+ "the lottery game if you wish to do so.");
			return;
		}
		this.rwdsBtn.setVisible(false);
		this.rwdsLbl.setVisible(false);
		this.mainPanel.setVisible(false);
		this.backBtn.setVisible(true);
		currentScreen = ""+type;
		switch(type) {
			case "game":
				this.gamePanel.setVisible(true);
				break;
			case "order":
				this.orderPanel.setVisible(true);
				this.orderPanel.getMenuItems();
				break;
			case "pay":
//				this.payPanel.setVisible(true);
//				this.payPanel.refreshTxtAreas();
				this.pay2.setVisible(true);
				this.pay2.refreshTxtAreas();
				break;
			case "rewards":
				this.rewardsPanel.setVisible(true);
				if(ClientSession.rwdsLoggedIn) {
					Client.clientFrame.customerSP.rewardsPanel.loginToRewards(false);
				}
				break;
			case "rps":
				this.gamePanel.setVisible(false);
				this.rpsPanel.setVisible(true);
				break;
			case "gtn":
				this.gamePanel.setVisible(false);
				this.gtnPanel.setVisible(true);
				break;
		}
	}
	
	/**
	 * Sends a refill request to the server
	 */
	protected void sendRefillRequest() {
		if(ClientSession.requestedRefill
			|| ClientSession.requestedHelp) {
			JFrameUtils.showMessage("Refill Request", "Error: You currently have another request in progress.\n"
				+ "Please wait for a member of the waitstaff to come and help you.");
			return;
		}
		String refill = (String) JFrameUtils.inputDialog("Refill Request", 
			"Please enter the drink you want a refill for (type coke, sprite, 7up or water):");
		if(refill == null || refill.equals("")) {
			return;
		}
		JFrameUtils.showMessage("Refill Request", "Sending a request to the waitstaff for a "+refill+" refill...");
		Client.session.getPacketEncoder().sendRefillRequest();
	}

	/**
	 * Sends a help request to the server.
	 */
	protected void sendHelpRequest() {
		if(ClientSession.requestedRefill
			|| ClientSession.requestedHelp) {
			JFrameUtils.showMessage("Help Request", "Error: You currently have another request in progress.\n"
				+ "Please wait for a member of the waitstaff to come and help you.");
			return;
		}
		JFrameUtils.showMessage("Help Request", "Sending a request to the waitstaff for help...");
		Client.session.getPacketEncoder().sendHelpRequest();
	}
	
}