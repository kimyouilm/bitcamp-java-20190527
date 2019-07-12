// Wrapper 클래스: 사용 후
package ch11;

import java.util.Date;

public class Test09_3 {
  public static void main(String[] args) {

    // Primitive Type의 값을 전달할 때는 Test09_2의 경우와 같이
    // 각타입의 값을 받는 메소드를 여러개 만들어야 하지만,
    // Wrapper 클래스의 인스턴스를 사용하면 객체 주소로 다룰 수 있기 때문에
    // 타입의 종류와 상관없이 파라미터로 값을 받는 메소드를 한개만 만들어도 된다.
    Integer obj1 = Integer.valueOf(100);
    Float obj2 = Float.valueOf(3.14f);
    
    printObject(obj1); // primitive 값을 인스턴스에 담아서 넘길 수 있다. 
    printObject(obj2); // 이것이 Wrapper 클래스가 필요한 이유이다.
    printObject(Character.valueOf('A'));
    printObject(Boolean.valueOf(true));
    
  }
  
  static void printObject(Object obj) {
    System.out.println("값 => " + obj.toString());
  }
}
















