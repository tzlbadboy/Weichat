<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title></title>

<link rel="stylesheet" type="text/css" href="css/style.css" />

<style type="text/css">
.demo{width:100%; margin:10px auto 10px auto}
</style>

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/quiz.js"></script>
<link rel="stylesheet" type="text/css" href="css/base.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
  <% 
		String str = (String)request.getAttribute("questions"); 
  %>
<script type="text/javascript">
var init = <%=str%>;
if(init!=null) {
$("title").html(init.scale.scaleName); 
}
$(function(){
	$('#quiz-container').jquizzy(init);
});
</script>

<script type="text/javascript">
var s=0
function startTime()
{
$("#now_time").html(s+"秒");
s = s+1
t=setTimeout('startTime()',1000);
}
</script>


</head>
<body onload="startTime()">
  
<div class="demo">
	<div id='quiz-container'></div>
</div>

<div class="fix_footer" id="fix_footer" style="left: 0px; color: rgb(255, 255, 255); height: 55px; width: 100%; position: fixed; bottom: 0%; font-size: 14px; display: block; background-color: rgb(51, 55, 57);">
<table width="100%" height="100%" border="0" style="border-collapse: collapse;">
<tbody><tr>
<td id="fix_footer_menu" align="center"><span id="now_an"></span><br>
已答题目</td>
<td id="fix_footer_menu" align="center"><span id="all_an"></span><br>
题目总数</td>
<td id="fix_footer_menu" align="center"><span id="now_time" data-src="21"></span><br>
答题用时</td>
</tr>
</tbody></table>
</div>


</body>
</html>
