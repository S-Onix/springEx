package spring;

import java.util.Collection;
import java.util.Iterator;

import vo.Member;

public class MemberPrinter {
	public void print(Member member) {
		System.out.printf(
				"ȸ�� ���� : ���̵� = %d, �̸��� = %s, �̸� = %s, ����� = %tF\n",
				member.getId(), member.getEmail(), member.getName(), member.getRegisterDate());
	}
	
//	public void printAll(Collection<Member> members) {
//		Iterator<Member> i = members.iterator();
//		while(i.hasNext()) {
//			Member member = i.next();
//			System.out.printf(
//					"ȸ�� ���� : ���̵� = %d, �̸��� = %s, �̸� = %s, ����� = %tF\n",
//					member.getId(), member.getEmail(), member.getName(), member.getRegisterDate());
//		}
//	}
}
