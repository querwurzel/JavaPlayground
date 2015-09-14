package com.wilke.typing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XVariance {
	
	/**
	 * Unbounded wildcard generic type:
	 * Implementation must be completely independent of the type
	 * since no assumptions can be made about the type (except inheriting from Object).
	 */
	public static int numElementsInCommon(List<?> l1, List<?> l2) {
		return Math.min(l1.size(), l2.size());
	}
	
	/**
	 * Now at least the assumption that the collections share the same type can be made!
	 */
	public static <T> List<T> numElementsWithSameTypeInCommon(List<T> l1, List<T> l2) {
		int minLength = Math.min(l1.size(), l2.size());
		List<T> result = new ArrayList<>();
		
		for (int idx = 0; idx < minLength; idx++) {
			result.add(l1.get(idx));
			result.add(l2.get(idx));
		}
		
		return result;
	}
	
	/**
	 * Method cannot have same name as the generic type information in Java are erased at runtime.
	 * Hence this method would have the same signature as the method above.
	 */
//	public static List<?> numElementsWithSameTypeInCommon(List<?> l1, List<?> l2) {
//		return null;
//	}
	
	public static void covariantArrays() {
		Object[] objects = { new Object() }; // array literal
		String[] strings = { "Item" };
		
		objects = strings; // arrays are covariant
		
		assert objects == strings;
	}
	
	// Just an example of how one can restrict a generic type on multiple interfaces.
	public static class RestrictiveGeneric<T extends Object & Runnable & Serializable> {
		public T instance;
	}
	
	public static void main(String[] args) {
		covariantArrays();
		
		// wont work, primitives cannot be used as generic type
//		List<int> numbers = new ArrayList<>();

		// ugly but since arrays are objects ..
		List<int[]> numbers = new ArrayList<>();
		
		
		
		List<Object> stack = new ArrayList<>();
		// generics are invariant
//		stack = new ArrayList<Integer>();
		
		/**
		 * PECS mnemonics
		 * (Producer extends, Consumer super)
		 */
		
		// declare a generic type covariant on the generic type
		List<?> covariant = new ArrayList<Integer>(Arrays.asList(new Integer(4711)));
		
		// won't compile
//		Integer it1 = covariant.get(0);
		// any object of the list can only be assumed to be at least of type Object
		Object item = covariant.get(0);
		
		List<? super Integer> contravariant = new ArrayList<Object>();
		
		// won't compile
//		contravariant.add(new Object());
		// only objects of type Integer (the superclass) can be added to the list as type safety is guaranteed
		contravariant.add(new Integer(4711));
//		contravariant.add(new Object()); // nope!
	}
}
