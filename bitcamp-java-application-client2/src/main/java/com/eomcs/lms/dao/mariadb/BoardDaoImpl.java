package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardDaoImpl implements BoardDao {

  @Override
  public int insert(Board board) throws Exception {
    try (
        Connection con = DriverManager
            .getConnection("jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111");
        Statement stmt = con.createStatement()) {

      return stmt
          .executeUpdate("insert into lms_board(conts)" + " values('" + board.getContents() + "')");
    }
  }

  @Override
  public List<Board> findAll() throws Exception {
    try (
        Connection con = DriverManager
            .getConnection("jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from lms_board order by board_id desc")) {

      ArrayList<Board> list = new ArrayList<>();

      while (rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_id"));
        board.setContents(rs.getString("conts"));
        board.setCreatedDate(rs.getDate("cdt"));
        board.setViewCount(rs.getInt("vw_cnt"));

        list.add(board);
      }
      return list;
    }
  }

  @Override
  public Board findBy(int no) throws Exception {
    try (
        Connection con = DriverManager
            .getConnection("jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from lms_board where board_id = " + no)) {

      Board board = new Board();
      if (rs.next()) {
        board.setNo(rs.getInt("board_id"));
        board.setContents(rs.getString("conts"));
        board.setCreatedDate(rs.getDate("cdt"));
        board.setViewCount(rs.getInt("vw_cnt"));
      }
      return board;
    }
  }

  @Override
  public int update(Board board) throws Exception {
    try (
        Connection con = DriverManager
            .getConnection("jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111");
        Statement stmt = con.createStatement()) {


      // update 문장은 executeUpdate()를 사용하여 서버에 전달한다.
      stmt.executeUpdate("update lms_board set" + ", contents = '" + board.getContents() + "'"
          + " where board_id = " + board.getNo());

    }
    return 1;
  }

  @Override
  public int delete(int no) throws Exception {
    try (Connection con = DriverManager
        .getConnection("jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {

      try (Statement stmt = con.createStatement()) {

        // delete 문장은 executeUpdate()를 사용하여 서버에 전달한다.
        int count = stmt.executeUpdate("delete from x_board where board_id = " + no);

        if (count == 0) {
          System.out.println("해당 번호의 게시물이 존재하지 않습니다.");
        } else {
          System.out.println("삭제하였습니다.");
        }
      }

    }
    return 0;
  }

}
