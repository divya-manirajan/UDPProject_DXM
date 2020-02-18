Divya Manirajan

The best way to run this is with two cmd windows, using one for the server and one for the client.

On the server window, once in the correct directory compile and run the server program using javac UDPServer.java and java UDPServer. When the server is run, it will report when the server started and anytime a request is received from the client.

Once the server is started, the client can be run and it can be run in two ways, one with arguments and one
without, after compiled usign javac UDPClient.java

Typing java UDPClient will run the client with a predetermined hostname used for the iP and a set port. These will be the same as running the program with arguments.

Typing java UDPClient _X_ _Y_ with X and Y being arguments will set X as the hostname for the iP and Y as the port number.
-X needs to be 127.a.b.c and Y needs to be 2045

The client will then ask for a command that the user will enter and if the user enters <REQUESTQUOTE> then a quote will be returned and the server will keep track of when this request was recieved and output this on the server cmd.

If <END> is entered then the client will close and show a "Client Closed" message.exit
  
