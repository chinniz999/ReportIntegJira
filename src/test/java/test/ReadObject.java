package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.relevantcodes.extentreports.ExtentReports;

//Deserialize  

public class ReadObject {
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		ObjectInputStream os=new ObjectInputStream(new FileInputStream("ExetRepobject.java"));
		ExtentReports extReports=(ExtentReports)os.readObject();
		
	}

}
