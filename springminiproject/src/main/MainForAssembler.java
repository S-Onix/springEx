package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import assembler.Assembler;
import miniexception.AlreadyExistingMemberException;
import service.MemberRegisterService;
import vo.RegisterRequest;

public class MainForAssembler {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		MainForAssembler main = new MainForAssembler();
		while (true) {
			System.out.println("��ɾ �Է��ϼ���.");
			String command = reader.readLine();
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("�����մϴ�.");
				break;
			}
			if (command.startsWith("new ")) { // �Է¹�� : "new email name pw repw"
				processNewCommand(command.split(" "));
				continue;
			}
			if(command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
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
			System.out.println("��ȣ�� Ȯ���� ��ġ���� �ʽ��ϴ�.\n");
			return;
		}
		
		try {
			regSvc.regist(req);
			System.out.println("����߽��ϴ�.");
		}catch(AlreadyExistingMemberException e) {
			System.out.println("�̹� �����ϴ� �̸����Դϴ�.\n");
		}
	}
	
	public static void processChangeCommand(String [] arg) {
		if(arg.length != 4) {
			printHelp();
			return;
		}
		
	}
	
	public static void printHelp() {
		System.out.println();
		System.out.println("�߸��� ����Դϴ�. �Ʒ� ��ɾ� ������ Ȯ���ϼ���.");
		System.out.println("��ɾ� ���� : ");
		System.out.println("new �̸��� �̸� ��ȣ ��ȣȮ��");
		System.out.println("change �̸��� ������ ������");
		System.out.println();
		
	}
}
