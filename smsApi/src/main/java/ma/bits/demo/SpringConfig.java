package ma.bits.demo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.log4j.Log4j2;
import ma.bits.demo.dao.EpsSmsLogHistoriqueRepository;
import ma.bits.demo.dao.EpsSmsLogRepository;
import ma.bits.demo.entities.EpsSmsLog;
import ma.bits.demo.entities.EpsSmsLogHistorique;
import ma.bits.demo.metier.SmsConfigData;
import ma.bits.demo.metier.SmsMetier;

@Configuration
@EnableScheduling
@Log4j2
public class SpringConfig {

	@Autowired
	EpsSmsLogRepository smsRepository;	
	
	@Autowired
	EpsSmsLogHistoriqueRepository  smsHistRepository;	
	
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
    private String reqUrlSmsWirepick;
    
	@Autowired
	SmsMetier metier;
	@Scheduled(fixedDelayString ="${fixedDelayScheduel}")		
	public void sendRequestedSms2(){
		
		 SmsConfigData config =new SmsConfigData(this.usernameSms,this.passwordSms,this.reqUrlSms,this.clientWirePick,this.passwordWirpick,this.reqUrlSmsWirepick);	
		
		 List<EpsSmsLog> logsms= smsRepository.getSmsLogsToBeSend('N','U');
		 List<EpsSmsLog> logsmsHist= smsRepository.getSentSmsLogs('S');

		 AsyncConfiguration asynConfig=new AsyncConfiguration();
		 for(EpsSmsLog s :logsms) {
			 //log.info("EpsSmsLog :  ="+s);
			 CompletableFuture.supplyAsync(()->metier.sendAsysnchronusSms(s, config, smsRepository)); 

		 }
		 for(EpsSmsLog hist :logsmsHist) {
			 CompletableFuture.supplyAsync(()->historizeEpsSmsLog(hist)); 
		 }

			return ;
	}
	
	
	
	 @Transactional
	 @Async
	   public Boolean historizeEpsSmsLog(EpsSmsLog hist) {
		   try {
				 log.info("Start historizeEpsSmsLog : "+hist);
				 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss.SSS");
				 log.info("Thread Name :"+Thread.currentThread().getName()+" SMS Details: "+hist+" DateTime :"+sdf.format(new Date()));	
				 
				 EpsSmsLogHistorique s=new EpsSmsLogHistorique(hist.getSmsTs(),hist.getMsisdn() , hist.getSenderModule(), hist.getSmsText()
						 , hist.getSmsStatus(), hist.getSend_ts(), hist.getApiRespCode(),hist.getApiRespMsg() );
	 
				 smsHistRepository.save(s);				
				 smsRepository.delete(hist);
		 
		   } catch (Exception e) {
			   log.error("End historizeEpsSmsLog Failed With Error",e);
			   e.printStackTrace();
			   return false;
		   }
			   log.info("End historizeEpsSmsLog Succesfully");
			   return true; 
	   }

}
