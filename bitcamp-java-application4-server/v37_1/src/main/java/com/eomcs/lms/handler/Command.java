package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.Scanner;

public interface Command {

  // interface는 default를 남발하지 않는다.
  // 기존규칙은 그대로 둔다.
  default void execute() {}

  // 새규칙을 추가한다.
  // 단 기존에 이 인터페이스를 구현한 클래스에게 영향을 주지 않기 위해
  // 새로 추가하는 메소드는 default메소드로 선언한다.
  default void execute(BufferedReader in, PrintStream out) {}

}
