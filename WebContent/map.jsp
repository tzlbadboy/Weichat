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
<script type="text/javascript"
	src="http://api.map.baidu.com/api?type=quick&ak=M8xS2NSEYa4amEnURlA8icFg&v=1.0"></script>
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<title>查看附近的人</title>

<%
	String str = (String) request.getAttribute("location_json");
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
						init.location[0].Latitude), 14);
				map.addControl(new BMap.ZoomControl()); //添加地图缩放控件

				var Point = [], marker = [], infoWindow = [];

				for (i = 0; i < init.location.length; i++) {
					Point[i] = new BMap.Point(init.location[i].Longitude,
							init.location[i].Latitude);
					marker[i] = new BMap.Marker(Point[i]);
					map.addOverlay(marker[i]);
					infoWindow[i] = new BMap.InfoWindow("普通标注");
					marker[i].addEventListener("click", function() {
						this.openInfoWindow(infoWindow[i]);
					});
				}
			});
</script>
</head>
<body>
	<div id="allmap"></div>
</body>
</html>

