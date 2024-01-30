package com.javaex.ex02;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		
		AuthorDao authorDao = new AuthorDao();
		authorDao.authorInsert("서장훈", "농구선수");
		authorDao.authorInsert("안정환", "축구선수");
		
		//나중되면 많은 양의 데이터를 넣어야함. 그럼 한덩어리로 넣어야한다. 그래서 밑의 방법을 더 많이 씀
		
		AuthorVo authorVo = new AuthorVo("황일영", "개발강사"); // -> 생성자쪽에서 2개짜리 만들어주기
		authorDao.authorInsert(authorVo);
		
		authorDao.authorDelete(26); // 처음에 1이라는 값을 넣었을때 foreign key에러나는 이유는 book에서 쓰고있는데 내가 1번을 지우면 그 책 테이블 1번은 어떻게 하냐. 나는 못지우겠다. -> 해결방법은 안쓰이게 만들면 됨 (26으로 바꿈)
		authorDao.authorDelete(27);
		authorDao.authorDelete(28);
		
		List<AuthorVo> authorList = authorDao.authorList();
		for(AuthorVo vo : authorList) {
			System.out.println(vo.getAuthorId() + "," 
							+ vo.getAuthorName() + "," 
							+ vo.getAuthorDesc());
		}
		
	}

}
