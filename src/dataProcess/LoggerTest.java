package dataProcess;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class LoggerTest {

	@Test
	void test() throws IOException {
		try {
			Logger.wipeFile("LOGGER_TEST.txt");
			Logger.appendToFile("LOGGER_TEST.txt", "Test message");
			String compareMsg = "Test message\n";
			assertEquals(compareMsg, Logger.readLogs("LOGGER_TEST.txt"));
		} catch (IOException e) {
			System.out.println("Text file does not exist!");
		}
	}

}
