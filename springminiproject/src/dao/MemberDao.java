package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import vo.Member;

public class MemberDao {
	private static long nextId = 0;
	private Map<String, Member> map = new HashMap<>();
	
	public Member selectByEmail(String email) {
		return map.get(email);
	}
	
	public void insert(Member member) {
		member.setId(++nextId);
		map.put(member.getEmail(), member);
	}
	
	public void update(Member member) {
		map.put(member.getEmail(), member);
	}
	
	//맵의 모든 정보 가져오기
	public ArrayList<Member> selectAll(){
		ArrayList<Member> members = new ArrayList<>();
		
		Iterator<String> i = map.keySet().iterator();
		while(i.hasNext()) {
			String key = i.next();
			members.add(map.get(key));
		}
		
		return members;
	}
}
