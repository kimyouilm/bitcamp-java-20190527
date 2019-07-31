package com.eomcs.lms.handler;

import com.eomcs.util.Input;

public class CalPlusCommand implements Command{

  Input input;
  
  public CalPlusCommand (Input input) {
    this.input = input;
  }
  @Override
  public void execute() {
    int num1 = input.getIntValue("첫번째 숫자: ");
    int num2 = input.getIntValue("두번째 숫자: ");
    int sum = num1 + num2;
    System.out.println(num1 + "과" + num2 + "의 합: " + sum);
  }

}
