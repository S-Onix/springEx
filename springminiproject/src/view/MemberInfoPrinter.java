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
			System.out.println("������ ����");
			return;
			//throw new MemberNotFoundException(); ���ܸ� �߻����ѵ� ��
		}
		printer.print(member);
		System.out.println();
	}
	
}
