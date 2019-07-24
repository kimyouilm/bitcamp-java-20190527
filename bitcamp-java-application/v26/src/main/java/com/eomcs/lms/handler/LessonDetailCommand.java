package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Input;

public class LessonDetailCommand implements Command{

  private List<Lesson> list;

  private Input input;

  public LessonDetailCommand(Input input, List<Lesson> list) {
    this.input = input;
    this.list = list;
  }

  @Override
  public void execute() {
    int no = input.getIntValue("번호? ");
    // 사용자가 입력한 번호를 가지고 목록에서 그 번호에 해당하는 Lessons 객체를 찾는다.
    Lesson lesson = null;
    // 인덱스를 알아낸다.
    for (int i = 0; i < list.size(); i++) {
      Lesson temp = list.get(i);
      if (temp.getNo() == no) {
        lesson = temp;
        break;
      }
    }

    if (lesson == null) {
      System.out.println("해당 번호의 데이터가 없습니다.");
      // 리턴타입이 void라면 함수를 끝내고 싶을때 return;
      return;
    }

    System.out.printf("수업명: %s\n", lesson.getTitle());
    System.out.printf("수업내용: %s\n", lesson.getContents());
    System.out.printf("수업기간: %s ~ %s\n", lesson.getStartDate(), lesson.getEndDate());
    System.out.printf("총 수업시간: %s\n", lesson.getTotalHours());
    System.out.printf("일 수업시간:: %s\n", lesson.getDayHours());
  }
}
