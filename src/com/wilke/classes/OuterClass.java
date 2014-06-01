package com.wilke.classes;

import com.wilke.classes.OuterClass.NonStaticNestedClass;
import com.wilke.classes.OuterClass.StaticNestedClass;

public class OuterClass {
	
	private String secret = "4711";

	public static class StaticNestedClass {
		@Override
		public String toString() {
			return "4713"; // no access to outer class private members
		}
	}
	
	// so-called "inner class"
	public class NonStaticNestedClass {
		@Override
		public String toString() {
			return secret; // access to outer class private members
		}
	}
	
	public NonStaticNestedClass anonymous = new NonStaticNestedClass() {
		@Override
		public String toString() {
			return secret;
		}
	};
	
	@Override
	public String toString() {
		return new NonStaticNestedClass().toString();
	}
}

/**
 * I cannot be public since there must only be one public class within a file, the one whose name matches the file's name.
 * Don't use me. I am just tolerated by the javac but may be implemented differently in different JVM's.
 */
class NonPublicTopLevelClass {
	public static void main(String[] args) {
		// instance of static nested class
		StaticNestedClass sn = new StaticNestedClass();
		
		System.out.println("StaticNested: " + sn);
		
		// instance of non static nested class; depends on an OuterClass instance
		NonStaticNestedClass nsn = new OuterClass().new NonStaticNestedClass();
		
		System.out.println("Inner Class: " + nsn);
		
		System.out.println("Anonymous Inner Class: " + new OuterClass().anonymous);
		
	}
}