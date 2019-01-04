package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.support.GenericXmlApplicationContext;

import assembler.Assembler;
import miniexception.AlreadyExistingMemberException;
import miniexception.IdPasswordNotMatchingException;
import miniexception.MemberNotFoundException;
import service.ChangePasswordService;
import service.MemberRegisterService;
import view.MemberInfoPrinter;
import view.MemberListPrinter;
import view.VersionPrinter;
import vo.RegisterRequest;

public class MainForAssembler {
	private static GenericXmlApplicationContext ctx;

	public static void main(String[] args) throws IOException {
		// bean ������ ��üȭ��
		ctx = new GenericXmlApplicationContext("classpath:configImport.xml");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("��ɾ �Է��ϼ���.");
			String command = reader.readLine();

			System.out.println(command);

			if (command.equalsIgnoreCase("exit")) {
				System.out.println("�����մϴ�.");
				break;
			}

			if (command.startsWith("new ")) { // �Է¹�� : "new email name pw repw"
				processNewCommand(command.split(" "));
				continue;
			} else if (command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			} else if (command.startsWith("info ")) {
				processInfoCommand(command.split(" "));
				continue;
			} else if (command.equals("list")) {
				processListCommand();
				continue;
			} else if (command.equals("version")) {
				processVersionCommand();
				continue;
			}

			printHelp();
		}
	}

	public static void processNewCommand(String[] arg) {
		if (arg.length != 5) {
			printHelp();
			return;
		}
		MemberRegisterService regSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class);
		RegisterRequest req = new RegisterRequest();
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);

		if (!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("��ȣ�� Ȯ���� ��ġ���� �ʽ��ϴ�.\n");
			return;
		}

		try {
			regSvc.regist(req);
			System.out.println("����߽��ϴ�.");
		} catch (AlreadyExistingMemberException e) {
			System.out.println("�̹� �����ϴ� �̸����Դϴ�.\n");
		}
	}

	public static void processChangeCommand(String[] arg) {
		if (arg.length != 4) {
			printHelp();
			return;
		}
		ChangePasswordService changePwdSvc = ctx.getBean("changePwdSvc", ChangePasswordService.class);
		try {
			changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("��ȣ�� �����߽��ϴ�.");
		} catch (MemberNotFoundException e) {
			System.out.println("�������� �ʴ� �̸����Դϴ�.");
		} catch (IdPasswordNotMatchingException e) {
			System.out.println("�̸��ϰ� ��ȣ�� ��ġ���� �ʽ��ϴ�.");
		}

	}

	public static void processInfoCommand(String[] arg) {
		if (arg.length != 2) {
			printHelp();
			return;
		}
		MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter.class);
		infoPrinter.printMemberInfo(arg[1]);
	}

	public static void processListCommand() {
		MemberListPrinter listPrinter = ctx.getBean("listPrinter", MemberListPrinter.class);
		listPrinter.printAllMemberInfo();
	}

	public static void processVersionCommand() {
		VersionPrinter versionPrinter = ctx.getBean("versionPrinter", VersionPrinter.class);
		versionPrinter.print();
	}

	public static void printHelp() {
		System.out.println();
		System.out.println("�߸��� ����Դϴ�. �Ʒ� ��ɾ� ������ Ȯ���ϼ���.");
		System.out.println("��ɾ� ���� : ");
		System.out.println("new �̸��� �̸� ��ȣ ��ȣȮ��");
		System.out.println("change �̸��� ������ ������");
		System.out.println("info �̸���");
		System.out.println("list");
		System.out.println("version");
		System.out.println();

	}
}
