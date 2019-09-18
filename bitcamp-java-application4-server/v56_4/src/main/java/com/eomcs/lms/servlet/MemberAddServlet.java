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
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
      request.setAttribute("message", "데이터 추가에 실패했습니다");
      request.setAttribute("refresh", "/member/list");
      request.setAttribute("error", e);
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
