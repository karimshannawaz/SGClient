
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

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jvnet.substance.SubstanceLookAndFeel;

import client.utils.Constants;



public class EmployeeStartPage extends JPanel {

	private static final long serialVersionUID = -8112480994553957L;

	private JTextField empID;
	private JPanel LoginBackground;
	public static EmployeeStartPage instance;
	public static String currentScreen = "";
	private JTextField empPass;
	
	public WaitstaffStartPage wait;
	
	

	public EmployeeStartPage(ClientFrame frame) {
		super();
		setBounds(0, 0, 1039, 656);
		setLayout(null);
		
		LoginBackground = new JPanel();
		LoginBackground.setLayout(null);
		LoginBackground.setBounds(0, 0, 1039, 656);
		add(LoginBackground);
		
		JLabel lblNewLabel = new JLabel("Employee Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(420, 64, 200, 47);
		LoginBackground.add(lblNewLabel);
		
		JLabel lblEmployeeID = new JLabel("Employee ID:");
		lblEmployeeID.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblEmployeeID.setBounds(187, 153, 143, 47);
		LoginBackground.add(lblEmployeeID);
		
		empID = new JTextField();
		
		empID.setFont(new Font("Tahoma", Font.PLAIN, 24));
		empID.setBounds(398, 153, 426, 47);
		LoginBackground.add(empID);
		empID.setColumns(10);
		
		JLabel lblEmpPassword = new JLabel("Password:");
		lblEmpPassword.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblEmpPassword.setBounds(187, 287, 141, 26);
		LoginBackground.add(lblEmpPassword);
		
		empPass = new JTextField();
		empPass.setFont(new Font("Tahoma", Font.PLAIN, 24));
		empPass.setBounds(398, 279, 426, 47);
		LoginBackground.add(empPass);
		empPass.setColumns(10);
		
		JButton sendInfo = new JButton("Enter");
		sendInfo.setFont(new Font("Tahoma", Font.PLAIN, 24));
		sendInfo.setBounds(449, 390, 141, 35);
		LoginBackground.add(sendInfo);
		sendInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitEmpInfo();
			}
		});
	}
	


protected void submitEmpInfo() {
	if( empID.getText().compareTo("WAITER")==0) {
		waiterLandingPage();
	}
}
private void waiterLandingPage() {
	LoginBackground.setVisible(false);
	//trying to open the waitstaff page
}


}
