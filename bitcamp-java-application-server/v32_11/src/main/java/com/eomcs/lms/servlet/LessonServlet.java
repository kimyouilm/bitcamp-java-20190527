package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.Servlet;
import com.eomcs.lms.dao.LessonSerialDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Lesson;

public class LessonServlet implements Servlet {

  LessonSerialDao lessonDao;
  ObjectInputStream in;
  ObjectOutputStream out;

  // 클래스가 없으면 실행이 안됨으로 던져
  public LessonServlet(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    this.in = in;
    this.out = out;
    
    lessonDao = new LessonSerialDao("./lesson.ser");
  }

  public void saveData() {
    lessonDao.saveData();
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
    if (lessonDao.insert(lesson) == 0) {
      fail("게시물을 입력할 수 없습니다.");
      return;
    }
    out.writeUTF("ok");
  }

  private void listLesson() throws Exception {
    out.writeUTF("ok");
    out.reset();
    out.writeObject(lessonDao.findAll());
  }

  private void deleteLesson() throws Exception {
    int no = in.readInt();
    
    if (lessonDao.delete(no) == 0) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }
    lessonDao.delete(no);
    out.writeUTF("ok");
  }

  private void detailLesson() throws Exception {
    int no = in.readInt();
    Lesson lesson = lessonDao.findBy(no);
    
    if (lesson == null) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }

    out.writeUTF("ok");
    out.writeObject(lesson);
  }

  private void updateLesson() throws Exception {
    Lesson lesson = (Lesson) in.readObject();

    if (lessonDao.update(lesson) == 0) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }

    out.writeUTF("ok");
  }
  // wow 이것도... 메소드로..?
  private void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }
}
