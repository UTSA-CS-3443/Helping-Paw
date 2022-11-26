package application.model;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;
public class PawThread extends Thread{
	public Stopwatch stopwatch = Stopwatch.createStarted();
	long runtime = 0;

	private int getRandomNum(int max, int min) {
		int random = (int) (Math.random() * (max - min + 1) + min);
		return random;
	}

	public String getMotivation() {
		String[] arr = {"Good work!", "Keep going!"
				, "You're doing great!","Onward soldier."
				, "Another task slain!", "Productivity master!"
				, "Amazing!", "You got this!"
				,"GG EZ"};
		return arr[getRandomNum(8,0)];
	}

	public String breakReminder(long h) {
		String[] arr = {"You've been working for "," hours. Maybe take a break?"};
		String p = "";
		if(h>7) {
			p = arr[0]+h+ " hours. Time to take a break! That's an order!";
		}
		else {
			p = arr[0]+h+arr[1];
		}
		return p;
	}
	public String pawReminder() {
		String[] arr = {"Reminder to drink water!", "Stretch break!",
				"Life is like a box of chocolates", "Dreams don't work unless you do!",
		"Have you eaten yet?", "Success is the sum of small efforts, repeated day in and day out. - Robert Collier",
		"Striving for success without hard work is like trying to harvest where you haven’t planted. - David Bly",
		"And why do we fall, Bruce? So we can learn to pick ourselves up. - Thomas Wayne"};
		return arr[getRandomNum(7,0)];
	}
	@Override
	public void run() {
		while(stopwatch.isRunning()) {
			try {
				TimeUnit.HOURS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			runtime = stopwatch.elapsed(TimeUnit.HOURS);
			application.controller.TimerController.updateCat(pawReminder());
			if(runtime > 3) {
				application.controller.TimerController.updateCat(breakReminder(runtime));
			}

		}
	}
}
