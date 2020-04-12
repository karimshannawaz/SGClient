package client.order;

import java.util.HashMap;
import java.util.Map;

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
	public static Map<Integer, MItem> instance = new HashMap<Integer, MItem>();

}
