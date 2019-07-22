// 인터페이스와 디폴트 메소드
package ch18.g5;

import ch18.g2.FilmPrinter;
import ch18.g2.PaperPrinter;
import ch18.g2.Printer;
import ch18.g3.WaterMarkPrinter;

public class Test01 {

  public static void main(String[] args) {
    // 신규 프로젝트에서필요한 워터마킹 기능을 기존 클래스에 영향을 주지 않으면서  기존 규격에 포함시키는 방법 
    // => 디폴트 메소드를 추가하라!
    // => g2 패키지의 Printer 인터페이스에 watermark() 메소드는
    //    디폴트 메소드로 선언하면 된다.
    
    // 프린터 준비.
    WaterMarkPrinter p1 = new WaterMarkPrinter();
    display(p1, "안녕하세요!", "bitcamp bitcamp bitcamp bitcamp bitcamp");
    
    // 기존 프린터 준비!
    PaperPrinter p2 = new PaperPrinter();
    FilmPrinter p3 = new FilmPrinter();
    // 기존 규격(Printer)에 새 규칙(watermark())을 추가하더라도
    // 기존에 작성한 클래스를 그대로 활용할 수 있다
    // 굳이 g4패키지에서 한것처럼 어댑터 패턴을 사용할 필요도 없다.
    display(p2, "안녕하세요!", "bitcamp bitcamp bitcamp bitcamp bitcamp");
    display(p3, "안녕하세요!", "bitcamp bitcamp bitcamp bitcamp bitcamp");
    // 안타깝게도 기존 프린터는 새 프린터 규격에 맞지 않아서
    // 출력하는데 사용할 수 없다.
  }

  private static void display(Printer printer, // 기존 구격을 그대로 사용하면된다.
      String text, String watermarkText) {
    printer.watermark(watermarkText); // 기존 규격에 새로 추가한 메소드이다.
    printer.print(text);
    printer.watermark(watermarkText); // 기존 규격에 새로 추가한 메소드이다.
  }
}
