package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.Task;
import application.model.User;
import javafx.application.Application;
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

		btEdit.setStyle("-fx-background-color: " + col);
		btTimer.setStyle("-fx-background-color: " + col);

		String kitty = "/images/" + Main.user.cat + "talking.png";
		Image catImg = new Image(this.getClass().getResourceAsStream(kitty));
		imgCat.setImage(catImg);

		if (Main.planner.isEmpty()) {
			txtCat.setText("Looks like your planner is empty! Let's add some tasks!");
		}

		else {
			txtCat.setText("Let's get to work! Click on a task, then start a timer!");
		}

		for (Task element : Main.planner) {
			if (element.getTimeOfDay().equals("morning")) {
				morningList.add(element.getName());
			}
			else if (element.getTimeOfDay().equals("afternoon")) {
				afternoonList.add(element.getName());
			}
			else
				eveningList.add(element.getName());
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
				loader.setLocation(this.getClass().getResource("/application/view/Main.fxml"));
				Scene scene = new Scene(loader.load());
				Main.stage.setScene(scene);
				Main.stage.show();
			}

			if (bt.getId().equals("btEdit")) {
				loader.setLocation(this.getClass().getResource("/application/view/EditPlannerView.fxml"));
				Scene scene = new Scene(loader.load());
				Main.stage.setScene(scene);
				Main.stage.show();
			}

			if (bt.getId().equals("btTimer")) {
				if (listMorning.getSelectionModel().getSelectedItem() != null) {
					Main.currTask = listMorning.getSelectionModel().getSelectedItem();

					loader.setLocation(this.getClass().getResource("/application/view/TimerView.fxml"));
					Scene scene = new Scene(loader.load());
					Main.stage.setScene(scene);
					Main.stage.show();
				}

				else if (listAfternoon.getSelectionModel().getSelectedItem() != null) {
					Main.currTask = listAfternoon.getSelectionModel().getSelectedItem();

					loader.setLocation(this.getClass().getResource("/application/view/TimerView.fxml"));
					Scene scene = new Scene(loader.load());
					Main.stage.setScene(scene);
					Main.stage.show();
				}

				else if (listEvening.getSelectionModel().getSelectedItem() != null) {
					Main.currTask = listEvening.getSelectionModel().getSelectedItem();

					loader.setLocation(this.getClass().getResource("/application/view/TimerView.fxml"));
					Scene scene = new Scene(loader.load());
					Main.stage.setScene(scene);
					Main.stage.show();
				}

				else {
					txtCat.setText("Select a task first before you start a timer!");

				}
			}

		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

	//checks if something is selected in any of the listViews
	public boolean isSelected() {
		if (listMorning.getSelectionModel().getSelectedItem() == null && listAfternoon.getSelectionModel().getSelectedItem() == null &&
				listEvening.getSelectionModel().getSelectedItem() == null) {
			return false;
		}
		return true;
	}

}
