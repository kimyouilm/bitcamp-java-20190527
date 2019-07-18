package com.eomcs.util;

public class Node {

  Object value;
  // 다음 상자를 가르김
  Node next;
  
  // 개발자가 아무런 생성자를 만들지 않았을때는 이클립스가 기본 생성자를 자동으로 만들어주지만 
  // 개발자가 기본생성자가 아닌 생성자를 만들었을시는 기본생성자가 자동으로 만들어 지지 않음
  public Node() {
    
  }
  public Node(Object value) {
    this.value = value;
  }
}
