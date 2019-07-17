// Object 클래스 - getClass() : 해당 클래스의 정보를 리턴한다.
package ch15;

class My11 {
}

public class Test11_1 {
  public static void main(String[] args) {
    My11 obj1 = new My11();
    
    // 레퍼런스를 통해서 인스턴스의 클래스 정보를 알아낼 수 있다.
    // 넘어 오는 클래스가 어떤거이든 상관이 없음 => ?
    Class<?> classInfo = obj1.getClass(); // getClass는 override 하지 않는다.
    
    // 클래스 정보로부터 다양한 값을 꺼낼 수 있다. 
    System.out.println(classInfo.getName()); // 패키지 이름을 포함한 이름
    System.out.println(classInfo.getSimpleName()); // 포함하지 않은 이름
  }
}






