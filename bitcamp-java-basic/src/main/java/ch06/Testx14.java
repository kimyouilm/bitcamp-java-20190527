package ch06;

public class Testx14 {

  public static void main(String[] args) {
    
    System.out.printf("이름: %s\n", args[0]);
    
    int sum = 0;
    
    for (int i = 1; i < 4; i++) {
        sum += Integer.parseInt(args[i]);
    }
    
    double avg = sum/(args.length-1);
    
    System.out.printf("합계: %d\n", sum);
    System.out.printf("평균: %.2f\n", avg);
  }
}
