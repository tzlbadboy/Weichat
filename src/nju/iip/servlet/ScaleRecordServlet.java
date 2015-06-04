package nju.iip.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nju.iip.dao.impl.ScaleDaoImpl;
import nju.iip.dto.ScaleRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 返回用户已填量表信息
 * @author wangqiang
 *
 */
public class ScaleRecordServlet extends HttpServlet {
	 private static final Logger logger = LoggerFactory.getLogger(ScaleRecordServlet.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScaleRecordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("doGet");
		String openId = (String) request.getSession().getAttribute("openId");
		List<ScaleRecord> record_listList = ScaleDaoImpl.getScaleRecord(openId);
		request.setAttribute("record_listList", record_listList);
		request.getRequestDispatcher("resultscale.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	

}
