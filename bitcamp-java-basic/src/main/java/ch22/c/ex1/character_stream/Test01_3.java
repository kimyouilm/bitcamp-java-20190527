// 버퍼 사용(for 텍스트 파일) - 사용 전
// 
package ch22.c.ex1.character_stream;

public class Test01_3 {
  public static void main(String[] args) throws Exception{
    
      BufferedReader in = new BufferedReader("temp/jls12.pdf");
      
      System.out.println("데이터 읽는 중...");
      
      long start = System.currentTimeMillis();
      
      int b;
      int count = 0;
      while ((b = in.read()) != -1) {
        count++;
      }
      
      long end = System.currentTimeMillis();
      System.out.println(end - start);
      System.out.println(count);
      
      in.close();
    
    System.out.println("출력 완료!");
  }
}







