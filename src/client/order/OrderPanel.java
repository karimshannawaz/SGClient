package client.order;

import javax.swing.JPanel;

import client.Client;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderPanel extends JPanel {

	private static final long serialVersionUID = -1338118268945423515L;

	/**
	 * Create the panel.
	 */
	public OrderPanel() {
		super();
		setBounds(0, 0, 1039, 522);
		setLayout(null);
		
		JButton btnNewButton = new JButton("Get Menu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Client.session.getPacketEncoder().requestMenu();
			}
		});
		btnNewButton.setBounds(66, 292, 115, 29);
		add(btnNewButton);
	}
}
