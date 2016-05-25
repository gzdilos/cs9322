import java.io.Serializable;

public class User implements Serializable{
	String username;
	String password;
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this == obj){
			return true;
			
		}
		User u = (User) obj;
		return (username.equals(u.username) && password.equals(u.password));
	}
	
}
