package ma.bits.demo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.crypto.Data;

public class Test2 {
	public static void main(String[] args) {
		Date d=new Date();
		
		String timeStamp = new SimpleDateFormat ("yyyy-mm-dd hh:mm:ss").format(new Timestamp(d.getTime()));

		
		System.out.println(timeStamp);
		
	}
}
