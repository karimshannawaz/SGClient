package client.order;

import javax.swing.JPanel;

/**
 * Contains all of the information needed for a payment panel.
 * 
 * @author Karimshan
 *
 */
public class Payment extends JPanel {

	private static final long serialVersionUID = 3521869431085751251L;
	
	public JPanel mainPanel;
	
	public Payment() {
		super();
		setBounds(0, 0, 1039, 522);
		setLayout(null);
		
		addMainPanel();
	}

	private void addMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 1039, 522);
		mainPanel.setLayout(null);
		add(mainPanel);
		mainPanel.setVisible(true);
		
	}
	
	

}
