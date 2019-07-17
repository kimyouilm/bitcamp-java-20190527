// Object 클래스 - clone() : shallow copy
package ch15;

// clone()은 인스턴스를 복제할 때 호출하는 메서드이다.

public class Test13_2 {

  static class Engine implements Cloneable {
    int cc;
    int valve;
    
    public Engine(int cc, int valve) {
      this.cc = cc;
      this.valve = valve;
    }
    
    // public으로 만들기 위해서 override한거임
    @Override
    public Engine clone() throws CloneNotSupportedException {
      return (Engine)super.clone();
    }

    @Override
    public String toString() {
      return "Engine [cc=" + cc + ", valve=" + valve + "]";
    }
    
  }
  static class Car implements Cloneable {
    String maker;
    String name;
    Engine engine;

    public Car(String maker, String name, Engine engine) {
      this.maker = maker;
      this.name = name;
      this.engine = engine;
    }

    @Override
    public String toString() {
      return "Car [maker=" + maker + ", name=" + name + ", engine=" + engine + "]";
    }

    @Override
    public Car clone() throws CloneNotSupportedException {
      // deep copy
      // => 포함하고 있는 하위객체에 대한 복제를 수행하려면 다음과 같이
      // 개발자가 하위 객체를 복제하는 코드를 작성해야한다.
      Car copy = (Car) super.clone();
      copy.engine = this.engine.clone();
      return copy;
      
    }
  }

  public static void main(String[] args) throws Exception {

    Engine engine = new Engine(3000, 16);
    Car car = new Car("비트자동차", "비트비트", engine);
    
    // 자동차 복제
    Car car2 = car.clone();
    
    System.out.println(car == car2);
    System.out.println(car);
    System.out.println(car2);
    System.out.println(car.engine == car2.engine);
    
    // clone()은 해당 객체의 필드 값만 복제한다.
    // 그 객체가 포함하고 있는 하위 객체는 복제하지않는다.
    // shallow copy(얕은 복제) 라고한다.
    
    // 그 객체가 포함하고 있는 하위 객체까지 복제하는 것을
    // "deep copy"라 부른다.
    // deep copy는 개발자가 직접 clone() 메소드 안에
    // deep copy를 수행하는 코드를 작성해야 한다.
  }
}


