package application.controller;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class MainController implements EventHandler<ActionEvent>, Initializable {

	@FXML
	Rectangle rectangle;

	@FXML
	ImageView imgCat;

	@FXML
	Button btPlanner;

	@FXML
	Button btOptions;

	@FXML
	Button btExit;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		rectangle.setFill(Main.user.color);
		String col = User.colorToString(Main.user.color);

		btPlanner.setStyle("-fx-background-color: " + col);
		btOptions.setStyle("-fx-background-color: " + col);
		btExit.setStyle("-fx-background-color: " + col);

		File file = new File("src/images/" + Main.user.cat + ".jpeg");
		Image catImg = new Image(file.toURI().toString());
		imgCat.setImage(catImg);

	}

	@Override
	public void handle(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		Button bt = (Button) event.getSource();

		try {
			if (bt.getId().equals("btOptions")) {
				loader.setLocation(getClass().getResource("../view/OptionsView.fxml"));	
			}
			//MyPlanner is temporarily TimerView for testing purposes
			if (bt.getId().equals("btPlanner")) {
				loader.setLocation(getClass().getResource("../view/PlannerView.fxml"));		
			}		

			if (bt.getId().equals("btExit")) {
				System.exit(0);
			}
			
			Scene scene = new Scene(loader.load());
			Main.stage.setScene(scene);
			Main.stage.show();

		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}

