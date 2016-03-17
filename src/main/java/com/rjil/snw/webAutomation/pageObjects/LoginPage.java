package com.rjil.snw.webAutomation.pageObjects;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.rjil.snw.webAutomation.interfaces.LoginInterface;



/**
 * @author Anand.Bajpai
 * 
 * This class implements the LoginInterface class.
 * This class provides a tool for login in preprod.switchnwalk.com
 *
 */

public class LoginPage implements LoginInterface {
	private final WebDriver driver;
	public Properties prop;
	
    /**
     * Below is set of locators for login
     * 1. Click button for login
     * 2. username field
     * 3. password field
     * 4. login submit button 
     */
	
	@FindBy(how = How.CLASS_NAME, using = "dropdown-toggle")
	private WebElement login_button;
	@FindBy(how = How.ID, using = "loginform:user_username")
	private WebElement userField;
	@FindBy(how = How.ID, using = "loginform:user_password")
	private WebElement passwordField;
	@FindBy(how = How.ID, using = "loginform:login_button")
	private WebElement login_submit;
	

	/**
	 * Below is constructor for present class
	 * @param driver WebDriver object
	 * @param prop Properties object
	 */

	public LoginPage(WebDriver driver, Properties prop) {
		this.driver = driver;
		this.prop = prop;
		PageFactory.initElements(driver, this);
	}

	/**
	 * This method is to click on login button.
	 */
	public void clickLoginButton() {
		login_button.click();
	}
	
	/**
	 * This method is to enter email ID.
	 */
	public void enterEmail(String username) {
		userField.sendKeys(username);
	}
	
	/**
	 * This method is to enter password.
	 */
	public void enterPassword(String password){
		passwordField.sendKeys(password);
	}
	
	/**
	 * This method is to do Partner login.
	 */
	public HomePage Performlogin() {
		login_submit.click();
		HomePage hp  = new HomePage(driver, prop);
		return hp;
	}
	
	/**
	 * This method is to do admin login.
	 */
	public AdminHomePage Adminlogin() {
		login_submit.click();
		AdminHomePage hp  = new AdminHomePage(driver, prop);
		return hp;
	}
	
	/**
	 * This method is to do Retailer and distributor login.
	 */
	public RetailerHomePage Retaillogin() {
		login_submit.click();
		RetailerHomePage hp  = new RetailerHomePage(driver, prop);
		return hp;
	
	}
	
	/**
	 * This method is to do Retailer and distributor login.
	 */
	public DistributorHomePage Distributorlogin() {
		login_submit.click();
		DistributorHomePage hp  = new DistributorHomePage(driver, prop);
		return hp;
	
	}

}
