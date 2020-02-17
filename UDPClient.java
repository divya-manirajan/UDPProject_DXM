/**
 * This class creates the UDP Client for port 2045 and IP local host
 * 
 * @author Divya Manirajan
 * @version 1.0
 * CS-415 UDP Project
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.*;

public class UDPClient {
	
    public static void main(String args[]) {
    	//creates scanner to read input from user for requesting quote or ending client
    	Scanner scan = new Scanner(System.in);
    	//string to hold user input for requesting quote of ending client
    	String request;
    	//string to hold selected data from array list
    	String data = null;
    	//array list to hold all quotes from file
    	ArrayList <String> dataArray = new ArrayList<String>();
    	
    	//string for the host name used in the InetAdress
    	String hostname;
    	//port used in outgoing datagram
    	int port;
    	
    	//if arguments are used in running client then first argument is the hostname and the second is the port
    	//if no arguments are used then hostname is automatically set to localhost and port is set to 2045
    	if(args.length < 2) {
    		hostname = "localhost";
    		port = 2045;
    	}
    	else {
    	hostname = args[0];
    	port = Integer.parseInt(args[1]);
    	}//end if-else
    	
    	//try-catch to set up file and copy data from file into array list
    	try {
    		File file = new File("quote.csv");
    		Scanner fileScan = new Scanner(file);
    		while(fileScan.hasNext()) {
    			dataArray.add(fileScan.nextLine());
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}//end try-catch
    	
    	//try-catch to set up datagrams and InetAddress information for the client
        try {
            //create datagram socket 
            DatagramSocket socket = new DatagramSocket();
           
            //byte array for data in and out
            byte[] inData = new byte[1024];
            byte[] outData = new byte[1024];
            
            //create InetAddress using the hostname
            InetAddress IP = InetAddress.getByName(hostname);
            
            //asks the user for a command to determine if client is started
            System.out.println("Enter a command:");
            request = scan.nextLine();
            
            //while client is activated requests will be sent to the server
            while(request.equals("<REQUESTQUOTE>")) {
            	
            //creates int to get random number from 0-20
            int rand = (int) (Math.random() *20);
            
            //sets data to a random quote from the data array
            data = dataArray.get(rand);
            
            //sets the sending data to the string data bytes
            outData = data.getBytes();
           
            //creates datagram to send out packet of data including IP and port and sends it out
            DatagramPacket sendPkt = new DatagramPacket(outData, outData.length, IP, port);
            socket.send(sendPkt);
            
            //creates datagram of receiving data from server
            DatagramPacket recievePkt = new DatagramPacket(inData, inData.length);
            socket.receive(recievePkt);
            
            //prints out the data as a string
            System.out.println(new String(recievePkt.getData()));
            
            //asks user if they want to request another quote or end client
            System.out.println("Enter a command:");
            //saves this value and uses it to evaluate in the while loop
            request = scan.nextLine();
           
            }//end while
            
            //prints out client closed if user does not request a quote
            System.out.println("Client Closed");
            
        } catch (Exception e) {
            System.out.println("error connect udp server");
        }//end try-catch
        	
    }
    	
 
}