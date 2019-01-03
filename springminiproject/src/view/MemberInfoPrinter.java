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
	
	//�Ʒ� ���� ���� ����
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
			System.out.println("������ ����");
			return;
			//throw new MemberNotFoundException(); ���ܸ� �߻����ѵ� ��
		}
		printer.print(member);
		System.out.println();
	}
	
}
