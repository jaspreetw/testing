package com.rjil.snw.webAutomation.test;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.rjil.snw.webAutomation.BrowserSetUp;
import com.rjil.snw.webAutomation.TestSetUp;
import com.rjil.snw.webAutomation.pageObjects.HomePage;
import com.rjil.snw.webAutomation.pageObjects.LoginPage;
import com.rjil.snw.webAutomation.pageObjects.RetailerHomePage;

public class RetailLoginTest {
	WebDriver driver;
	static LoginPage lp;
	static HomePage hp;
	static RetailerHomePage rhp;
	
	@BeforeMethod
	@Parameters({ "Retailusername", "Retailpassword", "FireFoxPath" })
	public void testsetUp(String username, String password,String FireFoxPath)
					throws SQLException, InterruptedException {
		try {
			driver = BrowserSetUp.appSetUp("SnW", "Firefox", FireFoxPath);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TestSetUp testSetting = new TestSetUp();
		LoginPage lp = testSetting.setUpProfile(driver);
		RetailLoginTest.lp = lp;
	}
	
	@Test
	@Parameters({ "Retailusername", "Retailpassword" })
	public void RetailloginTest(String Retailusername, String Retailpassword) {
		  lp.clickLoginButton();
		  lp.enterEmail(Retailusername);
		  lp.enterPassword(Retailpassword);
		  RetailerHomePage rhp = lp.Retaillogin();
		  rhp.logout();
	}
	}

