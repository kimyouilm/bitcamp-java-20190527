package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Input;

public class LessonAddCommand implements Command {

  private LessonDao lessonDao;

  public LessonAddCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {
    // 수업 데이터를 저장할 메모리를 Lesson 설계도에 따라 만든다.

    try {
      Lesson lesson = new Lesson();
      // 사용자가 입력한 값을 Lesson 인스턴스의 각 변수에 저장한다.
      lesson.setTitle(Input.getStringValue(in, out, "수업명? "));
      lesson.setContents(Input.getStringValue(in, out, "설명? "));
      lesson.setStartDate(Input.getDateValue(in, out, "시작일? "));
      lesson.setEndDate(Input.getDateValue(in, out, "종료일? "));
      lesson.setTotalHours(Input.getIntValue(in, out, "총수업시간? "));
      lesson.setDayHours(Input.getIntValue(in, out, "일수업시간? "));
      lessonDao.insert(lesson);
      out.println("저장하였습니다.");

    } catch (Exception e) {
      out.println("데이터 저장에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }

}


