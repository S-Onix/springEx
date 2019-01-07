package dao;

import javax.sql.DataSource;

public class MemberDao {
	DataSource dataSource;
	
	public MemberDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	// 연습문제
	
	public void selectByEmail(String email) {
		
	}
}
