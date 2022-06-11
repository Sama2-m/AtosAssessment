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
import pages.signupPage;
import utilities.ExcelReader;

public class validateLastNameIsMandatoryTest extends TestBase{
	String signupUrl= utilities.LoadProperties.userData.getProperty("SignUpUrl");
	signupPage signupPagePageObject;
	
	
	@BeforeMethod
	public void BeforeMethod() {
		openBrowser(signupUrl);
		signupPagePageObject = new signupPage(driver);
		
		
	}
	
	
	
	@DataProvider (name = "data", parallel=true)
	
	public Object[][] data() throws IOException, BiffException {
		Object[][] data = new Object[1][8];
		ExcelReader xl = new ExcelReader("Sheet1", "ValidateLastNameIsMandatoryTest");
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
	@Description("validate that last name is mandatory field")
	public void ValidateLastNameIsmandatoryTest (String firstName, String lastName, String phone, String mail, String password, String accountType, String validationMsg, String fieldNameToBeValidated) throws InterruptedException {
		signupPagePageObject.fillFirstNameField(firstName);;
		signupPagePageObject.fillPhoneField(phone);
		signupPagePageObject.fillMailField(mail);
		signupPagePageObject.fillPasswordField(password);
		signupPagePageObject.selectAccountType(accountType);
		signupPagePageObject.clickOnSignUpBtn();
		Assert.assertEquals(signupPagePageObject.getConstraintValidationMsg(fieldNameToBeValidated), validationMsg);
		
	}

}
