import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RestServices {
	String addr = "http://localhost:8080/FoundITServer";
	
	public HttpURLConnection doGet(String query,String uri,String userType,boolean doQuery) throws IOException{	
			String urlString = addr+uri;
			if(doQuery){
				urlString += query;
			}
			URL url = new URL(urlString);
			HttpURLConnection connection = 
			    (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			System.out.println(connection.getRequestMethod());
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Accept", "application/xml");
			connection.setRequestProperty("SecurityKey", "i-am-foundit");
			connection.setRequestProperty("ShortKey", "app-"+userType);
			if(doQuery){
				/**
				DataOutputStream os = new DataOutputStream(connection.getOutputStream());
				os.writeBytes(query);
				os.flush();
				os.close();
				**/
				
			}
		return connection;
		
	}
	public HttpURLConnection doPost(String query,String uri,String userType) throws IOException{
			String urlString = addr+uri;
			URL url = new URL(urlString);
			HttpURLConnection connection = 
			    (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			System.out.println("doing get");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");	
			connection.setRequestProperty("Accept", "application/xml");
			connection.setRequestProperty("SecurityKey", "i-am-foundit");
			connection.setRequestProperty("ShortKey", "app-"+userType);
			DataOutputStream os = new DataOutputStream(connection.getOutputStream());
			os.writeBytes(query);
			os.flush();
			os.close();
			return connection;
		
	}
	
	public InputStream doPut(String xml){
		return null;
		
	}
	public HttpURLConnection doDelete(String query,String uri,String userType) throws IOException{
		String urlString = addr+uri;
		URL url = new URL(urlString);
		HttpURLConnection connection = 
		    (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("DELETE");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");	
		connection.setRequestProperty("Accept", "application/xml");
		connection.setRequestProperty("SecurityKey", "i-am-foundit");
		connection.setRequestProperty("ShortKey", "app-"+userType);
		DataOutputStream os = new DataOutputStream(connection.getOutputStream());
		os.writeBytes(query);
		os.flush();
		os.close();
		return connection;
		
	}
}
