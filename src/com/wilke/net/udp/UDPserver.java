package com.wilke.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPserver {

    public static void main(String[] args) {
    	
    	try (DatagramSocket socket = new DatagramSocket(4711)) {
    		byte[] buffer = new byte[1024];
    		
    		DatagramPacket in_packet = new DatagramPacket(buffer, buffer.length);
    		
    		System.out.println("Listening ..");
    		socket.receive(in_packet); // blocking call
    		
            InetAddress clientAddress = in_packet.getAddress();
            Integer clientPort = in_packet.getPort();
            String in_message = new String(in_packet.getData(), 0, in_packet.getLength());
            
            System.out.println("Received: " + in_message);
            
            String out_messagae = "echo: " + in_message;
            DatagramPacket out_datagramPacket= new DatagramPacket(
                    out_messagae.getBytes(),
                    out_messagae.length(),
                    clientAddress,
                    clientPort);
            
            socket.send(out_datagramPacket);
    		
    	} catch ( IOException ex) {
    		Logger.getLogger(UDPserver.class.getName()).log(Level.SEVERE, "", ex);
    	}
    }
}
