package algorithm.data_structure.queue.step3;

public class QueueTest {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Queue<String> stack = new Queue<String>();
    stack.offer("aaa");
    stack.offer("bbb");
    stack.offer("ccc");
    stack.offer("ddd");
    // 끝인지 아닌지 확인할때 

    while (!stack.empty()) {
      System.out.println(stack.poll());
    }
  }
}