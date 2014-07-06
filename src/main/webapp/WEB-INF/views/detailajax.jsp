<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring3 Demo</title>
<script type="text/javascript">
var httpRequest = null;

function getXMLHttpRequest()
{
 if(window.ActiveXObject)
 {
  try
  {
   return new ActiveXObject("Msxml2.XMLHTTP");
  }
  catch(e)
  {
   try
   {
    return new ActiveXObject("Microsoft.XMLHTTP");
   }
   catch(e)
   {
    return null;
   }
  }
 }
 else if(window.XMLHTTPRequest)
 {
   return new XMLHTTPRequest();
 }
 else
 {
  return null;
 }
} 

function getDetail()
{
 var xmlHttp = getXMLHttpRequest();
 
 xmlHttp.open("GET", "../detail/ajax/${id}", false);
 xmlHttp.send(null);
 
 if(xmlHttp.status == 200)
 {
  var list = eval("(" + xmlHttp.responseText + ")");
  
  if(list[0] != null)
  {
   var board = list[0]; 
   
   document.getElementById("lblSubject").innerText = board.subject;
   document.getElementById("lblWriter").innerText = board.writer;
   document.getElementById("lblWriteTime").innerText = board.writeTime;
   document.getElementById("lblCount").innerText = board.count;
   document.getElementById("lblDetail").innerText = board.detail;
  }
 }
}
</script>
</head>
<body onload="javas-ript:getDetail();">
	<table border="1">
		<tr>
			<td>제목</td>
			<td><span id="lblSubject"></span></td>
		</tr>
		<tr>
			<td>글쓴이</td>
			<td><span id="lblWriter"></span></td>
		</tr>
		<tr>
			<td>글쓴시간</td>
			<td><span id="lblWriteTime"></span></td>
		</tr>
		<tr>
			<td>조회수</td>
			<td><span id="lblCount"></span></td>
		</tr>
		<tr>
			<td colspan="2"><span id="lblDetail"></span></td>
		</tr>
		<tr>
			<td colspan="2"><input type="button" value="수정"
				onclick="javas-ript:document.location.href='../update/${id}'">
				<input type="button" value="목록"
				onclick="javas-ript:document.location.href='../list'"></td>
		</tr>
	</table>
</body>
</html>