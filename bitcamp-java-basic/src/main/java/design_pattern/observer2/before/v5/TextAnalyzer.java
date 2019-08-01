package design_pattern.observer2.before.v5;

import java.io.Reader;

public class TextAnalyzer {

  Reader in;

  public TextAnalyzer(Reader in) {
    this.in = in;
  }

  public void execute() {
    try {
      int ch;
      int count = 0;
      int totalLine = 0;
      int totalLinecomment = 0;
      boolean startLinecomment = false;
      int countSlash = 0;
      boolean isEmpty = true;
      String mainClassName = null;
      StringBuffer line = new StringBuffer();

      while ((ch = in.read()) != -1) {
        count++;
        isEmpty = false;
        if (ch == '\n') {
          totalLine++;
          isEmpty = true;
        } else {
          isEmpty = false;
        }
        if (!startLinecomment) {
          if (ch == '/') {
            if (countSlash == 0) {
              countSlash++;
            } else {
              totalLinecomment++;
              startLinecomment = true;
            }
          } else {
            countSlash = 0;
          }
        } else if (ch == '\n') {
          startLinecomment = false;
        }

        // mainClassName의 값 알아내기
        // 만약 mainClassName이 없다면 -1
        if (ch == '\n') {
          if (line.indexOf("mainClassName") != -1) {
            int i = line.indexOf("\"");
            if (i != -1) {
              mainClassName = line.substring(i + 1, line.indexOf("\"", i + 1));
            }
            i = line.indexOf("'");
            if (i != -1) {
              mainClassName = line.substring(i + 1, line.indexOf("'", i + 1));
            }
          }
          line.setLength(0);
        } else {
          line.append((char)ch);
        }

      }
      if (!isEmpty) {
        totalLine++;
      }

      System.out.printf("총 읽은 문자 수 : %d\n", count);
      System.out.printf("총 줄 수 : %d\n", totalLine);
      System.out.printf("총 한 줄 주석 수 : %d\n", totalLinecomment);
      System.out.printf("mainClassName: %s\n", mainClassName);
    } catch (

    Exception e) {
      System.out.println("분석 중 오류 발생!");
    } finally {
      // 주의!
      // => 자원 해제는 그 자원을 관리하는 객체가 책임져야 한다.
      // => TextAnalyzer는 단지 Reader 자원을 생성자에서 받아서
      // excute()에서 사용할 뿐이다.
      // => 따라서 다음과 같이 사용이 끝났다고 해서
      // 여기서 자원을 해제해서는 안된다.
      // => 이 객체에 자원을 넘겨준 놈이 자원 해제에 책임을 져야한다.
      // try {in.close();} catch (Exception e) {}
    }
  }
}

