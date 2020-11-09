package ma.bits.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class LoadFileProp {
public static  Properties ps = new Properties();


public void loadFileProperties() {
try {
	 System.out.println("start load file properties");
	System.out.println("1");
	ClassLoader loader = Thread.currentThread().getContextClassLoader();
	// ps = new Properties();
	try(InputStream resourceStream = loader.getResourceAsStream("application2.properties")) {
	    ps.load(resourceStream);
	}
	ps.list(System.out);

	System.out.println(ps.getProperty("ipAdresses"));
	 System.out.println("file loaded !");
	} catch (IOException e) {
		System.out.println(e.getMessage());
		System.out.println("load properties file Error !");
	 e.getStackTrace();
	 return ;
	}

String ipaddresses=ps.getProperty("ipAdresses");
System.out.println(ipaddresses);
}

}
