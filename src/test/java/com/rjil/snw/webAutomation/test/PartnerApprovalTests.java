package com.rjil.snw.webAutomation.test;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.rjil.snw.webAutomation.BrowserSetUp;
import com.rjil.snw.webAutomation.TestSetUp;
import com.rjil.snw.webAutomation.pageObjects.AdminHomePage;
import com.rjil.snw.webAutomation.pageObjects.HomePage;
import com.rjil.snw.webAutomation.pageObjects.LoginPage;
import com.rjil.snw.webAutomation.utility.Log;

public class PartnerApprovalTests {
	WebDriver driver;
	static LoginPage lp;
	static HomePage hp;
	static AdminHomePage ahp;


    @BeforeMethod
    
    
     /** Step performed by this method are
     * 1. Create a WebDriver
     * 2. login via Partner.
     * 3. Upload a App
     * 4. logout*/
     
    
	@Parameters({ "username", "password", "appName", "appVersion", "appPackage", "iconFileName", "appFileName",
		"admin_username", "admin_password", "FireFoxPath" })
	public void testsetUp(String username, String password, String appName, String appVersion, String appPackage,
			String iconFileName, String appFileName, String admin_username, String admin_password, String FireFoxPath)
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
		lp = testSetting.setUpProfile(driver);
		lp.clickLoginButton();
		lp.enterEmail(username);
		lp.enterPassword(password);
		hp = lp.Performlogin();
		hp.clickUploadButton();
		hp.enterAppName(appName);
		hp.enterAppPackage(appPackage);
		hp.enterAppVersion(appVersion);
		hp.uploadApp(iconFileName, appFileName, true);
		hp.logout();
		Log.info("Set done. Proceeding for testcase now");
	}
    
    @Test
    
    /**
     * In this test, We inten to verify that Campaian is disabled for deactivated 
     * App 
     
     */
    
	@Parameters({ "admin_username", "admin_password", "appName" })
	public void checkIfAppApprovalComes(String admin_username, String admin_password, String appName) {
		Log.info("Testcase to verify if approval comes and campaian is disabled bt default");
		lp.clickLoginButton();
		lp.enterEmail(admin_username);
		lp.enterPassword(admin_password);
		ahp = lp.Adminlogin();
		boolean ifApprovalComes = ahp.approveApp(appName);
		assertEquals(ifApprovalComes, true);
		boolean ifCampaianDisabled = ahp.checkCurrentCampaianStatus(appName);
		assertEquals(ifCampaianDisabled, false, Log.info("Campainan is disabled. test passed"));
		ahp.logout();
	}

	@Test
	
	/**
     * In this test, We intend to verify that deactivated partner can not login
    */
	
	@Parameters({ "admin_username", "admin_password", "appName", "username", "password" })
	public void checkDeactiveApp(String admin_username, String admin_password, String appName, String username,
			String password) throws InterruptedException {
		Log.info("Testcase to verify Partner can not login after app deactivation");
		lp.clickLoginButton();
		lp.enterEmail(admin_username);
		lp.enterPassword(admin_password);
		ahp = lp.Adminlogin();
		boolean ifApproved = ahp.approveApp(appName);
		assertEquals(ifApproved, true);
		ahp.switchAccountStatus(false, appName);
		lp = ahp.logout();
		lp.clickLoginButton();
		lp.enterEmail(username);
		lp.enterPassword(password);
		hp = lp.Performlogin();
		boolean checkhome = hp.checkHomePage();
		assertEquals(checkhome, false, Log.info("could not make into HomePage. Test passed"));
		Thread.sleep(2000);
	}

	@Test
	/**
     * In this test, We intend to verify that deactivated partner shows as inactive with date 
     */
	@Parameters({"admin_username", "admin_password", "appName",  "username", "password" })
	public void checkDeactivePartnerLogin(String admin_username, String admin_password, String appName, String username,
			String password) {
		Log.info("Testcase to verify deactivated account date of inactive ");
		lp.clickLoginButton();
		lp.enterEmail(admin_username);
		lp.enterPassword(admin_password);
		ahp = lp.Adminlogin();
		boolean ifApproved = ahp.approveApp(appName);
		assertEquals(ifApproved, true, Log.error("App not approved"));
		ahp.switchAccountStatus(false, appName);
		boolean ifinactiveWithDate = ahp.checkAccountStatus(appName);
		assertEquals(ifinactiveWithDate, false, Log.info("App is inactive with correct date. test passed"));
		ahp.logout();
	}

	@Test
	
	/**
	 *In this test, We intend to verify whether all runing offers/Campaign are stopped afterS Deactivating
    */
	
	@Parameters({ "admin_username", "admin_password", "appName", "username", "password" })
	public void checkDeactivePartnerStatus(String admin_username, String admin_password, String appName,
			String username, String password) {
		Log.info("Testcase to verify whether all runing offers/Campaign are stopped after Deactivating");
		lp.clickLoginButton();
		lp.enterEmail(admin_username);
		lp.enterPassword(admin_password);
		ahp = lp.Adminlogin();
		boolean ifApproved = ahp.approveApp(appName);
		assertEquals(ifApproved, true);
		ahp.switchAccountStatus(false, appName);
		boolean ifstatus = ahp.checkAccountStatus(appName);
		assertEquals(ifstatus, false, Log.info("Desired status came. test passed"));
	}

	@Test
	
	 /**
	 *In this test, We intend to verify whether offers/Campaign are disabled after Deactivating
	*/
	
	@Parameters({ "admin_username", "admin_password", "appName"})
	public void checkDeactivePartnerCampianaOfferStatus(String admin_username, String admin_password, String appName) {
		Log.info("Testcase to verify whether all runing offers/Campaign are disabled after Deactivating");
		LoginPage lp = PartnerApprovalTests.lp;
		lp.clickLoginButton();
		lp.enterEmail(admin_username);
		lp.enterPassword(admin_password);
		ahp = lp.Adminlogin();
		boolean ifApproved = ahp.approveApp(appName);
		assertEquals(ifApproved, true);
		Log.info("Deactivating the App");
		ahp.switchAccountStatus(false, appName);
		boolean ifactivated = ahp.checkAccountAction(appName);
		assertEquals(ifactivated, false, Log.info("App is deactiavated"));
		boolean ifCampiananstatus = ahp.checkCurrentCampaianStatus(appName);
		assertEquals(ifCampiananstatus, false, Log.info("Campaign status is disabled now"));
		boolean ifOfferstatus = ahp.checkCurrentOfferStatus(appName);
		assertEquals(ifOfferstatus, false, Log.info("Campaign status is disabled now"));
	}
	
	@Test
	
	/**
	*In this test, We intend to verify whether admin can reactivate the account
	*/

	@Parameters({ "admin_username", "admin_password", "appName"})
	public void checkifAdminCanActivatePartner(String admin_username, String admin_password, String appName) {
		Log.info("Testcase to verify whether all runing offers/Campaign are disabled after Deactivating");
		LoginPage lp = PartnerApprovalTests.lp;
		lp.clickLoginButton();
		lp.enterEmail(admin_username);
		lp.enterPassword(admin_password);
		ahp = lp.Adminlogin();
		boolean ifApproved = ahp.approveApp(appName);
		assertEquals(ifApproved, true);
		Log.info("Deactivating the App");
		ahp.switchAccountStatus(false, appName);
		boolean ifactivated = ahp.checkAccountAction(appName);
		assertEquals(ifactivated, false, Log.info("App is deactiavated"));
		Log.info("Re-activating the App");
		ahp.switchAccountStatus(true, appName);
		boolean ifactivatedagain = ahp.checkAccountAction(appName);
		assertEquals(ifactivatedagain, true, Log.info("App is re-actiavated"));
	}
		
	@Test
	@Parameters({ "appName", "admin_username", "admin_password" })
	public void verifyApprovalStatus( String appName, String admin_username, String admin_password ) throws InterruptedException {
		lp.clickLoginButton();
		lp.enterEmail(admin_username);
		lp.enterPassword(admin_password);
		ahp = lp.Adminlogin();
		ahp.approveApp(appName);
		ahp.clickHistoryTab();
		ahp.checkAppApproveStatus(appName);
	}
	

    @AfterMethod
    
    /**
     In this method, This active our test app and delete it from the 
	 * Database so enabling us to start all over again in new test even if present test
	 * failed.
	 */
	 
    
	@Parameters({ "appName", "appPackage", "admin_username", "admin_password", "FireFoxPath" })
	public void afterSuite(String appName, String appPackage, String admin_username, String admin_password, String FireFoxPath)
			throws SQLException, InterruptedException {
		DOMConfigurator.configure("log4j.xml");
		Log.info("Test finished. Working on cleanup now");
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
		lp.enterEmail(admin_username);
		lp.enterPassword(admin_password);
		AdminHomePage hp = lp.Adminlogin();
		hp.switchAccountStatus(true, appName);
		boolean ifactivatedagain = hp.checkAccountAction(appName);
		assertEquals(ifactivatedagain, true, Log.info("App is ready to delete now"));
		hp.deleteApp(appPackage);
		hp.updateAppVersion("0.0.1", appName);
		driver.quit();
		Log.info("clean up done");
	}
}


