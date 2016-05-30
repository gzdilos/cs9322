import java.util.Vector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "jobApplications")
public class JobApplications extends Vector{
	@XmlElement(name = "jobApplication")
	Vector<JobApplication> jobApplication;

	public Vector<JobApplication> getJobApplications() {
		return jobApplication;
	}
	
	public void setJobApplications(Vector<JobApplication> jobApplications) {
		this.jobApplication = jobApplications;
	}
	   
   
}  