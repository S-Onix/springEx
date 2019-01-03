package view;

import java.util.Collection;

import dao.MemberDao;
import spring.MemberPrinter;
import vo.Member;

public class MemberListPrinter {
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	public MemberListPrinter() {
		
	}
	
	public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
		this.memberDao = memberDao;
		this.printer = printer;
	}
	
	
//	public void printAllMemberInfo() {
//		Collection<Member> members = memberDao.selectAll();
//		if(members == null) {
//			System.out.println("������ ����");
//			return;
//			//throw new MemberNotFoundException(); ���ܸ� �߻����ѵ� ��
//		}
//		printer.printAll(members);
//		System.out.println();
//	}
	
	public void printAllMemberInfo() {
		Collection<Member> members = memberDao.selectAll();
		if(members == null) {
			System.out.println("������ ����");
			return;
			//throw new MemberNotFoundException(); ���ܸ� �߻����ѵ� ��
		}
		for(Member m : members) {
			printer.print(m);
		}
		System.out.println();
	}
}
