package client.rewards;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import client.Client;
import client.ClientSession;
import client.network.ClientChannel;
import client.network.Session;
import client.utils.Constants;
import client.utils.JFrameUtils;

/*
 * Test currently fails because of NullPointerException
 * but Class works for implementation
 */

class RewardsPanelTest {

	@BeforeEach
	void setUp() throws Exception {
	}


	@Test
	void testSignup() {
		
		String email = "tester@gmail.com";
		String name = "tester";
		String DOB = "04161997";
		
		Client.session.getPacketEncoder().sendCreationRequest(email, DOB, name);
		
		assertEquals(email, ClientSession.email);
	}

	@Test
	void testSubmitLoginRequest() {
		String email = "tester@gmail.com";
		String name = "tester";
		String DOB = "04161997";
		
		Client.session.getPacketEncoder().sendCreationRequest(email, DOB, name);
		
		Client.session.getPacketEncoder().sendLoginRequest(email);
		
	}

}
