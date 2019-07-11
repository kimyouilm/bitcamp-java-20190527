// 애플리케이션 메인 클래스
// => 애플리케이션을 실행할 때 이 클래스를 실행한다.
package com.eomcs.lms;

import java.util.Scanner;
import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.LessonHandler;
import com.eomcs.lms.handler.MemberHandler;
import com.eomcs.lms.util.Input;

public class App {
  
  private static Scanner keyScan;
  
  public static void main(String[] args) {
    keyScan = new Scanner(System.in);
    Input.keyScan = keyScan;
    LessonHandler.keyScan = keyScan;
    MemberHandler.keyScan = keyScan;
    
    BoardHandler boardHandler1 = new BoardHandler();
    BoardHandler boardHandler2 = new BoardHandler();
    LessonHandler lessonHandler1 = new LessonHandler();
    MemberHandler memberHandler1 = new MemberHandler();
    
    while (true) {
      System.out.print("명령> ");
      String command = keyScan.nextLine();
      
      if (command.equals("quit")) {
        break;
      } else if (command.equals("/lesson/add")) {// 참이라면 add를 else면 그밖의 것 실행
        lessonHandler1.addLesson();// addLesson() 메서드 블록에 묶어 놓은 코드를 실행한다.
        
      } else if (command.equals("/lesson/list")) {
        lessonHandler1.listLesson();
        
      } else if (command.equals("/member/add")) {
        memberHandler1.addMember();
        
      } else if (command.equals("/member/list")) {
        memberHandler1.listMember();
        
      } else if (command.equals("/board/add")) {
        boardHandler1.addBoard();
        
      } else if(command.equals("/board/list")) {
        boardHandler1.listBoard();
        
      } else if (command.equals("/board2/add")) {
        boardHandler2.addBoard();
        
      } else if(command.equals("/board2/list")) {
        boardHandler2.listBoard();
        
      }else {
        System.err.println("뿌우우우우우우우우우");
        System.out.println("실행할 수 없는 명령입니다.");
      }
    }
  }

  

}