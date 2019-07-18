package com.eomcs.util;

public class LinkedList {
  // 인스턴스 필드는 값을 초기화 시키지 않으면 초기값은 0(정수형)임!
  Node head;
  Node tail;
  int size = 0;

  public LinkedList() {
    // head = new Node();
    // head를 저장하는게 아니라
    // head에 들어있는 값을 tail에 저장하는 개념
    // tail = head;
  }

  public boolean add(Object value) {
    Node temp = new Node(value);
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

  public Object get(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다.");

    Node node = head;

    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    return node.value;
  }

  // 특정 위치의 값을 바꾼다.
  // list.set(2, "aaa");
  public Object set(int index, Object value) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다.");
    Node node = head;

    for (int i = 0; i < index; i++) {
      node = node.next;
    }

    // 노드에 저장된 기존 값 백업
    Object oldVal = node.value;
    // 해당 노드의 파라미터에서 받은 값으로 변경
    node.value = value;
    // 변경 전 값을 리턴
    return oldVal;
  }

  // 특정 위치의 값을 삭제한다.
  public Object remove(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다.");

    Node deletedNode = null;
    if (index == 0) {
      deletedNode = head;
      head = deletedNode.next;
    } else {

      Node node = head;
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
      Object oldVal = deletedNode.value;
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
      Node deletedNode = head;
      head = head.next;
      deletedNode.value = null;
      deletedNode.next = null;
    }
    
    head = tail = null;
    size = 0;
  }
}
