package controller;

import java.io.IOException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.MemberDao;
import exception.MemberNotFoundException;
import vo.Member;

@Controller
public class MemberDetailController {
	private MemberDao memberDao;
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	@RequestMapping("/member/detail/{id}")
	public String detail(@PathVariable("id") Long memId, Model model) {
		Member member = null;
		try {
			member = memberDao.selectById(memId);
		}catch(Exception e) {
			return "member/memberList";
		}		
		if (member == null) {
			throw new MemberNotFoundException();
		}
		model.addAttribute("member", member);
		return "member/memberDetail";
	}
	@ExceptionHandler(TypeMismatchException.class)
	public String handleTypeMismatchException(TypeMismatchException ex) {
		return "member/invalidId";
	}	
	@ExceptionHandler(MemberNotFoundException.class)
	public String handleNotFoundException() throws IOException {
		return "member/noMember";
	}
}
