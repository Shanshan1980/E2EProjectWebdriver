package Academe;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.base;

public class validateNavBar extends base {
	//adding logs
	//generating html reports
	//screenshots on failure
	//Jenkins integration
	WebDriver driver; 
	public static Logger log=LogManager.getLogger(base.class.getName());
	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		driver.get("http://qaclickacademy.com");
	}

	@Test
	public void basePageNavigation() throws IOException {
		
		LandingPage l= new LandingPage(driver);
		if(l.getPopUpSize()>0)
		{
		l.getPopUp().click();
		}
				
		Assert.assertTrue(l.getNavigationBar().isDisplayed());
		log.info("NavBar is displayed");
		
	}

	@AfterTest
	public void teardown() {
		driver.close();
	}
}
