package com.wilke.typing;

public class ParameterArity {

	// just in case example
	public static String[] myArray = { "Hello", "Kitty" };
	
	public static void printList(String... list) {
		for (String item : list)
			System.out.print(item);
	}
	
	// works even on the main method !!!
	public static void main(String... args) {
		printList("Hello", " ", "World");
	}
}
