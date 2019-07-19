// 상속 문법을 이용하여 스택 만들기
package com.eomcs.util.step1;

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

    return remove(size() - 1);
  }

  public boolean empty() {
    return size() == 0;
  }

  // 스택에서 Iterator를 제공한다.
  public Iterator<E> getIterator() {

    return new StackIterator<>(this);
  }

}
