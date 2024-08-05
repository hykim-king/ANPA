package com.pcwk.ehr.member.domain;
import com.pcwk.ehr.cmn.DTO;

public class Member extends DTO {    
    private String userId   ;//회원아이디
    private String password ;//비밀번호
    private String userName ;//회원이름
    private String email    ;//이메일
    private int    emailYn  ;//이메일수신여부
    private int    adminYn  ;//관리자여부
    private int    subCity  ;//CODE 참조
    private String tel      ;//전화번호
    
    public Member() {
    }

	public Member(String userId, String password, String userName, String email, int emailYn, int adminYn, int subCity,
			String tel) {
		super();
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.email = email;
		this.emailYn = emailYn;
		this.adminYn = adminYn;
		this.subCity = subCity;
		this.tel = tel;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEmailYn() {
		return emailYn;
	}

	public void setEmailYn(int emailYn) {
		this.emailYn = emailYn;
	}

	public int getAdminYn() {
		return adminYn;
	}

	public void setAdminYn(int adminYn) {
		this.adminYn = adminYn;
	}

	public int getSubCity() {
		return subCity;
	}

	public void setSubCity(int subCity) {
		this.subCity = subCity;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", password=" + password + ", userName=" + userName + ", email=" + email
				+ ", emailYn=" + emailYn + ", adminYn=" + adminYn + ", subCity=" + subCity + ", tel=" + tel + "]";
	}
    
}