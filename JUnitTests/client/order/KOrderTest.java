package client.order;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KOrderTest {

	@BeforeEach
	void setUp() throws Exception {
		KOrder tester = new KOrder();
	}

	@Test
	void testAddItemStringDoubleIntStringString() {
		KOrder tester = new KOrder();
		tester.addItem("Cheeseburger", 6.75, 2, " no special request", "entree", "beef_patty:1,veggie_buns,vegan_cheese");
		tester.addItem("Classic Fried Wings", 8.99, 3, "none", "side", "wings:10,buffalo_f");
		
		assertEquals(2, tester.items.size(), "equals 2 because 2 items were added");
	}

	@Test
	void testAddItemMItem() {
		KOrder tester = new KOrder();

		MItem newMI = new MItem();
		newMI.name = "Pizza";
		newMI.price = 12.99;
		newMI.description = "From Italy";
		newMI.type = "default";
		newMI.menuType = "entree";
		newMI.allergens = "wheat, gluten";
		newMI.calories = 655;
		newMI.ingredients = "cheese:1";
		
		tester.addItem(newMI);
		
		assertEquals(1, tester.items.size(), "equals 1 because 1 item was added");
	}

	@Test
	void testClear() {
		KOrder tester = new KOrder();
		tester.addItem("Cheeseburger", 6.75, 2, "none", "entree", "beef_patty:1:t:vegan_patty,burger_buns:1:t:veggie_buns,cheese:1:t:vegan_cheese");
		tester.addItem("Classic Fried Wings", 8.99, 3, "none", "side", "wings:10:f:n,buffalo_f:1:t:bbq_f");
		
		tester.items.clear();
		assertEquals(0, tester.items.size(), "equals 0 because List was cleared");
	}

	@Test
	void testSetTableID() {
		KOrder tester = new KOrder();
		tester.setTableID(7);
		
		assertEquals(7, tester.getTableID());
	}
	
	@Test
	void testGetTableID() {
		KOrder tester = new KOrder();
		tester.setTableID(7);
		
		assertEquals(7, tester.getTableID());
		
	}


}
