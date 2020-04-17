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
 * Player will have 7 (because seven guys) tries to guess number, and if number is greater than number guessed,
 * it will say "too low", or "too high"
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

	public int randomNum = -1;
	public int tries = 1;


	/**
	 * Create the frame.
	 */
	public GuessTheNumberLottery() {

		setBounds(0, 0, 979, 574);

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 707, 278);
		add(scrollPane);

		guess = new JTextField();
		guess.setFont(new Font("Tahoma", Font.PLAIN, 14));
		guess.setBounds(81, 323, 116, 38);
		add(guess);
		guess.setColumns(10);

		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guessNum();
			}
		});
		btnNewButton.setBounds(219, 330, 97, 25);
		add(btnNewButton);

		output = new StringBuilder();

		textArea = new JTextArea();
		textArea.setEditable(false);
		output.append("Welcome! Type in your guess to get started. It is a number from 1 to 5. You have seven tries, good luck!\n");
		textArea.setText(output.toString());
		textArea.setBounds(81, 32, 707, 278);
		scrollPane.setViewportView(textArea);

		triesLbl = new JLabel("Tries Left: "+tries);
		triesLbl.setBounds(698, 323, 90, 27);
		add(triesLbl);
		
		this.payPanel = new PayPanel();
		add(payPanel);
		payPanel.setVisible(false);
		
	}


	protected void guessNum() {
		int num;
		try {
			num = Integer.parseInt(guess.getText());
		} catch(NumberFormatException e) {
			Client.sendMessage("Error, number was empty, please enter a valid number between 0 and 100");
			return;
		}
		if(guess.getText().equals("") || guess.getText().equals(null) || num > 5 || num < 1) {
			Client.sendMessage("Error, number was empty, please enter a valid number between 1 and 5");
			return;
		}
		if(num > randomNum) {
			Client.sendMessage("Your guess was too high, try again!");
			output.append("Your guess "+num+" was too high, try again.\n");
			textArea.setText(output.toString());
			if(tries == 1) {
				tries--;
				triesLbl.setText("Tries left: "+tries);
				Client.sendMessage("Unfortunately you've run out of tries; the number was: "+randomNum+". Exit this prompt to play again!");
				output.append("You've run out of tries; the number was: "+randomNum+".\n\n\n");
				
				this.payPanel.conf_screen.setVisible(true);
				return;
			}
			tries--;
			triesLbl.setText("Tries left: "+tries);
			return;
		}
		else if(num < randomNum) {
			Client.sendMessage("Your guess was too low, try again!");
			output.append("Your guess "+num+" was too low, try again.\n");
			textArea.setText(output.toString());
			if(tries == 1) {
				tries--;
				triesLbl.setText("Tries left: "+tries);
				Client.sendMessage("Unfortunately you've run out of tries; the number was: "+randomNum+". Exit this prompt to play again!");
				output.append("You've run out of tries; the number was: "+randomNum+".\n\n\n");
				resetGame();
				return;
			}
			tries--;
			triesLbl.setText("Tries left: "+tries);
			return;
		}
		tries--;
		Client.sendMessage("Congratulations, you've guessed the number right! It was "+num+". ");
		output.append("Congratulations, you've guessed the number right! It was "+num+".\n\n\n");
		resetGame();
	}

	private void resetGame() {
		output.append("Welcome! Type in your guess to get started. It is a number from 1 to 5. You have one try, good luck!\n");
		textArea.setText(output.toString());
		tries = 1;
		guess.setText("");
		triesLbl.setText("Tries left: "+tries);
		randomNum = generateNumber(1, 5);
	}


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
