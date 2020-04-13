
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

import client.games.GamePanel;
import client.order.MenuPanel;
import client.order.PayPanel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.GridLayout;


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
		
		JButton OrderBtn = new JButton("Order");
		OrderBtn.setFont(new Font("Tahoma", Font.PLAIN, 28));
		OrderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openScreen("order");
			}
		});
		
		JButton CompBtn = new JButton("Compensate");
		CompBtn.setFont(new Font("Tahoma", Font.PLAIN, 28));
		CompBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		CompBtn.setBounds(0, 394, 374, 262);
		mainPanel.add(CompBtn);
		
		JButton PayBtn = new JButton("Pay");
		PayBtn.setFont(new Font("Tahoma", Font.PLAIN, 28));
		PayBtn.setBounds(187, 0, 187, 392);
		mainPanel.add(PayBtn);
		OrderBtn.setBounds(0, 0, 187, 392);
		mainPanel.add(OrderBtn);
		
		
		backBtn = new JButton("Back");
		backBtn.setFont(new Font("Haettenschweiler", Font.BOLD, 24));
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back((currentScreen.equals("rps") || currentScreen.equals("gtn")) ? true : false);
			}
		});
		backBtn.setBounds(37, 5, 131, 77);
		
		this.orderPanel = new MenuPanel();
		orderPanel.setVisible(false);
		add(orderPanel);


		this.payPanel = new PayPanel();
		add(payPanel);
		payPanel.setVisible(false);
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
			this.utilityPanel.setVisible(true);
			this.orderPanel.setVisible(true);
			this.orderPanel.getMenuItems();
			
			break;
		case "pay":
			this.utilityPanel.setVisible(true);
			this.payPanel.setVisible(true);
			break;
	}
}

protected void back(boolean exception) {
	switch(currentScreen) {
		case "order":
			this.orderPanel.setVisible(false);
			break;
		case "pay":
			this.payPanel.setVisible(false);
			break;
		
	}
	if(!exception) {
		this.PayBtn.setVisible(true);
		this.OrderBtn.setVisible(true);
		this.CompBtn.setVisible(true);
		this.mainPanel.setVisible(true);
		this.backBtn.setVisible(false);
		this.utilityPanel.setVisible(false);
		currentScreen = "";
	}
}

}