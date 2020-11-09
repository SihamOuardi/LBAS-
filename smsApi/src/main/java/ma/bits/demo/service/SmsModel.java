package ma.bits.demo.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsModel {
 private String phoneNumber;
 private String message;
 private String senderModule;
 private String username;
 private String password;
}
