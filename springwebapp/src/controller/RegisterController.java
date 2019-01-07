package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import service.MemberRegisterService;

@Controller
public class RegisterController {
	//�������� �� Ŭ���� ��ü�� ����
	private MemberRegisterService memberRegisterService;

	public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}
	
	//1. ����� ��û�� ó���� ��� ����
	@RequestMapping("/register/step1")
	public String handleStep1() {
		return "register/step1";
	}
}
