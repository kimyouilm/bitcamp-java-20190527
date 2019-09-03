package ch29.a;

import org.springframework.context.annotation.ComponentScan;

// Component를 Scan하라는 Annotation.
// ch29.a패키지 밑에있는 annotation 밑에있는 bean을 자동생성하는.
@ComponentScan(basePackages= "ch29.a")
public class AppConfig03 {
}
