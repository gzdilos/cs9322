import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="userProfile")
public class UserProfile {
	public UserProfile(String name, String currentPosition, String education, String pastExperience,
			String professionalSkills, String id) {
		super();
		this.name = name;
		this.currentPosition = currentPosition;
		this.education = education;
		this.pastExperience = pastExperience;
		this.professionalSkills = professionalSkills;
		this.setId(id);
	}
	
	public UserProfile() {
		// TODO Auto-generated constructor stub
	}

	private String name;
	private String currentPosition;
	private String education;
    private String pastExperience;
    private String professionalSkills;
    private String id;
    
	public String getName() {
		return name;
	}
	public String getCurrentPosition() {
		return currentPosition;
	}
	public String getEducation() {
		return education;
	}
	public String getPastExperience() {
		return pastExperience;
	}
	public String getProfessionalSkills() {
		return professionalSkills;
	}
	public String getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCurrentPosition(String currentPosition) {
		this.currentPosition = currentPosition;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public void setPastExperience(String pastExperience) {
		this.pastExperience = pastExperience;
	}
	public void setProfessionalSkills(String professionalSkills) {
		this.professionalSkills = professionalSkills;
	}

	public void setId(String id) {
		this.id = id;
	}
    
    
}
