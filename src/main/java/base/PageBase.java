package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PageBase {
    
    	
    	public Actions actions;
    	public static WebDriverWait wait;
    	public WebDriver driver;
    	
    	//Super constructor
    	public PageBase(WebDriver driver) {
    		this.driver = driver;
    		PageFactory.initElements(driver, this);
    	}
        
    }

