package client.rewards;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.Client;
import client.ClientSession;
import client.utils.Constants;
import client.utils.JFrameUtils;

/**
 * Holds information pertaining to rewards on the client end.
 * 
 * @author Karimshan
 *
 */
public class RewardsPanel extends JPanel {

	private static final long serialVersionUID = -1338118268945423515L;
	
	public JPanel loginPanel;
	public JPanel rewardsPanel;
	
	// Represents an existing or new email.
	private JTextField exEmail;
	private JTextField newEmail;
	
	@SuppressWarnings("rawtypes")
	public JComboBox monthCB;
	@SuppressWarnings("rawtypes")
	public JComboBox dayCB;
	@SuppressWarnings("rawtypes")
	public JComboBox yearCB;
	private JTextField name;
	
	private JLabel fELbl;
	private JLabel fDLbl;
	
	private JLabel welcomeLbl;
	
	private JLabel visitsUntilEntree;
	private JLabel freeSignupSide;
	private JLabel freeBirthdayEntree;
	private JLabel freeLotteryDessert;
	private JLabel bdayTxtEntree;
	

	/**
	 * Create the panel.
	 */
	public RewardsPanel() {
		super();
		setBounds(0, 0, 1039, 522);
		setLayout(null);
		
		showLoginScreen();
		showRewardsPanel();
		this.rewardsPanel.setVisible(false);
	}
	
	private void showRewardsPanel() {
		rewardsPanel = new JPanel();
		rewardsPanel.setVisible(true);
		this.add(rewardsPanel);
		
		rewardsPanel.setBounds(0, 0, 1039, 522);
		rewardsPanel.setLayout(null);
		
		welcomeLbl = new JLabel("Welcome to Seven Guys Rewards, "+ClientSession.name+"!");
		welcomeLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		welcomeLbl.setBounds(299, 13, 644, 50);
		rewardsPanel.add(welcomeLbl);
		
		JLabel lblNewLabel_2 = new JLabel("Get a free appetizer/side when you sign up, a free entree every 5th visit and a free entree on your birthday!!");
		lblNewLabel_2.setFont(new Font("Sitka Subheading", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(100, 62, 852, 32);
		rewardsPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Rewards is constantly changing and we appreciate user feedback, so don't be shy to let us know!");
		lblNewLabel_2_1.setFont(new Font("Sitka Subheading", Font.PLAIN, 17));
		lblNewLabel_2_1.setBounds(137, 92, 852, 32);
		rewardsPanel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3 = new JLabel("Free Appetizer when you sign up:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_3.setBounds(199, 213, 335, 42);
		rewardsPanel.add(lblNewLabel_3);
		
		freeSignupSide = new JLabel("ACTIVE");
		freeSignupSide.setFont(new Font("Tahoma", Font.PLAIN, 21));
		freeSignupSide.setBounds(560, 213, 446, 42);
		rewardsPanel.add(freeSignupSide);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("*** To claim a reward, simply go to checkout on the pay screen, click "
				+ "on discounts and select the one you want to apply! ***");
		lblNewLabel_2_1_1.setBounds(37, 149, 1015, 42);
		lblNewLabel_2_1_1.setFont(new Font("Sitka Subheading", Font.PLAIN, 17));
		rewardsPanel.add(lblNewLabel_2_1_1);
			
		bdayTxtEntree = new JLabel("Free birthday () Entree:");
		bdayTxtEntree.setFont(new Font("Tahoma", Font.PLAIN, 21));
		bdayTxtEntree.setBounds(199, 268, 335, 42);
		rewardsPanel.add(bdayTxtEntree);
		
		freeBirthdayEntree = new JLabel("INACTIVE (30 days left)");
		freeBirthdayEntree.setFont(new Font("Tahoma", Font.PLAIN, 21));
		freeBirthdayEntree.setBounds(560, 268, 446, 42);
		rewardsPanel.add(freeBirthdayEntree);
		
		fELbl = new JLabel("Visits until free Entree:");
		fELbl.setFont(new Font("Tahoma", Font.PLAIN, 21));
		fELbl.setBounds(199, 329, 335, 42);
		rewardsPanel.add(fELbl);
		
		visitsUntilEntree = new JLabel("5");
		visitsUntilEntree.setFont(new Font("Tahoma", Font.PLAIN, 21));
		visitsUntilEntree.setBounds(560, 329, 429, 42);
		rewardsPanel.add(visitsUntilEntree);
		
		fDLbl = new JLabel("Free dessert from lottery game:");
		fDLbl.setFont(new Font("Tahoma", Font.PLAIN, 21));
		fDLbl.setBounds(199, 384, 335, 42);
		rewardsPanel.add(fDLbl);
		
		freeLotteryDessert = new JLabel("INACTIVE");
		freeLotteryDessert.setFont(new Font("Tahoma", Font.PLAIN, 21));
		freeLotteryDessert.setBounds(560, 384, 446, 32);
		rewardsPanel.add(freeLotteryDessert);
		
	}	
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void showLoginScreen() {
		
		loginPanel = new JPanel();
		loginPanel.setVisible(true);
		this.add(loginPanel);
		
		loginPanel.setBounds(0, 0, 1039, 522);
		loginPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Please login to rewards below!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(320, 13, 364, 47);
		loginPanel.add(lblNewLabel);
		
		JLabel lblUserIdemail = new JLabel("Existing user ID (Email):");
		lblUserIdemail.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblUserIdemail.setBounds(23, 99, 275, 47);
		loginPanel.add(lblUserIdemail);
		
		exEmail = new JTextField();
		
		exEmail.setFont(new Font("Tahoma", Font.PLAIN, 24));
		exEmail.setBounds(320, 99, 426, 47);
		loginPanel.add(exEmail);
		exEmail.setColumns(10);
		exEmail.addKeyListener(new KeyListener() {

			@Override public void keyPressed(KeyEvent arg0) {}
			@Override public void keyTyped(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				if(KeyEvent.getKeyText(e.getKeyCode()).toLowerCase().contains("enter")) {
					submitLoginRequest();
					return;
				}
			}
		});
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitLoginRequest();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnNewButton.setBounds(875, 99, 132, 47);
		loginPanel.add(btnNewButton);
		
		JTextArea txtrnewToRewards = new JTextArea();
		txtrnewToRewards.setLineWrap(true);
		txtrnewToRewards.setFont(new Font("Monospaced", Font.PLAIN, 20));
		txtrnewToRewards.setEditable(false);
		txtrnewToRewards.setText("New to rewards? Sign up below to access free appetizers and entrees! "
				+ "The best part? It's completely free, so get started today!!");
		txtrnewToRewards.setBounds(134, 189, 695, 101);
		loginPanel.add(txtrnewToRewards);
		
		newEmail = new JTextField();
		newEmail.setFont(new Font("Tahoma", Font.PLAIN, 24));
		newEmail.setColumns(10);
		newEmail.setBounds(158, 335, 270, 47);
		loginPanel.add(newEmail);
		
		JLabel lblNewEmail = new JLabel("New Email:");
		lblNewEmail.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewEmail.setBounds(12, 335, 163, 47);
		loginPanel.add(lblNewEmail);
		
		JButton btnSignUp = new JButton("Sign up!");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signup();
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnSignUp.setBounds(875, 388, 132, 47);
		loginPanel.add(btnSignUp);
		
		JLabel lblBirthdayfreeEntree = new JLabel("Birthday (Free entree every birthday!):");
		lblBirthdayfreeEntree.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBirthdayfreeEntree.setBounds(67, 435, 329, 47);
		loginPanel.add(lblBirthdayfreeEntree);
		
		monthCB = new JComboBox(new String[] {
			"January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December"
		});
		monthCB.setBounds(409, 437, 106, 47);
		loginPanel.add(monthCB);
		
		String[] days = new String[31];
		for(int i = 0; i < days.length; i++)
			days[i] = ""+((i + 1) < 10 ? ("0" + (i + 1)) : (i + 1));
		dayCB = new JComboBox(days);
		dayCB.setBounds(544, 437, 106, 47);
		loginPanel.add(dayCB);
		
		String[] years = new String[101];
		for(int i = 0; i < years.length; i++)
			years[i] = ""+(i + 1920);
		yearCB = new JComboBox(years);
		yearCB.setBounds(673, 435, 157, 47);
		loginPanel.add(yearCB);
		
		JLabel monthLbl = new JLabel("Month");
		monthLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		monthLbl.setBounds(432, 408, 65, 27);
		loginPanel.add(monthLbl);
		
		JLabel dayLbl = new JLabel("Day");
		dayLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dayLbl.setBounds(571, 405, 65, 27);
		loginPanel.add(dayLbl);
		
		JLabel yearLbl = new JLabel("Year");
		yearLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		yearLbl.setBounds(719, 405, 65, 27);
		loginPanel.add(yearLbl);
		
		JLabel fullNameLbl = new JLabel("Full Name:");
		fullNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		fullNameLbl.setBounds(440, 335, 145, 47);
		loginPanel.add(fullNameLbl);
		
		name = new JTextField();
		name.setFont(new Font("Tahoma", Font.PLAIN, 24));
		name.setColumns(10);
		name.setBounds(571, 335, 292, 47);
		loginPanel.add(name);
	}

	protected void signup() {
		String email = newEmail.getText();
		String nameTxt = name.getText();
		if(email.equals("") || email.equals(null) || !Constants.isValidEmail(email)) {
			JFrameUtils.showMessage("Rewards", "Invalid email entered for rewards, please try again.");
			return;
		}
		if(nameTxt.equals("") || nameTxt.equals(null)) {
			JFrameUtils.showMessage("Rewards", "Invalid name entered, please try again.");
			return;
		}
		int month = monthCB.getSelectedIndex();
		int day = dayCB.getSelectedIndex();
		String birthdate = ((month + 1) < 10 ? ("0" + (month + 1)) : (month + 1))+"/"+
			(day + 1 < 10 ? ("0" + (day + 1)) : (day + 1))+"/"+
				(yearCB.getSelectedIndex() + 1920);
		Client.session.getPacketEncoder().sendCreationRequest(email, birthdate, nameTxt);
	}
	
	public void finishSignup() {
		this.loginPanel.setVisible(false);
		this.rewardsPanel.setVisible(true);
		JFrameUtils.showMessage("Rewards Account", "Success! Your rewards account has been created with the email: "+
			ClientSession.email+" (Birthday: "+ClientSession.birthday+").\nRemember to visit frequently for "
					+ "free entrees after your 5th visit, "
					+ "free entree on your birthday, and\na chance to win a free dessert after every visit!");
	}

	protected void submitLoginRequest() {
		String email = exEmail.getText();
		if(email.equals("") || email.equals(null) || !Constants.isValidEmail(email)) {
			JFrameUtils.showMessage("Rewards", "Invalid email entered for rewards, please try again.");
			return;
		}
		Client.session.getPacketEncoder().sendLoginRequest(email);
	}
	
	public void loginToRewards(boolean showLogin) {
		this.loginPanel.setVisible(false);
		this.rewardsPanel.setVisible(true);
		
		Color darkgreen = new Color(0, 153, 0);
		
		welcomeLbl.setText("Welcome to Seven Guys Rewards, "+ClientSession.name+"!");
		
		String visitsTxt = ""+(ClientSession.visits == 5 ? "NONE! Claim your free entree!" : (5 - ClientSession.visits));
		visitsUntilEntree.setText(visitsTxt);
		visitsUntilEntree.setForeground((visitsTxt.startsWith("NONE") ? darkgreen : Color.RED));
		
		String signupTxt = ""+(ClientSession.hasFreeSide ? "ACTIVE! Claim your free side!" : "INACTIVE");
		this.freeSignupSide.setText(signupTxt);
		freeSignupSide.setForeground((signupTxt.startsWith("ACTIVE") ? darkgreen : Color.RED));
		
		String birthdayTxt = ""+(ClientSession.hasBirthdayEntree ? "ACTIVE! Claim your free entree!" : "INACTIVE");
		this.bdayTxtEntree.setText("Free birthday ("+ClientSession.birthday+") Entree:");
		this.freeBirthdayEntree.setText(birthdayTxt);
		freeBirthdayEntree.setForeground((birthdayTxt.startsWith("ACTIVE") ? darkgreen : Color.RED));
		
		String lotteryTxt = ""+(ClientSession.hasFreeDessert ? "ACTIVE! Claim your free dessert!" : "INACTIVE");
		this.freeLotteryDessert.setText(lotteryTxt);
		freeLotteryDessert.setForeground((lotteryTxt.startsWith("ACTIVE") ? darkgreen : Color.RED));
		
//		if(showLogin)
//			JFrameUtils.showMessage("Rewards Account", "Successfully logged in. Welcome back, "+ClientSession.name+"!");
	}
}
