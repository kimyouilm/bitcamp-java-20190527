package ch14.b;

public class X2 extends X1{
  @Override
  void m1() {
    System.out.println("X2.m1()");
  }
  
  void test() {
    // this에 저장된 인스턴스 주소의 클래스부터 m1()을 찾아 올라간다.
    // => this가 가리키는 객체(인스턴스)의 클래스에서 m1()을 찾아 호출한다.
    // => this 객체의 타입부터 m1()을 클래스에서 m1()을 찾아 호출한다.
    // => this의 타입부터 m1()을 클래스에서 m1()을 찾아 호출한다.
    // 만약 해당 클래스에 메소드가 없다면 이 메소드가 소속된 현재 클래스부터 메소드(상위클래스)를 찾아 올라간다.
    this.m1();
    // this의 super Class부터 m1()을 찾아 올라간다.
    // 이 메소드가 있는 현재 클래스의 수퍼 클래스부터 m1()을 찾아 올라간다.
    super.m1();
  }
}
