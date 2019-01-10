package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

	public List<Member> selectAll() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> results = new ArrayList<Member>();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("select * from MEMBERS order by mno desc");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Member member = new Member(rs.getString("EMAIL"), rs.getString("PWD"), rs.getString("MNAME"),
						rs.getDate("CRE_DATE"));
				member.setId(rs.getLong("MNO"));
				results.add(member);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}

		return results;
	}

	public Member selectById(Long no) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Member member = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT MNO,EMAIL,PWD,MNAME,CRE_DATE FROM MEMBERS" + " WHERE MNO=" + no);
			if (rs.next()) {
				member = new Member(rs.getString("EMAIL"), rs.getString("PWD"), rs.getString("MNAME"),
						rs.getDate("CRE_DATE"));
				member.setId(rs.getLong("MNO"));
				return member;
			} else {
//	    	  return member;
				throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
	}

}
