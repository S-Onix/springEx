package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;


public class MemberDao {
	private static long nextId = 0;
	private Map<String, Member> map = new HashMap<>();
	
	
	private DataSource dataSource;
	
	@Autowired
	public void MemberDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	

	public Member selectByEmail(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;
		try {
			String sql = "select * from springuser where email = ?";
			conn = dataSource.getConnection();
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
		member.setId(++nextId);
		map.put(member.getEmail(), member);
	}

	public void update(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "update springuser set password = ? where email = ?";
			conn = dataSource.getConnection();
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

	// selectAll()
	public Collection<Member> selectAll() {
		return map.values();
	}
}