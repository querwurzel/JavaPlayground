package com.wilke.concurrency;

import java.util.concurrent.TimeUnit;

public class ThisEscape {
	private final String name;

	public ThisEscape() throws InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int idx = 0; idx < 1000; idx++)
					System.out.println(name); // access to outer class properties
			}
		}).start();

		// make the "window of vulnerability" large
		// Note: effect heavily depends on your hardware
		TimeUnit.MICROSECONDS.sleep(100);

		this.name = "Hello World";
	}

	public static void main(final String[] args) throws InterruptedException {

		new ThisEscape();

	}
}
