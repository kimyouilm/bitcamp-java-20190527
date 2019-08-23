package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;
import com.eomcs.lms.App;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.util.Input;

public class PhotoBoardUpdateCommand implements Command {

  // 의존 객체 (DI)
  private PhotoBoardDao photoBoardDao;
  private PhotoFileDao photoFileDao;

  public PhotoBoardUpdateCommand(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {

    try {
      App.con.setAutoCommit(false);
      int no = Input.getIntValue(in, out, "번호? ");
      PhotoBoard photoBoard = photoBoardDao.findBy(no);
      if (photoBoard == null) {
        out.println("해당 번호의 데이터가 없습니다!");
        return;
      }

      out.println("제목을 변경하지 않으면 이전 제목을 유지합니다.");
      String str = Input.getStringValue(in, out, String.format("제목(%s)? ", photoBoard.getTitle()));

      if (str.length() > 0) {
        // 사진 게시글의 제목을 변경한다.
        photoBoard.setTitle(str);;
        photoBoardDao.update(photoBoard);
        out.println("게시물의 제목을 변경하였습니다.");
      }
      // 이전에 등록한 파일 목록을 출력한다.
      out.println("사진파일:");
      List<PhotoFile> files = photoFileDao.findAll(no);
      for (PhotoFile file : files) {
        out.printf("> %s\n", file.getFilePath());
      }

      // 파일을 변경할지 여부를 묻는다.
      out.println("사진은 일부만 변경할 수 없습니다.");
      out.println("전체를 새로 등록해야 합니다.");
      String response = Input.getStringValue(in, out, "사진을 변경하시겠습니까?(y/n)");

      if (response.equalsIgnoreCase("n")) {
        out.println("파일 변경을 취소합니다.");
        return;
      }
      // 기존 사진 파일을 삭제한다.
      photoFileDao.deleteAll(no);

      out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
      out.println("파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.");
      // 클라이언트에게 탕탕탕 쏘기
      // flush 생활화 하기
      out.flush();

      int count = 0;
      while (true) {
        String filepath = Input.getStringValue(in, out, "사진파일? ");
        if (filepath.length() == 0) {
          if (count > 0) {
            break;
          } else {
            out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
            continue;
          }
        }
        PhotoFile photoFile = new PhotoFile();
        photoFile.setFilePath(filepath);
        photoFile.setBoardNo(photoBoard.getNo());
        photoFileDao.insert(photoFile);
        count++;
      }
      App.con.commit();

      out.println("사진을 변경하였습니다.");


    } catch (Exception e) {
      // 예외가 발생하면 DBMS의 임시 데이터베이스에 보관된 데이터 변경작업들을 모두 취소한다.
      try {
        App.con.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
      out.println("데이터 변경에 실패했습니다!");
      System.out.println(e.getMessage());
    } finally {
      try {
        App.con.setAutoCommit(true);
      } catch (SQLException e1) {
      }
    }
  }

}