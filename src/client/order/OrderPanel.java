/*
 * Dillon McPherson 
 * 
 * creates the GUI for the order screen
 */

package client.order;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

import client.Client;
import client.network.packet.PacketDecoder;

public class OrderPanel extends JPanel {

	private static final long serialVersionUID = -1338118268945423515L;

	/**
	 * Create the panel.
	 */
	public OrderPanel() {
		super();
		setBounds(0, 0, 1039, 522);
		setLayout(null);

		//creates entrees button
		JToggleButton entree_button = new JToggleButton("Entrees");
		entree_button.setFont(new Font("Tahoma", Font.PLAIN, 45));
		entree_button.setBounds(0, 0, 346, 130);
		add(entree_button);

		//creates sides button
		JToggleButton side_button = new JToggleButton("Sides");
		side_button.setFont(new Font("Tahoma", Font.PLAIN, 45));
		side_button.setBounds(0, 130, 346, 130);
		add(side_button);

		//creates drinks button
		JToggleButton drink_button = new JToggleButton("Drinks");
		drink_button.setFont(new Font("Tahoma", Font.PLAIN, 45));
		drink_button.setBounds(0, 260, 346, 130);
		add(drink_button);

		//creates desserts button
		JToggleButton dessert_button = new JToggleButton("Desserts");
		dessert_button.setFont(new Font("Tahoma", Font.PLAIN, 45));
		dessert_button.setBounds(0, 390, 346, 130);
		add(dessert_button);

		//creates a frame to hold the menu items
		JPanel MenuListFrame = new JPanel();
		MenuListFrame.setBorder(null);
		MenuListFrame.setBounds(347, 0, 347, 522);
		add(MenuListFrame);
		MenuListFrame.setLayout(null);

		//creates a menu item button
		JButton item_1 = new JButton("");
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
		scrollPane.setBounds(0, 0, 346, 522);
		OrderListFrame.add(scrollPane);

		//textfield that holds the order summary
		JTextArea order_textfield = new JTextArea();
		order_textfield.setFont(new Font("Monospaced", Font.PLAIN, 15));
		order_textfield.setEditable(false);
		order_textfield.setLineWrap(true);
		order_textfield.setText("Order: ");
		scrollPane.setViewportView(order_textfield);

		//displays first 7 entrees when entree button is clicked
		entree_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (entree_button.isSelected())
				{
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
				}
				else
				{
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
				//	if (next_page_button.isClicked())
				//{
				item_1.setVisible(true);
				item_2.setVisible(true);
				item_3.setVisible(true);
				item_4.setVisible(true);
				item_5.setVisible(true);
				item_6.setVisible(true);
				item_7.setVisible(true);
				//}
				//else
				/*{
					item_1.setVisible(false);
					item_2.setVisible(false);
					item_3.setVisible(false);
					item_4.setVisible(false);
					item_5.setVisible(false);
					item_6.setVisible(false);
					item_7.setVisible(false);
					}*/
			}
		});	
		//displays previous 7 items when previous page button is clicked
		previous_page_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//	if (dessert_button.isSelected())
				/*	{
					item_1.setVisible(true);
					item_2.setVisible(true);
					item_3.setVisible(true);
					item_4.setVisible(true);
					item_5.setVisible(true);
					item_6.setVisible(true);
					item_7.setVisible(true);
				} */
				//	else
				//{
				item_1.setVisible(false);
				item_2.setVisible(false);
				item_3.setVisible(false);
				item_4.setVisible(false);
				item_5.setVisible(false);
				item_6.setVisible(false);
				item_7.setVisible(false);
				//	}
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

