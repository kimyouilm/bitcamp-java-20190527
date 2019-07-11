package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App2 {

  static Scanner scan;

  public static void main(String[] args) {
    scan = new Scanner(System.in);

    Member[] member = new Member[100];

    int i = 0;
    for (; i < member.length; i++) {
      Member m = new Member();
      // 사용자가 입력한 값을 Lesson인스턴스의 각 변수에 저장한다.
      m.name = getStringValue("수업명? ");
      m.email = getStringValue("메일?");
      m.pw = getStringValue("비번?");
      m.pic = getStringValue("사진?");
      m.phoneNum = getStringValue("폰번?");
      m.signUp = getDateValue("언제?");

      member[i] = m;
      System.out.println("계속 입력 하시겠습니까?(Y or N) ");
      String response = scan.nextLine();

      if (response.equalsIgnoreCase("N"))
        break;
    }
    
    System.out.println();
    for (int i2 = 0; i2 <= i; i2++) {
      
      Member m = member[i2];
      
      System.out.printf("%s, %s, %s, %s , %s, %s\n", m.name, m.email, m.pw,
          m.pic, m.phoneNum, m.signUp);
    }
  }

  private static Date getDateValue(String meg) {
    while (true) {
      try {
        System.out.println(meg);
        return Date.valueOf(scan.nextLine());
      } catch (IllegalArgumentException e) {
        System.out.println("다시써");
      }
    }
  }

  private static String getStringValue(String meg) {
    while (true) {
      try {
        System.out.println(meg);
        return scan.nextLine();
      } catch (Exception e) {
        System.out.println("잘못입력했다.");
      }
    }
  }

}
