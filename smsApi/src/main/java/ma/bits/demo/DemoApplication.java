package ma.bits.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.SpringVersion;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.annotation.Scheduled;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
//import com.bits.Acpg1Application;
import com.zaxxer.hikari.HikariConfig;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

import ma.bits.demo.dao.EpsSmsLogRepository;
import ma.bits.demo.entities.EpsSmsLog;
import ma.bits.demo.entities.EpsSmsLogId;
import ma.bits.demo.metier.SmsConfigData;
import ma.bits.demo.metier.SmsMetier;
import ma.bits.demo.models.WirepickSmsResponse;
import ma.bits.demo.service.ValidationResult;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@EnableEncryptableProperties
public class DemoApplication extends SpringBootServletInitializer implements CommandLineRunner  {
 
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    	
        return application.sources(DemoApplication.class);
    }
//implements CommandLineRunner {
	
    /*
    @Autowired	
	EpsSmsLogRepository smsRepository;
	@Autowired
	SmsMetier metier;
	
	
	
	  @Value("${usernameSms}")
	    private String usernameSms;
	    @Value("${passwordSms}")
	    private String passwordSms;
	    @Value("${reqUrlSms}")
	    private String reqUrlSms;
	    
	    @Value("${clientWirePick}")
	    private String clientWirePick;
	   @Value("${passwordWirpick}")
	    private String passwordWirpick;
	    @Value("${reqUrlSmsWirepick}")
	    private String reqUrlSmsWirepick;*/
	
	
	
	public static void main(String[] args) {
		
		SpringApplication.run(DemoApplication.class, args);
	/*	
		HikariConfig config = new HikariConfig();
       
        config.setConnectionTimeout(120000);
	*/
	}		
	
	
	@Override
	public void run(String... args) throws Exception {
		
		//SpringVersion.getVersion()
		
		System.out.println("*************Spring Version : "+SpringVersion.getVersion()+" ********");
		/*
		
		SmsConfigData config =new SmsConfigData(this.usernameSms,this.passwordSms,this.reqUrlSms,this.clientWirePick,
				this.passwordWirpick,this.reqUrlSmsWirepick);	
		List<EpsSmsLog> logsms= smsRepository.getSmsLogsToBeSend('B','U');
		 AsyncConfiguration asynConfig=new AsyncConfiguration();
		 for(EpsSmsLog s :logsms) {
			 
			 WirepickSmsResponse respWirePick=metier.callWirePickSmsApi(s,config.getClientWirePick(),config.getPasswordWirpick(),config);
			  	 if(respWirePick !=null) {
						 System.out.println("############ Response Wirepick ####### ="+respWirePick);
						 s.setApiRespCode(respWirePick.getMsgid());
					    	s.setApiRespMsg(respWirePick.getStatus());
					 }
			  	if(respWirePick ==null)
					System.out.println("########################## Response wirepick Nulllll#################");
			 
			  	else if(respWirePick !=null && respWirePick.getStatus().equals("Accepted")) {
			  	   s.setSmsStatus('S');
			  	   smsRepository.save(s); 
			  	  // returnInteger=0;
			  	}
			  	else if(respWirePick !=null && !respWirePick.getStatus().equals("Accepted")) {
			  		s.setSmsStatus('U');
			   	   smsRepository.save(s); 
			   	   
			  	}
		    
			 
		 }
		
	*/
		}
	
	
	
	
}
