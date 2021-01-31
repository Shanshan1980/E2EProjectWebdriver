package cucumberOptions;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

//Cucumber JUnit Test Runner Class
@RunWith(Cucumber.class)
@CucumberOptions(
//where is the feature file located, to package 'features',all the testcases inside will be executed
//if u want to run a single test: features = "src/test/java/features/Login.feature"
//the package for TestRunner.java and the package for stepDefination.java should be in the same level
features = "src/test/java/features",
glue="stepDefinations") // folder wo stepDefination file located

public class TestRunner {
	

}
