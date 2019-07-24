package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardDetailCommand implements Command{
  private List<Board> list;
  private Input input;
  
  // Interface이름을 파라미터로 하여 외부로부터의 값을 받음
  public BoardDetailCommand(Input input, List<Board> list) {
    this.input = input;
    this.list = list;
  }
  
  @Override
  public void execute() {
    int no = input.getIntValue("번호?");
    
    Board board = null;
    for (int i = 0; i < list.size(); i++) {
      Board temp = list.get(i);
      if(temp.getNo() == no) {
        board = temp;
        break;
      }
    }
    
    if(board == null) {
      System.out.println("해당하는 번호의 데이터가 없습니다.");
      return;
    }
    
    System.out.printf("수업내용: %s\n", board.getContents());
  }

}
