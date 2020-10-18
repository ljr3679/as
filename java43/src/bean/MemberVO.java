package bean;

public class MemberVO {
	//private라고 쓰면, 이클래스내에서만 변수에 접근해서 쓸수 있음.
	private String id;
	private String pw;
	private String name;
	private String tel;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	
	
	
	
	
	//각 변수에 값을 넣는 /뺴는 메서드를 정의 하면됨.!
	//가방에 넣을떄는 set메서드로 정의: setters //값을 넣을떄는 set을 넣는다
	//가방에서 꺼낼때는 get메서드로 정의: getters //값을 꺼낼떄는 set을 넣는다
	//헷갈려서 this.id = id; this.는 클래스를 의미 
	
}
