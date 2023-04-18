#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

#include <map>
#include <string>
#include <iostream>

int serverSocket, clientSocket;
fd_set master;
fd_set readeable;
int fd_max;
struct sockaddr_in clientAddr;
std::map<int, std::pair<int, uint16_t>> clients;


void setUpConnection(){
    serverSocket = socket(AF_INET, SOCK_STREAM, 0);
    if (serverSocket < 0){
        perror("Creation!");
        exit(1);
    }

    int yes = 1;
    setsockopt(serverSocket, SOL_SOCKET, SO_REUSEADDR, &yes, sizeof(yes));

    struct sockaddr_in serverAddr;
    memset(&serverAddr, 0, sizeof(serverAddr));
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = htons(8888);
    serverAddr.sin_addr.s_addr = inet_addr("192.168.1.137");

    if (bind(serverSocket, (struct sockaddr*)&serverAddr, sizeof(serverAddr)) < 0){
        perror("Bind!");
        exit(1);
    }

    if (listen(serverSocket, 10) < 0){
        perror("Listen!");
        exit(1);
    }

    fd_max = serverSocket;

    FD_SET(serverSocket, &master);
}


void sendToAllClients(std::pair<int, uint16_t> clientAddr, char type){
    std::map<int, std::pair<int, uint16_t>>::iterator it = clients.begin();
    for (it; it != clients.end(); it++){
        int sockTcp = it->first;
        if (send(sockTcp, &type, sizeof(char), 0) < 0){
            perror("Send!");
        }

        if (send(sockTcp, &clientAddr.first, sizeof(int), 0) < 0){
            perror("Send!");
        }
        if (send(sockTcp, &clientAddr.second, sizeof(uint16_t), 0) < 0){
            perror("Send!");
        }
    }
}


void handleClientDisconnect(int i){
    std::pair<int, uint16_t> clientAddr = clients[i];

    clients.erase(i);

    sendToAllClients(clientAddr, '2');

    struct in_addr ipAddr;
    ipAddr.s_addr = clientAddr.first;

    std::cout << inet_ntoa(ipAddr) << ":" << ntohs(clientAddr.second) << " has disconnected!" << std::endl;

    close(i);
    FD_CLR(i, &master);
}


void handleClientConnection(){
    int size = sizeof(clientAddr);
    memset(&clientAddr, 0, sizeof(sockaddr_in));
    clientSocket = accept(serverSocket, (struct sockaddr*)&clientAddr, (socklen_t*)&size);
    if (clientSocket < 0){
        perror("Accept!");
    }

    FD_SET(clientSocket, &master);
    if (clientSocket > fd_max){
        fd_max = clientSocket;
    }

    int ip = 0;
    uint16_t port = 0;

    if (recv(clientSocket, &ip, sizeof(ip), 0) < 0){
        perror("Recv!");
    }
    if (recv(clientSocket, &port, sizeof(port), 0) < 0){
        perror("Recv!");
    }

    std::pair<int, uint16_t> clientAddr(ip, port);

    sendToAllClients(clientAddr, '0');

    char type = '1';
    std::map<int, std::pair<int, uint16_t>>::iterator it = clients.begin();

    for (it; it != clients.end(); it++){
        int sockTcp = it->first;
        std::pair<int, uint16_t> addr = it->second;

        if (send(clientSocket, &type, sizeof(char), 0) < 0){
            perror("Send!");
        }
        if (send(clientSocket, &addr.first, sizeof(int), 0) < 0){
            perror("Send!");
        }
        if (send(clientSocket, &addr.second, sizeof(uint16_t), 0) < 0){
            perror("Send!");
        }
    }

    clients[clientSocket] = clientAddr;

    struct in_addr ip_addr;
    ip_addr.s_addr = clientAddr.first;

    std::cout << inet_ntoa(ip_addr) << ":" << ntohs(clientAddr.second) << " has connected!" << std::endl;
}


int main(){
    setUpConnection();

    while (true){
        readeable = master;
        if (select(fd_max + 1, &readeable, NULL, NULL, NULL) < 0){
            perror("Select");
            exit(1);
        }

        for (int i = 0; i <= fd_max; i++){
            if (FD_ISSET(i, &readeable)){
                if (i == serverSocket){
                    handleClientConnection();
                }
                else{
                    handleClientDisconnect(i);
                }
            }
        }
    }

    close(serverSocket);
    return 0;
}