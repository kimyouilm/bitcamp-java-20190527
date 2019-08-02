package com.eomcs.lms.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.domain.Lesson;

public class LessonSerialDao {

  ArrayList<Lesson> list = new ArrayList<>();

  File file;

  public LessonSerialDao(String file) throws ClassNotFoundException {
    this.file = new File(file);

    try {
      loadData();
    } catch (IOException e) {
      System.out.println("레슨 게시물 데이터 로딩중 오류 발생");
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  private void loadData() throws IOException, ClassNotFoundException {
    File file = new File("./lesson.ser");

    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {

      list = (ArrayList<Lesson>) in.readObject();
      System.out.println("레슨 게시물 데이터 로딩 완료");
    } catch (FileNotFoundException e) {
      System.out.println("레슨 읽을 파일을 찾을 수 없습니다!");

    } catch (Exception e) {
      System.out.println("레슨 파일을 읽는 중에 오류가 발생했습니다!");

    }
  }

  public void saveData() {

    File file = new File("./lesson.ser");

    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {

      // lessonList를 통째로 출력하기
      out.writeObject(list);
      System.out.println("레슨 게시물 데이터 로딩 완료");
    } catch (FileNotFoundException e) {
      System.out.println("레슨 파일을 생성할 수 없습니다!");

    } catch (IOException e) {
      System.out.println("레슨 파일에 데이터를 출력하는 중에 오류 발생!");
      e.printStackTrace();

    }
  }


  public int insert(Lesson lesson) throws Exception {

    list.add(lesson);
    return 1;
  }

  public List<Lesson> findAll() throws Exception {
    return list;
  }

  public Lesson findBy(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1)
      return null;
    return list.get(index);
  }

  public int update(Lesson lesson) throws Exception {
    int index = indexOf(lesson.getNo());
    if (index == -1)
      return 0;
    list.set(index, lesson);
    return 1;
  }

  public int delete(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1)
      return 0;
    list.remove(index);
    return 1;
  }

  private int indexOf(int no) {
    int i = 0;
    for (Lesson m : list) {
      if (m.getNo() == no) {
        return i;
      }
      i++;
    }
    return -1;
  }
}
