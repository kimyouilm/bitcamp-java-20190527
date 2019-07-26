// java.io.File 클래스 : 연습 과제 - bin 폴더를 삭제하라!
package ch22.a;

import java.io.File;

public class Test13 {

  public static void main(String[] args) throws Exception {
    // bin의 하위 폴더와 파일을 모두 삭제해야만 bin 폴더를 삭제할 수 있다.
    // => 재귀 호출을 이용할 것.
    File dir = new File("bin");

    delete(dir);

    System.out.println("삭제 완료!");
  }

  static void delete(File path) {
    if(path.isFile()) {
      path.delete();
      return;
    }
    
    File[] files = path.listFiles();
    for(File file : files) {
      delete(file);
    }
    
    path.delete();
//    // 1) 디렉토리가 포함하고 있는 하위 디렉토리나 파일 목록을 얻는다.
//    File[] files = path.listFiles();
//    // 2) 파일은 삭제하고 디렉토리 대귀호출로 삭제한다.
//    for (File file : files) {
//      if (file.isFile()) {
//        file.delete();
//      } else {
//        delete(file);
//      }
//    }
//    // 3) 디렉토리가 포함하고 있는 하위 디렉토리나 파일이 모두 삭제되었다면 현재의 디렉토리를 삭제한다.
//    path.delete();
  }
}


