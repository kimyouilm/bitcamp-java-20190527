package com.eomcs.lms.dao.csv;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

public abstract class AbstractCSVDataSerializer<T, K> {

  // 서브 클래스에서 저장된 데이터를 조회할 수 있도록 하기 위해 접근 범위를 protected로 한다.
  protected ArrayList<T> list = new ArrayList<>();

  // 내부에서만 사용할 필드이기 때문에, 외부에서는 사용해서는 안되는 필드이기 때문에 private로 한다.
  private File file;

  protected AbstractCSVDataSerializer(String file) {
    this.file = new File(file);
  }

  @SuppressWarnings("unchecked")
  protected void loadData() throws IOException, ClassNotFoundException {

    // Adapter Pattern
    try (BufferedReader in = new BufferedReader(
        // ByteStream을 charStream으로 변환 시켜주는 역할
        new InputStreamReader(new FileInputStream(file)))) {
      
      String line = null;
      while ((line = in.readLine()) != null) {
        String[] values = line.split(",");
        T obj = createObject(values);
        list.add(obj);
      }
    }
  }

  public void saveData() throws FileNotFoundException, IOException {

    // PrintStream에 바로 FileOutputStream할수있지만 buffered를 쓰기위하여 중간에 넣음
    try (PrintStream out = new PrintStream(
        new BufferedOutputStream(new FileOutputStream(file)))) {
      
      for (T obj : list) {
        out.println(createCSV(obj));
      }
    }
  }

  protected abstract String createCSV(T obj);

  // 서브 클래스에게 문자열에 배열에 대해 객체를 생성하는 책임을 넘긴다.
  // => 위으 loadData() 메소드에서 기본 기능을 만들고
  //    특정 기능에 대해서는 서브 클래스에서 완성하도록 유도하는 설계 기법을 
  //    "tesmplate method" 패턴이라 부른다.
  protected abstract T createObject(String[] values);
  
  // 서브 클랫에서 특정 값을 가지고 데이터를 찾는 기능을 반드시 구현하도록 강제하자.
  public abstract int indexOf(K key);


  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  

}
