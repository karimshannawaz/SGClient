package client;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import client.utils.JFrameUtils;

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
	
	// Weekly specials
	public static boolean receivedSpecialNoti = false;
	public static boolean kidsEatFree = false;
	public static boolean freeDrinkWPur = false;
	
	/**
	 * Checks to see if the day of the week is monday or sunday,
	 * and the time that the customer is currently visiting,
	 * and notifies the customer that they are visiting during 
	 * the time frame of a special.
	 */
	public static int checkSpecialsDay() {
		System.out.println("Checking");
		Date fullDate = new Date();
		String format = DateFormat.getInstance().format(fullDate);
		SimpleDateFormat dateF = new SimpleDateFormat("EEEE");
		String day = dateF.format(fullDate);
		String[] tok = format.split(" ");
		String date = tok[0];
		String time = tok[1];
		String meridiem = tok[2];
		String[] timeToks = time.split(":");
		int hour = Integer.parseInt(timeToks[0]);
		int mins = Integer.parseInt(timeToks[1]);
		
		// Kids eat free with the purchase of an adult entree on Mondays from 4 PM to 11:59 PM CST 
		if(day.equalsIgnoreCase("monday") && hour >= 4 && hour <= 11 && meridiem.equals("PM")) {
		//if(hour >= 1 && meridiem.equals("PM")) {
			if(!ClientSession.receivedSpecialNoti)
				JFrameUtils.showMessage("Seven Guys Specials", 
					"Thanks for stopping by at Seven Guys! You are currently visting\n"
					+ "during happy hour, where kids eat free with the purchase of an adult entree!");
			ClientSession.kidsEatFree = true;
			ClientSession.receivedSpecialNoti = true;
			return 1;
		}
		
		// Beverages are free with any purchase on Sundays from 4 PM to 11:59 PM CST
		if(day.equalsIgnoreCase("sunday") && hour >= 4 && hour <= 11 && meridiem.equals("PM")) {
			if(!ClientSession.receivedSpecialNoti)
				JFrameUtils.showMessage("Seven Guys Specials", 
					"Thanks for stopping by at Seven Guys! You are currently visting\n"
					+ "during happy hour, where a beverage is free with any purchase!!");
			ClientSession.freeDrinkWPur = true;
			ClientSession.receivedSpecialNoti = true;
			return 2;
		}
		return -1;
	}

	public static boolean isKitchen() {
		return role != null && role.toLowerCase().contains("kitchen");
	}

}
