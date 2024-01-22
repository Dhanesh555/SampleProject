package com.project.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.project.webdriverfactory.DriverManager;

public class CommonObjects {

	@FindBy(css = "a.close")
	public WebElement addCloseElement;
	
	@FindBy(xpath = "//iframe[contains(@name,'notification-frame')]")
	public WebElement notificationIframeElement;
	
	public CommonObjects() {
		
		PageFactory.initElements(DriverManager.getDriver(), this);
	}
}
