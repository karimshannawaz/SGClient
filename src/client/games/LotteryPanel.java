package client.games;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import client.ClientFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JTextPane;

public class LotteryPanel extends JPanel {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = -1126922831296889773L;

	public GuessTheNumberLottery gtnPanel;
	public JPanel mainPanel;

	/**
	 * Create the panel.
	 */
	public LotteryPanel() {
		super();
		setBounds(0, 0, 1039, 522);
		setLayout(null);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 1039, 522);
		add(mainPanel);
		mainPanel.setVisible(true);
		JButton btnNewButton_1 = new JButton("Guess The Number");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.setVisible(false);
				openScreen("gtn");
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnNewButton_1.setBounds(261, 215, 526, 227);
		mainPanel.add(btnNewButton_1);
		
		JTextPane GamePrompt = new JTextPane();
		GamePrompt.setEditable(false);
		GamePrompt.setFont(new Font("Tahoma", Font.PLAIN, 23));
		GamePrompt.setContentType("text/html");
		GamePrompt.setText("<html><center><h1><b>This game you will have a 1 in 5 chance to win a free desert.\n </h1></b></p1>\nIf you win, you will be given a free desert coupon to use on your next visit.\r\n</p1><p2>If there are any questions before starting, be sure to hit the \"HELP\" button.</p2></center></html>");
		GamePrompt.setBounds(50, 90, 933, 118);
		mainPanel.add(GamePrompt);
		
		this.gtnPanel = new GuessTheNumberLottery();
		add(gtnPanel);
		gtnPanel.setVisible(false);
		gtnPanel.randomNum = GuessTheNumberLottery.generateNumber(1, 5);
		
	}
	
	public void openScreen(String type)
	{
			
		switch(type) {
		case "gtn":
			this.gtnPanel.setVisible(true);
			break;
		case "cancel":
			//prompt to clear the sysytem
			break;
			
		}
	}
}
