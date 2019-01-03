package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import db.DBAction;
import vo.Member;

public class MemberDao {
	private static long nextId = 0;
	private Map<String, Member> map = new HashMap<>();
	
	public Member selectByEmail(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;
		try {
			String sql = "select * from springuser where email = ?";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new Member();
				member.setId(rs.getLong("id"));
				member.setEmail(rs.getString("email"));
				member.setName(rs.getString("name"));
				member.setPassword(rs.getString("password"));
				member.setRegisterDate(rs.getDate("registerDate"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return member;
	}
	
	public void insert(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "insert into springuser(email, password, name) values (?,?,?)";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(Member member) {
//		map.put(member.getEmail(), member);
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "update springuser set password = ? where email = ?";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getEmail());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//맵의 모든 정보 가져오기
//	public ArrayList<Member> selectAll(){
//		ArrayList<Member> members = new ArrayList<>();
//		
//		Iterator<String> i = map.keySet().iterator();
//		while(i.hasNext()) {
//			String key = i.next();
//			members.add(map.get(key));
//		}
//		
//		return members;
//	}
	
	public Collection<Member> selectAll(){
		Collection <Member> members = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from springuser";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member member = new Member();
				member.setId(rs.getLong("id"));
				member.setEmail(rs.getString("email"));
				member.setName(rs.getString("name"));
				member.setPassword(rs.getString("password"));
				member.setRegisterDate(rs.getDate("registerDate"));
				members.add(member);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return members;
	}
}
