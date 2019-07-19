package com.eomcs.util;

// 목록을 다루는 객체가 반드시 갖춰야할 기능을 규칙으로 정의한다.
// 사용하는 측에는 이 규칙에 따라 일관된 방식으로 클래스를 사용할 수 있다.
// => 규칙은 무조건 공개되어야 한다. 따라서 public이다
// => 규칙에 정의된 메소드는 클래스에서 구현해야 한다. 그래서 abstract이다.
// => public, abstract modifier는 생략할 수 있다.
// => private을 할시는 body를 구현 해줘야함
public interface List<E> {

  // 기능을 정의할 때는 메서드 시그너처만 선언한다.
  // 기능의 구현은 클래스에서 할 것이다.
  public abstract boolean add(E value);
  E get(int index); // public abstract생략된거임
  E set(int index, E value);
  E remove(int index);
  Object[] toArray();
  E[] toArray(E[] a);
  int size();
  void clear();
}
