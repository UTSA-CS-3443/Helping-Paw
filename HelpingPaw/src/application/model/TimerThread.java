package application.model;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

import application.Main;

public class TimerThread extends Thread{
	public Stopwatch stopwatch = Stopwatch.createUnstarted();
	long duration = 0;
	public long min;
	public long currMin = 200;
	public long currSec = 200;
	public Double totalSec;
	public Double calcSec = 0.0;
	public boolean isRunning() {
		return stopwatch.isRunning();
	}

	public TimerThread(long m) {
		this.min = m;
		currMin = min;
		totalSec = (double) (m*60);
	}
	public TimerThread(long m , long s, Double t, Double c) {
		this.min = m;
		this.currMin = min;
		this.currSec = s;
		totalSec = (double) ((m*60)+s);
		calcSec = c;
	}
	public void stopTimer() {
		stopwatch.reset();
	}

	public long[] pauseTimer() {
		stopwatch.reset();
		long[] dur = {currMin,currSec};
		return dur;
	}
	public String loadNextTask(String name) {
		Iterator<Task> i =  Main.planner.iterator();
		while(i.hasNext()) {
			Task t = i.next();
			if(name.equals(t.getName())) {
				if(i.hasNext()) {
					Task nt = i.next();
					Main.currTask = nt.getName();
					removeTask(name);
					return Main.currTask;
				}
			}
		}
		if(Main.planner.size() > 1) {
			Main.currTask = Main.planner.get(0).getName();
			removeTask(name);
			return Main.currTask;
		}
		removeTask(name);
		return "Done with all tasks today!";
	}
	public void removeTask(String name) {
		for(int i = 0; i < Main.planner.size();i++) {
			if(name.equals(Main.planner.get(i).getName())) {
				Main.planner.remove(i);
			}
		}
	}

	@Override
	public void run() {
		stopwatch.start();

		if(currSec == 200) {
			currSec = 60;
			application.controller.TimerController.updateTime(currMin--+":00");
		}
		else {
			if(currSec>10) {
				application.controller.TimerController.updateTime(currMin+":"+currSec);
			}
			else {
				application.controller.TimerController.updateTime(currMin+":0"+currSec);
			}
		}
		while(duration < totalSec && stopwatch.isRunning()) {
			try {
				TimeUnit.SECONDS.sleep(1);
				calcSec++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(currSec-1==0 && currMin>0) {
				application.controller.TimerController.updateTime(currMin--+":0"+--currSec);
				currSec = 60;
			}
			else {
				if(currSec < 11) {
					application.controller.TimerController.updateTime(currMin+":0"+--currSec);
				}
				else {
					application.controller.TimerController.updateTime(currMin+":"+--currSec);
				}
			}

			duration = stopwatch.elapsed(TimeUnit.SECONDS);
		}
		if(!application.controller.TimerController.doneP) {
			application.controller.TimerController.updateCat("Need more time? Hit the reset button");
			application.controller.TimerController.resetBt();
		}
	}
}
