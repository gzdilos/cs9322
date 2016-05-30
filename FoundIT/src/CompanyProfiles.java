
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyProfiles extends Vector{
	@XmlElement(name = "CompanyProfile")
	Vector<CompanyProfile> companyProfiles;

	public CompanyProfiles() {
		
	}
	
	public CompanyProfiles(Vector<CompanyProfile> companyProfiles) {
		super();
		this.companyProfiles = companyProfiles;
	}

	public List<CompanyProfile> getCompanyProfiles() {
		return companyProfiles;
	}
	
	public void setCompanyProfiles(Vector<CompanyProfile> companyProfiles) {
		this.companyProfiles = companyProfiles;
	}
	   
   
}  