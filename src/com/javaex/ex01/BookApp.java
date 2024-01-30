package com.javaex.ex01;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		
		AuthorDao authorDao = new AuthorDao();
		int cnt = authorDao.authorInsert("이연수", "제주도민");
		System.out.println("");
		
		authorDao.authorDelete(9);
		
		List<AuthorVo> authorList = authorDao.authorList();
		//내가 직접 만든다고하면 밑에 같이 되는데 그 역할을 위에 쓴것처럼 한다.  
		//List<AuthorVo> authorList = new ArratList<AuthorVo>();
		for(AuthorVo authorVo : authorList) {// 여러개중에 하나를 담는 authorVo,그리고 그걸 따라가면 AuthorVo가 나온다. 
			int id = authorVo.getAuthorId();
			String name = authorVo.getAuthorName();
			String desc = authorVo.getAuthorDesc();
			System.out.println(id + ", "+ name + ", " + desc);
		}
		System.out.println(authorList.size());
		System.out.println(authorList.toString());
		
		/*or(int i = 0; i<authorList.size(); i++) {			
			int id = authorList.get(i).getAuthorId(); //i번째의 아이디 출력
			String name = authorList.get(i).getAuthorName();
			String desc = authorList.get(i).getAuthorDesc();
			System.out.println(id + ", "+ name + ", " + desc);
		}*/
		
		
	}

}
