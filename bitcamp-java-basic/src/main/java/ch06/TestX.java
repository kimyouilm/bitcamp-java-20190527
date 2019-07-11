package ch06;

public class TestX {

  public static void main(String[] args) {

    String name = System.getProperty("name");
    String s1 = System.getProperty("kor");
    String s2 = System.getProperty("eng");
    String s3 = System.getProperty("math");
    
    if (name == null || s1 == null || s2 == null || s3 == null) {
      System.out.println("실행 형식: java -cp ./bin/main -Dname=이름 -Dkor=국어점수 -Deng=영어점수 -Dmath=수학점수 ch06.Test17");
      return;
    }
    int kor = Integer.parseInt(s1);
    int eng = Integer.parseInt(s2);
    int math = Integer.parseInt(s3);
    
    int sum = kor + eng + math;
    double avg = sum/3;
    System.out.println("이름: " + name);
    System.out.println("국어: " + kor);
    System.out.println("영어: " + eng);
    System.out.println("수학: " + math);
    System.out.println("합계: " + sum);
    System.out.printf("평균: %.2f", avg);
    
    
  }

}
