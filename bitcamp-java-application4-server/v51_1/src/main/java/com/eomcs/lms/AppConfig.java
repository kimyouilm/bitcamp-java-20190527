package com.eomcs.lms;

import org.springframework.context.annotation.ComponentScan;

// Spring IoC container에게 알려줄 설정 정보에 애노테이션을 이용하여
// 이클래스에 저장해 둔다.
//
// com.eomcs.lms 패키지에서 @Component가 붙은 클래슬 ㄹ찾아 인스턴스를 자동으로 생성하게 한다
@ComponentScan("com.eomcs.lms")
public class AppConfig {


}
