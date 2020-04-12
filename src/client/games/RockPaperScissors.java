package client.games;

/**
 * This is a java program that lets you play rock paper scisssors, lizard spock with the computer.
 * written by: Karimshan Nawaz
 * written on: January 18, 2017
 * jdk 1.8
 */
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import javazoom.jl.player.Player;

public class RockPaperScissors extends JPanel {

	private static final long serialVersionUID = 470079913645161332L;

	private JTextArea output;
	public Player music;

	private int pWins;
	private int cWins;
	private JLabel playerWins;
	private JLabel computerWins;


	public RockPaperScissors() {

		setBounds(0, 0, 810, 428);
		setBorder(new EmptyBorder(5, 5, 5, 5));

		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 463, 257);
		add(scrollPane);

		output = new JTextArea();
		output.setLineWrap(true);
		output.setBackground(Color.CYAN);
		output.setFont(new Font("SansSerif", Font.ITALIC, 13));
		output.setEditable(false);
		output.setText("WELCOME TO ROCK PAPER SCISSORS, START THE GAME WITH THE   COMPUTER BY SELECTING ONE OF THE BUTTONS");
		scrollPane.setViewportView(output);
		output.append("\n\n");

		JButton rock = new JButton("Rock");
		rock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rock.setBounds(10, 300, 79, 41);
		add(rock);
		rock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				process("rock");
			}
		});

		JButton paper = new JButton("Paper");
		paper.setFont(new Font("Tahoma", Font.PLAIN, 15));
		paper.setBounds(99, 300, 79, 41);
		add(paper);
		paper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				process("paper");
			}
		});

		JButton scissors = new JButton("Scissors");
		scissors.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scissors.setBounds(188, 300, 79, 41);
		add(scissors);
		scissors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				process("scissors");
			}
		});

		JButton lizard = new JButton("Lizard");
		lizard.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lizard.setBounds(277, 300, 79, 41);
		add(lizard);
		lizard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				process("lizard");
			}
		});

		JButton spock = new JButton("Spock");
		spock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spock.setBounds(366, 300, 79, 41);
		add(spock);
		spock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				process("spock");
			}
		});

		JLabel lblWins = new JLabel("WINS");
		lblWins.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblWins.setBounds(593, 11, 65, 35);
		add(lblWins);

		JLabel lblYou = new JLabel("You:");
		lblYou.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblYou.setBounds(504, 51, 50, 28);
		add(lblYou);

		JLabel lblComputer = new JLabel("Computer:");
		lblComputer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblComputer.setBounds(644, 51, 93, 28);
		add(lblComputer);

		playerWins = new JLabel("0");
		playerWins.setFont(new Font("Tahoma", Font.PLAIN, 14));
		playerWins.setBounds(572, 54, 50, 23);
		add(playerWins);

		computerWins = new JLabel("0");
		computerWins.setFont(new Font("Tahoma", Font.PLAIN, 14));
		computerWins.setBounds(748, 54, 36, 23);
		add(computerWins);

		JLabel lblMusic = new JLabel("Music");
		lblMusic.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMusic.setBounds(593, 99, 72, 28);
		add(lblMusic);

		JButton play = new JButton("");
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (music != null) {
					return;
				}
				new Thread() {
					@Override
					public void run() {
						try {
							File file = new File("data/music/Elevator Music.mp3");
							FileInputStream fip = new FileInputStream(file);
							BufferedInputStream bip = new BufferedInputStream(fip);
							Player player = new Player(bip);
							music = player;
							music.play();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}.start();
			}
		});
		ImageIcon icon = new ImageIcon("data/play.png");
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(56, 48, Image.SCALE_SMOOTH);
		icon = new ImageIcon(newImg);
		play.setIcon(icon);
		play.setBounds(544, 150, 56, 48);
		play.setMargin(new Insets(0, 0, 0, 0));
		play.setBorder(null);
		add(play);

		JButton stop = new JButton("");
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (music == null) {
					return;
				}
				music.close();
				music = null;
			}
		});
		icon = new ImageIcon("data/pause.png");
		img = icon.getImage();
		newImg = img.getScaledInstance(56, 48, Image.SCALE_SMOOTH);
		icon = new ImageIcon(newImg);
		stop.setIcon(icon);
		stop.setMargin(new Insets(0, 0, 0, 0));
		stop.setBorder(null);
		stop.setBounds(644, 150, 56, 48);
		add(stop);
	}

	/**
	 * Processes the player's choice.
	 *
	 * @param choice
	 */
	private void process(String choice) {
		String[] choices = {"rock", "paper", "scissors", "lizard", "spock"};
		int numChoice = -1;
		for (int i = 0; i < choices.length; i++) {
			if (choice.equals(choices[i])) {
				numChoice = i;
			}
		}
		int randomChoice = ((int) (Math.random() * 5));
		output.append("You picked " + choice + " and the computer picked " + choices[randomChoice] + "\n");
		if (numChoice == randomChoice) {
			output.append("It's a draw!!\n");
		} else if ((numChoice == 0 && randomChoice == 3) || (numChoice == 0 && randomChoice == 2)
				|| (numChoice == 1 && randomChoice == 4) || (numChoice == 1 && randomChoice == 0)
				|| (numChoice == 2 && randomChoice == 1) || (numChoice == 2 && randomChoice == 3)
				|| (numChoice == 3 && randomChoice == 1) || (numChoice == 3 && randomChoice == 4)
				|| (numChoice == 4 && randomChoice == 2) || (numChoice == 4 && randomChoice == 0)) {
			output.append("You won against the computer!!\n");
			pWins++;
		} else {
			output.append("You lost against the computer!!\n");
			cWins++;
		}
		updateWins();
		output.append("Click one of the buttons below to start playing again.\n\n");
	}

	/**
	 * Updates the win counter
	 */
	private void updateWins() {
		playerWins.setText("" + pWins);
		computerWins.setText("" + cWins);
	}

}
