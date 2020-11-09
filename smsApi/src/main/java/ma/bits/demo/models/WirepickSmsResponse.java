package ma.bits.demo.models;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="sms")
public class WirepickSmsResponse {	
	@XmlElement(name = "msgid")
	private String msgid;
	@XmlElement(name = "phone")
	private String phone;
	//@XmlElement(name = "country")
	//private String country;
	@XmlElement(name = "status")
	private String status;	
	@XmlElement(name = "recd_time")
	private String recd_time;
	public WirepickSmsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WirepickSmsResponse(String msgid, String phone, String status, String recd_time) {
		super();
		this.msgid = msgid;
		this.phone = phone;
		this.status = status;
		this.recd_time = recd_time;
	}
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRecd_time() {
		return recd_time;
	}
	public void setRecd_time(String recd_time) {
		this.recd_time = recd_time;
	}
	
	
}


