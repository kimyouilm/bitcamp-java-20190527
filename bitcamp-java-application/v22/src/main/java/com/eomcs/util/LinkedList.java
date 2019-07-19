// LinkedList : 목록으로 다루는 값을 특정 타입으로 제한하기 위해 제네릭(generic)적용하기
package com.eomcs.util;

import java.lang.reflect.Array;

public class LinkedList<T> {
  // 인스턴스 필드는 값을 초기화 시키지 않으면 초기값은 0(정수형)임!
  Node<T> head;
  Node<T> tail;
  int size = 0;

  public LinkedList() {
    // head = new Node();
    // head를 저장하는게 아니라
    // head에 들어있는 값을 tail에 저장하는 개념
    // tail = head;
  }

  public boolean add(T value) {
    Node<T> temp = new Node<>(value);
    if (head == null)
      head = temp;

    if (tail != null)
      // 새로만든 node를 기존의 값들에 연결시킴
      tail.next = temp;

    tail = temp;
    size++;
    return true;
    // list.add(aaa); => aaa 의 주소가 넘어감
    // tail.value = value;
    // 새롭게 Node생성해서 새로운 번지가 만들어짐
    // tail.next = new Node();
    // tail에 새로운 번지를 저장.
    // tail = tail.next;
  }

  public T get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다.");
    }

    Node<T> node = head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    return node.value;
  }

  // 특정 위치의 값을 바꾼다.
  // list.set(2, "aaa");
  public T set(int index, T value) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다.");
    
    Node<T> node = head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }

    // 노드에 저장된 기존 값 백업
    T oldVal = node.value;
    // 해당 노드의 파라미터에서 받은 값으로 변경
    node.value = value;
    // 변경 전 값을 리턴
    return oldVal;
  }

  // 특정 위치의 값을 삭제한다.
  public T remove(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다.");

    Node<T> deletedNode = null;
    if (index == 0) {
      deletedNode = head;
      head = deletedNode.next;
    } else {

      Node<T> node = head;
      for (int i = 0; i < index - 1; i++) {
        // 삭제하려는 노드의 이전 노드 까지 간다.
        node = node.next;
      }

      // 삭제될 노드를 임시 보관한다.
      deletedNode = node.next;
      // 이전노드가 가리키는 다음 노드를 다음 다음 노드를 가리키게 한다.
      // 삭제돌 노드의 다음 노드를 가리킨다.
      node.next = deletedNode.next;
      // 삭제할 노드가 마지막 노드라면
      if (deletedNode == tail) {
        // tail 노드를 변경한다.
        tail = node;
      }
    }
    // 삭제될 노드의 값을 임시 보관한다.
    T oldVal = deletedNode.value;
    // 삭제될 노드가 다른 객체를 참조하지 않도록 초기화 시킨다.
    deletedNode.value = null;
    // 이런 식으로 개발자가 메모리 관리에 기여할 수 있다.
    deletedNode.next = null;
    size--;
    return oldVal;
  }

  public int size() {
    return size;
  }

  public void clear() {
    if (size == 0)
      return;
    // size가 0 보다 클때 노드를 따라 가면서 삭제하기
    while (head != null) {
      Node<T> deletedNode = head;
      head = head.next;
      deletedNode.value = null;
      deletedNode.next = null;

    }
    tail = null;
    size = 0;
  }

  public Object[] toArray() {
    // LinkedList에 있는 데이터를 저장할 배열을 준비한다.
    Object[] arry = new Object[size];
    // LinkedList의 head에서 tail까지 반복하면서 배열에 value를 복사한다.
    Node<T> node = head;
    int i = 0;
    // i < size
    while (node != null) {
      arry[i] = node.value;
      node = node.next;
      i++;
    }

    // for (int j = 0; j <size; j++) {
    // arry[j] = node.value;
    // node = node.next;
    // }
    // 배열을 리턴한다.
    return arry;
  }

  @SuppressWarnings("unchecked")
  public T[] toArray(T[] a) {
    if (a.length < size) {
      // 파라미터로 넘겨받은 배열의 크기가 저장된 데이터의 개수 보다 작다면
      // 이 메소드에서 새 배열을 만든다.
      a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
    }
    Node<T> node = head;
    for (int j = 0; j < size; j++) {
      a[j] = node.value;
      node = node.next;
    }

    if (a.length > size)
      a[size] = null;
    return a;
  }

  // Node 객체에 보관하는 데이터의 클래스 이름을 "타입 파라미터" T에 받는다.
  static class Node<T> {

    T value;
    // 다음 상자를 가르김
    Node<T> next;

    // 개발자가 아무런 생성자를 만들지 않았을때는 이클립스가 기본 생성자를 자동으로 만들어주지만
    // 개발자가 기본생성자가 아닌 생성자를 만들었을시는 기본생성자가 자동으로 만들어 지지 않음
    public Node() {

    }

    public Node(T value) {
      this.value = value;
    }
  }
}
