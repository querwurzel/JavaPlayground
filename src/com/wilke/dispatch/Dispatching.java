package com.wilke.dispatch;


public class Dispatching {

	public static void DynamicDispatching() {
		// notice the left side type declaration (the static type)
		Animal animal = new Animal();
		Animal elephant = new Elephant();
		
		// one fruit suffices, this example is about the animal types
		Fruit fruit = new Fruit();
		
		// virtual functions are dynamically dispatched
		// to be clear: 'animal.' and 'elephant.' are arguments as well as 'this.' 'super.' etc
		// these are dynamically dispatched, but arguments on the right side are not
		// that is called single dispatch
		animal.eat(fruit);
		elephant.eat(fruit); // here an 'Elephant' eats the fruit but not just an 'Animal'
	}
	
	public static void Overloading() {
		// notice the left side type declaration (the static type)
		Animal animal = new Elephant();
		
		Fruit fruit = new Fruit();
		Fruit apple = new Apple();
		
		// overload dispatching is based on the compile-time static type of the argument
		animal.eat(fruit);
		animal.eat(apple); // same result
	}
	
	/**
	 * This example finally solves the problem of dynamic dispatching showed in Overloading()
	 * We now make full use of two hierarchies of inheritance (fruit and animal) that's why it's called DOUBLE dispatching.
	 * the method call is now dependent on the two types .. the left and right one.
	 */
	public static void DoubleDispatching() {
		
		// notice the left side type declaration (the static type)
		
		Animal animal = new Animal();
		Animal elephant = new Elephant();
		
		Fruit fruit = new Fruit();
		Fruit apple = new Apple();
		Fruit peach = new Peach();
		
		// in order to dispatch based on the runtime-type the call is inverted.
		// as a result we call virtual functions and hence get dynamic dispatching 
		// now not the animal eats a fruit but the fruit asks the animal to eat it
		
		fruit.eatMe(animal);
		apple.eatMe(animal);
		peach.eatMe(animal); // since the animal type's static type is Animal a "PeachEatingMethod" is not available
		
		fruit.eatMe(elephant);
		apple.eatMe(elephant);
		
		// Again, the Animal class doesn't know the peach fruit.
		// that's a disadvantage of double dispatch, each Animal needs to know each fruit (n*m effort)
		// This pattern can lead to a combinatorial explosion.
		peach.eatMe(elephant); 
	}
	
	/**
	 * So-called MultiMethods .. dependent on the runtime-type of multiple arguments (including the caller) the right method is dispatched.
	 * This is a weak example. EatMe should have multiple parameters with multiple inheritance hierarchies as parameters.
	 * This example only uses one hierarchy of inheritance (the Fruit side).
	 */
	public static void MultipleDispatching() {
		Animal animal = new Animal();
		
		// notice the left side type declaration (the static type)
		Fruit fruit = new Fruit();
		Fruit apple = new Apple();
		
		fruit.eatMe(animal);
		apple.eatMe(animal);
	}
	
	public static void main(String[] args) {
	
		DynamicDispatching();
		
		System.out.println("----------------------------");
		
		Overloading();
		
		System.out.println("----------------------------");
		
		DoubleDispatching();
		
		System.out.println("----------------------------");
		
		MultipleDispatching();
	}

	static class Fruit {
		public void eatMe(Animal animal) {
			animal.eat(this);
		}
	}

	static class Apple extends Fruit {
		/**
		 * Notice: without overriding the Superclass method is dispatched.
		 */
		@Override
		public void eatMe(Animal animal) {
			// works because now dynamic dispatch is used
			animal.eat(this);
		}
	}

	static class Peach extends Fruit {
		@Override
		public void eatMe(Animal animal) {
			animal.eat(this);
		}
	}

	static class Animal {
		void eat(Fruit fruit) {
			System.out.println("The animal eats a fruit");
		}

		void eat(Apple fruit) {
			System.out.println("The animal eats an apple.");
		}
	}

	static class Elephant extends Animal {
		@Override
		void eat(Fruit fruit) {
			System.out.println("The elephant eats a Fruit.");
		}

		@Override
		void eat(Apple fruit) {
			System.out.println("The elephant eats an Apple");
		}

		void eat(Peach fruit) {
			System.out.println("The Peach was fermented. The elephant laughs.");
		}
	}
}
