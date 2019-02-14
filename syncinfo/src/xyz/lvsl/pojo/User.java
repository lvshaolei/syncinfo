package xyz.lvsl.pojo;

import java.io.Serializable;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String username;
	private String password;
	private String role;
	
	public User(String id, String usernmae, String password, String role) {
		this.id = id;
		this.username = usernmae;
		this.password = password;
		this.role = role;
	}
	public User(String usernmae, String password, String role) {
		this.username = usernmae;
		this.password = password;
		this.role = role;
	}
	
	@Override
	public boolean equals(Object obj) {
		User user = (User)obj;
		return this.username.equals(user.username) && this.password.equals(user.password) 
				&& this.getRole() == user.getRole();
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
}
