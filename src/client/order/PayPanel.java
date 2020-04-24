package client.order;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JSplitPane;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import client.Client;
import client.ClientSession;
import client.games.GuessTheNumber;
import client.games.LotteryPanel;
import client.utils.Constants;
import client.utils.JFrameUtils;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;



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
	public static String prevScreen="";
	private JTextField tip_box;
	public double tax = 0.0825;
	public double tip=0;
	
	public JPanel main_panel;
	private Timer timer;
	public JButton SplitBtn;
	public JButton FullBtn;
	private int delay = 10000;
	
	public JTextArea orderSummary;
	public JTextArea printSummary;
	public JTextArea bothSummary;
	public JTextArea newOrderSummary;
	public JTextArea orderTotal;
	public JTextArea printTotal;
	public JTextArea bothTotal;
	public JTextArea newOrderTotal;
	private JTable Ordertable;
	private JTable Splittable;
	public Double splitSubTotal =0.0;
	public Double amountpaid = 0.0;
	public Boolean PrevSplit=false;
	public String oldOrder;
	
	public JLabel amtPaid;
	
	
	/**
	 * Create the panel.
	 */
	public PayPanel() {
		super();
		setBounds(0, 0, 1039, 522);
		setLayout(null);
		
		//panel for the lottery game
		this.lottery = new LotteryPanel();
		add(lottery);
		lottery.setVisible(false);
		
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
		
		//screen when user selects payment method as card
		JPanel screen_for_card = new JPanel();
		screen_for_card.setVisible(false);
		screen_for_card.setBounds(0, 0, 1039, 522);
		add(screen_for_card);
		screen_for_card.setLayout(null);
		
		//prompts the user to "insert" card into kiosk
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
		
		//setting up the scroll panel for the print reciept side
		JScrollPane scrollPrint = new JScrollPane();
		scrollPrint.setBounds(270, 0, 500, 379);
		printbtn_screen.add(scrollPrint);
		
		//holds the summary of the printed order
		printSummary = new JTextArea();
		printSummary.setFont(new Font("Tahoma", Font.PLAIN, 14));
		printSummary.setEditable(false);
		printSummary.setLineWrap(true);
		printSummary.setText("Order: ");
		scrollPrint.setViewportView(printSummary);
		
		//holds the total of the printed order
		printTotal = new JTextArea();
		printTotal.setFont(new Font("Monospaced", Font.PLAIN, 15));
		printTotal.setEditable(false);
		printTotal.setLineWrap(true);
		printTotal.setBounds(270, 380, 500, 90);
		printTotal.setText("Subtotal: $"+(CustomerOrder.subtotal)+"\nTax:\nTotal:");
		printbtn_screen.add(printTotal);
		
		//lets the user know this is their receipt
		JLabel screen_receipt = new JLabel("YOUR RECEIPT");
		screen_receipt.setBounds(99, 87, 824, 429);
		printbtn_screen.add(screen_receipt);
		printbtn_screen.setVisible(false);
		
		//when customer selects email receipt, a screen prompting for email pops up
		JPanel emailbtn_screen = new JPanel();
		emailbtn_screen.setVisible(false);
		emailbtn_screen.setBounds(0, 0, 1039, 522);
		add(emailbtn_screen);
		emailbtn_screen.setLayout(null);
				
		//label for the email entering section
		email_box = new JLabel("EMAIL ID:");
		email_box.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		email_box.setBounds(188, 249, 228, 68);
		emailbtn_screen.add(email_box);
		
		//label to prompt email entering
		JLabel email_prompt = new JLabel("               PLEASE ENTER YOUR EMAIL ID");
		email_prompt.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		email_prompt.setBounds(169, 61, 713, 128);
		emailbtn_screen.add(email_prompt);
		
		//takes the user email input
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
		
		//screen when user selects no receipt
		JPanel nobtn_screen = new JPanel();
		nobtn_screen.setVisible(false);
		nobtn_screen.setBounds(0, 0, 1039, 522);
		add(nobtn_screen);
		nobtn_screen.setLayout(null);
		
		//lets the user know their transaction is finished
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
		
		//confirming that an email has been sent to the user
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
		
		//prompts user for tip
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
		
		//if customer wants to enter custom amount
		JLabel custom_tip = new JLabel("CUSTOM TIP:");
		custom_tip.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		custom_tip.setBounds(51, 370, 272, 71);
		tip_panel.add(custom_tip);
		
		//holds the users tip amount
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
		
		//panel asking for payment when bill is split
		JPanel split_pay_panel = new JPanel();
		split_pay_panel.setBounds(0, 0, 988, 522);
		add(split_pay_panel);
		split_pay_panel.setLayout(null);
		
		//back button if on split pay screen if customer chooses to pay full bill
		//want to rework
		JButton backbtn1 = new JButton("Change Method");
		backbtn1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		backbtn1.setBounds(0, 471, 159, 51);
		split_pay_panel.add(backbtn1);
		backbtn1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		backbtn1.setBounds(0, 471, 159, 51);
		
		//for splitting the bill this table holds the current full table order
		Ordertable = new JTable();
		Ordertable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Order"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		Ordertable.setBounds(43, 0, 381, 450);
		Ordertable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		Ordertable.setRowSelectionAllowed(true);
		Ordertable.setColumnSelectionAllowed(false);
		split_pay_panel.add(Ordertable);
		
		//for splitting the bill this section holds the split items
		Splittable = new JTable();
		Splittable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Order"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		Splittable.setBounds(567, 0, 381, 450);
		Splittable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		Splittable.setColumnSelectionAllowed(false);
		Splittable.setRowSelectionAllowed(true);
		split_pay_panel.add(Splittable);
		
		//seperates the selected items
		JButton toSplit = new JButton(">");
		toSplit.setBounds(445, 103, 101, 35);
		split_pay_panel.add(toSplit);
		//removes the items form the order side to the split side
		toSplit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel orig = (DefaultTableModel) Ordertable.getModel();
				DefaultTableModel newtable =(DefaultTableModel) Splittable.getModel();
				int[] sel = Ordertable.getSelectedRows();
				Object[] row = new Object[1];
				for(int i=0; i<sel.length; i++)
				{
					row[0]=orig.getValueAt(sel[i], 0);
					newtable.addRow(row);
				}
				for(int i=0; i<sel.length; i++)
				{
					orig.removeRow(sel[i]);
				}
				
			}
		});
		//button to re add options to the order side
		JButton undoSplit = new JButton("<");
		undoSplit.setBounds(445, 150, 101, 35);
		split_pay_panel.add(undoSplit);
		
		undoSplit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel orig = (DefaultTableModel) Splittable.getModel();
				DefaultTableModel newtable =(DefaultTableModel) Ordertable.getModel();
				int[] sel = Splittable.getSelectedRows();
				Object[] row = new Object[1];
				for(int i=0; i<sel.length; i++)
				{
					row[0]=orig.getValueAt(sel[i], 0);
					newtable.addRow(row);
				}
				for(int i=0; i<sel.length; i++)
				{
					orig.removeRow(sel[i]);
				}
				
			}
		});
		
		//confirms the split
		JButton confirmSplit = new JButton("confirm");
		confirmSplit.setBounds(425, 384, 141, 35);
		split_pay_panel.add(confirmSplit);
		
		//screen asking receipt type
		JPanel receipt_type_popup = new JPanel();
		receipt_type_popup.setVisible(false);
		receipt_type_popup.setBounds(0, 0, 1039, 522);
		add(receipt_type_popup);
		receipt_type_popup.setLayout(null);
		
		//label to acknowledge all of the amount paid including tips
		amtPaid = new JLabel();
		amtPaid.setBounds(422, 83, 252, 56);
		amtPaid.setText("Amount Paid: " + decimalF(amountpaid));
		amtPaid.setFont(new Font("Haettenschweiler", Font.PLAIN, 26));
		receipt_type_popup.add(amtPaid);
		
		//prompts user for how to receive receipt
		JTextArea receipt_tab1 = new JTextArea();
		receipt_tab1.setText("\r\n                 HOW WOULD YOU LIKE YOUR RECIEPT?");
		receipt_tab1.setFont(new Font("Haettenschweiler", Font.BOLD, 30));
		receipt_tab1.setEditable(false);
		receipt_tab1.setBounds(204, 133, 670, 117);
		receipt_type_popup.add(receipt_tab1);
		
		//acknowledges a comepleted payment
		JLabel receipt_tab = new JLabel("                  PAYMENT SUCCESSFUL");
		receipt_tab.setFont(new Font("Impact", Font.BOLD, 35));
		receipt_tab.setBounds(207, 11, 719, 101);
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
		
		//for splitting the bill this holds the new order that was split
		newOrderSummary = new JTextArea();
		newOrderSummary.setFont(new Font("Tahoma", Font.PLAIN, 14));
		newOrderSummary.setEditable(false);
		newOrderSummary.setLineWrap(true);
		newOrderSummary.setText("Order: ");
		
		//holds the new total for split items
		newOrderTotal = new JTextArea();
		newOrderTotal.setFont(new Font("Monospaced", Font.PLAIN, 15));
		newOrderTotal.setEditable(false);
		newOrderTotal.setLineWrap(true);
		newOrderTotal.setBounds(270, 380, 500, 71);
		newOrderTotal.setText("Subtotal: $ \n"+"\nTax:\nTotal:");
		
		//panel after splitting that prompts the user for payment of split items
		JPanel Splitpay = new JPanel();
		Splitpay.setLayout(null);
		Splitpay.setBounds(0, 0, 1039, 522);
		add(Splitpay);
		Splitpay.setVisible(false);
		
		//scrollpane to hold the split items on display
		JScrollPane SplitscrollPane = new JScrollPane();
		SplitscrollPane.setBounds(270, 0, 500, 379);
		Splitpay.add(SplitscrollPane);
		SplitscrollPane.setViewportView(newOrderSummary);
		Splitpay.add(newOrderTotal);
		
		//if customer, paying with split, wants to pay with card
		JButton cardbtn1 = new JButton("CREDIT/DEBIT CARD");
		cardbtn1.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		cardbtn1.setBounds(175, 451, 345, 71);
		Splitpay.add(cardbtn1);
		cardbtn1.setVisible(true);
		
		//if customer, paying with split bill, wants to pay with cash
		JButton Cashbtn1 = new JButton("CASH");
		Cashbtn1.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		Cashbtn1.setBounds(520, 451, 372, 71);
		Splitpay.add(Cashbtn1);
		Cashbtn1.setVisible(true);
		
		//button to change method between split and full
		JButton backbtn2 = new JButton("Change Method");
		backbtn2.setBounds(0, 410, 179, 41);
		Splitpay.add(backbtn2);
		backbtn2.setBounds(0, 410, 179, 41);
		
		//when customer selects both, screen with email prompt and receipt pops up
		JPanel bothbtn_screen_1 = new JPanel();
		bothbtn_screen_1.setVisible(false);
		bothbtn_screen_1.setBounds(0, 0, 1039, 522);
		add(bothbtn_screen_1);
		
		//screen for both printing and email
		bothbtn_screen_1.setLayout(null);
		JScrollPane scrollBoth = new JScrollPane();
		scrollBoth.setBounds(167, 0, 422, 379);
		bothbtn_screen_1.add(scrollBoth);
		
		//summary for the both section to be printed
		bothSummary = new JTextArea();
		bothSummary.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bothSummary.setEditable(false);
		bothSummary.setLineWrap(true);
		bothSummary.setText("Order: ");
		scrollBoth.setViewportView(bothSummary);
		
		//total to be printed on both section
		bothTotal = new JTextArea();
		bothTotal.setFont(new Font("Monospaced", Font.PLAIN, 15));
		bothTotal.setEditable(false);
		bothTotal.setLineWrap(true);
		bothTotal.setBounds(167, 379, 422, 90);
		bothTotal.setText("Subtotal: $"+(CustomerOrder.subtotal)+"\nTax:\nTotal:");
		bothbtn_screen_1.add(bothTotal);
		
		//prompts the user for email on both options screen
		JLabel email_promptboth = new JLabel("PLEASE ENTER YOUR EMAIL BELOW");
		email_promptboth.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		email_promptboth.setBounds(661, 172, 327, 68);
		bothbtn_screen_1.add(email_promptboth);
		
		//hods the users email when eneterd
		email_prompt_box1 = new JTextField();
		email_prompt_box1.setFont(new Font("Haettenschweiler", Font.PLAIN, 25));
		email_prompt_box1.setBounds(640, 249, 348, 68);
		bothbtn_screen_1.add(email_prompt_box1);
		email_prompt_box1.setColumns(10);
		
		//submit button after entering the email id for both type
		JButton submitbtn1_1 = new JButton("SUBMIT");
		submitbtn1_1.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		submitbtn1_1.setBounds(794, 322, 149, 57);
		bothbtn_screen_1.add(submitbtn1_1);
		
		//labeling the receipt
		JLabel lblNewLabel_1 = new JLabel("  YOUR RECEIPT");
		lblNewLabel_1.setBounds(0, 0, 521, 522);
		bothbtn_screen_1.add(lblNewLabel_1);
		
		//main panel that opens after clicking on pay
		main_panel = new JPanel();
		main_panel.setLayout(null);
		main_panel.setBounds(0, 0, 1039, 522);
		add(main_panel);
		
		// Create our scroll pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(270, 0, 500, 379);
		main_panel.add(scrollPane);
		
		//holds the inital order summary for the entire table
		orderSummary = new JTextArea();
		orderSummary.setFont(new Font("Tahoma", Font.PLAIN, 14));
		orderSummary.setEditable(false);
		orderSummary.setLineWrap(true);
		orderSummary.setText("Order: ");
		scrollPane.setViewportView(orderSummary);
		
		//holds the order total for the entire table
		orderTotal = new JTextArea();
		orderTotal.setFont(new Font("Monospaced", Font.PLAIN, 15));
		orderTotal.setEditable(false);
		orderTotal.setLineWrap(true);
		orderTotal.setBounds(270, 380, 500, 71);
		orderTotal.setText("Subtotal: $"+(CustomerOrder.subtotal)+"\nTax:\nTotal:");
		main_panel.add(orderTotal);
		
		//split button if customer wants to split the bill
		SplitBtn = new JButton("Split Bill");
		SplitBtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		SplitBtn.setBounds(270, 451, 250, 71);
		SplitBtn.setVisible(false);
		main_panel.add(SplitBtn);
		
		//button if customer is paying full bill
		FullBtn = new JButton("Full Bill");
		FullBtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		FullBtn.setBounds(520, 451, 250, 71);
		FullBtn.setVisible(false);
		main_panel.add(FullBtn);		
		
		//if customer, paying fully, wants to pay with card
		JButton cardbtn = new JButton("CREDIT/DEBIT CARD");
		cardbtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		cardbtn.setBounds(270, 451, 250, 71);
		cardbtn.setVisible(false);
		main_panel.add(cardbtn);
		
		//if customer, paying fully, wants to pay with cash
		JButton Cashbtn = new JButton("CASH");
		Cashbtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		Cashbtn.setBounds(520, 451, 250, 71);
		Cashbtn.setVisible(false);
		main_panel.add(Cashbtn);
		
		//button to change the mthod of payment between split and full
		JButton backbtn3 = new JButton("Change Method");
		backbtn3.setBounds(0, 404, 192, 47);
		main_panel.add(backbtn3);
		backbtn3.setVisible(false);
		
		//sction for back button
		backbtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model1 = (DefaultTableModel) Ordertable.getModel();
				DefaultTableModel model2 = (DefaultTableModel) Splittable.getModel();
				model1.setRowCount(0);
				model2.setRowCount(0);
				Splitpay.setVisible(false);
				cardbtn.setVisible(false);
				Cashbtn.setVisible(false);
				FullBtn.setVisible(true);
				SplitBtn.setVisible(true);
				split_pay_panel.setVisible(false);
				main_panel.setVisible(true);
				receipt_type_popup.setVisible(false);
				
			}
		});
		
		
		
		//when customer clicks full pay button, a panel to pay full bill pops up 
		FullBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//full_pay_panel.setVisible(true);
				PrevSplit=false;
				SplitBtn.setVisible(false);
				FullBtn.setVisible(false);
				backbtn3.setVisible(true);
				cardbtn.setVisible(true);
				Cashbtn.setVisible(true);
				receipt_type_popup.setVisible(false);
				split_pay_panel.setVisible(false);
			}
		});
		
		//when customer clicks split pay button, a panel to select items pops up
		SplitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrevSplit = false;
				SplitMenuTable();
				split_pay_panel.setVisible(true);
				main_panel.setVisible(false);
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
							amountpaid = CustomerOrder.subtotal + (CustomerOrder.subtotal * tax);
							main_panel.setVisible(false);
							screen_for_cash.setVisible(true);
							receipt_type_popup.setVisible(false);				
							Donebtn.setVisible(true);
							amtPaid.setText("Amount Paid: " + decimalF(amountpaid));
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
							amountpaid = CustomerOrder.subtotal + (CustomerOrder.subtotal * tax) + tip;
							main_panel.setVisible(false);
							screen_for_card.setVisible(true);
							screen_for_cash.setVisible(false);
							receipt_type_popup.setVisible(false);
							amtPaid.setText("Amount Paid: " + decimalF(amountpaid));
						}
					}
				});
		
		//submit button after entering email for both types of receipt
		submitbtn1_1.addActionListener(new ActionListener()
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
				bothbtn_screen_1.setVisible(false);
				emailbtn_screen.setVisible(false);
				if(PrevSplit == true)
				{
					DefaultTableModel model1 = (DefaultTableModel) Ordertable.getModel();
					DefaultTableModel model2 = (DefaultTableModel) Splittable.getModel();
					model1.setRowCount(0);
					model2.setRowCount(0);
					CustomerOrder.subtotal = CustomerOrder.subtotal - splitSubTotal;
					orderSummary.setText(oldOrder);
					main_panel.setVisible(true);
				}
				else
				{
					LotteryChoice();
				}
				
				}
				
			}
		});
		
		//back button to change method actions
		backbtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model1 = (DefaultTableModel) Ordertable.getModel();
				DefaultTableModel model2 = (DefaultTableModel) Splittable.getModel();
				model1.setRowCount(0);
				model2.setRowCount(0);
				Splitpay.setVisible(false);
				split_pay_panel.setVisible(false);
				main_panel.setVisible(true);
				receipt_type_popup.setVisible(false);
				
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
					PrevSplit = true;
					amountpaid = splitSubTotal + (tax*splitSubTotal)+tip;
					main_panel.setVisible(false);
					Splitpay.setVisible(false);
					screen_for_cash.setVisible(true);
					split_pay_panel.setVisible(false);
					receipt_type_popup.setVisible(false);				
					Donebtn.setVisible(true);
					amtPaid.setText("Amount Paid: " + decimalF(amountpaid));
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
					PrevSplit = true;
					amountpaid = splitSubTotal + (tax*splitSubTotal)+tip;
				Splitpay.setVisible(false);
				screen_for_card.setVisible(true);
				split_pay_panel.setVisible(false);
				screen_for_cash.setVisible(false);
				amtPaid.setText("Amount Paid: " + decimalF(amountpaid));
				}
			}
		});
		
		//timer for when it is still a split bill situation and needs to be brought back to the main menu
		ActionListener action = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				printbtn_screen.setVisible(false);
				main_panel.setVisible(true);
			}
		};
		
		//option to print the bill
		printbtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				prevScreen="printbtn_screen";
				printbtn_screen.setVisible(true);
				screen_for_cash.setVisible(false);
				screen_for_card.setVisible(false);
				receipt_type_popup.setVisible(false);
				emailbtn_screen.setVisible(false);
				bothbtn_screen_1.setVisible(false);
				nobtn_screen.setVisible(false);
				conf_screen.setVisible(false);
				if(PrevSplit == true)//checking to see if this is a split bill situation
				{
					DefaultTableModel model1 = (DefaultTableModel) Ordertable.getModel();
					DefaultTableModel model2 = (DefaultTableModel) Splittable.getModel();
					model1.setRowCount(0);
					model2.setRowCount(0);
					CustomerOrder.subtotal = CustomerOrder.subtotal - splitSubTotal;
					printSummary.setText(newOrderSummary.getText());
					printTotal.setText("Subtotal: "+decimalF(splitSubTotal)+"\nTax: "+
							decimalF(tax * splitSubTotal)+"\nTip: "+decimalF(tip)+"\nTotal: "+decimalF(splitSubTotal + (splitSubTotal * tax) + tip));
					orderSummary.setText(oldOrder);
					timer = new Timer(delay,action);
					timer.start();
					
				}
				else
				{
					printSummary.setText(orderSummary.getText());
					printTotal.setText("Subtotal: "+decimalF(CustomerOrder.subtotal)+"\nTax: "+
							decimalF(tax * CustomerOrder.subtotal)+"\nTip: "+decimalF(tip)+"\nTotal: "+decimalF(CustomerOrder.subtotal + (CustomerOrder.subtotal * tax)));
					
					LotteryChoice();
				}
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
				bothbtn_screen_1.setVisible(false);
				nobtn_screen.setVisible(false);
				conf_screen.setVisible(false);
			}
		});
		
		//when user selects both for receipt type
		bothbtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				bothbtn_screen_1.setVisible(true);

				printbtn_screen.setVisible(false);
				screen_for_cash.setVisible(false);
				screen_for_card.setVisible(false);
				receipt_type_popup.setVisible(false);
				emailbtn_screen.setVisible(false);
				nobtn_screen.setVisible(false);
				conf_screen.setVisible(false);
				if(PrevSplit == true)//checking to see if this is a split bill situation
				{
					DefaultTableModel model1 = (DefaultTableModel) Ordertable.getModel();
					DefaultTableModel model2 = (DefaultTableModel) Splittable.getModel();
					model1.setRowCount(0);
					model2.setRowCount(0);
					CustomerOrder.subtotal = CustomerOrder.subtotal - splitSubTotal;
					bothSummary.setText(newOrderSummary.getText());
					bothTotal.setText("Subtotal: "+decimalF(splitSubTotal)+"\nTax: "+
							decimalF(tax * splitSubTotal)+"\nTip: "+decimalF(tip)+"\nTotal: "+decimalF(splitSubTotal + (splitSubTotal * tax) + tip));
					orderSummary.setText(oldOrder);

				}
				else
				{
					bothSummary.setText(orderSummary.getText());
					bothTotal.setText("Subtotal: "+decimalF(CustomerOrder.subtotal)+"\nTax: "+
							decimalF(tax * CustomerOrder.subtotal)+"\nTip: "+decimalF(tip)+"\nTotal: "+decimalF(CustomerOrder.subtotal + (CustomerOrder.subtotal * tax)));
					
					
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
				bothbtn_screen_1.setVisible(false);
				emailbtn_screen.setVisible(false);
				conf_screen.setVisible(false);
				if(PrevSplit == true)
				{
					DefaultTableModel model1 = (DefaultTableModel) Ordertable.getModel();
					DefaultTableModel model2 = (DefaultTableModel) Splittable.getModel();
					model1.setRowCount(0);
					model2.setRowCount(0);
					CustomerOrder.subtotal = CustomerOrder.subtotal - splitSubTotal;
					orderSummary.setText(oldOrder);
					main_panel.setVisible(true);
				}
				else
				{
					LotteryChoice();
				}
				
			}
		});
		
		//action to confirm the splitting of the bill
		confirmSplit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oldOrder = orderSummary.getText();
				StringBuilder splitOrder = new StringBuilder();
				String breaker = new String();
				splitOrder.append("Order: \n\n");
				//remove selected options from the main bill
				for(int i=0; i<Splittable.getRowCount(); i++)
				{
					breaker = (String) Splittable.getValueAt(i,0);
					splitOrder.append(breaker);
					oldOrder.replace(breaker, "");
					
				}
				//calculate the total

				split_pay_panel.setVisible(false);
				newOrderSummary.setText(splitOrder.toString());
				Pattern pattern = Pattern.compile("\\$(.*?)\n", Pattern.DOTALL);
				Matcher matcher = pattern.matcher(splitOrder.toString());
				while (matcher.find()) {
				    splitSubTotal = splitSubTotal + Double.valueOf(matcher.group(1));
				}
				newOrderTotal.setText("Subtotal: "+decimalF(splitSubTotal)+"\nTax: "+
						decimalF(tax*splitSubTotal)+"\nTotal: "+decimalF(splitSubTotal + (tax*splitSubTotal)));
				Splitpay.setVisible(true);
				backbtn2.setVisible(true);
							
				//orderTotal.setText("Subtotal: "+decimalF(CustomerOrder.subtotal)+"\nTax: "+
				//		decimalF(tax * CustomerOrder.subtotal)+"\nTotal: "+decimalF(CustomerOrder.subtotal + (CustomerOrder.subtotal * tax)));
				//prompt for card stuff similar to full pay panel
				//need to keep track that there is still things left to pay
			}
		});
		
		//back button for split bill screen
		backbtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model1 = (DefaultTableModel) Ordertable.getModel();
				DefaultTableModel model2 = (DefaultTableModel) Splittable.getModel();
				model1.setRowCount(0);
				model2.setRowCount(0);
				Splitpay.setVisible(false);
				split_pay_panel.setVisible(false);
				main_panel.setVisible(true);
				receipt_type_popup.setVisible(false);
				
			}
		});
		split_pay_panel.setVisible(false);
		
		
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
				if(PrevSplit == true)
				{
					tip = splitSubTotal * 0.25;
				}
				else
				{
					tip=CustomerOrder.subtotal * (0.25);
				}
				tip_box.setText(decimalF(tip));
				//receipt_type_popup.setVisible(true);
			}
		});
		
		//when user selects 20% tip
		tipbtn_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//functionality to add 20% of the tip to total
				if(PrevSplit == true)
				{
					tip = splitSubTotal * 0.20;
				}
				else
				{
					tip=CustomerOrder.subtotal * (0.20);
				}
				tip_box.setText(decimalF(tip));
				//receipt_type_popup.setVisible(true);
			}
		});
		
		//when user selects 15% tip
		tipbtn_3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//functionality to add 15% of the tip to total
				if(PrevSplit == true)
				{
					tip = splitSubTotal * 0.15;
				}
				else
				{
					tip=CustomerOrder.subtotal * (0.15);
				}
				tip_box.setText(decimalF(tip));
				//receipt_type_popup.setVisible(true);
			}
		});
		
		//when user chooses no tip option 
		no_tipbtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	tip=0;
				receipt_type_popup.setVisible(true);
				tip_panel.setVisible(false);
			}
		});
		
		//if user enters custom tip
		tip_donebtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//functionality to add custom amount to total 
				tip=Double.valueOf((tip_box.getText()).substring(1));
				Client.session.getPacketEncoder().sendRevenueUpdate(amountpaid, tip);
				amountpaid = amountpaid + tip;
				amtPaid.setText("Amount Paid: " + decimalF(amountpaid));
				receipt_type_popup.setVisible(true);
				tip_panel.setVisible(false);
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
				bothbtn_screen_1.setVisible(false);
				emailbtn_screen.setVisible(false);
				if(PrevSplit == true)
				{
					DefaultTableModel model1 = (DefaultTableModel) Ordertable.getModel();
					DefaultTableModel model2 = (DefaultTableModel) Splittable.getModel();
					model1.setRowCount(0);
					model2.setRowCount(0);
					CustomerOrder.subtotal = CustomerOrder.subtotal - splitSubTotal;
					orderSummary.setText(oldOrder);
					main_panel.setVisible(true);
				}
				else
				{
					LotteryChoice();
				}
				}
				
			}
		});
		tip_panel.setVisible(false);
		screen_for_cash.setVisible(false);
	}	
	
	public void LotteryChoice()
	{
			//still working on getting conf_screen to open
			boolean confirm = JFrameUtils.confirmDialog("LOTTERY CHANCE", 
					"Would you like to play a game for a chance to win a free desert?");
        	if(!confirm) {
        		Client.restart();
        		//prevOption(prevScreen);
        	}
        	else {
        		this.lottery.setVisible(true);
        		main_panel.setVisible(false);
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
				break;
			case "nobtn_screen":
				nobtn_screen.setVisible(true);
				break;
			
		}	
	}
	
	public void SplitMenuTable() {
		DefaultTableModel orig = (DefaultTableModel) Ordertable.getModel();
		StringBuilder s = new StringBuilder();

		for(MItem i : CustomerOrder.items) {
			s.append("x"+i.qty+" "+i.name+" - "+
					(decimalF(i.price * i.qty))+"\n");

			// Current Menu Item
			MItem prev = Menu.getItem(i.name);
			String[] oldIngTok = prev.ingredients.split(",");

			// Order Menu Item
			String[] newIngTok = i.ingredients.split(",");

			for(int index = 0; index < newIngTok.length; index++) {
				String[] oldIng = oldIngTok[index].split(":");
				String[] newIng = newIngTok[index].split(":");
				// Substituted ingredient
				if(!oldIng[0].equals(newIng[0])) {
					s.append("    - "+oldIng[0]+" sub for "+newIng[0]+"\n");
				}
				if(!oldIng[1].equals(newIng[1])) {
					s.append("    - x"+newIng[1]+" "+newIng[0]+"\n");
				}
			}
			if(!(i.specialReqs.equalsIgnoreCase("none")) 
					&& !i.specialReqs.equals("") && !i.specialReqs.equals(null)) {
				s.append("    - "+i.specialReqs+"\n");
			}
			Ordertable.setRowHeight(50);
			Splittable.setRowHeight(50);
			orig.addRow(new Object[] {s.toString()});
			s.setLength(0);
		}
		
	}
	
	public void refreshTxtAreas() {
		
		StringBuilder s = new StringBuilder();
		s.append("Order:\n\n");

		for(MItem i : CustomerOrder.items) {
			s.append("x"+i.qty+" "+i.name+" - "+
					(decimalF(i.price * i.qty))+"\n");

			// Current Menu Item
			MItem prev = Menu.getItem(i.name);
			String[] oldIngTok = prev.ingredients.split(",");

			// Order Menu Item
			String[] newIngTok = i.ingredients.split(",");

			for(int index = 0; index < newIngTok.length; index++) {
				String[] oldIng = oldIngTok[index].split(":");
				String[] newIng = newIngTok[index].split(":");
				// Substituted ingredient
				if(!oldIng[0].equals(newIng[0])) {
					s.append("    - "+oldIng[0]+" sub for "+newIng[0]+"\n");
				}
				if(!oldIng[1].equals(newIng[1])) {
					s.append("    - x"+newIng[1]+" "+newIng[0]+"\n");
				}
			}
			if(!(i.specialReqs.equalsIgnoreCase("none")) 
					&& !i.specialReqs.equals("") && !i.specialReqs.equals(null)) {
				s.append("    - "+i.specialReqs+"\n");
			}
		}
		orderSummary.setText(s.toString());
		orderTotal.setText("Subtotal: "+decimalF(CustomerOrder.subtotal)+"\nTax: "+
				decimalF(tax * CustomerOrder.subtotal)+"\nTotal: "+decimalF(CustomerOrder.subtotal + (CustomerOrder.subtotal * tax)));
	}
	
	/**
	 * Formats a decimal to be displayed as currency.
	 * @param num
	 * @return
	 */
	public String decimalF(double num) {
		return DecimalFormat.getCurrencyInstance().format(num);
	}

	public void enableButtons() {
		SplitBtn.setVisible(true);
		FullBtn.setVisible(true);
		this.refreshTxtAreas();
	}
}
