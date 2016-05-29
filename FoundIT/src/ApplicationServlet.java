

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Applicationservlet
 */
@WebServlet("/apply")
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
