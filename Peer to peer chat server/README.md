### A simple chat application for LAN

In a local area network, the server is run on a linux machine.

The other machines, than start a TCP connection with the server, getting the IP's of the other machines
creating a peer to peer connection between them, so they can chat

In the current version, the ip's are hardcoded in both the client and server side

-The server side:
```
struct sockaddr_in serverAddr;
memset(&serverAddr, 0, sizeof(serverAddr));
serverAddr.sin_family = AF_INET;
serverAddr.sin_port = htons(8888);
serverAddr.sin_addr.s_addr = inet_addr("192.168.1.137");
```

-The client side:
```
serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
serverAddr = ("192.168.1.137", 8888)
...
args = sys.argv
myAddr = ("192.168.1.131", int(args[1]))
mySocket.bind(myAddr)
```
Both of theese have to manually updated before running the application

So a possible improvement is for the ip's to be added in the command line, when running the program

Another improvement could be encryption between the senders