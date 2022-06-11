package PhpTravelsTestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jxl.read.biff.BiffException;
import pages.dashboardPage;
import pages.loginPage;

import utilities.ExcelReader;

public class loginSuccessullyTest extends TestBase{
	String loginUrl= utilities.LoadProperties.userData.getProperty("LoginUrl");
	loginPage loginPageObject;
	dashboardPage dashboardPageObj;
	
	@BeforeMethod
	public void BeforeMethod() {
		openBrowser(loginUrl);
		loginPageObject = new loginPage(driver);
		dashboardPageObj = new dashboardPage(driver);
		
	}
	
	
	
	@DataProvider (name = "data", parallel=true)
	
	public Object[][] data() throws IOException, BiffException {
		Object[][] data = new Object[1][3];
		ExcelReader xl = new ExcelReader("Sheet1", "loginSuccessfullyTest");
		Object[][] data1 = xl.getTestdata();

		
		for (int i = 0; i < data1.length; i++) {
			for (int j = 0; j < data1[i].length; j++) {
				data[0][j] = data1[i][j];
			}
			
		}
		return data;
		}
	

	
	@Test(dataProvider = "data")
	@Severity(SeverityLevel.NORMAL)
	@Description("login successfully")
	public void loginSuccessfullyTest (String mail, String password, String name) throws InterruptedException {
		String actualWelcomeMsg;
		loginPageObject.loginSuccessfully(mail, password);
		
		String expectedWelcomeMsg ="Hi, "+name+" Welcome Back";
		dashboardPageObj.getWelcomeMsg();
	
		actualWelcomeMsg =dashboardPageObj.getWelcomeMsg();	
		Assert.assertEquals(expectedWelcomeMsg, actualWelcomeMsg);
		
		
	}

}
