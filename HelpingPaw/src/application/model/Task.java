package application.model;

public class Task {

	private String name;
	private String timeOfDay;

	/**
	 * @param name
	 * @param timeOfDay
	 */
	public Task(String name, String timeOfDay) {
		super();
		this.name = name;
		this.timeOfDay = timeOfDay;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the timeOfDay
	 */
	public String getTimeOfDay() {
		return timeOfDay;
	}

	/**
	 * @param timeOfDay the timeOfDay to set
	 */
	public void setTimeOfDay(String timeOfDay) {
		this.timeOfDay = timeOfDay;
	}


}
