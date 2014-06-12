package com.wilke.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * JVM: -verbose:gc -XX:+PrintGCDetails -Xloggc:/tmp/gc.log
 */
public class Container {
	@SuppressWarnings("unused")
	private final byte[] waste = new byte[1024_000];

	private static volatile long Counter = 1;
	private final  long Id;
	
	public Container() {
		this.Id = Container.Counter++;
	}
	
    @Override
    protected void finalize() {
    	System.out.println("Container#" + this.Id + " gone.");
    }
   
    public static void oomTheStack() {
    	while (true)
    		oomTheStack();
    }
    
    public static void oomTheHeap() {
    	List<Container> memory = new ArrayList<>();
    	while(true)
    		memory.add(new Container());
    }
    
    public static void main(String[] args) {
		oomTheHeap();

		oomTheStack();
    }
}
