<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link type="text/css" rel="stylesheet" href="css/base.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<title>注册身份验证</title>
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</head>

<body>
<div class="wrap">
<form  action="AddUserServlet" methed="post">
	<div class="bgfff form ov">
		<div class="fb">请填写认证资料：</div>
         <ul class="cb">
               <li>
            	<div class="fl la_bg tc"><label for="name" class="lable">您的姓名</label></div>
                <div class="fl l_r"><input name="name" type="text" required id="name" class="w_img95 i_bor"></div>
               </li>
               
               <li>
            	<div class="fl la_bg tc"><label for="name" class="lable">病历号</label></div>
                <div class="fl l_r"><input name="cardId" type="text" id="name" required class="w_img95 i_bor"></div>
               </li>
               
               <li>
            	<div class="fl la_bg tc"><label for="name" class="lable">手机号码</label></div>
                <div class="fl l_r"><input name="phone" type="text" id="name" required class="w_img95 i_bor"></div>
               </li>
               <!--  
               <li>
            	<div class="fl check tc"><label for="name" class="lrcol">输入校证码</label></div>
                <div class="fl check_r"><input type="text" id="name" class="w_img95 i_bor"></div>
                <div class="fr"><span class="fr check_span" id="send">获 &nbsp;&nbsp;&nbsp;&nbsp;取<br>验证码</span></div>
               </li>
               -->
         </ul>
    </div>
    
    <div class="bgfff form ov">
		<div class="fb">隐私申明：</div>
        <p class="font_p pt15">请认真阅读以下协议，在提交信息之前，你必须接受此协议的条款。</p>
        <div class="xieyi">
        	<p>1、关于您的个人信息
本医院严格保护您个人信息的安全。我们使用各种安全技术和程序来保护您的个人信息不被未经授权的访问、使用或泄漏。本医院会在法律要求或符合本医院的相关服务条款、软件许可使用协议约定的情况下透露您的个人信息，或者有充分理由相信必须这样做才能： 
			<p>(a) 满足法律或行政法规的明文规定，或者符合本医院适用的法律程序；</p>
            
            <p>(b) 符合本医院相关服务条款、软件许可使用协议的约定；</p>

			<p>(c) 保护本医院的权利或财产，以及</p>
            
            <p>(d) 在紧急情况下保护本医院员工、本医院产品或服务的用户或大众的个人安全。</p>

        </div>
        

         <ul>
               <li class="sex">
               	<div class="fl ml30"><label for="radio">我接受</label> <input type="radio" id="tongyi" name="radio" ></div>
               	<div class="fr mr30"> <label for="radio1">不接受</label> <input type="radio" id="notongyi"  name="radio" ></div>
               </li>
 		</ul> 
        
        <div class="cb pt20"><input type="submit" id="tijiao" value="提交信息" disabled  class="nobut"></div>
        
    </div>
</form>  
<!-- 
<footer class="mt60">
<ul class="footer">
<li><a href="#" class="active">身份验证</a></li>
<li><a href="#">财务数据</a></li>
<li><a href="#">密码保护</a></li>
</ul>
</footer>
 -->
</div>
</body>
</html>
