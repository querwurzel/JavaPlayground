package com.wilke.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

public class DefaultSerialization {
	
	public static void serializePojo() {
		Pojo pojo = new Pojo();

		try {
			FileOutputStream fileOut = new FileOutputStream("/tmp/pojo.obj");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(pojo);
			out.close();
			fileOut.close();
			
			System.out.println("Serialised.");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void deSerializePojo() {
		Pojo pojo = new Pojo();

		try {
			FileInputStream fileIn = new FileInputStream("/tmp/pojo.obj");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			pojo = (Pojo)in.readObject();
			in.close();
			fileIn.close();
			
			System.out.println("Serialised Pojo equals default Instance: " + pojo.equals(new Pojo()));
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static class Pojo implements Serializable {
		
		private static final long serialVersionUID = -21457667382046671L;
		
		public String headline;
		public String[] content;
		private final String signature;
		
		// static variables are not serialised
		public transient String transString;
		
		// no effect on serialisation
		public volatile String volatString;
		
		// static variables are not serialised
		public static String staticString = "staticString";

		public Pojo() {
			this.headline = "Headline";
			this.content = new String[] { "C1", "C2", "C3" };
			this.signature = "Signature";
			this.transString = "transString";
			this.volatString = "staticString";
		}

		@Override
		public boolean equals(Object o) {
			if (o == null || !(o instanceof Pojo))
				return false;
			
			Pojo pojo = (Pojo)o;
			if (!this.headline.equals(pojo.headline))
				return false;
			
			if (!this.signature.equals(pojo.signature))
				return false;
			
			if (!Arrays.equals(this.content, pojo.content))
				return false;
			
			if (!this.volatString.equals(pojo.volatString))
				return false;

			return true;
		}
	}

	public static void main(String[] args) {
		serializePojo();
		
		deSerializePojo();
	}
}
