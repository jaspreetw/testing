package com.rjil.snw.webAutomation.pageObjects;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.rjil.cloud.selenium.fitnesse.KeywordMethods;
import com.rjil.snw.webAutomation.interfaces.HomePageInterface;
import com.rjil.snw.webAutomation.utility.Log;

/**
 * @author Anand.Bajpai
 *
 */
public class HomePage implements HomePageInterface {
	public Properties prop;
	public WebDriver driver;
	KeywordMethods methods_snw = new KeywordMethods();

	public HomePage(WebDriver driver, Properties prop) {
		driver.manage().window().maximize();
		this.prop = prop;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Below is set of locators for Home Page
	 */

	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Upload")
	private WebElement upload_button;
	@FindBy(how = How.ID, using = "uploadPForm:appName")
	private WebElement appName;
	@FindBy(how = How.ID, using = "uploadPForm:appVersion")
	private WebElement appVersion;
	@FindBy(how = How.ID, using = "uploadPForm:appPackage")
	private WebElement appPackage;
	@FindBy(how = How.ID, using = "uploadPForm:upload_button")
	private WebElement submit_app;
	@FindBy(how = How.LINK_TEXT, using = "Logout")
	private WebElement logoutLink;
	@FindBy(how = How.CLASS_NAME, using = "heading")
	private WebElement pageHeadingName;
	@FindBy(how = How.ID, using = "LayoutForm:dpd1")
	private WebElement startDate;
	@FindBy(how = How.ID, using = "LayoutForm:dpd2")
	private WebElement endDate;
	@FindBy(how = How.XPATH, using = ".//*[@id='__bookmark_1']/tbody/tr[1]/td/div")
	private WebElement installationsCount;
	@FindBy(how = How.ID, using = "iframe_dashboard")
	private WebElement reportDashBoard;
	@FindBy(how = How.XPATH, using = ".//*[@id='__bookmark_3']")
	private WebElement cityData;
	@FindBy(how = How.XPATH, using = ".//*[@id='__bookmark_5']")
	private WebElement osData;
	@FindBy(how = How.XPATH, using = ".//*[@id='__bookmark_7']")
	private WebElement modalData;
	@FindBy(how = How.CLASS_NAME, using = "welcomeMessage")
	private WebElement partnerName;
	@FindBy(how = How.CSS, using = ".datetimepicker.datetimepicker-dropdown-bottom-right.dropdown-menu")
	private WebElement calXpath;
	@FindBy(how = How.NAME, using = "LayoutForm:j_idt82")
	private WebElement dateRangeSubmit;
	@FindBy(how = How.ID, using = "uploadPForm:majVer")
	private WebElement versionDropdown;
	@FindBy(how = How.ID, using = "uploadPForm:j_idt67:1")
	private WebElement newAppRadio;
	@FindBy(how = How.ID, using = "uploadPForm:j_idt67:0")
	private WebElement existingAppRadio;
	@FindBy(how = How.CLASS_NAME, using = "ui-messages-info-summary")
	private WebElement successMsg;
	@FindBy(how = How.ID, using = "iframe_upload")
	private WebElement updateHistoryTable;
	@FindBy(how = How.CLASS_NAME, using = "style_5")
	private List<WebElement> myApps;
	@FindBy(how = How.XPATH, using = "//*[@type='submit']")
	private WebElement downloadLink;
	@FindBy(how = How.CLASS_NAME, using = "ui-messages-info-summary")
	private WebElement errorMsgForAppUpload;
	@FindBy(how = How.XPATH, using = "//center")
	private WebElement pageErrorMsg;
	@FindBy(how = How.XPATH, using = "//*[@id='n2']/a/span")
	private WebElement Report_Tab;
	@FindBy(how = How.XPATH, using = "//*[@id='n3']/a/span")
	private WebElement Upload_Tab;
	@FindBy(how = How.XPATH, using = "//*[@id='n4']/a/span")
	private WebElement My_Campaign_Tab;
	@FindBy(how = How.XPATH, using = "//*[@id='n6']/a/span")
	private WebElement Consumer_Offer_Tab;
	@FindBy(how = How.XPATH, using = "//*[@id='n7']/a/span")
	private WebElement Change_Password_Tab;

	/*
	 * Below is the set of methods for clicking/sending-some-text for
	 * WebElements.
	 * 
	 */

	public void selectExistingIconRadio() {
		existingAppRadio.click();
	}

	public void clickUploadButton() {
		upload_button.click();
	}

	public void enterAppName(String app) {
		appName.sendKeys(app);
	}

	public void enterAppPackage(String packageName) {
		appPackage.sendKeys(packageName);
	}

	public void enterAppVersion(String version) {
		appVersion.sendKeys(version);
	}

	public void submitApp() {
		submit_app.click();
	}

	public String getPageHeading() {
		return pageHeadingName.getText();
	}

	public String getStartDate() {
		return startDate.getAttribute("value");
	}

	public String getEndDate() {
		return endDate.getAttribute("value");
	}

	public boolean checkifStartDateVisible() {
		return startDate.isDisplayed();
	}

	public boolean checkifEndDateVisible() {
		return endDate.isDisplayed();
	}

	public String partnerName() {
		return partnerName.getText();
	}

	public void changeStartDate(String day) {
		startDate.click();
		selectDatefromMultiDate(day);
		dateRangeSubmit.submit();
	}

	/**
	 * @param day: day date of month 1-30
	 * @info: This method select the day in currently displayed month
	 * (Currently this supports day change only. Can be extended to months and 
	 * years if needed.)
	 */
	public void selectDatefromMultiDate(String day) {

		List<WebElement> allDays = calXpath.findElements(By.className("day"));
		for (WebElement element : allDays) {
			if (element.getText().contains(day)) {
				element.click();
				return;
			}
		}

	}

	/**
	 * @info this method is to get numbers of installations of given app
	 * @return number of installation in history
	 * any number greater than 5 is bad news.
	 */
	public String getInstallationCount() {
		driver.switchTo().frame(reportDashBoard);
		String number = installationsCount.getText();
		driver.switchTo().parentFrame();
		return number;
	}

	/**
	 * @info This method get data from city table.
	 * @return All city data in text format
	 */
	public String getCitydata() {
		driver.switchTo().frame(reportDashBoard);
		String data = cityData.getText();
		driver.switchTo().parentFrame();
		return data;
	}

	/**
	 * @info This method get data from OS table.
	 * @return All OS data in text format
	 */
	public String getOSdata() {
		driver.switchTo().frame(reportDashBoard);
		String data = osData.getText();
		driver.switchTo().parentFrame();
		return data;
	}

	/**
	 * @info This method get data from modal table.
	 * @return All modal data in text format
	 */
	public String getModaldata() {
		driver.switchTo().frame(reportDashBoard);
		String data = modalData.getText();
		driver.switchTo().parentFrame();
		return data;
	}

	/**
	 * @info This method is to select OS version
	 */
	public void selectOSversion(String OS_version) {
		Select dropdown = new Select(versionDropdown);
		dropdown.selectByVisibleText(OS_version);
	}

	/**
	 * @info Verify if new radio icon button is selected
	 * @return Boolean true for selected false for reverse case.
	 */
	public boolean checkifnewAppRadioIsSelected() {
		if (newAppRadio.getAttribute("outerHTML").contains("checked=\"checked\"")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @info Verifies app success method
	 * @return Boolean true for selected false for reverse case.
	 */
	public boolean verifySuccessfulUpload() {
		if (successMsg.getText().contains("Successfully Uploaded")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @info This method is to get app version history count
	 * @return count of version uploaded for given app
	 */
	public int countUpdateHistoryTable() {
		driver.switchTo().frame(updateHistoryTable);
		int count = myApps.size();
		driver.switchTo().parentFrame();
		return count;
	}

	/**
	 * @info This method is to download apk.
	 * @info Currently it will download first app in the list.
	 * @info can be extaned for user input if testcase comes
	 * @throws InterruptedException
	 */
	public void downloadApk() throws InterruptedException {
		driver.switchTo().frame(updateHistoryTable);
		downloadLink.click();
		Thread.sleep(1000);
		downloadLink.sendKeys(Keys.ENTER);
		driver.switchTo().parentFrame();
	}

	/**
	 * @param iconFileName
	 * @param appFileName
	 * @param newIcon
	 * @info This method will upload the app SnW
	 * @throws InterruptedException
	 */
	public void uploadApp(String iconFileName, String appFileName, boolean newIcon) throws InterruptedException {
		String uploadIconXpath = "//td[contains(text(),'Upload icon:*')]";
		String uploadappXpath = "//td[contains(text(),'Upload APK:*')]";
		String appDirPath = prop.getProperty("appDir");
		String iconPath = appDirPath + "\\" + iconFileName;
		String appFilePath = appDirPath + "\\" + appFileName;
		methods_snw.selectFilesFromLocalDrive(this.driver, uploadappXpath, iconPath);
		Thread.sleep(15000);
		if (newIcon) {
			methods_snw.selectFilesFromLocalDrive(this.driver, uploadIconXpath, appFilePath);
			Thread.sleep(15000);
		}
		submitApp();
		Thread.sleep(15000);
	}

	/**
	 * @info This method will verify whether we are on correct page
	 * @return Boolean
	 */
	public boolean checkHomePage() {
		if (driver.findElements(By.linkText("Logout")).size() != 0) {
			Log.info("We are on homePage");
			return true;
		} else {
			Log.info("We are not on homePage");
			return false;
		}
	}

	/**
	 * @info This method logout form home Page
	 * @return loginPage 
	 */
	public LoginPage logout() {
		logoutLink.click();
		LoginPage loginPage = new LoginPage(driver, prop);
		Log.info("Log out done for homePage");
		return loginPage;
	}

	/**
	 * @info This method is to get date of before/After "days".
	 * @info Parameter "days" can positive or negative.
	 * @param days
	 * @return Date of "days" ago 
	 */
	public static Date previousDaysFromNow(int days) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, days);
		Date result = cal.getTime();
		return result;
	}
	
	/**
	 * @info Method to verify the error message for unsuccessful app upload
	 * @return Boolean true for selected false for reverse case.
	 */
	public boolean verifyUnsuccessfulUpload() {
		if (errorMsgForAppUpload.getText().contains("App Information entered is invalid")) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @info This method is to get the error on Webpage
	 * @return it will return the page error text.
	 */
	public String pageError() {
		return pageErrorMsg.getText();
	}
	
	/**
	 * @info This method is to click on the Report tab
	 */
	public void clickOnReportTab() {
		Report_Tab.click();
	}
	
	/**
	 * @info This method is to click on the Upload tab
	 */
	public void clickOnUplodTab() {
		Report_Tab.click();
	}
	
	/**
	 * @info This method is to click on the My Campaign tab
	 */
	public void clickOnCampaginTab() {
		My_Campaign_Tab.click();
	}
	
	/**
	 * @info This method is to click on the Consumer Offer tab
	 */
	public void clickOnOfferTab() {
		Consumer_Offer_Tab.click();
	}
	
	/**
	 * @info This method is to click on the Change Password tab
	 */
	public void clickOnChangePasswordTab() {
		Change_Password_Tab.click();
	}
		 
		 public void switchTab(){
		  this.Report_Tab.click();
		  this.My_Campaign_Tab.click();
		 }

}