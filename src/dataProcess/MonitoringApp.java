package dataProcess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MonitoringApp extends Application {
	/**
	 * main, runs GUI
	 * 
	 * @param args, no args needed here
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Monitor.fxml"));
		primaryStage.setTitle("BTP400 Assignment 1 Monitoring Application");
		primaryStage.setScene(new Scene(root, 670, 554));
		primaryStage.show();
	}
}
