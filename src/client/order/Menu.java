package client.order;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the restaurant menu
 * CLIENT SIDED
 * 
 * @author karim
 *
 */
public class Menu {

	// Represents the list of menu items for the restaurant
	// from the client kiosk's point of view.
	public static List<MItem> instance = new ArrayList<MItem>();

	/**
	 * Returns the menu item with the specified name
	 * @param itemName
	 * @return
	 */
	public static MItem getItem(String itemName) {
		for(MItem i : instance)
			if(i.name.equals(itemName))
				return i;
		return null;
	}

}
