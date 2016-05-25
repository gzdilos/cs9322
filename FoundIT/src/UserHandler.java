import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class UserHandler {
	public void parse(Document doc,Vector<User> userList){
		NodeList userNodes = doc.getElementsByTagName("userList");
		System.out.println(userNodes.getLength());
		for(int i=0;i<userNodes.getLength();i++){
			User u = new User();	
			Node n = userNodes.item(i);
			NodeList userElements = n.getChildNodes();
			for(int j = 0; j < userElements.getLength();j++){
				Node e = userElements.item(j);
				if(e.getNodeName().equalsIgnoreCase("username")){
					u.setUsername(e.getTextContent());					
				}
				if(e.getNodeName().equalsIgnoreCase("password")){
					u.setPassword(e.getTextContent());
				}
			}
			userList.addElement(u);
			
		}
	}
}
