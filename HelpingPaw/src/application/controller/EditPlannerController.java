package application.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;



public class EditPlannerController implements EventHandler<ActionEvent>, Initializable{
		@FXML
	    TextField tfTask;

	    @FXML
	    Button btBack;

	    @FXML
	    Button btAdd;

	    @FXML
	    ListView<String> tblViewNightEP;
	    
	    @FXML
	    ListView<String> tblViewAfternoonEP;

	    @FXML
	    ListView<String> tblViewMorningEP;
	    
	    @FXML
	    ChoiceBox<String> choiceTOD;
	    
	    @FXML
	    Rectangle rectangleEP;

	    @FXML
	    Button btDelete;
	    
	    @FXML
	    ImageView imgCat;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		rectangleEP.setFill(Main.user.color);
		String col = User.colorToString(Main.user.color);
		
		btDelete.setStyle("-fx-background-color: " + col);
		btAdd.setStyle("-fx-background-color: " + col);
		
		File file = new File("src/images/" + Main.user.cat + "talking.png");
		Image catImg = new Image(file.toURI().toString());
		imgCat.setImage(catImg);
	}

	@Override
	public void handle(ActionEvent event) {
		
	}
}
