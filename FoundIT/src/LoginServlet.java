

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
		UserHandler u = new UserHandler();
		ServletContext context = getServletContext();
		String xmlPath = context.getRealPath("/WEB-INF/userList.xml");
		File xmlFile = new File(xmlPath);
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Vector<User> userList = new Vector<User>();
		if(userList.isEmpty()){
			try {
				builder = builderFactory.newDocumentBuilder();	
				System.out.println("derp");
				Document doc = builder.parse(xmlFile);
				u.parse(doc, userList);
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(userList.size());
		User newLogin = new User();
		newLogin.setUsername(request.getParameter("login_username"));
		newLogin.setPassword(request.getParameter("login_password"));
		if(userList.contains(newLogin)){
			System.out.println("Login Success");
			mySession.setAttribute("loggedIn", true);
		} else{
			System.out.println("User not found");
		}
		response.sendRedirect("home.jsp");
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
