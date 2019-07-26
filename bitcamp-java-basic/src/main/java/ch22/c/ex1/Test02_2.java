// 버퍼 사용(for 텍스트 파일) - 사용 전
// 
package ch22.c.ex1;

import java.io.FileInputStream;

public class Test02_2 {
  public static void main(String[] args) {
    
    try {
      FileInputStream in = new FileInputStream("temp/jls12.txt");
      
      System.out.println("데이터 읽는 중...");
      
      long start = System.currentTimeMillis();
      
      char[] buf = new char[8192];
      int b;
      int count = 0;
      while ((b = in.read()) != -1) {
        count++;
      }
      
      long end = System.currentTimeMillis();
      System.out.println(end - start);
      System.out.println(count);
      
      in.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    System.out.println("출력 완료!");
  }
}








