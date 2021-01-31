package cucumberOptions;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//Cucumber JUnit Test Runner Class, this is not needed in Cucumber-TestNG Runner
//@RunWith(Cucumber.class)
@CucumberOptions(

features = "src/test/java/features",
glue="stepDefinations") // folder wo stepDefination file located

// Cucumber-TestNG Runner
public class TestRunner1 extends AbstractTestNGCucumberTests  {
	

}
