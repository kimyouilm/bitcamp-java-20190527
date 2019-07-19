package com.eomcs.util;

import java.util.Arrays;
// ArrayList 클래스를 List 규칙에 따라 작성한다.
// => 클래스를 선언할 때 어떤 규칙을 따를 것인지 지정해야 한다.
//      class 클래스명 implements 규칙1, 규칙2, 규칙3{...}
// implements로 상속받은 인터페이스의 메소드들을 override를 하지 않으면 abstract class가 됨
public class ArrayList<E> implements List<E>{

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

  // 인터페이스(규칙)에 정의된 메소드를 구현할 때는 오버라이딩 하는 방법과 같다.
  // => public을 더 제한할 수 없다.
  // => @Override annotation을 붙일 수 있다.
  @Override // 붙이면 틀린거 바로 알수있음
  public boolean add(E obj) {
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
    return true;
  }

  @Override
  public Object[] toArray() {
    System.out.println("Object[] list");
    return Arrays.copyOf(this.list, this.size);
    //
    // Object[] arr = new Object[size];
    // for (int i = 0; i < size; i++) {
    // arr[i] = list[i];
    // }
    // return arr;
  }

  @Override
  @SuppressWarnings("unchecked")
  public E[] toArray(E[] a) {
    if (a.length < size) {
      // 파라미터로 넘겨 받은 배열의 크기가 저장된 데이터의 개수 보다 작다면
      // 이 메소드에서 새 배열을 만든다.
      // .getClass() => 그 클래스가 어떤 타입인지 정보를 담음
      System.out.println("toArray if a.length <size");
      System.out.println(a.length);
      System.out.println(size);
      return (E[]) Arrays.copyOf(list, size, a.getClass()); // 세번째 파라미터로 지정한 타입의 배열이 생성된다.
    }
    System.arraycopy(list, 0, a, 0, size);
    // 복사한다음에 null로 채움
    // 값을 채운 그다음방에만 null을 집어넣음 그뒤로는 쓰레기값
    if (a.length > size) {
      a[size] = null;
    }
    // if(a[size] == null)
    // break;
    return a;
  }

  @Override
  public int size() {
    return this.size;
  }

  @SuppressWarnings("unchecked")
  @Override
  public E get(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException(String.format("index = %s", index));

    return (E) list[index];
  }

  @SuppressWarnings("unchecked")
  @Override
  public E set(int index, E obj) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException(String.format("index = %s", index));

    E old = (E) list[index];
    list[index] = obj;
    return old;
  }
  
  @Override
  public E remove(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException(String.format("index = %s", index));
    
    @SuppressWarnings("unchecked")
    E old = (E)list[index];

    // 방법 1: 직접 반복문을 이용하여 삭제 처리하기
//    for(int i = index + 1; i < size; i++) {
//      list[i-1] = list[i];
//    }
    // 방법2: 배열복사 기능을 이용하여 삭제 처리하기
    // 현재 배열,  삭제하고픈 인덱스+1, 같은 배열, 삭제할 배열부터, 삭제할 인덱스 앞부분은 해당안됨으로 삭제할 인덱스 ~ 끝 ㅂㅎㄱ서
    //                  전송원배열 소스배열의개시위치, 전송처배열, 전송처 데이터내의 게시위치, 카피되는 배열요소의수
    // System.arraycopy(src, srcPos, dest, destPos, length)
    System.arraycopy(list, index+1, list, index, size- (index + 1));
    // 값을 삭제한 후 맨 끝 값이 들어있던 방을 null로 설정하여 
    // => 레퍼런스가 남아있지 않게 하여 가비지가 정상적으로 이뤄지도록 한다.
    list[--size] = null;
    return old;
    
  }
  @Override
  public void clear() {
    for (int i = 0; i < size; i++) {
      list[i] = null;
    }
    size = 0;
  }
}
