// 팩토리 클래스를 통해 객체 생성하기 III - 스프링 IoC 컨테이너의 FactoryBean 규칙에 따라 공장 클래스 만들기
package ch29.f;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ch29.SpringUtils;

public class Test03 {
  public static void main(String[] args) {
    ApplicationContext iocContainer = 
        new ClassPathXmlApplicationContext("ch29/f/application-context-03.xml");
    
    // Car() c1 : ch29.f.Car 가 출력됨 (public class CarFactoryBean implements FactoryBean<Car>)
    // 주어진 클래스가 FactoryBean이라는 인터페이스를 구현한 놈이라면 그놈을 이름으로 저장하는게 아니라 
    // factoryBean이 아니라면 그 주소를 c1으로 하고, 맞다면...getObject의 리턴값인 Car가 나옴 // ** 체크 **
    SpringUtils.printObjects(iocContainer);
    System.out.println("---------------------------------------");
    
//    System.out.println(iocContainer.getBean("blackBox")); // BlackBox 객체 리턴
//    System.out.println(iocContainer.getBean("carFactory"));// CarFactor2 객체 리턴
//    System.out.println(iocContainer.getBean("c1")); // Car 객체 리턴 
//    System.out.println(iocContainer.getBean("c1")); // 기존에 생성된 Car 객체 리턴
    
  }
}






