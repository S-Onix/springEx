package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainForAssembler {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		MainForAssembler main = new MainForAssembler();
		while (true) {
			System.out.println("명령어를 입력하세요.");
			String command = reader.readLine();
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			main.excute(command);

		}
	}

	public void excute(String command) {
		switch (command) {
		case "print":
			break;
		case "":
			break;
		}
	}
}
