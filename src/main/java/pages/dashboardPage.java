package pages;

import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PageBase;

public class dashboardPage extends PageBase {
	
	By welcomeMsg = By.xpath("//div[@class='breadcrumb-content']//child::h2");
	WebElement welcomeMsgEle;
	

	public dashboardPage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, 20);
	}
	
	public String getWelcomeMsg() {
		
		welcomeMsgEle = driver.findElement(welcomeMsg);

		String sentence = welcomeMsgEle.getAttribute("innerHTML");
		String[] words = sentence.split(Pattern.quote("<span>"));
		String partA = words[0];
		String partB = words[1];
		String partC = words[2];
		System.out.print("text is" +partA+partB+partC);	
		String wlcomeMsg = partA+partB+partC;
		
		return wlcomeMsg;
	}

}
