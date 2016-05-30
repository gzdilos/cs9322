import java.util.Vector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "jobPostings")
public class JobPostings extends Vector{	
	@XmlElement(name = "jobPosting")
	Vector<JobPosting> jobPosting;	
	
	public Vector<JobPosting> getJobPostings() {
		return jobPosting;
	}

	public void setJobPosting(Vector<JobPosting> JobPostings) {
		this.jobPosting = JobPostings;
	}
}


