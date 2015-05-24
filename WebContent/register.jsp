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
<form  action="RegisterServlet" method="post">
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
          <div class="cb pt20"><input type="submit" id="tijiao" value="提交信息"   class="but"></div>
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
