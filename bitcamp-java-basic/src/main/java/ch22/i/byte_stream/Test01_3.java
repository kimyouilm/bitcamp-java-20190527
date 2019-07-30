// 바이트 배열에 출력할 때 - Stream API를 사용하기
package ch22.i.byte_stream;

import java.io.ByteArrayOutputStream;

public class Test01_3 {
  public static void main(String[] args) {
    // 1~100 중에서 짝수만 바이트 배열에 넣어보자!

    // Test01_1.java와 Test01_2.java에서 값을 출력하는 방법이 다르다.
    // 메모리에 출력할 때는 바이트 배열을 직접 다루고 있다/
    // 그러나 파일에 출력할 때는 Stream API를 사용한다.
    // 그래서 데이터를 출력할 때 일관성이 없다. 즉 유지보수가 불편하다.
    // 그래서 이를 해결하기 위해 나온 방법이
    // 메모리를 출력할 때도 Stream API를 사용하는 것이다.
    // 1) 값을 메모리에 출력할 수 있도록 바이트 배열 출력 스트림을 준비한다.
    try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {

      // 2) 짝수를 바이트 배열에 출력한다.
      // => 오호라!!!! 파일에 출력n하는 것과 똑같다.
      // => 단 FileOutputStream과 달리 write()를 호출하여 값을 출력하면
      // 그 값은 ByteArrayOutputStream 객체의 내부에 보관된다.
      for (int i = 0; i < 100; i++) {
        if (i % 2 == 0)
          out.write(i);
      }
      System.out.println("출력완료!");
      // ByteArrayOutputStream에 저장된 값을 꺼내보자!
      byte[] bytes = out.toByteArray();
      for ( byte b :  bytes) {
        System.out.println(b + ", ");
      }
      System.out.println();
    } catch (Exception e) {
      System.out.println("파일 출력 예외 발생!");
      e.printStackTrace();
    }

  }
}


