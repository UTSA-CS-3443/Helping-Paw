package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import application.model.Task;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;



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
	
	@FXML
	Text txtCat;

	@FXML
	ListView<String> listMorning;

	@FXML
	ListView<String> listAfternoon;

	@FXML
	ListView<String> listEvening;

	@FXML
	TextField txtFieldTask;
	

	@FXML
	ChoiceBox<String> cbTOD = new ChoiceBox<String>();

	ObservableList<String> morningList = FXCollections.observableArrayList();
	ObservableList<String> afternoonList = FXCollections.observableArrayList();
	ObservableList<String> eveningList = FXCollections.observableArrayList();

	public void initialize(URL arg0, ResourceBundle arg1) {
		rectangleEP.setFill(Main.user.color);
		String col = User.colorToString(Main.user.color);

		btDelete.setStyle("-fx-background-color: " + col);
		btAdd.setStyle("-fx-background-color: " + col);

		File file = new File("src/images/" + Main.user.cat + "talking.png");
		Image catImg = new Image(file.toURI().toString());
		imgCat.setImage(catImg);
		if (Main.planner.isEmpty()) {
			txtCat.setText("Type in your task, select a time of day to work on it, then click 'add task'!");
		}
		
		else {
			txtCat.setText("Psst! To delete a task, click on the task, then click 'delete'.");
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

		cbTOD.getItems().addAll("morning", "afternoon", "evening");

	}

	@Override
	public void handle(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		Button bt = (Button) event.getSource();

		try {
			
			if (bt.getId().equals("btBack")) {
				loader.setLocation(getClass().getResource("../view/PlannerView.fxml"));	
				Scene scene = new Scene(loader.load());
				Main.stage.setScene(scene);
				Main.stage.show();
			}

			if (bt.getId().equals("btAdd")) {

				if (cbTOD.getValue() != null && !txtFieldTask.getText().isEmpty()) {
					File file = new File("src/images/" + Main.user.cat + "talking.png");
					Image catImg = new Image(file.toURI().toString());
					imgCat.setImage(catImg);
					
					Task newTask = new Task(txtFieldTask.getText(), cbTOD.getValue());
					Main.planner.add(newTask);
					txtFieldTask.clear();
					addTask(newTask);
					
					txtCat.setText("Your task has been added!");
				}
				
				else {

					File file = new File("src/images/" + Main.user.cat + "wtf.png");
					Image catImg = new Image(file.toURI().toString());
					imgCat.setImage(catImg);
					txtCat.setText("You must select a time of day to proceed, silly goose! Try again.");

				}
			}		

			
			if (bt.getId().equals("btDelete")) {
				//TODO: Get the task that the user has clicked on in the ListView and delete it from Main.planner
				if (listMorning.getSelectionModel().getSelectedItem() != null) {
					deleteTask(listMorning.getSelectionModel().getSelectedItem(), "morning");
					
					txtCat.setText("Your task has been deleted.");
				}

				else if (listAfternoon.getSelectionModel().getSelectedItem() != null) {
					deleteTask(listAfternoon.getSelectionModel().getSelectedItem(), "afternoon");
					
					txtCat.setText("Your task has been deleted.");
				}

				else if (listEvening.getSelectionModel().getSelectedItem() != null) {
					deleteTask(listEvening.getSelectionModel().getSelectedItem(), "evening");
					
					txtCat.setText("Your task has been deleted.");
				}

				listMorning.setItems(morningList);
				listAfternoon.setItems(afternoonList);
				listEvening.setItems(eveningList);

			}


		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	

	public void addTask(Task t) {

		if (t.getTimeOfDay().equals("morning")) {
			morningList.add(t.getName());
		}
		else if (t.getTimeOfDay().equals("afternoon")) {
			afternoonList.add(t.getName());
		}
		else
			eveningList.add(t.getName());

		listMorning.setItems(morningList);
		listAfternoon.setItems(afternoonList);
		listEvening.setItems(eveningList);
		
		sortPlanner();

	}
	
	
	public void deleteTask(String task, String timeOfDay) {
		for (int i = 0; i < Main.planner.size(); i++) {
			if (Main.planner.get(i).getName().equals(task)) {
				Main.planner.remove(i);
			}
		}
		if (timeOfDay.equals("morning")) {
			morningList.remove(task);
		}
		
		if (timeOfDay.equals("afternoon")) {
			afternoonList.remove(task);
		}
		
		else {
			eveningList.remove(task);
		}
			
	}
	
	//Sorts planner by time of day
	public void sortPlanner() {
		ArrayList<Task> sortedPlanner = new ArrayList<Task>();
		for (int i = 0; i < Main.planner.size(); i++) {
			
			if (Main.planner.get(i).getTimeOfDay().equals("morning"))
				sortedPlanner.add(Main.planner.get(i));
		}
		
		for (int i = 0; i < Main.planner.size(); i++) {
			
			if (Main.planner.get(i).getTimeOfDay().equals("afternoon"))
				sortedPlanner.add(Main.planner.get(i));
		}
		
		for (int i = 0; i < Main.planner.size(); i++) {
			
			if (Main.planner.get(i).getTimeOfDay().equals("evening"))
				sortedPlanner.add(Main.planner.get(i));
		}
		
		Main.planner = sortedPlanner;
	}
}
