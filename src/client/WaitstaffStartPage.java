
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



public class WaitstaffStartPage extends JPanel {

	private static final long serialVersionUID = -8112480994553957L;

	private JTextField empID;

	
	public static String currentScreen = "";
	private JTextField empPass;
	

	public WaitstaffStartPage(ClientFrame frame) {
		super();
		setBounds(0, 0, 1039, 656);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(420, 64, 200, 47);
		add(lblNewLabel);
		
		JLabel lblEmployeeID = new JLabel("Employee ID:");
		lblEmployeeID.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblEmployeeID.setBounds(187, 153, 143, 47);
		add(lblEmployeeID);
		
		empID = new JTextField();
		
		empID.setFont(new Font("Tahoma", Font.PLAIN, 24));
		empID.setBounds(398, 153, 426, 47);
		add(empID);
		empID.setColumns(10);
		
		JLabel lblEmpPassword = new JLabel("Password:");
		lblEmpPassword.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblEmpPassword.setBounds(187, 287, 141, 26);
		add(lblEmpPassword);
		
		empPass = new JTextField();
		empPass.setBounds(398, 279, 426, 47);
		add(empPass);
		empPass.setColumns(10);
		
		JButton sendInfo = new JButton("Enter");
		sendInfo.setBounds(449, 390, 141, 35);
		add(sendInfo);
		sendInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitEmpInfo();
			}
		});
	}
	
	protected void submitEmpInfo() {
		
	}
}