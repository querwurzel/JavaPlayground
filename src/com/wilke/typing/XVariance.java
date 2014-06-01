package com.wilke.typing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XVariance {
	
	/**
	 * Implementation must be completely independent of the generic type
	 * because no assumptions can be made about it.
	 */
	public static List<?> numElementsInCommon(List<?> pre, List<?> post) {
		int number = Math.min(pre.size(), post.size());
		List<?> result = new ArrayList<>();
		
		for (int idx = 0; idx < number; idx++)
			result.add(null);
		
		return result;
	}
	
	/**
	 * Now at least the assumption that the collections share the same type can be made!
	 * Method cannot have same name as the <?> variant as Java generics are erased at runtime
	 * and so the methods would share the same signature during runtime!
	 */
	public static <T> List<T> numElementsWithSameTypeInCommon(List<T> pre, List<T> post) {
		int number = Math.min(pre.size(), post.size());
		List<T> result = new ArrayList<>();
		
		for (int idx = 0; idx < number; idx++)
			result.add(pre.get(idx));
		
		return result;
	}
	
	public static void covariantArrays() {
		Object[] objects = new Object[] { new Object() };
		String[] strings = new String[] { new String("Item")};
		
		objects = strings; // arrays are covariant
		
		for (int idx = 0; idx < objects.length; idx++)
			System.out.println(objects[idx]);
	}
	
	public static void main(String[] args) {
		covariantArrays();
		
		List<Object> stack = new ArrayList<>();
		// generics are invariant
//		stack = new ArrayList<Integer>();
		
		// wont work, primitives cannot be used as generic type
//		List<int> numbers = new ArrayList<>();

//		// ugly but since arrays are objects ..
		List<int[]> numbers = new ArrayList<>();
		
		
		/**
		 * PECS mnemonics
		 * (Producer extends, Consumer super)
		 */
		
		// declare a generic type covariant on the generic type
		List<? extends Object> covariant = new ArrayList<Integer>(Arrays.asList(new Integer(4711)));
		
		// won't compile
//		Integer it1 = covariant.get(0);
		// any object can only be assumed to be at least of type Object
		Object it1 = covariant.get(0);
		
		List<? super Integer> contravariant = new ArrayList<Object>();
		
		// won't compile
//		contravariant.add(new Object());
		// any object passed to the list must be at most of type Integer
		contravariant.add(new Integer(4711));
	}
}
