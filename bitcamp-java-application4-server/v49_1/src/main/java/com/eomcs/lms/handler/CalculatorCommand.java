package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.util.Component;
import com.eomcs.util.Input;
import com.eomcs.util.RequestMapping;

@Component
public class CalculatorCommand {

  int value1;
  int value2;
  int result;

  @RequestMapping("/calc/plus")
  public void add(BufferedReader in, PrintStream out) {
    try {
      value1 = Input.getIntValue(in, out, "값1?>> ");
      value2 = Input.getIntValue(in, out, "값2?>> ");
      result = value1 + value2;

      out.printf("%d + %d = %d\n", value1, value2, result);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/calc/minus")
  public void minus(BufferedReader in, PrintStream out) {
    try {
      value1 = Input.getIntValue(in, out, "값1?>> ");
      value2 = Input.getIntValue(in, out, "값2?>> ");
      result = value1 - value2;

      out.printf("%d - %d = %d\n", value1, value2, result);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  // 실습과제:
  // => 다음과 같이 실행하도록 위 클래스를 완성하라!
  //

  /*
   * > /calc/plus 값1? 100 값2? 200
   * 
   * 100 + 200 = 300
   * 
   * > /calc/minus 값1? 100 값2? 200 100 - 200 = -100
   */
}
