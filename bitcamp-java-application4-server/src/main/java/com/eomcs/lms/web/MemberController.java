package com.eomcs.lms.web;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

@RequestMapping("/member")
@Controller
public class MemberController {

  @Resource 
  private MemberDao memberDao;

  @RequestMapping("form")
  public void form() {
  }
  
  @RequestMapping("add")
  public String add(
      HttpServletRequest request, 
      Member member, 
      Part file) throws Exception {
    
    String uploadDir = request.getServletContext().getRealPath("/upload/member");
    
    // 업로드 된 사진 파일 처리
    if (file != null && file.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      member.setPhoto(filename);
      file.write(uploadDir + "/" + filename);
    }

    memberDao.insert(member);
    return "redirect:list";
  }
  
  @RequestMapping("delete")
  public String delete(int no) 
      throws Exception {

    if (memberDao.delete(no) == 0) {
      throw new Exception("해당 데이터가 없습니다.");
    }
    return "redirect:list";
  }
  
  @RequestMapping("detail")
  public void detail(Map<String,Object> model, int no) 
      throws Exception {

    Member member = memberDao.findBy(no);
    if (member == null) {
      throw new Exception("해당 번호의 데이터가 없습니다!");
    } 

    model.put("member", member);
  }
  
  @RequestMapping("list")
  public void list(Map<String,Object> model) throws Exception {

    List<Member> members = memberDao.findAll();
    model.put("members", members);
  }
  
  @RequestMapping("search")
  public void search(Map<String,Object> model, String keyword) throws Exception {

    List<Member> members = memberDao.findByKeyword(keyword);
    model.put("members", members);
  }
  
  @RequestMapping("update")
  public String update(
      HttpServletRequest request,
      Member member,
      Part file) 
      throws Exception {
    
    String uploadDir = request.getServletContext().getRealPath("/upload/member");

    // 업로드 된 사진 파일 처리
    if (file != null && file.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      member.setPhoto(filename);
      file.write(uploadDir + "/" + filename);
    }
    memberDao.update(member);
    return "redirect:list";
  }
}
