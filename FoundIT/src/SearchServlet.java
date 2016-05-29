

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher r = request.getRequestDispatcher("");	
		if(request.getParameterMap().containsKey("search_keyword")){			
			Vector<JobPosting> resultList = new Vector<JobPosting>();
			String keyword = request.getParameter("search_keyword");
			String uri = 
				    "http://localhost:8080/FoundITServer/jobposting/search?keyword="+keyword;
				URL url = new URL(uri);
				HttpURLConnection connection = 
				    (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setRequestProperty("Accept", "application/xml");					
			
				try {
					
					JAXBContext jc;
					jc = JAXBContext.newInstance(JobPostings.class);
					InputStream xml = connection.getInputStream();		
					
					JobPostings jp = 
					    (JobPostings) jc.createUnmarshaller().unmarshal(xml);
					resultList.addAll(jp.getJobPosting());
					System.out.println(resultList.size());
					request.setAttribute("results", resultList);
					connection.disconnect();
					
					r = request.getRequestDispatcher("/WEB-INF/jsps/results.jsp");
					r.forward(request, response);
					return;
					
				
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					System.out.println(e.toString());
					connection.disconnect();
				}
				
		}
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
