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
	private JTextField email_prompt_box;
	private JLabel email_box;
	/**
	 * Create the panel.
	 */
	public PayPanel() {
		super();
		setBounds(0, 0, 1039, 522);
		setLayout(null);
		
		JPanel main_panel = new JPanel();
		main_panel.setBounds(0, 0, 1039, 522);
		add(main_panel);
		main_panel.setLayout(null);
		
		JTextArea order_textfield = new JTextArea();
		order_textfield.setEditable(false);
		order_textfield.setBounds(270, 0, 500, 450);
		order_textfield.setLineWrap(true);
		order_textfield.setWrapStyleWord(true);
		main_panel.add(order_textfield);
		
		SplitBtn = new JButton("Split Bill");
		SplitBtn.setBounds(270, 451, 250, 71);
		main_panel.add(SplitBtn);
		SplitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		SplitBtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		
		FullBtn = new JButton("Full Bill");
		FullBtn.setBounds(520, 451, 250, 71);
		main_panel.add(FullBtn);
		FullBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		FullBtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		
		
		
		JPanel full_pay_panel = new JPanel();
		full_pay_panel.setBounds(0, 0, 1039, 522);
		add(full_pay_panel);
		full_pay_panel.setLayout(null);
		
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
		
		JButton backbtn = new JButton("Back");
		
		backbtn.setFont(new Font("Tahoma", Font.PLAIN, 35));
		backbtn.setBounds(0, 400, 159, 122);
		full_pay_panel.add(backbtn);
		
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
		
		JButton submitbtn = new JButton("SUBMIT");
		submitbtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		submitbtn.setBounds(693, 345, 164, 68);
		emailbtn_screen.add(submitbtn);
		
		JPanel bothbtn_screen = new JPanel();
		bothbtn_screen.setVisible(false);
		bothbtn_screen.setBounds(0, 0, 1039, 522);
		add(bothbtn_screen);
		bothbtn_screen.setLayout(null);
		
		lblNewLabel = new JLabel("EMAIL ID:");
		lblNewLabel.setBounds(188, 249, 228, 68);
		bothbtn_screen.add(lblNewLabel);
		
		email_prompt = new JLabel("               PLEASE ENTER YOUR EMAIL ID");
		email_prompt.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		email_prompt.setBounds(169, 61, 713, 128);
		bothbtn_screen.add(email_prompt);
		
		email_prompt_box = new JTextField();
		email_prompt_box.setFont(new Font("Haettenschweiler", Font.PLAIN, 25));
		email_prompt_box.setBounds(312, 249, 486, 68);
		bothbtn_screen.add(email_prompt_box);
		email_prompt_box.setColumns(10);
		
		submitbtn = new JButton("SUBMIT");
		submitbtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		submitbtn.setBounds(693, 345, 164, 68);
		bothbtn_screen.add(submitbtn);
		
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
		confirmation_screen.setText("A BILLING RECEIPT IS SENT TO THIS EMAIL SUCCESSFULLY\r\n                               THANK YOU FOR VISITING!!\n           \r\n\t\t\t\t    HAVE A GOOD DAY!");
		confirmation_screen.setFont(new Font("Haettenschweiler", Font.BOLD, 30));
		confirmation_screen.setBounds(169, 97, 662, 179);
		conf_screen.add(confirmation_screen);
		
		backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				full_pay_panel.setVisible(false);
				main_panel.setVisible(true);
				receipt_type_popup.setVisible(false);
				
			}
		});
		
		FullBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				full_pay_panel.setVisible(true);
				main_panel.setVisible(false);
				receipt_type_popup.setVisible(false);
			}
		});

		Cashbtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				screen_for_cash.setVisible(true);
				full_pay_panel.setVisible(false);
				receipt_type_popup.setVisible(false);				
				Donebtn.setVisible(true);
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
		
		Donebtn2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				receipt_type_popup.setVisible(true);
				screen_for_cash.setVisible(false);
				screen_for_card.setVisible(false);
				
			}
		});
		
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
		
		
		submitbtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				conf_screen.setVisible(true);
				nobtn_screen.setVisible(false);
				printbtn_screen.setVisible(false);
				screen_for_cash.setVisible(false);
				screen_for_card.setVisible(false);
				receipt_type_popup.setVisible(false);
				bothbtn_screen.setVisible(false);
				emailbtn_screen.setVisible(false);
				
			}
		});
		
		Nobtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				nobtn_screen.setVisible(true);
				printbtn_screen.setVisible(false);
				screen_for_cash.setVisible(false);
				screen_for_card.setVisible(false);
				receipt_type_popup.setVisible(false);
				bothbtn_screen.setVisible(false);
				emailbtn_screen.setVisible(false);
				conf_screen.setVisible(false);
			}
		});
		screen_for_cash.setVisible(false);
	}
}
