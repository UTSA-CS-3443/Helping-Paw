package application.controller;

import java.io.File;
import java.io.IOException;
import application.Main;
import application.model.TimerThread;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class TimerController implements EventHandler<ActionEvent>  {
	@FXML
	Text timer;
	@FXML
	Text title;
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
	@FXML
	Button back;
	@FXML
	ImageView imgCat;
	@FXML
	Text txtCat;
	@FXML
	ProgressIndicator pb;

	@FXML
	Rectangle rectangleTV;
	TimerThread currTimer;
	static double ii = 0.6;
	public void initialize() {
		timer.setText("");
		resume.setVisible(false);
		pause.setVisible(false);
		done.setVisible(false);

		rectangleTV.setFill(Main.user.color);
		String col = User.colorToString(Main.user.color);

		pause.setStyle("-fx-background-color: " + col);
		start.setStyle("-fx-background-color: " + col);
		done.setStyle("-fx-background-color: " + col);
		resume.setStyle("-fx-background-color: " + col);


		File file = new File("src/images/" + Main.user.cat + "talking.png");
		Image catImg = new Image(file.toURI().toString());
		imgCat.setImage(catImg);
		txtCat.setText("Click start button when you are ready, and remember try your best! You got this!");
		title.setText(Main.currTask);

		//get the name of the task to display on the timer view

	}
	public static void updateTime(String timeLeft) {
		Node ts = Main.stage.getScene().lookup("#timer");
		if(ts != null && timeLeft!=null) {
			((Text) ts).setText(timeLeft);
		}
		Node bar = Main.stage.getScene().lookup("#pb");
		ii+=ii;
		((ProgressIndicator) bar).setProgress(ii);

	}
	@Override
	public void handle(ActionEvent event) {
		Button p = (Button) event.getSource();
		if(p.getId().equals("start")) {
			timer.setVisible(true);
			long m = Long.parseLong(input.getText());
			ii*=m;
			TimerThread t = new TimerThread(m);
			currTimer = t;
			input.clear();
			start.setVisible(false);
			input.setVisible(false);
			zero.setVisible(false);
			done.setVisible(true);
			resume.setVisible(false);
			pause.setVisible(true);


			txtCat.setText("");

			currTimer.start();
		}
		if(p.getId().equals("pause")) {
			currTimer.stopwatch.stop();

			long[] tim = currTimer.pauseTimer();
			TimerThread t = new TimerThread(tim[0],tim[1]);
			currTimer = t;
			pause.setVisible(false);
			done.setVisible(false);
			resume.setVisible(true);
		}
		else if(p.getId().equals("done")) {
			currTimer.stopwatch.reset();
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
		if(p.getId().equals("back")) {
			FXMLLoader loader = new FXMLLoader();
			try {
				loader.setLocation(getClass().getResource("../view/PlannerView.fxml"));
				Scene scene = new Scene(loader.load());
				Main.stage.setScene(scene);
				Main.stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
	}
	public void loadNextTask() {

	}
}
