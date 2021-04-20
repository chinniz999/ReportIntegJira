package test;

import java.io.File;

public class FileCheck {

	public static void main(String[] args) {

		 String fileLocation="C:\\Program Files\\Git\\bin\\Sample.txt";
		 
		 File deployFile=new File(fileLocation);
		 
		 for(int i=0;i<1000;i++) {
			 if(deployFile.exists()) {
				 System.out.println("File Exist");
				 break;
			 }
			 System.out.println("File dosenot Exist");	 
		 }
		
	}

}
