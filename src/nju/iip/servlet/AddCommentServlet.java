package nju.iip.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nju.iip.dao.impl.PostDaoImpl;
import nju.iip.dto.Comment;
import nju.iip.dto.WeixinUser;
import nju.iip.util.CommonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class AddCommentServlet
 */
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(AddCommentServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  
	    response.setCharacterEncoding("UTF-8");  
		String comment_content = request.getParameter("comment");
		String postId = (String)request.getSession().getAttribute("postId");
		WeixinUser user = (WeixinUser)request.getSession().getAttribute("snsUserInfo");
		logger.info("comment="+comment_content+ "   postId="+postId);
		
		Comment comment = new Comment();
		comment.setComment_content(comment_content);
		comment.setAuthor(user.getNickname());
		comment.setHeadImgUrl(user.getHeadImgUrl());
		comment.setOpenId(user.getOpenId());
		comment.setPostId(Integer.valueOf(postId));
		comment.setCommentTime(CommonUtil.getTime());
		
		PrintWriter out = response.getWriter();
		if(PostDaoImpl.addComment(comment)) {
			out.write("success!");
		}
		else {
			out.write("failed!");
		}
		
	}

}
