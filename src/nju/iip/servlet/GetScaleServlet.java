package nju.iip.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import nju.iip.dao.impl.ScaleDaoImpl;
import nju.iip.dto.Questions;
import nju.iip.dto.Scale;

/**
 * 
 * @author wangqiang
 *
 */
public class GetScaleServlet extends HttpServlet {
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
		System.out.println("totalScaleId="+totalScaleId);
		String jsonStr = "";
		Scale scale = ScaleDaoImpl.getScale(Integer.valueOf(totalScaleId));	
	    List<Questions> list = ScaleDaoImpl.getQuestions(ScaleDaoImpl.getQuestionId(Integer.valueOf(totalScaleId)));
	    JSONObject json = new JSONObject();
	    try {
			json.put("questions", list);
			json.put("scale", new JSONObject(scale));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    jsonStr = json.toString();
	    System.out.println("jsonStr="+jsonStr);
		request.setAttribute("questions", jsonStr);
		String openId = (String) request.getSession().getAttribute("openId");
	    System.out.println("openId="+openId);
		request.getRequestDispatcher("scale.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
