package client.order;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds information for a customer's order.
 * GIVEN TO KITCHEN STAFF FROM SERVER.
 * CLIENT SIDED
 * 
 * @author Karimshan
 *
 */
public class KOrder {
	
	private int tableID;
	
	public double subtotal;
	
	public List<MItem> items = new ArrayList<MItem>();
	
	public void addItem(String name, double price, int qty, 
			String specialRequests, String ingredients) {
		MItem item = new MItem();
		item.name = name;
		item.price = price;
		item.qty = qty;
		item.specialReqs = specialRequests;
		item.ingredients = ingredients;
		items.add(item);
	}
	
	public void addItem(MItem item) {
		items.add(item);
	}
	
	public void clear() {
		items.clear();
	}

	public int getTableID() {
		return tableID;
	}

	public void setTableID(int tableID) {
		this.tableID = tableID;
	}
}
