
package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.order.MenuPanel;
import client.order.PayPanel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//some issue with moving from order to back and then pay, pay will show the order screen

public class WaitstaffStartPage extends JPanel {

	private static final long serialVersionUID = -8112480994553957L;
	
	public static String currentScreen = "";
	public JButton OrderBtn;
	public JButton CompBtn;
	public JButton backBtn;
	public JButton PayBtn;
	public JPanel mainPanel;
	public JPanel utilityPanel;
	public PayPanel payPanel;
	public MenuPanel orderPanel;
	private JPanel panel;
	private JTextField tableNum;
	private JTable table;
	


	public WaitstaffStartPage(EmployeeStartPage employeeStartPage) {
		super();
		setBounds(0, 0, 1039, 656);
		setLayout(null);
		
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 1039, 656);
		add(mainPanel);
		mainPanel.setLayout(null);
		
		utilityPanel = new JPanel();
		utilityPanel.setBounds(0, 523, 1039, 133);
		add(utilityPanel);
		utilityPanel.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(302, 303, 468, 104);
		mainPanel.add(panel);
		panel.setVisible(false);
		
		JLabel lblpromptlabel = new JLabel("Enter the number of the table to continue:");
		lblpromptlabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(lblpromptlabel);
		
		tableNum = new JTextField();
		panel.add(tableNum);
		tableNum.setColumns(10);
		
		JButton btnNewButton = new JButton("Enter");
		panel.add(btnNewButton);
		
		PayBtn = new JButton("Pay");
		PayBtn.setFont(new Font("Tahoma", Font.PLAIN, 28));
		PayBtn.setBounds(187, 0, 187, 392);
		mainPanel.add(PayBtn);
		OrderBtn = new JButton("Order");
		OrderBtn.setFont(new Font("Tahoma", Font.PLAIN, 28));
		OrderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						panel.setVisible(false);
						openScreen("order");
					}
					
				});
			}
		});
		OrderBtn.setBounds(0, 0, 187, 392);
		mainPanel.add(OrderBtn);
		
		CompBtn = new JButton("Compensate");
		CompBtn.setFont(new Font("Tahoma", Font.PLAIN, 28));
		CompBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						panel.setVisible(false);
						openScreen("compensate");
					}
					
				});
			}
		});
		
		CompBtn.setBounds(0, 394, 374, 262);
		mainPanel.add(CompBtn);
		
		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(374, 44, 665, 604);
		mainPanel.add(tablePanel);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"Table Number", "Refil", "Help", "Order"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(166);
		table.getColumnModel().getColumn(1).setPreferredWidth(166);
		table.getColumnModel().getColumn(2).setPreferredWidth(166);
		table.getColumnModel().getColumn(3).setPreferredWidth(166);
		table.setBounds(0, 0, 1, 1);
		tablePanel.add(table);
		
		JLabel tableLabel = new JLabel("Table Number");
		tableLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tableLabel.setBounds(374, 0, 166, 44);
		mainPanel.add(tableLabel);
		
		JLabel refillLabel = new JLabel("Refill");
		refillLabel.setHorizontalAlignment(SwingConstants.CENTER);
		refillLabel.setBounds(540, 0, 166, 44);
		mainPanel.add(refillLabel);
		
		JLabel helpLabel = new JLabel("Help");
		helpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		helpLabel.setBounds(707, 0, 166, 44);
		mainPanel.add(helpLabel);
		
		JLabel lblNewLabel = new JLabel("Order");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(873, 0, 166, 44);
		mainPanel.add(lblNewLabel);
		
		PayBtn.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						panel.setVisible(false);
						openScreen("pay");
					}
					
				});
					
			}
		});
		
		
		backBtn = new JButton("Back");
		backBtn.setFont(new Font("Haettenschweiler", Font.BOLD, 24));
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back(false);
			}
		});
		
		backBtn.setBounds(37, 5, 131, 77);
		utilityPanel.add(backBtn);
		
		this.orderPanel = new MenuPanel();
		orderPanel.setVisible(false);
		add(orderPanel);
		
		this.payPanel = new PayPanel();
		payPanel.setVisible(false);
		add(payPanel);
	}

	protected void back(boolean exception) {
		switch(currentScreen) {
			case "order":
				this.orderPanel.setVisible(false);
				this.PayBtn.setVisible(true);
				this.OrderBtn.setVisible(true);
				this.CompBtn.setVisible(true);
				this.mainPanel.setVisible(true);
				this.backBtn.setVisible(false);
				this.utilityPanel.setVisible(false);
				currentScreen = "";
				break;
			case "pay":
				this.payPanel.setVisible(false);
				this.PayBtn.setVisible(true);
				this.OrderBtn.setVisible(true);
				this.CompBtn.setVisible(true);
				this.mainPanel.setVisible(true);
				this.backBtn.setVisible(false);
				this.utilityPanel.setVisible(false);
				currentScreen = "";
				break;
		}
		if(!exception) {
			this.PayBtn.setVisible(true);
			this.OrderBtn.setVisible(true);
			this.CompBtn.setVisible(true);
			this.mainPanel.setVisible(true);
			this.orderPanel.setVisible(false);
			this.payPanel.setVisible(false);
			this.backBtn.setVisible(false);
			this.utilityPanel.setVisible(false);
			currentScreen = "";
		}
	}
	
public void openScreen(String type) {
	
	this.mainPanel.setVisible(false);
	this.OrderBtn.setVisible(false);
	this.CompBtn.setVisible(false);
	this.PayBtn.setVisible(false);
	this.backBtn.setVisible(true);

	currentScreen = ""+type;
	switch(type) {
		case "order":
			//take table number
			this.orderPanel.setVisible(true);
			this.orderPanel.getMenuItems();
			this.utilityPanel.setVisible(true);
			
			break;
		case "pay":
			//take table number
			this.payPanel.setVisible(true);
			this.utilityPanel.setVisible(true);
			break;
	}
}
}