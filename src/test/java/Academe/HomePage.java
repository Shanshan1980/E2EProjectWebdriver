package Academe;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.ForgotPassword;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.base;

public class HomePage extends base{
	//in paralell tests should declare the driver local, so that every test use its own driver.
	
	//in sequent tests do not need to declare  the driver local, 
	//all the tests use the same driver which is created ininitializeDriver() 
	WebDriver driver;  
	public static Logger log=LogManager.getLogger(base.class.getName());
	@BeforeTest
	public void initialize() throws IOException {
		
		driver = initializeDriver();
		
	}
	@Test(dataProvider="getData")
	public void basePageNavigation(String Username, String Password,String text) throws IOException
	{   
	    driver.get(prop.getProperty("url"));
	   	LandingPage l= new LandingPage(driver);
		if(l.getPopUpSize()>0)
		{
		l.getPopUp().click();
		}
		
		//l.getLogin().click();
		//LoginPage lp=new LoginPage(driver);
		LoginPage lp=l.getLogin();
		lp.getEmail().sendKeys(Username);
		lp.getPassword().sendKeys(Password);
		log.info(text);
		lp.getLogin().click();
		ForgotPassword fp=lp.forgotPassword();
		fp.getEmail().sendKeys("myemail@gmail.com");
		fp.sendMeInstructions().click();
		
	}
	@AfterTest
	public void teardown() {
		driver.close();
	}
	
	
	@DataProvider   //if the testdata for more than one time test, needs not @BeforeTest and @AfterTest
	public Object[][] getData()
	{
		//Row stands for how many different data types test should run
		//column stands for how many values per each test
		//Array size is 5: 0,1,2,3,4, start from 0
		Object[][] data=new Object[2][3];
		
		data[0][0]="nonrestricteduser@qw.com";
		data[0][1]="123456";
		data[0][2]="Restricted User";
		
		data[1][0]="restricteduser@qw.com";
		data[1][1]="456789";
		data[1][2]="Nonrestricted User";
		
		return data;
	}
		
}
