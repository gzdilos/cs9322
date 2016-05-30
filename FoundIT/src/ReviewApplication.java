import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="reviewapplication")
public class ReviewApplication {

	public ReviewApplication(String userid, JobApplication jobapp) {
		super();
		this.userid = userid;
		this.jobapp = jobapp;
	}
	
	public ReviewApplication() {
		
	}

	private String userid;
	private JobApplication jobapp;
    private String id;
    private UserProfile profile;
    
	public JobApplication getJobApplication() {
		return jobapp;
	}
	
	public String getUserID() {
		return userid;
	}
	
	public String getId() {
		return id;
	}
	
	public UserProfile getProfile() {
		return profile;
	}
	
	public void setUserID(String userid) {
		this.userid = userid;
	}
	public void setJobApplication(JobApplication jobapp) {
		this.jobapp = jobapp;
	}

	public void setId(String id) {
		this.id = id;
	}
	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}

}
