package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App3 {

  static Scanner scan;

  public static void main(String[] args) {
    scan = new Scanner(System.in);

    Board[] board = new Board[100];
    int size = 0;
    while (true) {
      System.out.print("명령>>");
      String command = scan.nextLine();
      if (command.equalsIgnoreCase("quit"))
        break;
      else if (command.equals("/board/add")) {
        Board b = new Board();

        b.num = getIntValue("번호?");
        b.memo = getStringValue("메모?");
        b.date = new Date(System.currentTimeMillis());
        // b.date = getDateValue("언제?");
        b.views = getIntValue("조회수?");

        board[size++] = b;

      } else if (command.equals("/board/list")) {
        for (int j = 0; j < size; j++) {
          Board b = board[j];

          System.out.printf("%s, %s, %s, %s", b.num, b.memo, b.date, b.views);
        }
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }

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

  private static int getIntValue(String meg) {
    while (true) {
      try {
        System.out.println(meg);
        return Integer.parseInt(scan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("다시입력");
      }
    }

  }

}
