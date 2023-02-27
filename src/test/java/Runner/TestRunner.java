package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
	//  features = { ".//Features/Search.feature" },
	//	features= {".//Features/SearchDDT.feature"},
	//	features= {".//Features/SearchExcel.feature"},
	//	features= {".//Features/Hooks.feature"},
	//	features= {".//Features/ScreenShotOnFailure.feature"},
	//  features= {".//Features/Background.feature"},
	//	features = {"@target/rerun.txt" },
		features= {".//Features/Tags.feature"},
		glue = "StepDefinitions",
		plugin = { "pretty",
		"html:Reports/MyReport.html",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		"rerun:target/rerun.txt"},
		dryRun = false,
		monochrome = true,
		tags = "@Sanity"
		)

public class TestRunner {

}
