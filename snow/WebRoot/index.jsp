<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  <h3>提交至默认方法</h3>
  <form action="lyw/lyw.a" method="post">
  	<input type="submit" value="defaultMethod">
  </form>
  
  <h3>提交至指定方法,post需要name和方法名一致，get需要加上方法名参数</h3>
  <form action="lyw/lyw.a" method="post">
  	<input name="targetMethod" type="submit" value="post">
  </form>
  <a href="lyw/lyw.a?target1">get</a>
  
  <h3>上传文件</h3>
  <form action="lyw/lyw.a" method="post" enctype="multipart/form-data">
  	<input type="file" name="upload_file"/>
  	<input name="upload" type="submit" value="upload">
  </form>
  
  <h3>下载文件</h3>
  <form action="lyw/lyw.a" method="post">
  	<input name="download" type="submit" value="download">
  </form>
  
  <h3>上传参数</h3>
  <form action="lyw/lyw.a" method="post">
 	<input name="a" value="1"/>
 	<input name="b" value="2"/>
 	<input name="c" value="3"/>
 	<input name="d" value="4"/>
 	<input name="e" value="5"/>
 	<input name="f" value="6"/>
 	<input name="bean_param.g" value="7"/>
  	<input name="params" type="submit" value="params">
  </form>
  
  <form action="lyw/lyw.a" method="post" enctype="multipart/form-data">
 	<input name="a" value="1"/>
 	<input name="b" value="2"/>
 	<input name="c" value="3"/>
 	<input name="d" value="4"/>
 	<input name="e" value="5"/>
 	<input name="f" value="6"/>
 	<input name="bean_param.g" value="7"/>
  	<input name="params" type="submit" value="params multipart">
  </form>
  
  <h3>返回参数</h3>
  <form action="lyw/lyw.a" method="post">
 	<input name="a" value="1"/>
 	<input name="bean_param.g" value="7"/>
  	<input name="returnJsp" type="submit" value="returnJsp">
  </form>
  </body>
</html>
