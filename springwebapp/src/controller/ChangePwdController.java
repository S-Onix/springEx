package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.ChangePasswordService;
import vo.ChangePwdCommand;

@Controller
@RequestMapping("/edit/changePassword")
public class ChangePwdController {
	private ChangePasswordService changePasswordService;
	
	public void setChangePasswordService(ChangePasswordService changePasswordService){
		this.changePasswordService = changePasswordService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String form(@ModelAttribute("command") ChangePwdCommand pwdCmd) {
		return "edit/changePwdForm";
	}
}
