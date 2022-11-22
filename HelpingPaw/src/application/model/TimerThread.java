package application.model;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

public class TimerThread extends Thread {
	Stopwatch stopwatch = Stopwatch.createUnstarted();
	long duration = 0;
	long millis = TimeUnit.MILLISECONDS.convert(1L,TimeUnit.SECONDS);
	long min;
	long currMin = 200;
	long currSec = 200;
	public TimerThread(long m) {
		this.min = m;
	}
	public TimerThread(long m , long s) {
		this.min = m;
		this.currSec = s;
	}
	public void stopTimer() {
		stopwatch.reset();
	}

	public long[] pauseTimer() {
		stopwatch.stop();
		long totalSec = stopwatch.elapsed(TimeUnit.SECONDS);
		currMin = totalSec/60;
		currSec = totalSec%60;
		long[] dur = {currMin,currSec};
		stopwatch.reset();
		return dur;
	}
	public void run() {
		if(currSec == 200) {
			currMin = min-1;
			currSec = 60;
			stopwatch.start();
			application.controller.TimerController.updateTime(min+":00");
		}
		else {
			stopwatch.start();
			if(currSec>10) {
				application.controller.TimerController.updateTime(min+":"+currSec);
			}
			else {
				application.controller.TimerController.updateTime(min+":0"+currSec);
			}
		}
		while(duration < min) {
			try {
				Thread.sleep(millis);
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
		stopwatch.stop(); // optional
	}
}
