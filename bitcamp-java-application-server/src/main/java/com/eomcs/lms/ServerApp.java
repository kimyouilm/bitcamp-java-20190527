// v32_6: 회원데이터를 다루는 CRUD명령을 처리한다.
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import com.eomcs.lms.domain.Member;

/*
 * [Adapter pattern] 한 클래스의 인터페이스를 클라이언트에서 사용하고자하는 다른 인터페이스로 변환한다. 어댑터를 이용하면 인터페이스 호환성 문제 때문에 같이 쓸 수
 * 없는 클래스들을 연결해서 쓸 수 있다.
 */
public class ServerApp {
  
  static ArrayList<Member> memberList = new ArrayList<>();
  static ObjectInputStream in;
  static ObjectOutputStream out;
  
  public static void main(String[] args) {
    System.out.println("[수업관리시스템 서버 애플리케이션!]");


    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 시작!");

      try (Socket clientSocket = serverSocket.accept();
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {

        System.out.println("클라이언트와 연결되었음&&^");

        // 다른 메소드가 사용할 수 있도록 입출력 스트림을 스태틱 변수에 저장한다.
        ServerApp.in = in;
        ServerApp.out = out;
        
        Loop: while (true) {
          // 클라이언트가 보낸 명령을 읽는다.
          String command = in.readUTF();

          System.out.println(command + "요청 처리중...");
          // 명령어에 따라 처리한다.
          switch (command) {
            case "/member/add":
              // 클라이언트가 보낸 객체를 읽는다.
              addMember();
              break;
            case "/member/list":
              listMember();
              break;
            case "quit":
              out.writeUTF("ok");
              break Loop;
            default:
              out.writeUTF("fail");
              out.writeUTF("지원하지 않는 명령입니다.");
          }
          out.flush();
          System.out.println("클라이언트에게 응답 완료");
        } // Loop
        out.flush();
      }
      System.out.println("클라이언트와 연결을 끊었음!@#$");

    } catch (Exception e) {

      e.printStackTrace();
    }

    System.out.println("서버 종료&&");
  }
  
  private static void addMember() throws Exception{
    Member member = (Member) in.readObject();
    out.writeUTF("ok");
    memberList.add(member);
  }
  
  private static void listMember() throws Exception{
    out.writeUTF("ok");
    out.writeObject(memberList);
  }
}
