package com.eomcs.lms.limakim;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
    // 클라이언트의 연결 요청이 들어올 때까지 기다리다가 요청이 들어오는 즉시 승인을 한후, 연결정보(client info)를 리턴함.
    try (ServerSocket serverSocket = new ServerSocket(8886)) {
      System.out.println("<<Start Server>>");

      // 클라이언트 연결 객체(소켓)에서 I/O 스트림 객체를 얻는다.
      // => 사용하기 편하게 데코레이터를 붙인다.
      // Adapter pattern
      try (Socket clientSocket = serverSocket.accept();
          BufferedReader in =
              new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
          PrintWriter out =
              new PrintWriter(new BufferedOutputStream(clientSocket.getOutputStream()))) {
        System.out.println("<<Connect to Client>>");
        
        // 클라이언트가 보낸 데이터를 읽는다.
        // => 보낸 규칙에 맞춰서 읽어야 한다.
        String message = in.readLine();
        System.out.println("<<Read files on server>>");
        
        // 클라이언트가 보낸 문ㄴ자열이 궁금하다 서보쪽 콘솔 창에 출력해 보자.
        System.out.println("--> " + message);
        // PrintWriter에 출력한 데이터는 버퍼에 있다. 버퍼에 있는 데이터를 강제로 출력하라
        out.flush();
        // 클라이언트가 보낸 문자열을 그대로 리턴한다.
        out.println("[Youlim Kim" + message);
        System.out.println("<<Send data to client>>");

      }
      // try 블록을 벗어날 때 clientSocket.close()가 자동으로 호출되어 클라이언트와의 연결을 끊는다.
      System.out.println("<<The session has been disconnected by the client>>");

    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("<<Disconnected>>");

  }



}
