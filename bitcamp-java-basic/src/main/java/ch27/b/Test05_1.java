// reflection API - 파라미터 상세정보 꺼내기
package ch27.b;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
// 어떤 클래스나 인터페이스의 스태틱 멤버를 import 할 수 있다.
import static java.lang.reflect.Modifier.*;

public class Test05_1 {

  public static void main(String[] args) throws Exception {

    Class<?> clazz = C.class;

    Method m = clazz.getMethod("plus", int.class, int.class);
    System.out.printf("메서드명: %s.%s\n", m.getDeclaringClass().getSimpleName(), m.getName());

    Parameter[] params = m.getParameters();
    System.out.printf("파라미터: %d\n", m.getParameterCount());
    for (Parameter p : params) {
      System.out.printf("    %s:%s\n", p.getName(), // 파라미터명
          p.getType().getName() // 파라미터 타입명
      );
    }
  }
}

