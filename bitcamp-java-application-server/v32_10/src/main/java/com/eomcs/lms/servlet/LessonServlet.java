package com.eomcs.lms.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import com.eomcs.lms.Servlet;
import com.eomcs.lms.domain.Lesson;

public class LessonServlet implements Servlet {

  ArrayList<Lesson> lessonList = new ArrayList<>();

  ObjectInputStream in;
  ObjectOutputStream out;

  // 클래스가 없으면 실행이 안됨으로 던져
  public LessonServlet(ObjectInputStream in, ObjectOutputStream out) throws ClassNotFoundException {
    this.in = in;
    this.out = out;

    try {
      loadData();
    } catch (IOException e) {
      System.out.println("레슨 게시물 데이터 로딩중 오류 발생");
      e.printStackTrace();
    }

  }

  @SuppressWarnings("unchecked")
  private void loadData() throws IOException, ClassNotFoundException {
    // 1) .ser은 뭔가? 쉽표로 나누었는데 쉼표에의한 데이터 구분없이 불러 오려고
    File file = new File("./lesson.ser");


    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {

      lessonList = (ArrayList<Lesson>) in.readObject();

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
      out.writeObject(lessonList);
      System.out.println("레슨 게시물 데이터 로딩 완료");
    } catch (FileNotFoundException e) {
      System.out.println("레슨 파일을 생성할 수 없습니다!");

    } catch (IOException e) {
      System.out.println("레슨 파일에 데이터를 출력하는 중에 오류 발생!");
      e.printStackTrace();

    }
  }

  @Override
  public void service(String command) throws Exception {
    switch (command) {
      // Lesson
      case "/lesson/add":
        // 클라이언트가 보낸 객체를 읽는다.
        addLesson();
        break;
      case "/lesson/list":
        listLesson();
        break;
      case "/lesson/delete":
        deleteLesson();
        break;
      case "/lesson/detail":
        detailLesson();
        break;
      case "/lesson/update":
        updateLesson();
        break;

      default:
        fail("지원하지 않는 명령입니다.");
    }


  }

  private void addLesson() throws Exception {
    Lesson lesson = (Lesson) in.readObject();
    out.writeUTF("ok");
    lessonList.add(lesson);
  }

  private void listLesson() throws Exception {
    out.writeUTF("ok");
    out.reset();
    out.writeObject(lessonList);
  }

  private void deleteLesson() throws Exception {
    int no = in.readInt();
    int index = indexOfLesson(no);
    if (index == -1) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }

    lessonList.remove(index);
    out.writeUTF("ok");
  }

  private void detailLesson() throws Exception {
    int no = in.readInt();
    int index = indexOfLesson(no);

    if (index == -1) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }

    out.writeUTF("ok");
    out.writeObject(lessonList.get(index));
  }

  private void updateLesson() throws Exception {
    Lesson lesson = (Lesson) in.readObject();
    int index = indexOfLesson(lesson.getNo());

    if (index == -1) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }

    lessonList.set(index, lesson);
    out.writeUTF("ok");
  }

  private int indexOfLesson(int no) {
    int i = 0;
    for (Lesson m : lessonList) {
      if (m.getNo() == no) {
        return i;
      }
      i++;
    }
    return -1;
  }

  // wow 이것도... 메소드로..?
  private void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }
}
