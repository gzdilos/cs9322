

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
		if(request.getParameterMap().containsKey("register")){
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsps/register.jsp");
			rd.forward(request, response);
			return;
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
						//Check if user is reviewer
						System.out.println(userList.size());
						System.out.println(userList.indexOf(newLogin));
						User thisUser = (User) userList.get(userList.indexOf(newLogin));
						
						if(thisUser.userType.contentEquals("Reviewer")) {
							String id = getReviewerID(thisUser.username, thisUser.password);
							
							if (id == null) {
								response.sendRedirect("home.jsp");
								return;
							} else {
								thisUser.setId(id);
								mySession.setAttribute("loggedIn", true);
								mySession.setAttribute("user", thisUser);
							}
						}
						System.out.println("Login Success");
						mySession.setAttribute("loggedIn", true);
						mySession.setAttribute("user", thisUser);
						response.sendRedirect("home.jsp");
						return;
					} else{
						System.out.println("Checking if user is reviewer");
						//Check if user is reviewer
						String id = getReviewerID(newLogin.username, newLogin.password);
						
						if (id == null) {
							response.sendRedirect("home.jsp");
							return;
						} else {
							newLogin.setId(id);
							mySession.setAttribute("loggedIn", true);
							mySession.setAttribute("user", newLogin);
						}
						newLogin.setUserType("Reviewer");
						System.out.println("Login Success");
						mySession.setAttribute("loggedIn", true);
						mySession.setAttribute("user", newLogin);
						response.sendRedirect("home.jsp");
						return;
						//System.out.println("User not found");
					}
				}else if(action.equals("register")){
					System.out.println("Registering");
					FileWriter fw = new FileWriter(xmlPath);
					User newUser = new User();
					
					newUser.setUsername(request.getParameter("email"));
					newUser.setPassword(request.getParameter("password"));
					String uri = 
						    "http://localhost:8080/FoundITServer/userprofile/";
						URL url = new URL(uri);
						HttpURLConnection connection = 
						    (HttpURLConnection) url.openConnection();
						connection.setRequestMethod("POST");
						connection.setDoOutput(true);
						connection.setDoInput(true);
						connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");	
						connection.setRequestProperty("Accept", "application/xml");		
						OutputStream os = connection.getOutputStream();
						os.write(("").getBytes());
						JAXBContext jc;
						try {
							
							jc = JAXBContext.newInstance(UserProfile.class);
							InputStream xml = connection.getInputStream();		
							
							UserProfile profile = 
							    (UserProfile) jc.createUnmarshaller().unmarshal(xml);
							connection.disconnect();
							newUser.setId(profile.getId());
							userList.add(newUser);
							updateUserList(userList);
							mySession.setAttribute("loggedIn", true);
							mySession.setAttribute("user", newUser);
							mySession.setAttribute("profile", profile);
							response.sendRedirect("profile");
							return;
						} catch (JAXBException e) {
							// TODO Auto-generated catch block
							System.out.println(e.toString());
						}

				}else if(action.equals("saveJob")){
					System.out.println("Hello");
					System.out.println(request.getParameter("jobid"));
					User thisUser =(User) mySession.getAttribute("user");
					thisUser.saveJob(request.getParameter("jobid"));
					int index = userList.indexOf(thisUser);
					userList.set(index, thisUser);
					updateUserList(userList);
				}
		}
		


		return;

	}

	//Need to call the server to check reviewer id
	private String getReviewerID(String username, String password) {
		System.out.println("Checking server with " + username + " " + password);
		String uri = "http://localhost:8080/FoundITServer/teammemberprofile?username="+ username+"&password=" + password + "&professionalskills=s";
		URL url;
		HttpURLConnection connection;
		try {
			url = new URL(uri);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");	
			connection.setRequestProperty("Accept", "application/xml");		
			connection.setRequestMethod("POST");
			connection.setRequestProperty("SecurityKey", "i-am-foundit");
			connection.setRequestProperty("ShortKey", "app-reviewer");
			
			//BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			return parseReviewXML(connection.getInputStream());
			
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return null;
	}

	/*
	 * 
	 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
		<teamMemberProfile href="http://localhost:8080/FoundITServer/teammemberprofile/6" rel="teammemberprofile">
		    <id>6</id>
		    <password>review1</password>
		    <professionalSkills>s</professionalSkills>
		    <username>review1</username>
		</teamMemberProfile>
	 */
	private String parseReviewXML(InputStream inputStream) {
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
			
			if (inputStream == null) {
				doc = dBuilder.parse(filetemp);
			} else {
				doc = dBuilder.parse(inputStream);
			}
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("teamMemberProfile");
	        
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	        	Node nNode = nList.item(temp);
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	            	
	            	Element eElement = (Element) nNode;
	            	
	            	System.out.println(eElement.getElementsByTagName("id").item(0).getTextContent());
	            	System.out.println(eElement.getElementsByTagName("password").item(0).getTextContent());
	            	System.out.println(eElement.getElementsByTagName("professionalSkills").item(0).getTextContent());
	            	System.out.println(eElement.getElementsByTagName("username").item(0).getTextContent());
	            	
	            	return eElement.getElementsByTagName("id").item(0).getTextContent();
	            }
	         }
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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
