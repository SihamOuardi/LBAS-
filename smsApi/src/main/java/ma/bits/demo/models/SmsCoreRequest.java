package ma.bits.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsCoreRequest {
    private String username;
    private String password;
    private String source;
    private String destination;
    private String message;
	
	
}
