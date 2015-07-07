package nju.iip.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nju.iip.dao.impl.PostDaoImpl;
import nju.iip.dto.Love;
import nju.iip.dto.WeixinUser;
import nju.iip.util.CommonUtil;

/**
 * Servlet implementation class AddLikeServlet
 */
public class AddLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddLikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");  
	     response.setCharacterEncoding("UTF-8");  
	     String postId = request.getParameter("id");
	     PrintWriter out = response.getWriter();
	     WeixinUser user = (WeixinUser)request.getSession().getAttribute("snsUserInfo");
	     Love love = new Love();
	     love.setPostId(Integer.valueOf(postId));
	     love.setOpenId((String)request.getSession().getAttribute("openId"));
	     love.setHeadImgUrl(user.getHeadImgUrl());
	     love.setLoveTime(CommonUtil.getTime());
	     love.setAuthor(user.getNickname());
	     PostDaoImpl PDI = new PostDaoImpl();
	     if(PDI.addLike(love)) {
	    	 out.write("success!");
	     }
	     else {
	    	 out.write("failed!");
	     }
	     
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
