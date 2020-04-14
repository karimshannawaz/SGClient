package client.order;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.Client;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.DropMode;

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
		txtOrder.setBounds(451, 36, 116, 22);
		txtOrder.setText("Bill:");
		main_panel.add(txtOrder);
		txtOrder.setColumns(10);
		
		JPanel full_pay_panel = new JPanel();
		full_pay_panel.setBounds(0, 0, 1039, 522);
		add(full_pay_panel);
		full_pay_panel.setLayout(null);
		
		JTextArea pay_popup_window = new JTextArea();
		pay_popup_window.setEditable(false);
		pay_popup_window.setText("\t\r\n      \r\n                     How would you like to pay the bill?");
		pay_popup_window.setFont(new Font("Haettenschweiler", Font.BOLD, 35));
		pay_popup_window.setBounds(71, 108, 782, 208);
		full_pay_panel.add(pay_popup_window);
		
		JButton cardbtn = new JButton("Credit/Debit Card");
		cardbtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		cardbtn.setBounds(171, 346, 259, 65);
		full_pay_panel.add(cardbtn);
		
		JButton Cashbtn = new JButton("Cash");
		Cashbtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		Cashbtn.setBounds(486, 346, 259, 65);
		full_pay_panel.add(Cashbtn);
		
		
		FullBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				full_pay_panel.setVisible(true);
				main_panel.setVisible(false);
				FullBtn.setVisible(false);
				SplitBtn.setVisible(false);
			}
		});
		
		Cashbtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		
		cardbtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		
		
	}
}
