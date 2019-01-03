package service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import dao.MemberDao;
import miniexception.AlreadyExistingMemberException;
import vo.Member;
import vo.RegisterRequest;

public class MemberRegisterService {
	//@Aputowired를 위에 작성해도 됨 생성자 생략가능(DI의 개념을 모르면 해석을 못함)
	private MemberDao memberDao;

	@Autowired
	public MemberRegisterService(MemberDao memberDao) {
		// TODO Auto-generated constructor stub
		this.memberDao = memberDao;
	}

	public void regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		if (member != null) {
			throw new AlreadyExistingMemberException("dup email" + req.getEmail());
		}
		Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), new Date());
		memberDao.insert(newMember);
	}
}
