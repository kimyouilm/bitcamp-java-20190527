package ch29.h;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

// @Autowired 애노테이션을 처리해주는 역할을 한다.
// 스프링 IoC 컨테이너가 객체를 생성한 후 보고를 하면
// 이 클래스는 생성된 객체에서 @Autowired가 붙은 세터를 찾는다.
// 있다면 세터를 호출하여 의존 객체를 주입한다.
// 의존 객체가 없다면 그 의존 객체가 생성될 때까지
// 별도로 담아 둔다.
// 의존 객체가 생성되는 순간 즉시 별도로 담아 둔 그 객체에 대해 셋터를 호출할 것이다.
//
public class MyAutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {
  // 생성된 모든 객체를 기록한다.
  HashMap<Class<?>, List<Object>> beans = new HashMap<>();

  // 파라미터 값이 준비되지 않아서 호출이 연기된 @Autowired 메서드를 기록한 맵
  // method목록~
  HashMap<Class<?>, List<AutowiredMethod>> autowiredMethods = new HashMap<>();

  private void addBean(Class<?> type, Object bean) {
    List<Object> objList = beans.get(type);
    // 해당 타입의 객체 목록이 없다면
    if (objList == null) {
      // 새로 객체를 저장할 목록을 준비한다.
      objList = new ArrayList<>();
      // beans에 Car.class정보로 저장하라. => 지금은 빈배열.
      beans.put(type, objList);
    }
    // 처음에 c1을 저장했으니까 첫번째 list에는 c1이 들어감.
    objList.add(bean);
  }

  private Object getBean(Class<?> type) {
    List<Object> objList = beans.get(type);
    if (objList == null) {
      // 해당 타입의 객체 목록이 없다면 당연히 return 값을 null이 나옴
      return null;
    }
    return objList.get(0);
  }

  private int countBean(Class<?> type) {
    List<Object> objList = beans.get(type);
    if (objList == null) { // 해당 타입의 객체 목록이 없다면
      return 0;
    }
    return objList.size();
  }

  // 객체에 대해 모든 초기화가 끝난 후에 @Autowired 애노테이션을 처리하자!
  // 따라서 다음 메서드만 오버라이딩 한다.
  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    System.out.println(
        "MyAutowiredAnnotationBeanPostProcessor.postProcessAfterInitialization(Object, String");
    // 생성된 객체를 기록한다.
    // => 나중에 의존 객체를 주입할 때 사용할 것이다.

    this.addBean(bean.getClass(), bean);

    // 항상 어떤 객체가 생성되면 자신을 원하는 세터가 있는지 검사하여
    // 그 메서드를 호출해야 한다.
    // c1을 준거이이이이이임 c2(Car2)면 c2를 (List값)
    // beans key값에는 Car.class value값에는 c2가 됨
    // callAutowiredMethod(bean);
    // System.out.println("@Autowired 애노테이션 처리:");

    // 해당 빈에서 @Autowired가 붙은 메소드를 찾아 호출하거나 호출을 연기시킨다.
    callAutoWiredMethod(bean);

    // 해당 빈에 대해 @Autowired 메소드를 호출하거나 연기시켰으면,
    // 해당 빈을 주입할 셋터 메소드를 찾아 호출한다.

    // 해당빈에서 필요로하는 셋터 메소드를 찾아 호출한다.
    injectDependency(bean);
    return bean;
  }

  private void addAutowiredMethod(Class<?> paramType, AutowiredMethod autowiredMethod) {
    // 해당 의존 객체에 대해 나중에 호출하기 위해 모아둔 메소드 목록을 꺼낸다.

    List<AutowiredMethod> methods = autowiredMethods.get(paramType);
    // 의존 객체를 주입할 메소드 목록이 없다면
    if (methods == null) { // 파라미터 타입의 값을 원하는 세터가 등록된 적이 없다면,
      methods = new ArrayList<>(); // 메소드 목록을 새로 준비한다.
      autowiredMethods.put(paramType, methods); // 그리고 일단 맵에 저장한다.
    }

    // 지정된 파라미터 타입의 값을 원하는 세터를 기존 목록에 추가한다.
    // 목록에 셋터 메소드 정보(세터메서드 + 객체)를 추가한다.
    methods.add(autowiredMethod);
  }

  // 객체의 @Autowired 가 붙은 메소드를 모두 찾아 호출한다.
  private void callAutoWiredMethod(Object bean) {
    // 이 객체의 모든 public 메서드를 꺼낸다.
    Method[] methods = bean.getClass().getMethods();

    // public 메소드 중에서 @Autowired로 표시된 메소드를 찾는다.
    for (Method m : methods) {

      // 메서드에서 @Autowired 애노테이션이 있는지 확인한다.
      Autowired anno = m.getAnnotation(Autowired.class);

      if (anno == null) // @Autowired 가 붙지 않은 메서드는 무시한다.
        continue;

      // 애노테이션이 있다면 이 셋터 메서드가 어떤 타입의 값을 원하는 지 알아낸다.

      // @Autowired가 붙은 메소드를 찾았으면 호출하여 의존 객체를 주입한다.
      // => 먼저 어떤 타입의 의전 객체인지 알아낸다.@Autowired가 붙은 메소드의 파라미터 타입을 알아낸다.
      Class<?> paramType = m.getParameters()[0].getType();

      // 세터가 원하는 파라미터 값이 있는지 확인한다.
      // setBlackBox라면>> blackBox에 해당되는 객체가 있는지 없는지.

      // => 파라미터 타입의 객체를 보관소에서 꺼낸다.
      // 야 BlackBox있냐? 
      // 리턴해주면 의존객체(블박)가 있다는 거자나 그럼 호출
      Object dependency = this.getBean(paramType);

      // 해당 의존 객체가 있다면
      if (dependency != null) {
        // 세터가 원하는 파라미터 값이 있다면 즉시 호출하여 의존 객체를 주입한다.
        // 셋터를 호출한다.
        try {

          // 의존객체 메소드 목록
          // BlackBox (c1, setBlackBox) (c2, setBlackBox) (c3, setBlackBox)
          // 드디어 대기하고있던
          // 놈들이 호출되기 시작함! wow!!!
          m.invoke(bean, dependency);
        } catch (Exception e) {
          e.printStackTrace();
        }
      } else { // 셋터가 원하는 파라미터 값이 없다면,
        // 일단 그 값이 나타날 때까지 호출을 연기하자!
        // 그 파라미터 타입의 값을 원하는 메서드 정보를 기록한다.
        // 즉 호출될 메소드 목록에 추가한다.

        // ****application-context02랑 같이봐라

        // 야 지금은 객체가 없어서 호출을 못하지만 나중의 호출을 위해서 등록함. (없어서 일단 계속 등록)
        // blackBox.class (setBlackBox,c1) (setBlackBox,c2) (setBlackBox,c3) ~
        // 마지막에 드디어 BalckBox라 나옴 나오면! isRegistered로 들어감~ 왜냐면 등록 되어있으니까
        addAutowiredMethod(paramType, new AutowiredMethod(bean, m));
      }
    }
  }

  // dependency를 의존하고있는 메소드를 찾을꺼임
  private void injectDependency(Object dependency) {
    // 이 타입의 빈을 원하는 세터 목록을 꺼낸다.
    // => 즉 이 타입의 객체를 받기 위해 호출이 연기된 메소드가 있는지 확인한다.
    List<AutowiredMethod> setters = autowiredMethods.get(dependency.getClass());

    if (setters == null) // 없다면 호출하지 않는다.
      return;

    // 이 객체를 간절히 원하는 셋터 메소드가 있다면 호출하여 주입한다.
    while (setters.size() > 0) {
      // 앞에있는 애 없어짐 (호출되었기 때문에) 뒤에있는 애가 앞으로 호출되서 이동함. 
      AutowiredMethod setter = setters.remove(0);
      try {
        setter.method.invoke(setter.object, dependency);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }


  class AutowiredMethod {
    Object object; // 메서드를 호출할 때 사용할 인스턴스
    Method method; // @Autowired가 붙은 메서드

    public AutowiredMethod(Object object, Method method) {
      this.object = object;
      this.method = method;
    }
  }
}


