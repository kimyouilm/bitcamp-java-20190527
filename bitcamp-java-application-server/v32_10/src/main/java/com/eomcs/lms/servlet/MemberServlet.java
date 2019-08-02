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
import com.eomcs.lms.domain.Member;

public class MemberServlet implements Servlet {

  ArrayList<Member> memberList = new ArrayList<>();

  ObjectInputStream in;
  ObjectOutputStream out;

  public MemberServlet(ObjectInputStream in, ObjectOutputStream out) throws ClassNotFoundException {
    this.in = in;
    this.out = out;

    try {
      loadData();
    } catch (IOException e) {
      System.out.println("멤버 게시물 데이터 로딩중 오류 발생");
      e.printStackTrace();
    }

  }

  @SuppressWarnings("unchecked")
  private void loadData() throws IOException, ClassNotFoundException {
    File file = new File("./member.ser");

    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {

      memberList = (ArrayList<Member>) in.readObject();
      System.out.println("맴버 게시물 데이터 로딩 완료");
    } catch (FileNotFoundException e) {
      System.out.println("멤버 읽을 파일을 찾을 수 없습니다!");

    } catch (Exception e) {
      System.out.println("멤버 파일을 읽는 중에 오류가 발생했습니다!");

    }
  }

  public void saveData() {
    File file = new File("./member.ser");

    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {

      out.writeObject(memberList);
      System.out.println("멤버 데이터 저장 완료");
    } catch (FileNotFoundException e) {
      System.out.println("멤버 파일을 생성할 수 없습니다!");

    } catch (IOException e) {
      System.out.println("멤버 파일에 데이터를 출력하는 중에 오류 발생!");
      e.printStackTrace();

    }
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
    out.writeUTF("ok");
    memberList.add(member);
  }

  private void listMember() throws Exception {
    out.writeUTF("ok");
    // 기존의 serialize했던 객체의 상태를 무시하고 다시 serialize한다.
    out.reset();
    out.writeObject(memberList);
  }

  private void deleteMember() throws Exception {
    int no = in.readInt();
    int index = indexOfMember(no);
    if (index == -1) {
      // if (index != -1) {
      fail("해당 번호의 회원이 없습니다.");
      // memberList.remove(index);
      // out.writeUTF("ok");
      return;
    }
    /*
     * for (Member m : memberList) { if (m.getNo() == no) { // 객체 주소를 지움 memberList.remove(m);
     * out.writeUTF("ok"); return; } }
     */

    // 위의 for문에서 못찾으면
    // out.writeUTF("fail");
    // out.writeUTF("해당번호의 회원이 없습니다.");

    memberList.remove(index);
    out.writeUTF("ok");
  }

  private void detailMember() throws Exception {
    int no = in.readInt();
    int index = indexOfMember(no);

    if (index == -1) {
      // if (index != -1) {
      fail("해당 번호의 회원이 없습니다.");
      // memberList.remove(index);
      // out.writeUTF("ok");
      return;
    }

    out.writeUTF("ok");
    out.writeObject(memberList.get(index));

    /*
     * for (Member m : memberList) { if (m.getNo() == no) { out.writeUTF("ok"); out.writeObject(m);
     * return; } }
     */
  }

  private void updateMember() throws Exception {
    Member member = (Member) in.readObject();
    int index = indexOfMember(member.getNo());

    if (index == -1) {
      // if (index != -1) {
      fail("해당 번호의 회원이 없습니다.");
      // memberList.remove(index);
      // out.writeUTF("ok");
      return;
    }

    memberList.set(index, member);
    out.writeUTF("ok");
    // for (int i = 0; i < memberList.size(); i++) {
    // Member m = memberList.get(i);
    // if (m.getNo() == member.getNo()) {
    // // 기존 객체를 클라이언트가 보낸객체로 교체한다.
    // memberList.set(i, member);
    // out.writeUTF("ok");
    // return;
    // }
    // }
  }

  private int indexOfMember(int no) {
    int i = 0;
    for (Member m : memberList) {
      if (m.getNo() == no) {
        return i;
      }
      i++;
    }
    return -1;
  }

  // wow 이것도... 메소드로..?
  private void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }
}
