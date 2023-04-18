import socket
import threading
import sys

serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
serverAddr = ("192.168.1.137", 8888)
mySocket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

clientList = []

def sendall(data):
    for client in clientList:
        mySocket.sendto(data.encode(), client)

def getClientAddr():
    while(True):
        dataTypeB = serverSocket.recv(1)
        dataType = dataTypeB.decode()

        ipData = serverSocket.recv(4)
        ip = socket.inet_ntoa(ipData)
        portData = serverSocket.recv(2)
        port = int.from_bytes(portData, "big")
        clientAddr = (ip, port)

        match dataType:
            case '0':
                print(str(clientAddr) + " has connected!")
                clientList.append(clientAddr)
            case '1':
                clientList.append(clientAddr)
            case '2':
                print(str(clientAddr) + " has disconnected!")
                clientList.remove(clientAddr)  

def getClientMessage():
    while(True):
        data, addr = mySocket.recvfrom(256)
        print(str(addr) + " : " + data.decode())

def sendClientMessage():
    while(True):
        data = input()
        if len(data) > 255:
            print("Too many character (max is 255)")
            continue
        sendall(data)

if __name__=="__main__":
    serverSocket.connect(serverAddr)

    args = sys.argv
    myAddr = ("192.168.1.131", int(args[1]))
    mySocket.bind(myAddr)

    myAddrString = myAddr[0] + ":" + str(myAddr[1])
    serverSocket.send(socket.inet_aton(myAddr[0]))
    serverSocket.send(myAddr[1].to_bytes(2, "big"))

    getClientT = threading.Thread(target=getClientAddr)
    getClientMessageT = threading.Thread(target=getClientMessage)
    sendMessageT = threading.Thread(target=sendClientMessage)

    getClientT.start()
    getClientMessageT.start()
    sendMessageT.start()

