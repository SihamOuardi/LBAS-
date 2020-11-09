package ma.bits.demo;

import ma.bits.demo.entities.EpsSmsLog;
import ma.bits.demo.metier.SmsConfigData;
import ma.bits.demo.metier.SmsMetier;
import ma.bits.demo.models.SmsResponse;
import ma.bits.demo.models.WirepickSmsResponse;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
SmsMetier metier=new SmsMetier();
System.out.println("<?xml version='1.0' encoding='utf-8'?>");
EpsSmsLog smsLog =new EpsSmsLog();
smsLog.setMsisdn("0560922115");
smsLog.setSmsText("Hi ,this is a test message of wirePick api");
//https://deywuro.com/api/sms
SmsConfigData config=new SmsConfigData("G-MONEY","gmoney_pss_123", "https://gcb.deywuro.com/api/sms", "gcbbank","gcb2019", "http://10.10.30.105/httpsms/send");

//"GCBEPS$M$G@T3W@Y"

//wget http://10.10.30.105/httpsms/send?client=wirepick&password=xxxxxxxx&affiliate=999&phone=233560922115&text=TestingWirepick

//   wget http://10.10.30.105/httpsms/send?client=wirepick&password=xxxxxxxx&affiliate=999&phone=233560922115&text=Testing

//https://api.wirepick.com/httpsms/send?client=gcbbank&password=gcb2019&phone=+233560922115&text=Bonjour Mouad ,this is a test message of wirePick api&from=GCB BANK
//https://216.105.85.220:7502/httpsms/send?client=gcbbank&password=gcb2019&phone=0560922115&text=Bonjour_Mouad&from=GCB BANK

//http://10.10.30.105/httpsms/send?client=gcbbank&password=gcb2019&phone=233560922115&text=Bonjour_Mouad&from=GCBBANK

// wget http://10.10.30.105/httpsms/send?client=gcbbank&password=gcb2019&phone=233560922115&text=Bonjour_Test&from=GCBBANK --auth-no-challenge --force-directories
//wget  http://10.10.20.105/httpsms/send?client=gcbbank&password=gcb2019&phone=233560922115&text=Bonjour_Mouad&from=GCBBANK --auth-no-challenge --force-directories

//wget  http://10.10.30.105/httpsms/send?client=gcbbank&password=gcb2019&phone=233560922115&text=Bonjour_Mouad&from=GCBBANK

//10.10.20.105:80
//  nc -v 10.10.30.105 7502
// Date

//


WirepickSmsResponse respWirePick= metier.callWirePickSmsApi(smsLog,"gcbbank","gcb2019",config);
if(respWirePick.getStatus().equals("Accepted"))
   smsLog.setSmsStatus('S');
else
	smsLog.setSmsStatus('U');	
System.out.println(respWirePick.getStatus());

  System.out.println("smsLog.getSmsStatus() = "+smsLog.getSmsStatus());
	
  

	/*//---------------------------test deywuro api ---------------------------
	EpsSmsLog smsLog2 =new EpsSmsLog();
	smsLog2.setMsisdn("0560922115");
	smsLog2.setSmsText("Bonjour Mouad ,this is a test message of deywuro api");
	SmsResponse resp=  metier.callSmsApi(smsLog2,config);
	if(resp.getCode()==0)   
		smsLog2.setSmsStatus('S');
	else	
	  smsLog2.setSmsStatus('U');        
	  System.out.println("smsLog.getSmsStatus() = "+smsLog2.getSmsStatus());	  */

  
	
	}
}
