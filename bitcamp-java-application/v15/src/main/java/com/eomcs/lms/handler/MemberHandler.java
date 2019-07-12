package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;
import com.eomcs.lms.util.Input;

public class MemberHandler {
  
  static Member[] members = new Member[100];
  static int membersSize = 0;
  
  private Input input;
  public MemberHandler(Input input) {
    this.input = input;
  }
  public void listMember() {
    for (int i=0; i < membersSize; i++){
      Member member = members[i];
      System.out.printf("%s, %s, %s, %s, %s, %s, %s\n\n", 
          member.getNo(), member.getLectureName(), member.getEmail(), member.getPw(), 
          member.getPic(), member.getPhoneNum(), member.getSignedUpDate());
    }
  }

  public void addMember() {
    Member member = new Member();
    
    member.setNo(input.getIntValue("번호 ?"));
    member.setLectureName(input.getStringValue("이름? "));
    member.setEmail(input.getStringValue("이메일? "));
    member.setPw(input.getStringValue("암호? "));
    member.setPic(input.getStringValue("사진? "));
    member.setPhoneNum(input.getStringValue("전화? "));
    member.setSignedUpDate(input.getDateValue("가입일? "));
    
    members[membersSize++] = member;
    System.out.println("저장하였습니다.");
  }
}
