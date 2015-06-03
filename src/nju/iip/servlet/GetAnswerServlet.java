package nju.iip.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nju.iip.dto.Scale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 记录用户所填量表
 * @author wangqiang
 * @since 2015-6-3
 *
 */
public class GetAnswerServlet extends HttpServlet {
	
	 private static final Logger logger = LoggerFactory.getLogger(OAuthServlet.class);
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAnswerServlet() {
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
		    response.setContentType("text/html");
		    String openId = (String) request.getSession().getAttribute("openId");
		    logger.info("openId="+openId);
		    Scale scale = (Scale)request.getSession().getAttribute("scale");
		    logger.info("scaleId="+scale.getId());
	        PrintWriter out = response.getWriter();
	        String answers=request.getParameter("answers");
	        logger.info("answers="+answers);
	        out.print("success");
	        out.flush();
	        out.close();
	}

}
