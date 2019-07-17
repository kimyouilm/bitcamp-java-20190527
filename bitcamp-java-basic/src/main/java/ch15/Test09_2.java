// hash code 응용 IV - MyKey의 hashCode()와 equals() 오바리이딩 하기
// key로 사용할경우 hashCode와 equals를 사용
// 보통 equals와 hashCode는 한쌍임 같이 import해주자
package ch15;

import java.util.HashMap;


public class Test09_2 {
  static class MyKey2 {
    String contents;

    public MyKey2(String contents) {
      this.contents = contents;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((contents == null) ? 0 : contents.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      MyKey2 other = (MyKey2) obj;
      if (contents == null) {
        if (other.contents != null)
          return false;
      } else if (!contents.equals(other.contents))
        return false;
      return true;
    }

    @Override
    public String toString() {
      return "Key [contents=" + contents + "]";
    }
  }

  static class Student {
    String name;
    int age;
    boolean working;
    
    public Student(String name, int age, boolean working) {
      this.name = name;
      this.age = age;
      this.working = working;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + age;
      result = prime * result + ((name == null) ? 0 : name.hashCode());
      result = prime * result + (working ? 1231 : 1237);
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Student other = (Student) obj;
      if (age != other.age)
        return false;
      if (name == null) {
        if (other.name != null)
          return false;
      } else if (!name.equals(other.name))
        return false;
      if (working != other.working)
        return false;
      return true;
    }

    @Override
    public String toString() {
      return "Student [name=" + name + ", age=" + age + ", working=" + working + "]";
    }

  }

  public static void main(String[] args) {
    HashMap<MyKey2, Student> map = new HashMap<>();

    MyKey2 k1 = new MyKey2("ok");
    MyKey2 k2 = new MyKey2("no");
    MyKey2 k3 = new MyKey2("haha");
    MyKey2 k4 = new MyKey2("ohora");
    MyKey2 k5 = new MyKey2("hul");

    // String을 key로 사용해보자!
    map.put(k1, new Student("홍길동", 20, false));
    map.put(k2, new Student("임꺽정", 30, true));
    map.put(k3, new Student("유관순", 17, true));
    map.put(k4, new Student("안중근", 24, true));
    map.put(k5, new Student("윤봉길", 22, false));

    // HashMap
    // => 값을 저장할 때 key 객체의 해시코드를 이용하여 저장할 위치(인덱스)를 계산한다.
    // => 따라서 해시코드가 같다면 같은 key로 간주한다.
    
    // 값을 저장할 때 사용한 key객체로 값을 찾아 꺼낸다.
    System.out.println(map.get(k3));

    // 다른 key를 사용하여 값을 꺼내보자.
    MyKey2 k6 = new MyKey2("haha");

    System.out.println(k3 == k6);
    System.out.printf("k3(%s), k6(%s)\n", k3, k6);
    System.out.println(k3 == k6); // 인스턴스는 다르다.
    System.out.println(k3.hashCode()); // hash code는 다르다.
    System.out.println(k6.hashCode()); // hash code는 다르다.
    System.out.println(k3.equals(k6)); // equals()의 비교 결과도 다르다.

    // k3와 k6이 내용물이 같다 하더라도, (둘다 "haha"이다.)
    // hashCode()의 리턴 값이 다르고, equals() 비교 결과도 false 이기 때문에
    // HashMap 클래스에서는 서로 다른 key라고 간주한다.

    System.out.println(map.get(k6));

//    MyKey k7 = new MyKey("Haha");
//    System.out.println(map.get(k7));
  }

}