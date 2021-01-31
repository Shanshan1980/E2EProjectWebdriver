package Academe;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;  // log4j
import org.apache.logging.log4j.Logger;  // log4j
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.base;

public class validateTitle extends base{
	WebDriver driver; 
	public static Logger log=LogManager.getLogger(base.class.getName());
	LandingPage l;
	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		log.info("Driver is initialized");
		driver.get("http://qaclickacademy.com");
		log.info("Navigated to Home page");
		l= new LandingPage(driver);
		if(l.getPopUpSize()>0)
		{
		l.getPopUp().click();
		}
	}
	
	@Test
	public void validateTitle() throws IOException
	{
		Assert.assertEquals(l.getTitle().getText(), "FEATURED COURSES");
		log.info("Successfully validated Text message");
	}
	
	@Test
	public void validateHeader() throws IOException
	{
		Assert.assertEquals(l.getHeader().getText(), "AN ACADEMY TO LEARN EVERYTHING ABOUT TESTING");
		log.info("Successfully validated Header message");
	}
	@AfterTest
	public void teardown() {
		driver.close();
	}
	
	
}
