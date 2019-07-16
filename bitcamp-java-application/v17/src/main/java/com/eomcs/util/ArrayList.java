package com.eomcs.util;

import java.util.Arrays;

public class ArrayList {

  private static final int DEFAULT_CAPACITY = 100;
  private Object[] list;
  private int size = 0;
  public ArrayList() {
    // 생성자에서 다른 생성자를 호출할 수 있다.
    this(DEFAULT_CAPACITY);
  }

  public ArrayList(int initialCapacity) {
    // 생성자만이 다른 생성자를 호출 할 수있다.
    // 컴파일 오류! 일반 메소드는 생성자를 호출할 수 없다.
    // this(100);
    if (initialCapacity < 50 || initialCapacity > 100)
      // 꽉차면 기본값을 갖게 한다.
      list = new Object[DEFAULT_CAPACITY];
    else
      list = new Object[initialCapacity];
  }

  public void add(Object obj) {
    if (size == list.length) {
      int oldCapacity = list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      list = Arrays.copyOf(this.list, newCapacity);
//      Object[] arr = new Object[newCapacity];
//      for (int i = 0; i < this.size ; i++) {
//        arr[i] = this.list[i];
//      }
//      list = arr;
    }
    list[size++] = obj;
  }

  public Object[] toArray() {
    return Arrays.copyOf(this.list, this.size);
//    
//    Object[] arr = new Object[size];
//    for (int i = 0; i < size; i++) {
//      arr[i] = list[i];
//    }
//    return arr;
  }
}
