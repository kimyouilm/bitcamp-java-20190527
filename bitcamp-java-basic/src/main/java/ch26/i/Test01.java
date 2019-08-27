// 트렌잭션 다루기 - commit() & rollback 2) commit 호출전
package ch26.i;

import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test01 {

  public static void main(String[] args) throws Exception {

    InputStream inputStream = Resources.getResourceAsStream("ch26/i/mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = sqlSessionFactory.openSession();

    Board board = new Board();
    board.setTitle("a101");
    board.setContents("내용");

    int count = sqlSession.insert("board.insert", board);
    System.out.println(count);

    board = new Board();
    board.setTitle("a102");
    board.setContents("내용");
    count = sqlSession.insert("board.insert", board);
    
    
    // insert 할 때 사용한 SqlSession으로  select를 실행한다면,
    //  커밋을 하지 안하도,
    // 기본적으로 임시 데이터베이스에 보관된 입력 데이터를 포함하여 결과를 리턴 한다.
    // => 하지만 SqlSession을 닫으면 임시 보관된 데이터를 자동 제거된다.
    List<Board> boards = sqlSession.selectList("board.select");
    for (Board b : boards) {
      System.out.println(b);
    }
    sqlSession.close();

  }

}


