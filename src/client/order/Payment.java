package client.order;

import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import client.Client;
import client.ClientSession;
import client.utils.JFrameUtils;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Contains all of the information needed for a payment panel.
 * 
 * @author Karimshan
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class Payment extends JPanel {

	private static final long serialVersionUID = 3521869431085751251L;

	// Represents the State of Texas tax.
	public double tax = 0.0825;
	
	// Represents the tip that the customer can choose to give
	public double tip = 0.0825;
	
	// Represents the selected discount that the customer chose
	public String discount = null;
	public int discountIndex = -1;
	
	// Represents the manager's discount
	public double managerDiscount = 0.0;
	
	// Holds a temporary object of the customer's order.
	public KOrder tempOrder;

	// Main Panel Components, which include order summary panel
	// and pre payment panels.
	public JPanel mainPanel;
	// Order Summary Panel
	public JPanel orderSummaryPanel;
	public JTextArea orderSummary;
	// Pre Payment Panel with buttons and labels
	public JPanel prePaymentPanel;
	public JTextArea orderTotal;
	public JButton fullBillBtn;
	public JButton splitBillBtn;
	public JButton applyDiscountBtn;
	public JButton removeDiscountBtn;
	public JComboBox discountsBox;
	
	// Full Bill Panel Components
	public JPanel fullBillPanel;
	
	// Split Bill Panel Components
	public JPanel splitBillPanel;

	/**
	 * Contructor for the payment panel.
	 */
	public Payment() {
		super();
		setBounds(0, 0, 1039, 522);
		setLayout(null);

		// Adds the main panel.
		addMainPanel();
		
		// Adds the full bill panel.
		addFullBillPanel();
		
		// Adds the split bill panel.
		addSplitBillPanel();
	}

	/**
	 * Adds the main payment screen panel.
	 */
	private void addMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 1039, 522);
		mainPanel.setLayout(null);
		add(mainPanel);
		
		// Creates our temporary order object.
		tempOrder = new KOrder();
		
		// Create our order summary panel
		orderSummaryPanel = new JPanel();
		orderSummaryPanel.setBounds(0, 0, 452, 522);
		orderSummaryPanel.setLayout(null);
		mainPanel.add(orderSummaryPanel);

		// Creates a scroll pane for the order summary.
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 450, 450);
		orderSummaryPanel.add(scrollPane);

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
		orderTotal.setBounds(0, 451, 450, 71);
		orderTotal.setText("Subtotal: $"+(CustomerOrder.subtotal)+"\nTax:\nTotal:");
		orderSummaryPanel.add(orderTotal);
		
		// Create our pre-payment labels and buttons panel
		prePaymentPanel = new JPanel();
		prePaymentPanel.setBounds(454, 0, 582, 522);
		prePaymentPanel.setLayout(null);
		mainPanel.add(prePaymentPanel);

		JLabel preferenceLbl = new JLabel("<html><div style='text-align: center;'>" + 
				"How would you like to pay your bill?" + "</div></html>");
		preferenceLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		preferenceLbl.setBounds(104, 13, 411, 50);
		prePaymentPanel.add(preferenceLbl);

		fullBillBtn = new JButton("Full Bill");
		fullBillBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fullBillBtnAction();
			}
		});
		fullBillBtn.setFont(new Font("Arial Black", Font.PLAIN, 55));
		fullBillBtn.setBounds(30, 76, 525, 148);
		prePaymentPanel.add(fullBillBtn);
		
		splitBillBtn = new JButton("Split Bill");
		splitBillBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				splitBillBtnAction();
			}
		});
		splitBillBtn.setFont(new Font("Arial Black", Font.PLAIN, 55));
		splitBillBtn.setBounds(30, 228, 525, 148);
		prePaymentPanel.add(splitBillBtn);
		
		applyDiscountBtn = new JButton("Apply Discount!");
		applyDiscountBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				applyDiscountBtnAction();
			}
		});
		applyDiscountBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		applyDiscountBtn.setBounds(30, 463, 258, 46);
		prePaymentPanel.add(applyDiscountBtn);
		
		removeDiscountBtn = new JButton("Remove Discount");
		removeDiscountBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeDiscountBtnAction();
			}
		});
		removeDiscountBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		removeDiscountBtn.setBounds(308, 463, 248, 46);
		prePaymentPanel.add(removeDiscountBtn);
		
		JLabel applyDiscntLbl = new JLabel("Would you like to apply a discount?");
		applyDiscntLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		applyDiscntLbl.setBounds(30, 406, 266, 34);
		prePaymentPanel.add(applyDiscntLbl);
		
		discountsBox = new JComboBox();
		discountsBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		discountsBox.setBounds(308, 398, 248, 50);
		discountsBox.setSelectedItem(null);
		System.out.println(discountsBox.getSelectedIndex());
		prePaymentPanel.add(discountsBox);


		// Sets a few buttons/labels to be invisible until the customer
		// has placed their order (all part of the pre-payment panel)
		prePaymentPanel.setVisible(false);
	}
	
	/**
	 * Turns the main panel buttons on when the server/waitstaff
	 * marks an order has been delivered to the customer's table.
	 * It only makes sense because that's when they should pay and not
	 * before they even get their food.
	 */
	public void enablePrePaymentPanel() {
		prePaymentPanel.setVisible(true);
		createOrderBackup();
	}
	
	/**
	 * Creates a temporary order object that we can use
	 * to handle split payments or adding/removing discounts.
	 */
	public void createOrderBackup() {
		this.tempOrder.subtotal = CustomerOrder.subtotal;
		this.tempOrder.items.clear();
		for(MItem item : CustomerOrder.items) {
			this.tempOrder.addItem(item);
		}
	}
	
	/**
	 * Refreshes the discounts for the customer.
	 */
	public void refreshDiscounts() {
		if(ClientSession.hasFreeSide)
			this.discountsBox.addItem("Free Side");
		if(ClientSession.hasBirthdayEntree)
			this.discountsBox.addItem("Free Birthday Entree");
		if(ClientSession.hasFreeDessert)
			this.discountsBox.addItem("Free Dessert");
		if(ClientSession.visits == 5)
			this.discountsBox.addItem("Free Loyalty Entree");
	}
	
	/**
	 * Adds an additional manager discount to the customer's order.
	 * @param discount
	 */
	public void addManagerDiscount(double discount) {
		this.managerDiscount = discount;
		this.tempOrder.subtotal -= this.managerDiscount;
		CustomerOrder.subtotal -= this.managerDiscount;
		this.refreshTxtAreas(Client.clientFrame.customerSP.orderPanel.promoIndex);
	}
	
	/**
	 * Applies a discount to the customer's order
	 * based on the discount that the customer has in their rewards
	 * account.
	 */
	public void applyDiscountBtnAction() {
		String selected = (String) discountsBox.getSelectedItem();
		if(selected == null) {
			JFrameUtils.showMessage("Discounts", "Error: This is not a valid discount.");
			return;
		}
		if(selected.equals(discount) || discount != null) {
			JFrameUtils.showMessage("Discounts", "Error: You have already applied a discount to your order.");
			return;
		}
		boolean confirm = JFrameUtils.confirmDialog("Discounts", 
			"Are you sure you would like to apply the \""+selected+"\" discount?\n"
			+ "You can remove or change it as many times as you want before you pay.");
		if(!confirm)
			return;
		boolean containsDessert = false;
		boolean containsEntree = false;
		boolean containsSide = false;

		for(int i = 0; i < CustomerOrder.items.size(); i++) {
			if(selected.toLowerCase().contains("dessert")
				&& CustomerOrder.items.get(i).menuType.toLowerCase().contains("dessert")) {
				containsDessert = true;
				discountIndex = i;
				break;
			}
			if(selected.toLowerCase().contains("entree")
				&& CustomerOrder.items.get(i).menuType.toLowerCase().contains("entree")) {
				containsEntree = true;
				discountIndex = i;
				break;
			}
			if(selected.toLowerCase().contains("side")
				&& CustomerOrder.items.get(i).menuType.toLowerCase().contains("side")) {
				containsSide = true;
				discountIndex = i;
				break;
			}
		}
		if(selected.toLowerCase().contains("dessert") && !containsDessert) {
			JFrameUtils.showMessage("Discounts", "Error: You do not have a dessert in your order.");
			return;
		}
		if(selected.toLowerCase().contains("entree") && !containsEntree) {
			JFrameUtils.showMessage("Discounts", "Error: You do not have an entree in your order.");
			return;
		}
		if(selected.toLowerCase().contains("side") && !containsSide) {
			JFrameUtils.showMessage("Discounts", "Error: You do not have a side in your order.");
			return;
		}
		discount = selected;
		double price = CustomerOrder.items.get(discountIndex).price;
		CustomerOrder.subtotal -= price;
		this.refreshTxtAreas(Client.clientFrame.customerSP.orderPanel.promoIndex);
		JFrameUtils.showMessage("Discounts", "\""+discount+"\" discount has been successfully applied to your order.");
	}
	
	/**
	 * Removes the current discount applied to the
	 * customer's order.
	 */
	public void removeDiscountBtnAction() {
		if(discount == null) {
			JFrameUtils.showMessage("Discounts", "Error: You do not have a discount applied to your order.");
			return;
		}
		boolean confirm = JFrameUtils.confirmDialog("Discounts", 
			"Are you sure you would like to remove the \""+discount+"\" discount from your order?\n"
			+ "You can add another or change it as many times as you want before you pay.");
		if(!confirm)
			return;
		CustomerOrder.subtotal = this.tempOrder.subtotal;
		CustomerOrder.clear();
		for(MItem item : tempOrder.items) {
			CustomerOrder.addItem(item);
		}
		discount = null;
		discountIndex = -1;
		this.refreshTxtAreas(Client.clientFrame.customerSP.orderPanel.promoIndex);
	}

	/**
	 * Splits the customer's bill based on items they purchased
	 */
	public void splitBillBtnAction() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Represents the customer paying the full bill at once.
	 */
	public void fullBillBtnAction() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Refreshes the order text area with the correct item and prices.
	 */
	public void refreshTxtAreas(int promoIndex) {
		StringBuilder s = new StringBuilder();
		s.append("Order:\n\n");

		int mIIndex = 0;
		for(MItem i : CustomerOrder.items) {
			s.append("x"+i.qty+" "+i.name+" - "+
					(decimalF(i.price * i.qty))+"\n");
			
			if(promoIndex == mIIndex) {
				s.append("    - Weekly Specials Promo -"+decimalF(i.price)+"\n");
			}
			
			if(discountIndex == mIIndex) {
				s.append("    - Rewards Account Discount -"+decimalF(i.price)+"\n");
			}

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
			mIIndex++;
		}
		if(managerDiscount > 0) {
			s.append("    - Additional Manager Discount -"+decimalF(managerDiscount)+"\n");
		}
		orderSummary.setText(s.toString());
		orderTotal.setText("Subtotal: "+decimalF(CustomerOrder.subtotal)+"\nTax: "+
				decimalF(tax * CustomerOrder.subtotal)+"\nTotal: "+decimalF(CustomerOrder.subtotal + (CustomerOrder.subtotal * tax)));
	}
	
	/**
	 * Adds the full bill panel which will allow the customer to 
	 * select if they'd like to pay the full bill or not.
	 * Added to main panel component to keep it clean and simple to go
	 * back to previous page.
	 */
	private void addFullBillPanel() {
		fullBillPanel = new JPanel();
		fullBillPanel.setBounds(0, 0, 1039, 522);
		fullBillPanel.setLayout(null);
		mainPanel.add(fullBillPanel);
		// Setting invisible when first made.
		fullBillPanel.setVisible(false);
	}
	
	/**
	 * Adds the split bill panel which will allow the customer to 
	 * select if they'd like to split the bill or not.
	 * Added to main panel component to keep it clean and simple to go
	 * back to previous page.
	 */
	private void addSplitBillPanel() {
		splitBillPanel = new JPanel();
		splitBillPanel.setBounds(0, 0, 1039, 522);
		splitBillPanel.setLayout(null);
		mainPanel.add(splitBillPanel);
		// Setting invisible when first made.
		splitBillPanel.setVisible(false);
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
