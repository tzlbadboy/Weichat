package nju.iip.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nju.iip.dao.impl.PostDaoImpl;
import nju.iip.dto.Post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class ShowPostServlet
 */
public class ShowPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 private static final Logger logger = LoggerFactory.getLogger(ShowPostServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postId = request.getParameter("id");
		logger.info("postId="+postId);
		PostDaoImpl PDI = new PostDaoImpl();
		Post post = PDI.getPostById(Integer.valueOf(postId));
		request.setAttribute("post", post);
		request.getSession().setAttribute("postId", postId);
		request.getRequestDispatcher("post.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
