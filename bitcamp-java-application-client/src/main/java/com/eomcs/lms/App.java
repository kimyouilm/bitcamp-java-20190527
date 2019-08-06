// client - v33_2: Stateful 통신 방식을 Stateless 통신 방식으로 변경한다.
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import com.eomcs.lms.client.BoardDaoProxy;
import com.eomcs.lms.client.LessonDaoProxy;
import com.eomcs.lms.client.MemberDaoProxy;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.handler.BoardAddCommand;
import com.eomcs.lms.handler.BoardDeleteCommand;
import com.eomcs.lms.handler.BoardDetailCommand;
import com.eomcs.lms.handler.BoardListCommand;
import com.eomcs.lms.handler.BoardUpdateCommand;
import com.eomcs.lms.handler.CalPlusCommand;
import com.eomcs.lms.handler.Command;
import com.eomcs.lms.handler.HiCommand;
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

  Scanner keyScan;

  private void service() {

    // Command 객체가 사용할 데이터 처리 객체를 준비한다.
    BoardDao boardDao = new BoardDaoProxy("localhost", 8888);
    LessonDao lessonDao = null; // new LessonDaoProxy(in, out);
    MemberDao memberDao = null; // new MemberDaoProxy(in, out);

    // 회원과 수업 데이터를 다루는 커멘드는 일단 ArrayList를 사용!
    ArrayList<Member> memberList = new ArrayList<>();
    ArrayList<Board> boardList = new ArrayList<>();
    ArrayList<Lesson> lessonList = new ArrayList<>();

    keyScan = new Scanner(System.in);

    Deque<String> commandStack = new ArrayDeque<>();
    Queue<String> commandQueue = new LinkedList<>();

    Input input = new Input(keyScan);

    HashMap<String, Command> commandMap = new HashMap<>();

    commandMap.put("/lesson/add", new LessonAddCommand(input, lessonDao));
    commandMap.put("/lesson/delete", new LessonDeleteCommand(input, lessonDao));
    commandMap.put("/lesson/detail", new LessonDetailCommand(input, lessonDao));
    commandMap.put("/lesson/list", new LessonListCommand(input, lessonDao));
    commandMap.put("/lesson/update", new LessonUpdateCommand(input, lessonDao));

    commandMap.put("/member/add", new MemberAddCommand(input, memberDao));
    commandMap.put("/member/delete", new MemberDeleteCommand(input, memberDao));
    commandMap.put("/member/detail", new MemberDetailCommand(input, memberDao));
    commandMap.put("/member/list", new MemberListCommand(input, memberDao));
    commandMap.put("/member/update", new MemberUpdateCommand(input, memberDao));

    commandMap.put("/board/add", new BoardAddCommand(input, boardDao));
    commandMap.put("/board/delete", new BoardDeleteCommand(input, boardDao));
    commandMap.put("/board/detail", new BoardDetailCommand(input, boardDao));
    commandMap.put("/board/list", new BoardListCommand(input, boardDao));
    commandMap.put("/board/update", new BoardUpdateCommand(input, boardDao));

    commandMap.put("/hi", new HiCommand(input));
    commandMap.put("/calc/plus", new CalPlusCommand(input));


    while (true) {

      String command = prompt();

      if (command.length() == 0)
        continue;

      commandStack.push(command); // 사용자가 입력한 명령을 보관한다.
      commandQueue.offer(command); // 사용자가 입력한 명령을 보관한다.

      Command executor = commandMap.get(command);

      if (command.equals("serverstop")) {
        // out.writeUTF(command);
        // out.flush();
        break;
      } else if (command.equals("history")) {
        printCommandHistory(commandStack);

      } else if (command.equals("history2")) {
        printCommandHistory(commandQueue);

      } else if (executor != null) {
        executor.execute();

      } else {
        System.out.println("해당 명령을 지원하지 않습니다!");
      }

      System.out.println();
    }
  }

  private void printCommandHistory(Iterable<String> list) {
    Iterator<String> iterator = list.iterator();
    int count = 0;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      if (++count % 5 == 0) {
        System.out.print(":");
        if (keyScan.nextLine().equalsIgnoreCase("q"))
          break;
      }
    }
  }

  private String prompt() {
    System.out.print("명령> ");
    return keyScan.nextLine();
  }

  private void send() {

  }

  public static void main(String[] args) {
    App app = new App();
    app.service();
  }
}
