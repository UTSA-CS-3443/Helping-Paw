package application.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Main;
import application.model.TimerThread;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class TimerController implements EventHandler<ActionEvent>  {
	@FXML
	Text timer;
	@FXML
	TextField input;
	@FXML
	Text zero;
	@FXML
	Button pause;
	@FXML
	Button start;
	@FXML
	Button done;
	@FXML
	Button resume;
	TimerThread currTimer;
	TimerThread temp;
	public void initialize() {
		timer.setText("");
		resume.setVisible(false);
		pause.setVisible(false);
		done.setVisible(false);

	}
	public static void updateTime(String timeLeft) {
		Node ts = Main.stage.getScene().lookup("#timer");
		if(ts != null && timeLeft!=null) {
			((Text) ts).setText(timeLeft);
		} 
	}
	@Override
	public void handle(ActionEvent event) {
		Button p = (Button) event.getSource();
		if(p.getId().equals("start")) {
			timer.setVisible(true);
			long m = Long.parseLong(input.getText());
			TimerThread t = new TimerThread(m);
			currTimer = t;
			input.clear();
			start.setVisible(false);
			input.setVisible(false);
			zero.setVisible(false);
			done.setVisible(true);
			resume.setVisible(false);
			pause.setVisible(true);
			currTimer.start();

			//else {
			//t.run();
			//}
		}
		if(p.getId().equals("pause")) {
			long[] tim = currTimer.pauseTimer();
			TimerThread t = new TimerThread(tim[0],tim[1]);
			currTimer = t;
			pause.setVisible(false);
			done.setVisible(false);
			resume.setVisible(true);
		}
		else if(p.getId().equals("done")) {
			currTimer.stopTimer();
			timer.setVisible(false);
			input.setVisible(true);
			zero.setVisible(true);
			pause.setVisible(false);
			done.setVisible(false);
			resume.setVisible(false);
			start.setVisible(true);
		}
		else if(p.getId().equals("resume")) {
			done.setVisible(true);
			resume.setVisible(false);
			pause.setVisible(true);
			currTimer.start();
		}
	}
}
