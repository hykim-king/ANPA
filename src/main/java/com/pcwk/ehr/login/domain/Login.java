package com.pcwk.ehr.login.domain;
import com.pcwk.ehr.cmn.DTO;

public class Login extends DTO {    
    private String userId   ;//회원아이디
    private String password ;//비밀번호
    
    public Login() {
    }

	public Login(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
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

	@Override
	public String toString() {
		return "Login [userId=" + userId + ", password=" + password + "]";
	}
	
}