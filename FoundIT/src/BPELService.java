import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.w3c.dom.Element;


public class BPELService {

	public BPELService() {
		
	}
	
	public int callBpel(String address, String fullname, String license) {
		int status = 0;
		
		try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            String url = "http://localhost:6060/ode/processes/ValidationAddressServiceProcess";
            SOAPMessage soapResponse = null;
            
            if (license != null) {
            	url = "http://localhost:6060/ode/processes/ValidationLicenseServiceProcess";
            	soapResponse = soapConnection.call(createSOAPLicRequest(license, fullname), url);
            } else {
            	soapResponse = soapConnection.call(createSOAPAddrRequest(address, fullname), url);
            }
            
            // Process the SOAP Response
            status = Integer.parseInt(getSOAPResponse(soapResponse));

            soapConnection.close();
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
        }
		
		return status; 
	}
	
	private SOAPMessage createSOAPAddrRequest(String address, String fullname2) throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        
        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("emp1", "http://employeevalidaddr.soacourse.unsw.edu.au");
        
        /*
         * <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:emp="http://employeevalidaddr.soacourse.unsw.edu.au">
			   <soapenv:Header/>
			   <soapenv:Body>
			      <emp:validationAddressRequest>
			         <address>test</address>
			         <fullName>test</fullName>
			      </emp:validationAddressRequest>
			   </soapenv:Body>
			</soapenv:Envelope>
         */
        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("validationAddressRequest", "emp1");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("address");
        soapBodyElem1.addTextNode(address);
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("fullName");
        soapBodyElem2.addTextNode(fullname2);
        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
	}

	private SOAPMessage createSOAPLicRequest(String license, String fullname2) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        
        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("emp", "http://employeevalid.soacourse.unsw.edu.au");
        envelope.addNamespaceDeclaration("emp1", "http://employeevalidlic.soacourse.unsw.edu.au");

        /*<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:emp="http://employeevalid.soacourse.unsw.edu.au">
		   <soapenv:Header/>
		   <soapenv:Body>
		      <emp1:validationLicenseRequest xmlns:emp1="http://employeevalidlic.soacourse.unsw.edu.au">
		         <licenseNumber>asdas</licenseNumber>
		         <fullName>asdasd</fullName>
		      </emp1:validationLicenseRequest>
		   </soapenv:Body>
		</soapenv:Envelope>
		*/
        
        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("validationLicenseRequest", "emp1");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("licenseNumber");
        soapBodyElem1.addTextNode(license);
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("fullName");
        soapBodyElem2.addTextNode(fullname2);
        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }

    /**
     * Method used to print the SOAP Response
     */
    private String getSOAPResponse(SOAPMessage soapResponse) throws Exception {
        SOAPPart s = soapResponse.getSOAPPart();
        Element e = s.getDocumentElement();
        String status = e.getElementsByTagName("status").item(0).getTextContent();
        
        return status;
    }
}
