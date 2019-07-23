// 중첩 클래스 사용  I : static nested class 와 inner 클래스 사용
package ch19.a;
 class X {
   
   static class A {}
   
   class B {}
   static void m1() {
     // 같은 스태틱 맴버이기 때문에 static nested class를 바로 사용할 수 있다.
     A obj = new A();
     
     // 스태틱 맴버는 인스턴스 맴버를 바로 사용할 수 없다.
     B obj2; // 레퍼런스를 선언할 때는 바로 사용할 수 있다.
//     obj2 = new B(); //컴파일 오류!
     
     // 컴파일 오류가 발생한 이유?
     // => 다음과 같이 스태틱 메소드에서 인스턴스 메소드를 호출하지 못하는 이유와 같다.
     // => 스태틱 메소드에는 인스턴스 주소를 담은 this라는 변수가 없기 때문이다.
     // => 인스턴스 맴버는 사용하려면 반드시 인스턴스 주소가 있어야 한다.
//     m2(); // 컴파일 오류
   }
   
   void m2() {
     
   }
 }
public class Test03 {

  static class A {}

  class B {} 

  public static void main(String[] args) {
    // 스태틱 맴버는 static nested class를 바로 사용할 수 있다.
  }

  // 스태틱 메서드 
  public static void m1() {
    // 스태틱 멤버는 오직 같은 스태틱 멤버만 사용할 수 있다.
    A obj = new A();
    
    // 스태틱 멤버는 인스턴스 주소를 담는 this라는 built-in 변수가 없기 때문에 
    // 인스턴스 멤버(필드, 메서드, inner 클래스)를 사용할 수 없다.
    B obj2; // 레퍼런스 변수를 선언할 때는 inner 클래스를 사용할 수 있다.
    //obj2 = new B(); // 컴파일 오류!
    
    // 다른 로컬 멤버(변수, 중첩 클래스)는 사용할 수 없다.
    //C obj3; // 컴파일 오류! C 클래스는 main()에서만 사용할 수 있다.
    //obj3 = new C(); // 컴파일 오류!
    
    
  }

}







