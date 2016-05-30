

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class JobAlertsServlet
 */
@WebServlet("/jobalerts")
public class JobAlertsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobAlertsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession mySession = request.getSession(false);
		RequestDispatcher r = request.getRequestDispatcher("");	
		String keyword = request.getParameter("keyword");
		String sort_by = request.getParameter("sort_by");
		String email = request.getParameter("email");
		System.out.println("Arrived at job alerts");
		if (keyword != null) {
			System.out.println(keyword);
			System.out.println(sort_by);
			System.out.println(email);
			if (sort_by == null) {
				System.out.println("no sort");
				String uri = "http://localhost:8080/FoundITServer/jobalerts?keyword=" + keyword
						+ "&email=" + email; 
				URL url = new URL(uri);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				
				connection.setRequestMethod("GET");
				connection.setRequestProperty("Content-Type", "application/xml");
				connection.setRequestProperty("Accept", "application/xml");
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setRequestProperty("SecurityKey", "i-am-foundit");
				connection.setRequestProperty("ShortKey", "app-reviewer");
				
				System.out.println(connection.getInputStream());
				r = getServletContext().getRequestDispatcher( "/WEB-INF/jsps/confirmationAlert.jsp");
				r.forward(request, response);
				return;
			} else {
				System.out.println("sort");
				String uri = "http://localhost:8080/FoundITServer/jobalerts?keyword=" + keyword
						+ "&sort_by=" + sort_by + "&email=" + email; 
				URL url = new URL(uri);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				
				connection.setRequestMethod("POST");
				connection.setRequestProperty("Content-Type", "application/xml");
				connection.setRequestProperty("Accept", "application/xml");
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setRequestProperty("SecurityKey", "i-am-foundit");
				connection.setRequestProperty("ShortKey", "app-reviewer");
				
				System.out.println(connection.getInputStream());
				r = getServletContext().getRequestDispatcher( "/WEB-INF/jsps/confirmationAlert.jsp");
				r.forward(request, response);
				return;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
