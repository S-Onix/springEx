package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import exception.IdPasswordNotMatchingException;
import service.MemberLoginService;
import vo.AuthInfo;
import vo.LoginCommand;

@Controller
@RequestMapping("/login")
public class LoginController {
	private MemberLoginService memberLoginService;

	public void setMemberLoginService(MemberLoginService memberLoginService) {
		this.memberLoginService = memberLoginService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String form(LoginCommand loginCommand
			, @CookieValue(value = "REMEMBER", required = false) Cookie rememberCookie) {
		if(rememberCookie != null) {
			loginCommand.setEmail(rememberCookie.getValue());
			loginCommand.setRememberEmail(true);
		}
		return "login/loginForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String submit(
			LoginCommand loginCommand, Errors errors, HttpSession session, HttpServletResponse response
			) {
		if(errors.hasErrors()) {
			return "login/loginForm";
		}
		try {
			AuthInfo loginMember = memberLoginService.login(loginCommand.getEmail(), loginCommand.getPassword());
			session.setAttribute("authInfo", loginMember);
			//����� ��ü�� ������ �� �������� ���� ������ ���������� �ƴѴ�. �ʰ� ���� ����� key, value
			Cookie rememberCookie = new Cookie("REMEMBER", loginCommand.getEmail());
			rememberCookie.setPath("/");
			
			if(loginCommand.isRememberEmail()) {
				//�ð��� ����Ǳ� ������ ������ �����Ѵ�. �ð��� ����Ǹ� �������� ��Ű�� �������.
				//��� : ������ �ð���ŭ ��Ű�� �������� ������ ����
				//0 : �������� �����ִ� ���ȸ� ����
				//���� : �ٷ� ����
				rememberCookie.setMaxAge(60*60*24*30);
			}else {
				rememberCookie.setMaxAge(0);
			}
			//�������� ������ �����Ǵ� ����, ��Ű ���� ��ġ�� �� ���������� �ٸ�
			response.addCookie(rememberCookie);
			return "login/loginSuccess";
		}catch(IdPasswordNotMatchingException e) {
			return "login/loginForm";
		}
	}
}
