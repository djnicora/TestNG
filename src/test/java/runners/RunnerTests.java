package runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)

@CucumberOptions(features="src/test/resources/features",glue={"stepDefinition","AppHooks"},
monochrome = true,
publish = true,
plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
,tags = "@PerformSimpleTests"
)

public class RunnerTests extends AbstractTestNGCucumberTests{
	

}

