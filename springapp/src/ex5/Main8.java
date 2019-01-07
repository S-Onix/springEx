package ex5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import spring.ChangePasswordService;
import spring.IdPasswordNotMatchingException;
import spring.MemberNotFoundException;

public class Main8 {
	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext("classpath:appCtxDs.xml");
		ChangePasswordService cps = ctx.getBean("changePwdSvc", ChangePasswordService.class);
		
		try {
			cps.changePassword("test@email.com", "test", "test2");
			System.out.println("회원 데이터 변경 되었습니다.");
		}catch(MemberNotFoundException e) {
			System.out.println("회원 데이터가 존재하지 않습니다.");
		}catch(IdPasswordNotMatchingException e) {
			System.out.println("암호가 올바르지 않습니다.");
		}
		
	}
}
