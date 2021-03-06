package view;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import dao.MemberDao;
import spring.MemberPrinter;
import vo.Member;

public class MemberListPrinter {
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	public MemberListPrinter() {
		
	}
	
	@Autowired
	public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
		this.memberDao = memberDao;
		this.printer = printer;
	}
	
	
//	public void printAllMemberInfo() {
//		Collection<Member> members = memberDao.selectAll();
//		if(members == null) {
//			System.out.println("데이터 없음");
//			return;
//			//throw new MemberNotFoundException(); 예외를 발생시켜도 됨
//		}
//		printer.printAll(members);
//		System.out.println();
//	}
	
	public void printAllMemberInfo() {
		Collection<Member> members = memberDao.selectAll();
		if(members == null) {
			System.out.println("데이터 없음");
			return;
			//throw new MemberNotFoundException(); 예외를 발생시켜도 됨
		}
		for(Member m : members) {
			printer.print(m);
		}
		System.out.println();
	}
}
