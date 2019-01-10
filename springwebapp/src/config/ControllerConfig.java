package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import controller.ChangePwdController;
import controller.LoginController;
import controller.LogoutController;
import controller.MemberDetailController;
import controller.MemberListController;
import controller.RegisterController;
import controller.SurveyController;
import dao.MemberDao;
import service.ChangePasswordService;
import service.MemberLoginService;
import service.MemberRegisterService;

@Configuration
public class ControllerConfig implements WebMvcConfigurer{
	@Autowired
	private MemberRegisterService memberRegSvc;
	@Autowired
	private ChangePasswordService changePwdSvc;
	@Autowired
	private MemberLoginService authService;
	@Autowired
	private MemberDao memberDao;
	
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addViewController("/main").setViewName("main");
	}
	
	@Bean
	public RegisterController registerController() {
		RegisterController regCtrl = new RegisterController();
		regCtrl.setMemberRegisterService(memberRegSvc);
		return regCtrl;
	}
	
	@Bean
	public SurveyController surveyController() {
		return new SurveyController();
	}
	
	@Bean
	public LoginController loginController() {
		LoginController loginCtrl = new LoginController();
		loginCtrl.setMemberLoginService(authService);
		return loginCtrl;
	}
	
	@Bean
	public LogoutController logoutController() {
		return new LogoutController();
	}
	
	@Bean
	public ChangePwdController changePwdController() {
		ChangePwdController chgPwdContr = new ChangePwdController();
		chgPwdContr.setChangePasswordService(changePwdSvc);
		return chgPwdContr;
	}
	
	@Bean
	public MemberListController memberListController() {
		MemberListController memListCtrl = new MemberListController();
		memListCtrl.setMemberDao(memberDao);
		return memListCtrl;
	}
	
	@Bean
	public MemberDetailController memberDetailController() {
		MemberDetailController memDetailCtrl = new MemberDetailController();
		memDetailCtrl.setMemberDao(memberDao);
		return memDetailCtrl;
	}
	
	
	
}
