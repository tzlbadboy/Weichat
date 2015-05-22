package nju.iip.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nju.iip.dao.impl.UserDaoImpl;
import nju.iip.dto.WeixinUser;

/**
 * 用户注册
 * @author wangqiang
 *
 */
@WebServlet(name = "AddUserServlet", urlPatterns = { "/AddUserServlet" })
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
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
	     String name = request.getParameter("name");
	     String cardId = request.getParameter("cardId");
	     String phone = request.getParameter("phone");
	     String openId = request.getSession().getAttribute("openId").toString();
	     System.out.println("name="+name);
	     System.out.println("cardId="+cardId);
	     System.out.println("phone="+phone);
	     System.out.println("openId="+openId);
	     WeixinUser user = new WeixinUser();
	     user.setName(name);
	     user.setCardID(cardId);
	     user.setOpenId(openId);
	     user.setPhone(phone);
	     if(UserDaoImpl.addUser(user)){
	    	 request.getRequestDispatcher("registerOK.jsp").forward(request, response);
	     }
	     else {
	    	 request.getRequestDispatcher("register.jsp").forward(request, response);
	     }
		
	}

}
