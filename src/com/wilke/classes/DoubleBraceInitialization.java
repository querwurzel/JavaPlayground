package com.wilke.classes;

import java.util.HashSet;
import java.util.Set;

public class DoubleBraceInitialization {

	public static final Set<String> VALID_CODES = new HashSet<String>() {{
		add("XZ13s");
		add("AB21/X");
		add("YYLEX");
		add("AR2D");
	}};
	
	public static void main(String[] args) {
		for (String item : VALID_CODES)
			System.out.println(item);
	}
}
