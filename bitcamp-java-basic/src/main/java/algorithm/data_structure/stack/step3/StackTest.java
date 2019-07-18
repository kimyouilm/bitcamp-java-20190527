package algorithm.data_structure.stack.step3;

public class StackTest {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Stack<String> stack = new Stack<String>();
    stack.push("aaa");
    stack.push("bbb");
    stack.push("ccc");
    stack.push("ddd");

    while (!stack.empty()) {
      System.out.println(stack.pop());
    }
  }
}