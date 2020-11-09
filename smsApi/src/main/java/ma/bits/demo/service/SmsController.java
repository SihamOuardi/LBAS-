package ma.bits.demo.service;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ma.bits.demo.dao.EpsSmsLogRepository;
import ma.bits.demo.entities.EpsSmsLog;
import ma.bits.demo.metier.SmsMetier;


@RestController
@RequestMapping("/")
@CrossOrigin(origins="*")
public class SmsController {

	@Autowired
	EpsSmsLogRepository smsRepository;
	
	@Autowired
	SmsMetier metier;
	@Value("${username_eps_sms}")
    private String usernameSms;
    @Value("${password_eps_sms}")
    private String passwordSms;
    
	
	@PostMapping(value="/sendSms")
 	public ValidationResult sendRequestedSms(@RequestBody SmsModel smsModel){
		//ValidationResult result=new ValidationResult();
		
		System.out.println("start web service to send SMS");
		HttpServletRequest reqHttp = 
		        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
		                .getRequest();
		ValidationResult result =new ValidationResult(false,"sending SMS Failed");
		try {
			
			if(!smsModel.getUsername().equals(usernameSms) || !smsModel.getPassword().equals(passwordSms)) {
				result =new ValidationResult(false,"user is not allowed !");
				return result;
			}
		Timestamp timestamp= new Timestamp(new Date().getTime());
		smsRepository.save(new EpsSmsLog(timestamp, smsModel.getPhoneNumber(), smsModel.getSenderModule(), smsModel.getMessage(),'N', null, null, null));
		result.setMessage("sending SMS  is successfull");
		result.setStatus(true);
		return result;
		} catch (Exception e) {
			System.out.println("action sendSms failed with error :"+e.getMessage());
			e.getStackTrace();
			System.out.println(e.fillInStackTrace());
			e.fillInStackTrace();
			result.setStatus(false);
			result.setMessage("sending SMS Failed");
			return result;
		}
	
	}
	
	
	
	
}
