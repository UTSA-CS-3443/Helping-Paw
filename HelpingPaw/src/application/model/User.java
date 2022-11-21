package application.model;

import javafx.scene.paint.Color;

public class User {
	
	public Color color;
	public String cat;
	
	
	/**
	 * @param color
	 * @param cat
	 */
	public User(Color color, String cat) {
		super();
		this.color = color;
		this.cat = cat;
	}


	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}


	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}


	/**
	 * @return the cat
	 */
	public String getCat() {
		return cat;
	}


	/**
	 * @param cat the cat to set
	 */
	public void setCat(String cat) {
		this.cat = cat;
	}
	
	public static String colorToString(Color color) {
		
		if (color.equals(Color.rgb(255, 214, 218))) {
			return ("#ffd6da");
		}
		else if (color.equals(Color.rgb(255, 220, 157))) {
			return ("#ffdc9d");
		}
		else if (color.equals(Color.rgb(209, 255, 208))) {
			return ("#d1ffd0");
		}
		else if (color.equals(Color.rgb(219, 218, 255))) {
			return ("#dbdaff");
		}
		return null;
	}

}
