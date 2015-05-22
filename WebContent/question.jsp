<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>在线量表评测 </title>

<link rel="stylesheet" type="text/css" href="css/style.css" />

<style type="text/css">
.demo{width:760px; margin:60px auto 10px auto}
</style>

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/quiz.js"></script>
<script type="text/javascript">
var init={'questions':[{'question':'jQuery是什么？','answers':['JavaScript库','CSS库','PHP框架','以上都不是'],'correctAnswer':1},{'question':'找出不同类的一项?','answers':['写字台','沙发','电视','桌布'],'correctAnswer':3},{'question':'国土面积最大的国家是：','answers':['美国','中国','俄罗斯','加拿大'],'correctAnswer':3},{'question':'月亮距离地球多远？','answers':['18万公里','38万公里','100万公里','180万公里'],'correctAnswer':2}]};

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

<div style="text-align:center;margin:10px 0; font:normal 14px/24px 'MicroSoft YaHei';">

</div>
</body>
</html>
