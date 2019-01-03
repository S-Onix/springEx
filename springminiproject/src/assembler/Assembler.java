package assembler;

import dao.MemberDao;
import service.ChangePasswordService;
import service.MemberRegisterService;
import spring.MemberPrinter;
import view.MemberInfoPrinter;
import view.MemberListPrinter;

public class Assembler {
	private MemberDao memberDao;
	private MemberRegisterService regSvc;
	private ChangePasswordService pwdSvc;
	private MemberPrinter memberPrinter;
	private MemberInfoPrinter memberInfoPrint;
	private MemberListPrinter memberListPrint;
	
	public Assembler() {
		memberDao = new MemberDao();
		memberPrinter = new MemberPrinter();
		regSvc = new MemberRegisterService(memberDao);
		pwdSvc = new ChangePasswordService(memberDao);
		memberInfoPrint = new MemberInfoPrinter(memberDao, memberPrinter);
		memberListPrint = new MemberListPrinter(memberDao, memberPrinter);
	}
	
	public MemberDao getMemberDao() {
		return memberDao;
	}

	public MemberRegisterService getMemberRegisterService() {
		return regSvc;
	}

	public ChangePasswordService getChangePasswordService() {
		return pwdSvc;
	}

	public MemberPrinter getMemberPrinter() {
		return memberPrinter;
	}
	
	public MemberInfoPrinter getMemberInfoPrint() {
		return memberInfoPrint;
	}

	public MemberListPrinter getMemberListPrint() {
		return memberListPrint;
	}	
	
	
}
