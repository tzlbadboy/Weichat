package nju.iip.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import nju.iip.dto.Questions;
import nju.iip.util.CommonUtil;

/**
 * Servlet implementation class GetScaleServlet
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
		String id = request.getParameter("id");
		System.out.println("id="+id);
		String jsonStr = "";
		if(id.equals("1")) {
			Questions ques = new Questions();
			ques.setQuestion("1.焦虑心境：担心、担忧，感到有最坏的事情将要发生，容易激惹");
			String[] answer={"无症状","轻","中等","严重"};
			List<String> answers = Arrays.asList(answer);
			ques.setAnswers(answers);
			ques.setCorrectAnswer(1);
			
			Questions ques2 = new Questions();
			ques2.setQuestion("1.焦虑心境：担心、担忧，感到有最坏的事情将要发生，容易激惹");
			ques2.setAnswers(answers);
			ques2.setCorrectAnswer(1);
			
			Questions ques3 = new Questions();
			ques3.setQuestion("1.焦虑心境：担心、担忧，感到有最坏的事情将要发生，容易激惹");
			ques3.setAnswers(answers);
			ques3.setCorrectAnswer(1);
			
			Questions ques4 = new Questions();
			ques4.setQuestion("1.焦虑心境：担心、担忧，感到有最坏的事情将要发生，容易激惹");
			ques4.setAnswers(answers);
			ques4.setCorrectAnswer(1);
			
	    	
	    	List<Questions> list = new ArrayList<Questions>();
	    	list.add(ques);
	    	list.add(ques2);
	    	list.add(ques3);
	    	list.add(ques4);
	    	JSONObject json = new JSONObject();
	    	try {
				json.put("questions", list);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	jsonStr = json.toString();
	       
		}
		request.setAttribute("questions", jsonStr);
		request.getRequestDispatcher("scale.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
