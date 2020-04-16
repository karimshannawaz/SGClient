/*
 * Dillon McPherson 
 * 
 * creates the GUI for the order screen
*/

package client.order;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

// Test by dillon


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import client.Client;
import client.network.packet.PacketDecoder;
import client.utils.JFrameUtils;

import javax.swing.SwingConstants;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = -1338118268945423515L;
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
	private List<JTextField> ingredientsFM = new ArrayList<JTextField>();
	private List<JTextField> ingredientsQuantityFM = new ArrayList<JTextField>();
	private List<String> totalIngredientsFM = new ArrayList<String>();
	private List<String> totalIngredientsQuantityFM = new ArrayList<String>();

	public JPanel MenuListFrame;
	private JTextField item_allergens_textfield;
	private JTextField vegan_textfield;

	private JButton next_ingredient_button = new JButton("Next page");
	private JButton previous_ingredient_button = new JButton("previous page");

	
	/**
	 * Create the panel.
	 */
	public MenuPanel() {
		super();
		setBounds(0, 0, 1039, 522);
		setLayout(null);
		
		//creates a frame to hold the menu items
		MenuListFrame = new JPanel();
		MenuListFrame.setBorder(null);
		MenuListFrame.setBounds(347, 0, 347, 522);
		add(MenuListFrame);
		MenuListFrame.setLayout(null);

		//creates a button to display 7 new menu items
		JButton next_page_button = new JButton("Next page");
		next_page_button.setVisible(false);
		next_page_button.setBounds(174, 490, 173, 32);
		MenuListFrame.add(next_page_button);

		//creates a button to display 7 previous menu items
		JButton previous_page_button = new JButton("Previous page");
		previous_page_button.setVisible(false);
		previous_page_button.setBounds(0, 490, 173, 32);
		MenuListFrame.add(previous_page_button);
		
		//creates a frame to hold the order summary
		JPanel OrderListFrame = new JPanel();
		OrderListFrame.setBounds(693, 0, 346, 522);
		add(OrderListFrame);
		OrderListFrame.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 346, 441);
		OrderListFrame.add(scrollPane);
		
		//textfield that holds the order summary
		JTextArea order_textfield = new JTextArea();
		order_textfield.setFont(new Font("Monospaced", Font.PLAIN, 15));
		order_textfield.setEditable(false);
		order_textfield.setLineWrap(true);
		order_textfield.setText("Order: ");
		scrollPane.setViewportView(order_textfield);
		

		JButton place_order_button = new JButton("Place Order");
		place_order_button.setFont(new Font("Tahoma", Font.PLAIN, 25));
		place_order_button.setBounds(0, 440, 346, 82);
		OrderListFrame.add(place_order_button);
		
		JPanel OrderDetails = new JPanel();
		OrderDetails.setVisible(false);
		OrderDetails.setBounds(0, 0, 1039, 520);
		add(OrderDetails);
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
				
		JButton cancel_button = new JButton("Cancel");
		cancel_button.setBounds(93, 375, 125, 125);
		OrderSpecificsFrame.add(cancel_button);
		
		JButton confirm_button = new JButton("Confirm");
		confirm_button.setBounds(218, 375, 125, 125);
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
		add(OrderTypeFrame);
		OrderTypeFrame.setLayout(null);
		
		JToggleButton dessert_button = new JToggleButton("Desserts");
		dessert_button.setBounds(0, 390, 346, 130);
		dessert_button.setFont(new Font("Tahoma", Font.PLAIN, 45));
		OrderTypeFrame.add(dessert_button);
		
		JToggleButton entree_button = new JToggleButton("Entrees");
		entree_button.setBounds(0, 0, 346, 130);
		entree_button.setFont(new Font("Tahoma", Font.PLAIN, 45));
		OrderTypeFrame.add(entree_button);
		
		JToggleButton side_button = new JToggleButton("Sides");
		side_button.setBounds(0, 130, 346, 130);
		side_button.setFont(new Font("Tahoma", Font.PLAIN, 45));
		OrderTypeFrame.add(side_button);
		
		JToggleButton drink_button = new JToggleButton("Drinks");
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
					
					Client.clientFrame.customerSP.orderPanel.entreesFM.clear();
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
							Client.clientFrame.customerSP.orderPanel.MenuListFrame.add(b);
							Client.clientFrame.customerSP.orderPanel.entreesFM.add(b);
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
					
					Client.clientFrame.customerSP.orderPanel.sidesFM.clear();
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
							Client.clientFrame.customerSP.orderPanel.MenuListFrame.add(b);
							Client.clientFrame.customerSP.orderPanel.sidesFM.add(b);
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
					
					Client.clientFrame.customerSP.orderPanel.drinksFM.clear();
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
							Client.clientFrame.customerSP.orderPanel.MenuListFrame.add(b);
							Client.clientFrame.customerSP.orderPanel.drinksFM.add(b);
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
					
					Client.clientFrame.customerSP.orderPanel.dessertsFM.clear();
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
							Client.clientFrame.customerSP.orderPanel.MenuListFrame.add(b);
							Client.clientFrame.customerSP.orderPanel.dessertsFM.add(b);
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
				
					Client.clientFrame.customerSP.orderPanel.entreesFM.clear();

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
							Client.clientFrame.customerSP.orderPanel.MenuListFrame.add(b);
							Client.clientFrame.customerSP.orderPanel.entreesFM.add(b);
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
				
					Client.clientFrame.customerSP.orderPanel.sidesFM.clear();

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
							Client.clientFrame.customerSP.orderPanel.MenuListFrame.add(b);
							Client.clientFrame.customerSP.orderPanel.sidesFM.add(b);
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
				
					Client.clientFrame.customerSP.orderPanel.drinksFM.clear();

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
							Client.clientFrame.customerSP.orderPanel.MenuListFrame.add(b);
							Client.clientFrame.customerSP.orderPanel.drinksFM.add(b);
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
				
					Client.clientFrame.customerSP.orderPanel.dessertsFM.clear();

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
							Client.clientFrame.customerSP.orderPanel.MenuListFrame.add(b);
							Client.clientFrame.customerSP.orderPanel.dessertsFM.add(b);
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
				
					Client.clientFrame.customerSP.orderPanel.entreesFM.clear();

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
							Client.clientFrame.customerSP.orderPanel.MenuListFrame.add(b);
							Client.clientFrame.customerSP.orderPanel.entreesFM.add(b);
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
				
					Client.clientFrame.customerSP.orderPanel.sidesFM.clear();

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
							Client.clientFrame.customerSP.orderPanel.MenuListFrame.add(b);
							Client.clientFrame.customerSP.orderPanel.sidesFM.add(b);
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
				
					Client.clientFrame.customerSP.orderPanel.drinksFM.clear();

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
							Client.clientFrame.customerSP.orderPanel.MenuListFrame.add(b);
							Client.clientFrame.customerSP.orderPanel.drinksFM.add(b);
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
				
					Client.clientFrame.customerSP.orderPanel.dessertsFM.clear();

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
							Client.clientFrame.customerSP.orderPanel.MenuListFrame.add(b);
							Client.clientFrame.customerSP.orderPanel.dessertsFM.add(b);
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

		place_order_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				boolean confirmOrder = JFrameUtils.confirmDialog("Your Order",
						"Are you sure you would like to place this order? You cannot make changes"
								+ " once it has been placed.");

				// Object input = JFrameUtils.inputDialog("Order Price", "Choose what you want to pay for this order:");
				if (!confirmOrder)
				{
					order_textfield.setText("Order:");
					return;
				}
				else
				{
					System.out.println("CODE HERE TO AND SEND INFO TO KITCHEN / SERVER ");
				}
			}
		});

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
				public void actionPerformed(ActionEvent e) {
					for (JButton b : add)
						b.setVisible(false);
					
					for (JButton b : sub)
						b.setVisible(false);
					
					for (JTextField tf : ingredientsFM)
						tf.setVisible(false);
					
					for (JTextField tf : ingredientsQuantityFM)
						tf.setVisible(false);
					
					String text = order_textfield.getText();
					order_textfield.setText(text+"\n   "+item_price_textfield.getText()+" - "+item_name_textfield.getText());
					
					
					List<String> ingredientsQ = new ArrayList<String>();

					String[] ings = item.ingredients.replaceAll(",", ":").split(":");
					int i = 0;
					
					for (String ing : ings)
					{
						if (i % 4 == 1)
							ingredientsQ.add(ing);

						i++;
					}

					for (int j = 0; j < ingredientsQ.size(); j++)
					{
						if (!ingredientsQ.get(j).equals(totalIngredientsQuantityFM.get(j)))
						{
							text = order_textfield.getText();
							order_textfield.setText(text+"\n      "+totalIngredientsQuantityFM.get(j)+"-"+totalIngredientsFM.get(j));
						}
					}
					
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
	
	public void seeMenuDetails(MItem mItem, JPanel OrderSpecificsFrame)
	{
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
		totalIngredientsFM.clear();
		ingredientsQuantityFM.clear();
		totalIngredientsQuantityFM.clear();
	
		List<String> ingredients = new ArrayList<String>();
		List<String> ingredient_quantity = new ArrayList<String>();
		List<String> editable = new ArrayList<String>();
		List<String> replaceables = new ArrayList<String>();
		
		String[] ings = mItem.ingredients.replaceAll(",", ":").split(":");
		
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
		
		ingredients.set(0, (ingredients.get(0).substring(1, ingredients.get(0).length())));
		
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
}
