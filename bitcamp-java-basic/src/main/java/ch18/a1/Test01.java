// 인터페이스 사용전
package ch18.a1;

public class Test01 {

  public static void main(String[] args) {
    
    // 객체의 기능을 사용해 보자!
    // => 각 도구의 사용법이 다르기 때문에 각 도구에 맞는 메소드를 준비 해야 한다.
    
    // A 규칙을 따르지 않은 객체를 파라미터에 넘길 수 없다.
    //use(new String("Hello")); // 컴파일 오류!

    // 객체의 기능을 사용해보자!
    // => 각 도구(객체)의 사용법이 다르기 때문에 각 도구에 맞춰서 기능을 사용해야 한다.
    // => 그래서 각 도구에 맞는 use() 메소드를 각각 따로 준비했다.
    
    // 1) ToolA 객체 사용하기
    use(new ToolA());
    
    // 2) ToolB 객체 사용하기
    use(new ToolB());
  }
  
  static void use(ToolA tool) {
    // ToolA 객체를 사용하려면 m1()메소드를 호출해야 한다.
    tool.m1();
  }

  static void use(ToolB tool) {
    // ToolB 객체를 사용하려면 m2() 메소드를 호출해야 한다.
  }
}







