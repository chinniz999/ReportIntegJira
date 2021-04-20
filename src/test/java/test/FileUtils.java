package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileUtils {
	
	
	public static File  getLatestFile(String folderPath) throws IOException {
		
		
		 File directory = new File(folderPath);
		    File[] files = directory.listFiles(File::isFile);
		    long lastModifiedTime = Long.MIN_VALUE;
		    File chosenFile = null;

		    if (files != null)
		    {
		        for (File file : files)
		        {
		            if (file.lastModified() > lastModifiedTime)
		            {
		                chosenFile = file;
		                lastModifiedTime = file.lastModified();
		            }
		        }
		    }

		    return chosenFile;	
	}
	
	public static List<String> getSubFolders(String folderPath) throws IOException {
		
		File[] folders= new File(folderPath).listFiles(File::isDirectory);
		List<String> foldernames=new ArrayList<String>();
		
		for(File folder:folders){
			foldernames.add(folder.getName());
		}
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@"+foldernames);
		return foldernames;
	}
	
	public static List<String> getLatestReports(String filePath) throws IOException {

		List<String> subfolderNames = getSubFolders(filePath);

		Iterator<String> folders = subfolderNames.iterator();

		List<String> filepath = new ArrayList<String>();

		while (folders.hasNext()) {
			filepath.add(getLatestFile(filePath + folders.next()).getPath());
		}
		
		return filepath;

	}

	public static void main(String[] args) throws IOException {
		
		//A:\\Eclipse_WorkSpace\\Reports\\ExtententReports\\
		
		List<String> reports=getLatestReports("A:\\Eclipse_WorkSpace\\Reports\\ExtententReports\\");
		
		

	}
	
	
	
	
	
	
	
	
	
//	String path="A:\\Eclipse_WorkSpace\\Reports\\Reports\\";
//
//	List<String> file=getSubFolders(path);
//	
//	Iterator<String> itr=file.iterator();
//	while(itr.hasNext()) {
//		System.out.println(itr.next());
//	}
//	
//	
//	
//	SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MMM-dd hh:mm:ss");
//	//Setting the time zone
//	dateTimeInGMT.setTimeZone(TimeZone.getTimeZone("EDT"));
//	System.out.println(dateTimeInGMT.format(new Date()));
	

}
