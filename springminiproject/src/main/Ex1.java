package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import service.MemberRegisterService;
import view.MemberInfoPrinter;
import vo.RegisterRequest;

public class Ex1 {
	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext("classpath:appCtx1.xml");
		MemberRegisterService regSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class);
		MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter.class);
		RegisterRequest regReq = new RegisterRequest();
		regReq.setEmail("co@co.com");
		regReq.setName("ȫ�浿");
		regReq.setPassword("1234");
		regReq.setConfirmPassword("1234");
		regSvc.regist(regReq);
		infoPrinter.printMemberInfo("co@co.com");
		
		
	}
}
