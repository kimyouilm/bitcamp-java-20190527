package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet{
  
  private static final long serialVersionUID = 1L;
  private static final Logger logger = LogManager.getLogger(MemberAddServlet.class);
  private MemberDao memberDao;

  @Override
  public void init() throws ServletException {
    ApplicationContext appCtx =
        (ApplicationContext) getServletContext().getAttribute("iocContainer");
    memberDao = appCtx.getBean(MemberDao.class);
  }
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>맴버 등록폼</title></head>");
    out.println("<body><h1>맴버 등록폼</h1>");
    out.println("<form action='/member/add'method='post'>");
    out.println("이름 : <textarea name='name' rows='1' cols='50'></textarea><br>");
    out.println("이메일 : <textarea name='email' rows='1' cols='50'></textarea><br>");
    out.println("암호  : <textarea name='password' rows='1' cols='50'></textarea><br>");
    out.println("사진 : <textarea name='photo' rows='1' cols='50'></textarea><br>");
    out.println("전화 : <textarea name='tel' rows='1' cols='50'></textarea><br>");
    out.println("<button>등록</button>");
    out.println("</form>");
    out.println("</body></html>");
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    try {
      Member member = new Member();
      member.setName(request.getParameter("name"));
      member.setName(request.getParameter("email"));
      member.setName(request.getParameter("password"));
      member.setName(request.getParameter("photo"));
      member.setName(request.getParameter("tel"));

      memberDao.insert(member);
      response.sendRedirect("/member/list");
    } catch (Exception e) {
      PrintWriter out = response.getWriter();
      out.println("<html><head><title>맴버 등록</title>"
          + "<meta http-equiv='Refresh' content='1;url=/member/list'>" + "</head>");
      out.println("<body><h1>맴버 등록</h1>");
      out.println("<p>데이터 저장에 실패했습니다!</p>");
      out.println("</body></html>");
      response.setHeader("Refresh", "1/url=/member/list");

      // 왜 오류가 발생했는지 자세한 사항은 로그로 남긴다.
      StringWriter strOut = new StringWriter();
      e.printStackTrace(new PrintWriter(strOut));
      logger.error(strOut.toString());
    }
  }
}
