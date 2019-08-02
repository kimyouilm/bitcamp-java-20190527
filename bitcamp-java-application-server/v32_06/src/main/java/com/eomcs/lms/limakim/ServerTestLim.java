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
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    System.out.println("Disconnected");
  }
}
