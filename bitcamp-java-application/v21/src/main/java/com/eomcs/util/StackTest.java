package com.eomcs.util;

public class StackTest {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Stack<String> stack = new Stack<String>();
    stack.push("aaa");
    stack.push("bbb");
    stack.push("ccc");
    stack.push("ddd");
    // 끝인지 아닌지 확인할때 

    while (!stack.empty()) {
      System.out.println(stack.pop());
    }
  }
}