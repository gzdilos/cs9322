

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserProfile {
	private String id;
    private String name;
	private String address;
	private String licenseNumber;
    private String currentPosition;
	private String education;
    private String pastExperience;
    private String professionalSkills;
    private String link;
	private String rel;
	
    public UserProfile() {
		super();
	}

    public UserProfile(String id) {
		super();
		this.link = "http://localhost:8080/FoundITServer/userprofile/" + id;
	}
    
	public UserProfile(String id, String name, String currentPosition,
			String education, String pastExperience, String professionalSkills, String address, String licenseNumber) {
		super();
		this.id = id;
		this.name = name;
		this.currentPosition = currentPosition;
		this.education = education;
		this.pastExperience = pastExperience;
		this.professionalSkills = professionalSkills;
		this.link = "http://localhost:8080/FoundITServer/userprofile/" + this.id;
		this.address = address;
		this.licenseNumber = licenseNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(String currentPosition) {
		this.currentPosition = currentPosition;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getPastExperience() {
		return pastExperience;
	}

	public void setPastExperience(String pastExperience) {
		this.pastExperience = pastExperience;
	}

	public String getProfessionalSkills() {
		return professionalSkills;
	}

	public void setProfessionalSkills(String professionalSkills) {
		this.professionalSkills = professionalSkills;
	}

	@XmlAttribute(name = "href")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
		int startId = link.lastIndexOf('/') + 1;
		this.id = link.substring(startId,link.length());
	}

	@XmlAttribute(name = "rel")
	public String getRel() {
		rel = "userprofile";
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
    
    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
}
