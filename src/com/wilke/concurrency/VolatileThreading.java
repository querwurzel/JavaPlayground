package com.wilke.concurrency;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class VolatileThreading {

	private final static Logger logger = Logger.getLogger(VolatileThreading.class.getName());
	
	// volatile ensures visibility but not mutual exclusion
	private static volatile boolean start;
	private static volatile boolean shutdown;
	private static volatile long counter;

	static {
		try {
			// JVM parameter: "-Djava.util.logging.SimpleFormatter.format=%4$s: %5$s%n"
			FileHandler fh = new FileHandler("/tmp/out.log", false);
			fh.setFormatter(new SimpleFormatter());
			logger.addHandler(fh);
			logger.setLevel(Level.INFO); // min log level
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}
	
	// The cheap read-write pattern (performs when reads greatly outnumber writes)
	public static long getCounter() {
		return counter;
	}
	
	public static synchronized long increaseCounter() {
		return ++counter;
	}
	
	static class Worker implements Runnable {
		private static long idSequence = 1;
		private final long  id;
		private long sum;
		
		public Worker() {
			this.id = idSequence++;
		}
		
		@Override
		public void run() {
			while (true) {
				if (shutdown) {
					if (this.id % 10 == 0) // every 10th thread
						logger.info("Thread #" + this.id + " increased the counter " + this.sum + "-times");
					else
						logger.info("Thread #" + this.id + " summed up to " + this.sum);
					return;
				}
				if (start) {
					if (this.id % 10 == 0) { // every 10th thread
						increaseCounter();
						this.sum++;
					} else {
						this.sum += getCounter();
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		//System.setOut(new PrintStream(new FileOutputStream("/tmp/out.log")));
		
		for (int idx = 0; idx < 100; idx++) {
			Thread thread = new Thread(new Worker());
			thread.setPriority(Thread.MIN_PRIORITY);
			thread.setDaemon(true);
			thread.start();
		}
		
		TimeUnit.SECONDS.sleep(3);
		
		VolatileThreading.start = true;
		TimeUnit.SECONDS.sleep(5);
		
		VolatileThreading.start = false;
		TimeUnit.SECONDS.sleep(3);
		
		VolatileThreading.shutdown = true;

		TimeUnit.SECONDS.sleep(3);
		
		logger.info("Result: " + VolatileThreading.counter); // sum of all 10 counting threads
	}
}
