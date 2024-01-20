package test.project.testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions.SnippetType;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features",
		glue = "test.project.stepdefinitions",
		tags = "",
		monochrome = true,
		plugin = {"pretty", "html:target/cucumberReport.html"},
		snippets = SnippetType.CAMELCASE
		)
public class TestRunner {

}
