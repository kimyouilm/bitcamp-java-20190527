// 정렬 객체를 일관성 있게 사용하려면 같은 타입으로 묶어야 한다. 상속의 generalization 이용!
package ch17.c;

public class Test01 {

  public static void main(String[] args) {
    int[] values = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    int[] values2 = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    
    // 정렬 객체를 사용하는 방법을 통일하면 사용하기가 쉬워진다.
    // 어떻게?
    // => 두 클래스를 같은 부모의 자식 클래스가 되게 하라.
    // => 같은 부모라는 뜻은 같은 메서드를 갖는다는 의미다.
    // => 즉 메서드가 같으니 사용법도 같다.
    //

    // 두 개의 정렬 객체가 같은 타입이기 때문에 사용하기 편하다.
    // => 언제든 다른 객체로 교체할 수 있어 유연하다.
    display(new BubbleSort(), values);
    display(new QuickSort(), values2);

    
  }

  // 컴파일러는 어떤 인스턴스에 대해서 메소드가 호출할수 있냐없냐 검사하고 
  // 레퍼런스가 어느 타입인지 보고 그클래스에 있는지 보고 super클래스들을 확인함!
  // 있으묜 okay 없으면 nope!
  // 실행시 => JVM
  static void display(Sorter sorter, int[] values) {
    
    // 정렬 객체의 클래스가 뭔지 상관없다.
    // 그 클래스를 사용할 때는 공통 분모가 되는 
    // 수퍼 클래스의 메서드를 호출한다.
    sorter.sort(values);
    // sort()가 존재하는 메소드인지 확인
    // 실행을 할때 호출되는 메소드가 다르다.
    // 컴파일과 실행은 엄연히 다름.
    
    
    // 정렬된 값을 출력한다.
    for (int  value : values) {
      System.out.print(value + ",");
    }
    System.out.println();
  }
}



