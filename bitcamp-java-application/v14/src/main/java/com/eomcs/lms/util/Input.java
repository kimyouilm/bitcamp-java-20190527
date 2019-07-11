package com.eomcs.lms.util;

import java.sql.Date;
import java.util.Scanner;

public class Input {
  
  // 키보드임. 
  // public static Scanner keyScan = new Scanner(new FileInputStream...);
  // 입력 대상을 고정시켜버림. 다른 용도로 사용하고싶어도 사용하지 못하게 고정되버림.
  // Input에 연결된 Scanner 객체를 각 Input마다 개별적으로 관리하기 위해 인스턴스 필드로 선언한다.
  private Scanner keyScan;

  public Input(Scanner keyScan) {
    // Input 클래스에 있는 메소드를 사용하는데 필요한 keyScan 변수를 준비시킨다.
    // 단, 인스턴스를 생성할 때 반드시 Scanner객체를 넘기도록 강제하기 위해
    // 생성자 파라미터 변수를 선언한다.
    this.keyScan = keyScan;
  }
  
  // 기존의 스태틱 메소드를 인스턴스 메소드로 전환한 이유?
  // => 각 Input 객체마다 SCanner를 구분해서 다루기 위함이다.
  public String getStringValue(String msg) {
    while (true) {
      try {
        System.out.print(msg);
        return keyScan.nextLine();
      } catch (NumberFormatException e) {
        System.out.println("잘못입력하셨습니다.");
      }
    }
  }

  public int getIntValue(String msg) {
    while (true) {
      try {
        System.out.print(msg);
        return Integer.parseInt(keyScan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("숫자를 입력하세요.");
      }
    }
  }

  public Date getDateValue(String msg) {
    while (true) {
      try {
        System.out.print(msg);
        return Date.valueOf(keyScan.nextLine());
      } catch (IllegalArgumentException e) {
        System.out.println("2019-07-05 형식으로 입력하세요.");
      }
    }
  }
}
