package com.eomcs.util;
import java.util.ArrayList;
public class Test {

  public static void main(String[] args) {
    ArrayList<String> list = new ArrayList<>();
    list.add("aaa");
    list.add("bbb");
    list.add("ccc");
//    list.forEach(a -> System.out.println(list));
    for(String s : list) {
      System.out.println(s);
    }

  }

}
