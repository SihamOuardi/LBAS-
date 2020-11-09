package ma.bits.demo.metier;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsConfigData {


    private String usernameSms;
    private String passwordSms;
    private String reqUrlSms;
    
    private String clientWirePick;
    private String passwordWirpick;
    private String reqUrlSmsWirepick;

    
    
}
