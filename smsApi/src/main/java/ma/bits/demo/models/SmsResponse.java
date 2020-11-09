package ma.bits.demo.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
//@XmlRootElement(name="sms")
public class SmsResponse {
	
	private int code;
	private String message ;
	
	
/*//	@XmlElement(name = "msgid")
	private String msgid;
//	@XmlElement(name = "phone")
	private String phone;
//	@XmlElement(name = "country")
	private String country;
//	@XmlElement(name = "status")
	private String status;*/
}
