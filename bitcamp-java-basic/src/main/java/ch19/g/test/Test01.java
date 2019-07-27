package ch19.g.test;

public class Test01 {

  // EventFilter는 한번만 쓰임 그래서 이 클래스를 익명클래스도 만들어 람다를 적용하면 
  // 클래스를 따로 만들지 않고도 한줄로 표현할 수있음.
  public static void main(String[] args) {
    int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    
    List list = new List(a);
    //원래 Test01 기본값
    EvenFilter filter = new EvenFilter();
    int[] r = list.toArray(filter);
    
//    int[] r = list.toArray(new EvenFilter() {
//      @Override
//      public boolean accept(int value) {
//        return (value % 2) == 1;
//      }
//    });

    
    for (int v : r) {
      System.out.println(v);
    }
  }
  
  
  

}
