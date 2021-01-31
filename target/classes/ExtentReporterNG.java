package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	static ExtentReports extent;
	
	public static ExtentReports getReportObject()  // if this class static, all the var in it should also be static
			{
	
			// two important classes: ExtentReports, ExtentSparkReporter
			// ExtentReports : main class, consolidate all the ExtentSparkReporter objects
			String path = System.getProperty("user.dir")+"\\reports\\index.html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(path);
			reporter.config().setReportName("Web Automation Reports");
			reporter.config().setDocumentTitle("Test Results");
					
			extent = new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("Tester", "Rahul Shetty");
			return extent;
			}
	

}
