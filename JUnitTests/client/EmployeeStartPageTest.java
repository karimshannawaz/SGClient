package client;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/*
 * SubmitEmpInfo() Test currently fails because of NullPointerException
 * but Class works for implementation
 */
class EmployeeStartPageTest {

	@BeforeEach
	void setUp() throws Exception {
		EmployeeStartPage tester = new EmployeeStartPage();
		
	}

	@Test
	void testSubmitEmpInfo() {
		EmployeeStartPage tester = new EmployeeStartPage();
		String id = "waitstaff";
		String password = "testpassword";
		
		Client.session.getPacketEncoder().sendLoginRequest(id, password);
	
	}

	@Test
	void testWaiterLandingPage() {
		EmployeeStartPage tester = new EmployeeStartPage();
		tester.waitstaffPage.setVisible(true);

	}

	@Test
	void testKitchenLandingPage() {
		EmployeeStartPage tester = new EmployeeStartPage();
		tester.kitchenPage.setVisible(true);
	}

}
