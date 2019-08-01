/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.eomcs.lms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import com.eomcs.lms.domain.Member;

public class ServerTest {

  public static void main(String[] args) throws Exception{
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
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      // 서버와의 입출력을 위해 스트림 객체를 준비한다.
      System.out.println("Connect to server");

      // 서버에 전송할 객체를 준비한다.
      Member member = new Member();
      member.setNo(1);
      member.setName("Youlim Kim");
      member.setEmail("lima1016@gmail.com");
      member.setPhoto("kim.gif");
      member.setTel("1111-1111");
      
      out.writeUTF("add");
      // 서버에 객체를 전송한다.
      out.writeObject(member);
      System.out.println("add 요청함.");

      // 서버에 보낸 데이터를 읽는다
      String response = in.readUTF();
      System.out.println("==> " + response);

      member = new Member();
      member.setNo(2);
      member.setName("ss Kim");
      member.setEmail("eeef@gmail.com");
      member.setPhoto("23e.gif");
      member.setTel("fdw-1111");
      
      out.writeUTF("add");
      // 서버에 객체를 전송한다.
      out.writeObject(member);
      out.flush();
      System.out.println("add 요청함");

      // 서버에 보낸 데이터를 읽는다
      response = in.readUTF();
      System.out.println("==> " + response);

      out.writeUTF("list");
      out.flush();
      System.out.println("list 요청함");

      response = in.readUTF();
      System.out.println("==> " + response);

      @SuppressWarnings("unchecked")
      List<Member> list = (List<Member>) in.readObject();

      System.out.println("------------------------------");
      for (Member m : list) {
        System.out.println(m);
      }
      System.out.println("------------------------------");

      // 서버가 처리할 수 없는 명령어 보내기
      out.writeUTF("delete");
      out.flush();
      System.out.println("delete 요청함");
      // fail일때 
      response = in.readUTF();
      System.out.println("==> " + response);
      
      // 서버가 보낸 데이터를 읽는다.
      response = in.readUTF();
      System.out.println("==> " + response);

      // 서버가 처리할 수 없는 명령어 보내기
      out.writeUTF("quit");
      out.flush();
      System.out.println("quit 요청함");
      
      response = in.readUTF();
      System.out.println("==> " + response);
      
    } catch (IOException e) {

      // 예외가 발생하면 일단 어디에서 예외가 발생했는지 확인하기 위해 호출 정보를 모두 출력한다.
      e.printStackTrace();
    }

    System.out.println("Disconnect");
  }
}
