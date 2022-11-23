package application;
	
import java.util.ArrayList;

import application.model.Task;
import application.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Main extends Application {
	
	public static User user = new User(Color.rgb(255, 214, 218), "phil");
	public static ArrayList<Task> planner = new ArrayList<Task>();
	public static Stage stage; 
	public static String currTask = "";
	
	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/Main.fxml"));

			AnchorPane layout = (AnchorPane) loader.load();

			Scene scene = new Scene(layout);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
