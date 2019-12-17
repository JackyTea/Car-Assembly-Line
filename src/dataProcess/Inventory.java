package dataProcess;

public class Inventory {
	/**
	 * materials
	 */
	private static int chassis = 100;
	private static int axels = 100;
	private static int brakes = 100;
	private static int gears = 100;
	private static int transmissions = 100;
	private static int sensors = 100;
	private static int doors = 100;
	private static int paint = 100;

	/**
	 * getMaterial, subtract materials from inventory
	 * 
	 * @param amount, materials to use
	 * @return boolean, enough or not enough resources
	 */
	public static boolean getChassis(int amount) {
		if (chassis >= amount) {
			chassis -= amount;
			return true;
		}
		return false;
	}

	public static boolean getAxels(int amount) {
		if (axels >= amount) {
			axels -= amount;
			return true;
		}
		return false;
	}

	public static boolean getBrakes(int amount) {
		if (brakes >= amount) {
			brakes -= amount;
			return true;
		}
		return false;
	}

	public static boolean getGearBox(int amount) {
		if (gears >= amount) {
			gears -= amount;
			return true;
		}
		return false;
	}

	public static boolean getTransmission(int amount) {
		if (transmissions >= amount) {
			transmissions -= amount;
			return true;
		}
		return false;
	}

	public static boolean getSensors(int amount) {
		if (sensors >= amount) {
			sensors -= amount;
			return true;
		}
		return false;
	}

	public static boolean getDoors(int amount) {
		if (doors >= amount) {
			doors -= amount;
			return true;
		}
		return false;
	}

	public static boolean getPaint(int amount) {
		if (paint >= amount) {
			paint -= amount;
			return true;
		}
		return false;
	}

	public static boolean getVIN(int amount) {
		if (paint >= amount) {
			paint -= amount;
			return true;
		}
		return false;
	}
}
