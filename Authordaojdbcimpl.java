package com.dlnu.Author.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dlnu.Author.dao.AuthorDao;
import com.dlnu.Author.vo.Author;
import com.icss.hr.common.DBUtil;

public class Authordaojdbcimpl implements AuthorDao {

	@Override
	public void insert(Author author) throws Exception {
		Connection conn = null;
		PreparedStatement pst = null;
		try{
		StringBuilder sb = new StringBuilder();
//		sb.append("INSERT INTO Author ");
//		sb.append("VALUES(Author_SEQ.NEXTVAL,?,?,?,?,?,?) ");
		sb.append("INSERT INTO Author(AUTHORID,AUTHORNAME,HIREDATE,AUTHORADDRESS,AUTHOREMAIL,AUTHORTELEPHONE,APASSWORD) ");
		sb.append("VALUES(?,?,?,?,?,?,?) ");
		conn = DBUtil.getConnection();
		pst = conn.prepareStatement(sb.toString());
		pst.setInt(1,author.getAuthorId());
		pst.setString(2,author.getAuthorName());

		pst.setDate(3,author.getHiredate());
		pst.setString(4,author.getAuthoraddress());
		pst.setString(5, author.getAuthoremail());
		pst.setString(6,author.getAuthortelephone());
		pst.setString(7,author.getApassword());
//		pst.setString(1,author.getAuthorName());
//		pst.setString(2,author.getAuthorsex());
//		pst.setDate(3,author.getHiredate());
//		pst.setString(4,author.getAuthoraddress());
//		pst.setString(5, author.getAuthoremail());
//		pst.setString(6,author.getAuthortelephone());
		pst.executeUpdate();
	}catch(Exception ex){
		ex.printStackTrace();
		throw ex;
	}finally{
		DBUtil.close(null, pst, conn);
		}
	}

	@Override
	public void update(Author author) throws Exception {
		Connection conn = null;
		PreparedStatement pst = null;
		try{
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE Author ");
		sb.append("SET AuthorName=?, ");
		sb.append("    Hiredate=?,  ");
		sb.append("    Authoraddress=?,  ");
		sb.append("    Authoremail=?,  ");
		sb.append("    Authortelephone=?  ");
		sb.append("  WHERE  Authorid = ? ");
		
		conn = DBUtil.getConnection();
		pst = conn.prepareStatement(sb.toString());
		
		pst.setString(1,author.getAuthorName());
		pst.setDate(2,author.getHiredate());
		pst.setString(3,author.getAuthoraddress());
		pst.setString(4, author.getAuthoremail());
		pst.setString(5,author.getAuthortelephone());
		pst.setInt(6, author.getAuthorId());
		pst.executeUpdate();
	}catch(Exception ex){
		ex.printStackTrace();
		throw ex;
	}finally{
		DBUtil.close(null, pst, conn);
		}
	}

	@Override
	public void delete(Author author) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pst = null;
		try{
			StringBuilder sb = new StringBuilder();
			sb.append("DELETE Author ");
			sb.append("WHERE AuthorId=?  ");
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sb.toString());
			
			pst.setInt(1,author.getAuthorId());
			
			pst.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			DBUtil.close(null, pst, conn);
		}
	}

	@Override
	public Author queryId(int AuthorId) throws Exception {
		// TODO Auto-generated method stub
		Author author = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try{
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT 	AUTHORID,  ");
			sb.append(" 	  	AUTHORNAME,  ");
			sb.append(" 		HIREDATE,  ");
			sb.append(" 		AUTHORADDRESS,  ");
			sb.append(" 		AUTHOREMAIL,  ");
			sb.append(" 		AUTHORTELEPHONE  ");
			sb.append("FROM Author  ");
			sb.append("WHERE AUTHORID=?  ");
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sb.toString());
			pst.setInt(1,AuthorId);
			rs = pst.executeQuery();
			
			if(rs.next()){
				int authorId = rs.getInt(1);
				String authorName = rs.getString(2);
				Date date = rs.getDate(3);
				String authoraddress = rs.getString(4);
				String authoremail = rs.getString(5);
				String authortelephone = rs.getString(6);
				
				author = new Author(authorId, authorName,  date, authoraddress, authoremail, authortelephone);
				
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return author;
	}

	@Override
	public List<Author> queryAll() throws Exception {
		// TODO Auto-generated method stub
		List<Author> list = new ArrayList<Author>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try{
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT 	AUTHORID,  ");
			sb.append(" 	  	AUTHORNAME,  ");
			sb.append(" 		HIREDATE,  ");
			sb.append(" 		AUTHORADDRESS,  ");
			sb.append(" 		AUTHOREMAIL,  ");
			sb.append(" 		AUTHORTELEPHONE,  ");
			sb.append(" 		ALEVEL  ");
			sb.append("FROM Author  ");
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement(sb.toString());
			
			rs = pst.executeQuery();
			
			while(rs.next()){
				int authorId = rs.getInt(1);
				String authorName = rs.getString(2);
				Date date = rs.getDate(3);
				String authoraddress = rs.getString(4);
				String authoremail = rs.getString(5);
				String authortelephone = rs.getString(6);
				int alevel = rs.getInt(7);
				Author author = new Author(authorId, authorName, alevel,  date, authoraddress, authoremail, authortelephone);
				
				list.add(author);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return list;
	}

	@Override
	public void updatepw(int id, String pw) throws Exception {
		Connection conn = null;
		PreparedStatement pst = null;
		try{
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE Author ");
		sb.append("SET APASSWORD=? ");
		sb.append("  WHERE  Authorid = ? ");
		
		conn = DBUtil.getConnection();
		pst = conn.prepareStatement(sb.toString());
		
		pst.setString(1,pw);
		pst.setInt(2,id);
	
		pst.executeUpdate();
	}catch(Exception ex){
		ex.printStackTrace();
		throw ex;
	}finally{
		DBUtil.close(null, pst, conn);
		}
		
	}

	@Override
	public void updatelv(int level,int id) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pst = null;
		try{
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE Author ");
		sb.append("SET ALEVEL=? ");

		sb.append("  WHERE  Authorid = ? ");
		
		conn = DBUtil.getConnection();
		pst = conn.prepareStatement(sb.toString());
		
		pst.setInt(1,level);
		pst.setInt(2,id);
		pst.executeUpdate();
	}catch(Exception ex){
		ex.printStackTrace();
		throw ex;
	}finally{
		DBUtil.close(null, pst, conn);
		}
	}

}
