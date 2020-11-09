package ma.bits.demo.metier;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

import java.text.SimpleDateFormat;
import java.util.Date;

//import com.google.gson.Gson;


import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import ma.bits.demo.dao.EpsSmsLogRepository;
import ma.bits.demo.entities.EpsSmsLog;
import ma.bits.demo.models.SecurityConstants;
import ma.bits.demo.models.SendSms;
import ma.bits.demo.models.SmsCoreRequest;
import ma.bits.demo.models.SmsResponse;
import ma.bits.demo.models.WirepickSmsResponse;

@Service
@Log4j2
public class SmsMetier {
	
   @Async
	public Integer sendAsysnchronusSms(EpsSmsLog smsLog,SmsConfigData config,EpsSmsLogRepository smsRepository){
	   log.info("*********Start sendAsysnchronusSms*********");
		int returnInteger=-1;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss.SSS");
		//System.out.println(formattedDate);
		
		log.info(Thread.currentThread().getName()+" "+smsLog+" "+sdf.format(new Date()));
		
		 if(smsRepository==null) {
			 log.error("EpsSmsLogRepository smsRepository Is Null,the dynamique instance has not been created !");
		 }
		EpsSmsLog epsSmsLog= smsRepository.findOneById(smsLog.getSmsTs(), smsLog.getMsisdn());
		
		if(epsSmsLog==null)
			log.error("sendAsysnchronusSms Faild with ERROR :Cannot found Sms Record in dataBase With timeStamp = "+smsLog.getSmsTs());
		
		epsSmsLog.setSmsStatus('O'); // MAW20200507: O=Ongoing. meaning that the sms sending is ongoing.  So that the main thread won't read it again
		smsRepository.save(epsSmsLog); // MAW20200507
		

		try { 
			
	    	/* siham 10/15/2020
	    	 *  SmsResponse  response=callSmsApi(smsLog,config) ;

	    	if(response ==null) {	  
	 			 log.error("###### UNPONTU SMS sending  RESPONSE IS NULL,");
	 			 //System.out.println("########################## Response UNPONTU Nulllll#################");
	 		  }
	 		 if(response !=null) {
	 			//System.out.println("############ Response Deywuro ####### ="+response);
	 			log.info("UNPONTU Sending SMS   RESPONSE IS : "+response);
	 		 }	 			
	 		if(response !=null && response.getCode()==0) {  
	 				epsSmsLog.setApiRespCode(Integer.toString(response.getCode()));
	 				epsSmsLog.setApiRespMsg(response.getMessage());
	 				epsSmsLog.setSmsStatus('S');
	 				smsRepository.save(epsSmsLog);
	 				returnInteger=0;   
	 	            }	 				 				
	 			else {
	 				*/
			
	 			WirepickSmsResponse respWirePick= callWirePickSmsApi(smsLog,config.getClientWirePick(),config.getPasswordWirpick(),config);
	 			
	 			  	 if(respWirePick !=null) {
	 		 			log.info("WIREPICK Sending SMS   RESPONSE IS : "+respWirePick);
	 						 epsSmsLog.setApiRespCode(respWirePick.getMsgid());
	 					    	epsSmsLog.setApiRespMsg(respWirePick.getStatus());
	 					 }
	 			  	if(respWirePick ==null) {
	 		 			 log.error("######WIREPICK SMS sending  RESPONSE IS NULL,");
	 		 			 
	 		 			epsSmsLog.setSmsStatus('U');
	 		 			smsRepository.save(epsSmsLog); 
	 			  	}
	 			 
	 			  	else if(respWirePick !=null && respWirePick.getStatus().equals("Accepted")) {
	 			  	   epsSmsLog.setSmsStatus('S');
	 			  	   smsRepository.save(epsSmsLog); 
	 			  	   returnInteger=0;
	 			  	}
	 			  	else if(respWirePick !=null && !respWirePick.getStatus().equals("Accepted")) {
	 			  		epsSmsLog.setSmsStatus('U');
	 			   	   smsRepository.save(epsSmsLog); 
	 			   	   
	 			  	}
	 		    
	 		       //}	
  	
		}
  	catch (Exception e) {		    		
  		log.error(e);
  		e.printStackTrace();
  	}
		log.info("SendAsysnchronusSms Return Integer for SMS timeStamp :"+epsSmsLog.getSmsTs()+"  is :"+new Integer(returnInteger));
		 log.info("*********END sendAsysnchronusSms*********");
		return new Integer(returnInteger);
  	}
	
	
   


	public SmsResponse callSmsApi(EpsSmsLog smsLog,SmsConfigData config) {
		/*System.out.println(usernameSms);
		System.out.println(passwordSms);
		System.out.println(reqUrlSms);*/
		//usernameSms="G-MONEY";
		//passwordSms="gmoney_pss_123";
	  //  reqUrlSms="https://deywuro.com/api/sms";
		 SmsCoreRequest reqSms=new SmsCoreRequest(config.getUsernameSms(),config.getPasswordSms(),"GCB BANK",smsLog.getMsisdn(), smsLog.getSmsText());		   
		
		 log.info("Start Call UNPONTU SMS Service ");
		 //Message To send :"+reqSms.getMessage());
		 log.info("Message To send :"+reqSms.getMessage().replace("\n", "\\n")); 
		// reqSms.setMessage(reqSms.getMessage().replace("\n", "\\n"));
		 
		 String json="{"
					+ "\"username\":\""+reqSms.getUsername().trim()+"\","
				 		
				  +"\"password\": \""+reqSms.getPassword()+"\","
				  +"\"source\": \""+reqSms.getSource()+"\","
				  +"\"destination\": \""+reqSms.getDestination()+"\","
		          +"\"message\": \""+reqSms.getMessage().replace("\n", "\\n")+"\"}";
			
			SendSms sms=new SendSms();
			log.info("UNPONTO Service Url :reqUrlSms"+config.getReqUrlSms());
			log.info("Sms Request Body JSON :  ="+json);
			String resp=sms.sendSmsRequest(config.getReqUrlSms(), json);
			
			Gson gson=new Gson();
			SmsResponse smsResp=null;
			if(resp!=null && !resp.equals(""))
			  smsResp =gson.fromJson(resp, SmsResponse.class);			
		    return smsResp;				
	}	
	
	
	public WirepickSmsResponse callWirePickSmsApi(EpsSmsLog smsLog,String client,String password,SmsConfigData config){
		
		log.info("Start Call WIREPICK SMS Service ");
		String urlParameters  = "username="+client+"&password="+password+"&to="+smsLog.getMsisdn()+"&text="+smsLog.getSmsText();	
		log.info("WirePick Url Parametres :"+urlParameters);
		SendSms sms=new SendSms();
		String wirepickResponseString=sms.sendSmsRequestWirePick(config.getReqUrlSmsWirepick(), urlParameters);
		log.info("String Response From WIREPICK :"+wirepickResponseString);
		WirepickSmsResponse resp=null ;
		if(wirepickResponseString !=null)
		  resp =sms.CovertxmlToSmsResponseWirePick(wirepickResponseString);
		return resp;
	}
	
	//Secureproject / 3dsec2020*
	//siham exmple---> Http://192.168.16.9:14000/cgi-bin/sendsms?username=utilisateur&password=mot de passe&to=numeroDestinataireAvecIndicatif&text=message
	
	
	
/*	
	public List<Integer> sendEmailsAsychrone(List<EpsSmsLog> smss,SmsConfigData config,EpsSmsLogRepository smsRepository){

        List<CompletableFuture<Integer>> futures =
        		smss.stream()
                          .map(sms -> sendOneSms(sms,config,smsRepository))
                          .collect(Collectors.toList());

        List<Integer> result =
                futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList());

        return result;
    }

	

	
	
  @Async
   public CompletableFuture<Integer> sendOneSms(EpsSmsLog sms,SmsConfigData config,EpsSmsLogRepository smsRepository){
	   
		//Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss.SSS");	   
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
            	//EmailModel emailModel=new EmailModel(config,emailSender);
                final Integer integerResp = sendSms(sms, config, smsRepository);
                  
                return integerResp;
            }
        });

        return future;

    }
*/
	

	/*
	public Integer sendSms(EpsSmsLog smsLog,SmsConfigData config,EpsSmsLogRepository smsRepository){
		int returnInteger=-1;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss.SSS");
		//System.out.println(formattedDate);
		log.info(Thread.currentThread().getName()+" "+smsLog+" "+sdf.format(new Date()));
		
		 if(smsRepository==null)
			   System.out.println("ha probleme fin kkaaayyyynnnnn");
		EpsSmsLog epsSmsLog= smsRepository.findOneById(smsLog.getSmsTs(), smsLog.getMsisdn());
		if(epsSmsLog==null)
			System.out.println("******mochkila search sms in database !!!");
		else System.out.println();
		try {
		WirepickSmsResponse respWirePick= callWirePickSmsApi(smsLog,config.getClientWirePick(),config.getPasswordWirpick(),config);
  	 if(respWirePick !=null) {
			 System.out.println("############ Response Wirepick ####### ="+respWirePick);
			 epsSmsLog.setApiRespCode(respWirePick.getMsgid());
		    	epsSmsLog.setApiRespMsg(respWirePick.getStatus());
		 }
  	if(respWirePick ==null)
		System.out.println("########################## Response wirepick Nulllll#################");
 
  	else if(respWirePick !=null && respWirePick.getStatus().equals("Accepted")) {
  	   epsSmsLog.setSmsStatus('S');
  	   smsRepository.save(epsSmsLog); 
  	   returnInteger=0;
  	}
		}
  	catch (Exception e) {		    		
  		e.getStackTrace();
			e.fillInStackTrace();
			if(returnInteger !=0) {
		    	   SmsResponse  response=  callSmsApi(smsLog,config) ;
				     
		 		  if(response ==null) {
		 			  
		 			  System.out.println("########################## Response UNPONTU Nulllll#################");
		 		  }
		 		 if(response !=null) {
		 			 System.out.println("############ Response Deywuro ####### ="+response);
		 		 }
		 			
		 			if(response !=null && response.getCode()==0) {  
		 				epsSmsLog.setApiRespCode(Integer.toString(response.getCode()));
		 				epsSmsLog.setApiRespMsg(response.getMessage());
		 				epsSmsLog.setSmsStatus('S');
		 				returnInteger=0;
		 	            }
		 			
		 				
		 			else {
		 				 epsSmsLog.setSmsStatus('U');
		 		    	returnInteger=-1;
		 		    
		 		       }	
		}
  	
  	smsRepository.save(epsSmsLog);    	
  	}
		
		return new Integer(returnInteger);
  	}
  	
  	
  	*/
   
	
	
}
