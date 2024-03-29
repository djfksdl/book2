package com.javaex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "com.mysql.cj.jdbc.Driver" ;
	private String url = "jdbc:mysql://localhost:3306/book_db";
	private String id = "book";
	private String pw = "book";

	// 생성자
	// 기본생성자 사용

	// 메소드-gs

	// 메소드-일반
	private void getConnection() {
		// 0. import java.sql.*; -뒤에 conn이 인식을 못해서 필드에 올려준다.
//		Connection conn = null;
//		PreparedStatement pstmt = null;		
//		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver); // 위에 생성자로 올려주고 변수명으로 넣어줌

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}

	}

	private void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
		        rs.close();
		    }
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 작가리스트
	public List<AuthorVo> authorList() {// 받을게 없어서 파라미터 자리는 비움.
		this.getConnection();
		// authorList의 자료형이 List<AuthorVo>라서 써줌. 마지막에 return값이 있음.
		// 리스트 만들고
		// db에서 데이터 가져오고
		// 리스트에 추가하기
		// 리스트 주소 전달하기

		// = new ArrayList<Circle>이것도 알고 썼으면 CircleVo로 썼었을것임. Vo는 암묵적 약속이다. 안쓴다고 틀린건 아님
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();

		// 0. import java.sql.*;

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// - sql문 준비
			String query = "";
			query += " select author_id ";
			query += " 		,author_name ";
			query += " 		,author_desc ";
			query += " from author ";

			// - 바인딩
			pstmt = conn.prepareStatement(query);
			// 물음표는 없어서 더 쓰는건 없음

			// - 실행
			rs = pstmt.executeQuery(); // 나머지 3개와 다르게 executeUpdate()를 쓰지 않음.
			// ResultSet rs = pstmt.executeQuery(); //위에 rs가 선언이 되어있어서 rs만 써주면 됨.

			// 4.결과처리

			while (rs.next()) {
				int no = rs.getInt("author_id");
				String name = rs.getString("author_name");
				String desc = rs.getString("author_desc");

				AuthorVo authorVo = new AuthorVo(no, name, desc);
				authorList.add(authorVo);

			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		this.close();
		return authorList;

	}

	// 작가수정
	// 예제

	// 작가삭제
	public int authorDelete(int no) {
		int count = -1; // -1은 올 수가 없는 숫자라 구별하기 위해 -1을 써준다.
		
		this.getConnection();
		
		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// sql문 준비
			String query = "";
			query += " delete from author ";
			query += " where author_id = ? ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 삭제 되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		this.close();
		return count;
	}
	//작가수정
	public int authorUpdate(AuthorVo authorVo) {
		int count = -1 ;
		
		
		
		return count;
	}

	// 작가등록
	public int authorInsert(String name, String desc) {
		// book_db데이터베이스에 접속
		// book/book
		// author테이블에
		// 작가를 insert
		// 어제 배웠던 insert문 넣어주기
		int count = -1;

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행 
			// 같은 패턴임
			// sql문 준비
			String query = "";
			query += " insert into author "; // 앞에 한칸 안띄워주면 authorvalues가 되어서 안된다.근데 뒤에 띄우면 빠질 수 있으니까 앞에 다 띄워놓는다
			query += " values(null,? ,?) "; // 원래 쿼리문에 있는 마지막 ;는 빼기. 값이 뭐가 올지 모르면 ?를 넣어줘야함.

			// 바인딩 - 물음표에 실제 값을 매칭시켜준다.
			pstmt = conn.prepareStatement(query); // conn이 인식을 못해서 필드에 마련해 놓는다.
			pstmt.setString(1, name); // 첫번째 물음표에 값 매칭
			pstmt.setString(2, desc);

			// 실행
			count = pstmt.executeUpdate(); // 굳이 안알려줘도되는데 알려주는것. insert는 보통 1개씩되는데 delete는 범위때문에 10개도 넘게 지워질 수 있음.
			// count는 3개나 1개 등등 갯수 알려줌. 그래서 insert,update,delete는 이걸 써야 쿼리문대로 실행하고 갯수 알려줌.
			// select는 숫자가 아닌 데이터를 알려줘야하기때문에 executeUpdate를 쓰지않고 다른걸 씀.

			// 4.결과처리
			System.out.println(count + "건 등록되었습니다.");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		this.close();
		return count;

	}

	// 작가등록2 - 한덩어리에 담아서 넣는 방법 쓸 때 만들어줘야함.
	public int authorInsert(AuthorVo authorVo) {
		// book_db데이터베이스에 접속
		// book/book
		// author테이블에
		// 작가를 insert
		// 어제 배웠던 insert문 넣어주기
		int count = -1;

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행 - 쿼리문 복사해온거 줄바꿈 없이 그대로 옮기는게 나음/ insert, update, delete는
			// 같은 패턴임
			// sql문 준비
			String query = "";
			query += " insert into author "; // 앞에 한칸 안띄워주면 authorvalues가 되어서 안된다.근데 뒤에 띄우면 빠질 수 있으니까 앞에 다 띄워놓는다
			query += " values(null,? ,?) "; // 원래 쿼리문에 있는 마지막 ;는 빼기. 값이 뭐가 올지 모르면 ?를 넣어줘야함.

			// 바인딩 - 물음표에 실제 값을 매칭시켜준다.
			pstmt = conn.prepareStatement(query); // conn이 인식을 못해서 필드에 마련해 놓는다.
			pstmt.setString(1, authorVo.getAuthorName()); // 첫번째 물음표에 값 매칭
			pstmt.setString(2, authorVo.getAuthorDesc());

			// 실행
			count = pstmt.executeUpdate(); // 굳이 안알려줘도되는데 알려주는것. insert는 보통 1개씩되는데 delete는 범위때문에 10개도 넘게 지워질 수 있음.
			// count는 3개나 1개 등등 갯수 알려줌. 그래서 insert,update,delete는 이걸 써야 쿼리문대로 실행하고 갯수 알려줌.
			// select는 숫자가 아닌 데이터를 알려줘야하기때문에 executeUpdate를 쓰지않고 다른걸 씀.

			// 4.결과처리
			System.out.println(count + "건 등록되었습니다.");
		}  catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		this.close();
		return count;

	}

}
