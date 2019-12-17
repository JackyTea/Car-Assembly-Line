package dataProcess;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class StationServerThread extends Thread {

	// server socket and filename
	private Socket socket = null;
	private String fileName = null;
	static Integer stationNum = 0;

	// constructor
	public StationServerThread(Socket socket, String fileName) {
		super("StationServerThread");
		this.socket = socket;
		this.fileName = fileName;
	}

	/**
	 * Thread run() method that handles incoming tasks
	 */
	public void run() {

		// streams to get client requests and respond
		try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());) {

			// receive acknowledgement message
			String ack = (String) in.readObject();
			if (ack.equals("good")) {
				// respond to start stations
				String response = "okay";
				out.writeObject(response);

				// get car info and log file string
				Car theCar = (Car) in.readObject();
				System.out.println();
				System.out.println(theCar.toString(3) + "\n");

				// get number of tasks to process, respond for each one to start next station
				Integer taskCounter = (Integer) in.readObject();
				Boolean optional = (Boolean) in.readObject();
				if (optional == false) {
					System.out.println("There are " + taskCounter
							+ " tasks to be completed \n[Order does not require sensors to be installed on car]\n");
				} else {
					System.out.println("There are " + taskCounter
							+ " tasks to be completed \n[Order requires sensors to be installed on car]\n");
				}

				// process jobs and log file
				String logFileName = (String) in.readObject();
				StationClientHandler handler = new StationClientHandler();
				//int z = 0;
				//while (z < taskCounter) {
					stationNum += 1;
					//Thread.sleep(1000);
					int taskNum = (int) in.readObject(); // Retrieve task# 
					System.out.println("Working on Task" + taskNum);
					StationName job = (StationName) in.readObject();
					Boolean status = handler.processJob(job, logFileName);
					out.writeObject(status);
					if (status == true) {
						// final station
						if (taskNum == taskCounter) {
							System.out.println(job + " successfully completed!");
							System.out.println("Final station(" + stationNum + ") reached!");
							Logger.appendToFile(fileName, job + " successfully completed!");
							Logger.appendToFile(fileName, "Final station reached!");
							//break;
						}

						// initial station
						else if (taskNum == 1) {
							System.out.println("Initial station(" + stationNum + ") -> getting tasks . . .");
							System.out.println(job + " successfully completed!");
							System.out.println("Starting station " + (stationNum + 1) +   ". . .\n");
							Logger.appendToFile(fileName, "Initial station!");
							Logger.appendToFile(fileName, job + " successfully completed!");
						} else {
							System.out.println(job + " successfully completed!");
							System.out.println("Starting station " + (stationNum + 1) +   ". . .\n");
							Logger.appendToFile(fileName, job + " successfully completed!");
						}
						if (taskNum != taskCounter)
						{
							System.out.print("Starting up station " + (stationNum + 1) +   " -> ");
							for (int i = 0; i < 30; i++) {
								Thread.sleep(100);
								System.out.print("\u2588");
							}
							System.out.println();
							System.out.println("Station Ready!");
							System.out.println();
						}
					} else {
						System.out.println("Station does not have sufficient resources to complete " + job + "\n");
						Logger.appendToFile(fileName,
								"Station does not have sufficient resources to complete " + job + "\n");
						//break;
					}
					//z++;
				//}

			} else {
				System.out.print("Unable to start stations! Going offline . . .\n");
			}
			socket.close();
		} catch (IOException e) {
			System.out.println("Error: " + e);
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
}
