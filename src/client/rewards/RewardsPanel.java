package client.rewards;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import client.Client;
import client.ClientSession;
import client.utils.Constants;
import client.utils.JFrameUtils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

public class RewardsPanel extends JPanel {

	private static final long serialVersionUID = -1338118268945423515L;
	
	private JTextField rwdsID;
	private JTextField newEmail;
	
	public JComboBox<String> monthCB;
	public JComboBox<String> dayCB;
	public JComboBox<String> yearCB;

	/**
	 * Create the panel.
	 */
	public RewardsPanel() {
		super();
		setBounds(0, 0, 1039, 522);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Please login to rewards below!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(320, 13, 364, 47);
		add(lblNewLabel);
		
		JLabel lblUserIdemail = new JLabel("User ID (Email):");
		lblUserIdemail.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblUserIdemail.setBounds(67, 99, 220, 47);
		add(lblUserIdemail);
		
		rwdsID = new JTextField();
		/*
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println(e.getExtendedKeyCode());
			}
		});
		*/
		rwdsID.setFont(new Font("Tahoma", Font.PLAIN, 24));
		rwdsID.setBounds(299, 99, 426, 47);
		add(rwdsID);
		rwdsID.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitLoginRequest();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnNewButton.setBounds(795, 99, 132, 47);
		add(btnNewButton);
		
		JTextArea txtrnewToRewards = new JTextArea();
		txtrnewToRewards.setLineWrap(true);
		txtrnewToRewards.setFont(new Font("Monospaced", Font.PLAIN, 20));
		txtrnewToRewards.setEditable(false);
		txtrnewToRewards.setText("New to rewards? Sign up below to access free appetizers and entrees! "
				+ "The best part? It's completely free, so get started today!!");
		txtrnewToRewards.setBounds(136, 212, 695, 101);
		add(txtrnewToRewards);
		
		newEmail = new JTextField();
		newEmail.setFont(new Font("Tahoma", Font.PLAIN, 24));
		newEmail.setColumns(10);
		newEmail.setBounds(299, 345, 426, 47);
		add(newEmail);
		
		JLabel lblNewEmail = new JLabel("New Email:");
		lblNewEmail.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewEmail.setBounds(67, 345, 163, 47);
		add(lblNewEmail);
		
		JButton btnSignUp = new JButton("Sign up!");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signup();
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnSignUp.setBounds(795, 345, 132, 47);
		add(btnSignUp);
		
		JLabel lblBirthdayfreeEntree = new JLabel("Birthday (Free entree every birthday!):");
		lblBirthdayfreeEntree.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBirthdayfreeEntree.setBounds(67, 435, 329, 47);
		add(lblBirthdayfreeEntree);
		
		monthCB = new JComboBox<String>(new String[] {
			"January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December"
		});
		monthCB.setBounds(409, 437, 106, 47);
		add(monthCB);
		
		String[] days = new String[30];
		for(int i = 0; i < days.length; i++)
			days[i] = ""+((i + 1) < 10 ? ("0" + (i + 1)) : (i + 1));
		dayCB = new JComboBox<String>(days);
		dayCB.setBounds(544, 437, 106, 47);
		add(dayCB);
		
		String[] years = new String[101];
		for(int i = 0; i < years.length; i++)
			years[i] = ""+(i + 1920);
		yearCB = new JComboBox<String>(years);
		yearCB.setBounds(673, 435, 157, 47);
		add(yearCB);
		
		JLabel monthLbl = new JLabel("Month");
		monthLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		monthLbl.setBounds(432, 408, 65, 27);
		add(monthLbl);
		
		JLabel dayLbl = new JLabel("Day");
		dayLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dayLbl.setBounds(571, 405, 65, 27);
		add(dayLbl);
		
		JLabel yearLbl = new JLabel("Year");
		yearLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		yearLbl.setBounds(719, 405, 65, 27);
		add(yearLbl);
	}

	protected void signup() {
		String email = newEmail.getText();
		if(email.equals("") || email.equals(null) || !Constants.isValidEmail(email)) {
			JFrameUtils.showMessage("Rewards", "Invalid email entered for rewards, please try again.");
			return;
		}
		int month = monthCB.getSelectedIndex();
		int day = dayCB.getSelectedIndex();
		String birthdate = ((month + 1) < 10 ? ("0" + (month + 1)) : (month + 1))+"/"+
			(day + 1 < 10 ? ("0" + (day + 1)) : (day + 1))+"/"+
				(yearCB.getSelectedIndex() + 1920);
		Client.session.getPacketEncoder().sendCreationRequest(email, birthdate);
	}
	
	public void finishSignup() {
		JFrameUtils.showMessage("Rewards Account", "Success! Your rewards account has been created with the email: "+
			ClientSession.email+" (Birthday: "+ClientSession.birthday+").\nRemember to visit frequently for "
					+ "free entrees after your 5th visit, "
					+ "free entree on your birthday, and\na chance to win a free dessert after every visit!");
	}

	protected void submitLoginRequest() {
		
	}

	public void showLoginInfo(boolean rwdsLgn) {
		
	}
}
