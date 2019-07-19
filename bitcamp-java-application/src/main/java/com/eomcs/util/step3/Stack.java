// 상속 문법을 이용하여 스택 만들기
package com.eomcs.util.step3;

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
    // 특정 메소드 안에서만 사용되는 클래스라면 메소드 안에 선언하라!
    // 이렇게 메소드 안에 선언된 중첩 클래스를 "local class"라 부른다.
    // 유지보수하기 좋으라고
    class StackIterator implements Iterator<E> {

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

    }
    return new StackIterator();
  }

  // 스택에 있는 데이터를 꺼내주는 역할을 한다.
  // Iterator 규칙에 따라작성하여
  // 이 객체를 사용하는 개발자가 일관된 방식으로 호출할 수 있게 한다.
}
