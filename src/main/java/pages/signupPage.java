package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PageBase;

public class signupPage extends PageBase {
	
	
	By firstNameTxt = By.name("first_name");
	By lastNameTxt = By.name("last_name");
	By phoneTxt = By.name("phone");
	By mailTxt = By.name("email");
	By passwordTxt = By.name("password");

	By accoutTypeSlct = By.id("account_type");
	By acountTypeBtn = By.id("select2-account_type-container");
	By acountTypeTxt = By.className("select2-search__field");
	By signupBtn = By.xpath("//button[@type='submit']");
	
	public WebElement firstNameTxtEle;
	public WebElement lastNameTxtEle;
	public WebElement phoneTxtEle;
	public WebElement mailTxtEle;
	public WebElement passwordTxtEle;
	public WebElement accoutTypeSlctEle;
	public WebElement signupBtnEle;
	public WebElement acountTypeBtnEle;
	

	public signupPage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, 20);
		actions = new Actions(driver);
		
		
	}
	
	public void fillFirstNameField(String firstName) {
		firstNameTxtEle = driver.findElement(firstNameTxt);
		firstNameTxtEle.sendKeys(firstName);
		
	}
	
	public void fillLastNameField(String lastName) {
		lastNameTxtEle = driver.findElement(lastNameTxt);
		lastNameTxtEle.sendKeys(lastName);
		
	}
	public void fillPhoneField(String phone) {
		phoneTxtEle = driver.findElement(phoneTxt);
		phoneTxtEle.sendKeys(phone);
		
	}
	

	public void fillMailField(String mail) {
		mailTxtEle = driver.findElement(mailTxt);
		mailTxtEle.sendKeys(mail);
	
	}
	
	public void fillPasswordField(String password) {
		passwordTxtEle = driver.findElement(passwordTxt);
		passwordTxtEle.sendKeys(password);
		passwordTxtEle.sendKeys(Keys.TAB);
	
	}
	
	public void selectAccountType(String type) throws InterruptedException {
		acountTypeBtnEle = driver.findElement(acountTypeBtn);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("window.scrollBy(0,300)", "");
		// tried implicit and explicit wait and they does not work, it works only with thread.sleep
		Thread.sleep(1000);
		
		acountTypeBtnEle.click();
		WebElement typeTxt = driver.findElement(acountTypeTxt);
		typeTxt.sendKeys(type);
		typeTxt.sendKeys(Keys.ENTER);
		
	
	}
	
	public void clickOnSignUpBtn() {
		signupBtnEle = driver.findElement(signupBtn);
		signupBtnEle.click();
		
	}
	
	public String getConstraintValidationMsg(String fieldNameToBeValidated) {
		WebElement elementToBeValidated = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='"+fieldNameToBeValidated+"']")));
		System.out.println(elementToBeValidated.getAttribute("validationMessage"));
		return elementToBeValidated.getAttribute("validationMessage");
		
	}

}
