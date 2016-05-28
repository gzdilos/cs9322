import java.util.Vector;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="userList")
public class userList extends Vector{
	
	@XmlElement(name="user")
	private Vector<User> user;
	@XmlTransient
	public Vector<User> getUserList() {
		return user;
	}

	public void setUserList(Vector<User> usersList) {
		this.user = usersList;
	}
}
