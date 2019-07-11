// 스태틱 맴버와 인스턴스 멤버
package ch10;

class Test02_1 {

  // static members
  
  // static field
  // => 클래스가 로딩될 때 생성된다.
  static int f1;
  
  // static block
  static {
    // static initialize block
    // => 스태틱 필드가 모두 생성된 후 실행된다.
  }
  
  // static method
  static void m1() {
    // => 클래스 이름으로 호출된다.
  }
  
  // instance member
  // instance field
  // => new 명령을 실행할때 Heap에 생성된다.
  int f2;
  
  // instance initialize block
  // => 인스턴스 필드가 생성된후 실행된다.
  {
    
  }
  
  // instance method
  // => 인스턴스가 있어야하만 호출할 수 있다.
  // => 매소드를 호출할 때 넘겨준 인스턴스 주소는
  //    this라는 내장 변수(built-in)에 보관된다.
  void m2() {
    
  }
  
  // constructor 
  // => 인스턴스 초기화 블록이 실행된후 생성자가 호출된다.
  // 물론, new 명령에서 어떤 생성자를 호출할지 지정해야 한다.
  Test02_1(){
    
  }
}


