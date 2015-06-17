package nju.iip.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nju.iip.dto.Post;
import nju.iip.util.DBConnection;

/**
 * 与发帖有关的数据库操作
 * 
 * @author wangqiang
 * 
 */
public class PostDaoImpl {

	static Connection conn = null;
	static Statement sm = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	
	/**
	 * 添加一个帖子
	 * @param post
	 * @return
	 */
	public static boolean addPost(Post post) {
		String sql = "insert into weixin_post(title,content,postTime,author,reply,openId) values(?,?,?,?,?,?)";
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, post.getTitle());
			ps.setString(2, post.getContent());
			ps.setString(3, post.getPostTime());
			ps.setString(4, post.getAuthor());
			ps.setInt(5,post.getReply());
			ps.setString(6, post.getOpenId());
			return ps.executeUpdate() == 1 ? true : false;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeDB();
		}
	}
	
	/**
	 * 获得所有帖子
	 * @return
	 */
	public static List<Post> getAllPost() {
		List<Post> post_list = new ArrayList<Post>();
		String sql = "select * from weixin_post order by id";
		try {
			conn = DBConnection.getConn();
			sm=conn.createStatement();
			rs=sm.executeQuery(sql);
			while(rs.next()) {
				Post post = new Post();
				post.setAuthor(rs.getString("author"));
				post.setContent(rs.getString("content"));
				post.setId(rs.getInt("id"));
				post.setPostTime(rs.getString("postTime"));
				post.setTitle(rs.getString("title"));
				post.setReply(rs.getInt("reply"));
				post_list.add(post);
			}
		}catch(Exception e){
			e.printStackTrace();
			}
		finally {
			closeDB();
		}
		
		return post_list;
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
		List<Post> post_list = getAllPost();
		for(Post post:post_list) {
			System.out.println(post.getId());
		}
		
	}

}
