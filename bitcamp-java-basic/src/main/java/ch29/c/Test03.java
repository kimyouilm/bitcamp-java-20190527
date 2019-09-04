// 생성자 호출 - <constructor-arg> 의 value 속성
package ch29.c;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ch29.SpringUtils;

public class Test03 {
  public static void main(String[] args) {
    ApplicationContext iocContainer = 
        new ClassPathXmlApplicationContext("ch29/c/application-context-03.xml");
    
    SpringUtils.printObjects(iocContainer);
    System.out.println("---------------------------------------");
    
    System.out.println(iocContainer.getBean("c1"));
    System.out.println(iocContainer.getBean("c2"));
  }
}






