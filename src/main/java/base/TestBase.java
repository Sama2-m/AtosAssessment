package base;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.nio.file.Files;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;






	public class TestBase {
		public static WebDriver driver;
		public static String downloadPath = System.getProperty("user.dir") + "\\Downloads";
		
		

		static String driverType = utilities.LoadProperties.userData.getProperty("Browser");
		
		public static ChromeOptions chromeOption() {
			ChromeOptions options = new ChromeOptions();
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default.content_settings.popups", 0);
			options.setExperimentalOption("prefs", chromePrefs);
			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			options.addArguments("--start-maximized");
			//options.addArguments("--incognito");
			options.setHeadless(false);
			return options;
		}
		
		@BeforeClass
		public static WebDriver getDriver() {
			if (driverType.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver= new FirefoxDriver();

			} else if (driverType.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver= new ChromeDriver(chromeOption());

			} else if (driverType.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver= new EdgeDriver();
			}
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

			

			return driver;
		}
		@Parameters({"browser"})
		public void startDriver() throws IOException, InterruptedException {	
			WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}

			
			

		
		public void openBrowser(String URL) {
			driver.navigate().to(URL);
			
			
		}
		
		
		
		@AfterMethod(alwaysRun = true)
		public void screenShotOnFailure(ITestResult result) throws IOException {
			
			
			if (result.getStatus() == ITestResult.FAILURE) {
				System.out.println("Failed");
				System.out.println("Taking Screenshot....");
				Path dest = Paths.get("./Screenshots", result.getName() + ".png");
				
				try {
					Files.createDirectories(dest.getParent());
					FileOutputStream out = new FileOutputStream(dest.toString());
					out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
					out.close();

				} catch (IOException e) {
					System.out.println("Exception while taking screenshot" + e.getMessage());
				}
				
				Path content = Paths.get(System.getProperty("user.dir") + "\\Screenshots\\"+ result.getName()+ ".png");
				try (InputStream is = Files.newInputStream(content)) {
					Allure.addAttachment(result.getName(), is);
				}
			}
		}
		
		@AfterClass(alwaysRun = true)
		public void closeDriver() throws IOException {
			driver.close();
		}
		

}
