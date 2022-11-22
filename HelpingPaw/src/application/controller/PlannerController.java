package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class PlannerController implements EventHandler<ActionEvent>, Initializable {

	@FXML
	Rectangle rectangle;
	
	@FXML
	ImageView imgCat;
	
	@FXML
	Text txtCat;
	
	@FXML
	Button btBack;
	
	@FXML 
	Button btTimer;
	
	@FXML
	Button btEdit;
	
	@FXML
	ListView<String> listMorning;
	
	@FXML
	ListView<String> listAfternoon;
	
	@FXML
	ListView<String> listEvening;
	
	ObservableList<String> morningList = FXCollections.observableArrayList();
	ObservableList<String> afternoonList = FXCollections.observableArrayList();
	ObservableList<String> eveningList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		rectangle.setFill(Main.user.color);
		String col = User.colorToString(Main.user.color);
		
		btBack.setStyle("-fx-background-color: " + col);
		btEdit.setStyle("-fx-background-color: " + col);
		btTimer.setStyle("-fx-background-color: " + col);
		
		File file = new File("src/images/" + Main.user.cat + "talking.png");
		Image catImg = new Image(file.toURI().toString());
		imgCat.setImage(catImg);
		
		if (Main.planner.isEmpty()) {
			txtCat.setText("Looks like your planner is empty! Let's add some tasks!");
		}
		
		else {
			txtCat.setText("Let's get to work! Click on a task, then start a timer!");
		}
		
		for (int i = 0; i < Main.planner.size(); i++) {
			if (Main.planner.get(i).getTimeOfDay().equals("morning")) {
				morningList.add(Main.planner.get(i).getName());
			}
			else if (Main.planner.get(i).getTimeOfDay().equals("afternoon")) {
				afternoonList.add(Main.planner.get(i).getName());
			}
			else
				eveningList.add(Main.planner.get(i).getName());
		}
		
		listMorning.setItems(morningList);
		listAfternoon.setItems(afternoonList);
		listEvening.setItems(eveningList);
		
		
		
		
	}

	@Override
	public void handle(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		Button bt = (Button) event.getSource();

		try {
			if (bt.getId().equals("btBack")) {
				loader.setLocation(getClass().getResource("../view/Main.fxml"));	
			}
			
//			if (bt.getId().equals("btEdit")) {
//				loader.setLocation(getClass().getResource("../view/EditPlannerView.fxml"));		
//			}		

			if (bt.getId().equals("btTimer")) {
				loader.setLocation(getClass().getResource("../view/TimerView.fxml"));	
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
