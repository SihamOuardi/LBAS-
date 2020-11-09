package ma.bits.demo.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin(origins="http://epsprod.gcbltd.com")

//http://epsprod.gcbltd.com/CheckSmsApp
public class SmsControllerConnection {

	@GetMapping(value="CheckSmsApp")
	public Boolean CheckConnection() {
		
		return true;
	}
	
}
