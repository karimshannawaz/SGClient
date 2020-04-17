package client.order;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MenuTest {

	@BeforeEach
	void setUp() throws Exception {
		Menu tester = new Menu();
	}
	
	@Test
	void testGetItem() {
		MItem test = new MItem("Cheeseburger", 6.75, null, 740, null, null, null, null);
		Menu tester = new Menu();
		Menu.instance.add(test);
		
		assertNotNull(tester.getItem("Cheeseburger"), "If the item is not found, return is null");
		
		
	}

}
