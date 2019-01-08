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
			//쿠기의 객체가 생성될 뿐 실질적인 물리 파일이 생성되지는 아닌다. 맵과 같이 저장됨 key, value
			Cookie rememberCookie = new Cookie("REMEMBER", loginCommand.getEmail());
			rememberCookie.setPath("/");
			
			if(loginCommand.isRememberEmail()) {
				//시간이 만료되기 전까지 정보를 유지한다. 시간이 만료되면 물리적인 쿠키가 사라진다.
				//양수 : 설정된 시간만큼 쿠키의 물리적인 파일이 존재
				//0 : 브라우저가 켜져있는 동안만 유지
				//음수 : 바로 삭제
				rememberCookie.setMaxAge(60*60*24*30);
			}else {
				rememberCookie.setMaxAge(0);
			}
			//물리적인 파일이 생성되는 시점, 쿠키 저장 위치는 각 브라우저마다 다름
			response.addCookie(rememberCookie);
			return "login/loginSuccess";
		}catch(IdPasswordNotMatchingException e) {
			return "login/loginForm";
		}
	}
}
