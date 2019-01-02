package miniexception;

public class IdPasswordNotMatchingException extends RuntimeException{
	public IdPasswordNotMatchingException() {
		// TODO Auto-generated constructor stub
		System.out.println("패스워드가 일치하지 않습니다");
	}
}
