<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>量表评测</title>

<link rel="stylesheet" type="text/css" href="css/style.css" />

<style type="text/css">
.demo{width:100%; margin:10px auto 10px auto}
</style>

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/quiz.js"></script>
<link type="text/css" rel="stylesheet" href="css/base.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
  <% 
		String str = (String)request.getAttribute("questions"); 
  %>
<script type="text/javascript">
var init = <%=str%>;
$(function(){
	$('#quiz-container').jquizzy({
        questions: init.questions
    });
});
</script>
</head>
<body>
  
<div class="demo">
	<div id='quiz-container'></div>
</div>

</body>
</html>
