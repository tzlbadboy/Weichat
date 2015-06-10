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

import nju.iip.dto.WeixinUser;
import nju.iip.util.DBConnection;


/**
 * 与用户有关的数据库操作
 * @author wangqiang
 *
 */
public class UserDaoImpl {
	
	 static Connection conn  = null;
	 static Statement sm = null;
	 static ResultSet rs = null;
	 static PreparedStatement ps = null;
	
	/**
	 * 通过openId判断用户是否绑定微信
	 * @param openId
	 * @return
	 */
	public static boolean checkBind(String openId) {
		 boolean flag=false;
		 String sql = "select * from weixin where openId='"+openId+"'";
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
    	String sql = "insert into weixin(openId,cardID,name,phone) values(?,?,?,?)";
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
	
	/**
	 * 根据openId获得用户的信息
	 * @param openId
	 * @return
	 */
	public static WeixinUser getUser(String openId) {
		WeixinUser user = new WeixinUser();
		String sql = "select * from weixin where openId='"+openId+"'";
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
	 * 根据openId删除用户即解绑
	 * @param openId
	 * @return
	 */
	public static boolean deleteUser(String openId) {
		conn =DBConnection.getConn();
    	ps = null;
    	String sql = "delete from weixin where openId=?";
    	try{
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, openId);
    		return ps.executeUpdate() == 1 ? true : false;
    	}catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeDB();
		}
	}
	
	/**
	 * 判断用户位置是否被记录过
	 * @param openId
	 * @return
	 */
	public static boolean isLocated(String openId) {
		 String sql = "select * from weixin_location where openId='"+openId+"'";
		 try{
			 conn = DBConnection.getConn(); 
			 sm=conn.createStatement();
			 rs=sm.executeQuery(sql);
			 return rs.next() == true;
		 }catch(Exception e){
				e.printStackTrace();
				return false;
		 }
		finally {
			closeDB();
		}
	}
	
	public static boolean updateUserLocation(String Latitude,String Longitude,String openId) {
		Date now = new Date();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );//可以方便地修改日期格式
    	String time = dateFormat.format(now);
		conn =DBConnection.getConn();
		String sql="update weixin_location set Latitude='"+Latitude+"',Longitude='"+Longitude+"',time='"+time+"' where openId='"+openId+"'";
		try {
			conn = DBConnection.getConn(); 
			ps = conn.prepareStatement(sql);
			return ps.executeUpdate() == 1 ? true : false;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeDB();
		}
	}
	
	
	
	/**
	 * 记录用户位置信息
	 * @param Latitude (纬度)
	 * @param Longitude (经度)
	 * @param openId
	 * @return
	 */
	public static boolean locationUser(String Latitude,String Longitude,String openId) {
		Date now = new Date();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );//可以方便地修改日期格式
    	String time = dateFormat.format(now);
		conn =DBConnection.getConn();
    	ps = null;
    	String sql = "insert into weixin_location(Latitude,Longitude,openId,time) values(?,?,?,?)";
    	try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Latitude);
			ps.setString(2, Longitude);
			ps.setString(3,openId);
			ps.setString(4, time);
			return ps.executeUpdate() == 1 ? true : false;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeDB();
		}
	}
	
	public static List<WeixinUser> getAllUserLocation() {
		List<WeixinUser> user_list = new ArrayList<WeixinUser>();
		String sql = "select * from weixin_location";
		try{
			conn = DBConnection.getConn(); 
			sm=conn.createStatement();
			rs=sm.executeQuery(sql);
		    while(rs.next()){
		    	WeixinUser user = new WeixinUser();
				String Latitude = rs.getString("Latitude");
    			String Longitude = rs.getString("Longitude");
    			String openId = rs.getString("openId");
    			user.setLatitude(Latitude);
    			user.setLongitude(Longitude);
    		    user.setOpenId(openId);
    		    user_list.add(user);
			}
		}catch(Exception e){
			e.printStackTrace();
			}
		finally {
			closeDB();
		}
		return user_list;
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
	
	public static void main(String[] args) {
		System.out.println(updateUserLocation("a","b","om8TAtye3lkQ1PQMDzEI9UlMRKgo"));
	}
	
}
