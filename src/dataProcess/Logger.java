package dataProcess;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Logger {
	/**
	 * wipeFile, clears log files for clean start
	 * 
	 * @param fileName, String name of file
	 * @throws IOException if file does not exist
	 */
	public static void wipeFile(String fileName) throws IOException {
		try {
			FileWriter file = new FileWriter(fileName, false);
			PrintWriter print = new PrintWriter(file, false);
			file.flush();
			print.close();
			print.close();
		} catch (IOException e) {
			System.out.println("Error:" + e);
		}
	}

	/**
	 * appendToFile, add to log file on assembly line jobs
	 * 
	 * @param fileName, String name of file
	 * @param text, text to write to file
	 * @throws IOException if file does not exist
	 */
	public static void appendToFile(String fileName, String text) throws IOException {
		try (FileWriter file = new FileWriter(fileName, true);
				BufferedWriter buffer = new BufferedWriter(file);
				PrintWriter print = new PrintWriter(buffer)) {
			print.write("");
			print.println(text);
		} catch (IOException e) {
			System.out.println("Error:" + e);
		}
	}

	public static String readLogs(String fileName) throws IOException {
		String logText = null;
		try {
			Path path = Paths.get(fileName);
			byte[] logs = Files.readAllBytes(path);
			logText = new String(logs, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("Error:" + e);
		}
		return logText;
	}
}
