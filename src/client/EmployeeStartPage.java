
package client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.utils.JFrameUtils;




public class EmployeeStartPage extends JPanel{

	private static final long serialVersionUID = -8112480994553957L;

	private JTextField empID;
	private JPanel LoginBackground;
	public static EmployeeStartPage instance;
	public static String currentScreen = "";
	private JTextField empPass;

	public WaitstaffStartPage WaitstaffPage;
	public KitchenStartPage KitchenPage;

	JFrame frame = new JFrame();

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

		this.WaitstaffPage = new WaitstaffStartPage(null);
		WaitstaffPage.setVisible(false);
		add(WaitstaffPage);

		this.KitchenPage = new KitchenStartPage(null);
		KitchenPage.setVisible(false);
		add(KitchenPage);
	}

	protected void submitEmpInfo() {

		/*
		
		if( empID.getText().compareTo("WAITER")==0) {
			waiterLandingPage();
		}
		else if(empID.getText().equalsIgnoreCase("KITCHEN")) {
			kitchenLandingPage();
		}
		*/
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
		Client.session.getPacketEncoder().sendLoginRequest(id,password);
	}
	
	private void waiterLandingPage() {
		LoginBackground.setVisible(false);
		this.WaitstaffPage.setVisible(true);
	}

	private void kitchenLandingPage() {
		LoginBackground.setVisible(false);
		this.KitchenPage.setVisible(true);
	}

}
