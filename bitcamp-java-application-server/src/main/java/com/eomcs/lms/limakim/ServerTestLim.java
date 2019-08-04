package com.eomcs.lms.limakim;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerTestLim {
  public static void main(String[] args) {
    System.out.println("[Add or Drop Class Management Server Application Test]");

    try (Socket serverSocket = new Socket("localHost", 8886);
        PrintWriter out = new PrintWriter(
            new BufferedOutputStream(serverSocket.getOutputStream()));
        BufferedReader in = new BufferedReader(
            new InputStreamReader(serverSocket.getInputStream()))) {
      System.out.println("Connect to Server");
      
      // 서버에 출력한다.
      out.println("Lima");
      // 주의! 버퍼로 출력한 내용을 서버쪽에 보내도록 강제해야 한다.
      out.flush();
      
      // 서버에 보낸 데이터를 읽는다.
      String response = in.readLine();
      System.out.println("<<Send files to Server>>");
      
      // 서버가 보낸 데이터를 읽는ㄷ나.
      System.out.println("--> " + response);
      System.out.println("<<Get data from the server>>");
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    System.out.println("Disconnected");
  }
}
