package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class OptionsController implements EventHandler<MouseEvent>, Initializable {
	
	@FXML
	Rectangle rectangle;
	
	@FXML
	Circle cirPink;
	
	@FXML
	Circle cirOrange;
	
	@FXML
	Circle cirGreen;
	
	@FXML
	Circle cirPurple;
	
	@FXML
	Text txtPhil;
	
	@FXML
	Text txtEd;
	
	@FXML
	Text txtSalem;
	
	@FXML
	ImageView imgPhil;
	
	@FXML
	ImageView imgEd;
	
	@FXML
	ImageView imgSalem;
	
	@FXML
	Button btBack;
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		rectangle.setFill(Main.user.color);

		
		if (Main.user.color.equals(Color.rgb(255, 214, 218))) {
			cirPink.setStroke(Color.web("#836b60"));
		}
		else if (Main.user.color.equals(Color.rgb(255, 220, 157))) {
			cirOrange.setStroke(Color.web("#836b60"));
		}
		else if (Main.user.color.equals(Color.rgb(209, 255, 208))) {
			cirGreen.setStroke(Color.web("#836b60"));
		}
		else if (Main.user.color.equals(Color.rgb(219, 218, 255))) {
			cirPurple.setStroke(Color.web("#836b60"));
		}
		
		switch (Main.user.cat) {
			case "phil": 
				txtPhil.setStroke(Main.user.color);
				break;
			case "ed":
				txtEd.setStroke(Main.user.color);
				break;
			case "salem":
				txtSalem.setStroke(Main.user.color);
				break;
			default:
				break;		
		}
		
	}

	@Override
	public void handle(MouseEvent event) {
		if (event.getSource().equals(cirPink) || event.getSource().equals(cirOrange) || 
			event.getSource().equals(cirGreen) || event.getSource().equals(cirPurple)) {
			Circle cir = (Circle) event.getSource();
			
			if (cir.getId().equals("cirPink")) {
				Main.user.color = (Color.rgb(255, 214, 218));
				
				cirPink.setStroke(Color.web("#836b60"));
				cirOrange.setStroke(Color.TRANSPARENT);
				cirGreen.setStroke(Color.TRANSPARENT);
				cirPurple.setStroke(Color.TRANSPARENT);
				
				rectangle.setFill(Main.user.color);
				
			}
			
			if (cir.getId().equals("cirOrange")) {
				Main.user.color = (Color.rgb(255, 220, 157));
				
				cirPink.setStroke(Color.TRANSPARENT);
				cirOrange.setStroke(Color.web("#836b60"));
				cirGreen.setStroke(Color.TRANSPARENT);
				cirPurple.setStroke(Color.TRANSPARENT);
				
				rectangle.setFill(Main.user.color);
			}
			
			if (cir.getId().equals("cirGreen")) {
				Main.user.color = (Color.rgb(209, 255, 208));
				
				cirPink.setStroke(Color.TRANSPARENT);
				cirOrange.setStroke(Color.TRANSPARENT);
				cirGreen.setStroke(Color.web("#836b60"));
				cirPurple.setStroke(Color.TRANSPARENT);
				
				rectangle.setFill(Main.user.color);
				
			}
			
			if (cir.getId().equals("cirPurple")) {
				Main.user.color = (Color.rgb(219, 218, 255));
				
				cirPink.setStroke(Color.TRANSPARENT);
				cirOrange.setStroke(Color.TRANSPARENT);
				cirGreen.setStroke(Color.TRANSPARENT);
				cirPurple.setStroke(Color.web("#836b60"));
				
				rectangle.setFill(Main.user.color);
				
			}
		}
		
		else if (event.getSource().equals(imgPhil)) {
			Main.user.cat = "phil";
			txtPhil.setStroke(Main.user.color);
			txtEd.setStroke(Color.TRANSPARENT);
			txtSalem.setStroke(Color.TRANSPARENT);
		}
		
		else if (event.getSource().equals(imgEd)) {
			Main.user.cat = "ed";
			txtPhil.setStroke(Color.TRANSPARENT);
			txtEd.setStroke(Main.user.color);
			txtSalem.setStroke(Color.TRANSPARENT);
		}
		
		else if (event.getSource().equals(imgSalem)) {
			Main.user.cat = "salem";
			txtPhil.setStroke(Color.TRANSPARENT);
			txtEd.setStroke(Color.TRANSPARENT);
			txtSalem.setStroke(Main.user.color);
		}
		
		else if (event.getSource().equals(btBack)) {
			try {

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../view/Main.fxml"));		
				
				Scene scene = new Scene(loader.load());
				
				Main.stage.setScene(scene);
				Main.stage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}	
		
	}
		

}
