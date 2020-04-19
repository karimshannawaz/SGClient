package client.order;

import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Contains all of the information needed for a payment panel.
 * 
 * @author Karimshan
 *
 */
public class Payment extends JPanel {

	private static final long serialVersionUID = 3521869431085751251L;
	
	public double tax = 0.0825;

	public JPanel mainPanel;
	public JTextArea orderSummary;
	public JTextArea orderTotal;
	private JButton fullBillBtn;
	private JButton splitBillBtn;

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

		// Creates a scroll pane for the order summary.
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 500, 450);
		mainPanel.add(scrollPane);

		orderSummary = new JTextArea();
		scrollPane.setViewportView(orderSummary);
		orderSummary.setFont(new Font("Tahoma", Font.PLAIN, 14));
		orderSummary.setEditable(false);
		orderSummary.setLineWrap(true);
		orderSummary.setText("Order: ");

		orderTotal = new JTextArea();
		orderTotal.setFont(new Font("Monospaced", Font.PLAIN, 15));
		orderTotal.setEditable(false);
		orderTotal.setLineWrap(true);
		orderTotal.setBounds(0, 451, 500, 71);
		orderTotal.setText("Subtotal: $"+(CustomerOrder.subtotal)+"\nTax:\nTotal:");
		mainPanel.add(orderTotal);

		JLabel preferenceLbl = new JLabel("<html><div style='text-align: center;'>" + 
			"How would you like to pay your bill?" + "</div></html>");
		preferenceLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		preferenceLbl.setBounds(555, 13, 411, 50);
		mainPanel.add(preferenceLbl);

		fullBillBtn = new JButton("Full Bill");
		fullBillBtn.setFont(new Font("Arial Black", Font.PLAIN, 55));
		fullBillBtn.setBounds(512, 76, 515, 200);
		mainPanel.add(fullBillBtn);

		splitBillBtn = new JButton("Split Bill");
		splitBillBtn.setFont(new Font("Arial Black", Font.PLAIN, 55));
		splitBillBtn.setBounds(512, 309, 515, 200);
		mainPanel.add(splitBillBtn);
		
		fullBillBtn.setVisible(false);
		splitBillBtn.setVisible(false);
	}
	
public void refreshTxtAreas() {
		
		StringBuilder s = new StringBuilder();
		s.append("Order:\n\n");

		for(MItem i : CustomerOrder.items) {
			s.append("x"+i.qty+" "+i.name+" - "+
					(decimalF(i.price * i.qty))+"\n");

			// Current Menu Item
			MItem prev = Menu.getItem(i.name);
			String[] oldIngTok = prev.ingredients.split(",");

			// Order Menu Item
			String[] newIngTok = i.ingredients.split(",");

			for(int index = 0; index < newIngTok.length; index++) {
				String[] oldIng = oldIngTok[index].split(":");
				String[] newIng = newIngTok[index].split(":");
				// Substituted ingredient
				if(!oldIng[0].equals(newIng[0])) {
					s.append("    - "+oldIng[0]+" sub for "+newIng[0]+"\n");
				}
				if(!oldIng[1].equals(newIng[1])) {
					s.append("    - x"+newIng[1]+" "+newIng[0]+"\n");
				}
			}
			if(!(i.specialReqs.equalsIgnoreCase("none")) 
					&& !i.specialReqs.equals("") && !i.specialReqs.equals(null)) {
				s.append("    - "+i.specialReqs+"\n");
			}
		}
		orderSummary.setText(s.toString());
		orderTotal.setText("Subtotal: "+decimalF(CustomerOrder.subtotal)+"\nTax: "+
				decimalF(tax * CustomerOrder.subtotal)+"\nTotal: "+decimalF(CustomerOrder.subtotal + (CustomerOrder.subtotal * tax)));
	}
	
	/**
	 * Formats a decimal to be displayed as currency.
	 * @param num
	 * @return
	 */
	public String decimalF(double num) {
		return DecimalFormat.getCurrencyInstance().format(num);
	}
}
