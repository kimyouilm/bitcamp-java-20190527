package com.eomcs.util;

import java.sql.Connection;

// DB 커넥션 객체를 사용하여 트랜잭션을 관리하는 일을 한다.
public class PlatformTransactionManager {

  private DataSource dataSource;

  public PlatformTransactionManager(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public void beginTransaction() throws Exception {
    // ConnectionFactory는 커넥션 객체를 준비한 후
    // 그 커넥션 객체(의주소)를 현재 스레드의 주머니에 보관하고(주소를), 리턴한다(주소를).
    Connection con = dataSource.getConnection();
    con.setAutoCommit(false);
  }

  public void commit() throws Exception {
    // 현재 스레드의 주머니에서 커넥션 객체를 꺼낸다.
    Connection con = dataSource.getConnection();
    con.commit();
  }

  public void rollback() throws Exception {
    // 현재 스레드의 주머니에서 커넥션 객체를 꺼낸다.
    Connection con = dataSource.getConnection();
    con.rollback();
    con.setAutoCommit(true);
  }

}
