package client.order;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds information for a customer's order.
 * CLIENT SIDED
 * 
 * @author Karimshan
 *
 */
public class CustomerOrder {
	
	public static double subtotal;
	
	public static List<MItem> items = new ArrayList<MItem>();
	
	public static void addItem(String name, double price, int qty, 
			String specialRequests, String ingredients) {
		MItem item = new MItem();
		item.name = name;
		item.price = price;
		item.qty = qty;
		item.specialReqs = specialRequests;
		item.ingredients = ingredients;
		items.add(item);
	}
	
	public static void addItem(MItem item) {
		items.add(item);
	}
	
	public static void clear() {
		items.clear();
	}
}
