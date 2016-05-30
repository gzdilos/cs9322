

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
import org.xml.sax.SAXException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Vector<User> userList = new userList();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession mySession = request.getSession(false);
		UserParser u = new UserParser();
		ServletContext context = getServletContext();
		String xmlPath = context.getRealPath("/WEB-INF/userList.xml");
		File xmlFile = new File(xmlPath);
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		RequestDispatcher r = request.getRequestDispatcher("");		
			if(userList.isEmpty()){
				if(getUserList() != null){
					userList = getUserList();
				}
			}		
			if(request.getParameterMap().containsKey("action")){
				String action = request.getParameter("action");
					if(action.equals("logout")){
						mySession.invalidate();
						response.sendRedirect("home.jsp");
						return;
					}else if(action.equals("login")){
						User newLogin = new User();
						newLogin.setUsername(request.getParameter("login_username"));
						newLogin.setPassword(request.getParameter("login_password"));
						if(userList.contains(newLogin)){
							System.out.println(userList.size());
							System.out.println(userList.indexOf(newLogin));
							User thisUser = (User) userList.get(userList.indexOf(newLogin));
							System.out.println(thisUser.getId()+"  "+thisUser.getUserType());
							System.out.println("Login Success");
							mySession.setAttribute("loggedIn", true);
							mySession.setAttribute("user", thisUser);
							if(thisUser.getUserType().equals("candidate")){
								RequestDispatcher rd = request.getRequestDispatcher("/search");
								rd.forward(request, response);
								return;
							}else if(thisUser.getUserType().equals("manager")){
								RequestDispatcher rd = request.getRequestDispatcher("/manager");
								rd.forward(request, response);
								return;
							}else if(thisUser.getUserType().equals("reviewer")){
								RequestDispatcher rd = request.getRequestDispatcher("/review");
								rd.forward(request, response);
								return;
							}else{
								RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
								rd.forward(request, response);
								return;
							}
						} else if(action.equals("addReviewer")){
							User user = (User) mySession.getAttribute("user");
							String query="/";
							String uri = "/teammemberprofile";
							String username = request.getParameter("username");
							String password = request.getParameter("password");
							String professionalskills = request.getParameter("professionalskills");
							query += "username="+username;
							query += "&password="+password;
							query += "&professionalskills="+professionalskills;
							User newUser = new User();
							RestServices rs = new RestServices();
							HttpURLConnection connection = rs.doPost(query, uri,user.getUserType() );							
							//read the response
							if(connection.getResponseCode() == 200){
								try {
									JAXBContext jc;								
									InputStream xml = connection.getInputStream();		
										jc = JAXBContext.newInstance(TeamMemberProfile.class);
										System.out.println("reading xml response for user");
										TeamMemberProfile profile = 
										    (TeamMemberProfile) jc.createUnmarshaller().unmarshal(xml);
										connection.disconnect();
										newUser.setId(profile.getId());
										newUser.setPassword(password);
										newUser.setUsername(username);
										newUser.setUserType("reviewer");
										userList.add(newUser);
										updateUserList(userList);
										r = request.getRequestDispatcher("/manager?team");
										r.forward(request,response);
										return;
								}catch (JAXBException e) {
									
								}
							}else{
								System.out.println("Failed creating new team member");
							}
							
						}else{
							RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
							rd.forward(request, response);
							return;
						}
					}else if(action.equals("preregister")){
						String userType;	
						System.out.println("prereg");
						if(request.getParameterMap().containsKey("registerEmployee")){
							userType = "candidate";
							UserProfile p = new UserProfile();
							request.setAttribute("profile", p);
							request.setAttribute("userType", userType);
							request.setAttribute("email", request.getParameter("email"));
							request.setAttribute("password", request.getParameter("password"));
							r = request.getRequestDispatcher("/WEB-INF/jsps/profile.jsp");
							r.forward(request,response);
							return;
						}else if (request.getParameterMap().containsKey("registerEmployer")){
							userType = "manager";
							CompanyProfile p = new CompanyProfile();
							request.setAttribute("profile", p);
							request.setAttribute("userType", userType);
							request.setAttribute("email", request.getParameter("email"));
							request.setAttribute("password", request.getParameter("password"));
							r = request.getRequestDispatcher("/WEB-INF/jsps/profile.jsp");
							r.forward(request,response);
							return;
						} else {
							RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
							rd.forward(request, response);
							
							return;
						}
						
					}else if(action.equals("register")){	
						System.out.println("register");
						String registerUrl = null;
						String userType = request.getParameter("userType");					
						if(userType.equals("candidate")){
							registerUrl = "/userprofile";
						}else if (userType.equals("manager")){
							registerUrl = "/companyprofile";
						} else {
						}	
						
						CompanyProfile company = new CompanyProfile();
						UserProfile userProfile = new UserProfile();
						String query="/";
						
						if(userType.equals("manager")){
						    query += "name="+ request.getParameter("name");
						    query += "&address="+request.getParameter("address");
						    query += "&industrytype="+request.getParameter("industrytype");
						    query += "&description="+request.getParameter("description");
						    query += "&website="+request.getParameter("website");
						}else if(userType.equals("candidate")){
							query = "name="+ request.getParameter("name");
							query += "&currentposition="+ request.getParameter("currentPosition");
						    query += "&education="+request.getParameter("education");
						    query += "&pastexperience="+request.getParameter("pastExperience");
						    query += "&professionalskills="+request.getParameter("professionalSkills");
						    
						}
						
						User newUser = new User();					
						newUser.setUsername(request.getParameter("email"));
						newUser.setPassword(request.getParameter("password"));
						newUser.setUserType(userType);
						RestServices rs = new RestServices();
						String uri = registerUrl;
						HttpURLConnection connection = rs.doPost(query, uri, userType);
							
							//read the response
							try {
								JAXBContext jc;								
								InputStream xml = connection.getInputStream();		
								if(userType.equals("candidate")){
									jc = JAXBContext.newInstance(UserProfile.class);
									System.out.println("reading xml response for user");
									UserProfile profile = 
									    (UserProfile) jc.createUnmarshaller().unmarshal(xml);
									connection.disconnect();
									newUser.setId(profile.getId());
									mySession.setAttribute("profile", profile);
								}else if(userType.equals("manager")){
									jc = JAXBContext.newInstance(CompanyProfile.class);
									System.out.println("reading xml response for company");
									CompanyProfile profile = 
										    (CompanyProfile) jc.createUnmarshaller().unmarshal(xml);
									connection.disconnect();
									newUser.setId(profile.getId());
									mySession.setAttribute("profile", profile);
								}		
								System.out.println("Finished reading response xml");
								userList.add(newUser);
								updateUserList(userList);
								mySession.setAttribute("loggedIn", true);
								mySession.setAttribute("user", newUser);
								if(newUser.getUserType().equals("candidate")){
									RequestDispatcher rd = request.getRequestDispatcher("/search");
									rd.forward(request, response);
									return;
								}else if(newUser.getUserType().equals("manager")){
									RequestDispatcher rd = request.getRequestDispatcher("/manager");
									rd.forward(request, response);
									return;
								}else if(newUser.getUserType().equals("reviewer")){
									RequestDispatcher rd = request.getRequestDispatcher("/review");
									rd.forward(request, response);
									return;
								}else{
									RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
									rd.forward(request, response);
									return;
								}
							} catch (JAXBException e) {
								// TODO Auto-generated catch block
								System.out.println(e.toString());
							}
	
					}else if(action.equals("saveJob")){
						System.out.println(request.getParameter("jobid"));
						User thisUser =(User) mySession.getAttribute("user");
						thisUser.saveJob(request.getParameter("jobid"));
						int index = userList.indexOf(thisUser);
						userList.set(index, thisUser);
						updateUserList(userList);
					}else if(action.equals("removeJob")){
						System.out.println(request.getParameter("jobid"));
						User thisUser =(User) mySession.getAttribute("user");
						thisUser.removeJob(request.getParameter("jobid"));
						int index = userList.indexOf(thisUser);
						userList.set(index, thisUser);
						updateUserList(userList);
					}
			}
			if(request.getParameterMap().containsKey("register")){
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsps/register.jsp");
				rd.forward(request, response);
				return;
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
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
	public Vector<User> getUserList(){
		ServletContext context = getServletContext();
		String xmlPath = context.getRealPath("/WEB-INF/userList.xml");
		File xmlFile = new File(xmlPath);
		try {
			JAXBContext jc;
			jc = JAXBContext.newInstance(userList.class);
			File xml = new File(xmlPath);	
			
			 userList newList = 
			    (userList) jc.createUnmarshaller().unmarshal(xml);
			//builder = builderFactory.newDocumentBuilder();
			 return newList.getUserList();
			//Document doc = builder.parse(xmlFile);
			//u.parse(doc, userList);			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateUserList(Vector<User> userList){
		JAXBContext jc;
		try{
		ServletContext context = getServletContext();
		String xmlPath = context.getRealPath("/WEB-INF/userList.xml");
		File xmlFile = new File(xmlPath);
		
		jc = JAXBContext.newInstance(userList.class);
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		userList saveList = new userList();
		saveList.setUserList(userList);
		m.marshal(saveList, xmlFile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
