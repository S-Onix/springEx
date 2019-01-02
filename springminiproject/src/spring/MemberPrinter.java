package spring;

import java.util.ArrayList;
import java.util.Iterator;

import vo.Member;

public class MemberPrinter {
	public void print(Member member) {
		System.out.printf(
				"회원 정보 : 아이디 = %d, 이메일 = %s, 이름 = %s, 등록일 = %tF\n",
				member.getId(), member.getEmail(), member.getName(), member.getRegisterDate());
	}
	
	public void printAll(ArrayList<Member> members) {
		Iterator<Member> i = members.iterator();
		while(i.hasNext()) {
			Member member = i.next();
			System.out.printf(
					"회원 정보 : 아이디 = %d, 이메일 = %s, 이름 = %s, 등록일 = %tF\n",
					member.getId(), member.getEmail(), member.getName(), member.getRegisterDate());
		}
	}
}
