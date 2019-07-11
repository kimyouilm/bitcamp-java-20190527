package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App2 {

  static Scanner scan;
	public static void main(String[] args) {
	    scan = new Scanner(System.in);
	    
	    int no = getIntValue("번호:");
	    String name = getStringValue("이름:");
	    String email = getStringValue("이메일:");
	    String pw = getStringValue("암호:");
	    String pic = getStringValue("사진:");
	    String phoneNum = getStringValue("전화:");
	    Date signUp = getDateValue("가입일: ");

	    System.out.println();
	    System.out.println("번호: " + no);
	    System.out.println("이름" + name);
	    System.out.println("이메일 " + email);
	    System.out.println("암호" + pw);
	    System.out.println("사진 " + pic);
	    System.out.println("총수업시간: 1000 시간 " + phoneNum);
	    System.out.println("일수업시간: 8 시간 " + signUp);
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
