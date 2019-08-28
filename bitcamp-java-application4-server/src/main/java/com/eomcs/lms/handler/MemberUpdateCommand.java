package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.HashMap;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;

public class MemberUpdateCommand implements Command {
  private MemberDao memberDao;
  
  public MemberUpdateCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  @Override
  public void execute(BufferedReader in, PrintStream out) {
    try {
      int no = Input.getIntValue(in, out, "번호? ");
      
      Member member = memberDao.findBy(no);
      if (member == null) {
        out.println("해당 번호의 데이터가 없습니다!");
        return;
      }
      
      // 혹시 String뿐만아니라 int가 있을 경우hashMap
      // 사용자로부터 변경할 값을 입력 받는다.
//      HashMap<String,Object> data = new HashMap<>();
      Member data = new Member();
      // 사용자가 해당번호를 찾을수 있도록!
      data.setNo(no);
      String str = Input.getStringValue(in, out, "이름(" + member.getName() + ")? ");
      if (str.length() > 0) {
//        data.put("name", str);
        data.setName(str);
      }
      
      str = Input.getStringValue(in, out, "이메일(" + member.getEmail() + ")? ");
      if (str.length() > 0) {
//        data.put("email", str);
        data.setEmail(str);
      }
      
      str = Input.getStringValue(in, out, "암호? ");
      if (str.length() > 0) {
//        data.put("password", str);
        data.setPassword(str);
      }
      
      str = Input.getStringValue(in, out, "사진(" + member.getPhoto() + ")? ");
      if (str.length() > 0) {
//        data.put("photo", str);
        data.setPhoto(str);
      }
      
      str = Input.getStringValue(in, out, "전화(" + member.getTel() + ")? ");
      if (str.length() > 0) {
//        data.put("tel", str);
        data.setTel(str);
      }
      
      memberDao.update(data);
      out.println("데이터를 변경하였습니다.");

    } catch (Exception e) {
      out.println("데이터 변경에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }

}
