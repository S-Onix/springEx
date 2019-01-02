package ex2;

public class HelloSpring {
	public static void main(String[] args) {
		MessageBean bean = new MessageBeanKr();
		bean.sayHello("½ºÇÁ¸µ");
		bean = new MessageBeanEn();
		bean.sayHello("Spring");
	}
}
