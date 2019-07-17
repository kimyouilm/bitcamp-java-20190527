// Object 클래스 - clone() : shallow clone
// -> 원본 참조변수의 참조값만 복사하는것.
// 원본이나 복사본을 수정하면 둘다 동일한 객체를 참조하기에 같이 변경됨.
// => 참조형 필드는 '번지만 복제'되기때문에 같은 객체를 참조하게 된다.
// 원본값이 바뀌어도 복제본의 값에는 영향을 미치진 않는다.
package ch15;
//clone()은 인스턴스를 복제할 때 호출하는 메서드이다.
public class Test13_1 {
  
  static class Engine implements Cloneable {
    int cc;
    int valve;
    
    public Engine(int cc, int valve) {
      this.cc = cc;
      this.valve = valve;
    }
    
    @Override
    public String toString() {
      return "Engine [cc=" + cc + ", valve=" + valve + "]";
    }

    @Override
    public Engine clone() throws CloneNotSupportedException {
      return (Engine) super.clone();
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
      return (Car) super.clone();
    }
  }
  
  public static void main(String[] args) throws Exception {
    Engine engine = new Engine(3000, 16);
    Car car = new Car("비트자동차", "비트비트", engine);
    
    // 자동차 복제
    Car car2 = car.clone();
    
    System.out.println(car == car2);
//    car.name = "헬로우헬로우";
    car.engine.cc = 12222223;
    System.out.println(car);
    System.out.println(car2);
    System.out.println(car.engine == car2.engine);
    System.out.println(car.engine.hashCode());
    System.out.println(car2.engine.hashCode());
    
    // clone()은 해당 객체의 필드 값만 복제한다.
    // 그 객체가 포함하고 있는 하위 객체는 복제하지 않는다.
    // "shallow copy(얕은 복제)"라 부른다.
    //
    // 그 객체가 포함하고 있는 하위 객체까지 복제하는 것을 
    // "deep copy(깊은 복제)"라 부른다.
    // deep copy는 개발자가 직접 clone() 메서드 안에 
    // deep copy를 수행하는 코드를 작성해야 한다.
  }
}









