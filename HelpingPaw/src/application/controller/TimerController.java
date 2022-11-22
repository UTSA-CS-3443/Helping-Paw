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

public class TimerController implements EventHandler<KeyEvent>  {
	@FXML
	Text timer;
	@FXML
	TextField input;
	@FXML
	Text zero;
	
	@Override
	public void handle(KeyEvent event) {
		TextField in = (TextField) event.getSource();
		try {
			 if (event.getCode() == KeyCode.ENTER && in.getText()!=null) {
				 input.setVisible(false);
				 zero.setVisible(false);
				 long m = Long.parseLong(input.getText());
				 TimerThread t = new TimerThread(m);
				 t.start();
			 }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void initialize() {
		timer.setText("");
		
	}
	public static void updateTime(String timeLeft) {
		Node ts = Main.stage.getScene().lookup("#timer");
		if(ts != null && timeLeft!=null) {
			((Text) ts).setText(timeLeft);
		} 
	}
}
