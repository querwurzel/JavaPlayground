package com.wilke.classes;

interface SomeInterface {
	EnumWithInterface getSomeEnumValue();
}

/**
 * Not really a surprise since Java enumerations can implement methods .. but nonetheless.
 */
public enum EnumWithInterface implements SomeInterface {
	NORTH {
		@Override
		public EnumWithInterface getSomeEnumValue() {
			return NORTH; // always go north
		}
	},
	SOUTH {
		@Override
		public EnumWithInterface getSomeEnumValue() {
			return SOUTH; // always go north
		}
	},
	EAST {
		@Override
		public EnumWithInterface getSomeEnumValue() {
			return EAST; // always go north
		}
	},
	WEST {
		@Override
		public EnumWithInterface getSomeEnumValue() {
			return WEST; // always go north
		}
	};
}

class Main {
	public static void main(int isd) {
		System.out.println(EnumWithInterface.NORTH.getSomeEnumValue().toString());
	}
}
