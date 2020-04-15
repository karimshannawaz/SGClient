package client.rewards;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	private JPanel loginPanel;
	private JPanel rewardsPanel;
	
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
	
	
	private JLabel visitsUntilEntree;
	private JLabel freeSignupSide;
	private JLabel freeBirthdayEntree;
	private JLabel freeLotteryDessert;
	

	/**
	 * Create the panel.
	 */
	public RewardsPanel() {
		super();
		setBounds(0, 0, 1039, 522);
		setLayout(null);
		
		showLoginScreen();
		//showRewardsPanel();
	}
	
	private void showRewardsPanel() {
		rewardsPanel = new JPanel();
		rewardsPanel.setVisible(true);
		this.add(rewardsPanel);
		
		rewardsPanel.setBounds(0, 0, 1039, 522);
		rewardsPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome to Seven Guys Rewards, "+ClientSession.name+"!");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(299, 13, 644, 50);
		rewardsPanel.add(lblNewLabel_1);
		
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
		freeSignupSide.setBounds(560, 213, 105, 42);
		rewardsPanel.add(freeSignupSide);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("*** To claim a reward, simply go to checkout on the pay screen, click "
				+ "on discounts and select the one you want to apply! ***");
		lblNewLabel_2_1_1.setBounds(37, 149, 1015, 42);
		lblNewLabel_2_1_1.setFont(new Font("Sitka Subheading", Font.PLAIN, 17));
		rewardsPanel.add(lblNewLabel_2_1_1);
			
		JLabel lblNewLabel_3_2 = new JLabel("Your free birthday () Entree:");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_3_2.setBounds(199, 268, 335, 42);
		rewardsPanel.add(lblNewLabel_3_2);
		
		freeBirthdayEntree = new JLabel("INACTIVE (30 days left)");
		freeBirthdayEntree.setFont(new Font("Tahoma", Font.PLAIN, 21));
		freeBirthdayEntree.setBounds(560, 268, 335, 42);
		rewardsPanel.add(freeBirthdayEntree);
		
		JLabel lblNewLabel_3_2_2 = new JLabel("Visits until free Entree:");
		lblNewLabel_3_2_2.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_3_2_2.setBounds(199, 329, 335, 42);
		rewardsPanel.add(lblNewLabel_3_2_2);
		
		visitsUntilEntree = new JLabel("5");
		visitsUntilEntree.setFont(new Font("Tahoma", Font.PLAIN, 21));
		visitsUntilEntree.setBounds(560, 329, 335, 42);
		rewardsPanel.add(visitsUntilEntree);
		
		JLabel lblNewLabel_3_2_2_1 = new JLabel("Free dessert from lottery game:");
		lblNewLabel_3_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_3_2_2_1.setBounds(199, 384, 335, 42);
		rewardsPanel.add(lblNewLabel_3_2_2_1);
		
		freeLotteryDessert = new JLabel("INACTIVE");
		freeLotteryDessert.setFont(new Font("Tahoma", Font.PLAIN, 21));
		freeLotteryDessert.setBounds(560, 384, 129, 32);
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
		this.showRewardsPanel();
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
	
	public void loginToRewards() {
		this.loginPanel.setVisible(false);
		this.showRewardsPanel();
		JFrameUtils.showMessage("Rewards Account", "Successfully logged in. Welcome back, "+ClientSession.name+"!");
	}
}
