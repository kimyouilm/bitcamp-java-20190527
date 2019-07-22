package ch18.f;

// 자동차의 기능을 갖추려면 CarSpec을 만족 시켜야 하는데,
// 직접 CarSpec 인터페이스를 구현하는 대신에
// CarSpec을 미리 구현한 AbstractCar를 상속 받는 것이
// 클래스를 만들기가 편하다.
public class Sonata extends AbstractCar {

  // 그리고 서브 클래스에게 구현하라고 맡겨진 추상 메서드를 정의한다.
  @Override
  public void run() {
    System.out.println("씽씽~~ 달린다!");
  }
}
