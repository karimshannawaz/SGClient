
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
import client.games.GuessTheNumber;
import client.games.RockPaperScissors;
import client.order.MenuPanel;
import client.order.PayPanel;
import client.rewards.RewardsPanel;
import javazoom.jl.player.Player;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.GridLayout;



public class KitchenStartPage extends JPanel {

	private static final long serialVersionUID = -8112480994553957L;
	
	public static String currentScreen = "";

	public KitchenStartPage(ClientFrame frame) {
		super();
		setBounds(0, 0, 1039, 656);
		setLayout(null);
		
		JButton btnCallWait = new JButton("Call Waitstaff");
		btnCallWait.setFont(new Font("Tahoma", Font.PLAIN, 32));
		btnCallWait.setBounds(0, 0, 336, 328);
		add(btnCallWait);
		btnCallWait.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callWaitstaff();
			}
		});
		
		JButton btnCallMang = new JButton("Call Manager");
		btnCallMang.setFont(new Font("Tahoma", Font.PLAIN, 32));
		btnCallMang.setBounds(0, 328, 336, 328);
		add(btnCallMang);
		btnCallMang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callManager();
			}
		});
		
		
		JLabel lblTableNum = new JLabel("Table Number");
		lblTableNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblTableNum.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTableNum.setBounds(336, 0, 163, 63);
		add(lblTableNum);
		
		JLabel lblOrder = new JLabel("Order");
		lblOrder.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblOrder.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrder.setBounds(499, 0, 377, 63);
		add(lblOrder);
		
		JLabel lblOrdStatus = new JLabel("Status");
		lblOrdStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdStatus.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblOrdStatus.setBounds(876, 0, 163, 63);
		add(lblOrdStatus);
		
	}
	
	public void callWaitstaff() {
		
	};
	
	public void callManager() {
		
	};
}