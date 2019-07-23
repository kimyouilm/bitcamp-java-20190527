// 인터페이스 레퍼런스와 인스턴스의 관계
package ch18.a2;

public class Test01 {

  public static void main(String[] args) {

    // 인터페이스 레퍼런스 선언
    // => Spec 인터페이스를 구현한 클래스의 인스턴스 주소를 저장하겠다는 의미이다.
    // => Spec 규칙에 따라 작성한 클래스의 객체를 담겠다는 의미이다.
    // => Spec 객체를 담겠다는 의미이다.
    Spec tool;
    
    // ToolA 클래스는 Spec 인터페이스의 규칙에 따라 만든 클래스이기 때문에
    // 이클래스의 인스턴스 주소를 tool 레퍼런스 변수에 저장할 수 있다.
    tool = new ToolA();
    
    // ToolB클래스도 ToolA와 마찬가지로 
    // Spec 구현체(Implementer: 인터페이스 규칙에 따라 만든 클래스) 이기 때문에
    // 해당 객체를 저장할 수 있따.
    tool = new ToolB();
    // Spec 규칙에 따라 만든 도구를 사용한다.
    use(new ToolA());
    use(new ToolB());
    
    // String 클래스는 Spec 구현체가 아니기 때문에 해당 객체를 레퍼런스에 저장할 수 없다.

    // Spec 규칙을 따르지 않은 객체를 파라미터에 넘길 수 없다.
    //use(new String("Hello")); // 컴파일 오류!

  }
  
  static void use(Spec tool) {
    // tool 레퍼런스가 가리키는 인스턴스에 대해 A 규칙에 정의된 메서드를 호출한다.
    // 그러면 해당 인스턴스의 클래스를 찾아 메서드를 호출한다.
    tool.m1();
  }

}







