package client.games;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.ClientFrame;

public class GamePanel extends JPanel {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = -1126922831296889773L;

	public static GuessTheNumber guessFrame;

	/**
	 * Create the panel.
	 */
	public GamePanel() {
		super();
		setBounds(0, 0, 1039, 522);
		setLayout(null);

		JButton btnNewButton = new JButton("Game A - Rock-Paper-Scissors-Lizard-Spock");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClientFrame.instance.customerSP.openScreen("rps");
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnNewButton.setBounds(0, 0, 511, 522);
		add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Game B - Guess the number!!");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientFrame.instance.customerSP.openScreen("gtn");
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnNewButton_1.setBounds(513, 0, 526, 522);
		add(btnNewButton_1);

		
	
	}
}
