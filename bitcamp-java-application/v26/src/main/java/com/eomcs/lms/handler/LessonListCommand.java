package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Input;

public class LessonListCommand implements Command{

  private List<Lesson> list;

  public LessonListCommand(Input input, List<Lesson> list) {
    this.list = list;
  }
 
  @Override
  public void execute() {

    Lesson[] lessons = list.toArray(new Lesson[] {});
    for (Lesson lesson : lessons) {
      System.out.printf("%s, %s, %s, %s ~ %s, %s\n\n", lesson.getNo(), lesson.getTitle(),
          lesson.getContents(), lesson.getStartDate(), lesson.getEndDate(), lesson.getTotalHours(),
          lesson.getDayHours());
    }
  }
}
