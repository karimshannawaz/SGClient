package client;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

//Floreta

/*
 * handles customer feedback survey
 * survey is to be implemented after payment
 * 
 */

public class SurveyPanel extends JPanel {
	
	public ButtonGroup quest1;
	public ButtonGroup quest2;
	public ButtonGroup quest3;
	public ButtonGroup quest4;
	
	private static final long serialVersionUID = 8683520609889420771L;

	/**
	 * Create the panel.
	 */
	public SurveyPanel() {
		super();
		setBounds(0, 0, 1039, 522);
		setLayout(null);
		
		JLabel question1 = new JLabel("How was your experience today?");
		question1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		question1.setBounds(114, 113, 354, 38);
		add(question1);
		
		JLabel question2 = new JLabel("Did our staff meet your needs?");
		question2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		question2.setBounds(114, 184, 329, 35);
		add(question2);
		
		JLabel question3 = new JLabel("How likely are you to return?");
		question3.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		question3.setBounds(114, 249, 329, 28);
		add(question3);
		
		JLabel question4 = new JLabel("How satisfied were you with our kiosk system?");
		question4.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		question4.setBounds(114, 311, 450, 38);
		add(question4);
		
		JLabel lblNewLabel = new JLabel("Seven Guys Survey");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblNewLabel.setBounds(385, 22, 271, 75);
		add(lblNewLabel);
		
		JRadioButton q1bad = new JRadioButton("Bad");
		q1bad.setActionCommand( q1bad.getText() );
		q1bad.setBackground(Color.WHITE);
		q1bad.setBounds(634, 123, 71, 23);
		add(q1bad);
		
		JRadioButton q1okay = new JRadioButton("Okay");
		q1okay.setActionCommand( q1okay.getText() );
		q1okay.setBackground(Color.WHITE);
		q1okay.setBounds(717, 123, 71, 23);
		add(q1okay);
		
		JRadioButton q1excellent = new JRadioButton("Excellent");
		q1excellent.setActionCommand( q1excellent.getText() );
		q1excellent.setBackground(Color.WHITE);
		q1excellent.setBounds(800, 123, 89, 23);
		add(q1excellent);
		
		JRadioButton q2yes = new JRadioButton("Yes");
		q2yes.setActionCommand( q2yes.getText() );
		q2yes.setBackground(Color.WHITE);
		q2yes.setBounds(636, 192, 69, 23);
		add(q2yes);
		
		JRadioButton q2no = new JRadioButton("No");
		q2no.setActionCommand( q2no.getText() );
		q2no.setBackground(Color.WHITE);
		q2no.setBounds(719, 192, 69, 23);
		add(q2no);
		
		JRadioButton q3notLikely = new JRadioButton("Not Likely");
		q3notLikely.setActionCommand( q3notLikely.getText() );
		q3notLikely.setBackground(Color.WHITE);
		q3notLikely.setBounds(634, 254, 106, 23);
		add(q3notLikely);
		
		JRadioButton q3veryLikely = new JRadioButton("Very Likely");
		q3veryLikely.setActionCommand( q3veryLikely.getText() );
		q3veryLikely.setBackground(Color.WHITE);
		q3veryLikely.setBounds(746, 254, 106, 23);
		add(q3veryLikely);
		
		JRadioButton q4dissatisfied = new JRadioButton("Dissatisfied");
		q4dissatisfied.setActionCommand( q4dissatisfied.getText() );
		q4dissatisfied.setBackground(Color.WHITE);
		q4dissatisfied.setBounds(634, 321, 117, 23);
		add(q4dissatisfied);
		
		JRadioButton q4neutral = new JRadioButton("Neutral");
		q4neutral.setActionCommand( q4neutral.getText() );
		q4neutral.setBackground(Color.WHITE);
		q4neutral.setBounds(763, 321, 83, 23);
		add(q4neutral);
		
		JRadioButton q4satisfied = new JRadioButton("Very Satisfied");
		q4satisfied.setActionCommand( q4satisfied.getText() );
		q4satisfied.setBounds(858, 321, 117, 23);
		add(q4satisfied);
		
		JButton submitBtn = new JButton("Submit");
		submitBtn.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get selections for each question
				quest1.getSelection().getActionCommand();
				quest2.getSelection().getActionCommand();
				quest3.getSelection().getActionCommand();
				quest4.getSelection().getActionCommand();
				
				//send them to the server
				//????
			}
		});
		submitBtn.setBounds(448, 403, 151, 48);
		add(submitBtn);
		
		JPanel p1 = new JPanel();
		p1.setBackground(Color.WHITE);
		p1.setBounds(49, 109, 946, 46);
		add(p1);
		
		JPanel p2 = new JPanel();
		p2.setBackground(Color.WHITE);
		p2.setBounds(49, 177, 946, 48);
		add(p2);
		
		JPanel p3 = new JPanel();
		p3.setBackground(Color.WHITE);
		p3.setBounds(49, 239, 946, 48);
		add(p3);
		
		JPanel p4 = new JPanel();
		p4.setBackground(Color.WHITE);
		p4.setBounds(49, 309, 946, 48);
		add(p4);
		
		//set up button group for question 1 so only one button is selected at a time
		ButtonGroup quest1 = new ButtonGroup();
		quest1.add(q1bad);
		quest1.add(q1okay);
		quest1.add(q1excellent);
		
		//set up button group for question 2 so only one button is selected at a time
		ButtonGroup quest2 = new ButtonGroup();
		quest2.add(q2yes);
		quest2.add(q2no);
		
		//set up button group for question 3 so only one button is selected at a time
		ButtonGroup quest3 = new ButtonGroup();
		quest3.add(q3notLikely);
		quest3.add(q3veryLikely);
		
		//set up button group for question 4 so only one button is selected at a time
		ButtonGroup quest4 = new ButtonGroup();
		quest4.add(q4dissatisfied);
		quest4.add(q4neutral);
		quest4.add(q4satisfied);

	}
}
