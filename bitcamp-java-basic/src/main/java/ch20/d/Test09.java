// Hashtable에서 value 목록 꺼내기 - elements()
package ch20.d;
  
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;

public class Test09 {
  
  public static void main(String[] args) {
    
    class Student {
      String name;
      int age;
      
      public Student(String name, int age) {
        this.name = name;
        this.age = age;
      }

      @Override
      public String toString() {
        return "Student [name=" + name + ", age=" + age + "]";
      }
    }
    
    Hashtable<String, Student> table = new Hashtable<>();
    table.put("aaa", new Student("홍길동", 20));
    table.put("bbb", new Student("임꺽정", 30));
    
    Student s = new Student("안중근", 25);
    table.put("ccc", s);
    table.put("ddd", s);
    
    // Hashtable도 HashMap과 마찬가지로 values()를 사용하여 값 목록만 꺼낼 수 있다.
    // 또한 Hashtable은 Enumeration으로 값 목록을 꺼내는 메서드를 추가로 제공한다.
    // => Enumeration의 사용법은 Iterator와 유사하다.
//    Set<String> keySet = table.keySet();
//    Iterator<String> iterator = keySet.iterator();
//
//    while (iterator.hasNext()) {
//      System.out.println(iterator.next());
//    }
    
    Set<Entry<String, Student>> entrySet = table.entrySet();
    for (Entry<String, Student> entry : entrySet) {
      System.out.println("key: " + entry.getKey() + ", value:" + entry.getValue());
    }
    System.out.println("-------------------");
    Enumeration<Student> values = table.elements();
    while (values.hasMoreElements()) {
      System.out.println(values.nextElement());
    }
    
    // 정리: 일반적으로 Entry는 value을 이용, Enumeration은 elements을 이용해 값을 찾아냄
  }
}

/* 
List vs Set vs Map

항목                           List               Set                  Map
1) 중복 저장            |        가능        |     불가능         | key 불가능, value 가능
2) null 허용 여부       |        가능        |      가능(한 개만)  | HashMap(key/value 가능), 
                                                               Hashtable(key/value 불가능)
                                                               
 */




















