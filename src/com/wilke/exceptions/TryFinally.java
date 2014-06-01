package com.wilke.exceptions;

public class TryFinally {
	
	public static Integer ThrowBeforeFinally() {
		try {
			throw new RuntimeException();
		} finally {
			return 4711_000;
		}
	}
	
	public static Integer ReturnBeforeFinally() throws InterruptedException {
		try {
			return 4711_000;
		} finally {
			System.out.println("Still having control.");
			return 4713_000;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println(ReturnBeforeFinally());
		
		System.out.println(ThrowBeforeFinally());
	}

}
