package com.project.pageobjects;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.project.webdriverfactory.DriverManager;

public class HomePageObjects {

	@FindBy(xpath = "//img[contains(@src,'mmtLogoWhite.png')]")
	public WebElement logoElement;
	
	@FindBy(xpath = "//span[contains(@class,'headerIconTextAlignment')]")
	public List<WebElement> headerElements;
	
	public HomePageObjects() {
		
		PageFactory.initElements(DriverManager.getDriver(), this);
	}
}
