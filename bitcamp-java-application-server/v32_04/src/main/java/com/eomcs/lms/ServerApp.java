// v32_4: 클라이언트와 서버간에 데이터 주고받기 II - 인스턴스 주고받기
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import com.eomcs.lms.domain.Member;

/*
 * [Adapter pattern] 한 클래스의 인터페이스를 클라이언트에서 사용하고자하는 다른 인터페이스로 변환한다. 어댑터를 이용하면 인터페이스 호환성 문제 때문에 같이 쓸 수
 * 없는 클래스들을 연결해서 쓸 수 있다.
 */
public class ServerApp {

  public static void main(String[] args) {
    System.out.println("[수업관리시스템 서버 애플리케이션!]");

    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 시작!");

      try (Socket clientSocket = serverSocket.accept();
          ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {
        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

        System.out.println("클라이언트와 연결되었음&&^");

        // 클라이언트가 보낸 데이터를 읽는다.
        // => 보낸 규칙에 맞춰서 읽어야 한다.
        Member member = (Member) in.readObject();
        System.out.println("클라이언트가 보낸 객체를 읽었음~~");

        // 클라이언트가 보낸 객체의 내용이 궁금하다. 서버쪽 콘솔 창에 출력해 보자.
        System.out.println(member);

        out.writeUTF("ok");
        out.flush(); // PrintWriter에 출력한 데이터는 버퍼에 있다. 버퍼에 있는 데이터를 강제로 출력하라!
        // 클라이언트가 보낸 문자열을 그대로 리턴한다.
        System.out.println("클라이언트로 데이터를 보냈음@@@");

      }
      System.out.println("클라이언트와 연결을 끊었음!@#$");

    } catch (Exception e) {

      e.printStackTrace();
    }

    System.out.println("서버 종료&&");
  }
}
