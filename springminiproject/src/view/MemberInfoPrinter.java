package view;

import org.springframework.beans.factory.annotation.Autowired;

import dao.MemberDao;
import spring.MemberPrinter;
import vo.Member;

public class MemberInfoPrinter {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MemberPrinter printer;
	
	//아래 구문 생략 가능
//	@Autowired
//	public void setMemberDao(MemberDao memberDao) {
//		this.memberDao = memberDao;
//	}
//	
//	@Autowired
//	public void setPrinter(MemberPrinter printer) {
//		this.printer = printer;
//	}
	
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
