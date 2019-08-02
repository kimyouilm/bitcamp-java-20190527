// v32_8: 회원/수업/게시물 요청을 처리하는 코드를 별도의 클래스로 분리하다.
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import com.eomcs.lms.limakim.MemberServlet;

/*
 * [Adapter pattern] 한 클래스의 인터페이스를 클라이언트에서 사용하고자하는 다른 인터페이스로 변환한다. 어댑터를 이용하면 인터페이스 호환성 문제 때문에 같이 쓸 수
 * 없는 클래스들을 연결해서 쓸 수 있다.
 */
public class ServerApp {

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

        BoardServlet boardServlet = new BoardServlet(in, out);
        LessonServlet lessonServlet = new LessonServlet(in, out);
        MemberServlet memberServlet = new MemberServlet(in, out);

        Loop: while (true) {
          // 클라이언트가 보낸 명령을 읽는다.
          String command = in.readUTF();

          System.out.println(command + "요청 처리중...");

          if (command.startsWith("/board/")) {
            boardServlet.service(command);
            out.flush();
            System.out.println("클라이언트에게 응답 완료");
            // 처리하면 밑으로 가지말고 다시 while문으로 가렴! m
            continue;
          } else if (command.startsWith("/lesson/")) {
            lessonServlet.service(command);
            out.flush();
            System.out.println("클라이언트에게 응답 완료");
            continue;
          } else if (command.startsWith("/member/")) {
            memberServlet.service(command);
            out.flush();
            System.out.println("클라이언트에게 응답 완료");
            continue;
          } else if (command.equals("quit")) {
            out.writeUTF("ok");
          } else {
            fail("지원하지 않는 명령입니다.");
          }
          
          // 명령어에 따라 처리한다.
          switch (command) {
            case "quit":
              out.writeUTF("ok");
              break Loop;
            default:
              fail("지원하지 않는 명령입니다.");
          }
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

  // private static void updateMember0() throws Exception {
  // Member member = (Member) in.readObject();
  //
  // for (Member m : memberList) {
  // if (m.getNo() == member.getNo()) {
  // // 클라이언트가 보낸 값으로 기존 객체의 값을 바꾼다.
  // m.setName(member.getName());
  // m.setEmail(member.getEmail());
  // m.setPassword(member.getPassword());
  // m.setPhoto(member.getPhoto());
  // m.setTel(member.getTel());
  // m.setRegisteredDate(member.getRegisteredDate());
  // out.writeUTF("ok");
  // return;
  // }
  // }
  // }
  // wow 이것도... 메소드로..?
  private static void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }
}
