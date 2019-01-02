package ex3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MessageMain {
	public static void main(String[] args) {
		ApplicationContext factory = new FileSystemXmlApplicationContext("beans.xml");
		MessageBean bean = factory.getBean("messageBean", MessageBeanEn.class);
		bean.sayHello("spring");
		
		MessageBean beanKr = factory.getBean("messageBeanKr", MessageBeanKr.class);
		beanKr.sayHello("½ºÇÁ¸µ");
	}
	
}
