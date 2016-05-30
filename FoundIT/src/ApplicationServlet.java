

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Servlet implementation class Applicationservlet
 */
@WebServlet("/applications")
public class ApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher r = request.getRequestDispatcher("");
		HttpSession mySession = request.getSession(false);
		User user =(User) mySession.getAttribute("user");
		if(request.getParameterMap().containsKey("action")){
			String action = request.getParameter("action");
			if(action.equals("apply")){
				String jobid = request.getParameter("jobid");
			    String query = "jobpostid="+URLEncoder.encode(jobid,"UTF-8");
			    query += "&";
			    query += "userprofileid="+URLEncoder.encode(user.getId(),"UTF-8");
			    query += "&";
			    query += "coverletter="+URLEncoder.encode("cv.txt","UTF-8");
			    query += "&";
			    query += "resume="+URLEncoder.encode("resume.pdf","UTF-8");
			    System.out.println(query);
			    String uri = 
					    "http://localhost:8080/FoundITServer/jobapplication/";
					URL url = new URL(uri);
					HttpURLConnection connection = 
					    (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("POST");
					connection.setDoOutput(true);
					connection.setDoInput(true);
					connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
					connection.setRequestProperty("Accept", "application/xml");	
					DataOutputStream os = new DataOutputStream(connection.getOutputStream());
					os.writeBytes(query);
					os.flush();
					os.close();
					InputStream xml = connection.getInputStream();	
					r = request.getRequestDispatcher("/WEB-INF/jsps/application.jsp");
					r.forward(request, response);
					return;
			}
		}else{
			Vector<ApplicationDisplay> myApplications = new Vector<ApplicationDisplay>();
			User u = (User)mySession.getAttribute("user");
			String uri = 
				    "http://localhost:8080/FoundITServer/jobapplication/search?userprofileid="+u.getId();
				URL url = new URL(uri);
				HttpURLConnection connection = 
				    (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setRequestProperty("Accept", "application/xml");					
			
				try {
					JAXBContext jc;
					jc = JAXBContext.newInstance(JobApplications.class);
					InputStream xml = connection.getInputStream();	
					JobApplications ja=(JobApplications) jc.createUnmarshaller().unmarshal(xml);
					Vector<JobApplication> resultList = new Vector<JobApplication>();
					resultList.addAll(ja.getJobApplications());
					request.setAttribute("myApplications", resultList);					
					connection.disconnect();	
					for(JobApplication j:resultList){
						uri = 
							    "http://localhost:8080/FoundITServer/jobposting/"+j.getJobPostId();
							url = new URL(uri);
							connection = 
							    (HttpURLConnection) url.openConnection();
							connection.setRequestMethod("GET");
							connection.setRequestProperty("Accept", "application/xml");								
							xml = connection.getInputStream();							
							jc = JAXBContext.newInstance(JobPosting.class);							
							JobPosting jp = (JobPosting) jc.createUnmarshaller().unmarshal(xml);
							String jobid = jp.getId();
							String title = jp.getTitle();
							String companyId =  jp.getCompanyProfileId();
							System.out.println("Got job post info" +jobid+" "+title+" " +companyId);
							connection.disconnect();
							uri = 
								    "http://localhost:8080/FoundITServer/companyProfileId/"+companyId;
								url = new URL(uri);
								connection = 
								    (HttpURLConnection) url.openConnection();
								connection.setRequestMethod("GET");
								connection.setRequestProperty("Accept", "application/xml");								
								xml = connection.getInputStream();
								connection.disconnect();
								jc = JAXBContext.newInstance(CompanyProfile.class);
								CompanyProfiles cp = (CompanyProfiles) jc.createUnmarshaller().unmarshal(xml);
								System.out.println("Got company info");
								String companyName = cp.getCompanyProfiles().get(0).getName();
						ApplicationDisplay app = new ApplicationDisplay();
						app.setCompany(companyName);
						app.setJobId(jobid);
						app.setStatus(j.getStatus());
						app.setTitle(title);
						connection.disconnect();
						myApplications.add(app);
					}
					//Get job posting
					//Get company
					request.setAttribute("myApplications",myApplications);
					r = request.getRequestDispatcher("/WEB-INF/jsps/myApplications.jsp");
					r.forward(request, response);
					return;
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					System.out.println(e.toString());
					connection.disconnect();
				}
				 
		}

	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
