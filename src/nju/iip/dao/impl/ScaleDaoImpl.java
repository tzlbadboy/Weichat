package nju.iip.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nju.iip.dto.Options;
import nju.iip.dto.Questions;
import nju.iip.dto.Scale;
import nju.iip.dto.ScaleRecord;
import nju.iip.util.DBConnection;


/**
 * 与量表有关的数据库操作
 * @author wangqiang
 * @since 2015-6-2
 */
public class ScaleDaoImpl {
	
	 private Connection conn  = null;
	 private Statement sm = null;
	 private ResultSet rs = null;
	 private PreparedStatement ps = null;
	
	
	/**
	 * 根据totalScaleId 从parkinsonscale表中取出所有题目的questionId
	 * @param totalScaleId
	 * @return
	 */
	public  List<Integer> getQuestionId(int totalScaleId) {
		List<Integer> questionId_list = new ArrayList<Integer>();
		conn = DBConnection.getConn();
		String sql = "select * from parkinsonscale where totalScaleId='"+totalScaleId+"'";
		try {
			sm=conn.createStatement();
			rs=sm.executeQuery(sql);
			while(rs.next()) {
				int questionId = rs.getInt("questionId");//取出questionId
				questionId_list.add(questionId);
			}
		}catch(Exception e){
			e.printStackTrace();
			}
		finally {
			closeDB();
		}
		return questionId_list;
	}
	
	
	
	
	
	
	
	
	/**
	 * 根据questionId_list取出所有对应题目和选项
	 * @param questionId_list
	 * @return List<Questions> question_list
	 */
	public  List<Questions> getQuestions(List<Integer> questionId_list) {
		List<Questions> question_list = new ArrayList<Questions>();
		conn = DBConnection.getConn();
		try {
			for(Integer questionId:questionId_list) {
				Questions question = new Questions();
				String sql = "select * from scalequestion where id='"+questionId+"'";
				sm=conn.createStatement();
				rs=sm.executeQuery(sql);
				//取出题目的内容
				if(rs.next()) {
					String showType = rs.getString("showType");
					int indexParent = rs.getInt("indexParent");
					if(rs.getString("showType").equals("cut")||(showType.equals("matrix")&&indexParent==0)) continue;
					question.setQuestionContent(rs.getString("questionContent"));
					question.setShowType(showType);
					
				}
				List<Options> options = new ArrayList<Options>();
				sql = "select * from questionoption where questionId='"+questionId+"'";
				sm=conn.createStatement();
				rs=sm.executeQuery(sql);
				//取出题目对应的选项
				while(rs.next()) {
					Options option = new Options();
					option.setOptionContent(rs.getString("optionContent"));
					option.setOptionValue(rs.getString("optionValue"));
					options.add(option);
				}
				question.setAnswers(options);
				question.setQuestionId(questionId);
				question_list.add(question);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			closeDB();
		}
		return question_list;
	}
	
	/**
	 * 根据totalScaleId 从parkinsontotalscale表中取出对应量表的信息
	 * @param totalScaleId
	 * @return scale
	 */
	public  Scale getScale(int totalScaleId) {
		Scale scale = new Scale();
		conn = DBConnection.getConn();
		String sql = "select * from parkinsontotalscale where id='"+totalScaleId+"'";
		try {
			sm=conn.createStatement();
			rs=sm.executeQuery(sql);
			if(rs.next()) {
				scale.setScaleName(rs.getString("scaleName"));
				scale.setScaleDescription(rs.getString("scaleDescription"));
				scale.setShortname(rs.getString("shortname"));
				scale.setId(totalScaleId+"");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			closeDB();
		}
		return scale;
	}
	
	
	/**
	 * 将用户量表填写结果存入数据库表
	 * @param openId
	 * @param scale
	 * @param score
	 * @return
	 */
	public  boolean storeResult(String openId,Scale scale,String score) {
		Date now = new Date();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );//可以方便地修改日期格式
    	String time = dateFormat.format(now);
		String sql = "insert into weixin_scaleresult(openId,scaleId,scaleName,score,time) values(?,?,?,?,?)";
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, openId);
			ps.setString(2, scale.getId());
			ps.setString(3, scale.getScaleName());
			ps.setString(4, score);
			ps.setString(5, time);
			return ps.executeUpdate() == 1 ? true : false;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeDB();
		}
	}
	
	/**
	 * 根据openId取出用户填表信息
	 * @param openId
	 * @return
	 */
	public  List<ScaleRecord> getScaleRecord(String openId) {
		List<ScaleRecord> record_listList = new ArrayList<ScaleRecord>();
		String sql = "select * from weixin_scaleresult where openId='"+openId+"'";
		try {
			conn = DBConnection.getConn();
			sm=conn.createStatement();
			rs=sm.executeQuery(sql);
			while(rs.next()) {
				ScaleRecord record = new ScaleRecord();
				record.setScaleName(rs.getString("scaleName"));
				record.setScore(rs.getString("score"));
				record.setTime(rs.getString("time"));
				record_listList.add(record);
			}
		}catch(Exception e){
			e.printStackTrace();
			}
		finally {
			closeDB();
		}
		return record_listList;
	}
	
	/**
	 * 取出所有量表
	 * @return
	 */
	public  List<Scale> getScaleList() {
		List<Scale> ScaleList = new ArrayList<Scale>();
		String sql = "select * from parkinsontotalscale";
		try {
			conn = DBConnection.getConn();
			sm=conn.createStatement();
			rs=sm.executeQuery(sql);
			while(rs.next()) {
				Scale scale = new Scale();
				scale.setScaleName(rs.getString("scaleName"));
				scale.setId(rs.getString("id"));
				scale.setScaleDescription(rs.getString("scaleDescription"));
				scale.setShortname(rs.getString("shortname"));
				ScaleList.add(scale);
			}
		}catch(Exception e){
			e.printStackTrace();
			}
		finally {
			closeDB();
		}
		return ScaleList;
	}
	
	
	/**
	 * 关闭数据库
	 */
	public  void closeDB() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (sm != null) {
			try {
				sm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
	}

}
