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
			System.out.println("ȸ�� ������ ���� �Ǿ����ϴ�.");
		}catch(MemberNotFoundException e) {
			System.out.println("ȸ�� �����Ͱ� �������� �ʽ��ϴ�.");
		}catch(IdPasswordNotMatchingException e) {
			System.out.println("��ȣ�� �ùٸ��� �ʽ��ϴ�.");
		}
		
	}
}
