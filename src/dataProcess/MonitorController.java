package dataProcess;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MonitorController {

	/**
	 * JavaFX components
	 */
	public TextArea serverOutput;
	public TextArea clientOutput;
	public TextField file;
	public TextField file2;
	public TextArea error;
	public Button view;
	public Button clear;
	public Button exit;

	@FXML
	private void getLogs() throws IOException {
		try {
			serverOutput.setText(Logger.readLogs(file.getText()));
			clientOutput.setText(Logger.readLogs(file2.getText()));
		} catch (IOException e) {
			error.setText("No such file!");
		}
	}

	@FXML
	private void clearLogs() {
		serverOutput.clear();
		clientOutput.clear();
	}

	@FXML
	private void closeWindow() {
		Stage stage = (Stage) exit.getScene().getWindow();
		stage.close();
	}
}
