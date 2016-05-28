

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

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


/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession mySession = request.getSession(false);
		RequestDispatcher r = request.getRequestDispatcher("");	
		User u = (User) mySession.getAttribute("user");
		if(request.getParameterMap().containsKey("action")){
			String action = request.getParameter("action");
			if(action.equals("update")){
				System.out.println("Updating");
				String uri = 
					    "http://localhost:8080/FoundITServer/userprofile";
				URL url = new URL(uri);
				HttpURLConnection connection = 
				    (HttpURLConnection) url.openConnection();
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
				try {
					JAXBContext jc;
					jc = JAXBContext.newInstance(UserProfile.class);
					Marshaller m = jc.createMarshaller();
					//m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
					m.marshal(user,os);
					os.flush();
					connection.getResponseCode();
			        connection.disconnect();					
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					connection.disconnect();
				}
				
				
			}
		}
		if(u != null){
			String uri = 
			    "http://localhost:8080/FoundITServer/userprofile/"+u.getId();
			URL url = new URL(uri);
			HttpURLConnection connection = 
			    (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");					
		
			try {
				JAXBContext jc;
				jc = JAXBContext.newInstance(UserProfile.class);
				InputStream xml = connection.getInputStream();		
				
				UserProfile profile = 
				    (UserProfile) jc.createUnmarshaller().unmarshal(xml);
				connection.disconnect();
				mySession.setAttribute("profile", profile);
				r = getServletContext().getRequestDispatcher( "/WEB-INF/jsps/profile.jsp");
				r.forward(request, response);
				return;
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
				connection.disconnect();
			}
			 
		
		}
			
		r = getServletContext().getRequestDispatcher( "/WEB-INF/jsps/profile.jsp");
		r.forward(request, response);
		return;
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
