// Object 클래스 - clone() : deep clone
// -> 원본과 복사본은 독립적인 객체임로 영향을 받지 않는다.
// => 참조형 필드가 참조하는 객체도 복제하기 때문에
// 복제된 참조 객체를 변경해도 원본객체가 변경되지 않음.
// 원본 값이 바뀌어도 다른번지의 복제된 다른 객체를 참조하기 때문에 원본의 값은 변하지 않음
// Object 클래스 - clone() : deep copy
package ch15;
 
public class Test13_2 {
  
  static class Engine implements Cloneable {
    int cc;
    int valve;
    
    public Engine(int cc, int valve) {
      this.cc = cc;
      this.valve = valve;
    }
    
//    @Override
//    public String toString() {
//      return "Engine [cc=" + cc + ", valve=" + valve + "]";
//    }

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

//    @Override
//    public String toString() {
//      return "Car [maker=" + maker + ", name=" + name + ", engine=" + engine + "]";
//    }

    @Override
    public Car clone() throws CloneNotSupportedException {
      // deep copy
      // => 포함하고 있는 하위 객체에 대한 복제를 수행하려면 다음과 같이 
      //    개발자가 직접 하위 객체를 복제하는 코드를 작성해야 한다.
      // 
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
//    car.name = "헬로우헬로우";
//    car.engine.cc = 12222223;
    System.out.println(car);
    System.out.println(car2);
    // 하위 객체까지 다 복사함
    // 복사된것은 원본을 바꿔도 clone은 바뀌지가 않음
//    System.out.println(car.name.hashCode());
//    System.out.println(car2.name.hashCode());
    System.out.println("----------");
    System.out.println(car.name.toString());
    System.out.println(car2.name.toString());
    System.out.println("----------");
    System.out.println(car.engine.toString());
    System.out.println(car2.engine.toString());
    System.out.println("----------");
    System.out.println(car.hashCode());
    System.out.println(car2.hashCode());
    System.out.println("==========");
    System.out.println(car.engine == car2.engine);
    System.out.println(car.name == car2.name);
  }
}










