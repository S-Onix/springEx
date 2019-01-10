package controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vo.AnsweredData;
import vo.Question;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	@RequestMapping(method = RequestMethod.GET)
	public String form(Model model) {
		List<Question> questions = createQuestions();
		model.addAttribute("questions", questions);
		return "survey/surveyForm";
	}
	
	public List<Question> createQuestions(){
		Question q1 = new Question("����� ������ �����Դϱ�?", Arrays.asList("����", "����Ʈ", "Ǯ����"));
		Question q2 = new Question("���� ����ϴ� ���ߵ����� �����Դϱ�?", Arrays.asList("��Ŭ����", "���ڸ�J", "�������"));
		Question q3 = new Question("�ϰ� ���� ���� �����ּ���.");
		
		return Arrays.asList(q1,q2,q3);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String submit(@ModelAttribute("ansData") AnsweredData data) {
		return "survey/submitted";
	}
}