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
import com.eomcs.lms.domain.Board;

// 게시물 요청을 처리하는 담당자
public class BoardServlet implements Servlet {

  ArrayList<Board> boardList = new ArrayList<>();

  ObjectInputStream in;
  ObjectOutputStream out;

  // 클래스가 없으면 실행이 안됨으로 던져
  public BoardServlet(ObjectInputStream in, ObjectOutputStream out) throws ClassNotFoundException {
    this.in = in;
    this.out = out;

    try {
      loadData();
    } catch (IOException e) {
      System.out.println("보드 게시물 데이터 로딩중 오류 발생");
      e.printStackTrace();
    }

  }

  @SuppressWarnings("unchecked")
  private void loadData() throws IOException, ClassNotFoundException {
    File file = new File("./board.ser");

    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {

      boardList = (ArrayList<Board>) in.readObject();
      System.out.println("보드 게시물 데이터 로딩 완료");

    } catch (FileNotFoundException e) {
      System.out.println("보드 읽을 파일을 찾을 수 없습니다!");

    } catch (Exception e) {
      System.out.println("보드 파일을 읽는 중에 오류가 발생했습니다!");

    }
  }

  public void saveData() {

    File file = new File("./board.ser");

    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {

      out.writeObject(boardList);
      System.out.println("보드 게시물 데이터 저장 완료");
    } catch (FileNotFoundException e) {
      System.out.println("보드 파일을 생성할 수 없습니다!");

    } catch (IOException e) {
      System.out.println("보드 파일에 데이터를 출력하는 중에 오류 발생!");
      e.printStackTrace();

    }
  }

  @Override
  public void service(String command) throws Exception {

    switch (command) {
      // Board
      case "/board/add":
        // 클라이언트가 보낸 객체를 읽는다.
        addBoard();
        break;
      case "/board/list":
        listBoard();
        break;
      case "/board/delete":
        deleteBoard();
        break;
      case "/board/detail":
        detailBoard();
        break;
      case "/board/update":
        updateBoard();
        break;
      default:
        fail("지원하지 않는 명령입니다.");
    }

  }

  private void addBoard() throws Exception {
    Board board = (Board) in.readObject();
    out.writeUTF("ok");
    boardList.add(board);
  }

  private void listBoard() throws Exception {
    out.writeUTF("ok");
    out.reset();
    out.writeObject(boardList);
  }

  private void deleteBoard() throws Exception {
    int no = in.readInt();
    int index = indexOfBoard(no);
    if (index == -1) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }

    boardList.remove(index);
    out.writeUTF("ok");
  }

  private void detailBoard() throws Exception {
    int no = in.readInt();
    int index = indexOfBoard(no);

    if (index == -1) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }

    out.writeUTF("ok");
    out.writeObject(boardList.get(index));
  }

  private void updateBoard() throws Exception {
    Board board = (Board) in.readObject();
    int index = indexOfBoard(board.getNo());

    if (index == -1) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }

    boardList.set(index, board);
    out.writeUTF("ok");
  }

  private int indexOfBoard(int no) {
    int i = 0;
    for (Board m : boardList) {
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
