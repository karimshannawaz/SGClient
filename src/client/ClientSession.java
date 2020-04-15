package client;

/**
 * Represents a customer session, as in the customer's order, table,
 * payment and anything else they may do that is temporary. This information is
 * discarded after the customer pays and is ready to leave, similar to how it would be
 * in an actual restaurant.
 * 
 * @author Karimshan
 *
 */
public class ClientSession {
	
	// Represents the temporary table ID.
	public static int tableID = -1;
	
	// Indicates whether the user has logged into rewards or not.
	public static boolean rwdsLoggedIn = false;
	
	// Represents if the email the customer is trying to register with already exists or not.
	public static boolean emailExists = false;
	
	// Set to true if the customer has logged in with their rewards or not.
	public static boolean rwdsLgn = false;
	
	// Represents the email, birthday MM/DD/YYYY (used for rewards) and how many visits
	// for this customer.
	public static String email = null;
	public static String birthday = null;
	public static String name = null;
	public static int visits = -1;
	public static boolean hasFreeSide = false;
	public static boolean hasBirthdayEntree = false;
	public static boolean hasFreeDessert = false;
	
	// Represents the id and password for the employee that logs in.
	public static String id = null;
	public static String password = null;
	public static String role = null;
	
	// Set to true if the customer is notified that the restaurant is closing soon.
	public static boolean closingSoonNoti = false;
	
	// Set to true if it is past 11:29 PM CST.
	public static boolean ordersStopped = false;

}
