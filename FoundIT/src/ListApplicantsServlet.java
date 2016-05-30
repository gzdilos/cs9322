

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/listapplicants")
public class ListApplicantsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListApplicantsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Get all reviews
		//String uri = "http://localhost:8080/FoundITServer/review";
		
		HttpSession mySession = request.getSession(false);
		RequestDispatcher r = request.getRequestDispatcher("");	
		User u = (User) mySession.getAttribute("user");
		
		if(request.getParameterMap().containsKey("shortlist")){
			String action = request.getParameter("shortlist");
			if(action.equals("success")){
				
				String userid = request.getParameter("userid");
				String jobid = request.getParameter("jobid");
				
				System.out.println("Selected to ShortList Applicant with userid: " + userid + " and jobid: " + jobid);
				
				ReviewApplications listapp = (ReviewApplications) mySession.getAttribute("listapp");
				
				int i = 0;
				
				while (i != listapp.getSize()) {
					System.out.println(listapp.getJobApp(i).getId() + listapp.getProfile(i).getId());
					if (listapp.getJobApp(i).getId().contentEquals(jobid) && listapp.getProfile(i).getId().contentEquals(userid)) {
						listapp.getJobApp(i).setStatus(JobApplication.STATUS_SHORTLISTED);
						System.out.println("Status is now " + listapp.getJobApp(i).getStatus());
						break;
					}
					i++;
				}
				
				//TODO: Need to send something to the rest server to tell the application has been shortlisted!!
				//Should get the user profile of an application
				/*
				String uri = "http://localhost:8080/FoundITServer/userprofile"; 
				URL url = new URL(uri);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				
				connection.setRequestMethod("PUT");
				connection.setRequestProperty("Content-Type", "application/xml");
				connection.setDoOutput(true);
				UserProfile user = new UserProfile();
				user.setName(request.getParameter("name"));
				user.setCurrentPosition(request.getParameter("currentPosition"));
				user.setEducation(request.getParameter("education"));
				user.setPastExperience(request.getParameter("pastExperience"));
				user.setProfessionalSkills(request.getParameter("professionalSkills"));
				user.setId(request.getParameter("id"));
				OutputStream os = connection.getOutputStream();
				*/
				mySession.removeAttribute("listapp");
				
				mySession.setAttribute("listapp", listapp);
				
				r = getServletContext().getRequestDispatcher( "/WEB-INF/jsps/listapplications.jsp");
				r.forward(request, response);
				return;
				
			} else {
				String userid = request.getParameter("userid");
				String jobid = request.getParameter("jobid");
				
				System.out.println("Selected to Reject Applicant with userid: " + userid + " and jobid: " + jobid);
				
				ReviewApplications listapp = (ReviewApplications) mySession.getAttribute("listapp");
				
				int i = 0;
				
				while (i != listapp.getSize()) {
					if (listapp.getJobApp(i).getId().contentEquals(jobid) && listapp.getProfile(i).getId().contentEquals(userid)) {
						listapp.getJobApp(i).setStatus(JobApplication.STATUS_NOT_SHORTLISTED);
						break;
					}
					i++;
				}
				
				//TODO: Need to send something to the rest server to tell the application has been shortlisted!!
				
				mySession.removeAttribute("listapp");
				mySession.setAttribute("listapp", listapp);
				
				r = getServletContext().getRequestDispatcher( "/WEB-INF/jsps/listapplications.jsp");
				r.forward(request, response);
				return;
			}
		}
		
		//Somehow get all job applications
		//Should receive an xml
		//TODO: Remove comment below
		
		String uri = "http://localhost:8080/FoundITServer/jobappreviewassign/reviewer/"+u.getId();
		URL url = new URL(uri);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/xml");
		
		//Go through job application and get applicants			
		InputStream xml = connection.getInputStream();
		
		ReviewApplications listapp = parseStreamJobApp(xml);
		connection.disconnect();
		listapp = getUserProfile(listapp);
		/*Local testing
			ReviewApplications listapp = parseStreamJobApp(null);
		
			listapp = getUserProfile(listapp);
		*/
		
		/*
		try {
			JAXBContext jc;
			jc = JAXBContext.newInstance(JobApplication.class);
			InputStream xml = connection.getInputStream();		
			
			JobApplication app = (JobApplication) jc.createUnmarshaller().unmarshal(xml);
			connection.disconnect();
			mySession.setAttribute("application", app);
			r = getServletContext().getRequestDispatcher( "/WEB-INF/jsps/listapplications.jsp");
			r.forward(request, response);
			return;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			connection.disconnect();
		}*/

		mySession.setAttribute("listapp", listapp);
		r = getServletContext().getRequestDispatcher( "/WEB-INF/jsps/listapplications.jsp");
		r.forward(request, response);
		return;
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private ReviewApplications parseStreamJobApp(InputStream xml) {
		
		ArrayList<ReviewApplication> listapp = new ArrayList<ReviewApplication>();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}
		try {
			File filetemp = new File("review.xml");
			Document doc;
			
			if (xml == null) {
				doc = dBuilder.parse(filetemp);
			} else {
				doc = dBuilder.parse(xml);
			}
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("jobApplication");
	        
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	        	Node nNode = nList.item(temp);
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	            	JobApplication job = new JobApplication();
	            	
	            	Element eElement = (Element) nNode;
	            	
	            	System.out.println(eElement.getElementsByTagName("archived").item(0).getTextContent());
	            	System.out.println(eElement.getElementsByTagName("coverLetter").item(0).getTextContent());
	            	System.out.println(eElement.getElementsByTagName("resume").item(0).getTextContent());
	            	System.out.println(eElement.getElementsByTagName("status").item(0).getTextContent());
	            	System.out.println(eElement.getElementsByTagName("userProfileId").item(0).getTextContent());
	            	
	            	job.setArchived(eElement.getElementsByTagName("archived").item(0).getTextContent());
	            	job.setId(eElement.getElementsByTagName("id").item(0).getTextContent());
	            	job.setCoverLetter(eElement.getElementsByTagName("coverLetter").item(0).getTextContent());
	            	job.setResume(eElement.getElementsByTagName("resume").item(0).getTextContent());
	            	job.setStatus(eElement.getElementsByTagName("status").item(0).getTextContent());
	            	job.setJobPostId(eElement.getElementsByTagName("jobPostId").item(0).getTextContent());
	            	
	            	String userid = eElement.getElementsByTagName("userProfileId").item(0).getTextContent();
	            	
	            	System.out.println("Received id" + userid);
	            	ReviewApplication rev = new ReviewApplication();
	            	rev.setUserID(userid);
	            	rev.setJobApplication(job);
	            	
	            	listapp.add(rev);
	            }
	         }
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ReviewApplications list = new ReviewApplications(listapp);
		return list;
	}

	private ReviewApplications getUserProfile(ReviewApplications list) {
		int i = 0;
		
		while(i != list.getSize()) {
			
			String userid = list.getReview(i).getUserID();
			System.out.println("id is " + userid);
			
			String uri = "http://localhost:8080/FoundITServer/userprofile/"+userid;
			URL url;
			try {
				url = new URL(uri);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setRequestProperty("Accept", "application/xml");
				
				//Go through job application and get applicants			
				InputStream xml = connection.getInputStream();
				
				UserProfile profile = parseProfile(xml, userid);
				list.getReview(i).setProfile(profile);
				connection.disconnect();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//UserProfile profile = parseProfile(xml, userid);
			//UserProfile profile = parseProfile(null, userid);
			//list.getReview(i).setProfile(profile);
			i++;
		}
		
		return list;
	}

	private UserProfile parseProfile(InputStream xml, String userid) {
		UserProfile newUser = new UserProfile();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}
		try {
			Document doc;
			
			File filetemp = new File("userprofile" + userid + ".xml");
			if(xml == null){
				doc = dBuilder.parse(filetemp);
			} else {
				doc = dBuilder.parse(xml);
			}
			//doc = dBuilder.parse(xml);
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("userProfile");
	        
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	        	Node nNode = nList.item(temp);
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

	            	Element eElement = (Element) nNode;
	            	
	            	System.out.println(eElement.getElementsByTagName("currentPosition").item(0).getTextContent());
	            	System.out.println(eElement.getElementsByTagName("education").item(0).getTextContent());
	            	System.out.println(eElement.getElementsByTagName("id").item(0).getTextContent());
	            	System.out.println(eElement.getElementsByTagName("name").item(0).getTextContent());
	            	System.out.println(eElement.getElementsByTagName("pastExperience").item(0).getTextContent());
	            	System.out.println(eElement.getElementsByTagName("professionalSkills").item(0).getTextContent());

	            	newUser.setCurrentPosition(eElement.getElementsByTagName("currentPosition").item(0).getTextContent());
	            	newUser.setEducation(eElement.getElementsByTagName("education").item(0).getTextContent());
	            	newUser.setId(eElement.getElementsByTagName("id").item(0).getTextContent());
	            	newUser.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
	            	newUser.setPastExperience(eElement.getElementsByTagName("pastExperience").item(0).getTextContent());
	            	newUser.setProfessionalSkills(eElement.getElementsByTagName("professionalSkills").item(0).getTextContent());
	            	//break;
	            }
	         }
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return newUser;
	}
	
	
}
