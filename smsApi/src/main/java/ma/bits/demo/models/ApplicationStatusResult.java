package ma.bits.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ApplicationStatusResult {

	private String wildflyName;
	private boolean connection;
	private String instanceName;
	private String ipAdress;
	
}
