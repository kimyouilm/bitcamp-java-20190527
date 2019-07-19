package com.eomcs.util.step4;

import com.eomcs.util.Iterator;
import com.eomcs.util.LinkedList;

public class Stack<E> extends LinkedList<E> implements Cloneable {

  @Override
  public Stack<E> clone() throws CloneNotSupportedException {
    Stack<E> temp = new Stack<>();
    for (int i = 0; i < size(); i++) {
      temp.push(get(i));
    }
    return temp;
  }

  public void push(E value) {
    add(value);
  }

  public E pop() {

    if (size() == 0) {
      throw new ArrayIndexOutOfBoundsException("꺼냘 대이터가 없습니다.");
    }
    return remove(size() - 1);
  }

  public boolean empty() {
    return size() == 0;
  }

  public Iterator<E> getIterator() {
    return new Iterator<E>() {

      @Override
      public boolean hasNext() {
        // TODO Auto-generated method stub
        return size() > 0;
      }

      @Override
      public E next() {
        // TODO Auto-generated method stub
        return pop();
      }
    };
  }

}
