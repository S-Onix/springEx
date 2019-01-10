package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.MemberDao;
import vo.Member;

@Controller
public class MemberListController {
	private MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@RequestMapping("/member/list")
	public String list(Model model) {
		try {
			List<Member> members = memberDao.selectAll();
			model.addAttribute("members", members);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "member/memberList";
	}
}
