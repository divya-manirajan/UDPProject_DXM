/**
 * This class creates the UDP Server with port 2045
 * 
 * @author Divya Manirajan
 * @version 1.0
 * CS-415 UDP Project
 */
import java.text.DateFormat;
import java.util.Date;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;

public class UDPServer {
	
    public static void main(String args[]) throws Exception {
    	//Date object to get current date and time
    	Date date = new Date();
    	//Formats date and time with specified guidelines
    	SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
    	
         //creates datagram with port 2045
        DatagramSocket socket = new DatagramSocket(2045);
        //prints out when the server is started
        System.out.println("Server Started at "+dateTimeFormat.format(date));
        
        //byte array for data in and out
        byte[] inServer = new byte[1024];
        byte[] outServer = new byte[1024];
        
        //creates the receiving datagram
        DatagramPacket rcvPkt = new DatagramPacket(inServer, inServer.length);
        
        //whiel loop to continuously wait for data from client
        while (true) {
        	
            // waiting for the receiving data from the client
            socket.receive(rcvPkt);
            //prints out where and when the data is received
            System.out.println("Request Received from " +rcvPkt.getSocketAddress().toString().replace("/", "")+" "+dateTimeFormat.format(date));
                      
            //sets the IP of the receiving info
            InetAddress IP = rcvPkt.getAddress();
            //sets the port of the receiving info
            int port = rcvPkt.getPort();
            
            //creates a string to hold the received data and finds the number of bytes of this data
            String temp = new String(rcvPkt.getData());
            outServer = temp.getBytes();
            
            //sends datagram of information 
            DatagramPacket sndPkt = new DatagramPacket(outServer, outServer.length, IP, port);
            socket.send(sndPkt);
        }//ends while
    }//ends main
}//ends UDPServer