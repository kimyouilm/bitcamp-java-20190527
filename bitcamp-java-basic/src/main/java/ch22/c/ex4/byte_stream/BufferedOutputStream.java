package ch22.c.ex4.byte_stream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public class BufferedOutputStream extends DecoratorOutputStream {

  byte[] buf = new byte[1024];
  int size = 0;

  public BufferedOutputStream(OutputStream other) throws FileNotFoundException {
    super(other);
    size = 0;
  }

  @Override
  public void write(int b) throws IOException {
    buf[size++] = (byte) b;

    if (size >= buf.length) { // 배열이 꽉 찼으면 파일로 출력한다.
      other.write(buf); // 실제 출력할 때 생성자에게 받은 OutputStream 객체에게 맡겨야 한다.
      size = 0;
    }
  }

  @Override
  public void flush() throws IOException {
    // 배열에 출력할 베이터가 남아 있다면 파일로 출력한다.
    if (size > 0) {
      other.write(buf, 0, size);
    }
    // 원래 수퍼 클래스에서 하던 일이 있다면 그대로 수행시킨다.
    other.flush(); // flush() 또한 생성자에서 받은 OutputStream 객체에게 전달해야 한다.
  }
}
