package ch12.d;
// 캡슐화(encapsulation) : 적용 전
public class Test01 {
  
  public static void main(String[] args) {

    Patient p = new Patient();
    p.name = "김영희";
    p.age = 20;
    p.weight = 60;
    p.height = 157;
    p.gender = Patient.WOMAN; // woman 
    
    // p만 불러도 toString값이 출력됨
    System.out.println(p);
    
    
    // 환자를 컴퓨터에서 다루려면 데이터화 해야한다.
    // Patient는 이럴 목적으로 적의한 클래스이다.
    // 이렇게 Patient의 경우처럼 컴퓨터에서 다루기 위해 데이터화하여 정의하는 것을
    // "추상화"라 부른다.
    // 꼭 데이터만  해당하는 것은 아니다.
    // MemberHandler 클래스의 경우처럼 특정 업무를 정의하는 것도
    // "추상화"라 부른다.
    // => 즉 실세계의 객체(예: 사람, 물건, 업무)를 컴퓨터에서 다룰 수 있도록 클래스로 정의하는 행위를 "추상화"라 부른다.
    
    Patient p2 = new Patient();
    p2.name = "이철희";
    p2.age = 300;
    p2.weight = -50;
    p2.height = 400;
    p2.gender = Patient.MAN; // woman 
    
    // p만 불러도 toString값이 출력됨
    System.out.println(p2);
    
  }

}
