import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="JobPosting")
public class JobPosting {
	String id; 
	String title; 
	String description; 
	String companyProfileId; 
	String positionType; 
	String desiredSkills; 
	String salaryLevel; 
	String location;
	
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public String getCompanyProfileId() {
		return companyProfileId;
	}
	public String getPositionType() {
		return positionType;
	}
	public String getDesiredSkills() {
		return desiredSkills;
	}
	public String getSalaryLevel() {
		return salaryLevel;
	}
	public String getLocation() {
		return location;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCompanyProfileId(String companyProfileId) {
		this.companyProfileId = companyProfileId;
	}
	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}
	public void setDesiredSkills(String desiredSkills) {
		this.desiredSkills = desiredSkills;
	}
	public void setSalaryLevel(String salaryLevel) {
		this.salaryLevel = salaryLevel;
	}
	public void setLocation(String location) {
		this.location = location;
	} 
}
