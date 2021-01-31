package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class base {

public WebDriver driver;
public Properties prop=new Properties();// should be public and cross all the classes
public WebDriver initializeDriver() throws IOException
{
	//System.getProperty("user.dir")->....E2EProject
	
	FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
	prop.load(fis);
	//**$ mvn test -Dbrowser=chrome 
	//run the test in Maven, Maven treat this as system properties 
	//the code will check in Maven command if any properties defined with browser
	//in this case chrome will be extracted
	//controll the executing browser from command
	//String browserName=System.getProperty("browser");
	String browserName=prop.getProperty("browser");
	if(browserName.contains("chrome"))  // extract value from property should use equals(), not "=="
	{
		System.setProperty("webdriver.chrome.driver", "C:\\workspace\\chromedriver_win64\\chromedriver.exe");
		ChromeOptions options=new ChromeOptions();  //create a ChromeOptions obj and pass it as argument to the ChromDriver
		if(browserName.contains("headless"))
		{
		options.addArguments("headless");   //definieren the options, want to run the chrome in headless mode
		}
		driver=new ChromeDriver(options);
    }
	else if(browserName.equals("firefox"))
	{
		//execute in firefox driver
		driver=new FirefoxDriver();
	}
	else if(browserName.equals("IE"))
	{
		//execute in IE driver
	}
	
	//this line will not be executed if driver has no life
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	return driver;
}
public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException 
{
	
	TakesScreenshot ts =(TakesScreenshot) driver;

    File source=ts.getScreenshotAs(OutputType.FILE);

    String desFile= System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
   
    File DestFile=new File(desFile);
    FileUtils.copyFile(source, DestFile);
    return desFile;
}

}
