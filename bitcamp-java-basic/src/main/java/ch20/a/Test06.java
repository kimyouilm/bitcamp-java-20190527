// java.util.Collection의 forEach() 사용법
package ch20.a;

import java.util.ArrayList;

public class Test06 {

  public static void main(String[] args) {
    ArrayList<String> names = new ArrayList<>();

    names.add("name1");
    names.add("name2");
    names.add("name3");
    names.add("name4");
    names.add("name5");
    names.add("name6");
    names.add("name7");

    names.forEach(t -> {
      System.out.println(t);
    });
  }

}


