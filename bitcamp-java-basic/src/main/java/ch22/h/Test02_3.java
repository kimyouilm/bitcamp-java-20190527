// 파일 입출력 객체의 데코레이터 연결하기 - try-with-resources
package ch22.h;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test02_3 {
  public static void main(String[] args) {

    // primitive 타입
    try (DataOutputStream out =
        new DataOutputStream(new BufferedOutputStream(new FileOutputStream("temp3/data.bin")))) {


      out.write(0x11223344);

    } catch (IOException e) {
      System.out.println("파일 입출력 예외 발생");
      e.printStackTrace();
    }finally {
      // try- with-resources 문법은 try 벗어날 때 자동으로 close()가 호출된다.
      // 따라서 finally 블록에서 따로 close()를 호출할 필요가 없다ㅏ.
      // close()가 아니라 그냥 다른 마무리를 할 일이 있다면
      // 이 블록에 작성하라!
    }

  }
}


