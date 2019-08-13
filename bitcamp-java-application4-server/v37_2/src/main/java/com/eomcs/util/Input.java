package com.eomcs.util;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;

// 클라이언트에서 입력 받는 기능을 수행
public class Input {

  // Input에 연결된 Scanner 객체를 각 Input 마다 개별적으로 관리하기 위해 인스턴스 필드로 선언한다.
  private Scanner keyScan;
  static final String INPARAM_MESSAGE = "!{}!";

  private static void requestParameterWithCaption(BufferedReader in, PrintStream out, String title)
      throws Exception {
    out.println(title);
    // 클라이언트에게 데이터를 주세여~
    out.println(INPARAM_MESSAGE);
    out.flush();
  }

  public Input(Scanner keyScan) {
    // Input 클래스에 있는 메서드를 사용하는데 필요한 keyScan 변수를 준비시킨다.
    // 단 인스턴스를 생성할 때 반드시 Scanner 객체를 넘기도록 강제하기 위해
    // 생성자에 파라미터 변수를 선언한다.
    this.keyScan = keyScan;
  }

  // 기존의 스태틱 메서드를 인스턴스 메서드로 전환한 이유?
  // => 각 Input 객체 마다 Scanner를 구분해서 다루기 위함이다.
  public static int getIntValue(BufferedReader in, PrintStream out, String title) throws Exception {
    requestParameterWithCaption(in, out, title);
    return Integer.parseInt(in.readLine());
  }

  public static Date getDateValue(BufferedReader in, PrintStream out, String title) throws Exception {
    requestParameterWithCaption(in, out, title);
    return Date.valueOf(in.readLine());
  }

  public static String getStringValue(BufferedReader in, PrintStream out, String title) throws Exception {
    requestParameterWithCaption(in, out, title);
    Lesson lesson = new Lesson();
    Date temp1 = lesson.getStartDate();
    Date temp2 = lesson.getEndDate();
    try {
      if (lesson.getStartDate() == null) {
        lesson.setStartDate(temp1);
      }
      if (lesson.getEndDate() == null) {
        lesson.setEndDate(temp2);
      }
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
    return in.readLine();
  }
}
