package com.rjil.snw.webAutomation.pageObjects;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.rjil.snw.webAutomation.interfaces.HomePageInterface;

public class RetailerHomePage implements HomePageInterface {
	public Properties prop;
	public WebDriver driver;
	LoginPage lp;

	@FindBy(how = How.LINK_TEXT, using = "Logout")
	private WebElement logoutLink;

	/**
	 * @param driver
	 * @param prop
	 */
	public RetailerHomePage (WebDriver driver, Properties prop) {
		driver.manage().window().maximize();
		this.prop = prop;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
 
	
	/**
	 * INFO: This method will logout from home page
	 * @param none
	 * @return
	 */
	public LoginPage logout() {
		logoutLink.click();
		LoginPage loginPage = new LoginPage(driver, prop);
		return loginPage;
	}

}

