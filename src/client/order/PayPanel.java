package client.order;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import client.Client;
import client.games.GuessTheNumber;
import client.games.LotteryPanel;
import client.utils.Constants;
import client.utils.JFrameUtils;

//Manasa Nimmagadda



public class PayPanel extends JPanel {

	private static final long serialVersionUID = -1338118268945423515L;
	private JTextField email_prompt_box;
	private JTextField email_prompt_box1;
	private JLabel email_box;
	private JButton submitbtn1;
	public LotteryPanel lottery;
	public JPanel bothbtn_screen;
	public JPanel nobtn_screen;
	public JPanel printbtn_screen;
	public JPanel conf_screen;
	public static DecimalFormat df2 = new DecimalFormat("#.##");
	public static String prevScreen="";
	private JTextField tip_box;
	public double tax = 0.0825;
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
		
		//main panel that opens after clicking on pay
		JPanel main_panel = new JPanel();
		main_panel.setLayout(null);
		main_panel.setBounds(0, 0, 1039, 522);
		add(main_panel);
		
		//shows order total
		JTextArea order_textfield = new JTextArea();
		order_textfield.setWrapStyleWord(true);
		order_textfield.setLineWrap(true);
		order_textfield.setEditable(false);
		order_textfield.setBounds(270, 0, 500, 450);
		order_textfield.setText("Order: "+ getOrderToString());
		main_panel.add(order_textfield);
		
		//split button if customer wants to split the bill
		JButton SplitBtn = new JButton("Split Bill");
		SplitBtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		SplitBtn.setBounds(270, 451, 250, 71);
		main_panel.add(SplitBtn);
		
		//button if customer is paying full bill
		JButton FullBtn = new JButton("Full Bill");
		FullBtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		FullBtn.setBounds(520, 451, 250, 71);
		main_panel.add(FullBtn);
		
		//a new panel pops up asking for payment type
		JPanel full_pay_panel = new JPanel();
		full_pay_panel.setBounds(0, 0, 1039, 522);
		add(full_pay_panel);
		full_pay_panel.setLayout(null);
		
		//panel asking for payment when bill is split
		JPanel split_pay_panel = new JPanel();
		split_pay_panel.setBounds(0, 0, 988, 522);
		add(split_pay_panel);
		split_pay_panel.setLayout(null);
		
		
		//shows the order bill
		JTextArea pay_popup_window = new JTextArea();
		pay_popup_window.setWrapStyleWord(true);
		pay_popup_window.setLineWrap(true);
		pay_popup_window.setEditable(false);
		pay_popup_window.setText("Order: "+ getOrderToString());
		pay_popup_window.setFont(new Font("Monospaced", Font.PLAIN, 13));
		pay_popup_window.setBounds(270, 0, 500, 450);
		full_pay_panel.add(pay_popup_window);
		
		//if customer, paying fully, wants to pay with card
		JButton cardbtn = new JButton("CREDIT/DEBIT CARD");
		cardbtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		cardbtn.setBounds(270, 451, 250, 71);
		full_pay_panel.add(cardbtn);
		
		//if customer, paying fully, wants to pay with cash
		JButton Cashbtn = new JButton("CASH");
		Cashbtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		Cashbtn.setBounds(520, 451, 250, 71);
		full_pay_panel.add(Cashbtn);
		
		//if customer, paying with split, wants to pay with card
		JButton cardbtn1 = new JButton("CREDIT/DEBIT CARD");
		cardbtn1.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		cardbtn1.setBounds(175, 451, 345, 71);
		split_pay_panel.add(cardbtn1);
		
		//if customer, paying with split bill, wants to pay with cash
		JButton Cashbtn1 = new JButton("CASH");
		Cashbtn1.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		Cashbtn1.setBounds(520, 451, 372, 71);
		split_pay_panel.add(Cashbtn1);
		
		//back button if on full pay screen if customer chooses to split the bill
		JButton backbtn = new JButton("BACK");
		backbtn.setFont(new Font("Tahoma", Font.PLAIN, 35));
		backbtn.setBounds(0, 400, 159, 122);
		full_pay_panel.add(backbtn);
		
		//back button if on split pay screen if customer chooses to pay full bill
		JButton backbtn1 = new JButton("BACK");
		backbtn1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		backbtn1.setBounds(0, 400, 159, 122);
		split_pay_panel.add(backbtn1);
		
		//screen to select items which customer wants to pay for
		JTextArea split_selection_panel = new JTextArea();
		split_selection_panel.setText("Select Items:");
		split_selection_panel.setFont(new Font("Monospaced", Font.BOLD, 13));
		split_selection_panel.setEditable(false);
		split_selection_panel.setBounds(174, 0, 345, 450);
		split_pay_panel.add(split_selection_panel);
		
		//screen showing bill for selceted items
		JTextArea splititems_bill_panel = new JTextArea();
		splititems_bill_panel.setText("Your Bill:");
		splititems_bill_panel.setEditable(false);
		splititems_bill_panel.setFont(new Font("Monospaced", Font.BOLD, 13));
		splititems_bill_panel.setBounds(520, 0, 372, 451);
		split_pay_panel.add(splititems_bill_panel);
		
		//screen when customer selects payment type as cash 
		JPanel screen_for_cash = new JPanel();
		screen_for_cash.setBounds(0, 0, 1039, 522);
		add(screen_for_cash);
		screen_for_cash.setLayout(null);
		
		//notification to let the customer know wait-staff will assist
		JTextArea assistance_popup = new JTextArea();
		assistance_popup.setText("\r\n\r\n\r\n       PLEASE WAIT....YOU WILL BE ASSISTED SHORTLY BY OUR WAIT-STAFF");
		assistance_popup.setFont(new Font("Haettenschweiler", Font.BOLD, 35));
		assistance_popup.setEditable(false);
		assistance_popup.setBounds(51, 80, 936, 271);
		screen_for_cash.add(assistance_popup);
		
		//after payment, done button to go to receipt selection
		JButton Donebtn = new JButton("DONE");
		Donebtn.setEnabled(true);
		Donebtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		Donebtn.setBounds(445, 372, 150, 150);
		screen_for_cash.add(Donebtn);
		
		//screen asking receipt type
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
		
		//button for user to select no receipt
		JButton Nobtn = new JButton("NO RECEIPT");
		Nobtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		Nobtn.setBounds(379, 471, 295, 51);
		receipt_type_popup.add(Nobtn);
		
		//button for user to select both(print and email) receipts
		JButton bothbtn = new JButton("BOTH");
		bothbtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		bothbtn.setBounds(378, 410, 295, 51);
		receipt_type_popup.add(bothbtn);
		
		//button for user to select email receipt
		JButton emailbtn = new JButton("EMAIL RECEIPT");
		emailbtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		emailbtn.setBounds(379, 345, 288, 51);
		receipt_type_popup.add(emailbtn);
		
		//button for user to select print receipt
		JButton printbtn = new JButton("PRINT RECEIPT");
		printbtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		printbtn.setBounds(378, 278, 291, 51);
		receipt_type_popup.add(printbtn);
		
		//screen when user selects payment method as card
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
		
		//done button after payment to enter receipt type
		JButton Donebtn2 = new JButton("DONE");
		Donebtn2.setBounds(445, 372, 150, 150);
		screen_for_card.add(Donebtn2);
		
		//when customer selects print receipt, a screen with receipt pops up
		JPanel printbtn_screen = new JPanel();
		printbtn_screen.setVisible(false);
		printbtn_screen.setBounds(0, 0, 1039, 522);
		add(printbtn_screen);
		printbtn_screen.setLayout(null);
		
		JLabel screen_receipt = new JLabel("YOUR RECEIPT");
		screen_receipt.setBounds(99, 87, 824, 429);
		printbtn_screen.add(screen_receipt);
		
		//when customer selects email receipt, a screen prompting for email pops up
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
		
		//submit button after entering the email
		JButton submitbtn;
		submitbtn = new JButton("SUBMIT");
		submitbtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		submitbtn.setBounds(693, 345, 164, 68);
		emailbtn_screen.add(submitbtn);
		
		//when customer selects both, screen with email prompt and receipt pops up
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
		
		email_prompt_box1 = new JTextField();
		email_prompt_box1.setFont(new Font("Haettenschweiler", Font.PLAIN, 25));
		email_prompt_box1.setBounds(640, 249, 348, 68);
		bothbtn_screen.add(email_prompt_box1);
		email_prompt_box1.setColumns(10);
		
		//submit button after entering the email id for both type
		JButton submitbtn1 = new JButton("SUBMIT");
		submitbtn1.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		submitbtn1.setBounds(794, 322, 149, 57);
		bothbtn_screen.add(submitbtn1);
		
		JLabel lblNewLabel_1 = new JLabel("  YOUR RECEIPT");
		lblNewLabel_1.setBounds(0, 0, 521, 522);
		bothbtn_screen.add(lblNewLabel_1);
		
		//screen when user selects no receipt
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
		
		//screen after email submission
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
		
		//new panel to prompt, customers paying with card, for tip 
		JPanel tip_panel = new JPanel();
		tip_panel.setBounds(0, 0, 1039, 522);
		add(tip_panel);
		tip_panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("ADD A TIP");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Haettenschweiler", Font.BOLD, 30));
		lblNewLabel_2.setBounds(51, 16, 936, 105);
		tip_panel.add(lblNewLabel_2);
		
		//button to select tip awarding 15%
		JButton tipbtn_3 = new JButton("15%");
		tipbtn_3.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		tipbtn_3.setBounds(50, 153, 301, 202);
		tip_panel.add(tipbtn_3);
		
		//button to select tip awarding 20%
		JButton tipbtn_2 = new JButton("20%");
		tipbtn_2.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		tipbtn_2.setBounds(362, 153, 301, 202);
		tip_panel.add(tipbtn_2);
		
		//button to select tip awarding 25%
		JButton tipbtn_1 = new JButton("25%");
		tipbtn_1.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		tipbtn_1.setBounds(676, 153, 301, 207);
		tip_panel.add(tipbtn_1);
		
		//if customer wants to enter custome amount
		JLabel custom_tip = new JLabel("CUSTOM TIP:");
		custom_tip.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		custom_tip.setBounds(51, 370, 272, 71);
		tip_panel.add(custom_tip);
		
		tip_box = new JTextField();
		tip_box.setBounds(223, 373, 301, 68);
		tip_panel.add(tip_box);
		tip_box.setColumns(10);
		
		//button to submit the entered custom tip
		JButton tip_donebtn = new JButton("DONE");
		tip_donebtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		tip_donebtn.setBounds(622, 371, 164, 68);
		tip_panel.add(tip_donebtn);
		
		//if customer chooses to not award tip
		JButton no_tipbtn = new JButton("NO TIP");
		no_tipbtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		no_tipbtn.setBounds(57, 451, 235, 55);
		tip_panel.add(no_tipbtn);
		
		
		
		//when customer clicks full pay button, a panel to pay full bill pops up 
		FullBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				full_pay_panel.setVisible(true);
				main_panel.setVisible(false);
				receipt_type_popup.setVisible(false);
				split_pay_panel.setVisible(false);
			}
		});
		
		//when customer clicks split pay button, a panel to select items pops up
		SplitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				split_pay_panel.setVisible(true);
				main_panel.setVisible(false);
				full_pay_panel.setVisible(false);
				receipt_type_popup.setVisible(false);
				
			}
		});
		
		//back button for full pay screen
		backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				full_pay_panel.setVisible(false);
				main_panel.setVisible(true);
				receipt_type_popup.setVisible(false);
				
			}
		});
		
		//back button for split bill screen
		backbtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				split_pay_panel.setVisible(false);
				main_panel.setVisible(true);
				receipt_type_popup.setVisible(false);
				
			}
		});

		
		//actions after clicking cash button for full pay screen
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
		
		//actions after clicking card button in full pay screen
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
		
		//actions after clicking cash button in split pay screen
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
		
		//actions after clicking card button in split pay screen
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
				tip_panel.setVisible(true);
				receipt_type_popup.setVisible(false);
				screen_for_cash.setVisible(false);
				screen_for_card.setVisible(false);
				
			}
		});
		

		//when user selects 25% tip
		tipbtn_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//functionality to add 15% of the tip to total
				receipt_type_popup.setVisible(true);
			}
		});
		
		//when user selects 20% tip
		tipbtn_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//functionality to add 20% of the tip to total
				
				receipt_type_popup.setVisible(true);
			}
		});
		
		//when user selects 15% tip
		tipbtn_3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//functionality to add 15% of the tip to total
				receipt_type_popup.setVisible(true);
			}
		});
		
		//when user chooses no tip option 
		no_tipbtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				receipt_type_popup.setVisible(true);
			}
		});
		
		//if user enters custom tip
		tip_donebtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//functionality to add custom amount to total 
				receipt_type_popup.setVisible(true);
			}
		});
		

		printbtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				prevScreen="printbtn_screen";
				printbtn_screen.setVisible(true);
				viewOrderDetails();
				screen_for_cash.setVisible(false);
				screen_for_card.setVisible(false);
				receipt_type_popup.setVisible(false);
				emailbtn_screen.setVisible(false);
				bothbtn_screen.setVisible(false);
				nobtn_screen.setVisible(false);
				conf_screen.setVisible(false);
				//LotteryChoice();
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
				viewOrderDetails();
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
				prevScreen="conf_screen";
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
				String email = email_prompt_box1.getText();
				if(email.equals("") || email.equals(null) || !Constants.isValidEmail(email)) {
					JFrameUtils.showMessage("Error", "Invalid email entered, please try again.");
					return;
				}
				else
				{
				prevScreen="conf_screen";
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
				prevScreen="nobtn_screen";
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
		tip_panel.setVisible(false);
		screen_for_cash.setVisible(false);
		split_pay_panel.setVisible(false);
	}	
	
	public void LotteryChoice()
	{
			//still working on getting conf_screen to open
			boolean confirm = JFrameUtils.confirmDialog("LOTTERY CHANCE", "Would you like to play a game for a chance to win a free desert?");
        	if(!confirm) {
        		prevOption(prevScreen);
        	}
        	else {
        		this.lottery.setVisible(true);
        	}
			
	}
	
	public void prevOption(String type)
	{
		switch(type) {
			case "conf_screen":
				conf_screen.setVisible(true);
				break;
			case "printbtn_screen":
				printbtn_screen.setVisible(true);
				viewOrderDetails();
				break;
			case "nobtn_screen":
				nobtn_screen.setVisible(true);
				break;
			
		}	
	}
	
	public void viewOrderDetails() {
		
		JPanel panel = new JPanel();
		panel.setBounds(200, 100, 500, 400);
		panel.setLayout(null);
		add(panel);
		
		// Create our scroll pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 500, 560);
		panel.add(scrollPane);
		
		JTextArea orderSummary = new JTextArea();
		orderSummary.setFont(new Font("Tahoma", Font.PLAIN, 14));
		orderSummary.setEditable(false);
		orderSummary.setLineWrap(true);
		orderSummary.setText(getOrderToString());
		scrollPane.setViewportView(orderSummary);
	}
	
	public String getOrderToString() {
		DecimalFormat df2 = new DecimalFormat("#.##");
		StringBuilder s = new StringBuilder();
		//s.append("Order:\n\n");

		for(MItem i : CustomerOrder.items) {
			s.append("x"+i.qty+" "+i.name+" - "+i.price+"\n");

			// Order Menu Item
			String[] newIngTok = i.ingredients.split(",");

			for(int index = 0; index < newIngTok.length; index++) {
				String[] newIng = newIngTok[index].split(":");
				// ingredients
				s.append("    - x"+newIng[1]+" "+newIng[0]+"\n");
			}
			if(!(i.specialReqs.equalsIgnoreCase("none")) 
					&& !i.specialReqs.equals("") && !i.specialReqs.equals(null)) {
				s.append("    - "+i.specialReqs+"\n");
			}
		}
		s.append("Total: $" + df2.format(CustomerOrder.subtotal +(CustomerOrder.subtotal + tax)));
		return s.toString();
	}

}
