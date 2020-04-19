package client.utils;

import java.util.regex.Pattern;

/**
 * Holds CLIENT constants and functions.
 * 
 * @author Karimshan
 *
 */
public class Constants {

	public static final int PORT = 43594;
	public static final String IP = "127.0.0.1";
	public static final String NAME = "Seven Guys";

	// Indicates that the client will allow us to login as a waiter, manager, kitchen staff or customer.
	public static final boolean DEV_MODE = false;

	// Indicates that the client will allow us to login as a waiter, manager or kitchen staff.
	// If false, then client will only allow customer input.
	// DEV MODE overrides this.
	public static final boolean EMPLOYEE_MODE = false;

	/**
	 * Returns true if the email entered is valid or not.
	 * Credit: GeeksForGeeks.org
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(String email) { 
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
			"[a-zA-Z0-9_+&*-]+)*@" + 
			"(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
			"A-Z]{2,7}$"; 

		Pattern pat = Pattern.compile(emailRegex); 
		if (email == null) 
			return false; 
		return pat.matcher(email).matches(); 
	}
}
