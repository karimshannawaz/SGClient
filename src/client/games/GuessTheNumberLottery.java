package client.games;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import client.Client;
import client.order.PayPanel;
import client.utils.Constants;

/**
 * 
 * @author karim
 *
 */ 
public class GuessTheNumberLottery extends JPanel {

	private static final long serialVersionUID = 153913217794518944L;

	private StringBuilder output;
	private JTextField guess;
	private JLabel triesLbl;
	private JTextArea textArea;
	public PayPanel payPanel;
	public JPanel conf_screen;

	public int randomNum = -1;
	public int tries = 1;


	/**
	 * Create the frame.
	 */
	public GuessTheNumberLottery() {

		setBounds(0, 0, 979, 574);

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JPanel conf_screen = new JPanel();
		conf_screen.setVisible(false);
		conf_screen.setBounds(0, 0, 1039, 522);
		add(conf_screen);
		conf_screen.setLayout(null);
		
		JTextArea confirmation_screen = new JTextArea();
		confirmation_screen.setEditable(false);
		confirmation_screen.setText("\r\r\n                           THANK YOU FOR VISITING!!\r\n           \r\t         HAVE A GOOD DAY!");
		confirmation_screen.setFont(new Font("Haettenschweiler", Font.BOLD, 30));
		confirmation_screen.setBounds(169, 173, 662, 136);
		conf_screen.add(confirmation_screen);
		conf_screen.setVisible(false);
		
		// create scroll pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 707, 278);
		add(scrollPane);

		guess = new JTextField();
		guess.setFont(new Font("Tahoma", Font.PLAIN, 14));
		guess.setBounds(81, 323, 116, 38);
		add(guess);
		guess.setColumns(10);

		/*
		 *  create button to submit the customers guess
		 *  If game was played correctly once, submit button is disabled
		 */
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean played = guessNum();
				if(played) {
					btnNewButton.setEnabled(false);
					Client.restart();
				}
			}
		});
		btnNewButton.setBounds(219, 330, 97, 25);
		add(btnNewButton);

		output = new StringBuilder();

		textArea = new JTextArea();
		textArea.setEditable(false);


		output.append("Welcome! Type in your guess to get started. It is a number from 1 to 5. You have 1 try, good luck!\n");


		textArea.setText(output.toString());
		textArea.setBounds(81, 32, 707, 278);
		scrollPane.setViewportView(textArea);

		// keeps track of tries to guess the user has
		triesLbl = new JLabel("Tries Left: "+tries);
		triesLbl.setBounds(698, 323, 90, 27);
		add(triesLbl);
		
		
	}


	protected boolean guessNum() {
		int num;
		
		// If the guess is not between 1 and 5, send error to user
		try {
			num = Integer.parseInt(guess.getText());
		} catch(NumberFormatException e) {
			Client.sendMessage("Error, number was empty, please enter a valid number between 1 and 5");
			return false;
		}
		if(guess.getText().equals("") || guess.getText().equals(null) || num > 5 || num < 1) {
			Client.sendMessage("Error, number was empty, please enter a valid number between 1 and 5");
			return false;
		}
		// If guess is between 1 and 5, send results to user
		if(num != randomNum) {
				Client.sendMessage("Unfortunately you've guessed the wrong number; the number was: "+randomNum+".");
				output.append("You've run out of tries; the number was: "+randomNum+".\n\n\n");
				//resetGame();
				//this.payPanel.conf_screen.setVisible(true);
				return true;
			}
		else {
			Client.sendMessage("Congratulations, you've guessed the number right! It was "+num+". "
					+ "Your free coupon is being printed!");
			output.append("Congratulations, you've guessed the number right! It was "+num+".\n\n\n");
			return true;
			//printCoupon();
		}
		
	}

	public void printCoupon() {
		//print the coupon
		JPanel coupon = new JPanel();
		
		//resetGame();
		
	}
	public void resetGame() {
		
		conf_screen.setVisible(true);
		//restart the session
	}

	// Generate a random number
	public static int generateNumber(int minimum, int maximum) {
		if (minimum > maximum) {
			return 0;
		}
		Random r = new Random();
		long range = (long) maximum - (long) minimum + 1;
		long fraction = (long) (range * r.nextDouble());
		return  ((int) (fraction + minimum));
	}
}
