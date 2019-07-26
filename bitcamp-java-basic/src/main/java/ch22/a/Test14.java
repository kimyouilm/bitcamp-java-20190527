// java.io.File 클래스 : 연습 과제 - bin/main 폴더를 뒤져서 모든 클래스 파일의 이름을 출력하라.
package ch22.a;

import java.io.File;

public class Test14 {

  public static void main(String[] args) throws Exception {
    // 클래스 이름을 출력할 때 패키지 이름을 포함해야 한다.
    // 예) ch01.Test01
    // 예) ch22.a.Test14
    //
    File dir = new File("bin/main");

    // 패키지 이름은 빈문자열로 시작
    findClass(dir, "");
    System.out.println("완료!");
  }

  static void findClass(File path, String packageName) {

    // 1) path가 파일이면 패키지 이름과 파일 이름을 합쳐 출력하고 리턴한다.
    // => 단 파일 이름에서 .class확장자 명은 제외한다.
    // => 파일 명이 Hello.class 이고 패키지명이 aaa.bbb 이면
    // 출력할 이름은 aaa.bbb.Hello 이다.
    if (path.isFile()) {
      System.out.printf("%s.%s\n", packageName, path.getName().replace(".class", "").substring(1));
      return;
    }
    // 2) path가 디렉토리라면 하위 디렉토리와 파일 목록을 얻는다.
    // => 단 필터를 이용하여 디렉토리와 클래스 파일(.class) 목록만 추출한다.
    File[] files = path.listFiles(f -> f.isDirectory() || f.getName().endsWith(".class"));
    // 3) 하위 디렉토리와 파일 목록에서 클래스를 찾는다.
    for (File file : files) {
      if (file.isDirectory())
        findClass(file, packageName + "." + file.getName());
      else
        findClass(file, packageName);
    }

  }

  static void findClass2(File path, String packageName) {

    // 1) path가 파일이면 패키지 이름과 파일 이름을 합쳐 출력하고 리턴한다.
    // => 단 파일 이름에서 .class확장자 명은 제외한다.
    // => 파일 명이 Hello.class 이고 패키지명이 aaa.bbb 이면
    // 출력할 이름은 aaa.bbb.Hello 이다.
    File[] files = path.listFiles(f -> f.isDirectory() || f.getName().endsWith(".class"));
    // 2) path가 디렉토리라면 하위 디렉토리와 파일 목록을 얻는다.
    // => 단 필터를 이용하여 디렉토리와 클래스 파일(.class) 목록만 추출한다.
    // 3) 하위 디렉토리와 파일 목록에서 클래스를 찾는다.
    for (File file : files) {
      if (file.isDirectory())
        findClass(file, packageName + "." + file.getName());
      else {
        System.out.println(
            String.format("%s.%s", packageName, file.getName().replace(".class", "").substring(1)));
      }
    }
    // File[] files = dir.listFiles(pathname ->
    // pathname.isDirectory() ||
    // (pathname.isFile() && pathname.getName().endsWith(".class")) ?
    // true : false);
    //
    // for (File file : files) {
    // if (file.isFile()) {
    // System.out.printf("%s%s\n",
    // packageName,
    // file.getName().replace(".class", ""));
    // } else {
    // findClass(file, packageName + file.getName() + ".");
    // }
    // }
  }
}


