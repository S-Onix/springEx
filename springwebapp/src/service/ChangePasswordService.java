package service;

import dao.MemberDao;

public class ChangePasswordService {
	private MemberDao memberDao;
	public ChangePasswordService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
}
