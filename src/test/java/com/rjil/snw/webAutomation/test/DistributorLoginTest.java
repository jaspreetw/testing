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
import com.rjil.snw.webAutomation.pageObjects.DistributorHomePage;
import com.rjil.snw.webAutomation.pageObjects.HomePage;
import com.rjil.snw.webAutomation.pageObjects.LoginPage;


	public class DistributorLoginTest {
		WebDriver driver;
		static LoginPage lp;
		static HomePage hp;
		static DistributorHomePage dhp;
		
		@BeforeMethod
		@Parameters({ "Distributorusername", "Distributorpassword", "FireFoxPath" })
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
			DistributorLoginTest.lp = lp;
		}
		@Test
		@Parameters({ "Distributorusername", "Distributorpassword"})
		public void DistributorloginTest(String Distributorusername, String Distributorpassword) {
			  lp.clickLoginButton();
			  lp.enterEmail(Distributorusername);
			  lp.enterPassword(Distributorpassword);
			  DistributorHomePage dhp = lp.Distributorlogin();
			  dhp.logout();
		}
		}

