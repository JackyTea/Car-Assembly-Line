package dataProcess;

import java.io.IOException;

public class StationClientHandler {
	/**
	 * processJob, processes a job via accessing Inventory
	 * 
	 * @param station, StationName enumeration for the switch
	 * @param fileName, String of file name
	 * @return Boolean, if Station is successful (true)
	 */
	public Boolean processJob(StationName station, String fileName) {
		Boolean status = false;
		try {
			switch (station) {
			case GET_CHASSIS:
				if (Inventory.getChassis(20)) {
					Logger.appendToFile(fileName, "Chassis installed!");
					status = true;
				} else {
					Logger.appendToFile(fileName, "Not enough materials for chassis!");
					status = false;
				}
				break;
			case INSTALL_AXELS:
				if (Inventory.getAxels(20)) {
					Logger.appendToFile(fileName, "Axels installed!");
					status = true;
				} else {
					Logger.appendToFile(fileName, "Not enough materials for axels!");
					status = false;
				}
				break;
			case INSTALL_BRAKES:
				if (Inventory.getBrakes(20)) {
					Logger.appendToFile(fileName, "Brakes installed!");
					status = true;
				} else {
					Logger.appendToFile(fileName, "Not enough materials for brakes!");
					status = false;
				}
				break;
			case INSTALL_GEARBOX:
				if (Inventory.getGearBox(20)) {
					Logger.appendToFile(fileName, "Gearbox installed!");
					status = true;
				} else {
					Logger.appendToFile(fileName, "Not enough materials for gearbox!");
					status = false;
				}
				break;
			case INSTALL_TRANSMISSION:
				if (Inventory.getTransmission(20)) {
					Logger.appendToFile(fileName, "Transmission installed!");
					status = true;
				} else {
					Logger.appendToFile(fileName, "Not enough materials for transmission!");
					status = false;
				}
				break;
			case INSTALL_SENSORS:
				if (Inventory.getSensors(20)) {
					Logger.appendToFile(fileName, "Sensors installed!");
					status = true;
				} else {
					Logger.appendToFile(fileName, "Not enough materials for sensors!");
					status = false;
				}
				break;
			case INSTALL_DOORS:
				if (Inventory.getDoors(20)) {
					Logger.appendToFile(fileName, "Doors installed!");
					status = true;
				} else {
					Logger.appendToFile(fileName, "Not enough materials for doors!");
					status = false;
				}
				break;
			case PAINT:
				if (Inventory.getPaint(20)) {
					Logger.appendToFile(fileName, "Painted");
					status = true;
				} else {
					Logger.appendToFile(fileName, "Not enough materials to paint!");
					status = false;
				}
				break;
			case ENGRAVE_VIN:
				if (Inventory.getPaint(20)) {
					Logger.appendToFile(fileName, "VIN engraved!");
					status = true;
				} else {
					Logger.appendToFile(fileName, "Not enough materials to engrave VIN!");
					status = false;
				}
				break;
			default:
				Logger.appendToFile(fileName, "Error processing " + station + "!");
				status = false;
				break;
			}
		} catch (IOException e) {
			System.out.println("ClientHandler Error: " + e);
			status = false;
		}
		return status;
	}
}
