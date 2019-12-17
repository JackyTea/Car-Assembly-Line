package dataProcess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.TreeMap;

public class StationClient {
	/**
	 * processJobFile, process key pair task file
	 * 
	 * @param fileName, String name of file
	 * @return map, TreeMap array of key pair values
	 * @throws IOException if file does not exist
	 */
	public static TreeMap<String, String> processJobFile(String fileName) throws IOException {
		int key = 0;
		int value = 1;
		TreeMap<String, String> map = new TreeMap<String, String>();
		BufferedReader buffer = new BufferedReader(new FileReader(new File(fileName)));
		String line;
		while ((line = buffer.readLine()) != null) {
			if (!line.startsWith("#") && !line.isEmpty()) {
				String[] pair = line.trim().split("=");
				map.put(pair[key].trim(), pair[value].trim());
			}
		}
		buffer.close();
		return (map);
	}

	/**
	 * main, runs the client on localhost and port 27000
	 * 
	 * @param args, log file and task file names
	 * @throws IOException if error occurs
	 */
	public static void main(String[] args) throws IOException {

		// make sure args are present
		// first arg is log file, second arg is task file
		if (args.length != 2) {
			System.out.println("Log file and task file not found!");
			System.exit(1);
		}

		// port and localhost
		InetAddress inetAddress = InetAddress.getLocalHost();
		String hostName = inetAddress.getHostName();
		int portNumber = 27000;

		// process the job file and initialize for Car
		TreeMap<String, String> job = processJobFile(args[1]);
		ArrayList<StationName> tasks = new ArrayList<>();
		Car theCar = new Car(job.get("vin").toString(), job.get("name").toString(), Integer.parseInt(job.get("year")),
				false);
		Integer taskCounter = 0;
		
		int x = 0;
		while (job.get("task" + (++x)) != null) {
			taskCounter++;
		}
		for (int i = 1; i <= taskCounter; i++) {
			tasks.add(StationName.valueOf(job.get("task" + i)));
		}
		theCar.setTasks(tasks);
		
		Integer totalTasks = taskCounter;
		for (int i = 0; i < totalTasks; i++) //Run for every task
		{
		// client communications
		try (Socket clientSocket = new Socket(hostName, portNumber);
				ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());) {

			// send server acknowledgement
			String ack = "good";
			out.writeObject(ack);

			// receive response and start stations
			String response = (String) in.readObject();
			if (response.equals("okay")) {
				Thread.sleep(500);

				// let server on number of tasks, optional tasks, car info and log file
				out.writeObject(theCar);
				out.writeObject(taskCounter);
				Boolean optional = Boolean.valueOf(job.get("optional").toString());
				out.writeObject(optional);
				String logFileName = args[0];
				out.writeObject(logFileName);
				System.out.println();

				// send jobs over socket and processes them
				//int z = 0;
				int taskNum = 0;
				//while (z < taskCounter) {
					Thread.sleep(1000);
					taskNum = 1 + i;
					out.writeObject(taskNum);
					out.writeObject(theCar.carTasks.get(i));
					System.out.println("Connected on port" + portNumber);
					Boolean status = (Boolean) in.readObject();
					if (status == true) {
						if (i == taskCounter - 1) {
							theCar.setComplete(true);
							System.out.println(theCar.toString(2));
							System.out.println(
									"Station " + (i + 1) + ": " + theCar.carTasks.get(i) + " successfully done!");
							Thread.sleep(1500);
							System.out.println("Car assembly finished!");
							Logger.appendToFile(args[0], "Car completed!");
							//break;
						}else {
						System.out.println(theCar.toString(1));
						System.out
								.println("Station " + (i + 1) + ": " + theCar.carTasks.get(i) + " successfully done!");
						System.out.println("Calling next station . . .\n");
						}
					} else {
						System.out.println("Not enough resources! Shutting down assembly line!\n");
						Logger.appendToFile(args[0], "Car could not be completed!");
						//break;
					}
					Thread.sleep(2500); // Compensate for station startup
					//z++;
				//}
			} else {
				System.out.print("Server not responding! Going offline . . .");
			}
			portNumber ++;
			clientSocket.close();
		}
		// possible errors
		catch (UnknownHostException e) {
			System.out.println("Host: " + hostName + "does not exist!");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("Station is offline!");
			System.exit(1);
		} catch (Exception e) {
			System.out.println("Error: " + e);
			System.exit(1);
		}
		}
		}
}
