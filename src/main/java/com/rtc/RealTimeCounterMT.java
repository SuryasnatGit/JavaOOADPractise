package com.rtc;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLongArray;

/**
 * Design threadsafe real time counter
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/multithreaded/RealTimeCounter.java
 * 
 * Develop a software to count number of events in last 5 mins. You have to
 * support two apis
 * 
 * 1) addEvent() -> It means increment event by 1
 * 
 * 2) getTotalEvents() -> Return total number of events in last 5 mins
 *
 * Program should support millions of events every minute and should also
 * provide multi-threading support
 *
 * This class might not have 100% accuracy as far as events in last 5 mins are
 * concerned. Since we are using circular queue last second information may not
 * be very accurate.
 * 
 * Category : Hard
 *
 */
public class RealTimeCounterMT {

	private final static int GRANULARITY = 300;
	private AtomicLongArray counter = new AtomicLongArray(GRANULARITY);

	private volatile int pos = 0;

	private RealTimeCounterMT() {
		PositionUpdater positionUpdater = new PositionUpdater(this);
		positionUpdater.start();
	}

	// singleton pattern of creation
	private static volatile RealTimeCounterMT INSTANCE;

	public static RealTimeCounterMT getInstance() {
		if (INSTANCE == null) {
			synchronized (RealTimeCounterMT.class) {
				if (INSTANCE == null)
					INSTANCE = new RealTimeCounterMT();
			}
		}
		return INSTANCE;
	}

	public long getTotalEvents() {
		int total = 0;
		for (int i = 0; i < GRANULARITY; i++) {
			total += counter.get(i);
		}
		return total;
	}

	public void addEvent() {
		counter.getAndIncrement(pos);
	}

	void incrementPosition() {
		// first reset the value to 0 at next counter location.
		counter.set((pos + 1) % GRANULARITY, 0);
		pos = (pos + 1) % GRANULARITY;
	}

	class PositionUpdater extends TimerTask {

		private final RealTimeCounterMT realTimeCounter;
		private final Timer timer = new Timer(true);
		private static final int DELAY = 1000;

		PositionUpdater(RealTimeCounterMT realTimeCounter) {
			this.realTimeCounter = realTimeCounter;
		}

		public void start() {
			timer.schedule(this, DELAY);
		}

		@Override
		public void run() {
			realTimeCounter.incrementPosition();
		}
	}

	public static void main(String args[]) {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		final RealTimeCounterMT realTimeCounter = RealTimeCounterMT.getInstance();
		final Random random = new Random();
		final int TOTAL_EVENTS = 10000;
		final CountDownLatch countDownLatch = new CountDownLatch(TOTAL_EVENTS);
		for (int i = 0; i < TOTAL_EVENTS; i++) {
			executor.execute(() -> {
				realTimeCounter.addEvent();
				try {
					Thread.sleep(random.nextInt(10));
				} catch (Exception e) {
					e.printStackTrace();
				}
				countDownLatch.countDown();
			});
		}
		try {
			countDownLatch.await();
		} catch (Exception e) {

		}
		System.out.println(realTimeCounter.getTotalEvents());
		executor.shutdownNow();
	}

}
