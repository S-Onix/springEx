package service;

import dao.MemberDao;

public class MemberRegisterService {
	private MemberDao memberDao;
	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
}
