<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link type="text/css" rel="stylesheet" href="css/base.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<script src="js/common.js"></script>
<title>请选择一张量表</title>
</head>
<body>
<div class="wrap">
     <div class="bgfff form ov">
     <div class="fb">请选择一张量表：</div>
         <ul class="cb">
               <li>
               <form action="GetScaleServlet" method="GET">
                <input name="totalScaleId" value="117" type="hidden" />
            	<div class="cb pt20"><input  type="submit"  value="量表一"   class="but2" /></div>
               </form>
               </li>
               <li>
               <form action="GetScaleServlet" method="GET">
                <input name="totalScaleId" value="2" type="hidden" />
            	<div class="cb pt20"><input  type="submit"  value="量表二"   class="but2"></div>
               </form>
               </li>
               
               <li>
               <form action="GetScaleServlet" method="GET" >
                <input name="totalScaleId" value="3" type="hidden" />
            	<div class="cb pt20"><input  type="submit"  value="量表三"   class="but2"/></div>
               </form>
               </li>
               
          </ul>
     </div>
</div>
</body>
</html>