package client.games;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GuessTheNumberTest {
	
	@BeforeEach
	void setup() throws Exception
	{
		GuessTheNumber tester = new GuessTheNumber();
	}
	
	@Test
	void testGuessTheNumber() {
		GuessTheNumber tester = new GuessTheNumber();
		tester.guessNum();
		//assertNotEquals(5, tester.guessNum());
	}

	@Test
	void testGuessNum() {
		GuessTheNumber tester = new GuessTheNumber();
		int num = 32;
		
		
		//assertEquals(num, tester.guessNum());
		
	}

	@Test
	void testGenerateNumber() {
		GuessTheNumber tester = new GuessTheNumber();
		int minimum = 0;
		int maximum = 100;
		
		tester.generateNumber(minimum, maximum);
		
		assertNotEquals(45, tester.generateNumber(minimum, maximum));
	}

}
