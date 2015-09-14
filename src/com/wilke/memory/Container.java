package com.wilke.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * JVM:
 * -showversion -verbose:gc -XX:+PrintGCDetails -Xloggc:/home/stefan/gc.log -XX:+HeapDumpOnOutOfMemoryError
 */
public class Container {
	@SuppressWarnings("unused")
	private final byte[] waste = new byte[1024_000];

	private static long Counter = 1;
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
    
    public static void oomTheHeap() throws InterruptedException {
    	List<Container> memory = new ArrayList<>();
    	while(true)
    		memory.add(new Container());
    }
    
    public static void main(String[] args) throws InterruptedException {
		oomTheHeap();

		oomTheStack();
    }
}
