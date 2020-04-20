/*
 * Dillon McPherson 
 * 
 * creates the GUI for the order screen
 */

package client.order;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

// Test by dillon
// Shan


import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import client.Client;
import client.ClientSession;
import client.network.packet.PacketDecoder;
import client.utils.JFrameUtils;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = -1338118268945423515L;

	public JPanel mainPanel;
	public JPanel postOrder;

	private int page_number = 0;
	private int ing_page_number = 0;
	private int index = 0;
	private MItem item = new MItem();


	private JTextField item_name_textfield;
	private JTextField item_price_textfield;

	public List<JButton> entreesFM = new ArrayList<JButton>();
	public List<JButton> sidesFM = new ArrayList<JButton>();
	public List<JButton> drinksFM = new ArrayList<JButton>();
	public List<JButton> dessertsFM = new ArrayList<JButton>();
	private List<JButton> add = new ArrayList<JButton>();
	private List<JButton> sub = new ArrayList<JButton>();
	private List<JButton> ingredientSubButton = new ArrayList<JButton>();
	private List<JTextField> ingredientsFM = new ArrayList<JTextField>();
	private List<JTextField> ingredientsQuantityFM = new ArrayList<JTextField>();
	private List<String> totalIngredientsFM = new ArrayList<String>();
	private List<String> totalIngredientsQuantityFM = new ArrayList<String>();

	public JPanel MenuListFrame;
	private JTextField item_allergens_textfield;
	private JTextField vegan_textfield;

	private JButton next_ingredient_button = new JButton("Next page");
	private JButton previous_ingredient_button = new JButton("previous page");

	// Shan changes
	public JTextArea specialReqs;
	public JComboBox qtyCBox;
	public JTextArea orderSummary;
	public JTextArea orderTotal;
	
	public double subtotal;
	public double tax = 0.0825; // State of TX tax
	public int promoIndex = -1;

	private JButton next_page_button;
	private JButton previous_page_button;
	private JToggleButton dessert_button;
	private JToggleButton entree_button;
	private JToggleButton side_button;
	private JToggleButton drink_button;
	
	public JTextArea orderSummaryPostOrder;
	public JLabel orderMsgLbl;

	/**
	 * Create the panel.
	 */
	public MenuPanel() {
		super();
		setBounds(0, 0, 1039, 522);
		setLayout(null);

		// Main Panel that appears.
		addMainPanel();

		// The panel that appears after the order.
		addPostOrderPanel();

	}
	
	public void updateMessage(String msg) {
		orderMsgLbl.setText(msg);
	}
	
	private void addPostOrderPanel() {
		postOrder = new JPanel();
		postOrder.setBounds(0, 0, 1039, 522);
		postOrder.setLayout(null);
		add(postOrder);
		
		JLabel placeOrderLbl = new JLabel("Thanks for placing your order at Seven Guys!");
		placeOrderLbl.setFont(new Font("Tahoma", Font.PLAIN, 22));
		placeOrderLbl.setBounds(291, 0, 470, 59);
		postOrder.add(placeOrderLbl);
		
		JLabel lblYouCanPay = new JLabel("You can pay for your meal using the pay button after tapping on \"back\"");
		lblYouCanPay.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblYouCanPay.setBounds(174, 38, 803, 59);
		postOrder.add(lblYouCanPay);
		
		JLabel lblNewLabel_1 = new JLabel("Your order:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(174, 92, 116, 25);
		postOrder.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 125, 431, 384);
		postOrder.add(scrollPane);
		
		orderSummaryPostOrder = new JTextArea();
		orderSummaryPostOrder.setEditable(false);
		orderSummaryPostOrder.setFont(new Font("Monospaced", Font.PLAIN, 14));
		scrollPane.setViewportView(orderSummaryPostOrder);
		
		orderMsgLbl = new JLabel("Order Details");
		orderMsgLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		orderMsgLbl.setBounds(510, 236, 490, 73);
		postOrder.add(orderMsgLbl);
		
		postOrder.setVisible(false);
	}
	
	public void doPostOrder() {
		this.mainPanel.setVisible(false);
		this.postOrder.setVisible(true);
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
		
		orderSummaryPostOrder.setText(s.toString());
	}

	private void addMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 1039, 522);
		mainPanel.setLayout(null);
		add(mainPanel);

		//creates a frame to hold the menu items
		MenuListFrame = new JPanel();
		MenuListFrame.setBorder(null);
		MenuListFrame.setBounds(347, 0, 347, 522);
		mainPanel.add(MenuListFrame);
		MenuListFrame.setLayout(null);

		next_page_button = new JButton("Next page");
		next_page_button.setVisible(false);
		next_page_button.setBounds(174, 490, 173, 32);
		MenuListFrame.add(next_page_button);

		previous_page_button = new JButton("Previous page");
		previous_page_button.setVisible(false);
		previous_page_button.setBounds(0, 490, 173, 32);
		MenuListFrame.add(previous_page_button);

		//creates a frame to hold the order summary
		JPanel OrderListFrame = new JPanel();
		OrderListFrame.setBounds(693, 0, 346, 522);
		mainPanel.add(OrderListFrame);
		OrderListFrame.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 346, 370);
		OrderListFrame.add(scrollPane);

		// Shan - Text Area that holds the order summary
		orderSummary = new JTextArea();
		orderSummary.setFont(new Font("Monospaced", Font.PLAIN, 13));
		orderSummary.setEditable(false);
		orderSummary.setLineWrap(true);
		orderSummary.setText("Order: ");
		scrollPane.setViewportView(orderSummary);

		// Shan - Text Area that holds order total;
		orderTotal = new JTextArea();
		orderTotal.setFont(new Font("Monospaced", Font.PLAIN, 15));
		orderTotal.setEditable(false);
		orderTotal.setLineWrap(true);
		orderTotal.setBounds(0, 370, 346, 71);
		orderTotal.setText("Subtotal: $"+(subtotal)+"\nTax:\nTotal:");
		OrderListFrame.add(orderTotal);

		// Shan - Added Clear button
		JButton clearOBtn = new JButton("Clear Order");
		clearOBtn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		clearOBtn.setBounds(0, 440, 173, 82);
		OrderListFrame.add(clearOBtn);

		JButton place_order_button = new JButton("Place Order");
		place_order_button.setFont(new Font("Tahoma", Font.PLAIN, 22));
		place_order_button.setBounds(173, 440, 173, 82);
		OrderListFrame.add(place_order_button);

		JPanel OrderDetails = new JPanel();
		OrderDetails.setVisible(false);
		OrderDetails.setBounds(0, 0, 1039, 520);
		mainPanel.add(OrderDetails);
		OrderDetails.setLayout(null);

		JPanel PictureFrame = new JPanel();
		PictureFrame.setBounds(0, 0, 518, 520);
		OrderDetails.add(PictureFrame);
		PictureFrame.setLayout(null);

		JLabel lblNewLabel = new JLabel("No Picture Added");
		lblNewLabel.setBounds(160, 70, 200, 200);
		PictureFrame.add(lblNewLabel);


		item_name_textfield = new JTextField();
		item_name_textfield.setEditable(false);
		item_name_textfield.setBounds(160, 10, 200, 50);
		PictureFrame.add(item_name_textfield);
		item_name_textfield.setColumns(10);

		item_price_textfield = new JTextField();
		item_price_textfield.setEditable(false);
		item_price_textfield.setBounds(100, 10, 50, 50);
		PictureFrame.add(item_price_textfield);
		item_price_textfield.setColumns(10);

		JTextField item_calories_textfield = new JTextField();
		item_calories_textfield.setEditable(false);
		item_calories_textfield.setBounds(370, 10, 125, 50);
		PictureFrame.add(item_calories_textfield);
		item_calories_textfield.setColumns(10);

		JTextArea item_description_textfield = new JTextArea();
		item_description_textfield.setWrapStyleWord(true);
		item_description_textfield.setLineWrap(true);
		item_description_textfield.setEditable(false);
		item_description_textfield.setBounds(50, 280, 438, 100);
		PictureFrame.add(item_description_textfield);

		item_allergens_textfield = new JTextField();
		item_allergens_textfield.setEditable(false);
		item_allergens_textfield.setText("Contains: ");
		item_allergens_textfield.setBounds(59, 450, 400, 35);
		PictureFrame.add(item_allergens_textfield);
		item_allergens_textfield.setColumns(10);

		vegan_textfield = new JTextField();
		vegan_textfield.setFont(new Font("Tahoma", Font.PLAIN, 25));
		vegan_textfield.setHorizontalAlignment(SwingConstants.CENTER);
		vegan_textfield.setText("Vegetarian");
		vegan_textfield.setVisible(false);
		vegan_textfield.setEditable(false);
		vegan_textfield.setBounds(184, 390, 150, 50);
		PictureFrame.add(vegan_textfield);
		vegan_textfield.setColumns(10);



		JPanel OrderSpecificsFrame = new JPanel();
		OrderSpecificsFrame.setBounds(519, 0, 518, 520);
		OrderDetails.add(OrderSpecificsFrame);
		OrderSpecificsFrame.setLayout(null);


		// Shan - add special requests
		JLabel specialReqLabel = new JLabel("Special Requests?");
		specialReqLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		specialReqLabel.setBounds(0, 360, 120, 30);
		specialReqLabel.setVisible(true);
		OrderSpecificsFrame.add(specialReqLabel);

		// special req text area
		specialReqs = new JTextArea();
		specialReqs.setFont(new Font("Monospaced", Font.PLAIN, 13));
		specialReqs.setBounds(0, 395, 220, 110);
		specialReqs.setLineWrap(true);
		specialReqs.setText("none");
		OrderSpecificsFrame.add(specialReqs);

		// Shan - quantity button
		JLabel qtyLbl = new JLabel("QUANTITY:");
		qtyLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		qtyLbl.setBounds(270, 370, 120, 30);
		qtyLbl.setVisible(true);
		OrderSpecificsFrame.add(qtyLbl);

		// Shan - qty combobox
		qtyCBox = new JComboBox( new Integer[] {
				1, 2, 3, 4, 5, 6, 7, 8, 9, 10
		});

		qtyCBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		qtyCBox.setBounds(370, 370, 100, 30);
		qtyCBox.setVisible(true);
		OrderSpecificsFrame.add(qtyCBox);

		JButton cancel_button = new JButton("Cancel");
		cancel_button.setBounds(305, 430, 90, 90);
		OrderSpecificsFrame.add(cancel_button);

		JButton confirm_button = new JButton("Confirm");
		confirm_button.setBounds(400, 430, 90, 90);
		OrderSpecificsFrame.add(confirm_button);

		//JButton next_ingredient_button = new JButton("Next page");
		next_ingredient_button.setVisible(false);
		next_ingredient_button.setBounds(218, 325, 125, 25);
		OrderSpecificsFrame.add(next_ingredient_button);

		//JButton previous_ingredient_ = new JButton("Previous page");
		previous_ingredient_button.setVisible(false);
		previous_ingredient_button.setBounds(93, 325, 125, 25);
		OrderSpecificsFrame.add(previous_ingredient_button);

		JPanel OrderTypeFrame = new JPanel();
		OrderTypeFrame.setBounds(0, 0, 346, 520);
		mainPanel.add(OrderTypeFrame);
		OrderTypeFrame.setLayout(null);

		dessert_button = new JToggleButton("Desserts");
		dessert_button.setBounds(0, 390, 346, 130);
		dessert_button.setFont(new Font("Tahoma", Font.PLAIN, 45));
		OrderTypeFrame.add(dessert_button);

		entree_button = new JToggleButton("Entrees");
		entree_button.setBounds(0, 0, 346, 130);
		entree_button.setFont(new Font("Tahoma", Font.PLAIN, 45));
		OrderTypeFrame.add(entree_button);

		side_button = new JToggleButton("Sides");
		side_button.setBounds(0, 130, 346, 130);
		side_button.setFont(new Font("Tahoma", Font.PLAIN, 45));
		OrderTypeFrame.add(side_button);

		drink_button = new JToggleButton("Drinks");
		drink_button.setBounds(0, 260, 346, 130);
		drink_button.setFont(new Font("Tahoma", Font.PLAIN, 45));
		OrderTypeFrame.add(drink_button);

		// displays first 7 entrees when entree button is clicked
		entree_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (entree_button.isSelected())
				{
					getMenuItems();
					try
					{
						Thread.sleep(50);
					}
					catch (InterruptedException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					page_number = 0;
					next_page_button.setVisible(false);
					previous_page_button.setVisible(false);

					entreesFM.clear();
					int buttonIndex = 0;
					for (MItem mItem : Menu.instance)
					{

						if ((mItem.menuType.equals("entree") && (buttonIndex < 7)))
						{
							JButton b = new JButton();
							b.setText(mItem.name);
							b.setEnabled(true);
							b.setVisible(false);
							b.setFont(new Font("Tahoma", Font.PLAIN, 30));
							b.setBounds(0, 70 * buttonIndex, 347, 70);
							MenuListFrame.add(b);
							entreesFM.add(b);
							b.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent e)
								{

									seeMenuDetails(mItem, OrderSpecificsFrame);
									item_name_textfield.setText(mItem.name);
									item = mItem;
									if (mItem.type.equals("default"))
										vegan_textfield.setVisible(false);

									else if (mItem.type.equals("vegetarian"))
									{
										vegan_textfield.setVisible(true);
										vegan_textfield.setText("vegetarian");
									}
									else 
									{	
										vegan_textfield.setVisible(true);
										vegan_textfield.setText("vegan");
									}
									String text = "data\\\\menu-images\\\\";
									text = text.concat(mItem.name);
									text = text.concat(".jpg");
									lblNewLabel.setIcon(new ImageIcon(text));

									text = "Calories: ";
									text = text.concat(String.valueOf(mItem.calories));
									item_calories_textfield.setText(text);

									item_allergens_textfield.setText("Allergens: "+ mItem.allergens);
									item_description_textfield.setText(mItem.description);
									item_price_textfield.setText("$"+String.valueOf(mItem.price));
									MenuListFrame.setVisible(false);
									OrderListFrame.setVisible(false);
									OrderTypeFrame.setVisible(false);
									OrderDetails.setVisible(true);
								}
							});
							buttonIndex++;
						}
						else if ((mItem.menuType.equals("entree") && (buttonIndex >= 7)))
						{
							next_page_button.setVisible(true);
							break;
						}						
					}
					for(JButton b : sidesFM) 
						b.setVisible(false);

					for(JButton b : dessertsFM) 
						b.setVisible(false);

					for(JButton b : drinksFM) 
						b.setVisible(false);

					for (JButton b : entreesFM)
						b.setVisible(true);

					side_button.setSelected(false);
					drink_button.setSelected(false);
					dessert_button.setSelected(false);
				}
				else
				{
					for (JButton b : entreesFM)
						b.setVisible(false);

					entree_button.setSelected(false);
					next_page_button.setVisible(false);
					previous_page_button.setVisible(false);
				}
			}
		});

		//displays first 7 sides when side button is clicked
		side_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (side_button.isSelected())
				{
					getMenuItems();
					try
					{
						Thread.sleep(50);
					}
					catch (InterruptedException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					page_number = 0;
					next_page_button.setVisible(false);
					previous_page_button.setVisible(false);

					sidesFM.clear();
					int buttonIndex = 0;
					for (MItem mItem : Menu.instance)
					{
						if ((mItem.menuType.equals("side") && (buttonIndex < 7)))
						{
							JButton b = new JButton();
							b.setText(mItem.name);
							b.setEnabled(true);
							b.setVisible(false);
							b.setFont(new Font("Tahoma", Font.PLAIN, 30));
							b.setBounds(0, 70 * buttonIndex, 347, 70);
							MenuListFrame.add(b);
							sidesFM.add(b);
							b.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent e)
								{

									seeMenuDetails(mItem, OrderSpecificsFrame);
									item_name_textfield.setText(mItem.name);
									item = mItem;
									if (mItem.type.equals("default"))
										vegan_textfield.setVisible(false);

									else if (mItem.type.equals("vegetarian"))
									{
										vegan_textfield.setVisible(true);
										vegan_textfield.setText("vegetarian");
									}
									else 
									{	
										vegan_textfield.setVisible(true);
										vegan_textfield.setText("vegan");
									}
									String text = "data\\\\menu-images\\\\";
									text = text.concat(mItem.name);
									text = text.concat(".jpg");
									lblNewLabel.setIcon(new ImageIcon(text));

									text = "Calories: ";
									text = text.concat(String.valueOf(mItem.calories));
									item_calories_textfield.setText(text);

									item_allergens_textfield.setText("Allergens: "+ mItem.allergens);
									item_description_textfield.setText(mItem.description);
									item_price_textfield.setText("$"+String.valueOf(mItem.price));
									MenuListFrame.setVisible(false);
									OrderListFrame.setVisible(false);
									OrderTypeFrame.setVisible(false);
									OrderDetails.setVisible(true);
								}
							});
							buttonIndex++;
						}
						else if ((mItem.menuType.equals("side") && (buttonIndex >= 7)))
						{
							next_page_button.setVisible(true);
							break;
						}
					}

					for(JButton b : sidesFM) 
						b.setVisible(true);

					for(JButton b : dessertsFM) 
						b.setVisible(false);

					for(JButton b : drinksFM) 
						b.setVisible(false);

					for (JButton b : entreesFM)
						b.setVisible(false);

					entree_button.setSelected(false);
					drink_button.setSelected(false);
					dessert_button.setSelected(false);
				}
				else
				{
					for (JButton b : sidesFM)
						b.setVisible(false);

					side_button.setSelected(false);
					next_page_button.setVisible(false);
					previous_page_button.setVisible(false);
				}
			}
		});

		//displays first 7 drinks when drink button is clicked
		drink_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (drink_button.isSelected())
				{
					getMenuItems();
					try
					{
						Thread.sleep(50);
					}
					catch (InterruptedException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					page_number = 0;
					next_page_button.setVisible(false);
					previous_page_button.setVisible(false);

					drinksFM.clear();
					int buttonIndex = 0;
					for (MItem mItem : Menu.instance)
					{
						if ((mItem.menuType.equals("drink") && (buttonIndex < 7)))
						{
							JButton b = new JButton();
							b.setText(mItem.name);
							b.setEnabled(true);
							b.setVisible(false);
							b.setFont(new Font("Tahoma", Font.PLAIN, 30));
							b.setBounds(0, 70 * buttonIndex, 347, 70);
							MenuListFrame.add(b);
							drinksFM.add(b);
							b.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent e)
								{

									seeMenuDetails(mItem, OrderSpecificsFrame);
									item_name_textfield.setText(mItem.name);
									item = mItem;
									if (mItem.type.equals("default"))
										vegan_textfield.setVisible(false);

									else if (mItem.type.equals("vegetarian"))
									{
										vegan_textfield.setVisible(true);
										vegan_textfield.setText("vegetarian");
									}
									else 
									{	
										vegan_textfield.setVisible(true);
										vegan_textfield.setText("vegan");
									}

									String text = "data\\\\menu-images\\\\";
									text = text.concat(mItem.name);
									text = text.concat(".jpg");
									lblNewLabel.setIcon(new ImageIcon(text));

									text = "Calories: ";
									text = text.concat(String.valueOf(mItem.calories));
									item_calories_textfield.setText(text);

									item_allergens_textfield.setText("Allergens: "+ mItem.allergens);
									item_description_textfield.setText(mItem.description);
									item_price_textfield.setText("$"+String.valueOf(mItem.price));
									MenuListFrame.setVisible(false);
									OrderListFrame.setVisible(false);
									OrderTypeFrame.setVisible(false);
									OrderDetails.setVisible(true);
								}
							});
							buttonIndex++;
						}
						else if ((mItem.menuType.equals("drink") && (buttonIndex >= 7)))
						{
							next_page_button.setVisible(true);
							break;
						}
					}

					for(JButton b : sidesFM) 
						b.setVisible(false);

					for(JButton b : dessertsFM) 
						b.setVisible(false);

					for(JButton b : drinksFM) 
						b.setVisible(true);

					for (JButton b : entreesFM)
						b.setVisible(false);

					entree_button.setSelected(false);
					side_button.setSelected(false);
					dessert_button.setSelected(false);
				}
				else
				{
					for (JButton b : drinksFM)
						b.setVisible(false);

					drink_button.setSelected(false);
					next_page_button.setVisible(false);
					previous_page_button.setVisible(false);
				}
			}
		});

		//displays first 7 desserts when dessert button is clicked
		dessert_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dessert_button.isSelected())
				{
					getMenuItems();
					try
					{
						Thread.sleep(50);
					}
					catch (InterruptedException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					page_number = 0;
					next_page_button.setVisible(false);
					previous_page_button.setVisible(false);

					dessertsFM.clear();
					int buttonIndex = 0;
					for (MItem mItem : Menu.instance)
					{
						if ((mItem.menuType.equals("dessert") && (buttonIndex < 7)))
						{
							JButton b = new JButton();
							b.setText(mItem.name);
							b.setEnabled(true);
							b.setVisible(false);
							b.setFont(new Font("Tahoma", Font.PLAIN, 30));
							b.setBounds(0, 70 * buttonIndex, 347, 70);
							MenuListFrame.add(b);
							dessertsFM.add(b);
							b.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent e)
								{
									seeMenuDetails(mItem, OrderSpecificsFrame);
									item_name_textfield.setText(mItem.name);
									item = mItem;
									if (mItem.type.equals("default"))
										vegan_textfield.setVisible(false);

									else if (mItem.type.equals("vegetarian"))
									{
										vegan_textfield.setVisible(true);
										vegan_textfield.setText("vegetarian");
									}
									else 
									{	
										vegan_textfield.setVisible(true);
										vegan_textfield.setText("vegan");
									}

									String text = "data\\\\menu-images\\\\";
									text = text.concat(mItem.name);
									text = text.concat(".jpg");
									lblNewLabel.setIcon(new ImageIcon(text));

									text = "Calories: ";
									text = text.concat(String.valueOf(mItem.calories));
									item_calories_textfield.setText(text);

									item_allergens_textfield.setText("Allergens: "+ mItem.allergens);
									item_description_textfield.setText(mItem.description);
									item_price_textfield.setText("$"+String.valueOf(mItem.price));
									MenuListFrame.setVisible(false);
									OrderListFrame.setVisible(false);
									OrderTypeFrame.setVisible(false);
									OrderDetails.setVisible(true);
								}
							});
							buttonIndex++;
						}
						else if ((mItem.menuType.equals("dessert") && (buttonIndex >= 7)))
						{
							next_page_button.setVisible(true);
							break;
						}
					}

					for(JButton b : sidesFM) 
						b.setVisible(false);

					for(JButton b : dessertsFM) 
						b.setVisible(true);

					for(JButton b : drinksFM) 
						b.setVisible(false);

					for (JButton b : entreesFM)
						b.setVisible(false);

					entree_button.setSelected(false);
					side_button.setSelected(false);
					drink_button.setSelected(false);
				}
				else
				{
					for (JButton b : dessertsFM)
						b.setVisible(false);

					dessert_button.setSelected(false);
					next_page_button.setVisible(false);
					previous_page_button.setVisible(false);
				}
			}
		});

		//displays next 7 items when next page button is clicked
		next_page_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int buttonIndex = 0;
				page_number++;

				if (entree_button.isSelected())
				{				
					for (JButton b : entreesFM)
						b.setVisible(false);

					entreesFM.clear();

					for (MItem mItem : Menu.instance)
					{
						if ((mItem.menuType.equals("entree") && ((buttonIndex >= (7 * page_number)) && (buttonIndex < (7 * (page_number + 1))))))
						{
							JButton b = new JButton();
							b.setText(mItem.name);
							b.setEnabled(true);
							b.setVisible(false);
							b.setFont(new Font("Tahoma", Font.PLAIN, 30));
							b.setBounds(0, 70 * (buttonIndex % 7), 347, 70);
							b.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent e)
								{

									seeMenuDetails(mItem, OrderSpecificsFrame);
									item_name_textfield.setText(mItem.name);
									item = mItem;
									item_description_textfield.setText(mItem.description);
									item_price_textfield.setText("$"+String.valueOf(mItem.price));
									MenuListFrame.setVisible(false);
									OrderListFrame.setVisible(false);
									OrderTypeFrame.setVisible(false);
									OrderDetails.setVisible(true);
								}
							});
							MenuListFrame.add(b);
							entreesFM.add(b);
							buttonIndex++;
						}
						else if (mItem.menuType.equals("entree"))
							buttonIndex++;

						for (JButton b : entreesFM)
							b.setVisible(true);
					}
				}	
				else if (side_button.isSelected())
				{				
					for (JButton b : sidesFM)
						b.setVisible(false);

					sidesFM.clear();

					for (MItem mItem : Menu.instance)
					{
						if ((mItem.menuType.equals("side") && ((buttonIndex >= (7 * page_number)) && (buttonIndex < (7 * (page_number + 1))))))
						{
							JButton b = new JButton();
							b.setText(mItem.name);
							b.setEnabled(true);
							b.setVisible(false);
							b.setFont(new Font("Tahoma", Font.PLAIN, 30));
							b.setBounds(0, 70 * (buttonIndex % 7), 347, 70);
							MenuListFrame.add(b);
							sidesFM.add(b);
							buttonIndex++;
						}
						else if (mItem.menuType.equals("side"))
							buttonIndex++;

						for (JButton b : sidesFM)
							b.setVisible(true);
					}
				}
				else if (drink_button.isSelected())
				{				
					for (JButton b : drinksFM)
						b.setVisible(false);

					drinksFM.clear();

					for (MItem mItem : Menu.instance)
					{
						if ((mItem.menuType.equals("drink") && ((buttonIndex >= (7 * page_number)) && (buttonIndex < (7 * (page_number + 1))))))
						{
							JButton b = new JButton();
							b.setText(mItem.name);
							b.setEnabled(true);
							b.setVisible(false);
							b.setFont(new Font("Tahoma", Font.PLAIN, 30));
							b.setBounds(0, 70 * (buttonIndex % 7), 347, 70);
							MenuListFrame.add(b);
							drinksFM.add(b);
							buttonIndex++;
						}
						else if (mItem.menuType.equals("drink"))
							buttonIndex++;

						for (JButton b : drinksFM)
							b.setVisible(true);
					}
				}
				else if (dessert_button.isSelected())
				{				
					for (JButton b : dessertsFM)
						b.setVisible(false);

					dessertsFM.clear();

					for (MItem mItem : Menu.instance)
					{
						if ((mItem.menuType.equals("dessert") && ((buttonIndex >= (7 * page_number)) && (buttonIndex < (7 * (page_number + 1))))))
						{
							JButton b = new JButton();
							b.setText(mItem.name);
							b.setEnabled(true);
							b.setVisible(false);
							b.setFont(new Font("Tahoma", Font.PLAIN, 30));
							b.setBounds(0, 70 * (buttonIndex % 7), 347, 70);
							MenuListFrame.add(b);
							dessertsFM.add(b);
							buttonIndex++;
						}
						else if (mItem.menuType.equals("dessert"))
							buttonIndex++;

						for (JButton b : dessertsFM)
							b.setVisible(true);
					}
				}

				if (buttonIndex < (7 * (page_number + 1)))
					next_page_button.setVisible(false);

				previous_page_button.setVisible(true);
			}
		});	

		//displays previous 7 items when previous page button is clicked
		previous_page_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int buttonIndex = 0;
				page_number--;

				if (entree_button.isSelected())
				{				
					for (JButton b : entreesFM)
						b.setVisible(false);

					entreesFM.clear();

					for (MItem mItem : Menu.instance)
					{
						if ((mItem.menuType.equals("entree") && ((buttonIndex >= (7 * page_number)) && (buttonIndex < (7 * (page_number + 1))))))
						{
							JButton b = new JButton();
							b.setText(mItem.name);
							b.setEnabled(true);
							b.setVisible(false);
							b.setFont(new Font("Tahoma", Font.PLAIN, 30));
							b.setBounds(0, 70 * (buttonIndex % 7), 347, 70);
							MenuListFrame.add(b);
							entreesFM.add(b);
							buttonIndex++;
						}
						else if (mItem.menuType.equals("entree"))
							buttonIndex++;

						for (JButton b : entreesFM)
							b.setVisible(true);
					}
				}	
				else if (side_button.isSelected())
				{				
					for (JButton b : sidesFM)
						b.setVisible(false);

					sidesFM.clear();

					for (MItem mItem : Menu.instance)
					{
						if ((mItem.menuType.equals("side") && ((buttonIndex >= (7 * page_number)) && (buttonIndex < (7 * (page_number + 1))))))
						{
							JButton b = new JButton();
							b.setText(mItem.name);
							b.setEnabled(true);
							b.setVisible(false);
							b.setFont(new Font("Tahoma", Font.PLAIN, 30));
							b.setBounds(0, 70 * (buttonIndex % 7), 347, 70);
							MenuListFrame.add(b);
							sidesFM.add(b);
							buttonIndex++;
						}
						else if (mItem.menuType.equals("side"))
							buttonIndex++;

						for (JButton b : sidesFM)
							b.setVisible(true);
					}
				}
				else if (drink_button.isSelected())
				{				
					for (JButton b : drinksFM)
						b.setVisible(false);

					drinksFM.clear();

					for (MItem mItem : Menu.instance)
					{
						if ((mItem.menuType.equals("drink") && ((buttonIndex >= (7 * page_number)) && (buttonIndex < (7 * (page_number + 1))))))
						{
							JButton b = new JButton();
							b.setText(mItem.name);
							b.setEnabled(true);
							b.setVisible(false);
							b.setFont(new Font("Tahoma", Font.PLAIN, 30));
							b.setBounds(0, 70 * (buttonIndex % 7), 347, 70);
							MenuListFrame.add(b);
							drinksFM.add(b);
							buttonIndex++;
						}
						else if (mItem.menuType.equals("drink"))
							buttonIndex++;

						for (JButton b : drinksFM)
							b.setVisible(true);
					}
				}
				else if (dessert_button.isSelected())
				{				
					for (JButton b : dessertsFM)
						b.setVisible(false);

					dessertsFM.clear();

					for (MItem mItem : Menu.instance)
					{

						if ((mItem.menuType.equals("dessert") && ((buttonIndex >= (7 * page_number)) && (buttonIndex < (7 * (page_number + 1))))))
						{
							JButton b = new JButton();
							b.setText(mItem.name);
							b.setEnabled(true);
							b.setVisible(false);
							b.setFont(new Font("Tahoma", Font.PLAIN, 30));
							b.setBounds(0, 70 * (buttonIndex % 7), 347, 70);
							MenuListFrame.add(b);
							dessertsFM.add(b);
							buttonIndex++;

						}
						else if (mItem.menuType.equals("dessert"))
							buttonIndex++;

						for (JButton b : dessertsFM)
							b.setVisible(true);
					}
				}

				if (page_number < 1)
					previous_page_button.setVisible(false);

				next_page_button.setVisible(true);
			}
		});


		// Shan - Added clear order button
		clearOBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean confirmOrder = JFrameUtils.confirmDialog("Your Order",
						"Are you sure you would like to clear your existing order? This action cannot"
								+ " be undone.");
				if (!confirmOrder) {
					return;
				} 
				else {
					CustomerOrder.clear();
					subtotal = 0;
					promoIndex = -1;
					refreshOrderTxtArea();
					if(ClientSession.receivedSpecialNoti) {
						int result = ClientSession.checkSpecialsDay();
						if(result == 1) {
							ClientSession.kidsEatFree = true;
						}
						else if(result == 2) {
							ClientSession.freeDrinkWPur = true;
						}
					}
				}
			}
		});

		// Button to allow customer to submit order
		place_order_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(CustomerOrder.items.isEmpty()) {
					JFrameUtils.showMessage("Placing an Order", 
							"Your order is currently empty! Try adding some items to the cart.");
					return;
				}
				boolean confirmOrder = JFrameUtils.confirmDialog("Your Order",
						"Are you sure you would like to place this order? You cannot make changes"
								+ " once it has been placed.");

				// Object input = JFrameUtils.inputDialog("Order Price", "Choose what you want to pay for this order:");
				if (!confirmOrder)
				{
					return;
				}
				else
				{
					System.out.println("CODE HERE TO AND SEND INFO TO KITCHEN / SERVER ");
					JFrameUtils.showMessage("Placing an Order", 
							"Please wait, attempting to communicate with the restaurant\n and send the order...");
					
					Client.session.getPacketEncoder().sendOrder(subtotal);
				}
			}
		});
		
		// clear order button
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JButton b : add)
					b.setVisible(false);

				for (JButton b : sub)
					b.setVisible(false);

				for (JTextField tf : ingredientsFM)
					tf.setVisible(false);

				for (JTextField tf : ingredientsQuantityFM)
					tf.setVisible(false);


				add.clear();
				sub.clear();				
				ingredientsFM.clear();
				totalIngredientsFM.clear();
				ingredientsQuantityFM.clear();
				totalIngredientsQuantityFM.clear();

				OrderTypeFrame.setVisible(true);
				OrderListFrame.setVisible(true);
				MenuListFrame.setVisible(true);
				OrderDetails.setVisible(false);	

				ing_page_number = 0;
			}
		});		

		confirm_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (JButton b : add)
					b.setVisible(false);

				for (JButton b : sub)
					b.setVisible(false);

				for (JTextField tf : ingredientsFM)
					tf.setVisible(false);

				for (JTextField tf : ingredientsQuantityFM)
					tf.setVisible(false);

				String itemName = item_name_textfield.getText();
				double price = Menu.getItem(itemName).price;
				int qty = (int) qtyCBox.getSelectedItem();
				String specialReq = specialReqs.getText();
				specialReq = specialReq.replaceAll("~", "");
				String modifiedIngs = "";

				MItem oldItem = Menu.getItem(itemName);

				String[] oldIngTok = oldItem.ingredients.split(",");

				int index = 0;
				for(int i = 0; i < oldIngTok.length; i++) {
					String[] splTok = oldIngTok[i].split(":");
					if(splTok[2].equals("f")) {
						modifiedIngs += ""+splTok[0]+":"+
								splTok[1];
						if(i < oldIngTok.length - 1)
							modifiedIngs += ",";
						continue;
					}
					modifiedIngs += ""+ingredientsFM.get(index).getText()+":"+
							ingredientsQuantityFM.get(index).getText();
					if(index < ingredientsFM.size() - 1)
						modifiedIngs += ",";
					index++;
				}

				MItem orderItem = new MItem();
				orderItem.name = itemName;
				orderItem.price = price;
				orderItem.qty = qty;
				orderItem.specialReqs = specialReq;
				orderItem.ingredients = modifiedIngs;
				orderItem.menuType = oldItem.menuType;

				CustomerOrder.addItem(orderItem);
				CustomerOrder.subtotal = subtotal;
				refreshOrderTxtArea();

				add.clear();
				sub.clear();				
				ingredientsFM.clear();
				totalIngredientsFM.clear();
				ingredientsQuantityFM.clear();
				totalIngredientsQuantityFM.clear();
				specialReqs.setText("none");
				qtyCBox.setSelectedIndex(0);

				OrderTypeFrame.setVisible(true);
				OrderListFrame.setVisible(true);
				MenuListFrame.setVisible(true);
				OrderDetails.setVisible(false);
				ing_page_number = 0;
			}	
		});		

		next_ingredient_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ing_page_number++;

				int index2 = 0;

				for (JButton b : add)
					b.setVisible(false);

				for (JButton b : sub)
					b.setVisible(false);

				for (JTextField tf : ingredientsFM)
					tf.setVisible(false);

				for (JTextField tf : ingredientsQuantityFM)
					tf.setVisible(false);

				add.clear();
				sub.clear();
				ingredientsFM.clear();
				ingredientsQuantityFM.clear();

				List<String> ingredients = new ArrayList<String>();
				List<String> ingredient_quantity = new ArrayList<String>();

				String[] ings = item.ingredients.replaceAll(",", ":").split(":");
				int i = 0;

				for (String ing : totalIngredientsFM)
					ingredients.add(ing);

				for (String ing : totalIngredientsQuantityFM)
					ingredient_quantity.add(ing);

				index = 0;

				for (String ing : ingredients)
				{					
					if ((index < (5 * (ing_page_number + 1)) && (index >= (5 * ing_page_number))))
					{
						JTextField tf1 = new JTextField();

						tf1.setText(ing);
						tf1.setEnabled(true);
						tf1.setVisible(false);
						tf1.setEditable(false);
						tf1.setFont(new Font("Tahoma", Font.PLAIN, 15));
						tf1.setBounds(93, (50 * (index2 + 1)) + (index2 * 5), 250, 50);

						OrderSpecificsFrame.add(tf1);
						ingredientsFM.add(tf1);
						index2++;
					}
					index++;
				}

				// COME BACK HERE
				if (index > (5 * (ing_page_number + 1)))
					next_ingredient_button.setVisible(true);

				else
					next_ingredient_button.setVisible(false);

				if (ing_page_number < 1)
					previous_ingredient_button.setVisible(false);

				index = 0;
				index2 = 0;

				for (String ing : ingredient_quantity)
				{							
					if ((index < (5 * (ing_page_number + 1)) && (index >= (5 * ing_page_number))))
					{
						JButton b1 = new JButton();
						JButton b2 = new JButton();
						JTextField tf2 = new JTextField();

						b1.setText("+");
						b2.setText("-");
						tf2.setText(ing);

						b1.setEnabled(true);
						b2.setEnabled(true);
						tf2.setEnabled(true);

						tf2.setEditable(false);

						b1.setVisible(false);
						b2.setVisible(false);
						tf2.setVisible(false);

						b1.setFont(new Font("Tahoma", Font.PLAIN, 30));
						b2.setFont(new Font("Tahoma", Font.PLAIN, 30));
						tf2.setFont(new Font("Tahoma", Font.PLAIN, 20));

						b1.setBounds(360, (50 * (index2 + 1)) + (index2 * 5), 50, 50);
						b2.setBounds(420, (50 * (index2 + 1)) + (index2 * 5), 50, 50);
						tf2.setBounds(22, (50 * (index2 + 1)) + (index2 * 5), 50, 50);

						b1.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								int text = Integer.valueOf(tf2.getText());
								tf2.setText(String.valueOf(text + 1));

								if (b1.getY() == 50)
									totalIngredientsQuantityFM.set((ing_page_number * 5) + 0, tf2.getText());
								else if (b1.getY() == 105)
									totalIngredientsQuantityFM.set((ing_page_number * 5) + 1, tf2.getText());
								else if (b1.getY() == 160)
									totalIngredientsQuantityFM.set((ing_page_number * 5) + 2, tf2.getText());
								else if (b1.getY() == 215)
									totalIngredientsQuantityFM.set((ing_page_number * 5) + 3, tf2.getText());
								else if (b1.getY() == 270)
									totalIngredientsQuantityFM.set((ing_page_number * 5) + 4, tf2.getText());
							}
						});	

						b2.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								int text = Integer.valueOf(tf2.getText());

								if (text > 0)
								{
									tf2.setText(String.valueOf(text - 1));

									if (b1.getY() == 50)
										totalIngredientsQuantityFM.set((ing_page_number * 5) + 0, tf2.getText());
									else if (b1.getY() == 105)
										totalIngredientsQuantityFM.set((ing_page_number * 5) + 1, tf2.getText());
									else if (b1.getY() == 160)
										totalIngredientsQuantityFM.set((ing_page_number * 5) + 2, tf2.getText());
									else if (b1.getY() == 215)
										totalIngredientsQuantityFM.set((ing_page_number * 5) + 3, tf2.getText());
									else if (b1.getY() == 270)
										totalIngredientsQuantityFM.set((ing_page_number * 5) + 4, tf2.getText());
								}
							}	
						});	

						OrderSpecificsFrame.add(b1);
						OrderSpecificsFrame.add(b2);
						OrderSpecificsFrame.add(tf2);

						add.add(b1);
						sub.add(b2);
						ingredientsQuantityFM.add(tf2);

						index2++;
					}

					index++;
				}

				for (JButton b1 : add)
					b1.setVisible(true);

				for (JButton b2 : sub)
					b2.setVisible(true);

				for (JTextField tf1 : ingredientsFM)
					tf1.setVisible(true);

				for (JTextField tf2 : ingredientsQuantityFM)
					tf2.setVisible(true);


				previous_ingredient_button.setVisible(true);
			}
		});

		previous_ingredient_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ing_page_number--;

				int index2 = 0;

				for (JButton b : add)
					b.setVisible(false);

				for (JButton b : sub)
					b.setVisible(false);

				for (JTextField tf : ingredientsFM)
					tf.setVisible(false);

				for (JTextField tf : ingredientsQuantityFM)
					tf.setVisible(false);

				add.clear();
				sub.clear();
				ingredientsFM.clear();
				ingredientsQuantityFM.clear();

				List<String> ingredients = new ArrayList<String>();
				List<String> ingredient_quantity = new ArrayList<String>();

				String[] ings = item.ingredients.replaceAll(",", ":").split(":");
				int i = 0;

				for (String ing : totalIngredientsFM)
					ingredients.add(ing);

				for (String ing : totalIngredientsQuantityFM)
					ingredient_quantity.add(ing);

				index = 0;

				for (String ing : ingredients)
				{					
					if ((index < (5 * (ing_page_number + 1)) && (index >= (5 * ing_page_number))))
					{
						JTextField tf1 = new JTextField();

						tf1.setText(ing);
						tf1.setEnabled(true);
						tf1.setVisible(false);
						tf1.setEditable(false);
						tf1.setFont(new Font("Tahoma", Font.PLAIN, 15));
						tf1.setBounds(93, (50 * (index2 + 1)) + (index2 * 5), 250, 50);

						OrderSpecificsFrame.add(tf1);
						ingredientsFM.add(tf1);
						index2++;
					}
					index++;
				}

				if (index >= (5 * (ing_page_number + 1)))
					next_ingredient_button.setVisible(true);

				else
					next_ingredient_button.setVisible(false);

				if (ing_page_number < 1)
					previous_ingredient_button.setVisible(false);

				index = 0;
				index2 = 0;

				for (String ing : ingredient_quantity)
				{							
					if ((index < (5 * (ing_page_number + 1)) && (index >= (5 * ing_page_number))))
					{
						JButton b1 = new JButton();
						JButton b2 = new JButton();
						JTextField tf2 = new JTextField();

						b1.setText("+");
						b2.setText("-");
						tf2.setText(ing);

						b1.setEnabled(true);
						b2.setEnabled(true);
						tf2.setEnabled(true);

						tf2.setEditable(false);

						b1.setVisible(false);
						b2.setVisible(false);
						tf2.setVisible(false);

						b1.setFont(new Font("Tahoma", Font.PLAIN, 30));
						b2.setFont(new Font("Tahoma", Font.PLAIN, 30));
						tf2.setFont(new Font("Tahoma", Font.PLAIN, 20));

						b1.setBounds(360, (50 * (index2 + 1)) + (index2 * 5), 50, 50);
						b2.setBounds(420, (50 * (index2 + 1)) + (index2 * 5), 50, 50);
						tf2.setBounds(22, (50 * (index2 + 1)) + (index2 * 5), 50, 50);

						b1.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								int text = Integer.valueOf(tf2.getText());
								tf2.setText(String.valueOf(text + 1));

								if (b1.getY() == 50)
									totalIngredientsQuantityFM.set((ing_page_number * 5) + 0, tf2.getText());
								else if (b1.getY() == 105)
									totalIngredientsQuantityFM.set((ing_page_number * 5) + 1, tf2.getText());
								else if (b1.getY() == 160)
									totalIngredientsQuantityFM.set((ing_page_number * 5) + 2, tf2.getText());
								else if (b1.getY() == 215)
									totalIngredientsQuantityFM.set((ing_page_number * 5) + 3, tf2.getText());
								else if (b1.getY() == 270)
									totalIngredientsQuantityFM.set((ing_page_number * 5) + 4, tf2.getText());
							}
						});	

						b2.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								int text = Integer.valueOf(tf2.getText());

								if (text > 0)
								{
									tf2.setText(String.valueOf(text - 1));

									if (b1.getY() == 50)
										totalIngredientsQuantityFM.set((ing_page_number * 5) + 0, tf2.getText());
									else if (b1.getY() == 105)
										totalIngredientsQuantityFM.set((ing_page_number * 5) + 1, tf2.getText());
									else if (b1.getY() == 160)
										totalIngredientsQuantityFM.set((ing_page_number * 5) + 2, tf2.getText());
									else if (b1.getY() == 215)
										totalIngredientsQuantityFM.set((ing_page_number * 5) + 3, tf2.getText());
									else if (b1.getY() == 270)
										totalIngredientsQuantityFM.set((ing_page_number * 5) + 4, tf2.getText());
								}
							}	
						});	

						OrderSpecificsFrame.add(b1);
						OrderSpecificsFrame.add(b2);
						OrderSpecificsFrame.add(tf2);

						add.add(b1);
						sub.add(b2);
						ingredientsQuantityFM.add(tf2);

						index2++;
					}

					index++;
				}

				for (JButton b1 : add)
					b1.setVisible(true);

				for (JButton b2 : sub)
					b2.setVisible(true);

				for (JTextField tf1 : ingredientsFM)
					tf1.setVisible(true);

				for (JTextField tf2 : ingredientsQuantityFM)
					tf2.setVisible(true);

			}
		});
	}

	public void getMenuItems() {
		Client.session.getPacketEncoder().requestMenu();
		try {
			PacketDecoder.menuRequestFix = 0;
			Thread.sleep(7);
			Client.session.getPacketEncoder().requestMenu();
			PacketDecoder.menuRequestFix = 0;
			Thread.sleep(7);
			Client.session.getPacketEncoder().requestMenu();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Display a specific items details
	 */
	public void seeMenuDetails(MItem mItem, JPanel OrderSpecificsFrame)
	{
		int index2 = 0;

		for (JButton b : add)
			b.setVisible(false);

		for (JButton b : sub)
			b.setVisible(false);

		for (JButton b : ingredientSubButton)
			b.setVisible(false);

		for (JTextField tf : ingredientsFM)
			tf.setVisible(false);

		for (JTextField tf : ingredientsQuantityFM)
			tf.setVisible(false);

		add.clear();
		sub.clear();
		ingredientSubButton.clear();
		ingredientsFM.clear();
		totalIngredientsFM.clear();
		ingredientsQuantityFM.clear();
		totalIngredientsQuantityFM.clear();

		List<String> ingredients = new ArrayList<String>();
		List<String> ingredient_quantity = new ArrayList<String>();
		List<String> editable = new ArrayList<String>();
		List<String> replaceables = new ArrayList<String>();

		String[] ings = mItem.ingredients.replaceAll(",", ":").split(":");

		mItem.ingredients = mItem.ingredients.replace("]", "").replace("[", "");
		String[] ingTok = mItem.ingredients.split(",");
		List<String> indIngs = new ArrayList<String>();
		for(String ing : ingTok) {
			indIngs.add(ing);
		}

		int i = 0;

		for (String ing : ings)
		{
			if (i % 4 == 0)
			{
				i++;
				ingredients.add(ing);
			}
			else if (i % 4 == 1)
			{
				i++;
				ingredient_quantity.add(ing);
			}
			else if (i % 4 == 2)
			{
				i++;
				editable.add(ing);
			}
			else
			{
				i++;
				replaceables.add(ing);
			}		
		}

		//ingredients.set(0, (ingredients.get(0).substring(1, ingredients.get(0).length())));

		index = 0;

		for (String ing : ingredients)
		{
			totalIngredientsFM.add(ing);

			if ((index < (5 * (ing_page_number + 1)) && (index >= (5 * ing_page_number))) && (editable.get(index).equals("t")))
			{
				JTextField tf1 = new JTextField();

				tf1.setText(ing);
				tf1.setEnabled(true);
				tf1.setVisible(false);
				tf1.setEditable(false);
				tf1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				tf1.setBounds(93, (50 * (index2 + 1)) + (index2 * 5), 250, 50);

				OrderSpecificsFrame.add(tf1);
				ingredientsFM.add(tf1);
				index2++;
			}
			index++;
		}

		if (index >= (5 * (ing_page_number + 1)))
			next_ingredient_button.setVisible(true);

		else
			next_ingredient_button.setVisible(false);

		if (ing_page_number < 1)
			previous_ingredient_button.setVisible(false);

		index = 0;
		index2 = 0;

		for (String ing : ingredient_quantity)
		{		
			totalIngredientsQuantityFM.add(ing);

			if ((index < (5 * (ing_page_number + 1)) && (index >= (5 * ing_page_number))) && (editable.get(index).equals("t")))
			{
				JButton b1 = new JButton();
				JButton b2 = new JButton();
				JTextField tf2 = new JTextField();

				JButton subB = new JButton();

				String[] splitToks = indIngs.get(index).split(":");
				String ingName = splitToks[0];
				int qty = Integer.parseInt(splitToks[1]);
				boolean ingEditable = splitToks[2].equals("t");
				String ingSub = splitToks[3];

				b1.setText("+");
				b2.setText("-");
				tf2.setText(ing);

				b1.setEnabled(true);
				b2.setEnabled(true);
				tf2.setEnabled(true);

				tf2.setEditable(false);

				b1.setVisible(false);
				b2.setVisible(false);
				tf2.setVisible(false);

				b1.setFont(new Font("Tahoma", Font.PLAIN, 30));
				b2.setFont(new Font("Tahoma", Font.PLAIN, 30));
				tf2.setFont(new Font("Tahoma", Font.PLAIN, 20));

				// Adds a substitution button
				subB.setFont(new Font("Tahoma", Font.PLAIN, 18));
				subB.setText("Sub");
				subB.setBounds(43, (50 * (index2 + 1)) + (index2 * 5), 40, 50);
				subB.setVisible(false);
				ingredientSubButton.add(subB);

				if(!ingSub.equals("n")) {
					subB.setVisible(true);
				}
				else {
					subB.setVisible(false);
				}


				b1.setBounds(360, (50 * (index2 + 1)) + (index2 * 5), 50, 50);
				b2.setBounds(420, (50 * (index2 + 1)) + (index2 * 5), 50, 50);
				tf2.setBounds(0, (50 * (index2 + 1)) + (index2 * 5), 40, 50);


				b1.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						int text = Integer.valueOf(tf2.getText());
						tf2.setText(String.valueOf(text + 1));
						if (b1.getY() == 50)
							totalIngredientsQuantityFM.set((ing_page_number * 5) + 0, tf2.getText());
						else if (b1.getY() == 105)
							totalIngredientsQuantityFM.set((ing_page_number * 5) + 1, tf2.getText());
						else if (b1.getY() == 160)
							totalIngredientsQuantityFM.set((ing_page_number * 5) + 2, tf2.getText());
						else if (b1.getY() == 215)
							totalIngredientsQuantityFM.set((ing_page_number * 5) + 3, tf2.getText());
						else if (b1.getY() == 270)
							totalIngredientsQuantityFM.set((ing_page_number * 5) + 4, tf2.getText());
					}
				});	

				b2.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						int text = Integer.valueOf(tf2.getText());

						if (text > 0)
						{
							tf2.setText(String.valueOf(text - 1));

							if (b1.getY() == 50)
								totalIngredientsQuantityFM.set((ing_page_number * 5) + 0, tf2.getText());
							else if (b1.getY() == 105)
								totalIngredientsQuantityFM.set((ing_page_number * 5) + 1, tf2.getText());
							else if (b1.getY() == 160)
								totalIngredientsQuantityFM.set((ing_page_number * 5) + 2, tf2.getText());
							else if (b1.getY() == 215)
								totalIngredientsQuantityFM.set((ing_page_number * 5) + 3, tf2.getText());
							else if (b1.getY() == 270)
								totalIngredientsQuantityFM.set((ing_page_number * 5) + 4, tf2.getText());
						}
					}	
				});	

				final int fInd2 = index2;
				subB.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JPopupMenu menu = new JPopupMenu();
						JMenuItem[] options = { 
								new JMenuItem(ingName), 
								new JMenuItem(ingSub)
						};
						for (JMenuItem i : options)
							menu.add(i);
						options[0].addActionListener(actionEvent -> {
							ingredientsFM.get(fInd2).setText(options[0].getText());
						});
						options[1].addActionListener(actionEvent -> {
							ingredientsFM.get(fInd2).setText(options[1].getText());
						});
						menu.show(ingredientSubButton.get(fInd2), 
								ingredientSubButton.get(fInd2).getX() - 40, subB.getY() + 
								(fInd2 > 0 ? ((subB.getHeight() * -1) * fInd2) - (5 * fInd2) : 0));
					}
				});

				OrderSpecificsFrame.add(b1);
				OrderSpecificsFrame.add(b2);
				OrderSpecificsFrame.add(tf2);

				// adds sub button
				OrderSpecificsFrame.add(subB);

				add.add(b1);
				sub.add(b2);
				ingredientsQuantityFM.add(tf2);

				index2++;
			}

			index++;
		}

		for (JButton b1 : add)
			b1.setVisible(true);

		for (JButton b2 : sub)
			b2.setVisible(true);

		for (JTextField tf1 : ingredientsFM)
			tf1.setVisible(true);

		for (JTextField tf2 : ingredientsQuantityFM)
			tf2.setVisible(true);

	}


	/**
	 * Shan - Refreshes order txt area.
	 */
	public void refreshOrderTxtArea() {
		StringBuilder s = new StringBuilder();
		s.append("Order:\n\n");

		subtotal = 0;
		// Checks for weekly specials, free beverage or kids eat free.
		if(ClientSession.freeDrinkWPur) {
			for(int i = 0; i < CustomerOrder.items.size(); i++) {
				if(ClientSession.receivedSpecialNoti && 
						CustomerOrder.items.get(i).menuType.equals("drink") && CustomerOrder.items.size() > 1) {
					subtotal -= CustomerOrder.items.get(i).price;
					promoIndex = i;
					ClientSession.freeDrinkWPur = false;
				}
			}
		}
		else if(ClientSession.kidsEatFree) {
			for(int i = 0; i < CustomerOrder.items.size(); i++) {
				if(ClientSession.receivedSpecialNoti && 
						CustomerOrder.items.get(i).name.toLowerCase().contains("kid") && CustomerOrder.items.size() > 1) {
					subtotal -= CustomerOrder.items.get(i).price;
					promoIndex = i;
					ClientSession.kidsEatFree = false;
				}
			}
		}
		
		int mIIndex = 0;
		for(MItem i : CustomerOrder.items) {
			s.append("x"+i.qty+" "+i.name+" - "+
					(decimalF(i.price * i.qty))+"\n");
			
			if(promoIndex == mIIndex) {
				s.append("    - Weekly Specials Promo -"+decimalF(i.price)+"\n");
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

			subtotal += (i.price * i.qty);
			
			mIIndex++;
		}
		orderSummary.setText(s.toString());
		orderTotal.setText("Subtotal: "+decimalF(subtotal)+"\nTax: "+
			decimalF(tax * subtotal)+"\nTotal: "+decimalF(subtotal + (subtotal * tax)));
		CustomerOrder.subtotal = subtotal;
		
		Client.clientFrame.customerSP.pay2.refreshTxtAreas(promoIndex);
	}

	/**
	 * Formats a decimal to be displayed as currency.
	 * @param num
	 * @return
	 */
	public String decimalF(double num) {
		return DecimalFormat.getCurrencyInstance().format(num);
	}

	/**
	 * Returns the customer to the front page
	 * Used for if the menu needs to be updated again..
	 */
	public void goToFront() {
		for(JButton b : sidesFM) 
			b.setVisible(false);

		for(JButton b : dessertsFM) 
			b.setVisible(true);

		for(JButton b : drinksFM) 
			b.setVisible(false);

		for (JButton b : entreesFM)
			b.setVisible(false);

		entree_button.setSelected(false);
		side_button.setSelected(false);
		drink_button.setSelected(false);
		dessert_button.setSelected(false);
		next_page_button.setVisible(false);
		previous_page_button.setVisible(false);
	}

}
