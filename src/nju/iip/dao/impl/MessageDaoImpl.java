package nju.iip.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	 
	 //将新的私信消息插入数据库
	 public boolean addMessage(Message message) {
		 String sql = "insert into weixin_message(content,fromOpenId,toOpenId,fromNickname,toNickname,fromHeadImgUrl,isRead,sendTime) values(?,?,?,?,?,?,?,?)";
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
			 ps.setString(8, message.getSendTime());
			 return ps.executeUpdate() == 1 ? true : false;
		 }catch (SQLException e) {
			    logger.error("MessageDaoImpl-------->",e);
				return false;
			} finally {
				closeDB();
			}
	 }
	 
	 //读取用户的私信消息
	 public List<Message> getMessage(String openId) {
		 List<Message> messages = new ArrayList<Message>();
		 String sql = "select * from weixin_message where toOpenId='"+openId+"' order by id desc";
		 try {
			 conn = DBConnection.getConn();
			 sm=conn.createStatement();
			 rs=sm.executeQuery(sql);
			 while(rs.next()) {
				 Message message = new Message();
				 message.setContent(rs.getString("content"));
				 message.setFromHeadImgUrl(rs.getString("fromHeadImgUrl"));
				 message.setFromNickname(rs.getString("fromNickname"));
				 message.setSendTime(rs.getString("sendTime"));
				 message.setIsRead(rs.getInt("isRead"));
				 message.setId(rs.getInt("id"));
				 message.setFromOpenId(rs.getString("fromOpenId"));
				 messages.add(message);
			 }
		 }catch(Exception e){
				logger.error("MessageDaoImpl-getMessage---->",e);
				}
			finally {
				closeDB();
			}
		 return messages;
	 }
	 
	 /**
	  * 标记私信消息已读过
	  * @return
	  */
	 public boolean updateIsRead(int id) {
		 String sql = "update weixin_message set isRead=1 where id='"+id+"'";
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
			MessageDaoImpl MDI = new MessageDaoImpl();
			System.out.println(MDI.getMessage("om8TAt9-24UvwsTBRhK48pPMrcBg").get(0).getFromHeadImgUrl());
		}

}
