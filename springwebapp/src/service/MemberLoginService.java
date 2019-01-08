package service;

import dao.MemberDao;
import exception.IdPasswordNotMatchingException;
import vo.AuthInfo;
import vo.Member;

public class MemberLoginService {
	private MemberDao memberDao;
	
	public MemberLoginService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public AuthInfo login(String email, String password) {
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			throw new IdPasswordNotMatchingException();
		}
		if(!member.matchPassword(password)) {
			throw new IdPasswordNotMatchingException();
		}
		
		return new AuthInfo(member.getId(), member.getEmail(), member.getName());
	}
}
