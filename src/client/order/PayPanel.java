package client.order;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import client.Client;
import client.games.GuessTheNumber;
import client.games.LotteryPanel;
import client.utils.Constants;
import client.utils.JFrameUtils;



public class PayPanel extends JPanel {

	private static final long serialVersionUID = -1338118268945423515L;
	private JTextField email_prompt_box;
	private JLabel email_box;
	private JButton submitbtn1;
	public LotteryPanel lottery;
	public JPanel bothbtn_screen;
	public JPanel nobtn_screen;
	public JPanel printbtn_screen;
	public JPanel conf_screen;
	/**
	 * Create the panel.
	 */
	public PayPanel() {
		super();
		setBounds(0, 0, 1039, 522);
		setLayout(null);
		
		this.lottery = new LotteryPanel();
		add(lottery);
		lottery.setVisible(false);
		
		JPanel main_panel = new JPanel();
		main_panel.setLayout(null);
		main_panel.setBounds(0, 0, 1039, 522);
		add(main_panel);
		
		JTextArea order_textfield = new JTextArea();
		order_textfield.setWrapStyleWord(true);
		order_textfield.setLineWrap(true);
		order_textfield.setEditable(false);
		order_textfield.setBounds(270, 0, 500, 450);
		main_panel.add(order_textfield);
		
		JButton SplitBtn = new JButton("Split Bill");
		SplitBtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		SplitBtn.setBounds(270, 451, 250, 71);
		main_panel.add(SplitBtn);
		
		JButton FullBtn = new JButton("Full Bill");
		FullBtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		FullBtn.setBounds(520, 451, 250, 71);
		main_panel.add(FullBtn);
		
		JPanel full_pay_panel = new JPanel();
		full_pay_panel.setBounds(0, 0, 1039, 522);
		add(full_pay_panel);
		full_pay_panel.setLayout(null);
		
		JPanel split_pay_panel = new JPanel();
		split_pay_panel.setBounds(0, 0, 988, 522);
		add(split_pay_panel);
		split_pay_panel.setLayout(null);
		
		JTextArea pay_popup_window = new JTextArea();
		pay_popup_window.setWrapStyleWord(true);
		pay_popup_window.setLineWrap(true);
		pay_popup_window.setEditable(false);
		pay_popup_window.setText("Order: ");
		pay_popup_window.setFont(new Font("Monospaced", Font.PLAIN, 13));
		pay_popup_window.setBounds(270, 0, 500, 450);
		full_pay_panel.add(pay_popup_window);
		
		JButton cardbtn = new JButton("CREDIT/DEBIT CARD");
		cardbtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		cardbtn.setBounds(270, 451, 250, 71);
		full_pay_panel.add(cardbtn);
		
		
		JButton Cashbtn = new JButton("CASH");
		Cashbtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		Cashbtn.setBounds(520, 451, 250, 71);
		full_pay_panel.add(Cashbtn);
		
		
		JButton cardbtn1 = new JButton("CREDIT/DEBIT CARD");
		cardbtn1.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		cardbtn1.setBounds(175, 451, 345, 71);
		split_pay_panel.add(cardbtn1);
		
		JButton Cashbtn1 = new JButton("CASH");
		Cashbtn1.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		Cashbtn1.setBounds(520, 451, 372, 71);
		split_pay_panel.add(Cashbtn1);
		
		JButton backbtn = new JButton("BACK");
		
		backbtn.setFont(new Font("Tahoma", Font.PLAIN, 35));
		backbtn.setBounds(0, 400, 159, 122);
		full_pay_panel.add(backbtn);
		
		JButton backbtn1 = new JButton("BACK");
		
		backbtn1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		backbtn1.setBounds(0, 400, 159, 122);
		split_pay_panel.add(backbtn1);
		
		JTextArea split_selection_panel = new JTextArea();
		split_selection_panel.setText("Select Items:");
		split_selection_panel.setFont(new Font("Monospaced", Font.BOLD, 13));
		split_selection_panel.setEditable(false);
		split_selection_panel.setBounds(174, 0, 345, 450);
		split_pay_panel.add(split_selection_panel);
		
		JTextArea splititems_bill_panel = new JTextArea();
		splititems_bill_panel.setText("Your Bill:");
		splititems_bill_panel.setEditable(false);
		splititems_bill_panel.setFont(new Font("Monospaced", Font.BOLD, 13));
		splititems_bill_panel.setBounds(520, 0, 372, 451);
		split_pay_panel.add(splititems_bill_panel);
				
		JPanel screen_for_cash = new JPanel();
		screen_for_cash.setBounds(0, 0, 1039, 522);
		add(screen_for_cash);
		screen_for_cash.setLayout(null);
		
		JTextArea assistance_popup = new JTextArea();
		assistance_popup.setText("\r\n\r\n\r\n       PLEASE WAIT....YOU WILL BE ASSISTED SHORTLY BY OUR WAIT-STAFF");
		assistance_popup.setFont(new Font("Haettenschweiler", Font.BOLD, 35));
		assistance_popup.setEditable(false);
		assistance_popup.setBounds(51, 80, 936, 271);
		screen_for_cash.add(assistance_popup);
		
		JButton Donebtn = new JButton("DONE");
		Donebtn.setEnabled(true);
		Donebtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		Donebtn.setBounds(445, 372, 150, 150);
		screen_for_cash.add(Donebtn);
		
		JPanel receipt_type_popup = new JPanel();
		receipt_type_popup.setVisible(false);
		receipt_type_popup.setBounds(0, 0, 1039, 522);
		add(receipt_type_popup);
		receipt_type_popup.setLayout(null);
		
		JTextArea receipt_tab1 = new JTextArea();
		receipt_tab1.setText("\r\n                 HOW WOULD YOU LIKE YOUR RECIEPT?");
		receipt_tab1.setFont(new Font("Haettenschweiler", Font.BOLD, 30));
		receipt_tab1.setEditable(false);
		receipt_tab1.setBounds(177, 133, 719, 117);
		receipt_type_popup.add(receipt_tab1);
		
		JLabel receipt_tab = new JLabel("                  PAYMENT SUCCESSFUL");
		receipt_tab.setFont(new Font("Impact", Font.BOLD, 35));
		receipt_tab.setBounds(173, 16, 719, 101);
		receipt_type_popup.add(receipt_tab);
		
		JButton Nobtn = new JButton("NO RECEIPT");
		Nobtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		Nobtn.setBounds(379, 471, 295, 51);
		receipt_type_popup.add(Nobtn);
		
		JButton bothbtn = new JButton("BOTH");
		bothbtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		bothbtn.setBounds(378, 410, 295, 51);
		receipt_type_popup.add(bothbtn);
		
		JButton emailbtn = new JButton("EMAIL RECEIPT");
		emailbtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		emailbtn.setBounds(379, 345, 288, 51);
		receipt_type_popup.add(emailbtn);
		
		JButton printbtn = new JButton("PRINT RECEIPT");
		printbtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		printbtn.setBounds(378, 278, 291, 51);
		receipt_type_popup.add(printbtn);
		
		JPanel screen_for_card = new JPanel();
		screen_for_card.setVisible(false);
		screen_for_card.setBounds(0, 0, 1039, 522);
		add(screen_for_card);
		screen_for_card.setLayout(null);
		
		JTextArea steps_popup = new JTextArea();
		steps_popup.setEditable(false);
		steps_popup.setFont(new Font("Haettenschweiler", Font.BOLD, 35));
		steps_popup.setText("\r\n\r\n\t\t\r\n\t       SWIPE/INSERT THE CARD....");
		steps_popup.setBounds(51, 80, 936, 271);
		screen_for_card.add(steps_popup);
		
		JButton Donebtn2 = new JButton("DONE");
		Donebtn2.setBounds(445, 372, 150, 150);
		screen_for_card.add(Donebtn2);
		
		JPanel printbtn_screen = new JPanel();
		printbtn_screen.setVisible(false);
		printbtn_screen.setBounds(0, 0, 1039, 522);
		add(printbtn_screen);
		printbtn_screen.setLayout(null);
		
		JLabel screen_receipt = new JLabel("YOUR RECEIPT");
		screen_receipt.setBounds(99, 87, 824, 429);
		printbtn_screen.add(screen_receipt);
		
		JPanel emailbtn_screen = new JPanel();
		emailbtn_screen.setVisible(false);
		emailbtn_screen.setBounds(0, 0, 1039, 522);
		add(emailbtn_screen);
		emailbtn_screen.setLayout(null);
		
		JLabel lblNewLabel;
		email_box = new JLabel("EMAIL ID:");
		email_box.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		email_box.setBounds(188, 249, 228, 68);
		emailbtn_screen.add(email_box);
		
		JLabel email_prompt = new JLabel("               PLEASE ENTER YOUR EMAIL ID");
		email_prompt.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		email_prompt.setBounds(169, 61, 713, 128);
		emailbtn_screen.add(email_prompt);
		
		email_prompt_box = new JTextField();
		email_prompt_box.setFont(new Font("Haettenschweiler", Font.PLAIN, 25));
		email_prompt_box.setBounds(312, 249, 486, 68);
		emailbtn_screen.add(email_prompt_box);
		email_prompt_box.setColumns(10);
		
		
		JButton submitbtn;
		submitbtn = new JButton("SUBMIT");
		submitbtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		submitbtn.setBounds(693, 345, 164, 68);
		emailbtn_screen.add(submitbtn);
		
		JPanel bothbtn_screen = new JPanel();
		bothbtn_screen.setVisible(false);
		bothbtn_screen.setBounds(0, 0, 1039, 522);
		add(bothbtn_screen);
		bothbtn_screen.setLayout(null);
		
		lblNewLabel = new JLabel("EMAIL ID:");
		lblNewLabel.setBounds(491, 249, 197, 68);
		bothbtn_screen.add(lblNewLabel);
		
		email_prompt = new JLabel("               PLEASE ENTER YOUR EMAIL ID");
		email_prompt.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		email_prompt.setBounds(591, 61, 340, 128);
		bothbtn_screen.add(email_prompt);
		
		email_prompt_box = new JTextField();
		email_prompt_box.setFont(new Font("Haettenschweiler", Font.PLAIN, 25));
		email_prompt_box.setBounds(640, 249, 348, 68);
		bothbtn_screen.add(email_prompt_box);
		email_prompt_box.setColumns(10);
		
		
		JButton submitbtn1 = new JButton("SUBMIT");
		submitbtn1.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		submitbtn1.setBounds(794, 322, 149, 57);
		bothbtn_screen.add(submitbtn1);
		
		JLabel lblNewLabel_1 = new JLabel("  YOUR RECEIPT");
		lblNewLabel_1.setBounds(0, 0, 521, 522);
		bothbtn_screen.add(lblNewLabel_1);
		
		JPanel nobtn_screen = new JPanel();
		nobtn_screen.setVisible(false);
		nobtn_screen.setBounds(0, 0, 1039, 522);
		add(nobtn_screen);
		nobtn_screen.setLayout(null);
		
		JTextArea bye_screen = new JTextArea();
		bye_screen.setEditable(false);
		bye_screen.setText("\r\n\r\n\t       THANK YOU FOR VISITING!\r\n\t           HAVE A GOOD DAY!");
		bye_screen.setFont(new Font("Haettenschweiler", Font.BOLD, 30));
		bye_screen.setBounds(133, 100, 769, 297);
		nobtn_screen.add(bye_screen);
		
		JPanel conf_screen = new JPanel();
		conf_screen.setVisible(false);
		conf_screen.setBounds(0, 0, 1039, 522);
		add(conf_screen);
		conf_screen.setLayout(null);
		
		JTextArea confirmation_screen = new JTextArea();
		confirmation_screen.setEditable(false);
		confirmation_screen.setText("\n  A BILLING RECEIPT IS SENT TO THIS EMAIL SUCCESSFULLY\r\n                           THANK YOU FOR VISITING!!\n           \r\t         HAVE A GOOD DAY!");
		confirmation_screen.setFont(new Font("Haettenschweiler", Font.BOLD, 30));
		confirmation_screen.setBounds(169, 97, 662, 179);
		conf_screen.add(confirmation_screen);
		
		
		
		
		FullBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				full_pay_panel.setVisible(true);
				main_panel.setVisible(false);
				receipt_type_popup.setVisible(false);
				split_pay_panel.setVisible(false);
			}
		});
		
		SplitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				split_pay_panel.setVisible(true);
				main_panel.setVisible(false);
				full_pay_panel.setVisible(false);
				receipt_type_popup.setVisible(false);
				
			}
		});
		
		backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				full_pay_panel.setVisible(false);
				main_panel.setVisible(true);
				receipt_type_popup.setVisible(false);
				
			}
		});
		
		backbtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				split_pay_panel.setVisible(false);
				main_panel.setVisible(true);
				receipt_type_popup.setVisible(false);
				
			}
		});

		Cashbtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				boolean confirmPayment = JFrameUtils.confirmDialog("Confirm payment method.", "Once proceeded you can't change payment type");
				if(!confirmPayment) {
					return;
				}
				else {
				screen_for_cash.setVisible(true);
				full_pay_panel.setVisible(false);
				receipt_type_popup.setVisible(false);				
				Donebtn.setVisible(true);
				}
			}
				
		});
		
		
		//code to make them pay with cash and give bill
		
		cardbtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				boolean confirmPayment = JFrameUtils.confirmDialog("Confirm Payment Method", "Once proceeded you can't change payment type");
				if(!confirmPayment) {
					return;
				}
				else {
				screen_for_card.setVisible(true);
				full_pay_panel.setVisible(false);
				screen_for_cash.setVisible(false);
				receipt_type_popup.setVisible(false);
				}
			}
		});
		
		
		//code to send card payment verification to server
		
		Cashbtn1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				boolean confirmPayment = JFrameUtils.confirmDialog("Confirm payment method.", "Once proceeded you can't change payment type");
				if(!confirmPayment) {
					return;
				}
				else {
				screen_for_cash.setVisible(true);
				split_pay_panel.setVisible(false);
				receipt_type_popup.setVisible(false);				
				Donebtn.setVisible(true);
				}
			}
		});
		
		
		//code here goes to make them pay with cash and give bill
		
		cardbtn1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				boolean confirmPayment = JFrameUtils.confirmDialog("Confirm payment method.", "Once proceeded you can't change payment type");
				if(!confirmPayment) {
					return;
				}
				else {
				screen_for_card.setVisible(true);
				split_pay_panel.setVisible(false);
				screen_for_cash.setVisible(false);
				receipt_type_popup.setVisible(false);
				}
			}
		});
		
		
		//code here goes for server approval before proceeding to receipt type
		
		//done button for cash payment
		Donebtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				receipt_type_popup.setVisible(true);
				screen_for_cash.setVisible(false);
				screen_for_card.setVisible(false);
				
			}
		});
		
		//done button for card payment
		Donebtn2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				receipt_type_popup.setVisible(true);
				screen_for_cash.setVisible(false);
				screen_for_card.setVisible(false);
				
			}
		});
		
		//when user selects print for receipt type
		printbtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				printbtn_screen.setVisible(true);
				screen_for_cash.setVisible(false);
				screen_for_card.setVisible(false);
				receipt_type_popup.setVisible(false);
				emailbtn_screen.setVisible(false);
				bothbtn_screen.setVisible(false);
				nobtn_screen.setVisible(false);
				conf_screen.setVisible(false);
			}
		});
		
		//when user selects email for receipt type
		emailbtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				emailbtn_screen.setVisible(true);
				printbtn_screen.setVisible(false);
				screen_for_cash.setVisible(false);
				screen_for_card.setVisible(false);
				receipt_type_popup.setVisible(false);
				bothbtn_screen.setVisible(false);
				nobtn_screen.setVisible(false);
				conf_screen.setVisible(false);
			}
		});
		
		//when user selects both for receipt type
		bothbtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				bothbtn_screen.setVisible(true);
				printbtn_screen.setVisible(false);
				screen_for_cash.setVisible(false);
				screen_for_card.setVisible(false);
				receipt_type_popup.setVisible(false);
				emailbtn_screen.setVisible(false);
				nobtn_screen.setVisible(false);
				conf_screen.setVisible(false);
				
			}
		});
		
		//when user clicks submit for entering email id
		submitbtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String email = email_prompt_box.getText();
				if(email.equals("") || email.equals(null) || !Constants.isValidEmail(email)) {
					JFrameUtils.showMessage("Error", "Invalid email entered, please try again.");
					return;
				}
				else
				{
				//conf_screen.setVisible(true);
				nobtn_screen.setVisible(false);
				printbtn_screen.setVisible(false);
				screen_for_cash.setVisible(false);
				screen_for_card.setVisible(false);
				receipt_type_popup.setVisible(false);
				bothbtn_screen.setVisible(false);
				emailbtn_screen.setVisible(false);
				LotteryChoice();
				}
				
			}
		});
		
		//submit button after entering email for both types of receipt
		submitbtn1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String email = email_prompt_box.getText();
				if(email.equals("") || email.equals(null) || !Constants.isValidEmail(email)) {
					JFrameUtils.showMessage("Error", "Invalid email entered, please try again.");
					return;
				}
				else
				{
				
				//conf_screen.setVisible(true);
				nobtn_screen.setVisible(false);
				printbtn_screen.setVisible(false);
				screen_for_cash.setVisible(false);
				screen_for_card.setVisible(false);
				receipt_type_popup.setVisible(false);
				bothbtn_screen.setVisible(false);
				emailbtn_screen.setVisible(false);
				LotteryChoice();
				}
				
			}
		});
		
		//when user clicks no receipt
		Nobtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//nobtn_screen.setVisible(true);
				printbtn_screen.setVisible(false);
				screen_for_cash.setVisible(false);
				screen_for_card.setVisible(false);
				receipt_type_popup.setVisible(false);
				bothbtn_screen.setVisible(false);
				emailbtn_screen.setVisible(false);
				conf_screen.setVisible(false);
				LotteryChoice();
			}
		});
		screen_for_cash.setVisible(false);
		split_pay_panel.setVisible(false);
	}	
	
	public void LotteryChoice()
	{
			//kinda buggy
			boolean confirm = JFrameUtils.confirmDialog("LOTTERY CHANCE", "Would you like to play a game for a chance to win a free desert?");
        	if(!confirm) {
        		conf_screen.setVisible(true);
        	}
        	else {
        		this.lottery.setVisible(true);
        	}
			
	}
	/*protected void bill_email() {
		String email = email_prompt_box.getText();
		if(email.equals("") || email.equals(null) || !Constants.isValidEmail(email)) {
			JFrameUtils.showMessage("Error", "Invalid email entered, please try again.");
			return;
		}
	}*/
}
