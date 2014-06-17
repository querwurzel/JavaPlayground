package com.wilke.classes;

interface SomeInterface {
	SomeEnum getSomeValue();
}

public enum SomeEnum implements SomeInterface {
	
	NORTH {
		@Override
		public SomeEnum getSomeValue() {
			return NORTH;
		}
	},
	SOUTH {
		@Override
		public SomeEnum getSomeValue() {
			return SOUTH;
		}
	},
	EAST {
		@Override
		public SomeEnum getSomeValue() {
			return EAST;
		}
	},
	WEST {
		@Override
		public SomeEnum getSomeValue() {
			return WEST;
		}
	},
	NOWHERE;

	// kind of default implementation
	@Override
	public SomeEnum getSomeValue() {
		return null;
	}
	
	public static void main(String... args) {
		System.out.println(SomeEnum.NORTH.getSomeValue().toString());
		
		System.out.println(SomeEnum.NOWHERE.getSomeValue());
	}
}
