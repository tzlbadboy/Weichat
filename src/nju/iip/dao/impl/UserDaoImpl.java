package nju.iip.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import nju.iip.dto.WeixinUser;
import nju.iip.util.DBConnection;


/**
 * 
 * @author wangqiang
 *
 */
public class UserDaoImpl {
	
	 static Connection conn  = null;
	 static Statement sm = null;
	 static ResultSet rs = null;
	 static PreparedStatement ps = null;
	
	/**
	 * 通过openid判断用户是否绑定微信
	 * @param openid
	 * @return
	 */
	public static boolean checkBind(String openid) {
		 boolean flag=false;
		 String sql = "select * from weixin where openid='"+openid+"'";
		 try{
			 conn = DBConnection.getConn(); 
			 sm=conn.createStatement();
			 rs=sm.executeQuery(sql);
			 flag=rs.next();
		 }catch(Exception e){
				e.printStackTrace();
				}
			finally {
				closeDB();
			}
		 return flag;
	}
	
	/**
	 * 增加用户
	 * @param user
	 * @return
	 */
	public static boolean addUser(WeixinUser user) {
		conn =DBConnection.getConn();
    	ps = null;
    	String sql = "insert into weixin(openid,cardID,name,phone) values(?,?,?,?)";
    	try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getOpenId());
			ps.setString(2, user.getCardID());
			ps.setString(3, user.getName());
			ps.setString(4, user.getPhone());
			return ps.executeUpdate() == 1 ? true : false;
		}catch (SQLException e) {

			e.printStackTrace();
			return false;
		} finally {
			closeDB();
		}
	}
	
	
	public static WeixinUser getUser(String openid) {
		WeixinUser user = new WeixinUser();
		String sql = "select * from weixin where openid='"+openid+"'";
		try{
			conn = DBConnection.getConn(); 
			sm=conn.createStatement();
			rs=sm.executeQuery(sql);
			if(rs.next()){
				String name = rs.getString("name");
    			String cardID = rs.getString("cardID");
    			String phone = rs.getString("phone");
    			user.setName(name);
    			user.setCardID(cardID);
    		    user.setPhone(phone);
			}
		}catch(Exception e){
			e.printStackTrace();
			}
		finally {
			closeDB();
		}
		return user;
	}
	
	
	/**
	 * 根据openid删除用户即解绑
	 * @param openid
	 * @return
	 */
	public static boolean deleteUser(String openid) {
		conn =DBConnection.getConn();
    	ps = null;
    	String sql = "delete from weixin where openid=?";
    	try{
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, openid);
    		return ps.executeUpdate() == 1 ? true : false;
    	}catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeDB();
		}
	}
	
	/**
	 * 关闭数据库
	 */
	public static void closeDB() {
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
		if (conn != null) {
			try {
				conn.close();
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
	}
	
}
