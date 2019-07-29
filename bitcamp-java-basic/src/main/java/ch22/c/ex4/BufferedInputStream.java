package ch22.c.ex4;

import java.io.IOException;
import java.io.InputStream;

// 데코레이터 설계 패턴을 적용하여 기존 InputStream에 기능을 추가한다.
// => DecoratorInputStream을 상속 받는다.
public class BufferedInputStream extends DecoratorInputStream {
  
  byte[] buf = new byte[8192];
  int size = 0;
  int cursor = 0;
  int count = 0;
  
  public BufferedInputStream(InputStream other) throws IOException {
    super(other);
  }
  
  @Override
  public int read() throws IOException {
    if (cursor >= size) { // 버퍼에 보관된 데이터를 다 읽었으면, 
      count++;
      size = other.read(buf); // 다시 상속 받은 메서드를 사용하여 1024 바이트를 읽어 온다.
      if (size == -1) // 물론 읽은 데이터가 없다면, 즉 파일의 데이터를 다 읽었다면 -1을 리턴한다.
        return -1;
      cursor = 0; // FileInputStream을 사용하여 새로 1024 바이트를 읽어 버퍼에 저장했다면,
    }             // 다시 커서의 위치를 0으로 설정한다.

    return buf[cursor++] & 0x000000ff; 
  }
  
}






