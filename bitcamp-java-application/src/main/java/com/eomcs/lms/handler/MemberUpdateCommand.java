package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;

public class MemberUpdateCommand implements Command{

  private List<Member> memberList;
  private Input input;

  public MemberUpdateCommand(Input input, List<Member> list) {
    this.input = input;
    this.memberList = list;
  }

  @Override
  public void execute() {
    int no = input.getIntValue("번호?");
    
    Member member = null;
    
    for (int i = 0; i < memberList.size(); i++) {
      Member temp = memberList.get(i);
      if(temp.getNo() == no) {
        member = temp;
        break;
      }
    }
    
    if(member == null) {
      System.out.println("해당하는 번호의 데이터가 없습니다.");
      return;
    }
    String str = input.getStringValue("이름(" + member.getName() + ")");
    if(str.length() > 0) {
      member.setName(str);
    }
    str = input.getStringValue("이메일(" + member.getEmail() + ")");
    if(str.length() > 0) {
      member.setEmail(str);
    }
    str = input.getStringValue("암호(" + member.getPw() + ")");
    if(str.length() > 0) {
      member.setPw(str);
    }
    str = input.getStringValue("사진(" + member.getPic() + ")");
    if(str.length() > 0) {
      member.setPic(str);
    }
    str = input.getStringValue("전번(" + member.getPhoneNum() + ")");
    if(str.length() > 0) {
      member.setPhoneNum(str);
    }
    
    
  }
}
