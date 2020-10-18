package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BbsDAO {
	String url = "jdbc:mysql://localhost:3366/shop1";
	String user = "root";
	String password = "1234";
	Connection con;
	
	public BbsDAO() throws Exception {
		//DB프로그램 절차에 맞춰 코딩
		//1. connector설정
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("1. connector 연결 성공.!!");
		//2. db연결
		//String url = "연결하는 방법://ip:port/db명";
		con = DriverManager.getConnection(url, user, password);
		System.out.println("2. db연결 성공.!!");
		
		
	}
	
	public boolean create(BbsVO vo) throws Exception{
		
		//3. sql문을 만들다.
		String sql = "insert into bbs values (?, ?, ?, ?)";//값이 없으면 null을 넣어주면됨
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, vo.getNo());
		ps.setString(2, vo.getTitle());
		ps.setString(3, vo.getContent());
		ps.setString(4, vo.getWriter());
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

	public BbsVO one(int no) throws Exception {    
     // 3. sql문을 만든다.
     String sql = "select * from bbs where no = ?";
     //select * from member where id = 'park' //setString
     //select * from bbs where no = 1 //setInt
     PreparedStatement ps = con.prepareStatement(sql);
     ps.setInt(1, no);

     // 4. sql문은 전송
     // select의 결과는 검색결과가 담긴 테이블(항목+내용)
     // 내용에는 없을 수도 있고, 많은 수도 있음.
     ResultSet rs = ps.executeQuery();
     System.out.println("4. SQL문 전송 성공.!!");
     BbsVO bag = new BbsVO();//가방만들어서,
     if (rs.next()) { // 결과가 있는지 없는지 체크해주는 메서드
        System.out.println("검색결과가 있어요.");
        int no2 = rs.getInt("no");
        String title = rs.getString("title");
        String content = rs.getString("content");
        String writer = rs.getString("writer");
        //가방에 넣기
        bag.setNo(no2);
        bag.setTitle(title);
        bag.setContent(content);
        bag.setWriter(writer);
        System.out.println("검색결과 ino2: " + no2);
        System.out.println("검색결과 title: " + title);
        System.out.println("검색결과 content: " + content);
        System.out.println("검색결과 writer: " + writer);
     } else {
        System.out.println("검색결과가 없어요.");
     }
     rs.close();
		ps.close();
		con.close();
     return bag;
     // bag은 참조형 변수, 주소를 전달!
  }
	
	//특정검색어로 가져오기(content)2
		public List<BbsVO> all2(String content) throws Exception {
			//가방을 넣는 컨테이너 역할을 하게됨.!
			//<>안에는 컨테이너에 무엇을 넣을지 지정.
	     // 3. sql문을 만든다.
	     String sql = "select * from bbs where content like'" + content + "%'";
	     //select * from member where id = 'park' //setString
	     //select * from member where no = 1 //setInt
	     PreparedStatement ps = con.prepareStatement(sql);
	     // 4. sql문은 전송
	     // select의 결과는 검색결과가 담긴 테이블(항목+내용)
	     // 내용에는 없을 수도 있고, 많은 수도 있음.
	     ResultSet rs = ps.executeQuery();
	     System.out.println("4. SQL문 전송 성공.!!");
	     List<BbsVO> list = new ArrayList<BbsVO>();
	     while (rs.next()) { // 결과가 있는지 없는지 체크해주는 메서드
	    	 //object(vo) Relational DB(row) Mapping(ORM)
	    	 BbsVO bag = new BbsVO();//가방만들어서,
	        //가방에 넣기
	        bag.setNo(rs.getInt("no")); //커서(위치 알려주는친구)
	        bag.setTitle(rs.getString("title"));
	        bag.setContent(rs.getString("content"));
	        bag.setWriter(rs.getString("writer"));
	        //컨테이너에 넣는다.
	        list.add(bag);
	     } 
	     	rs.close();
			ps.close();
			con.close();
	     return list;
		}
		
		//title 검색
		public List<BbsVO> all(String title) throws Exception {
			//가방을 넣는 컨테이너 역할을 하게됨.!
			//<>안에는 컨테이너에 무엇을 넣을지 지정.
	     // 3. sql문을 만든다.
	     String sql = "select * from bbs where title = ?";
	     //select * from member where id = 'park' //setString
	     //select * from member where no = 1 //setInt
	     PreparedStatement ps = con.prepareStatement(sql);
	     ps.setString(1, title);
	     // 4. sql문은 전송
	     // select의 결과는 검색결과가 담긴 테이블(항목+내용)
	     // 내용에는 없을 수도 있고, 많은 수도 있음.
	     ResultSet rs = ps.executeQuery();
	     System.out.println("4. SQL문 전송 성공.!!");
	     List<BbsVO> list = new ArrayList<BbsVO>();
	     while (rs.next()) { // 결과가 있는지 없는지 체크해주는 메서드
	    	 //object(vo) Relational DB(row) Mapping(ORM)
	    	 BbsVO bag = new BbsVO();//가방만들어서,
	        //가방에 넣기
	    	 bag.setNo(rs.getInt("no")); //커서(위치 알려주는친구)
		     bag.setTitle(rs.getString("title"));
		     bag.setContent(rs.getString("content"));
		     bag.setWriter(rs.getString("writer"));
	        //컨테이너에 넣는다.
	        list.add(bag);
	     } 
	     	rs.close();
			ps.close();
			con.close();
	     return list;
		}	
	
	//전체검색
	public List<BbsVO> all() throws Exception {
		//가방을 넣는 컨테이너 역할을 하게됨.!
		//<>안에는 컨테이너에 무엇을 넣을지 지정.!
		
      
     // 3. sql문을 만든다.
     String sql = "select * from bbs";
     //select * from member where id = 'park' //setString
     //select * from bbs where no = 1 //setInt
     PreparedStatement ps = con.prepareStatement(sql);
     
     // 4. sql문은 전송
     // select의 결과는 검색결과가 담긴 테이블(항목+내용)
     // 내용에는 없을 수도 있고, 많은 수도 있음.
     ResultSet rs = ps.executeQuery();
     System.out.println("4. SQL문 전송 성공.!!");
     List<BbsVO> list = new ArrayList<BbsVO>();
     while (rs.next()) { // 결과가 있는지 없는지 체크해주는 메서드
    	BbsVO bag = new BbsVO();//가방만들어서,
        //가방에 넣기
        bag.setNo(rs.getInt("no")); //커서(위치 알려주는친구)
        bag.setTitle(rs.getString("title"));
        bag.setContent(rs.getString("content"));
        bag.setWriter( rs.getString("writer"));
        //컨테이너에 넣는다.
        list.add(bag);
     } 
        rs.close();
		ps.close();
		con.close();
     return list;
     // bag은 참조형 변수, 주소를 전달!
  }
	
	
	/*public BbsVO one(int no) throws ClassNotFoundException, SQLException {
		
		//3. sql문을 만들다.
		String sql = "select * from bbs where no = ?";//값이 없으면 null을 넣어주면됨
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, no);
		
		System.out.println("3. SQL문 생성 성공.!!");
		//4. sql문은 전송
		//select의 결과는 검색결과가 담긴 테이블(항목+내용)
		//내용에는 없을수도 있고, 많을수고 있음.
		ResultSet rs = ps.executeQuery();
		System.out.println("4. SQL문 정송 성공.!!");
		BbsVO bag = new BbsVO();//가방을 만들어서,
		if (rs.next() == true) {//결과가 있는지 없는지 체크 해주는 메서드
			//if (rs.next())와 동일함
			//if문은 rs.next()가 true일떄만 실행되므로!!
			System.out.println("검색결과가 있어요.");
						//가방에 넣기
			bag.setNo(rs.getInt("no"));
			bag.setTitle(rs.getString("title"));
			bag.setContent(rs.getString("content"));
			bag.setWriter(rs.getString("writer"));
				
		}else {
			System.out.println("검색결과가 없어요.");
		}
		rs.close();
		ps.close();
		con.close();
		return bag;
		//bag은 참조형 변수, 주소를 전달!

	}*/
		//0이 넘어가면, 검색결과 없음.
		//1이 넘어가면, 검색결과 있음.
	
	public boolean update(BbsVO vo) throws ClassNotFoundException, SQLException{
		//3. sql문을 만들다.
		String sql = "update bbs set title = ?, content = ?, writer = ? where no = ?";//값이 없으면 null을 넣어주면됨
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, vo.getTitle());
		ps.setString(2, vo.getContent());
		ps.setString(3, vo.getWriter());
		ps.setInt(4, vo.getNo());
		
		
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
	
	public boolean delete(int no) throws Exception{
		
		//3. sql문을 만들다.
		String sql = "delete from bbs where no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, no);
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

