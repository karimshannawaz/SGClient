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
	
	// Indicates whether the user has logged into rewards or not.
	public static boolean rwdsLoggedIn = false;
	
	// Represents if the email the customer is trying to register with already exists or not.
	public static boolean emailExists = false;
	
	// Set to true if the customer has logged in with their rewards or not.
	public static boolean rwdsLgn = false;
	
	// Represents the email, birthday MM/DD/YYYY (used for rewards) and how many visits
	// for this customer..
	public static String email = null;
	public static String birthday = null;
	public static String name = null;
	public static int visits = -1;
	public static boolean hasFreeSide = false;
	public static boolean hasBirthdayEntree = false;
	public static boolean hasFreeDessert = false;
	
	// Represents the id and password for the employee that logs in.
	public static int id = -1;
	public static String password = null;
	

}
