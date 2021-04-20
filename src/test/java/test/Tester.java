package test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import jira.Jira;

public class Tester {

	public static void main(String[] args) throws IOException {
		
		 //Get latest Reports from Build folders.
		 List<String> listOfLatestReports=FileUtils.getLatestReports("A:\\Eclipse_WorkSpace\\Reports\\ExtententReports\\");
		 
		 //Extract failed test cases from above list of latest reports
		 List<HashMap<String, String>> failedTCSlist= ReadReport.readHTML(listOfLatestReports);
		 
		 for(int i=0;i<failedTCSlist.size();i++) {
			 Iterator<Entry<String, String>> tcs= failedTCSlist.get(i).entrySet().iterator();
			 while(tcs.hasNext()) {
				 Entry<String, String> tc= tcs.next();
				 System.out.println(tc.getKey()+"---->"+tc.getValue());
				 Jira.isBugLogged(tc.getKey());
			 }
			 
		 }

	}

}
