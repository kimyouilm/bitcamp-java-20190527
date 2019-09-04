// Java config - @Configuration 과 @Bean 
package ch29.j;
// annotation으로 객체를 못만드는 경우는 => 우리가 만든게 아닐때
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ch29.SpringUtils;

public class Test04 {
  public static void main(String[] args) {
    
    ApplicationContext iocContainer = 
        new AnnotationConfigApplicationContext(AppConfig4.class);
    
    System.out.println("---------------------------------------");
    SpringUtils.printObjects(iocContainer);
    System.out.println("---------------------------------------");
    
    System.out.println(iocContainer.getBean("sub1Car"));
    System.out.println(iocContainer.getBean("sub2Car"));
    System.out.println(iocContainer.getBean("car2"));
  }
}






