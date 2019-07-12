package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Board;

public class BoardList {

  private static final int DEFAULT_CAPACITY = 100;
  private Board[] list = new Board[100];
  private int size = 0;
  public BoardList() {
    // 생성자에서 다른 생성자를 호출할 수 있다.
    this(DEFAULT_CAPACITY);
  }

  public BoardList(int initialCapacity) {
    // 생성자만이 다른 생성자를 호출 할 수있다.
    // 컴파일 오류! 일반 메소드는 생성자를 호출할 수 없다.
    // this(100);
    if (initialCapacity < 50 || initialCapacity > 100)
      // 꽉차면 기본값을 갖게 한다.
      list = new Board[DEFAULT_CAPACITY];
    else
      list = new Board[initialCapacity];
  }

  public void add(Board board) {
    if (size == list.length)
      throw new RuntimeException("배열 꽉찬듯");
    list[size++] = board;
  }

  public Board[] toArray() {
    Board[] arr = new Board[size];
    for (int i = 0; i < size; i++) {
      arr[i] = list[i];
    }
    return arr;
  }
}
