package com.rjil.snw.webAutomation.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.rjil.snw.webAutomation.BrowserSetUp;
import com.rjil.snw.webAutomation.TestSetUp;
import com.rjil.snw.webAutomation.pageObjects.AdminHomePage;
import com.rjil.snw.webAutomation.pageObjects.HomePage;
import com.rjil.snw.webAutomation.pageObjects.LoginPage;
import com.rjil.snw.webAutomation.utility.Log;

public class PartnerDashboard_P1_Tests {
	WebDriver driver;
	static LoginPage lp;
	static HomePage hp;
	static AdminHomePage ahp;

	@BeforeSuite
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
		lp = testSetting.setUpProfile(driver);
		lp.clickLoginButton();
		lp.enterEmail(username);
		lp.enterPassword(password);
		hp = lp.Performlogin();
	}

	@Test(priority = 0)
	public void CheckDashBoard_01() {
		String headingName = hp.getPageHeading();
		assertEquals(headingName, "Dashboard", Log.info("Dashboard page verified"));
	}

	@Test(priority = 0)
	public void CheckDate_02_07() {
		String fromDate = hp.getStartDate();
		String tillDate = hp.getEndDate();
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		assertEquals(tillDate, strDate, Log.info("Last date is verified. It is showing today's date"));
		Date expectedfromDate = hp.previousDaysFromNow(-30);
		String strfromDate = sdfDate.format(expectedfromDate);
		assertEquals(fromDate, strfromDate, Log.info("start date verified"));
	}

	@Test(priority = 0)
	public void verifyifDateDisplayed_03() {
		boolean startdate = hp.checkifStartDateVisible();
		assertEquals(startdate, true, "start date field is not visible");
		Log.info("start date field verified");
		boolean tillDate = hp.checkifEndDateVisible();
		assertEquals(tillDate, true, "end date field is not visible");
		Log.info("till date field verified");
	}

	@Test(priority = 0)
	public void verifyZeroInstallations_04() {
		String number = hp.getInstallationCount();
		assertEquals(number, "0", "Number of installation is not zero as expected");
		Log.info("Number of installations verified");
	}

	@Test(priority = 0)
	public void verifyPartnerDashBoard_05() {
		String citydata = hp.getCitydata();
		assertEquals(citydata, "No installation records found", "Some entries are found in city data");
		Log.info("City data verified");
		String osdata = hp.getOSdata();
		assertEquals(osdata, "No installation records found", "Some entries are found in OS data");
		Log.info("OS data verified");
		String modaldata = hp.getModaldata();
		assertEquals(modaldata, "No installation records found", "Some entries are found in Modal data");
		Log.info("Modal data verified");
	}

	@Test(priority = 0)
	@Parameters({ "partnerName" })
	public void verifyPartnerName_06(String partnerName) {
		String partnerNamePortal = hp.partnerName();
		assertTrue(partnerNamePortal.contains(partnerName), "Partner name did not showed up");
		Log.info("partner name verified");
	}

	@Test(priority = 0)
	public void verifyPartnerDashBoard_newDate_08() {
		hp.changeStartDate("15");
		// taking 15th of previous month as start date
		String citydata = hp.getCitydata();
		assertEquals(citydata, "No installation records found", "Some entries are found in city data");
		Log.info("City data verified");
		String osdata = hp.getOSdata();
		assertEquals(osdata, "No installation records found", "Some entries are found in OS data");
		Log.info("OS data verified");
		String modaldata = hp.getModaldata();
		assertEquals(modaldata, "No installation records found", "Some entries are found in Modal data");
		Log.info("Modal data verified");
	}

	@Test(priority = 1)
	@Parameters({ "admin_username", "admin_password", "appName", "appVersion", "appPackage", "iconFileName",
			"appFileName", "username", "password" })
	public void verifyUploadTests_9_10_11_12_13_14_15_16(String admin_username, String admin_password, String appName,
			String appVersion, String appPackage, String iconFileName, String appFileName, String username,
			String password) throws InterruptedException, SQLException {
		hp.clickUploadButton();
		hp.enterAppName(appName);
		Log.info("Uploading APK TC 14");
		hp.enterAppPackage(appPackage);
		hp.enterAppVersion(appVersion);
		Log.info("Updating OS verison TC 15");
		hp.selectOSversion("Android 4.4 KitKat (API level 19)");
		boolean ifNewIconSelected = hp.checkifnewAppRadioIsSelected();
		assertEquals(ifNewIconSelected, true, "New icon not selected. TC 13 failed");
		Log.info("Upload via new icon radio button. TC 11");
		Log.info("Upload App icon as .PNG file which is more than 100 * 100 px. TC 9 and 10");
		hp.uploadApp(iconFileName, appFileName, true);
		boolean ifUploaded = hp.verifySuccessfulUpload();
		assertEquals(ifUploaded, true, "App failed to upload");
		lp = hp.logout();
		lp.clickLoginButton();
		lp.enterEmail(admin_username);
		lp.enterPassword(admin_password);
		ahp = lp.Adminlogin();
		boolean ifapproved = ahp.approveApp(appName);
		assertEquals(ifapproved, true, "App is not approved");
		ahp.updateAppVersion("0.0.1", appName);
		lp = ahp.logout();
		lp.clickLoginButton();
		lp.enterEmail(username);
		lp.enterPassword(password);
		hp = lp.Performlogin();
	}

	@Test(priority = 2)
	@Parameters({ "admin_username", "admin_password", "appName", "appVersion", "appPackage", "iconFileName",
			"appFileName", "username", "password" })
	public void verifyUploadTestExistingIcon_TC_12(String admin_username, String admin_password, String appName,
			String appVersion, String appPackage, String iconFileName, String appFileName, String username,
			String password) throws InterruptedException {
		hp.clickUploadButton();
		hp.enterAppName(appName);
		hp.enterAppPackage(appPackage);
		hp.enterAppVersion(appVersion);
		hp.selectExistingIconRadio();
		hp.uploadApp(iconFileName, appFileName, false);
		boolean ifUploaded = hp.verifySuccessfulUpload();
		assertEquals(ifUploaded, true, "App failed to upload");
		Log.info("Upload App with existing icon successfull");
		lp = hp.logout();
		lp.clickLoginButton();
		lp.enterEmail(admin_username);
		lp.enterPassword(admin_password);
		ahp = lp.Adminlogin();
		boolean ifapproved = ahp.approveApp(appName);
		lp = ahp.logout();
		assertEquals(ifapproved, true, "App is not approved");
		lp.clickLoginButton();
		lp.enterEmail(username);
		lp.enterPassword(password);
		hp = lp.Performlogin();
	}

	@Test(priority = 3)
	public void verifyUpdateAppHistory_17() {
		hp.clickUploadButton();
		int count = hp.countUpdateHistoryTable();
		System.out.println(count);
		assertEquals(count, 5, "Update History not showing last 5 entries");
	}

	@Test(priority = 3)
	public void verifyDownloadButton_18() throws InterruptedException {
		hp.clickUploadButton();
		hp.downloadApk();
	}

	@Test(priority = 0)
	public void verifyUpdateAppHistory_newUser_19() {
		hp.clickUploadButton();
		int count = hp.countUpdateHistoryTable();
		System.out.println(count);
		assertEquals(count, 0, "Update History not showing any entry");
	}

	@Test(priority = 0)
	@Parameters({ "appName", "appVersion", "invalidappPackage", "iconFileName", "appFileName", "username", "password" })
	public void verifyAppPackage(String appName, String appVersion, String invalidappPackage, String iconFileName,
			String appFileName, String username, String password) throws InterruptedException, SQLException {
		hp.clickUploadButton();
		hp.enterAppName(appName);
		Log.info("Uploading APK with invalid package name");
		hp.enterAppPackage(invalidappPackage);
		hp.enterAppVersion(appVersion);
		hp.uploadApp(appFileName, iconFileName, true);
		hp.verifyUnsuccessfulUpload();
	}

	@Test(priority = 0)
	@Parameters({ "admin_username", "admin_password", "appName", "appVersion", "appPackage", "iconFileName",
			"invalidappFileName", "username", "password", "FireFoxPath" })
	public void verifyAppFile(String admin_username, String admin_password, String appName, String appVersion,
			String appPackage, String iconFileName, String invalidappFileName, String username, String password,
			String FireFoxPath) throws InterruptedException, SQLException {
		hp.clickUploadButton();
		hp.enterAppName(appName);
		hp.enterAppPackage(appPackage);
		hp.enterAppVersion(appVersion);
		Log.info("Uploading APK with invalid app file");
		hp.uploadApp(invalidappFileName, iconFileName, true);
		String msg = hp.pageError();
		assertTrue(msg.contains("Oops! Something Went wrong!"), "Error massge did not showed up");
		TestSetUp testSetting = new TestSetUp();
		testSetting.closeBrowser(driver);
		testsetUp(username, password, FireFoxPath);
	}

	@AfterSuite
	@Parameters({ "admin_username", "admin_password", "appName", "appPackage" })
	public void testcleanUp(String admin_username, String admin_password, String appName, String appPackage)
			throws SQLException {
		lp = hp.logout();
		lp.clickLoginButton();
		lp.enterEmail(admin_username);
		lp.enterPassword(admin_password);
		ahp = lp.Adminlogin();
		ahp.updateAppVersion("0.0.1", appName);
		ahp.deleteApp(appPackage);
		ahp.logout();
	}

}
