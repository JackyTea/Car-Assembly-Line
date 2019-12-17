package dataProcess;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class StationClientHandlerTest {

	@Test
	void test() {
		StationClientHandler handler = new StationClientHandler();
		Boolean status = handler.processJob(StationName.INSTALL_AXELS, "HANDLER.txt");
		Boolean status2 = handler.processJob(StationName.GET_CHASSIS, "HANDLER.txt");
		assertEquals(status, status2);
	}

}
