package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App2 {

  static Scanner scan;

  public static void main(String[] args) {
    scan = new Scanner(System.in);

    Member[] member = new Member[100];
    int size = 0;

    while (true) {
      System.out.println("명령>>");
      String command = scan.nextLine();

      if (command.equals("quit"))
        break;
      else if (command.equals("/member/add")) {
        Member m = new Member();
        // 사용자가 입력한 값을 Lesson인스턴스의 각 변수에 저장한다.
        m.name = getStringValue("수업명? ");
        m.email = getStringValue("메일?");
        m.pw = getStringValue("비번?");
        m.pic = getStringValue("사진?");
        m.phoneNum = getStringValue("폰번?");
        m.signUp = new Date(System.currentTimeMillis()); 

        member[size++] = m;
        System.out.println("저장하였습니다.");
      } else if (command.equals("/member/list")) {
        for (int i2 = 0; i2 < size; i2++) {
          Member m = member[i2];
          System.out.printf("%s, %s, %s, %s , %s, %s \n", m.name, m.email, m.pw, m.pic, m.phoneNum,
              m.signUp);
        }
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
    }

    System.out.println();
  }

  private static String getStringValue(String msg) {
    while(true) {
      try {
        System.out.print(msg);
        return scan.nextLine();
      } catch (NumberFormatException e) {
        System.out.println("잘못입력하셨습니다.");
      }
    }
  }

  private static Date getDateValue(String msg) {
    while(true) {
      try {
        System.out.print(msg);
        return Date.valueOf(scan.nextLine());
      } catch (IllegalArgumentException e) {
        System.out.println("2019-07-05 형식으로 입력하세요.");
      }
    }
  }

}
