package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import assembler.Assembler;
import miniexception.AlreadyExistingMemberException;
import miniexception.IdPasswordNotMatchingException;
import miniexception.MemberNotFoundException;
import service.ChangePasswordService;
import service.MemberRegisterService;
import view.MemberInfoPrinter;
import view.MemberListPrinter;
import vo.RegisterRequest;

public class MainForAssembler {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		MainForAssembler main = new MainForAssembler();
		while (true) {
			System.out.println("명령어를 입력하세요.");
			String command = reader.readLine();
			
			System.out.println(command);
			
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			
			if (command.startsWith("new ")) { // 입력방식 : "new email name pw repw"
				processNewCommand(command.split(" "));
				continue;
			}else if(command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			}else if(command.startsWith("print ")) {
				showUserCommand(command.split(" "));
				continue;
			}else if(command.equals("printAll")) {
				showAllUserCommand();
				continue;
			}
		
			printHelp();
		}
	}

	private static Assembler assembler = new Assembler();
	
	public static void processNewCommand(String[] arg) {
		if(arg.length != 5) {
			printHelp();
			return;
		}
		MemberRegisterService regSvc = assembler.getMemberRegisterService();
		RegisterRequest req = new RegisterRequest();
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		
		if(!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호와 확인이 일치하지 않습니다.\n");
			return;
		}
		
		try {
			regSvc.regist(req);
			System.out.println("등록했습니다.");
		}catch(AlreadyExistingMemberException e) {
			System.out.println("이미 존재하는 이메일입니다.\n");
		}
	}
	
	public static void processChangeCommand(String [] arg) {
		if(arg.length != 4) {
			printHelp();
			return;
		}
		ChangePasswordService changePwdSvc = assembler.getChangePasswordService();
		try {
			changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("암호를 변경했습니다.");
		}catch(MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일입니다.");
		}catch(IdPasswordNotMatchingException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다.");
		}
		
	}
	
	public static void showUserCommand(String [] arg) {
		if(arg.length != 2) {
			printHelp();
			return;
		}
		MemberInfoPrinter print = assembler.getMemberInfoPrint();
		try {
			print.printMemberInfo(arg[1]);
		}catch(MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일입니다.");
		}
	}
	
	public static void showAllUserCommand() {
		MemberListPrinter printAll = assembler.getMemberListPrint();
		printAll.printAllMemberInfo();
	}
	
	public static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
		System.out.println("명령어 사용법 : ");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change 이메일 현재비번 변경비번");
		System.out.println("print 이메일");
		System.out.println("printAll");
		System.out.println();
		
	}
}
