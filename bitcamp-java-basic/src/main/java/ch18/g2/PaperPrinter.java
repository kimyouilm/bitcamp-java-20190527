// 인터페이스 구현 
package ch18.g2;

public class PaperPrinter implements Printer {
  // Printer 인터페이스에 선언된 규칙에 따라 기능을 구현한다.
  // B에 선언된 메서드 구현 
  @Override
  public void print (String text) {
    System.out.println("PaperPrinter: " + text);
  }

  @Override
  public void watermark(String title) {
    // TODO Auto-generated method stub
    
  }
}
