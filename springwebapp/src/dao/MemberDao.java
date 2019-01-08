package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import vo.Member;

public class MemberDao {
	DataSource dataSource;

	public MemberDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// 연습문제
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
			if (rs.next()) {
				member = new Member();
				member.setId(rs.getLong("id"));
				member.setEmail(rs.getString("email"));
				member.setName(rs.getString("name"));
				member.setPassword(rs.getString("password"));
				member.setRegisterDate(rs.getDate("registerDate"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return member;
	}

	public int insert(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			String sql = "insert into springuser(email, password, name) values (?,?,?)";
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public int update(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("update springuser set password = ? where email = ?");
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getEmail());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return -1;
	}

	
}
