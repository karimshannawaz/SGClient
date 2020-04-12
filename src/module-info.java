module SGClient {
	exports client.network;
	exports client.network.packet;
	exports client.rewards;
	exports client.games;
	exports client;
	exports client.order;
	exports client.utils;

	requires JLayer;
	requires java.desktop;
	requires netty;
	requires substance;
}