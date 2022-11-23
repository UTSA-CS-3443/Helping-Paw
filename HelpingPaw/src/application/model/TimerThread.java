package application.model;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;
import java.time.temporal.ChronoUnit;
import com.google.common.base.Stopwatch;

public class TimerThread extends Thread{
	public Stopwatch stopwatch = Stopwatch.createUnstarted();
	long duration = 0;
	long millis = TimeUnit.MILLISECONDS.convert(1L,TimeUnit.SECONDS);
	long min;
	public long currMin = 200;
	public long currSec = 200;
	int x = 0;
	Duration total;
	public TimerThread(long m) {
		this.min = m;
		currMin = min;
		total = Duration.ofMinutes(min);
	}
	public TimerThread(long m , long s) {
		this.min = m;
		this.currMin = min;
		this.currSec = s;
		total = Duration.ofMinutes(min+1);
	}
	public void stopTimer() {
		stopwatch.reset();
	}

	public long[] pauseTimer() {
		stopwatch.reset();
		long[] dur = {currMin,currSec};
		return dur;
	}

	public void run() {
		stopwatch.start();
		
		if(currSec == 200) {
			currSec = 60;
			application.controller.TimerController.updateTime(currMin--+":00");
		}
		else {
			if(currSec>10) {
				System.out.println("Unpaused Timer at " + currMin + ":" + currSec);
				application.controller.TimerController.updateTime(currMin+":"+currSec);
			}
			else {
				application.controller.TimerController.updateTime(currMin+":0"+currSec);
			}
		}
		while(duration < min && stopwatch.isRunning()) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(currSec-1==0 && currMin>0) {
				application.controller.TimerController.updateTime(--currMin+":0"+--currSec);
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

			duration = stopwatch.elapsed(TimeUnit.MINUTES);
		}
	}
}
