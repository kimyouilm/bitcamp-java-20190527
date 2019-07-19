// 애플리케이션 메인 클래스
// => 애플리케이션을 실행할 때 이 클래스를 실행한다.
package com.eomcs.lms;

import java.util.Scanner;
import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.LessonHandler;
import com.eomcs.lms.handler.MemberHandler;
import com.eomcs.util.Input;
import com.eomcs.util.Queue;
import com.eomcs.util.Stack;

public class App {

  static Scanner keyScan;
  static Stack<String> commandStack = new Stack<>();
  static Queue<String> commandQueue = new Queue<>();

  public static void main(String[] args) throws Exception {

    keyScan = new Scanner(System.in);

    // Input input2 = new Input(new Scanner(new FileInputStream("a.txt")));
    // Input 생성자를 통해 Input이 의존하는 객체인 Scanner를 주입한다.
    Input input = new Input(keyScan);

    // 각 핸들러의 생성자를 통해 의존 객체 "Input"을 주입한다.
    // => 이렇게 어떤 객체가 필요로 하는 의존 객체를 주입하는 것을 "의존성 주입(Dependency Injection; DI)라고 한다.
    // => ㅇDI를 전문적으로 관리해주는 프레임워크가 있으니 그 이름도 유명한 Spring Ioc컨테이너!
    // => 우리는 Spring Ioc 컨테이너를 간단하게 나마 만들어 볼 것이다.
    LessonHandler lessonHandler = new LessonHandler(input);
    MemberHandler memberHandler = new MemberHandler(input);
    BoardHandler boardHandler = new BoardHandler(input);


    while (true) {

      String command = prompt("명령>>");
      commandStack.push(command); // 사용자가 입력한 명령을 보관한다.
      commandQueue.offer(command); // 사용자가 입력한 명령을 보관한다.
      if (command.equals("quit")) {
        break;
      } else if (command.equals("history")) {
        printCommandHistory();
      } else if (command.equals("history2")) {
        printCommandHistory2();
      } else if (command.equals("/lesson/add")) {// 참이라면 add를 else면 그밖의 것 실행
        lessonHandler.addLesson();// addLesson() 메서드 블록에 묶어 놓은 코드를 실행한다.

      } else if (command.equals("/lesson/list")) {
        lessonHandler.listLesson();

      } else if (command.equals("/lesson/detail")) {
        lessonHandler.detailLesson();

      } else if (command.equals("/lesson/update")) {
        lessonHandler.updateLesson();

      } else if (command.equals("/lesson/delete")) {
        lessonHandler.deleteLesson();

      } else if (command.equals("/member/add")) {
        memberHandler.addMember();

      } else if (command.equals("/member/list")) {
        memberHandler.listMember();

      } else if (command.equals("/member/detail")) {
        memberHandler.detailMember();

      } else if (command.equals("/member/update")) {
        memberHandler.updateMember();

      } else if (command.equals("/member/delete")) {
        memberHandler.deleteMember();

      } else if (command.equals("/board/add")) {
        boardHandler.addBoard();

      } else if (command.equals("/board/list")) {
        boardHandler.listBoard();

      } else if (command.equals("/board/detail")) {
        boardHandler.detailBoard();

      } else if (command.equals("/board/update")) {
        boardHandler.updateBoard();

      } else if (command.equals("/board/delete")) {
        boardHandler.deleteBoard();

      } else {
        System.err.println("뿌우우우우우우우우우");
        System.out.println("실행할 수 없는 명령입니다.");
      }
    }
  }

  // shallow copy하면 안됨 => Node까지 그대로 복사가 되버림(원본이랑 복제랑 같은 노드가 되버림)
  // 그래서 deep copy를 해야함
  private static void printCommandHistory() throws Exception {
    Stack<String> stack = commandStack.clone();
    while (!stack.empty()) {
      System.out.println(stack.pop());
    }
  }

  private static void printCommandHistory2() throws Exception {
    Queue<String> queue = commandQueue.clone();
    int count = 0;
    while (!queue.empty()) {
      System.out.println(queue.poll());
      // 5 10 ~ 5의 배수일때 마다
      if (++count % 5 == 0) {
        System.out.print(":");
        if (keyScan.nextLine().equalsIgnoreCase("q"))
          break;
      }
    }
  }

  private static String prompt(String message) {
    System.out.println(message);
    return keyScan.nextLine();
  }
}
