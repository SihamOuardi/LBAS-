package ma.bits.demo.entities;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpsSmsLogId implements Serializable{
	
	private Timestamp smsTs;	
	private String msisdn;
	//private String smsText;
	
	//private Character smsStatus;
	
}
