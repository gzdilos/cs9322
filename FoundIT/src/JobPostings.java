import java.util.Vector;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="JobPostings")
public class JobPostings extends Vector{
	
	@XmlElement(name="JobPosting")
	private Vector<JobPosting> JobPosting;
	
	@XmlTransient
	public Vector<JobPosting> getJobPosting() {
		return JobPosting;
	}

	public void setJobPosting(Vector<JobPosting> JobPosting) {
		this.JobPosting = JobPosting;
	}
}
