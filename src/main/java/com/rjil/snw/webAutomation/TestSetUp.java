package com.rjil.snw.webAutomation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;

import com.rjil.snw.webAutomation.pageObjects.LoginPage;

/**
 * @author Anand.Bajpai
 *
 */
public class TestSetUp {

	/**
	 * @return This method returns Properties which are needed for test run
	 * These variables should be same through out the suite.
	 */
	private Properties load_data() {
		Properties prop = new Properties();

		try {
			FileInputStream input = new FileInputStream("src\\main\\java\\com\\SnWWeb\\config.properties");
			// load a properties file
			prop.load(input);
			// get the property value and print it out
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return prop;
	}

	/**
	 * @param driver
	 * @return It will return loginPage Page Object.
	 */
	public LoginPage setUpProfile(WebDriver driver) {
		
		DOMConfigurator.configure("log4j.xml");
		Properties prop = load_data();
		String base_url = prop.getProperty("url");
		driver.get(base_url);
		LoginPage loginPage = new LoginPage(driver, prop);
		return loginPage;
	}
	
	/**
	 * @info Method to close the current browser page 
	 * @param driver
	 */
	public void closeBrowser(WebDriver driver){
		driver.close();
	}

}
