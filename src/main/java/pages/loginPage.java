package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PageBase;

public class loginPage extends PageBase {
	
	By mailTxt = By.name("email");
	By passwordTxt = By.name("password");
	By loginBtn = By.xpath("//button[@type='submit']");

	public loginPage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, 20);
		
	}
	
	WebElement mailTxtEle;
	WebElement passwordTxtEle;
	WebElement loginBtnEle;
	
	public void loginSuccessfully(String mail, String password) {
		mailTxtEle = driver.findElement(mailTxt);
		mailTxtEle.sendKeys(mail);
		passwordTxtEle = driver.findElement(passwordTxt);
		passwordTxtEle.sendKeys(password);;
		loginBtnEle = driver.findElement(loginBtn);
		loginBtnEle.click();
		
	}

}
