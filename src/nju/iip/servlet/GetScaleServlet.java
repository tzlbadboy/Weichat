package nju.iip.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nju.iip.dao.impl.ScaleDaoImpl;
import nju.iip.dto.Questions;
import nju.iip.dto.Scale;

/**
 * 根据totalScaleId取出所有题目
 * @author wangqiang
 *
 */
public class GetScaleServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(GetScaleServlet.class);
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetScaleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String totalScaleId = request.getParameter("totalScaleId");
		logger.info("totalScaleId="+totalScaleId);
		String jsonStr = "";
		ScaleDaoImpl SDI = new ScaleDaoImpl();
		Scale scale = SDI.getScale(Integer.valueOf(totalScaleId));	
	    List<Questions> list = SDI.getQuestions(SDI.getQuestionId(Integer.valueOf(totalScaleId)));
	    JSONObject json = new JSONObject();
	    try {
			json.put("questions", list);
			json.put("scale", new JSONObject(scale));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    jsonStr = json.toString();//量表对应所有题目的json字符串
	    logger.info("jsonStr="+jsonStr);
		request.setAttribute("questions", jsonStr);
		request.getSession().setAttribute("scale", scale);
		String openId = (String) request.getSession().getAttribute("openId");
	    logger.info("openId="+openId);
		request.getRequestDispatcher("scale.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
