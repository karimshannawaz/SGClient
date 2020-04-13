/*
 * Dillon McPherson 
 * 
 * creates the GUI for the order screen
*/

package client.order;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

// Test by dillon


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import client.Client;
import client.network.packet.PacketDecoder;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = -1338118268945423515L;
	private JTextField txtpictureOfHamburger;
	private JTextField item_name_textfield;
	private JTextField ingredient_1;
	private JTextField ingredient_2;
	private JTextField ingredient_3;
	private JTextField ingredient_4;
	private JTextField ingredien_5;
	
	public List<JButton> entreesFM = new ArrayList<JButton>();
	public List<JButton> sidesFM = new ArrayList<JButton>();
	public List<JButton> drinksFM = new ArrayList<JButton>();
	public List<JButton> dessertsFM = new ArrayList<JButton>();
	
	public JPanel MenuListFrame;

	/**
	 * Create the panel.
	 */
	public MenuPanel() {
		super();
		setBounds(0, 0, 1039, 522);
		setLayout(null);

		MItem item = new MItem();
		
		//creates a frame to hold the menu items
		MenuListFrame = new JPanel();
		MenuListFrame.setBorder(null);
		MenuListFrame.setBounds(347, 0, 347, 522);
		add(MenuListFrame);
		MenuListFrame.setLayout(null);
		
		//creates a menu item button
		JButton item_1 = new JButton("");
		item_1.setEnabled(true);
		item_1.setVisible(false);
		item_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		item_1.setBounds(0, 0, 347, 70);
		MenuListFrame.add(item_1);

		//creates a menu item button
		JButton item_2 = new JButton("");
		item_2.setVisible(false);
		item_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		item_2.setBounds(0, 70, 347, 70);
		MenuListFrame.add(item_2);

		//creates a menu item button
		JButton item_3 = new JButton("");
		item_3.setVisible(false);
		item_3.setFont(new Font("Tahoma", Font.PLAIN, 30));
		item_3.setBounds(0, 140, 347, 70);
		MenuListFrame.add(item_3);

		//creates a menu item button
		JButton item_4 = new JButton("");
		item_4.setVisible(false);
		item_4.setFont(new Font("Tahoma", Font.PLAIN, 30));
		item_4.setBounds(0, 210, 347, 70);
		MenuListFrame.add(item_4);

		//creates a menu item button
		JButton item_5 = new JButton("");
		item_5.setVisible(false);
		item_5.setFont(new Font("Tahoma", Font.PLAIN, 30));
		item_5.setBounds(0, 280, 347, 70);
		MenuListFrame.add(item_5);

		//creates a menu item button
		JButton item_6 = new JButton("");
		item_6.setVisible(false);
		item_6.setFont(new Font("Tahoma", Font.PLAIN, 30));
		item_6.setBounds(0, 350, 347, 70);
		MenuListFrame.add(item_6);

		//creates a menu item button
		JButton item_7 = new JButton("");
		item_7.setVisible(false);
		item_7.setFont(new Font("Tahoma", Font.PLAIN, 30));
		item_7.setBounds(0, 420, 347, 70);
		MenuListFrame.add(item_7);

		//creates a button to display 7 new menu items
		JButton next_page_button = new JButton("Next page");
		next_page_button.setBounds(174, 490, 173, 32);
		MenuListFrame.add(next_page_button);

		//creates a button to display 7 previous menu items
		JButton previous_page_button = new JButton("Previous page");
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
		place_order_button.setFont(new Font("Tahoma", Font.PLAIN, 35));
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
		
		txtpictureOfHamburger = new JTextField();
		txtpictureOfHamburger.setText("*Picture of hamburger*");
		txtpictureOfHamburger.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpictureOfHamburger.setBounds(137, 132, 207, 131);
		PictureFrame.add(txtpictureOfHamburger);
		txtpictureOfHamburger.setColumns(10);
		
		item_name_textfield = new JTextField();
		item_name_textfield.setEditable(false);
		item_name_textfield.setBounds(137, 276, 207, 43);
		PictureFrame.add(item_name_textfield);
		item_name_textfield.setColumns(10);
		
		JTextArea item_description_textfield = new JTextArea();
		item_description_textfield.setEditable(false);
		item_description_textfield.setText("It's a hamburger. What did you expect?");
		item_description_textfield.setBounds(88, 337, 345, 103);
		PictureFrame.add(item_description_textfield);
		
		JPanel OrderSpecificsFrame = new JPanel();
		OrderSpecificsFrame.setBounds(519, 0, 518, 520);
		OrderDetails.add(OrderSpecificsFrame);
		OrderSpecificsFrame.setLayout(null);
		
		JButton cancel_button = new JButton("Cancel");
		cancel_button.setBounds(50, 350, 125, 125);
		OrderSpecificsFrame.add(cancel_button);
		
		JButton confirm_button = new JButton("Confirm");
		confirm_button.setBounds(344, 350, 125, 125);
		OrderSpecificsFrame.add(confirm_button);
		
		ingredient_1 = new JTextField();
		ingredient_1.setEnabled(true);
		ingredient_1.setEditable(false);
		ingredient_1.setBounds(93, 50, 250, 50);
		OrderSpecificsFrame.add(ingredient_1);
		ingredient_1.setColumns(10);
		
		ingredient_2 = new JTextField();
		ingredient_2.setEnabled(true);
		ingredient_2.setEditable(false);
		ingredient_2.setColumns(10);
		ingredient_2.setBounds(93, 105, 250, 50);
		OrderSpecificsFrame.add(ingredient_2);
		
		ingredient_3 = new JTextField();
		ingredient_3.setEnabled(true);
		ingredient_3.setEditable(false);
		ingredient_3.setColumns(10);
		ingredient_3.setBounds(93, 160, 250, 50);
		OrderSpecificsFrame.add(ingredient_3);
		
		ingredient_4 = new JTextField();
		ingredient_4.setEnabled(true);
		ingredient_4.setEditable(false);
		ingredient_4.setColumns(10);
		ingredient_4.setBounds(93, 215, 250, 50);
		OrderSpecificsFrame.add(ingredient_4);
		
		JButton next_ingredient_button = new JButton("Next page");
		next_ingredient_button.setBounds(218, 325, 125, 25);
		OrderSpecificsFrame.add(next_ingredient_button);
		
		JButton previous_ingredient_ = new JButton("Previous page");
		previous_ingredient_.setBounds(93, 325, 125, 25);
		OrderSpecificsFrame.add(previous_ingredient_);
		
		ingredien_5 = new JTextField();
		ingredien_5.setEnabled(true);
		ingredien_5.setEditable(false);
		ingredien_5.setColumns(10);
		ingredien_5.setBounds(93, 270, 250, 50);
		OrderSpecificsFrame.add(ingredien_5);
		
		JButton add_ing1_button = new JButton("+");
		add_ing1_button.setFont(new Font("Tahoma", Font.PLAIN, 30));
		add_ing1_button.setBounds(360, 50, 50, 50);
		OrderSpecificsFrame.add(add_ing1_button);
		
		JButton sub_ing1_button = new JButton("+");
		sub_ing1_button.setFont(new Font("Tahoma", Font.PLAIN, 30));
		sub_ing1_button.setBounds(420, 50, 50, 50);
		OrderSpecificsFrame.add(sub_ing1_button);
		
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
		
		//displays first 7 entrees when entree button is clicked
		entree_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (entree_button.isSelected())
				{
					getMenuItems();
					try {
						Thread.sleep(50);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Client.clientFrame.panel.orderPanel.entreesFM.clear();
					int buttonIndex = 0;
					for(MItem mItem : Menu.instance.values()) {
						System.out.println("Mitem is "+mItem.name);
						if(mItem.menuType.equals("entree")) {
							JButton b = new JButton();
							b.setText(mItem.name);
							b.setEnabled(true);
							b.setVisible(false);
							b.setFont(new Font("Tahoma", Font.PLAIN, 30));
							b.setBounds(0, 70 * buttonIndex, 347, 70);
							Client.clientFrame.panel.orderPanel.MenuListFrame.add(b);
							Client.clientFrame.panel.orderPanel.entreesFM.add(b);
							buttonIndex++;
							System.out.println("Button worked");
						}
					}
					for(JButton b : entreesFM) {
						b.setVisible(true);
					}
					side_button.setSelected(false);
					drink_button.setSelected(false);
					dessert_button.setSelected(false);
					item_5.setVisible(false);
					item_6.setVisible(false);
					item_7.setVisible(false);
					item_1.setVisible(false);
					item_2.setVisible(false);
					item_3.setVisible(false);
					/*
					item_1.setVisible(true);
					item_2.setVisible(true);
					item_3.setVisible(true);
					item_4.setVisible(true);
					item_5.setVisible(false);
					item_6.setVisible(false);
					item_7.setVisible(false);
					
					item_1.setText("Juicy Lucy Burger");
					item_2.setText("Cheeseburger");
					item_3.setText("Seven Wonders Burger");
					item_4.setText("Kid's burger");
					side_button.setSelected(false);
					drink_button.setSelected(false);
					dessert_button.setSelected(false);
					*/
				}
				else
				{
					for(JButton b : entreesFM) {
						b.setVisible(false);
					}
					item_1.setVisible(false);
					item_2.setVisible(false);
					item_3.setVisible(false);
					item_4.setVisible(false);
					item_5.setVisible(false);
					item_6.setVisible(false);
					item_7.setVisible(false);
					entree_button.setSelected(false);
				}
			}
		});
		//displays first 7 sides when side button is clicked
		side_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (side_button.isSelected())
				{
					for(JButton b : entreesFM) {
						b.setVisible(false);
					}
					item_1.setVisible(true);
					item_2.setVisible(true);
					item_3.setVisible(true);
					item_4.setVisible(false);
					item_5.setVisible(false);
					item_6.setVisible(false);
					item_7.setVisible(false);
					
					item_1.setText("Salad");
					item_2.setText("Fries");
					item_3.setText("Wings");

					entree_button.setSelected(false);
					drink_button.setSelected(false);
					dessert_button.setSelected(false);
				}
				else
				{
					for(JButton b : entreesFM) {
						b.setVisible(false);
					}
					
					item_1.setVisible(false);
					item_2.setVisible(false);
					item_3.setVisible(false);
					item_4.setVisible(false);
					item_5.setVisible(false);
					item_6.setVisible(false);
					item_7.setVisible(false);
					
					side_button.setSelected(false);
				}
			}
		});
		//displays first 7 drinks when drink button is clicked
		drink_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (drink_button.isSelected())
				{
					for(JButton b : entreesFM) {
						b.setVisible(false);
					}
					item_1.setVisible(true);
					item_2.setVisible(false);
					item_3.setVisible(false);
					item_4.setVisible(false);
					item_5.setVisible(false);
					item_6.setVisible(false);
					item_7.setVisible(false);
					
					item_1.setText("Coke");
					entree_button.setSelected(false);
					side_button.setSelected(false);
					dessert_button.setSelected(false);
				}
				else
				{
					for(JButton b : entreesFM) {
						b.setVisible(false);
					}
					item_1.setVisible(false);
					item_2.setVisible(false);
					item_3.setVisible(false);
					item_4.setVisible(false);
					item_5.setVisible(false);
					item_6.setVisible(false);
					item_7.setVisible(false);
					
					drink_button.setSelected(false);
				}
			}
		});
		//displays first 7 desserts when dessert button is clicked
		dessert_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dessert_button.isSelected())
				{
					for(JButton b : entreesFM) {
						b.setVisible(false);
					}
					item_1.setVisible(true);
					item_2.setVisible(false);
					item_3.setVisible(false);
					item_4.setVisible(false);
					item_5.setVisible(false);
					item_6.setVisible(false);
					item_7.setVisible(false);
					
					item_1.setText("Shake");
					entree_button.setSelected(false);
					side_button.setSelected(false);
					drink_button.setSelected(false);
				}
				else
				{
					for(JButton b : entreesFM) {
						b.setVisible(false);
					}
					item_1.setVisible(false);
					item_2.setVisible(false);
					item_3.setVisible(false);
					item_4.setVisible(false);
					item_5.setVisible(false);
					item_6.setVisible(false);
					item_7.setVisible(false);
					dessert_button.setSelected(false);
				}
			}
		});	
		//displays next 7 items when next page button is clicked
		next_page_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					item_1.setVisible(true);
					item_2.setVisible(true);
					item_3.setVisible(true);
					item_4.setVisible(true);
					item_5.setVisible(true);
					item_6.setVisible(true);
					item_7.setVisible(true);
				}
		});	
		//displays previous 7 items when previous page button is clicked
		previous_page_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
					item_1.setVisible(false);
					item_2.setVisible(false);
					item_3.setVisible(false);
					item_4.setVisible(false);
					item_5.setVisible(false);
					item_6.setVisible(false);
					item_7.setVisible(false);
			}
		});	
		
		item_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderTypeFrame.setVisible(false);
				OrderListFrame.setVisible(false);
				MenuListFrame.setVisible(false);
				OrderDetails.setVisible(true);
				
				item.name = item_1.getText();
				item_name_textfield.setText(item.name);
			}
		});	
		
		item_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderTypeFrame.setVisible(false);
				OrderListFrame.setVisible(false);
				MenuListFrame.setVisible(false);
				OrderDetails.setVisible(true);
				
				item.name = item_2.getText();
				item_name_textfield.setText(item.name);
					
			}
		});		
	
		item_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderTypeFrame.setVisible(false);
				OrderListFrame.setVisible(false);
				MenuListFrame.setVisible(false);
				OrderDetails.setVisible(true);
				
				item.name = item_3.getText();
				item_name_textfield.setText(item.name);
					
			}
		});
		
		item_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderTypeFrame.setVisible(false);
				OrderListFrame.setVisible(false);
				MenuListFrame.setVisible(false);
				OrderDetails.setVisible(true);
				
				item.name = item_4.getText();
				item_name_textfield.setText(item.name);
					
			}
		});
		
		item_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderTypeFrame.setVisible(false);
				OrderListFrame.setVisible(false);
				MenuListFrame.setVisible(false);
				OrderDetails.setVisible(true);
				
				item.name = item_5.getText();
				item_name_textfield.setText(item.name);
					
			}
		});
		item_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderTypeFrame.setVisible(false);
				OrderListFrame.setVisible(false);
				MenuListFrame.setVisible(false);
				OrderDetails.setVisible(true);
				
				item.name = item_6.getText();
				item_name_textfield.setText(item.name);
					
			}
		});
		
		item_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderTypeFrame.setVisible(false);
				OrderListFrame.setVisible(false);
				MenuListFrame.setVisible(false);
				OrderDetails.setVisible(true);
				
				item.name = item_7.getText();
				item_name_textfield.setText(item.name);
					
			}
		});
		
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderTypeFrame.setVisible(true);
				OrderListFrame.setVisible(true);
				MenuListFrame.setVisible(true);
				OrderDetails.setVisible(false);					
			}
		});		
		
		confirm_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String text = order_textfield.getText();			
					order_textfield.setText(text+"\n   "+item.price+" - "+item.name);
					OrderTypeFrame.setVisible(true);
					OrderListFrame.setVisible(true);
					MenuListFrame.setVisible(true);
					OrderDetails.setVisible(false);					
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
}
