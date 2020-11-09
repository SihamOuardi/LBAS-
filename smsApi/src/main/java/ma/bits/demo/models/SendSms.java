package ma.bits.demo.models;





import java.io.BufferedReader;


import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import lombok.extern.log4j.Log4j2;



@Log4j2
public class SendSms {

	//private  String url ;
	// private String xml;
	
	
	
	
	 public String	sendSmsRequestWirePick(String reqUrl,String urlParameters){
		 try {
			 
			 byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8 );
			 int postDataLength = postData.length;
			
			 URL url = new URL( reqUrl );
			 HttpURLConnection conn= (HttpURLConnection) url.openConnection();           
			 conn.setDoOutput(true);
			 conn.setInstanceFollowRedirects(false);
			 conn.setRequestMethod("POST");
			 conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
			 conn.setRequestProperty("charset", "utf-8");
			 conn.setRequestProperty("Content-Length", Integer.toString(postDataLength ));
			 conn.setUseCaches(false);
			 try(DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
			    wr.write( postData );
			    wr.flush();
				 wr.close();
				 String responseStatus = conn.getResponseMessage();
				 System.out.println(responseStatus);
				 BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				 String inputLine;
				 StringBuffer response = new StringBuffer();
				 while ((inputLine = in.readLine()) != null) {
				 response.append(inputLine);
				 }
				 in.close();
				 log.info("WIREPICK STRING RESPONSE :" + response.toString());
				 String reponse =response.toString();
				 return  reponse;
			 }
			 
			 		
		/*	 URL obj = new URL(url);
			 HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			 con.setRequestMethod("POST");
			 con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			 con.setDoOutput(true);
			 DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			 wr.writeBytes(xml);
			 wr.flush();
			 wr.close();
			 String responseStatus = con.getResponseMessage();
			 System.out.println(responseStatus);
			 BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			 String inputLine;
			 StringBuffer response = new StringBuffer();
			 while ((inputLine = in.readLine()) != null) {
			 response.append(inputLine);
			 }
			 in.close();
			 System.out.println("response:" + response.toString());
			 String reponse =response.toString();
			 return  reponse;*/
		
		 
		 } catch (Exception e) {
				log.error("sendSmsRequestWirePick Failed with ERROR : ",e);

			 e.getStackTrace();
			 return null;		 
			 }

		}
	 
	 
	 
	 
	 
	
 public String	sendSmsRequest(String reqUrl,String jsonInputString)    //,String urlParameters)
 {
	 try {
		 
	//	 byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8 );
	//	 int postDataLength = postData.length;
		 URL url = new URL( reqUrl );
		 HttpURLConnection conn= (HttpURLConnection) url.openConnection();           
		 conn.setDoOutput(true);
		 conn.setInstanceFollowRedirects(false);
		 conn.setRequestMethod("POST");
		 conn.setRequestProperty("Content-Type", "application/json");
		 
		 try(OutputStream os = conn.getOutputStream()) {
			    byte[] input = jsonInputString.getBytes("utf-8");
			    os.write(input, 0, input.length);           
			}
		 
		 try(BufferedReader br = new BufferedReader(
				  new InputStreamReader(conn.getInputStream(), "utf-8"))) {
				    StringBuilder response = new StringBuilder();
				    String responseLine = null;
				    while ((responseLine = br.readLine()) != null) {
				        response.append(responseLine.trim());
				    }
					 log.info("UNPONTO STRING RESPONSE :" + response.toString());

				    return response.toString();
				}
		
	 
	 } catch (Exception e) {
		log.error("sendSmsRequest (UNPONTU) Failed with ERROR : ",e);
		 e.printStackTrace();
		 return null;		 
		 }

	}
 
	
 
/* public String getkra(String url,String xml) {
	 
	 try {
		 URL obj = new URL(url);
		 HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		 con.setRequestMethod("POST");
		 con.setRequestProperty("Content-Type", "application/json");		 con.setDoOutput(true);
		 DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		 wr.writeBytes(xml);
		 wr.flush();
		 wr.close();
		 String responseStatus = con.getResponseMessage();
		 System.out.println(responseStatus);
		 BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		 String inputLine;
		 StringBuffer response = new StringBuffer();
		 while ((inputLine = in.readLine()) != null) {
		 response.append(inputLine);
		 }
		 in.close();
		 System.out.println("response:" + response.toString());
		 String reponse =response.toString();
		 return  reponse;
	 } catch (Exception e) {
		 System.out.println(e);
		 return null;		 
		 }
	 
	 
 }
	*/
 
 
 
 public WirepickSmsResponse	CovertxmlToSmsResponseWirePick(String xmlString){
	
	 JAXBContext jaxbContext;
	 try
	 {    
 		 xmlString	= xmlString.substring(38);
 		 //replaceAll("<?xml version=\"1.0\" encoding=\"utf-8\"?>", " ");
		 xmlString= xmlString.replaceAll("<messages>"," ");
		 xmlString= xmlString.replaceAll("</messages>","");
		 log.info("XML String To Convert To Object : "+xmlString);	     jaxbContext = JAXBContext.newInstance(WirepickSmsResponse.class);             	  
	     Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();	 
	     //SmsResponse nedresp= (SmsResponseWi) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));	      
	     WirepickSmsResponse wirepickSmsResponse =(WirepickSmsResponse) jaxbUnmarshaller.unmarshal(new StringReader(xmlString)); 
	     log.info("wirepickSmsResponse = "+wirepickSmsResponse);
	     return wirepickSmsResponse;
	 }
	 catch (JAXBException e)
	 {
		 log.error("CovertxmlToSmsResponseWirePick Failed With ERROR :",e);
	     e.printStackTrace();
	    
	     return null;
	 }	
	}
 
 
 
 
 
 
 
	
	
	
	 
	 
	 
	
}
