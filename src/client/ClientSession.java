package client;

/**
 * Represents a customer session, as in the customer's order, table,
 * payment and anything else they may do that is temporary. This information is
 * discarded after the customer pays and is ready to leave, similar to how it would be
 * in an actual restaurant.
 * 
 * @author karim
 *
 */
public class ClientSession {
	
	// Set to true if the customer has logged in with their rewards or not.
	public static boolean rwdsLgn = false;

}
