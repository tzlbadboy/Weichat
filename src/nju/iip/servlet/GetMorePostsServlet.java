package nju.iip.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import nju.iip.dao.impl.PostDaoImpl;
import nju.iip.dto.Post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class GetMorePostsServlet
 */
public class GetMorePostsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ShowPostServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMorePostsServlet() {
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
        String count = request.getParameter("count");
        logger.info("count="+count);
        
        PostDaoImpl PDI = new PostDaoImpl();
        List<Post> post_list = PDI.getAllPostLimit(Integer.valueOf(count)*10);
        JSONObject json = new JSONObject();
        json.put("post", post_list);
        logger.info("json="+json.toString());
        PrintWriter out = response.getWriter();  
        out.print(json.toString());
	}

}
