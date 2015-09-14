package com.wilke.classes;

public class EncapsulationAndScope {

	private final String secret;
	
	EncapsulationAndScope(String secret) {
		this.secret = secret;
	}
	
	public static void tellMeYourSecret(EncapsulationAndScope instance) {
		System.out.println(instance.secret);
	}
	
	private static void tellSomeMoreSecrets() {
		System.out.println("SECRETS!");
	}
	
	public static void main(String[] args) {
		EncapsulationAndScope.tellMeYourSecret(new EncapsulationAndScope("MySecret"));

		// works because the main method is part of the EncapsulationAndScope class
		EncapsulationAndScope.tellSomeMoreSecrets();
		
		// variable scope
		{
			@SuppressWarnings("unused")
			int magic = 4711;
		}
		
		int magic = 1337;
		System.out.println(magic);
	}
}
