<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body,html,#allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
}

#golist {
	display: none;
}

@media ( max-device-width : 780px) {
	#golist {
		display: block !important;
	}
}
</style>
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="css/base.css">
<link rel="stylesheet" href="css/common.css">
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?type=quick&ak=M8xS2NSEYa4amEnURlA8icFg&v=1.0"></script>
<title>查看附近的人</title>

<%
	String str = (String) request.getAttribute("location_json");
    String openId = (String)request.getSession().getAttribute("openId");
%>


<script type="text/javascript">
	$(document).ready(
			function() {
				var init = 
<%=str%>
	;
				// 百度地图API功能
				var map = new BMap.Map("allmap");
				map.centerAndZoom(new BMap.Point(init.location[0].Longitude,
						init.location[0].Latitude), 11);
				map.addControl(new BMap.ZoomControl()); //添加地图缩放控件

				//var Point = [], marker = [], infoWindow = [];
				//var i = 0;

				for (i = 0; i < init.location.length; i++) {
					var headImgUrl = init.location[i].headImgUrl;
					var nickname = init.location[i].nickname;
					var openId = init.location[i].openId;
					if(nickname == null) continue;
					 (function (x) { 
					var Point = new BMap.Point(init.location[i].Longitude,
							init.location[i].Latitude);
					var marker = new BMap.Marker(Point);
					var opts = {
							width : 200,    // 信息窗口宽度
							height: 65,     // 信息窗口高度
							title : '<img id="'+openId+'" alt="求真相" class="img-circle" style="width:30%;" src="'+headImgUrl+'"><font id="name">'+nickname+'</font>' , // 信息窗口标题
							enableAutoPan : true //自动平移
						}
					var infoWindow = new BMap.InfoWindow("",opts);
					marker.addEventListener("click", function() {
						map.openInfoWindow(infoWindow,Point);
					});
					map.addOverlay(marker);
					 })(i);
				}
				
				
				var ToOpenId,ToNickname;
				
				//模态框发送按钮响应函数 
				$("font#name").click(function() {
					var userOpenId = "<%=openId%>";
					ToOpenId =  $(this).attr("id");
					alert(haha);
					if(ToOpenId!=userOpenId) {
						$("#myModal").modal("show");
						ToNickname = $(this).children("font").text();
						$("h4.modal-title").children("font").html(ToNickname);
					}
					
				});
				
			});
</script>
</head>
<body>
	<div id="allmap"></div>
	
	
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">发送消息给:<font color="#337ab7"></font></h4>
				</div>
				<div class="modal-body">

					<div class="form-group" id="form-content">
						<label for="message-text" class="control-label">内容:</label>
						<textarea class="form-control" id="message-text" name="message"></textarea>
					</div>
                    
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal"
							value="取消"> <input type="button" class="btn btn-primary"
							id="send" value="确认">
					</div>
				</div>

			</div>
		</div>
	</div>
	<!-- end -->
	
	<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content" id="show_reslut"
				style="text-align: center; margin: 5px auto 5px auto">
				<br>
				<span class="return_msg"></span><span class="glyphicon glyphicon-ok"></span><br>
				<br>
			</div>
		</div>
	</div>
	
</body>
</html>

