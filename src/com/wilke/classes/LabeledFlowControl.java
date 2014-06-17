package com.wilke.classes;

public class LabeledFlowControl {

	public static final String[] array = { "Hello", " ", "Kitty" };
	
	public static void main(String[] args) {
		outerFlow:
			// for each string
			for(int i1 = 0; i1 < array.length; i1++) {
				innerFlow:
				// for each character
				for (int i2 = 0; i2 < array[i1].length(); i2++) {
					if (array[i1].charAt(i2) == 'y') {
						System.out.println("y found at position " + (i2 + 1) + " in string " + (i1 + 1));
						break outerFlow;
					} else {
						continue innerFlow; // not necessary
					}
				}
			}
	}

}
