package view;

import java.util.ArrayList;

import dao.MemberDao;
import spring.MemberPrinter;
import vo.Member;

public class MemberListPrinter {
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	
	public void printAllMemberInfo(String email) {
		ArrayList<Member> members = memberDao.selectAll();
		if(members == null) {
			System.out.println("데이터 없음");
			return;
			//throw new MemberNotFoundException(); 예외를 발생시켜도 됨
		}
		printer.printAll(members);
		System.out.println();
	}
}
