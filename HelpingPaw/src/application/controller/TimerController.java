package application.controller;

import java.io.File;
import java.io.IOException;

import application.Main;
import application.model.PawThread;
import application.model.TimerThread;
import application.model.User;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

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
	Button reset;
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
	Timeline timeline;
	public static boolean doneP = false;
	static TimerThread currTimer;
	PawThread pt = new PawThread();
	public void initialize() {
		pb.setId("pb");
		pt.start();
		String col = User.colorToString(Main.user.color);
		pb.setStyle(" -fx-progress-color: " + col +"; -fx-progress-fill:white;");
		timer.setText("");
		resume.setVisible(false);
		pause.setVisible(false);
		done.setVisible(false);
		reset.setVisible(false);
		rectangleTV.setFill(Main.user.color);

		pause.setStyle("-fx-background-color: " + col);
		reset.setStyle("-fx-background-color: " + col);
		start.setStyle("-fx-background-color: " + col);
		done.setStyle("-fx-background-color: " + col);
		resume.setStyle("-fx-background-color: " + col);


		String kitty = "/images/" + Main.user.cat + "talking.png";
		Image catImg = new Image(this.getClass().getResourceAsStream(kitty));
		imgCat.setImage(catImg);
		txtCat.setText("Click start button when you are ready, and remember try your best! You got this!");
		title.setText(Main.currTask);

		//get the name of the task to display on the timer view

	}
	public static void updateTime(String timeLeft) {
		try {
			if(currTimer.isRunning()) {
				Node ts = Main.stage.getScene().lookup("#timer");
				if(ts != null && timeLeft!=null) {
					((Text) ts).setText(timeLeft);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateCat(String t) {
		Node ts = Main.stage.getScene().lookup("#txtCat");
		if(ts != null) {
			((Text) ts).setText(t);
		}
	}
	public static void resetBt(){
		Node r = Main.stage.getScene().lookup("#reset");
		if(r != null) {
			((Button) r).setVisible(true);
		}
		Node p = Main.stage.getScene().lookup("#pause");
		if(p != null) {
			((Button) p).setVisible(false);
		}
	}
	public void doneBtPressed(){
		doneP = true;
	}
	@Override
	public void handle(ActionEvent event) {
		Button p = (Button) event.getSource();
		if(p.getId().equals("start")) {
			doneP = false;
			reset.setVisible(false);
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
			txtCat.setText("");
			currTimer.start();
			back.setVisible(false);
			pb.setProgress(1);
			timeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(pb.progressProperty(), 1)),
					new KeyFrame(Duration.seconds(currTimer.totalSec), new KeyValue(pb.progressProperty(), 0)));
			timeline.setCycleCount(1);
			timeline.play();
		}
		if(p.getId().equals("pause")) {
			doneBtPressed();
			timeline.stop();
			reset.setVisible(false);
			currTimer.stopwatch.stop();
			pb.setProgress(1-(currTimer.calcSec/currTimer.totalSec));
			long[] tim = currTimer.pauseTimer();
			TimerThread t = new TimerThread(tim[0],tim[1],currTimer.totalSec,currTimer.calcSec);
			currTimer = t;
			pause.setVisible(false);
			done.setVisible(false);
			resume.setVisible(true);
			back.setVisible(true);
		}
		else if(p.getId().equals("done")) {
			reset.setVisible(false);
			doneBtPressed();
			currTimer.stopwatch.reset();
			timer.setVisible(false);
			input.setVisible(true);
			zero.setVisible(true);
			pause.setVisible(false);
			done.setVisible(false);
			resume.setVisible(false);
			start.setVisible(true);
			pb.setProgress(0.0);
			back.setVisible(true);
			title.setText(currTimer.loadNextTask(Main.currTask));
			timeline.stop();
			if(title.getText().equals("Done with all tasks today!")) {
				start.setVisible(false);
				input.setVisible(false);
				zero.setVisible(false);
				txtCat.setText("Great work!");
			}
			else {
				txtCat.setText(pt.getMotivation());
			}

		}
		else if(p.getId().equals("reset")) {
			txtCat.setText("Click start button when you are ready, and remember try your best! You got this!");
			reset.setVisible(false);
			currTimer.stopwatch.reset();
			timer.setVisible(false);
			input.setVisible(true);
			zero.setVisible(true);
			pause.setVisible(false);
			done.setVisible(false);
			resume.setVisible(false);
			start.setVisible(true);
			pb.setProgress(0.0);
			back.setVisible(true);
			title.setText(Main.currTask);
			timeline.stop();
		}
		else if(p.getId().equals("resume")) {
			timeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(pb.progressProperty(), pb.getProgress())),
					new KeyFrame(Duration.seconds(currTimer.totalSec), new KeyValue(pb.progressProperty(), 0)));
			timeline.setCycleCount(1);
			timeline.play();
			reset.setVisible(false);
			done.setVisible(true);
			resume.setVisible(false);
			pause.setVisible(true);
			doneP = false;
			currTimer.start();
			timeline.play();
		}
		if(p.getId().equals("back")) {
			FXMLLoader loader = new FXMLLoader();
			try {
				loader.setLocation(this.getClass().getResource("/application/view/PlannerView.fxml"));
				Scene scene = new Scene(loader.load());
				Main.stage.setScene(scene);
				Main.stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


}
