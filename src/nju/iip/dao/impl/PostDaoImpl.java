package nju.iip.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nju.iip.dto.Comment;
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
		String sql = "insert into weixin_post(title,content,postTime,author,reply,openId,headImgUrl) values(?,?,?,?,?,?,?)";
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, post.getTitle());
			ps.setString(2, post.getContent());
			ps.setString(3, post.getPostTime());
			ps.setString(4, post.getAuthor());
			ps.setInt(5,post.getReply());
			ps.setString(6, post.getOpenId());
			ps.setString(7, post.getHeadImgUrl());
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
		String sql = "select * from weixin_post order by id desc";
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
				post.setLove(rs.getInt("love"));
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
	 * 根据帖子id取出帖子
	 * @param id
	 * @return
	 */
	public static Post getPostById(int id) {
		Post post =new Post();
		String sql = "select * from weixin_post where id='"+id+"'";
		try {
			conn = DBConnection.getConn();
			sm=conn.createStatement();
			rs=sm.executeQuery(sql);
			if(rs.next()) {
				post.setId(id);
				post.setAuthor(rs.getString("author"));
				post.setContent(rs.getString("content"));
				post.setTitle(rs.getString("title"));
				post.setHeadImgUrl(rs.getString("headImgUrl"));
				post.setPostTime(rs.getString("postTime"));
				post.setReply(rs.getInt("reply"));
				post.setLove(rs.getInt("love"));
			}
		}catch(Exception e){
			e.printStackTrace();
			}
		finally {
			closeDB();
		}
		return post;
	}
	
	
	/**
	 * 增加一条评论
	 * @param comment
	 * @return
	 */
	public static boolean addComment(Comment comment) {
		String sql = "insert into weixin_comment(postId,comment,commentTime,author,openId,headImgUrl) values(?,?,?,?,?,?)";
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, comment.getPostId());
			ps.setString(2, comment.getComment_content());
			ps.setString(3, comment.getCommentTime());
			ps.setString(4, comment.getAuthor());
			ps.setString(5,comment.getOpenId());
			ps.setString(6, comment.getHeadImgUrl());
			return ps.executeUpdate() == 1 ? true : false;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeDB();
		}
	}
	
	/**
	 * 评论数+1操作
	 * @return
	 */
	public static boolean addReplyNum(int postId) {
		String sql = "update weixin_post set reply=reply+1 where id='"+postId+"'";
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
	 * 增加一个点赞数
	 * @param postId
	 * @return
	 */
	public static boolean addLike(int postId) {
		String sql = "update weixin_post set love=love+1 where id='"+postId+"'";
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
	 * 取得帖子的所有评论
	 * @param postId
	 * @return
	 */
	public static List<Comment> getAllComment(int postId) {
		List<Comment> comment_list = new ArrayList<Comment>(); 
		String sql = "select * from weixin_comment where postId='"+postId+"' order by id";
		try {
			conn = DBConnection.getConn();
			sm=conn.createStatement();
			rs=sm.executeQuery(sql);
			while(rs.next()) {
				Comment comment = new Comment();
				comment.setAuthor(rs.getString("author"));
				comment.setComment_content(rs.getString("comment"));
				comment.setCommentTime(rs.getString("commentTime"));
				comment.setHeadImgUrl(rs.getString("headImgUrl"));
				comment_list.add(comment);
			}
		}catch(Exception e){
			e.printStackTrace();
			}
		finally {
			closeDB();
		}
		return comment_list;
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
		List<Comment> comment_list = getAllComment(1);
			System.out.println(comment_list.size());
		
	}

}
