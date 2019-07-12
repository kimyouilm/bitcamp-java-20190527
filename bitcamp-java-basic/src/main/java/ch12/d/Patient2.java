package ch12.d;

public class Patient2 {
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
  }

  public int getHeight() {
    return this.height;
  }

  public void setHeight(int height) {
    if (height > 1 && height < 300)
      this.height = height;
  }

  public int getWeight() {
    return this.weight;
  }

  public void setWeight(int weight) {
    if (weight > 0 && weight < 500)
      this.weight = weight;
  }

  public int getGender() {
    return this.gender;
  }

  public void setGender(int gender) {
    if (gender > 0 && gender < 3)
      this.gender = gender;
  }

  @Override
  public String toString() {
    // number는 %s나 %d 상관없움
    return String.format("name=%s, age=%s, height=%d, weight=%s, gender=%d,", this.name, this.age,
        this.height, this.weight, this.gender);
  }


}
