package view;

import dao.MemberDao;
import spring.MemberPrinter;
import vo.Member;

public class MemberInfoPrinter {
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	
	public void printMemberInfo(String email) {
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			System.out.println("데이터 없음");
			return;
			//throw new MemberNotFoundException(); 예외를 발생시켜도 됨
		}
		printer.print(member);
		System.out.println();
	}
	
}
