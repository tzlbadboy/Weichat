package nju.iip.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import nju.iip.dao.impl.LocationDaoImpl;
import nju.iip.dao.impl.UserDaoImpl;
import nju.iip.dto.WeixinOauth2Token;
import nju.iip.dto.WeixinUser;
import nju.iip.util.AdvancedUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 授权后的回调请求处理
 * 
 * @author mrpod2g
 * @date 2013-11-12
 */
public class OAuthServlet extends HttpServlet {
	 private static final Logger logger = LoggerFactory.getLogger(OAuthServlet.class);
	
	private static final long serialVersionUID = -1847238807216447030L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		// 用户同意授权后，能获取到code
		String code = request.getParameter("code");
		
		logger.info("code="+code);
		
		
		// 用户同意授权后，能获取到state
		String state = request.getParameter("state");
				
		logger.info("state="+state);
		
		boolean flag = false;

		// 用户同意授权
		if (!"authdeny".equals(code)) {
			// 获取网页授权access_token
			WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken("wxb4bb093136ffd648", "0a513930a9e98380ec1b338f5cd47390", code);
			// 网页授权接口访问凭证
			String accessToken = weixinOauth2Token.getAccessToken();
			// 用户标识
			String openId = weixinOauth2Token.getOpenId();
			
			
			UserDaoImpl UDI = new UserDaoImpl();
			
			//检查用户是否已绑定
			flag = UDI.checkBind(openId);
			
			// 获取用户信息
			WeixinUser snsUserInfo = AdvancedUtil.getWeixinUserInfo(accessToken, openId);
			
			WeixinUser user = UDI.getUser(openId);
			
			snsUserInfo.setName(user.getName());
			snsUserInfo.setCardID(user.getCardID());
			snsUserInfo.setPhone(user.getPhone());

			// 设置要传递的参数
			request.getSession().setAttribute("snsUserInfo", snsUserInfo);
			request.getSession().setAttribute("openId", openId);
			logger.info("openId="+openId);
		}
		
		if(flag) {
			
			if(state.equals("weixin")) {
				// 跳转到index.jsp
				request.getRequestDispatcher("MessageBox.jsp").forward(request, response);
			}
			
			else if(state.equals("liangbiao")){
				request.getRequestDispatcher("GetScaleListServlet").forward(request, response);
			}
			
			else if(state.equals("jilu")) {
				request.getRequestDispatcher("ScaleRecordServlet").forward(request, response);
			}
			
			else if(state.equals("nearby")) {
				LocationDaoImpl LD = new LocationDaoImpl();
				UserDaoImpl UDI = new UserDaoImpl();
				List<Map<String, String>> location_list = LD.getAllUserLocation();
				for(Map<String, String> map:location_list) {
					String openId = map.get("openId");
					WeixinUser user = UDI.getUserInfo(openId);
					map.put("nickname", user.getNickname());
					map.put("headImgUrl", user.getHeadImgUrl());
				}
				JSONObject json = new JSONObject();
				json.put("location", location_list);
				logger.info(json.toString());
				request.setAttribute("location_json",json.toString());
				request.getRequestDispatcher("map.jsp").forward(request, response);
			}
			
			else if(state.equals("bbs")) {
				request.getRequestDispatcher("post_list.jsp").forward(request, response);
			}
			
			else {
				request.getRequestDispatcher("sorry.jsp").forward(request, response);
			}
			
		}
		
		else {
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		
	}
}
