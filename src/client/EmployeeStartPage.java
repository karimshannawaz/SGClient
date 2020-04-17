
package client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.utils.JFrameUtils;




public class EmployeeStartPage extends JPanel{

	private static final long serialVersionUID = -8112480994553957L;

	private JTextField empID;
	private JPanel loginBackground;
	public static EmployeeStartPage instance;
	public static String currentScreen = "";
	private JTextField empPass;

	public WaitstaffStartPage waitstaffPage;
	public KitchenStartPage kitchenPage;

	JFrame frame = new JFrame();

	public EmployeeStartPage() {
		super();
		setBounds(0, 0, 1039, 656);
		setLayout(null);

		loginBackground = new JPanel();
		loginBackground.setLayout(null);
		loginBackground.setBounds(0, 0, 1039, 656);
		add(loginBackground);

		JLabel lblNewLabel = new JLabel("Employee Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(420, 64, 200, 47);
		loginBackground.add(lblNewLabel);

		JLabel lblEmployeeID = new JLabel("Employee ID:");
		lblEmployeeID.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblEmployeeID.setBounds(187, 153, 143, 47);
		loginBackground.add(lblEmployeeID);

		empID = new JTextField();

		empID.setFont(new Font("Tahoma", Font.PLAIN, 24));
		empID.setBounds(398, 153, 426, 47);
		loginBackground.add(empID);
		empID.setColumns(10);

		JLabel lblEmpPassword = new JLabel("Password:");
		lblEmpPassword.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblEmpPassword.setBounds(187, 287, 141, 26);
		loginBackground.add(lblEmpPassword);

		empPass = new JTextField();
		empPass.setFont(new Font("Tahoma", Font.PLAIN, 24));
		empPass.setBounds(398, 279, 426, 47);
		loginBackground.add(empPass);
		empPass.setColumns(10);
		empPass.addKeyListener(new KeyListener() {

			@Override public void keyPressed(KeyEvent arg0) {}
			@Override public void keyTyped(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				if(KeyEvent.getKeyText(e.getKeyCode()).toLowerCase().contains("enter")) {
					submitEmpInfo();
					return;
				}
			}
		});

		JButton sendInfo = new JButton("Enter");
		sendInfo.setFont(new Font("Tahoma", Font.PLAIN, 24));
		sendInfo.setBounds(449, 390, 141, 35);
		loginBackground.add(sendInfo);
		sendInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitEmpInfo();
			}
		});

		this.waitstaffPage = new WaitstaffStartPage();
		waitstaffPage.setVisible(false);
		add(waitstaffPage);

		this.kitchenPage = new KitchenStartPage(null);
		kitchenPage.setVisible(false);
		add(kitchenPage);
	}

	protected void submitEmpInfo() {

		String id = empID.getText();
		if(id.equals("") || id.equalsIgnoreCase("null") || id.equals(null)) {
			JFrameUtils.showMessage("Employee Login", "Invalid employee ID entered, try again.");
			return;
		}
		String password = empPass.getText();
		if(password.equals("") || password.equals(null)) {
			JFrameUtils.showMessage("Employee Login", "Invalid password entered, try again.");
			return;
		}

		Client.session.getPacketEncoder().sendLoginRequest(id, password);
	}
	
	public void waiterLandingPage() {
		loginBackground.setVisible(false);
		this.waitstaffPage.setVisible(true);
		//JFrameUtils.showMessage("Employee Login", "Successfully logged in. Welcome back, "+ClientSession.name+"!");
	}

	public void kitchenLandingPage() {
		loginBackground.setVisible(false);
		this.kitchenPage.setVisible(true);
	//	JFrameUtils.showMessage("Employee Login", "Successfully logged in. Welcome back, "+ClientSession.name+"!");
	}

}
