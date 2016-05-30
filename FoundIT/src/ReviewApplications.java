import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="reviewapplications")
public class ReviewApplications {
	private ArrayList<ReviewApplication> list;
	private int size;
	
	public ReviewApplications(ArrayList<ReviewApplication> list) {
		super();
		this.list = list;
		this.size = list.size();
	}
	
	public ReviewApplications() {
		
	}
	
	public ReviewApplication getReview(int i) {
		return list.get(i);
	}
	
	public UserProfile getProfile(int i) {
		return list.get(i).getProfile();
	}
	
	public JobApplication getJobApp(int i) {
		return list.get(i).getJobApplication();
	}
	
	public int getSize() {
		return size;
	}
	
	public void setList(ArrayList<ReviewApplication> list) {
		this.list = list;
		this.size = list.size();
	}
	
	public ArrayList<ReviewApplication> getList() {
		return list;
	}
}
