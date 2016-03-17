package com.rjil.snw.webAutomation.test;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.rjil.snw.webAutomation.BrowserSetUp;
import com.rjil.snw.webAutomation.TestSetUp;
import com.rjil.snw.webAutomation.pageObjects.AdminHomePage;
import com.rjil.snw.webAutomation.pageObjects.HomePage;
import com.rjil.snw.webAutomation.pageObjects.LoginPage;

public class ApprovalStatusTest {

	public Properties prop;
	public WebDriver driver;
	static LoginPage lp;
	static HomePage hp;
	static AdminHomePage ahp;

	@BeforeMethod
	@Parameters({ "username", "password", "FireFoxPath"})
	public void testsetUp(String username, String password, String FireFoxPath)
			throws SQLException, InterruptedException {
		System.out.println("Test started");
		try {
			driver = BrowserSetUp.appSetUp("SnW", "Firefox", FireFoxPath);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TestSetUp testSetting = new TestSetUp();
		LoginPage lp = testSetting.setUpProfile(driver);
		ApprovalStatusTest.lp = lp;
	}


	@Test
	@Parameters({ "appName", "admin_username", "admin_password", })
	public void verifyApprovalStatus(String username, String password,
			String appName, String admin_username,
			String admin_password) throws InterruptedException {
		LoginPage lp = ApprovalStatusTest.lp;
		lp.clickLoginButton();
		lp.enterEmail(admin_username);
		lp.enterPassword(admin_password);
		ahp = lp.Adminlogin();
		ahp.approveApp(appName);
		ahp.clickHistoryTab();
		ahp.checkAppApproveStatus(appName);
	}

}
