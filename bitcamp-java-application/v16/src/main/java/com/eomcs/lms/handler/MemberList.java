package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;

public class MemberList {
  private static final int DEFAULT_CAPACITY = 100;
  static Member[] list = new Member[100];
  static int size = 0;

  public MemberList() {
    this(DEFAULT_CAPACITY);
  }
  public MemberList(int initialCapacity) {
    if(initialCapacity < 50 || initialCapacity > 100) {
      list = new Member[DEFAULT_CAPACITY];
    }else
      list = new Member[initialCapacity];
  }
  public void add(Member member) {
    if(size == list.length) {
      throw new RuntimeException("배열 꽉참");
    }
    list[size++] = member;
  }
  
  public Member[] toArray() {
    Member[] arr = new Member[size];
    for (int i = 0; i < size; i++) {
      arr[i] = list[i];
    }
    return arr;
  }
}
