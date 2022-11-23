package application.model;

import java.util.concurrent.TimeUnit;
import com.google.common.base.Stopwatch;

public class TimerThread extends Thread{
	public Stopwatch stopwatch = Stopwatch.createUnstarted();
	long duration = 0;
	long min;
	public long currMin = 200;
	public long currSec = 200;
	public Double totalSec;
	public Double calcSec = 0.0;
	
	public boolean isRunning() {
		return stopwatch.isRunning();
	}
	/**
	 * @return the totalSec
	 */
	public Double getTotalSec() {
		return totalSec;
	}
	/**
	 * @param totalSec
	 */
	public void setTotalSec(Double totalSec) {
		this.totalSec = totalSec;
	}
	/**
	 * @return seconds passed
	 */
	public Double getCalcSec() {
		return calcSec;
	}
	/**
	 * @param seconds passed
	 */
	public void setCalcSec(Double calcSec) {
		this.calcSec = calcSec;
	}
	public TimerThread(long m) {
		this.min = m;
		currMin = min;
		totalSec = (double) (m*60);
		System.out.println(totalSec);
	}
	public TimerThread(long m , long s, Double t, Double c) {
		this.min = m;
		this.currMin = min;
		this.currSec = s;
		totalSec= t;
		calcSec = c;
		System.out.println(totalSec);
	}
	public void stopTimer() {
		stopwatch.reset();
	}

	public long[] pauseTimer() {
		stopwatch.reset();
		long[] dur = {currMin,currSec};
		return dur;
	}
	public Double getProgress() {
		return 1 - (calcSec/totalSec);
	}
	public void run() {
		stopwatch.start();
		
		if(currSec == 200) {
			currSec = 60;
			application.controller.TimerController.updateTime(currMin--+":00",getProgress());
		}
		else {
			if(currSec>10) {
				application.controller.TimerController.updateTime(currMin+":"+currSec,getProgress());
			}
			else {
				application.controller.TimerController.updateTime(currMin+":0"+currSec,getProgress());
			}
		}
		while(duration < min && stopwatch.isRunning()) {
			try {
				TimeUnit.SECONDS.sleep(1);
				System.out.println(calcSec);
				calcSec++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(currSec-1==0 && currMin>0) {
				application.controller.TimerController.updateTime(--currMin+":0"+--currSec,getProgress());
				currSec = 60;
			}
			else {
				if(currSec < 11) {
					application.controller.TimerController.updateTime(currMin+":0"+--currSec,getProgress());
				}
				else {
					application.controller.TimerController.updateTime(currMin+":"+--currSec,getProgress());
				}
			}

			duration = stopwatch.elapsed(TimeUnit.MINUTES);
		}
	}
}
