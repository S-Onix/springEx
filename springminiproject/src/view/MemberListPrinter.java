package view;

import java.util.ArrayList;

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
	
	
	public void printAllMemberInfo() {
		ArrayList<Member> members = memberDao.selectAll();
		if(members == null) {
			System.out.println("������ ����");
			return;
			//throw new MemberNotFoundException(); ���ܸ� �߻����ѵ� ��
		}
		printer.printAll(members);
		System.out.println();
	}
}
