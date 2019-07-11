package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App3 {

  static Scanner scan;

  public static void main(String[] args) {
    scan = new Scanner(System.in);
    
    Board[] board = new Board[100];
    int i = 0;
    for (; i < board.length; i++) {
      Board b = new Board();
      
      b.num = getIntValue("번호?");
      b.memo = getStringValue("메모?");
      b.date = new Date(System.currentTimeMillis());
//      b.date = getDateValue("언제?");
      b.views = getIntValue("조회수?");
      
      board[i] = b;
      
      System.out.println("계속 하실?(y/n)");
      String key = scan.nextLine();
      if(key.equalsIgnoreCase("n"))
        break;
    }
    
    for (int j = 0; j <= i; j++) {
      Board b = board[j];
      
      System.out.printf("%s, %s, %s, %s", b.num, b.memo, b.date, b.views);
    }
    
  }
  private static Date getDateValue(String meg) {
    while(true) {
      try {
        System.out.println(meg);
        return Date.valueOf(scan.nextLine());
      }catch (IllegalArgumentException e) {
        System.out.println("다시써");
      }
    }
  }
  private static String getStringValue(String meg) {
    while(true) {
      try {
        System.out.println(meg);
        return scan.nextLine();
      }catch(Exception e) {
        System.out.println("잘못입력했다.");
      }
    }
  }
  private static int getIntValue(String meg) {
    while(true) {
      try {
        System.out.println(meg);
        return Integer.parseInt(scan.nextLine());
      }catch (NumberFormatException e) {
        System.out.println("다시입력");
      }
    }
    
  }

}
