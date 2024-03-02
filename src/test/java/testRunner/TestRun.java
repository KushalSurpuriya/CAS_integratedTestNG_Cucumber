package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.testng.CucumberOptions;

//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class)
@CucumberOptions(features= {".//Features/UserVerifyFeature.feature"}, 
				 glue="StepDefination",
				 dryRun = false,
				 plugin= {"pretty", "html:target/myreport.html",
						 "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
				 )
public class TestRun extends AbstractTestNGCucumberTests{

}