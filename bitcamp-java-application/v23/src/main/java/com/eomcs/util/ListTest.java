package com.eomcs.util;

public class ListTest {

  public static void main(String[] args) {
    LinkedList<String> list1 = new LinkedList<>();
//    LinkedList<String> list2 = new ArrayList<>(); // 컴파일 오류
    
    ArrayList<String> list3 = new ArrayList<>(); 
//    ArrayList<String> list3 = new LinkedList<>(); // 컴파일 오류
    
    //클래스 래퍼런스
    // => 해당 클레스의  인스턴스의 주소를 저장할 수 있다.
    // => 해당 클래스의 서브 클래스에 대해서도 인스턴스 주소를 저장 할 수있따.
    Object obj1 = new LinkedList<>();
    Object obj2 = new ArrayList<>();
    Object obj3 = new String("Hello");
    
    // Interface reference
    // => 해당 인터페이스에 따라 작성한 클래스의 인스턴스 주소를 저장할 수 있다.
    // => 해당 인터페이스를 구현한 클래스의 인스턴스 주소를 저장할 수 있다.
    // => 해당 인터페이스를 구현한 객체를 저장할 수 있다.
    // => 해당 인터페이즈의 객체를 저장할 수 있다.
    // => List 객체를 저장할 수 있다.
    List<String>    list5 =  new LinkedList<>();
    List<String>    list6 =  new LinkedList<>();
//    List<String>    list7 =  new java.sql.Date<>(); // 리스트 객체가 아니다.

  }

}
