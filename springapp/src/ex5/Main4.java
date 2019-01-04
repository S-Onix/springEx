package ex5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.ConfigPart1;
import config.ConfigPart2;
import spring.MemberInfoPrinter;
import spring.MemberRegisterService;
import spring.RegisterRequest;

public class Main4 {
	public static void main(String[] args) {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigPart1.class, ConfigPart2.class);
		MemberRegisterService regSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class);
		MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter.class);
		RegisterRequest regReq = new RegisterRequest();

		regReq.setEmail("co@co.net");
		regReq.setName("ȫ�浿");
		regReq.setPassword("1234");
		regReq.setConfirmPassword("1234");
		regSvc.regist(regReq);
		infoPrinter.printMemberInfo("co@co.net");
	}
}
