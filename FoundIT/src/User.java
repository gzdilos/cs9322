import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="user")
public class User implements Serializable{
	String username;
	String password;
	String userType;
	String id;
	
	public String getUserType() {
		return userType;
	}
	public String getId() {
		return id;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public void setId(String id) {
		this.id = id;
	}
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
