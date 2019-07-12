package ch12.d;

import javax.management.RuntimeErrorException;

public class Patient3 {
  public static final int WOMAN = 1;
  public static final int MAN = 2;
  private String name;
  private int age;
  private int height;
  private int weight;
  private int gender;

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return this.age;
  }

  public void setAge(int age) {
    if (age > 0 && age < 150)
      this.age = age;
    else
      throw new RuntimeException("나이값이 유효하지 않습니다!");
  }

  public int getHeight() {
    return this.height;
  }

  public void setHeight(int height) {
    if (height > 1 && height < 300)
      this.height = height;
    else
      throw new RuntimeException("키값이 유효하지 않습니다!");
  }

  public int getWeight() {
    return this.weight;
  }

  public void setWeight(int weight) {
    if (weight > 0 && weight < 500)
      this.weight = weight;
    else
      throw new RuntimeException("몸무게값이 유효하지 않습니다!");
  }

  public int getGender() {
    return this.gender;
  }

  public void setGender(int gender) {
    if (gender > 0 && gender < 3)
      this.gender = gender;
    else
      throw new RuntimeException("성별의값이 유효하지 않습니다!");
  }

  @Override
  public String toString() {
    // number는 %s나 %d 상관없움
    return String.format("name=%s, age=%s, height=%d, weight=%s, gender=%d,", this.name, this.age,
        this.height, this.weight, this.gender);
  }


}
