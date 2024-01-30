package com.javaex.ex01;

public class AuthorVo {

	//valueObject는 한세트로 데이터를 가지고 있다는 개념임
	
	//필드
	private int authorId ; //이름이 같지 않아도 됨. 자바에서는 _대신 카멜케이스로 씀
	private String authorName;
	private String authorDesc;
	

	//생성자
	public AuthorVo() {

	}

	public AuthorVo(int authorId, String authorName, String authorDesc) {

		this.authorId = authorId;
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}

	
	//메소드-gs
	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorDesc() {
		return authorDesc;
	}

	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc;
	}

	
	//메소드-일반
	@Override
	public String toString() {
		return "AuthorVo [authorId=" + authorId + ", authorName=" + authorName + ", authorDesc=" + authorDesc + "]";
	}
	
}
