package ch12.d;

public class Patient {
  public static final int WOMAN = 1;
  public static final int MAN = 2;
  String name;
  int age;
  int height;
  int weight;
  int gender;

  @Override
  public String toString() {
    // number는 %s나 %d 상관없움
    return String.format("name=%s, age=%s, height=%d, weight=%s, gender=%d,", 
        this.name, this.age, this.height, this.weight, this.gender);
  }


}
