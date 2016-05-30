
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserProfiles {
	@XmlElement(name = "userProfile")
	List<UserProfile> userProfiles = new ArrayList<UserProfile>();

	public UserProfiles() {
		
	}
	
	public UserProfiles(List<UserProfile> userProfiles) {
		super();
		this.userProfiles = userProfiles;
	}

	public List<UserProfile> getUserProfiles() {
		return userProfiles;
	}
	
	public void setUserProfiles(List<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}
	   
   
}  