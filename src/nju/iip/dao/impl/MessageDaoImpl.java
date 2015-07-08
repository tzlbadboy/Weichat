package nju.iip.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nju.iip.dto.Message;
import nju.iip.util.DBConnection;

/**
 * 私信消息处理相关数据库操作
 * @author wangqiang
 *
 */
public class MessageDaoImpl {
	 private static final Logger logger = LoggerFactory.getLogger(MessageDaoImpl.class);
	 private Connection conn  = null;
	 private Statement sm = null;
	 private ResultSet rs = null;
	 private PreparedStatement ps = null;
	 
	 public boolean addMessage(Message message) {
		 String sql = "insert into weixin_message(Content,fromOpenId,toOpenId,fromNickname,toNickname,fromHeadImgUrl,isRead) values(?,?,?,?,?,?,?)";
		 try {
			 conn =DBConnection.getConn();
			 ps = conn.prepareStatement(sql);
			 ps.setString(1, message.getContent());
			 ps.setString(2, message.getFromOpenId());
			 ps.setString(3, message.getToOpenId());
			 ps.setString(4, message.getFromNickname());
			 ps.setString(5, message.getToNickname());
			 ps.setString(6, message.getFromHeadImgUrl());
			 ps.setInt(7, message.getIsRead());
			 return ps.executeUpdate() == 1 ? true : false;
		 }catch (SQLException e) {
			    logger.error("MessageDaoImpl-------->",e);
				return false;
			} finally {
				closeDB();
			}
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

}
