package nju.iip.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nju.iip.dao.impl.PostDaoImpl;
import nju.iip.dto.Post;
import nju.iip.dto.WeixinUser;
import nju.iip.util.CommonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class ReceivePost
 */
public class ReceivePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory
			.getLogger(RecordAnswerServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReceivePostServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		WeixinUser user = (WeixinUser) request.getSession().getAttribute(
				"snsUserInfo");

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String picture = request.getParameter("picture");
		logger.info(request.getContextPath()+" "+request.getScheme() );
		logger.info("title=" + title + " content=" + content+" picture="+picture);
		String filePath=this.getServletConfig().getServletContext().getRealPath("/");
		logger.info("filePath="+filePath);
		Post post = new Post();
		if(picture.length()!=0) {
			String picPath = CommonUtil.savePicture(picture, user.getOpenId(),filePath);
			logger.info(picPath);
			post.setPictureUrl(picPath);
		}
		post.setHeadImgUrl(user.getHeadImgUrl());
		post.setTitle(title);
		post.setContent(content);
		post.setOpenId(user.getOpenId());
		post.setAuthor(user.getNickname());
		post.setReply(0);
		post.setLove(0);
		post.setPostTime(CommonUtil.getTime());
		PrintWriter out = response.getWriter();
		PostDaoImpl PDI = new PostDaoImpl();
		if (PDI.addPost(post)) {
			out.write("发帖成功");
		} else {
			out.write("发帖失败");
		}

		out.flush();
		out.close();

	}

}
