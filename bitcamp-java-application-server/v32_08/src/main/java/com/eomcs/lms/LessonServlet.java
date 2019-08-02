package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import com.eomcs.lms.domain.Lesson;

public class LessonServlet implements Servlet{

  ArrayList<Lesson> lessonList = new ArrayList<>();
  
  ObjectInputStream in;
  ObjectOutputStream out;
  
  public LessonServlet(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
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
