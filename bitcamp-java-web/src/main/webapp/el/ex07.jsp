<%@page import="java.util.HashMap"%>
<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL</title>
</head>
<body>
<h1>EL - Map 객체에서 값 꺼내기</h1>
<%
HashMap<String,Object> map = new HashMap<>();
map.put("s01", "김구");
map.put("s02", "안중근");
map.put("s03", "윤봉길");

pageContext.setAttribute("map", map);
%>

${map["s01"]}<br>
${map['s01']}<br>
${map.s01}<br>
<%--
key 문자열에 공백이나 특수 문자가 포함된 경우에는 점(.)을 사용할 수 없다.
 ${map.04 ^^}<br>
 --%>
 ${"map.04 ^^""} <br>
 ${'map.04 ^^'} <br>
</body>
</html>












