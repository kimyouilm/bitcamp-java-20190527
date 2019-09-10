package com.eomcs.lms.handler;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.ServletRequest;
import com.eomcs.util.ServletResponse;

@Component
public class LessonCommand {

  private LessonDao lessonDao;

  public LessonCommand(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @RequestMapping("/lesson/form")
  public void form(ServletRequest request, ServletResponse response) {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>수업 등록폼</title></head>");
    out.println("<body><h1>수업 등록폼</h1>");
    out.println("<form action='/lesson/add'>");
    out.println("수업명 : <textarea name='title' rows='1' cols='50'></textarea><br>");
    out.println("설명 : <textarea name='contents' rows='1' cols='50'></textarea><br>");
    out.println("시작일?  : <textarea name='startDate' rows='1' cols='50'></textarea><br>");
    out.println("종료일 : <textarea name='endDate' rows='1' cols='50'></textarea><br>");
    out.println("총수업시간 : <textarea name='totalHours' rows='1' cols='50'></textarea><br>");
    out.println("일수업시간 : <textarea name='dayHours' rows='1' cols='50'></textarea><br>");
    out.println("<button>등록</button>");
    out.println("</form>");
    out.println("</body></html>");
  }
  
  @RequestMapping("/lesson/add") // 클라이언트 요청이 들어 왔을 때 이 메서드를 호출하라고 표시한다.
  public void add(ServletRequest request, ServletResponse response) {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>수업 등록</title>"
        + "<meta http-equiv='Refresh' content='1;url=/lesson/list'>" 
        + "</head>");
    out.println("<body><h1>게시물 등록</h1>");
    
    try {
      Lesson lesson = new Lesson();
      lesson.setTitle(request.getParameter("title"));
      lesson.setContents(request.getParameter("contents"));
      lesson.setStartDate(Date.valueOf(request.getParameter("startDate")));
      lesson.setEndDate(Date.valueOf(request.getParameter("endDate")));
      lesson.setTotalHours(Integer.valueOf(request.getParameter("totalHours")));
      lesson.setDayHours(Integer.valueOf(request.getParameter("dayHours")));
      
      lessonDao.insert(lesson);
      out.println("<p>저장하였습니다!</p>");
      
    } catch (Exception e) {
      out.println("<p>데이터 저장에 실패했습니다!</p>");
      System.out.println(e.getMessage());
    }
    out.println("</body></html>");
  }
  
  @RequestMapping("/lesson/delete") // 클라이언트 요청이 들어 왔을 때 이 메서드를 호출하라고 표시한다.
  public void delete(ServletRequest request, ServletResponse response) {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>수업 삭제</title>"
        + "<meta http-equiv='Refresh' content='1;url=/lesson/list'>"
        + "</head>");
    out.println("<body><h1>수업 삭제</h1>");
    try {

      int no = Integer.parseInt(request.getParameter("no"));
      
      if (lessonDao.delete(no) > 0) {
        out.println("<p>삭제하였습니다.!</p>");
      } else {
        out.println("<p>해당 데이터가 없습니다.</p>");
      }
      
    } catch (Exception e) {
      out.println("<p>데이터 삭제에 실패했습니다!</p>");
      System.out.println(e.getMessage());
    }
    out.println("</body></html>");
  }

  @RequestMapping("/lesson/detail") // 클라이언트 요청이 들어 왔을 때 이 메서드를 호출하라고 표시한다.
  public void detail(ServletRequest request, ServletResponse response) {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>수업 상세</title></head>");
    out.println("<body><h1>수업 상세</h1>");
    try {
     
      int no = Integer.parseInt(request.getParameter("no"));
      Lesson lesson = lessonDao.findBy(no);
      if (lesson == null) {
        out.println("<p>해당 번호의 데이터가 없습니다!</p>");
      } else {
        out.println("<form action='/lesson/update'>");
        out.printf("번호 : <input type='text' name='no' value='%d' readonly><br>\n",
            lesson.getNo());
        out.printf("제목 : <textarea name='title' rows='1'"
            + " cols='50'>%s</textarea><br>\n",
            lesson.getTitle());
        out.printf("내용 : <textarea name='contents' rows='2'"
            + " cols='50'>%s</textarea><br>\n",
            lesson.getContents());
        out.printf("시작 : <textarea name='staratDate' rows='1'"
            + " cols='50'>%s</textarea><br>\n", lesson.getStartDate());
        out.printf("끝 : <textarea name='endDate' rows='1'"
            + " cols='50'>%s</textarea><br>\n", lesson.getEndDate());
        out.printf("총수업시간 : <textarea name='totlaHours' rows='1'"
            + " cols='50'>%s</textarea><br>\n", lesson.getTotalHours());
        out.printf("일수업시간 : <textarea name='dayHours' rows='1'"
            + " cols='50'>%s</textarea><br>\n", lesson.getDayHours());
        out.println("<button>변경</button>");
        out.printf("<a href='/lesson/delete?no=%d'>삭제</a>\n", lesson.getNo());
        out.println("</form>");
      }
    } catch (Exception e) {
      out.println("<p>데이터 조회에 실패했습니다!</p>");
      System.out.println(e.getMessage());
    }
    out.println("</body></html>");
  }
  
  @RequestMapping("/lesson/list") // 클라이언트 요청이 들어 왔을 때 이 메서드를 호출하라고 표시한다.
  public void list(ServletRequest request, ServletResponse response) {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>수업 목록</title>"
        + "<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' integrity='sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T' crossorigin='anonymous'>"
        + "</head>");
    out.println("<body><h1>수업 목록</h1>");
    out.println("<a href='/lesson/form'>수업 등록</a><br>");
    try {
      out.println("<table class='table table-hover'>");
      out.println("<tr><th>번호</th><th>수업</th><th>내용</th><th>시작일</th><th>끝나는일</th><th>총수업시간</th></tr>");
      List<Lesson> lessons = lessonDao.findAll();
      for (Lesson lesson : lessons) {
        out.printf("<tr><td>%d</td>"
            + "<td><a href='/lesson/detail?no=%d'>%s</a></td>"
            + "<td>%s</td><td>%s</td><td>%s</td><td>%d</td></tr>\n", 
            lesson.getNo(),
            lesson.getNo(),
            lesson.getTitle(),
            lesson.getContents(), 
            lesson.getStartDate(),
            lesson.getEndDate(),
            lesson.getTotalHours());
      }
      
    } catch (Exception e) {
      out.println("<p>데이터 조회에 실패했습니다!</p>");
      System.out.println(e.getMessage());
    }
    out.println("</body></html>");
  }
  
  @RequestMapping("/lesson/update") 
  public void update(ServletRequest request, ServletResponse response) {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>수업 변경</title>"
        + "<meta http-equiv='Refresh' content='1;url=/lesson/list'>"
        + "</head>");
    out.println("<body><h1>수업 변경</h1>");
    try {
      Lesson lesson = new Lesson();
      lesson.setNo(Integer.parseInt(request.getParameter("no")));
      lesson.setTitle(request.getParameter("title"));
      lesson.setContents(request.getParameter("contents"));
      lesson.setStartDate(Date.valueOf(request.getParameter("startDate")));
      lesson.setEndDate(Date.valueOf(request.getParameter("endDate")));
      lesson.setTotalHours(Integer.parseInt(request.getParameter("totalHours")));
      lesson.setDayHours(Integer.parseInt(request.getParameter("dayHours")));
      
      lessonDao.update(lesson);
      out.println("<p>변경 했습니다</p>");
      
    } catch (Exception e) {
      out.println("<p>데이터 변경에 실패했습니다!</p>");
      throw new RuntimeException(e);
      
    } finally {
      out.println("</body></html>");
    }
  }
}




