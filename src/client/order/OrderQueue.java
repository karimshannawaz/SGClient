package client.order;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds the queue of orders to be processed
 * Kitchen can see these.
 *
 * CLIENT SIDED
 * 
 * @author karim
 *
 */
public class OrderQueue {
	
	public static List<KOrder> unfulfilledOrders = new ArrayList<KOrder>();
	public static List<KOrder> unpaidOrders = new ArrayList<KOrder>();
	public static List<KOrder> completedOrders = new ArrayList<KOrder>();
}
