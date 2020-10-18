package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//CRUD 중김으로 기능을 정의
//데이터와 관련된 작업(Data Access object: DAO)
public class MemberDAO {
	//2. db연결
	//String url = "연결하는 방법://ip:port/db명";
	String url = "jdbc:mysql://localhost:3366/shop1";
	String user = "root";
	String password = "1234";
	Connection con;
	//DB프로그램 절차에 맞춰 코딩
	public MemberDAO() throws Exception {
			//1. connector설정
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("1. connector 연결 성공.!!");
			//2. db연결
			
			con = DriverManager.getConnection(url, user, password);
			System.out.println("2. db연결 성공.!!");
	// 기능을 정의 할떄는 메서드(함수)를 사용
	// create메서드 호출시 입력값을 받아주는 중간 매개체 역할의 변수
	//public static void main(String[] s) throws ClassNotFoundException, SQLException {
	}
	
	//회원가입1
	public void create(String id, String pw, String name, String tel) throws ClassNotFoundException, SQLException {
		
		//3. sql문을 만들다.
		String sql = "insert into member values (?, ?, ?, ?)";//값이 없으면 null을 넣어주면됨
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, pw);
		ps.setString(3, name);
		ps.setString(4, tel);
		System.out.println("3. SQL문 생성 성공.!!");
		//4. sql문은 전송
		ps.executeUpdate();
		System.out.println("4. SQL문 정송 성공.!!");
		
		ps.close();
		con.close();
	}
	
	//회원가입2
	public boolean create(MemberVO vo) throws Exception {
		//3. sql문을 만들다.
		String sql = "insert into member values (?, ?, ?, ?)";//값이 없으면 null을 넣어주면됨
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, vo.getId());
		ps.setString(2, vo.getPw());
		ps.setString(3, vo.getName());
		ps.setString(4, vo.getTel());
		System.out.println("3. SQL문 생성 성공.!!");
		//4. sql문은 전송
		int row = ps.executeUpdate();
		System.out.println("4. SQL문 정송 성공.!!");
		boolean result = false;
		if (row==1) {
			result = true;
		}
		ps.close();
		con.close();
		return result;
	}
	
	//특정검색어로 가져오기(전화번호)전화번호중 부분 조건검색
		public List<MemberVO> all(String tel) throws Exception {
			//가방을 넣는 컨테이너 역할을 하게됨.!
			//<>안에는 컨테이너에 무엇을 넣을지 지정.
	     // 3. sql문을 만든다.
	     String sql = "select * from member where tel = ?";
	     //select * from member where id = 'park' //setString
	     //select * from member where no = 1 //setInt
	     PreparedStatement ps = con.prepareStatement(sql);
	     ps.setString(1, tel);
	     // 4. sql문은 전송
	     // select의 결과는 검색결과가 담긴 테이블(항목+내용)
	     // 내용에는 없을 수도 있고, 많은 수도 있음.
	     ResultSet rs = ps.executeQuery();
	     System.out.println("4. SQL문 전송 성공.!!");
	     List<MemberVO> list = new ArrayList<MemberVO>();
	     while (rs.next()) { // 결과가 있는지 없는지 체크해주는 메서드
	    	 //object(vo) Relational DB(row) Mapping(ORM)
	    	MemberVO bag = new MemberVO();//가방만들어서,
	        //가방에 넣기
	        bag.setId(rs.getString("id")); //커서(위치 알려주는친구)
	        bag.setPw(rs.getString("pw"));
	        bag.setName(rs.getString("name"));
	        bag.setTel(rs.getString("tel"));
	        //컨테이너에 넣는다.
	        list.add(bag);
	     } 
	     	rs.close();
			ps.close();
			con.close();
	     return list;
		}
		
		
	//특정검색어로 가져오기(전화번호)2
	public List<MemberVO> all2(String tel) throws Exception {
		//가방을 넣는 컨테이너 역할을 하게됨.!
		//<>안에는 컨테이너에 무엇을 넣을지 지정.
     // 3. sql문을 만든다.
     String sql = "select * from member where tel like'" + tel + "%'";
     //select * from member where id = 'park' //setString
     //select * from member where no = 1 //setInt
     PreparedStatement ps = con.prepareStatement(sql);
     
     // 4. sql문은 전송
     // select의 결과는 검색결과가 담긴 테이블(항목+내용)
     // 내용에는 없을 수도 있고, 많은 수도 있음.
     ResultSet rs = ps.executeQuery();
     System.out.println("4. SQL문 전송 성공.!!");
     List<MemberVO> list = new ArrayList<MemberVO>();
     while (rs.next()) { // 결과가 있는지 없는지 체크해주는 메서드
    	 //object(vo) Relational DB(row) Mapping(ORM)
    	MemberVO bag = new MemberVO();//가방만들어서,
        //가방에 넣기
        bag.setId(rs.getString("id")); //커서(위치 알려주는친구)
        bag.setPw(rs.getString("pw"));
        bag.setName(rs.getString("name"));
        bag.setTel(rs.getString("tel"));
        //컨테이너에 넣는다.
        list.add(bag);
     } 
     	rs.close();
		ps.close();
		con.close();
     return list;
	}
	
	
	//전체검색
	public List<MemberVO> all() throws Exception {
		//가방을 넣는 컨테이너 역할을 하게됨.!
		//<>안에는 컨테이너에 무엇을 넣을지 지정.
     // 3. sql문을 만든다.
     String sql = "select * from member";
     //select * from member where id = 'park' //setString
     //select * from member where no = 1 //setInt
     PreparedStatement ps = con.prepareStatement(sql);
     
     // 4. sql문은 전송
     // select의 결과는 검색결과가 담긴 테이블(항목+내용)
     // 내용에는 없을 수도 있고, 많은 수도 있음.
     ResultSet rs = ps.executeQuery();
     System.out.println("4. SQL문 전송 성공.!!");
     
     List<MemberVO> list = new ArrayList<MemberVO>();
     while (rs.next()) { // 결과가 있는지 없는지 체크해주는 메서드
    	 //object(vo) Relational DB(row) Mapping(ORM)
    	MemberVO bag = new MemberVO();//가방만들어서,
        //가방에 넣기
        bag.setId(rs.getString("id")); //커서(위치 알려주는친구)
        bag.setPw(rs.getString("pw"));
        bag.setName(rs.getString("name"));
        bag.setTel(rs.getString("tel"));
        //컨테이너에 넣는다.
        list.add(bag);
     } 
     	rs.close();
		ps.close();
		con.close();
     return list;
	}
	
	
	
	
	//id 중복체크
	public int read(String id) throws ClassNotFoundException, SQLException {
		
		//3. sql문을 만들다.
		String sql = "select * from member where id = ?";//값이 없으면 null을 넣어주면됨
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		System.out.println("3. SQL문 생성 성공.!!");
		//4. sql문은 전송
		//select의 결과는 검색결과가 담긴 테이블(항목+내용)
		//내용에는 없을수도 있고, 많을수고 있음.
		ResultSet rs = ps.executeQuery();
		System.out.println("4. SQL문 정송 성공.!!");
		
		int result = 0;//없음
		if (rs.next() == true) {//결과가 있는지 없는지 체크 해주는 메서드
			//if (rs.next())와 동일함
			//if문은 rs.next()가 true일떄만 실행되므로!!
			System.out.println("검색결과가 있어요.");
			result = 1;//있음
			String id2 = rs.getString("id");
			String pw2 = rs.getString("pw");
			String name2 = rs.getString("name");
			String tel2 = rs.getString("tel");
			System.out.println("검색결과 id : " + id2);
			System.out.println("검색결과 pw : " + pw2);
			System.out.println("검색결과 name : " + name2);
			System.out.println("검색결과 tel : " + tel2);
		
		}else {
			System.out.println("검색결과가 없어요111111111111.");
		}
		ps.close();
		con.close();
		return result;
		//0이 넘어가면, 검색결과 없음.
		//1이 넘어가면, 검색결과 있음.

	}
	
	//id 중복체크
		public MemberVO one(String id) throws ClassNotFoundException, SQLException {
			
			//3. sql문을 만들다.
			String sql = "select * from member where id = ?";//값이 없으면 null을 넣어주면됨
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			
			System.out.println("3. SQL문 생성 성공.!!");
			//4. sql문은 전송
			//select의 결과는 검색결과가 담긴 테이블(항목+내용)
			//내용에는 없을수도 있고, 많을수고 있음.
			ResultSet rs = ps.executeQuery();
			System.out.println("4. SQL문 정송 성공.!!");
			MemberVO bag = new MemberVO();//가방을 만들어서,
			if (rs.next() == true) {//결과가 있는지 없는지 체크 해주는 메서드
				//if (rs.next())와 동일함
				//if문은 rs.next()가 true일떄만 실행되므로!!
				System.out.println("검색결과가 있어요.");
				String id2 = rs.getString("id");
				String pw2 = rs.getString("pw");
				String name2 = rs.getString("name");
				String tel2 = rs.getString("tel");
				//가방에 넣기
				bag.setId(id2);
				bag.setPw(pw2);
				bag.setName(name2);
				bag.setTel(tel2);
				System.out.println("검색결과 id : " + id2);
				System.out.println("검색결과 pw : " + pw2);
				System.out.println("검색결과 name : " + name2);
				System.out.println("검색결과 tel : " + tel2);	
			}else {
				System.out.println("검색결과가 없어요.");
			}
			rs.close();
			ps.close();
			con.close();
			return bag;
			//bag은 참조형 변수, 주소를 전달!

		}
	//id,pw맞는지 로그인처리 
		public boolean read(String id, String pw) throws ClassNotFoundException, SQLException {
			
			//3. sql문을 만들다.
			String sql = "select * from member where id = ? and pw = ?";//값이 없으면 null을 넣어주면됨
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			
			System.out.println("3. SQL문 생성 성공.!!");
			//4. sql문은 전송
			//select의 결과는 검색결과가 담긴 테이블(항목+내용)
			//내용에는 없을수도 있고, 많을수고 있음.
			ResultSet rs = ps.executeQuery();
			System.out.println("4. SQL문 정송 성공.!!");
			boolean result1 = false;//로그인 not인 상태!
			if (rs.next() == true) {//결과가 있는지 없는지 체크 해주는 메서드
				//if (rs.next())와 동일함
				//if문은 rs.next()가 true일떄만 실행되므로!!
				System.out.println("로그인 ok.");
				result1 = true;//있음					
			}else {
				System.out.println("로그인 not.");
			}
			rs.close();
			ps.close();
			con.close();
			return result1;
			//false면 로그인not.
			//true면 로그인 ok.

		}

	public boolean update(MemberVO vo) throws ClassNotFoundException, SQLException{
		
		//3. sql문을 만들다.
		String sql = "update member set pw = ?, name = ?, tel = ? where id = ?";//값이 없으면 null을 넣어주면됨
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, vo.getPw());
		ps.setString(2, vo.getName());
		ps.setString(3, vo.getTel());
		ps.setString(4, vo.getId());
		
		System.out.println("3. SQL문 생성 성공.!!");
		//4. sql문은 전송
		int row = ps.executeUpdate();
		System.out.println("4. SQL문 정송 성공.!!");
		
		ps.close();
		con.close();
		boolean result = false;
		if (row == 1) {
			result = true;
		}
		return result;
	}
	
	public boolean delete(String id) throws Exception{
		
		//3. sql문을 만들다.
		String sql = "delete from member where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		System.out.println("3. SQL문 생성 성공.!!");
		
		int row = ps.executeUpdate();
		System.out.println("4. SQL문 정송 성공.!!");
		
		
		ps.close();
		con.close();
		boolean result =false;
		if (row == 1 ) {
			result = true;
		}
		return result;
		
	}

}
