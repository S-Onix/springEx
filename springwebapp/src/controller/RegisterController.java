package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import service.MemberRegisterService;

@Controller
public class RegisterController {
	//의존성을 줄 클래스 객체를 정의
	private MemberRegisterService memberRegisterService;

	public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}
	
	//1. 사용자 요청을 처리할 기능 정의
	@RequestMapping("/register/step1")
	public String handleStep1() {
		return "register/step1";
	}
}
