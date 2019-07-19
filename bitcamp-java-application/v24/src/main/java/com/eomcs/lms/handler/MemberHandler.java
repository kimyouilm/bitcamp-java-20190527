package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;
import com.eomcs.util.LinkedList;
import com.eomcs.util.List;

public class MemberHandler {

  private List<Member> memberList;
  private Input input;

  public MemberHandler(Input input, List<Member> list) {
    this.input = input;
    this.memberList = list;
  }

  public void listMember() {

    Member[] members = memberList.toArray(new Member[] {});
    for(Member member : members) {
      System.out.printf("%s, %s, %s, %s, %s, %s, %s\n\n", 
          member.getNo(), member.getName(), member.getEmail(), member.getPw(), 
          member.getPic(), member.getPhoneNum(), member.getSignedUpDate());
      
    }
  }

  public void addMember() {
    Member member = new Member();

    member.setNo(input.getIntValue("번호 ?"));
    member.setName(input.getStringValue("이름? "));
    member.setEmail(input.getStringValue("이메일? "));
    member.setPw(input.getStringValue("암호? "));
    member.setPic(input.getStringValue("사진? "));
    member.setPhoneNum(input.getStringValue("전화? "));
    member.setSignedUpDate(input.getDateValue("가입일? "));

    memberList.add(member);
    
    System.out.println("저장하였습니다.");
  }

  public void detailMember() {
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
    
    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("이메일: %s\n", member.getEmail());
    System.out.printf("암호: %s\n", member.getPw());
    System.out.printf("사진: %s\n", member.getPic());
    System.out.printf("전번: %s\n", member.getPhoneNum());
    System.out.printf("가입일: %s\n", member.getSignedUpDate());
  }

  public void updateMember() {
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

  public void deleteMember() {
    int no = input.getIntValue("번호?");
    
    Member member = null;
    
    for (int i = 0; i < memberList.size(); i++) {
      Member temp = memberList.get(i);
      if(temp.getNo() == no) {
        memberList.remove(i);
        break;
      }
    }
    if(member == null) {
      System.out.println("해당하는 번호의 데이터가 없습니다.");
      return;
    }
  }
}
