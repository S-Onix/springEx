package ex4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		Greeter g = ctx.getBean("greeter", Greeter.class);
		String msg = g.greet("½ºÇÁ¸µ");
		System.out.println(msg);
	}
}
