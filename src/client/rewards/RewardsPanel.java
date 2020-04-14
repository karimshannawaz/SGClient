package client.rewards;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class RewardsPanel extends JPanel {

	private static final long serialVersionUID = -1338118268945423515L;
	
	private JTextField rwdsID;
	private JTextField newEmail;

	/**
	 * Create the panel.
	 */
	public RewardsPanel() {
		super();
		setBounds(0, 0, 1039, 522);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Please login to rewards below!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(320, 13, 364, 47);
		add(lblNewLabel);
		
		JLabel lblUserIdemail = new JLabel("User ID (Email):");
		lblUserIdemail.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblUserIdemail.setBounds(67, 99, 220, 47);
		add(lblUserIdemail);
		
		rwdsID = new JTextField();
		/*
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println(e.getExtendedKeyCode());
			}
		});
		*/
		rwdsID.setFont(new Font("Tahoma", Font.PLAIN, 24));
		rwdsID.setBounds(299, 99, 426, 47);
		add(rwdsID);
		rwdsID.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitLoginRequest();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnNewButton.setBounds(795, 99, 132, 47);
		add(btnNewButton);
		
		JTextArea txtrnewToRewards = new JTextArea();
		txtrnewToRewards.setLineWrap(true);
		txtrnewToRewards.setFont(new Font("Monospaced", Font.PLAIN, 20));
		txtrnewToRewards.setEditable(false);
		txtrnewToRewards.setText("New to rewards? Sign up below to access free appetizers and entrees! "
				+ "The best part? It's completely free, so get started today!!");
		txtrnewToRewards.setBounds(136, 212, 695, 101);
		add(txtrnewToRewards);
		
		newEmail = new JTextField();
		newEmail.setFont(new Font("Tahoma", Font.PLAIN, 24));
		newEmail.setColumns(10);
		newEmail.setBounds(299, 345, 426, 47);
		add(newEmail);
		
		JLabel lblNewEmail = new JLabel("New Email:");
		lblNewEmail.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewEmail.setBounds(67, 345, 163, 47);
		add(lblNewEmail);
		
		JButton btnSignUp = new JButton("Sign up!");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signup();
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnSignUp.setBounds(795, 345, 132, 47);
		add(btnSignUp);
	}

	protected void signup() {
		String email = newEmail.getText();
	}

	protected void submitLoginRequest() {
		
	}

	public void showLoginInfo(boolean rwdsLgn) {
		
	}
}
