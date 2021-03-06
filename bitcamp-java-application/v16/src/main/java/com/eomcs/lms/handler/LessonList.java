package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Lesson;

public class LessonList {
  private static final int DEFAULT_CAPACITY = 100;
  private Lesson[] list = new Lesson[100];
  private int size = 0;

  public LessonList() {
    this(DEFAULT_CAPACITY);
  }
  public LessonList(int initialCapacity) {
    if(initialCapacity < 50 || initialCapacity > 100) {
      list = new Lesson[DEFAULT_CAPACITY];
    }else {
      list = new Lesson[initialCapacity];
    }
  }
  public void add(Lesson lesson) {
    if(size == list.length) {
      throw new RuntimeException("배열 꽉참");
    }
    list[size++] = lesson;
  }
  
  public Lesson[] toArray() {
    Lesson[] arr = new Lesson[size];
    for (int i = 0; i < size; i++) {
      arr[i] = this.list[i];
    }
    return arr;
  }
  
}
