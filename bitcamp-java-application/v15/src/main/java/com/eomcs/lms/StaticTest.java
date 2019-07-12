package com.eomcs.lms;

public class StaticTest {

  public static void main(String[] args) {
    // static field 사용하기
    // => 클래스 이름으로 사용하면 된다.
//    Car.count = 0;
//    System.out.println(Car.count);
    
    // 인스턴스 필드 사용하기
    Car c1;
    c1 = new Car();
    c1.no = 1;
    c1.model = "티코";
    // static field는 보통 클래스 이름으로 사용한다.
    // 그러나 클래스 이름 대신 레퍼런스를 통해 스태틱 필드를 사용할 수 있다. 비추
    // 개발자가 인스턴스 필드라고 착각할 수 있다. 따라서 이런 식으로 사용하지 말자.
    c1.count++;
    Car.count++; // okay
    
    Car c2 = new Car();
    c2.no = 2;
    c2.model ="소나타";
    // 클래스 이름 대신 레퍼런스로 사요할 수 있다. 비추
    c2.count++;
    
    System.out.printf("%d, %s\n", c1.no, c1.model);
    System.out.printf("%d, %s\n", c2.no, c2.model);
    System.out.println(c1.count);
    System.out.println(c2.count);
    System.out.println(Car.count);

  }

}
