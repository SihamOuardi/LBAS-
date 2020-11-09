package ma.bits.demo.entities;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import javax.persistence.Table;
//import javax.validation.constraints.NotNull;

//import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="EPS_SMS_LOG_HISTORY")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(EpsSmsLogId.class)
public class EpsSmsLogHistorique implements Serializable {

	//,unique = true
	//@NotNull		
	@Column(name="SMS_TS" , length=6 , nullable = false)	
	@Id
	private Timestamp smsTs;
	
	//@NotNull
	@Id
	@Column(name="MSISDN", length=64, nullable = false)
	private String msisdn;
	

	@Column(name="SENDER_MODULE", length=64)
	private String SenderModule;
	
	//@Id
	//@NotNull
	@Column(name="SMS_TEXT", length=2000, nullable = false)
	private String smsText;
	
	//@NotNull
	//@Id
	@Column(name="SMS_STATUS", nullable = false)
	private Character smsStatus;
	
	
	@Column(name="SEND_TS", length=6)
	private Timestamp send_ts;
	
	@Column(name="API_RESP_CODE", length=64)
	private String apiRespCode;
	
	@Column(name="API_RESP_MSG" , length=256)
	private String apiRespMsg;
	
}
