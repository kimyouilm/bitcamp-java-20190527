package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;
import com.eomcs.lms.util.Input;

public class MemberHandler {

  private MemberList memberList = new MemberList();
  private Input input;

  public MemberHandler(Input input) {
    this.input = input;
  }

  public void listMember() {

    Member[] members = memberList.toArray();
    for(Member member : members) {
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

    memberList.add(member);
    
    System.out.println("저장하였습니다.");
  }
}
