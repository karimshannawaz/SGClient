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
	void testGuessNum() {
		GuessTheNumber tester = new GuessTheNumber();
		int num = 32;
		//tester.guessNum();
		tester.generateNumber(0, 100);
		
		assertNotEquals(num, tester.generateNumber(0, 100));
		
		
	}

	@Test
	void testGenerateNumber() {
		GuessTheNumber tester = new GuessTheNumber();
		int minimum = 0;
		int maximum = 100;
		
		tester.generateNumber(minimum, maximum);
		
		assertNotNull(tester.generateNumber(minimum, maximum));
	}

}
