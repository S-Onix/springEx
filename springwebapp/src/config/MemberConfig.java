package config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import dao.MemberDao;
import service.ChangePasswordService;
import service.MemberLoginService;
import service.MemberRegisterService;

@Configuration
public class MemberConfig {
	@Bean
	public DataSource dataSource() {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		try {
			ds.setDriverClass("com.mysql.jdbc.Driver");
		}catch(PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		ds.setJdbcUrl(URL);
		ds.setUser(ID);
		ds.setPassword(PASSWORD);
		return ds;
	}
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao());
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		return new ChangePasswordService(memberDao());
	}
	
	@Bean
	public MemberLoginService authService(){
		MemberLoginService authSvc =new MemberLoginService(memberDao());
		return authSvc;
		
	}
}
