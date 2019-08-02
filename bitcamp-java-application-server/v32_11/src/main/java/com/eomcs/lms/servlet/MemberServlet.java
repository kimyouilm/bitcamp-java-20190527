package com.eomcs.lms.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import com.eomcs.lms.Servlet;
import com.eomcs.lms.dao.MemberSerialDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Member;

public class MemberServlet implements Servlet {

  MemberSerialDao memberDao;
  ObjectInputStream in;
  ObjectOutputStream out;

  public MemberServlet(ObjectInputStream in, ObjectOutputStream out) throws ClassNotFoundException {
    this.in = in;
    this.out = out;

    memberDao = new MemberSerialDao("./member.ser");
  }

  public void saveData() {
    memberDao.saveData();
  }

  @Override
  public void service(String command) throws Exception {
    // 명령어에 따라 처리한다.
    switch (command) {
      // Member
      case "/member/add":
        // 클라이언트가 보낸 객체를 읽는다.
        addMember();
        break;
      case "/member/list":
        listMember();
        break;
      case "/member/delete":
        deleteMember();
        break;
      case "/member/detail":
        detailMember();
        break;
      case "/member/update":
        updateMember();
        break;

      case "quit":
        out.writeUTF("ok");
        break;
      default:
        fail("지원하지 않는 명령입니다.");
    }
  }

  private void addMember() throws Exception {
    Member member = (Member) in.readObject();
    if (memberDao.insert(member) == 0) {
      fail("게시물을 입력할 수 없습니다.");
      return;
    }
    out.writeUTF("ok");
  }

  private void listMember() throws Exception {
    out.writeUTF("ok");
    out.reset();
    out.writeObject(memberDao.findAll());
  }

  private void deleteMember() throws Exception {
    int no = in.readInt();
    if (memberDao.delete(no) == 0) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }
    memberDao.delete(no);
    out.writeUTF("ok");
  }

  private void detailMember() throws Exception {
    int no = in.readInt();
    Member member = memberDao.findBy(no);

    if (member == null) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }

    out.writeUTF("ok");
    out.writeObject(member);
  }

  private void updateMember() throws Exception {
    Member member = (Member) in.readObject();

    if (memberDao.update(member) == 0) {
      fail("해방 번호의 게시물이 없습니다.");
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
