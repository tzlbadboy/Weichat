package nju.iip.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nju.iip.dao.impl.MessageDaoImpl;
import nju.iip.dto.Message;
import nju.iip.dto.WeixinUser;
import nju.iip.util.CommonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 私信消息处理
 * @author wangqiang
 * @date 2015-7-8
 *
 */
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private static final Logger logger = LoggerFactory.getLogger(OAuthServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String sendToOpenId = request.getParameter("sendToOpenId");
		String message = request.getParameter("message");
		String ToNickname = request.getParameter("ToNickname");
		logger.info("sendToOpenId="+sendToOpenId+" message="+message+" ToNickname="+ToNickname);
		
		WeixinUser user = (WeixinUser)request.getSession().getAttribute("snsUserInfo");
		Message msg = new Message();
		msg.setContent(message);
		msg.setToNickname(ToNickname);
		msg.setFromNickname(user.getNickname());
		msg.setFromOpenId(user.getOpenId());
		msg.setFromHeadImgUrl(user.getHeadImgUrl());
		msg.setIsRead(0);
		msg.setSendTime(CommonUtil.getTime());
		
		PrintWriter out = response.getWriter();
		MessageDaoImpl MDI = new MessageDaoImpl();
		if(MDI.addMessage(msg)) {
			out.write("发送消息成功!");
		}
		else {
			out.write("发送消息失败!");
		}
		
		
		
	}

}
