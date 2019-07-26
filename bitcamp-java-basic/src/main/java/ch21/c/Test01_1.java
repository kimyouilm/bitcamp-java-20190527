// 애플리케이션 예외의 종류: Exception 계열의 예외와 RuntimeException 계열의 예외
package ch21.c;

public class Test01_1 {

  public static void main(String[] args) {

    // 1) Exception 계열의 예외
    // => try ~ catch 를 강요하는 예외
    // => 예외처리를 하지 않으면 컴파일 오류가 발생한다.
    // => 단 Exception의 자식 클래스인 RuntimeException은 제외한다.
    //

    // Exception 예외가 발생할 수 있는 메소드 호출하기
//    divide(100, 2); // 컴파일 오류!
    // 이유?
    // divide() 메소드는 두 번째 값이 0일때 Exception 예외를 발생시킨다.
    // 메소드 시그너처에 그렇게 정의되어 있다.
    // Exception 계열의 예외가 발생할 수 있는 메소드를 호출할 때는
    // 반드시 try ~ catch 로 예외처리를 해야 한다.
    
    // 해결책?
    // 다음과 같이 try ~ catch ~ 블록에서 호출하라!

    int result = divide(100, 2); // OK
    System.out.println(result);
    // RuntimeException 예외가 발생할 수 있는 메소드를 호출할 경우
    // 컴파일러가 try ~ catch ~ 로 예외를 처리하라고 요구하지 않는다.
    // 그렇다고 특정 예외 조건에서 예외가 발생되지 않는 것은 아니다.
    // 다만 이 메소드에서 처리하지 않으면 이 메소드를 호출한 쪽에 예외를 자동으로 전달 한다.
    // main()을 호출한 것은 JVM이기 때문에
    // main()에서 예외를 처리하지 않으면 JVM에게 전달되고
    // JVM은 예외를 받는 즉시 실행을 멈춘다.
    
    result = divide(100, 0); // 예외발생! main() 호출자에게 전달, 즉 JVM에 전달! 즉시 종료!
    System.out.println(result);
    System.out.println("실행 종료");
  }
  
  static int divide (int a, int b ) throws RuntimeException{
    if (b == 0) {
      throw new RuntimeException("0으로 나눌수 없습니다.");
    }
    return a / b;
  }

}
