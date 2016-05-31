

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
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
		String query = "";
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
				}else{
					request.setAttribute("error", true);
				}
				
			}
			if(action.equals("addTeam")){
				User member1 = new User();
				User member2 = new User();
				User member3 = new User();
				User member4 = new User();
				User member5 = new User();
				boolean membersValid = true;			       
				Vector<User> userList =  (Vector<User>) mySession.getAttribute("userList");
				System.out.println(userList.size()+"size of userList");
				member1.setUsername(request.getParameter("username1"));
				member1.setPassword(request.getParameter("password1"));
				if(userList.contains(member1)){
					member1 = userList.get(userList.indexOf(member1));
				} else {
					membersValid = false;
				}
				
				member2.setUsername(request.getParameter("username2"));
				member2.setPassword(request.getParameter("password2"));
				if(userList.contains(member2)){
					member2 = userList.get(userList.indexOf(member2));
				} else {
					membersValid = false;
				}
				
				member3.setUsername(request.getParameter("username3"));
				member3.setPassword(request.getParameter("password3"));
				if(userList.contains(member2)){
					member3 = userList.get(userList.indexOf(member3));
				} else {
					membersValid = false;
				}
				
				member4.setUsername(request.getParameter("username4"));
				member4.setPassword(request.getParameter("password4"));
				if(userList.contains(member2)){
					member4 = userList.get(userList.indexOf(member4));
				} else {
					membersValid = false;
				}
				
				member5.setUsername(request.getParameter("username5"));
				member5.setPassword(request.getParameter("password5"));
				if(userList.contains(member2)){
					member5 = userList.get(userList.indexOf(member5));
				} else {
					membersValid = false;
				}
				
				if(membersValid){
					uri = "/hiringteam";
					RestServices rs = new RestServices();
					query += "companyprofileid="+user.getId();
					query +="&member1id="+member1.getId();
					query +="&member2id="+member2.getId();
					query +="&member3id="+member3.getId();
					query +="&member4id="+member4.getId();
					query +="&member5id="+member5.getId();
					HttpURLConnection connection = rs.doPost(query, uri, user.getUserType());
					if(connection.getResponseCode() == 200 || connection.getResponseCode() == 201 ){
							rd = request.getRequestDispatcher("/Manager/?team=");
							rd.forward(request, response);
							return;
										
					}else{
						request.setAttribute("error", true);
					}
					
				}
				rd = request.getRequestDispatcher("/Manager/?team=");
				rd.forward(request, response);
				return;
			}
		}
		if(request.getParameterMap().containsKey("add")){
			rd = request.getRequestDispatcher("/WEB-INF/jsps/addJob.jsp");
			rd.forward(request, response);
			return;
		}
		if(request.getParameterMap().containsKey("team")){
			uri = "/hiringteam";
			RestServices rs = new RestServices();			
			query = "/"+user.getId();
			HttpURLConnection connection = rs.doGet(query, uri, user.getUserType(),true);
			if(connection.getResponseCode() == 200){
				JAXBContext jc;								
				InputStream xml = connection.getInputStream();
					
			}else{
				System.out.println("Cant find hiring team");
			}
			
			rd = request.getRequestDispatcher("/WEB-INF/jsps/team.jsp");
			rd.forward(request, response);
			return;
		}if(request.getParameterMap().containsKey("addTeam")){
			request.setAttribute("action", "addTeam");
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
