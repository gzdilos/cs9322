
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="JobApplication")
public class JobApplication {
	
   //job app status
    public static final String STATUS_SUBMITTED = "submitted";
    public static final String STATUS_SHORTLISTED = "shortlisted";
    public static final String STATUS_NOT_SHORTLISTED = "not-shortlisted";
    public static final String STATUS_ACCEPTED = "accepted-invitation";
    public static final String STATUS_REJECTED = "rejected-invitation";
    //archived status
    public static final String ARCHIVED_TRUE = "T";
    public static final String ARCHIVED_FALSE = "F";
    
    
	private String id;
    private String jobPostId;
    private JobPosting jobPostLink;
    private String userProfileId;
    private UserProfile userProfileLink;
    private String coverLetter;
    private String resume; //Maybe just a text pretending to be an attachment (i.e. “resume.pdf”)
    private String status; // (shortlisted/not-shortlisted or rejected/accepted)
    private String archived; //Y or N
    private String link;
    private String rel;
	private boolean sendVersion = false;
	public void setSendVersion(boolean set){
		this.sendVersion = set;
	}
	
    public JobApplication() {

	}
    
    public JobApplication(String id) {
		super();
		this.link = "http://localhost:8080/RestfulJobService/jobapplication/" + id;
	}
    
    public JobApplication(String id, String jobPostId,
			String userProfileId, String coverLetter, String resume) {
		super();
		this.id = id;
		this.jobPostId = jobPostId;
		this.userProfileId = userProfileId;
		this.coverLetter = coverLetter;
		this.resume = resume;
		this.status = STATUS_SUBMITTED;
		this.archived = ARCHIVED_FALSE;
		this.link = "http://localhost:8080/RestfulJobService/jobapplication/" + this.id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobPostId() {
		if(sendVersion == true)
			return null;
		return jobPostId;
	}

	public void setJobPostId(String jobpostId) {
		this.jobPostId = jobpostId;
	}

	public String getUserProfileId() {
		if(sendVersion == true)
			return null;
		return userProfileId;
	}

	public void setUserProfileId(String userProfileId) {
		this.userProfileId = userProfileId;
	}

	public String getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getArchived() {
		return archived;
	}

	public void setArchived(String archived) {
		this.archived = archived;
	}

	@XmlAttribute(name = "href")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@XmlAttribute(name = "rel")
	public String getRel() {
		rel = "jobapplication";
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}


    

}
