package application.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class PlannerController implements EventHandler<ActionEvent>, Initializable {

	@FXML
	Rectangle rectangle;
	
	@FXML
	ImageView imgCat;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		rectangle.setFill(Main.user.color);
		
		File file = new File("src/images/" + Main.user.cat + ".jpeg");
		Image catImg = new Image(file.toURI().toString());
		imgCat.setImage(catImg);
		
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	
}
