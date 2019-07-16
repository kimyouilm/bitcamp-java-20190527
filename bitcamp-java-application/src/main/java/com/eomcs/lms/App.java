// 애플리케이션 메인 클래스
// => 애플리케이션을 실행할 때 이 클래스를 실행한다.
package com.eomcs.lms;
// test
import java.io.FileNotFoundException;
import java.util.Scanner;
import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.LessonHandler;
import com.eomcs.lms.handler.MemberHandler;
import com.eomcs.util.Input;

public class App {

  static Scanner keyScan;

  public static void main(String[] args) throws FileNotFoundException {

    keyScan = new Scanner(System.in);
    
//    Input input2 = new Input(new Scanner(new FileInputStream("a.txt")));
    // Input 생성자를 통해 Input이 의존하는 객체인 Scanner를 주입한다.
    Input input = new Input(keyScan);
    
    // 각 핸들러의 생성자를 통해 의존 객체 "Input"을 주입한다. 
    // => 이렇게 어떤 객체가 필요로 하는 의존 객체를 주입하는 것을 "의존성 주입(Dependency Injection; DI)라고 한다.
    // => ㅇDI를 전문적으로 관리해주는 프레임워크가 있으니 그 이름도 유명한 Spring Ioc컨테이너!
    // => 우리는 Spring Ioc 컨테이너를 간단하게 나마 만들어 볼 것이다. 
    LessonHandler lessonHandler = new LessonHandler(input);
    MemberHandler memberHandler = new MemberHandler(input);
    BoardHandler boardHandler1 = new BoardHandler(input);
    BoardHandler boardHandler2 = new BoardHandler(input);

    while (true) {

      String command = prompt("명령>>");
      if (command.equals("quit")) {
        break;
      } else if (command.equals("/lesson/add")) {// 참이라면 add를 else면 그밖의 것 실행
        lessonHandler.addLesson();// addLesson() 메서드 블록에 묶어 놓은 코드를 실행한다.

      } else if (command.equals("/lesson/list")) {
        lessonHandler.listLesson();

      } else if (command.equals("/member/add")) {
        memberHandler.addMember();

      } else if (command.equals("/member/list")) {
        memberHandler.listMember();

      } else if (command.equals("/board1/add")) {
        boardHandler1.addBoard();

      } else if (command.equals("/board1/list")) {
        boardHandler1.listBoard();

      } else if (command.equals("/board2/add")) {
        boardHandler2.addBoard();

      } else if (command.equals("/board2/list")) {
        boardHandler2.listBoard();

      } else {
        System.err.println("뿌우우우우우우우우우");
        System.out.println("실행할 수 없는 명령입니다.");
      }
    }
  }
  private static String prompt(String message) {
    System.out.println(message);
    return keyScan.nextLine();
  }
}
