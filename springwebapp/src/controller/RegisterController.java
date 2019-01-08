package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import exception.AlreadyExistingMemberException;
import service.MemberRegisterService;
import vo.RegisterRequest;

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
	
	@RequestMapping(value="/register/step2", method=RequestMethod.POST)
	public String handleStep2(
			@RequestParam(value="agree", defaultValue="false") Boolean agree, Model model) {
		if(!agree) {
			return "register/step1";
		}
		model.addAttribute("registerRequest", new RegisterRequest());
		return "register/step2";
	}
	
	@RequestMapping(value="/register/step2", method = RequestMethod.GET)
	public String handleStep2Get() {
		return "redirect:step1";
	}
	
	@RequestMapping(value="/register/step3", method = RequestMethod.POST)
	public String handleStep3(RegisterRequest regReq) {
		try {
			memberRegisterService.regist(regReq);
			return "register/step3";
		}catch(AlreadyExistingMemberException ex) {
			return "register/step2";
		}
	}
}
