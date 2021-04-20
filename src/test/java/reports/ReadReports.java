package reports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ReadReports {

	public static List<HashMap<String, String>> readHTML(List<String> fileLocation) {

		System.out.println("@@@@@@@@@size@@@@@@@@@@" + fileLocation.size());
		List<HashMap<String, String>> failuresToCheckInJira = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < fileLocation.size(); i++) {

			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\lib\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(true);

			WebDriver driver = new ChromeDriver(options);
			System.out.println("File running --> now" + fileLocation.get(i));
			driver.get(fileLocation.get(i));

			List<WebElement> fails = driver.findElements(By
					.xpath("//span[@class='test-status label right outline capitalize fail']/preceding-sibling::span"));

			// List<HashMap<String, String>> failuresToCheckInJira=new
			// ArrayList<HashMap<String,String>>();

			for (int j = 0; j < fails.size(); j++) {

				HashMap<String, String> failure = new LinkedHashMap<String, String>();

				System.out.println("TC-Name" + fails.get(j).getText());
				String theTextIWant = (String) ((JavascriptExecutor) driver)
						.executeScript("return arguments[0].innerHTML;", driver.findElement(By.xpath(
								"//td[@class='step-details'][contains(text(),'" + fails.get(j).getText() + "')]")));
				System.out.println(theTextIWant);

				if (theTextIWant.contains("java.lang.AssertionError:")) {
					failure.put(fails.get(i).getText(), theTextIWant.split("@")[1]);
				}

				// driver.findElement(By.xpath("//td[@class='step-details'][contains(text(),'"+fails.get(i).getText()+"
				// ---->')]")).getText();
				failuresToCheckInJira.add(failure);

			}

		}
		return failuresToCheckInJira;
	}

}