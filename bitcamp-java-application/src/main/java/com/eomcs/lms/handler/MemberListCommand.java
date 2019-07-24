package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;

public class MemberListCommand implements Command{

  private List<Member> list;

  public MemberListCommand(Input input, List<Member> list) {
    this.list = list;
  }

  public void execute() {

    Member[] members = list.toArray(new Member[] {});
    for(Member member : members) {
      System.out.printf("%s, %s, %s, %s, %s, %s, %s\n\n", 
          member.getNo(), member.getName(), member.getEmail(), member.getPw(), 
          member.getPic(), member.getPhoneNum(), member.getSignedUpDate());
      
    }
  }
}
