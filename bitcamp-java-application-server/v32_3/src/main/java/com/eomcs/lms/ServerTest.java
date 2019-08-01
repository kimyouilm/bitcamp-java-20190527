/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.eomcs.lms;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

public class ServerTest {

  public static void main(String[] args) {
    System.out.println("[Add&Drop class management Server Application Test!]");

    // 서버에 통신을 연결한다.
    // => new Socket(인터넷주소, 포트번호)
    // => 서버와 연결될 때까지 리턴하지 않는다.
    // 인터넷 주소?
    // => IP주소(예: 192.168.0.1)
    // => 도메일 이름(예: www.okok.com)
    // 클라이언트의 연결을 처리할 객체 준비
    // => new ServerSocket(포트번호)
    // 포트번호?
    // =>클라이언트가 서버에 연결할 때 사용할 사서함 번호
    // => 사서함 번호는 한 컴퓨터에서 중복 사용할 수 없다.
    // localHost?
    // => 로컬 PC를 가리키는 특수 도메인 이름이다.
    // 127.0.0.17
    // => 로컬 PC를 가리키는 특수 IP주소이다.

    try (Socket socket = new Socket("192.168.43.153", 8888);
        PrintWriter out = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
      // 서버와의 입출력을 위해 스트림 객체를 준비한다.
      System.out.println("Connect server");

      // 서버에 출력한다.
      out.println("YoulimKim: 입니다!");
      out.flush(); //주의! 버퍼로 출력한 내용을 서버쪽에 보내도록 강제해야한다.
      // 서버에 보낸 데이터를 읽는다
      String response = in.readLine();
      System.out.println("서버에 데이터를 보냈음.");


      // 서버가 보낸 데이터를 읽는다.
      System.out.println("-->" + response);
      System.out.println("서버로부터 데이터를 받았음!!!");

    } catch (IOException e) {

      // 예외가 발생하면 일단 어디에서 예외가 발생했는지 확인하기 위해 호출 정보를 모두 출력한다.
      e.printStackTrace();
    }

    System.out.println("Disconnect");
  }
}
