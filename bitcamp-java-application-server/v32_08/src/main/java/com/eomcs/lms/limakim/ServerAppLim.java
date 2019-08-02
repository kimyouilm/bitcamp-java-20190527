package com.eomcs.lms.limakim;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerAppLim {

  // <Socket>
  // To connect to other machine we need a socket connection.
  // A socket connection means the two machines have information
  // about each other’s network location (IP Address) and TCP port.
  public static void main(String[] args) {
    System.out.println("[Add Drop Class Server Application]");

    // <PortNum>
    // The logical address of each application or process
    // that uses a network or the Internet to communicate.
    // A port number uniquely identifies a network-based application on a computer.

    // <ServerSocket>
    // A ServerSocket which waits for the client requests.
    // A plain old Socket socket to use for communication with the client.
    // 포트와 연결(bind)되어 외부의 연결요청을 기다리다 연결요청이 오면 Socket을 생성해서 소켓과 소켓간의 통신이 이루어지도록 한다.
    // 실제 통신은 소켓끼리 하지만 그 소켓끼리를 연결해주는 역할을 서버소켓이 하는 것이다.
    // 한 포트에는 하나의 서버소켓만 연결할 수 있는데
    // 한 포트에 둘 이상의 서버소켓이 연결된다면 클라이언트 입장에서 그 둘을 구분할 수 없기 때문이다.

    // 서버소켓을 생성하여 8886번 포트와 결합(bind)시킨다.
    try (ServerSocket serverSocket = new ServerSocket(8886)) {
      System.out.println("<<Start Server>>");

      try(Socket clientSocket = serverSocket.accept()){
        System.out.println("<<Connect to Client>>");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    System.out.println("<<Disconnected>>");

  }



}
