package com.eomcs.util;

import java.util.Arrays;

public class ArrayList<E> {

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

  public void add(E obj) {
    if (size == list.length) {
      int oldCapacity = list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      list = Arrays.copyOf(this.list, newCapacity);
      // Object[] arr = new Object[newCapacity];
      // for (int i = 0; i < this.size ; i++) {
      // arr[i] = this.list[i];
      // }
      // list = arr;
    }
    list[size++] = obj;
  }

  public Object[] toArray() {
    return Arrays.copyOf(this.list, this.size);
    //
    // Object[] arr = new Object[size];
    // for (int i = 0; i < size; i++) {
    // arr[i] = list[i];
    // }
    // return arr;
  }

  @SuppressWarnings("unchecked")
  public E[] toArray(E[] a) {
    if (a.length < size) {
      // 파라미터로 넘겨 받은 배열의 크기가 저장된 데이터의 개수 보다 작다면
      // 이 메소드에서 새 배열을 만든다.
      //                        .getClass() => 그 클래스가 어떤 타입인지 정보를 담음
      return (E[]) Arrays.copyOf(list, size, a.getClass()); // 세번째 파라미터로 지정한 타입의 배열이 생성된다.
    }
    System.arraycopy(list, 0, a, 0, size);
    if (a.length > size){
      a[size] = null;
    }
     return a;
  }
  public int size() {
    return this.size;
  }
}
