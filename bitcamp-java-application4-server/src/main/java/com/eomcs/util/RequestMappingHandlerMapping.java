package com.eomcs.util;

import java.lang.reflect.Method;
import java.util.HashMap;

// 열할:
// => @RequestMapping 애노테이션이 붙은 메소드의 정보를 보관한다.
// => 나중에 메소드를 호출하려면 인스턴스 주소로 알아야 하기 때문에
// 메소드 정보를 보관할 때 인스턴스도 함께 보관해야 한다.
public class RequestMappingHandlerMapping {

  // HashMap<명령어, (메소드 + 인스턴스)>
  HashMap<String, RequestHandler> handlerMap = new HashMap<>();

  @Override
  public String toString() {
    return "RequestMappingHandlerMapping [handlerMap=" + handlerMap + "]";
  }

  public void addRequestHandler(String name, Object bean, Method method) {
    handlerMap.put(name, new RequestHandler(method, bean));
  }

  // 명령이 들어오면 이 메소드로 찾는 거임.
  public RequestHandler getRequestHandler(String name) {
    return handlerMap.get(name);
  }

  public static class RequestHandler {
    public Method method;
    public Object bean;

    public RequestHandler(Method method, Object bean) {
      this.method = method;
      this.bean = bean;
    }
  }
}
