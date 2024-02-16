package com.project.pageobjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.project.webdriverfactory.DriverManager;

public class HomePageObjects {
	
	
	@FindBy(xpath = "//img[contains(@src,'mmtLogoWhite.png')]")
	public WebElement logoElement;
	
	@FindBy(xpath = "//span[contains(@class,'headerIconTextAlignment')]")
	public List<WebElement> headerElements;
	
	@FindBy(css = "ul.fswTabs.latoRegular.darkGreyText > li")
	public List<WebElement> tripElements;
	
	@FindBy(css = "input[placeholder=From]")
	public WebElement fromInputElement;
	
	@FindBy(css = "input[placeholder=To]")
	public WebElement toInputElement;
	
	@FindBy(xpath = "//li[contains(@data-cy,'travelClass')]")
	public List<WebElement> travellClassWebElement;
	
	@FindBy(xpath = "(//li[@role='option']//p[text()])[1]")
	public WebElement firstRoleOptionElement;
	
	private String datePickerXpath = "//div[text()='{MONTH} {YEAR}']/ancestor::div[@class='DayPicker-Month']//div[@class='dateInnerCell']/p[text()='{DAY}']";
	private String flightSearchOptionsXpath = "//span[text()='{OPTION}']";
	private String travelAndClassXpath = "//p[contains(text(),'{OPTION}')]/parent::div//li[text()='{NUMBER}']";
	
	public WebElement getDatePickerWebElement(String month, String year, String day) {
		
		String Xpath = datePickerXpath.replace("{MONTH}", month).replace("{YEAR}", year).replace("{DAY}", day);
		return DriverManager.getDriver().findElement(By.xpath(Xpath));
	}
	
	public WebElement getFlightSearchOptionWebElement(String option) {
		
		String xpath = flightSearchOptionsXpath.replace("{OPTION}", option);
		return DriverManager.getDriver().findElement(By.xpath(xpath));
	}
	
	public WebElement getTravellAndClassWebElement(String option, String number) {
		
		String xpath = travelAndClassXpath.replace("{OPTION}", option).replace("{NUMBER}", number);
		return DriverManager.getDriver().findElement(By.xpath(xpath));
	}
	
	public HomePageObjects() {
		
		PageFactory.initElements(DriverManager.getDriver(), this);
	}
}
