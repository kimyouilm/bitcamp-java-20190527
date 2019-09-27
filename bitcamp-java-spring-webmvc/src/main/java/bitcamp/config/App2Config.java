package bitcamp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.util.UrlPathHelper;
import bitcamp.app2.Controller04_1_Interceptor1;
import bitcamp.app2.Controller04_1_Interceptor3;
import bitcamp.app2.Controller04_1_Interceptor4;

@ComponentScan("bitcamp.app2")
// => 지정된 패키지를 뒤져서 @Component, @Controller 등 붙은 클래스에 대해 인스턴스를 생성한다.
// 


@EnableWebMvc
// => Web MVC 관련 객체를 등록하고 설정한다.
// => WebMvcConfigurer 구현체를 찾아 메소드를 호출한다.
// 
public class App2Config implements WebMvcConfigurer {

  // DispatcherServlet의 기본 ViewResolver를 교체하기
  // 1) XML 설정
  // <bean id="viewResolver"
  // class="org.springframework.web.servlet.view.InternalResourceViewResolver">
  // <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
  // <property name="prefix" value="/WEB-INF/jsp/"/>
  // <property name="suffix" value=".jsp"/>
  // </bean>
  //
  // 2) Java Config 설정
  // @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver vr = 
        new InternalResourceViewResolver("/WEB-INF/jsp2/", ".jsp");
    return vr;
  }

  // @MatrixVariable 애노테이션을 처리를 활성화시킨다.
  // => 이 작업을 수행하려면 MVC 관련 설정 작업을 수행할 수 있도록
  //    WebMvcConfigurer 인터페이스를 구현해야 한다.
  // => 그리고 다음 메소드를 오버라이딩 하여 기존 설정을 변경한다.
  // 
  // DispatcherServlet 이  MVC 관련 설정을 처리하는 과정
  // => WebMVC 설정을 활성화 시켰는지 검사한다.
  // => 활성화 시켰으면, IoC 컨테이너의 Java Config 클래스 중에서 
  //    WebMvcConfigurer 구현체를 찾는다.
  // => WebMvcConfigurer 규칙에 따라 메소드를 호출하여
  //    설정을 추가하거나, 기존 설정을 변경한다.
  // => WebMVC 설정을 활성화 시키지 않으면,
  //    WebMvcConfigurer 구련체가 있다 하더라도 무시한다.
  // => WebMVC 설정을 활성화 시키는 방법
  //    1) XML 설정
  //        <mvc:annotation-driven/>
  //    2) Java Config 설정
  //        @EnableWebMvc 애노테이션 표시

  @Override
  public void configurePathMatch(PathMatchConfigurer configurer) {
    UrlPathHelper helper = new UrlPathHelper();
    helper.setRemoveSemicolonContent(false);

    // DispatcherServlet의 MVC Path 곤련 설정을 변경한다.
    configurer.setUrlPathHelper(helper);
  }
  
  // 인터셉터 설정하기
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 1) 모든 요청에 대해 실행할 인터셉터 등록하기
    // => 인터셉터를 적용할 URL을 지정하지 않으면 현재 프론트 컨트롤러의 모든 요청에 대해 적용된다.
    registry.addInterceptor(new Controller04_1_Interceptor1());
    
    // 2) 특정 요청에 대해 실행할 인터셉터 등록하기
    // => 예) 패턴: /c04_1/*
    //      => 적용 가능:
    //         /c04_1/h1
    //         /c04_1/x
    //      => 적용 불가:
    //         /x
    //         /c03_1/x
    //         /04_1/a/x
    //         /04_1/a/b/x
    // 즉, /c04_1/ 의 모든 하위 경로에 있는 자원에 대해서만 인터셉터를 적용한다.
    // registry.addInterceptor(new Controller04_1_Interceptor2())
    // .addPathPatterns("/c04_1/*");
    
    registry.addInterceptor(new Controller04_1_Interceptor3())
    .addPathPatterns("/c04_1/**");
    
    // 3) 특정 요청에 대해 인터셉터 적용을 제외하기
    // => 예) 패턴: /c04_1/*
    //      => 적용 가능:
    //         /c04_1/h1
    //         /c04_1/x
    //      => 적용 불가:
    //         /x
    //         /c03_1/x
    //         /04_1/a/x
    //         /04_1/a/b/x
    // 즉, /c04_1/ 의 모든 하위 경로에 있는 자원에 대해서만 인터셉터를 적용한다.
    // 단, /c04_1/a 의 모든 하위 경로에 있는 자원은 제외한다.
    registry.addInterceptor(new Controller04_1_Interceptor4())
    .addPathPatterns("/c04_1/**").excludePathPatterns("/c04_1/a/**");
    
  }


}


