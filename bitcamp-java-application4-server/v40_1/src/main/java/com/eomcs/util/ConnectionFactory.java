package com.eomcs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// DAO가 사용할 커넥션 객체를 생성해주는 역할
public class ConnectionFactory {
  String jdbcDriver;
  String jdbcUrl;
  String username;
  String password;

  // 스레드 별로 커넥션 객체를 사용하도록 
  // 현쟈 스레드의 값을 넣고 꺼낼수 수 있는 도구를 준비한다.
  ThreadLocal<Connection> localConnection = new ThreadLocal<>();
  public ConnectionFactory(
      String jdbcDriver, String jdbcUrl, String username, String password) {
    
    this.jdbcDriver = jdbcDriver;
    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;
  }

  public Connection getConnection() throws Exception {
    // ThreaLocal 도구를 사용하여 현재 스레드에서 커넥션 객체를 꺼낸다.
    Connection con = localConnection.get();
    
    // 없다면 새로만들어 현재 스레드에 보관한다.
    if (con == null) {
      Class.forName(jdbcDriver);
      con = new TxConnection(DriverManager.getConnection(jdbcUrl, username, password));
      // 생성한 커넥션을 리턴하기 전에 ThreadLocal도구를 사용하여 현재 스레드에 보관한다.
      localConnection.set(con);
    }
    return con;
  }

  // 현재 스레드에 보관된 커넥션 객체를 삭제한다.
  public void clearConnection() {
    Connection con = localConnection.get();
    if (localConnection.get() != null) {
      try {((TxConnection)con).realClose();} catch (SQLException e) {}
      localConnection.remove();
    }
  }
}





