package com.wilke.typing;

public class ParameterArity {

	// just in case examples of array initializations
	public static String[] myArray = { "Hello", "Kitty" };
	public static String[] yourArray = new String[] { "Hello", "World" };
	public static String[] yourMomsArray = new String[1];
	
	// but ..!!
	public String[] array;
	public ParameterArity() {
		// won't compile
//		this.array = { "Hello", "Kitty" };
	}
	
	public static void printList(String... list) {
		for (String item : list)
			System.out.print(item);
	}
	
	// works even on the main method !!!
	public static void main(String... args) {
		printList("Hello", " ", "World");
	}
}
