package com.wilke.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPclient {

	public static void main(String[] args) {
        InetAddress servAddress = InetAddress.getLoopbackAddress();
        Integer sport = 4711;

		try (DatagramSocket socket = new DatagramSocket()) {
	        Scanner userScanner = new Scanner(System.in);
	        System.out.println("Enter a message: ");
	        String userInput = userScanner.nextLine();

	        DatagramPacket out_packet = new DatagramPacket(
                userInput.getBytes(),
                userInput.length(),
                servAddress,
                sport);

	        // TODO: use connect to filter packets to only receive server-side ones
	        
	        System.out.println("Sending ..");

	        userScanner.close();
	        
	        socket.send(out_packet);
	        
	        byte[] buffer = new byte[1024];
	        
	        DatagramPacket in_packet = new DatagramPacket(buffer, buffer.length);
	        
	        System.out.println("Listening ..");
	        
	        try {
        		socket.setSoTimeout(3000);
	        	socket.receive(in_packet);
	        	
	        	String serverEcho = new String(in_packet.getData(), 0, in_packet.getLength());
	        	
	        	System.out.println("Server echo received: " + serverEcho);
	        } catch (SocketTimeoutException ex) {
	        	System.out.println("No server echo.");
	        }
		} catch (IOException ex ) {
			Logger.getLogger(UDPserver.class.getName()).log(Level.SEVERE, "", ex);
		}
	}
}