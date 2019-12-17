package dataProcess;

import java.io.Serializable;
import java.util.ArrayList;

public class Car implements Serializable {
	/**
	 * unique serializable ID
	 */
	private static final long serialVersionUID = -8530243603214592312L;

	/**
	 * car properties and stored list of assembly tasks
	 */
	private String VIN;
	private String model;
	private int year;
	private boolean complete;
	public ArrayList<StationName> carTasks = new ArrayList<>();

	/**
	 * constructor
	 */
	public Car(String VIN, String model, int year, boolean complete) {
		this.VIN = VIN;
		this.model = model;
		this.year = year;
		this.complete = complete;
	}

	/**
	 * setTasks, puts assembly tasks into car Object
	 * 
	 * @return void
	 */
	public void setTasks(ArrayList<StationName> tasks) {
		for (int i = 0; i < tasks.size(); i++) {
			carTasks.add(tasks.get(i));
		}
	}

	/**
	 * setComplete, indicates car completion status
	 * 
	 * @return void
	 */
	public void setComplete(boolean done) {
		this.complete = done;
	}

	/**
	 * toString, car description
	 * 
	 * @param mode, description choice for server/cleint
	 * @return String car description
	 */
	public String toString(int mode) {
		if (mode == 1) {
			return "Car Model: " + this.model + "\n" + "Car VIN: " + this.VIN + "\n" + "Car Year: " + this.year + "\n"
					+ "Completion: "
					+ (this.complete == true ? "Car completed!" : "Car assembly still in progress . . .");
		} else if (mode == 2) {
			this.complete = true;
			return "Car Model: " + this.model + "\n" + "Car VIN: " + this.VIN + "\n" + "Car Year: " + this.year + "\n"
					+ "Completion: " + "Car completed!";
		} else {
			return "Car Model: " + this.model + "\n" + "Car VIN: " + this.VIN + "\n" + "Car Year: " + this.year;
		}
	}

	/**
	 * getComplete, car completion status
	 * 
	 * @return Boolean true/false complete
	 */
	public boolean getComplete() {
		return this.complete;
	}
}
