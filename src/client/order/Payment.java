package client.order;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.Client;
import client.ClientSession;
import client.utils.Constants;
import client.utils.JFrameUtils;

/**
 * Contains all of the information needed for a payment panel.
 * 
 * @author Karimshan
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class Payment extends JPanel {

	private static final long serialVersionUID = 3521869431085751251L;
	
	// Represents the amount the customer total and the amount that they paid.
	public double totalAfterTax = 0;
	public double amtPaid = 0;
	public double tip = 0; // Represents the tip that the customer can choose to give
	public double tax = 0.0825; // Represents the State of Texas tax.
	
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
	
	// Split Bill Panel Components
	public JPanel splitBillPanel;
	
	// Choose Payment Panel Components
	public JPanel choosePaymentPanel;
	public JLabel totalDueLbl;
	
	// Confirm payment Panel Components
	public JPanel confirmPaymentPanel;
	public JLabel confirmPmtLbl;
	public boolean isCard;
	private JLabel lblifYouAre;
	
	// Tip Panel Components
	public JPanel tipPanel;
	private JTextField tipTxtField;
	public JLabel totalPmtAfterTipLbl;
	
	// Receipt Panel Components
	public JPanel receiptPanel;
	public JLabel amtPaidLbl;
	
	// Lottery Panel Components
	public JPanel lotteryPanel;
	
	// Final Panel Components
	public JPanel finalPanel;
	public JLabel receiptPrefLbl;
	public JLabel sentToLbl;

	/**
	 * Constructor for the payment panel.
	 */
	public Payment() {
		super();
		setBounds(0, 0, 1039, 522);
		setLayout(null);

		// Adds the main, full/split bill, tip, receipt, lottery and final panels.
		addMainPanel();
		addSplitBillPanel();
		addChoosePaymentPanel();
		addTipPanel();
		addReceiptPanel();
		addLotteryPanel();
		addFinalPanel();
		addConfirmPaymentPanel();
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
		scrollPane.setBounds(0, 0, 450, 430);
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
		orderTotal.setBounds(0, 431, 450, 91);
		orderTotal.setText("Subtotal: $"+(CustomerOrder.subtotal)+"\nTax:\nTip:\nTotal:");
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
		if(this.tempOrder.subtotal < 0)
			this.tempOrder.subtotal = 0;
		if(CustomerOrder.subtotal < 0)
			CustomerOrder.subtotal = 0;
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
	 * Represents the customer paying the full bill at once.
	 * This is a bit easier than split bill
	 */
	public void fullBillBtnAction() {
		// Skips straight to receipt because the whole order total
		// was 0 anyway.
		if(CustomerOrder.subtotal == 0) {
			openReceiptPanel();
		}
		else {
			openPaymentTypePanel();
		}
	}

	/**
	 * Splits the customer's bill based on items they purchased
	 */
	public void splitBillBtnAction() {
		// Skips straight to receipt because the whole order total
		// was 0 anyway.
		if(CustomerOrder.subtotal == 0) {
			openReceiptPanel();
		}
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
			decimalF(tax * CustomerOrder.subtotal)+"\nTip: "
			+ ""+(decimalF(tip))+"\nTotal: "+decimalF(CustomerOrder.subtotal + (CustomerOrder.subtotal * tax)));
	}
	
	/**
	 * Adds the split bill panel which will allow the customer to 
	 * select if they'd like to split the bill or not.
	 * Added to main panel component to keep it clean and simple to go
	 * back to previous page.
	 */
	private void addSplitBillPanel() {
		splitBillPanel = new JPanel();
		splitBillPanel.setBounds(454, 0, 582, 522);
		splitBillPanel.setLayout(null);
		mainPanel.add(splitBillPanel);
		// Setting invisible when first made.
		splitBillPanel.setVisible(false);
	}
	
	/**
	 * Adds the choose payment panel
	 */
	private void addChoosePaymentPanel() {
		choosePaymentPanel = new JPanel();
		choosePaymentPanel.setBounds(454, 0, 582, 522);
		choosePaymentPanel.setLayout(null);
		mainPanel.add(choosePaymentPanel);
		
		totalDueLbl = new JLabel("<html>Subtotal: <br>Tax: <br>Total Due:</html>");
		totalDueLbl.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 29));
		totalDueLbl.setBounds(132, 13, 340, 102);
		choosePaymentPanel.add(totalDueLbl);
		
		JLabel lblPleaseChooseYour = new JLabel("Please choose your payment method:");
		lblPleaseChooseYour.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 29));
		lblPleaseChooseYour.setBounds(52, 146, 492, 51);
		choosePaymentPanel.add(lblPleaseChooseYour);
		
		JButton cardBtn = new JButton("Credit/Debit Card");
		cardBtn.setFont(new Font("Tahoma", Font.PLAIN, 50));
		cardBtn.setBounds(62, 210, 484, 82);
		cardBtn.addActionListener((e) -> {
			isCard = true;
			openConfirmPaymentPanel();
		});
		choosePaymentPanel.add(cardBtn);
		
		JButton cashBtn = new JButton("Cash");
		cashBtn.setFont(new Font("Tahoma", Font.PLAIN, 50));
		cashBtn.setBounds(60, 305, 484, 82);
		cashBtn.addActionListener((e) -> {
			isCard = false;
			openConfirmPaymentPanel();
		});
		choosePaymentPanel.add(cashBtn);
		
		JButton paymentTypeBackBtn = new JButton("<-- Back");
		paymentTypeBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Goes back to the previous screen
				prePaymentPanel.setVisible(true);
				choosePaymentPanel.setVisible(false);
			}
		});
		paymentTypeBackBtn.setFont(new Font("Tahoma", Font.PLAIN, 50));
		paymentTypeBackBtn.setBounds(60, 427, 484, 82);
		choosePaymentPanel.add(paymentTypeBackBtn);
		
		// Setting invisible when first made.
		choosePaymentPanel.setVisible(false);
	}
	
	/**
	 * Adds the confirm payment panel
	 */
	private void addConfirmPaymentPanel() {
		confirmPaymentPanel = new JPanel();
		confirmPaymentPanel.setBounds(454, 0, 582, 522);
		confirmPaymentPanel.setLayout(null);
		mainPanel.add(confirmPaymentPanel);
		
		confirmPmtLbl = new JLabel("<html>You are paying with: "
			+ "<br> Is this amount: correct?"
			+ "<br>You will not be able to go back once you"
			+ "<br>click confirm.");
		confirmPmtLbl.setFont(new Font("Tahoma", Font.PLAIN, 26));
		confirmPmtLbl.setBounds(42, 0, 489, 165);
		confirmPaymentPanel.add(confirmPmtLbl);
		
		JButton backBtn = new JButton("<-- Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Goes back to the previous screen
				confirmPaymentPanel.setVisible(false);
				choosePaymentPanel.setVisible(true);
			}
		});
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 50));
		backBtn.setBounds(60, 427, 484, 82);
		confirmPaymentPanel.add(backBtn);
		
		JButton btnConfirm = new JButton("CONFIRM");
		btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 50));
		btnConfirm.setBounds(60, 299, 484, 82);
		btnConfirm.addActionListener((e) -> {
			if(isCard) {
				openTipPanel();
			}
		});
		confirmPaymentPanel.add(btnConfirm);
		
		lblifYouAre = new JLabel("<html>If you are paying with cash, "
			+ "a member of our staff will come"
			+ "<br>and take your payment and mark it completed once they process it.");
		lblifYouAre.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblifYouAre.setBounds(42, 156, 489, 126);
		confirmPaymentPanel.add(lblifYouAre);
		
		// Setting invisible when first made.
		confirmPaymentPanel.setVisible(false);
	}
	
	/**
	 * Adds the tip panel.
	 */
	private void addTipPanel() {
		tipPanel = new JPanel();
		tipPanel.setBounds(454, 0, 582, 522);
		tipPanel.setLayout(null);
		mainPanel.add(tipPanel);
		
		JLabel lblNewLabel_2 = new JLabel("<html>We appreciate your payment!"
			+ "<br>Would you like to add a tip?"
			+ "<br>100% of tips go to our wonderful wait staff.</html>");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(54, 13, 475, 138);
		tipPanel.add(lblNewLabel_2);
		
		JButton[] percentBtns = new JButton[] { 
			new JButton("10%"),
			new JButton("15%"),
			new JButton("20%"),
			new JButton("25%"),
			new JButton("None")
		};

		for(int i = 0; i < percentBtns.length; i++) {
			percentBtns[i].setFont(new Font("Tahoma", Font.PLAIN, 25));
			percentBtns[i].setBounds(12 + (i * 115), 162, 103, 102);
			tipPanel.add(percentBtns[i]);
			final int index = i;
			percentBtns[i].addActionListener((e) -> {
				tip = index == 4 ? 0 : totalAfterTax * ((0.05 * index) + 0.10);
				tipTxtField.setText(""+(new DecimalFormat("0.00").format(tip)));
				amtPaid = totalAfterTax + tip;
				orderTotal.setText("Subtotal: "+decimalF(CustomerOrder.subtotal)+"\nTax: "+
					decimalF(tax * CustomerOrder.subtotal)+"\nTip: "
					+ ""+(decimalF(tip))+"\nTotal: "+decimalF(amtPaid));
				totalPmtAfterTipLbl.setText("<html>Total you are paying: <b>"+(decimalF(amtPaid))+"</b></html>");
			});
		}
		
		JLabel lblNewLabel_3 = new JLabel("$");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel_3.setBounds(221, 285, 30, 43);
		tipPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Custom Tip:");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel_3_1.setBounds(12, 285, 201, 43);
		tipPanel.add(lblNewLabel_3_1);
		
		tipTxtField = new JTextField();
		tipTxtField.setBounds(253, 285, 141, 43);
		tipTxtField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tipTxtField.setText("0.00");
		tipTxtField.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) { }
			
			@Override 
			public void keyReleased(KeyEvent arg0) { 
				double tipFromTxt = 0;
				boolean setNewTip = true;
				try {
					tipFromTxt = Double.parseDouble(tipTxtField.getText());
				} catch(NumberFormatException fe) {
					setNewTip = false;
					tip = 0;
				}
				if(tipFromTxt < 0) {
					setNewTip = false;
					tip = 0;
				}
				if(setNewTip) {
					tip = tipFromTxt;
				}
				amtPaid = totalAfterTax + tip;
				orderTotal.setText("Subtotal: "+decimalF(CustomerOrder.subtotal)+"\nTax: "+
					decimalF(tax * CustomerOrder.subtotal)+"\nTip: "
					+ ""+(decimalF(tip))+"\nTotal: "+decimalF(amtPaid));
				totalPmtAfterTipLbl.setText("<html>Total you are paying: <b>"+(decimalF(amtPaid))+"</b></html>");
			}
			
			@Override public void keyTyped(KeyEvent arg0) { }
			
		});
		tipPanel.add(tipTxtField);
		
		totalPmtAfterTipLbl = new JLabel("Total you are paying: ");
		totalPmtAfterTipLbl.setFont(new Font("Segoe UI Historic", Font.PLAIN, 35));
		totalPmtAfterTipLbl.setBounds(45, 341, 511, 60);
		tipPanel.add(totalPmtAfterTipLbl);
		
		JButton tipConfirmBtn = new JButton("CONFIRM");
		tipConfirmBtn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		tipConfirmBtn.setBounds(91, 407, 422, 102);
		tipConfirmBtn.addActionListener((e) -> {
			double tipFromTxt;
			try {
				tipFromTxt = Double.parseDouble(tipTxtField.getText());
			} catch(NumberFormatException fe) {
				JFrameUtils.showMessage("Tips", "Invalid tip entered, please try again.");
				return;
			}
			if(tipFromTxt < 0) {
				JFrameUtils.showMessage("Tips", "Invalid tip entered, please try again.");
				return;
			}
			openReceiptPanel();
		});
		tipPanel.add(tipConfirmBtn);
		
		// Setting invisible when first made.
		tipPanel.setVisible(false);
	}
	
	/**
	 * Adds the receipt panel.
	 */
	private void addReceiptPanel() {
		receiptPanel = new JPanel();
		receiptPanel.setBounds(454, 0, 582, 522);
		receiptPanel.setLayout(null);
		mainPanel.add(receiptPanel);
		
		JLabel lblNewLabel = new JLabel("<html>Thank you for your payment!<br>How would you like your receipt?</html>");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(145, 0, 322, 101);
		receiptPanel.add(lblNewLabel);
		
		amtPaidLbl = new JLabel("Amount paid: <dynamic>");
		amtPaidLbl.setFont(new Font("Arial Black", Font.PLAIN, 22));
		amtPaidLbl.setBounds(145, 103, 355, 38);
		receiptPanel.add(amtPaidLbl);
		
		JButton[] preferenceBtns = new JButton[] { 
			new JButton("Printed"), 
			new JButton("Emailed"), 
			new JButton("Both"), 
			new JButton("Neither") 
		};
		
		for(int i = 0; i < preferenceBtns.length; i++) {
			preferenceBtns[i].setFont(new Font("Imprint MT Shadow", Font.PLAIN, 46));
			preferenceBtns[i].setBounds(12, 168 + (i * 90), 558, 77);
			receiptPanel.add(preferenceBtns[i]);
			final int index = i;
			preferenceBtns[i].addActionListener((e) -> {
				switch(preferenceBtns[index].getText()) {
					case "Printed":
						receiptPrefLbl.setText("Your receipt has been printed!");
						sentToLbl.setText("It will be available at the kiosk's printer.");
						receiptPanel.setVisible(false);
						finalPanel.setVisible(true);
						break;
					case "Emailed":
						String email = (String) JFrameUtils.inputDialog("Email Receipt", 
							"Please enter the email address that will receive your receipt:");
						if(email == null || email.equals("") || !Constants.isValidEmail(email)) {
							JFrameUtils.showMessage("Printed and Email Receipt", 
								"Error: invalid email entered, please try again.");
							return;
						}
						receiptPrefLbl.setText("Your receipt has been emailed!");
						sentToLbl.setText("It has been sent to: "+email);
						receiptPanel.setVisible(false);
						finalPanel.setVisible(true);
						break;
					case "Both":
						email = (String) JFrameUtils.inputDialog("Email Receipt", 
							"Please enter the email address that will receive your receipt:");
						if(email == null || email.equals("") || !Constants.isValidEmail(email)) {
							JFrameUtils.showMessage("Printed and Email Receipt", 
								"Error: invalid email entered, please try again.");
							return;
						}
						receiptPrefLbl.setText("Your receipt has been printed and emailed!");
						sentToLbl.setText("It has been sent to: "+email);
						receiptPanel.setVisible(false);
						finalPanel.setVisible(true);
						break;
					case "Neither":
						receiptPrefLbl.setText("You have chosen not to receive a receipt.");
						sentToLbl.setText("");
						receiptPanel.setVisible(false);
						finalPanel.setVisible(true);
						break;
				}
			});
		}

		// Setting invisible when first made.
		receiptPanel.setVisible(false);
	}
	
	/**
	 * If the customer chooses card payment,
	 * then they're given the option to add a tip
	 * after the order.
	 */
	public void openTipPanel() {
		// Hide any more requests from the customer once they've paid and are ready
		// to leave.
		Client.clientFrame.customerSP.utilityPanel.setVisible(false);
		this.confirmPaymentPanel.setVisible(false);
		this.tipPanel.setVisible(true);
		amtPaid = totalAfterTax + tip;
		totalPmtAfterTipLbl.setText("<html>Total you are paying: <b>"+(decimalF(amtPaid))+"</b></html>");
	}
	
	/**
	 * If the customer chooses full/split bill, it will open this
	 * page to ask them to choose payment type.
	 */
	public void openPaymentTypePanel() {
		this.prePaymentPanel.setVisible(false);
		this.totalDueLbl.setText("<html>Subtotal: "+(decimalF(CustomerOrder.subtotal))+""
			+ "<br>Tax: "+(decimalF(tax * CustomerOrder.subtotal))+""
			+ "<br>Total Due: "+(decimalF(((tax * CustomerOrder.subtotal)) + CustomerOrder.subtotal))+"</html>");
		this.totalAfterTax = (tax * CustomerOrder.subtotal) + CustomerOrder.subtotal;
		this.choosePaymentPanel.setVisible(true);
	}
	
	/**
	 * If the customer chooses cash/card
	 */
	public void openConfirmPaymentPanel() {
		this.choosePaymentPanel.setVisible(false);
		this.confirmPmtLbl.setText("<html>You are paying with: <b>"+(isCard ? "Credit/Debit Card" : "Cash")+"</b>"
				+ "<br> Is this amount: <b>"+(decimalF(totalAfterTax))+"</b> correct?"
				+ "<br>You will not be able to go back once you"
				+ "<br>click confirm.");
		this.confirmPaymentPanel.setVisible(true);
	}

	/**
	 * If the customer gets so far as to get to the receipt panel,
	 * then they 
	 */
	public void openReceiptPanel() {
		// Hide any more requests from the customer once they've paid and are ready
		// to leave.
		Client.clientFrame.customerSP.utilityPanel.setVisible(false);
		this.prePaymentPanel.setVisible(false);
		this.tipPanel.setVisible(false);
		this.amtPaidLbl.setText("Amount paid: "+(decimalF(amtPaid)));
		this.receiptPanel.setVisible(true);
	}
	
	/**
	 * Adds the lottery panel.
	 */
	private void addLotteryPanel() {
		lotteryPanel = new JPanel();
		lotteryPanel.setBounds(454, 0, 582, 522);
		lotteryPanel.setLayout(null);
		mainPanel.add(lotteryPanel);
		// Setting invisible when first made.
		lotteryPanel.setVisible(false);
	}
	
	/**
	 * Adds the final panel.
	 */
	private void addFinalPanel() {
		finalPanel = new JPanel();
		finalPanel.setBounds(454, 0, 582, 522);
		finalPanel.setLayout(null);
		mainPanel.add(finalPanel);
		
		receiptPrefLbl = new JLabel("Your receipt has been: ");
		receiptPrefLbl.setFont(new Font("Lucida Bright", Font.PLAIN, 25));
		receiptPrefLbl.setBounds(12, 13, 565, 54);
		finalPanel.add(receiptPrefLbl);
		
		JLabel lblNewLabel_1 = new JLabel("<html>Thank you for visiting Seven Guys!<br>We hope you have a wonderful day!<br>Please stay safe during these tough times,<br>and we are here for you if you<br>ever need any sort of help.</html>");
		lblNewLabel_1.setFont(new Font("Lucida Bright", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(12, 91, 565, 276);
		finalPanel.add(lblNewLabel_1);
		
		JButton exitBtn = new JButton("Exit");
		exitBtn.setFont(new Font("Lucida Bright", Font.PLAIN, 51));
		exitBtn.setBounds(83, 388, 438, 121);
		exitBtn.addActionListener((e) -> {
			Client.restart();
		});
		finalPanel.add(exitBtn);
		
		sentToLbl = new JLabel("We have sent it to: ");
		sentToLbl.setFont(new Font("Lucida Bright", Font.PLAIN, 25));
		sentToLbl.setBounds(12, 65, 565, 41);
		finalPanel.add(sentToLbl);
		
		
		// Setting invisible when first made.
		finalPanel.setVisible(false);
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
