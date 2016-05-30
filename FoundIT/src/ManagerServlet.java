

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

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
 * Servlet implementation class ManagerServlet
 */
@WebServlet("/manager")
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession mySession = request.getSession(false);
		User user = (User) mySession.getAttribute("user");
		RequestDispatcher rd = request.getRequestDispatcher("");
		String uri = "";
		String query = "/";
		if(request.getParameterMap().containsKey("action")){
			String action = request.getParameter("action");
			if(action.equals("add")){
				uri = "/jobposting";
				RestServices rs = new RestServices();
				query += "title="+request.getParameter("title");
				query +="&description="+request.getParameter("description");
				query +="&positiontype="+request.getParameter("positiontype");
				query +="&companyprofileid="+user.getId();
				query +="&desiredskills="+request.getParameter("desiredskills");
				query +="&salarylevel="+request.getParameter("salarylevel");
				query +="&location="+request.getParameter("location");
				HttpURLConnection connection = rs.doPost(query, uri, user.getUserType());
				if(connection.getResponseCode() == 200){
					JAXBContext jc;								
					InputStream xml = connection.getInputStream();
					try {
						jc = JAXBContext.newInstance(JobPosting.class);
						JobPosting newJob = 
							    (JobPosting) jc.createUnmarshaller().unmarshal(xml);
						connection.disconnect();
					} catch (JAXBException e) {
						System.out.println("Job Creation Failed");
					}				
				}
				
			}
		}
		if(request.getParameterMap().containsKey("add")){
			rd = request.getRequestDispatcher("/WEB-INF/jsps/addJob.jsp");
			rd.forward(request, response);
			return;
		}
		if(request.getParameterMap().containsKey("team")){
			uri = "/hiringteam/";
			RestServices rs = new RestServices();
			query = "/";
			query = user.getId();
			HttpURLConnection connection = rs.doPost(query, uri, user.getUserType());
			if(connection.getResponseCode() == 200){
				JAXBContext jc;								
				InputStream xml = connection.getInputStream();
				try {
					jc = JAXBContext.newInstance(JobPosting.class);
					HiringTeam team = 
						    (HiringTeam) jc.createUnmarshaller().unmarshal(xml);
					connection.disconnect();
					request.setAttribute("hiringTeam", team);
					rd = request.getRequestDispatcher("/WEB-INF/jsps/team.jsp");
					rd.forward(request, response);
					return;
				} catch (JAXBException e) {
					System.out.println("No hiring Teams");
				}				
			}
			
			rd = request.getRequestDispatcher("/WEB-INF/jsps/team.jsp");
			rd.forward(request, response);
			return;
		}if(request.getParameterMap().containsKey("addTeam")){
			//post
			rd = request.getRequestDispatcher("/WEB-INF/jsps/modifyteam.jsp");
			rd.forward(request, response);
			return;
		}if(request.getParameterMap().containsKey("changeTeam")){
			//put
			rd = request.getRequestDispatcher("/WEB-INF/jsps/modifyteamteam.jsp");
			rd.forward(request, response);
			return;
		}if(request.getParameterMap().containsKey("removeTeam")){
			//delete
			rd.forward(request, response);
			return;
		}if(request.getParameterMap().containsKey("addMember")){
			request.setAttribute("action", "addMember");
			rd = request.getRequestDispatcher("/WEB-INF/jsps/modifyteam.jsp");
			rd.forward(request, response);
			return;
		}
		uri = "/jobposting/all";
		//uri = "/jobposting/search?companyid="+user.getId();
		RestServices rs = new RestServices();
		HttpURLConnection connection = rs.doGet("", uri, user.getUserType(),false);		
		InputStream xml = connection.getInputStream();	
		try {
			JAXBContext jc = JAXBContext.newInstance(JobPostings.class);
			JobPostings jobsList;		
			jobsList = (JobPostings) jc.createUnmarshaller().unmarshal(xml);
			connection.disconnect();
			request.setAttribute("jobsList", jobsList.getJobPostings());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		rd = request.getRequestDispatcher("/WEB-INF/jsps/manage.jsp");
		rd.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
