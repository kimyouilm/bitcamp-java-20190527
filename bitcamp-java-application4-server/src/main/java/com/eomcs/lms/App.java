// client-v37_1: '규칙1' 프로토콜에 따라 클라이언트 요청을 처리한다.
package com.eomcs.lms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.dao.mariadb.BoardDaoImpl;
import com.eomcs.lms.dao.mariadb.LessonDaoImpl;
import com.eomcs.lms.dao.mariadb.MemberDaoImpl;
import com.eomcs.lms.handler.BoardAddCommand;
import com.eomcs.lms.handler.BoardDeleteCommand;
import com.eomcs.lms.handler.BoardDetailCommand;
import com.eomcs.lms.handler.BoardListCommand;
import com.eomcs.lms.handler.BoardUpdateCommand;
import com.eomcs.lms.handler.Command;
import com.eomcs.lms.handler.LessonAddCommand;
import com.eomcs.lms.handler.LessonDeleteCommand;
import com.eomcs.lms.handler.LessonDetailCommand;
import com.eomcs.lms.handler.LessonListCommand;
import com.eomcs.lms.handler.LessonUpdateCommand;
import com.eomcs.lms.handler.MemberAddCommand;
import com.eomcs.lms.handler.MemberDeleteCommand;
import com.eomcs.lms.handler.MemberDetailCommand;
import com.eomcs.lms.handler.MemberListCommand;
import com.eomcs.lms.handler.MemberUpdateCommand;
import com.eomcs.util.Input;

public class App {

  private static final int CONTINUE = 1;
  private static final int STOP = 0;

  HashMap<String, Command> commandMap = new HashMap<>();
  Connection con;

  public App() throws Exception {
    try {
      // DAO가 사용할 Connection 객체 준비하기
      con = DriverManager
          .getConnection("jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111");

      // Command 객체가 사용할 데이터 처리 객체를 준비한다.
      BoardDao boardDao = new BoardDaoImpl(con);
      MemberDao memberDao = new MemberDaoImpl(con);
      LessonDao lessonDao = new LessonDaoImpl(con);

      // Client 명령을 처리할 커맨드 객체를 준비한다.
      commandMap.put("/lesson/add", new LessonAddCommand(null, lessonDao));
      commandMap.put("/lesson/delete", new LessonDeleteCommand(null, lessonDao));
      commandMap.put("/lesson/detail", new LessonDetailCommand(null, lessonDao));
      commandMap.put("/lesson/list", new LessonListCommand(null, lessonDao));
      commandMap.put("/lesson/update", new LessonUpdateCommand(null, lessonDao));

      commandMap.put("/member/add", new MemberAddCommand(null, memberDao));
      commandMap.put("/member/delete", new MemberDeleteCommand(null, memberDao));
      commandMap.put("/member/detail", new MemberDetailCommand(null, memberDao));
      commandMap.put("/member/list", new MemberListCommand(null, memberDao));
      commandMap.put("/member/update", new MemberUpdateCommand(null, memberDao));

      commandMap.put("/board/add", new BoardAddCommand(null, boardDao));
      commandMap.put("/board/delete", new BoardDeleteCommand(null, boardDao));
      commandMap.put("/board/detail", new BoardDetailCommand(null, boardDao));
      commandMap.put("/board/list", new BoardListCommand(null, boardDao));
      commandMap.put("/board/update", new BoardUpdateCommand(null, boardDao));

    } catch (Exception e) {
      System.out.println("DBMS에 연결할 수 없습니다!");
      throw e;
    }
  }

  private void service() {
    try (ServerSocket serverSocket = new ServerSocket(8888);) {
      System.out.println("애플리케이션 서버가 시작되었음!");
      while (true) {
        if (processClient(serverSocket.accept()) == STOP)
          break;
      } // while
      
      System.out.println("애플리케이션 서버를 종료함!");
    }
    // DBMS와의 연결을 끊는다.
    catch (Exception e) {
      System.out.println("소켓 통신오류");
      e.printStackTrace();
    }
  }

  private int processClient(Socket s) {
    int state = CONTINUE;
    try (Socket socket = s;
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      System.out.println("클라이언트와 연결됨!");

      // return value;
      while (true) {
        // 클라이언트가 보낸 명령을 읽는다.
        String request = in.readLine();
        if (request.contentEquals("quit")) {
          break;
        } else if (request.equals("serverstop")) {
          state = STOP;
          break;
        }
        Command command = commandMap.get(request);

        if (command == null) {
          out.println("해당 명령을 처리할 수 없습니다.");
          out.println("!end!");
          out.flush();
          continue;
        } else {
          command.execute(in, out);
        }
        out.println("!end!");
        out.flush();
      }
      System.out.println("클라이언트와 연결 끊음!");

      // 다른 클라이언트의 요청을 계속 처리할지 말지 상태값으로 알려준다.
    } catch (Exception e) {
      System.out.println("클라이언트와 통신 오류!");
    }
    return state;
    
  }

  public static void main(String[] args) {
    try {
      App app = new App();
      app.service();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}


