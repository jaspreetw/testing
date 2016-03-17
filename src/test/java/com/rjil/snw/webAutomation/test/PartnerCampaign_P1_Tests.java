package com.rjil.snw.webAutomation.test;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.rjil.snw.webAutomation.BrowserSetUp;
import com.rjil.snw.webAutomation.TestSetUp;
import com.rjil.snw.webAutomation.pageObjects.AdminHomePage;
import com.rjil.snw.webAutomation.pageObjects.CampaignPage;
import com.rjil.snw.webAutomation.pageObjects.HomePage;
import com.rjil.snw.webAutomation.pageObjects.LoginPage;
import com.rjil.snw.webAutomation.utility.Log;

import junit.framework.Assert;

/**
 * @author Manisha.Kamble
 *
 */
public class PartnerCampaign_P1_Tests {
	WebDriver driver;
	static LoginPage lp;
	static HomePage hp;
	static AdminHomePage ahp;
	static CampaignPage cp;

	@BeforeMethod

	/**
	 * Step performed by this method are 1. Create a WebDriver 2. login via
	 * Partner
	 */

	@Parameters({ "username", "password", "FireFoxPath" })
	public void testsetUp(String username, String password, String FireFoxPath)
			throws SQLException, InterruptedException {
		DOMConfigurator.configure("log4j.xml");
		Log.info("preparing the set up test");
		try {
			driver = BrowserSetUp.appSetUp("SnW", "Firefox", FireFoxPath);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TestSetUp testSetting = new TestSetUp();

		LoginPage lp = testSetting.setUpProfile(driver);
		lp.clickLoginButton();
		lp.enterEmail(username);
		lp.enterPassword(password);
		hp = lp.Performlogin();
		Log.info("Patner login done. Proceeding for testcase now");
	}

	/**
	 * @info TC -24 To verify the all tabs available on page after successful
	 *       login
	 */
		 @Test(priority = 0) public void VerifyTabsOnPartnerPage() { String
		 headingName = hp.getPageHeading(); 
		 assertEquals(headingName,"Dashboard", Log.info("Dashboard page verified"));
		 hp.clickOnReportTab(); String headingName1 = hp.getPageHeading();
		 assertEquals(headingName1, "Reports", Log.info("Report page verified")); 
		 hp.clickOnCampaginTab(); String headingName2 =
		 hp.getPageHeading(); 
		 assertEquals(headingName2, "My campaigns",
		 Log.info("My campaigns page verified")); hp.clickOnOfferTab(); String
		 headingName3 = hp.getPageHeading(); 
		 assertEquals(headingName3,"Consumer Offers", Log.info("Consumer Offers page verified"));
		 hp.clickOnChangePasswordTab(); String headingName4 =
		 hp.getPageHeading(); 
		 assertEquals(headingName4, "Change Password",
		 Log.info("Change Password page verified"));
		 }

	/**
	 * @info TC -27 verify that after clicking on create new camapaign button
	 *       user redirects to create campaign page
	 */

	
	 @Test(priority = 1) 
	 public void VerifyCreateCampaignpage() {
	 hp.clickOnCampaginTab(); cp = new CampaignPage(driver);
	 cp.clickOnCreateNewCampaign(); cp.checkCreateCampaignPage(); }
	 

	/**
	 * @info TC -33 verify the error messages if clicked create campaign button
	 *       without entering all mandatory parameters
	 * 
	 */
	
	 @Test(priority = 2) 
	 public void createCampaignWithoutAllMandatoryParmeters() { hp.clickOnCampaginTab();
	 cp = new CampaignPage(driver); cp.clickOnCreateNewCampaign();
	 cp.clickOnCreateCampaignButton(); cp.checkErrorMessageForCampaginName();
	 Log.info("ErrorMessage For CampaginName verified");
	 cp.checkErrorMessageForStartDate(); Log.info(
	 "ErrorMessage For StartDate verified"); 
	 cp.checkErrorMessageForEndDate();
	 Log.info("ErrorMessage For EndDate verified"); }
	 

	/**
	 * @info TC -34.1 verify the error messages if clicked create campaign
	 *       button without entering End date
	 * 
	 */

	
	 @Test(priority = 3)
	 
	 @Parameters({ "CampaignName"}) 
	 public void CreateCampaignWithoutStartDate(String CampaignName) {
	 hp.clickOnCampaginTab(); cp = new CampaignPage(driver);
	 cp.clickOnCreateNewCampaign(); cp.clickOnCreateCampaignButton();
	 cp.enterCampaginName(CampaignName); Log.info("Campaign name is entered");
	 cp.CampaginStartDate(); Log.info("Campaign Start date is selected");
	 cp.clickOnCreateCampaignButton(); cp.checkErrorMessageForEndDate();
	 Log.info("ErrorMessage For EndDate verified");
	 
	 }
	 

	/**
	 * @info TC -34.2 verify the error messages if clicked create campaign
	 *       button without entering Start date
	 * 
	 */

	
	  @Test(priority = 4)
	 
	 @Parameters({ "CampaignName"}) 
	 public void CreateCampaignWithoutEndtDate(String CampaignName) {
	 hp.clickOnCampaginTab(); cp = new CampaignPage(driver);
	 cp.clickOnCreateNewCampaign(); cp.clickOnCreateCampaignButton();
	 cp.enterCampaginName(CampaignName); Log.info("Campaign name is entered");
	 cp.CampaginEndDate(); Log.info("Campaign End date is selected");
	 cp.clickOnCreateCampaignButton(); cp.checkErrorMessageForStartDate();
	 Log.info("ErrorMessage For StartDate verified");
	 
	 }
	 

	/**
	 * @info TC -34.3 verify the error messages if clicked create campaign
	 *       button without entering campaign name
	 * 
	 */

	@Test(priority = 5)
	public void CreateCampaignWithoutCampaignName() {
		hp.clickOnCampaginTab();
		cp = new CampaignPage(driver);
		cp.clickOnCreateNewCampaign();
		cp.clickOnCreateCampaignButton();
		cp.CampaginStartDate();
		cp.CampaginEndDate();
		cp.clickOnCreateCampaignButton();
		cp.checkErrorMessageForCampaginName();
		Log.info("ErrorMessage For CampaginName verified");

	}
	 

	/**
	 * @throws InterruptedException
	 * @info TC -35 verify the error messages if clicked create campaign button
	 *       without entering No of installs required
	 * 
	 */

	@Test(priority = 6)
	@Parameters({ "CampaignName" })
	public void CreateCampaignWithoutNoOfInstalls(String CampaignName) throws InterruptedException {
		hp.clickOnCampaginTab();
		cp = new CampaignPage(driver);
		cp.clickOnCreateNewCampaign();
		cp.clickOnCreateCampaignButton();
		cp.enterCampaginName(CampaignName);
		cp.CampaginStartDate();
		cp.CampaginEndDate();
		cp.clickOnUnlimitedCheckBox();
		cp.clickOnCreateCampaignButton();
		cp.checkErrorMessageForNoOfInstalls();
		Log.info("ErrorMessage For No of Installs verified");

	}

	@Test
	public void checkNewCampaignButtonEnabled() {
		cp = new CampaignPage(driver);
		cp.clickOnCreateNewCampaign();
		Assert.assertTrue(cp.isCreateNewCampaignEnabled());
	}

	@Test
	public void checkCampaignHistoryDisplayed() {
		hp.clickOnCampaginTab();
		cp = new CampaignPage(driver);
		Assert.assertTrue(cp.isCampaignHistoryVisible());
	}

	@Test
	public void defaultValuesRetained() {
		hp.clickOnCampaginTab();
		System.out.println("my campaign clicked");
		cp = new CampaignPage(driver);
		cp.changeDefaultValue();
		System.out.println("default changed");
		hp.switchTab();
		System.out.println("tab switched");
		Assert.assertTrue(cp.checkDefaultValues());
	}
	
}




