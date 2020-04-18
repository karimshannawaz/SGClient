package client.order;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MItemTest {

	@BeforeEach
	void setUp() throws Exception {
		MItem newMI = new MItem();
		assertEquals("NOTHING", newMI.name, "equals nothing b/c its a constructor");
	}

	@Test
	void testMItemStringDoubleStringIntStringStringStringString() {
		
		MItem newMI = new MItem();
		newMI.name = "Pizza";
		newMI.price = 12.99;
		newMI.description = "From Italy";
		newMI.type = "default";
		newMI.menuType = "entree";
		newMI.allergens = "wheat, gluten";
		newMI.calories = 655;
		newMI.ingredients = "cheese:1";
		newMI.sub = "vegan_patty";
		
		assertNotEquals("Nothing", newMI.name);
		assertNotEquals(0, newMI.price);
	}

	@Test
	void testHasSub() {
		MItem newMI = new MItem();
		newMI.sub = "n";
		
		boolean test = newMI.hasSub();
		assertFalse(test);
	}

}
