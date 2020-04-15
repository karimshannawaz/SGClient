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



public class PayPanel extends JPanel {

	private static final long serialVersionUID = -1338118268945423515L;

	JButton SplitBtn;
	JButton FullBtn;
	private JTextField txtOrder;
	/**
	 * Create the panel.
	 */
	public PayPanel() {
		super();
		setBounds(0, 0, 1039, 522);
		setLayout(null);
		
		SplitBtn = new JButton("Split Bill");
		SplitBtn.setBounds(412, 475, 120, 47);
		add(SplitBtn);
		SplitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		SplitBtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		
		FullBtn = new JButton("Full Bill");
		FullBtn.setBounds(553, 475, 120, 47);
		add(FullBtn);
		FullBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		FullBtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		
		JPanel main_panel = new JPanel();
		main_panel.setBounds(0, 0, 1039, 522);
		add(main_panel);
		main_panel.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(292, 106, 457, 345);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		main_panel.add(textArea);
		
		txtOrder = new JTextField();
		txtOrder.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		txtOrder.setBounds(451, 36, 116, 33);
		txtOrder.setText("     ORDER");
		main_panel.add(txtOrder);
		txtOrder.setColumns(10);
		
		JPanel full_pay_panel = new JPanel();
		full_pay_panel.setBounds(0, 0, 1039, 522);
		add(full_pay_panel);
		full_pay_panel.setLayout(null);
		
		JTextArea pay_popup_window = new JTextArea();
		pay_popup_window.setEditable(false);
		pay_popup_window.setText("\t\r\n      \r\n                   HOW WOULD YOU LIKE TO PAY THE BILL?");
		pay_popup_window.setFont(new Font("Haettenschweiler", Font.BOLD, 35));
		pay_popup_window.setBounds(71, 108, 782, 208);
		full_pay_panel.add(pay_popup_window);
		
		JButton cardbtn = new JButton("CREDIT/DEBIT CARD");
		cardbtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		cardbtn.setBounds(171, 346, 259, 65);
		full_pay_panel.add(cardbtn);
		
		JButton Cashbtn = new JButton("CASH");
		Cashbtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		Cashbtn.setBounds(486, 346, 259, 65);
		full_pay_panel.add(Cashbtn);
		
		JPanel screen_for_cash = new JPanel();
		screen_for_cash.setBounds(0, 0, 1039, 522);
		add(screen_for_cash);
		screen_for_cash.setLayout(null);
		
		JTextArea assistance_popup = new JTextArea();
		assistance_popup.setText("\r\n\r\n\r\n       PLEASE WAIT....YOU WILL BE ASSISTED SHORTLY BY OUR WAIT-STAFF");
		assistance_popup.setFont(new Font("Haettenschweiler", Font.BOLD, 35));
		assistance_popup.setEditable(false);
		assistance_popup.setBounds(51, 132, 936, 271);
		screen_for_cash.add(assistance_popup);
		
		JButton Donebtn = new JButton("DONE");
		Donebtn.setEnabled(true);
		Donebtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		Donebtn.setBounds(778, 417, 125, 61);
		screen_for_cash.add(Donebtn);
		
		JPanel receipt_type_popup = new JPanel();
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
		screen_for_card.setBounds(0, 0, 1039, 522);
		add(screen_for_card);
		screen_for_card.setLayout(null);
		
		JTextArea steps_popup = new JTextArea();
		steps_popup.setEditable(false);
		steps_popup.setFont(new Font("Haettenschweiler", Font.BOLD, 35));
		steps_popup.setText("\r\n\r\n\t\t\r\n\t       SWIPE/INSERT THE CARD....");
		steps_popup.setBounds(130, 128, 791, 294);
		screen_for_card.add(steps_popup);
		
		JPanel bill_splitScreen = new JPanel();
		bill_splitScreen.setBounds(0, 0, 1039, 522);
		add(bill_splitScreen);
		bill_splitScreen.setLayout(null);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEnabled(false);
		textArea_1.setEditable(false);
		textArea_1.setBounds(0, 0, 543, 522);
		bill_splitScreen.add(textArea_1);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setEnabled(false);
		textArea_2.setEditable(false);
		textArea_2.setBounds(545, 0, 511, 522);
		bill_splitScreen.add(textArea_2);
		
		
		FullBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				full_pay_panel.setVisible(true);
				main_panel.setVisible(false);
				receipt_type_popup.setVisible(false);
				FullBtn.setVisible(false);
				SplitBtn.setVisible(false);
			}
		});
		
		Cashbtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				screen_for_cash.setVisible(true);
				full_pay_panel.setVisible(false);
				receipt_type_popup.setVisible(false);
			}
		});
		
		
		//code to make them pay with cash and give bill
		
		cardbtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				screen_for_card.setVisible(true);
				full_pay_panel.setVisible(false);
				screen_for_cash.setVisible(false);
				receipt_type_popup.setVisible(false);
			}
		});
		
		
		//code to send card payment verification to server
		
		Donebtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				receipt_type_popup.setVisible(true);
				screen_for_cash.setVisible(false);
				screen_for_card.setVisible(false);
				
			}
		});
		receipt_type_popup.setVisible(false);
		screen_for_cash.setVisible(false);
		screen_for_card.setVisible(false);
		
	}
}
