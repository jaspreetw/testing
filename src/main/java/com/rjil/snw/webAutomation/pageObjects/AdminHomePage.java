package com.rjil.snw.webAutomation.pageObjects;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.rjil.cloud.selenium.fitnesse.KeywordMethods;
import com.rjil.snw.webAutomation.interfaces.HomePageInterface;
import com.rjil.snw.webAutomation.utility.Log;

public class AdminHomePage implements HomePageInterface {
	public Properties prop;
	public WebDriver driver;
	KeywordMethods methods_snw = new KeywordMethods();

	public AdminHomePage(WebDriver driver, Properties prop) {
		driver.manage().window().maximize();
		this.prop = prop;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(how = How.XPATH, using = "//*[@id='Apps']//*[@style='display:block;']//*[@style='display:block']//*[@class='block1']")
	private List<WebElement> allApps;
	@FindBy(how = How.XPATH, using = "//div[@class='modal-approval-size modal fade modal1 in']/div/div/div[3]/button")
	private WebElement app_confrim;
	@FindBy(how = How.XPATH, using = "//*[@class='adminAccountsTableRow']")
	private List<WebElement> allAccounts;
	@FindBy(how = How.ID, using = "adminAccounts")
	private WebElement accountTab;
	@FindBy(how = How.XPATH, using = "//*[@id='approvalConfirm']")
	private WebElement accountConfirm;
	@FindBy(how = How.LINK_TEXT, using = "Logout")
	private WebElement logoutLink;
	@FindBy(how = How.XPATH, using = "//*[@id='LayoutForm:panelPendingCount']/div/div/nav/div/ul/li[4]/a")
	private WebElement History_Tab;

	public boolean approveApp(String myAppName) {
		/*
		 * INFO: This method Get the required method from to-be-approve method
		 * list and Approves it. if App not found in the list it return false
		 * and returns true for happy condition.
		 * 
		 * INPUT: String containing desired App name
		 * 
		 * RETURN: Boolean
		 * 
		 */
		Log.info("Checking if App:" + myAppName + "came for approval");
		WebElement myElement = getAppFromList(myAppName);
		if (myElement != null) {
			Log.info(myAppName + "came for approval. Approving it now..");
			WebElement approveButton = myElement.findElement(By.id("appsEditApprove"));
			approveButton.click();
			app_confrim.click();
			if (getAccontDetails(myAppName) != null) {
				Log.info(myAppName + "is approved now");
				return true;
			} else {
				Log.info("failed to approve" + myAppName);
				return false;
			}
		} else {
			Log.info(myAppName + "did not showed up in approval list");
			return false;
		}

	}

	public WebElement getAppFromList(String myAppName) {
		/*
		 * INFO: This method search for desired app in to-be-approve list
		 * 
		 * INPUT: String containing desired App name
		 * 
		 * RETURN: WebElement ( will contain 'null' if not found )
		 */
		WebElement myElement = null;
		for (WebElement element : allApps) {
			if (element.getText().contains(myAppName)) {
				Log.info("App Found");
				return element;
			}
		}
		return myElement;
	}

	public WebElement getAccontDetails(String myAppName) {
		/*
		 * INFO: This method provides searching desired account based on App
		 * names
		 * 
		 * INPUT: String containing desired App name
		 * 
		 * RETURN: RETURN: WebElement ( will contain 'null' if not found )
		 * 
		 */
		Log.info("Searching for account details for App: " + myAppName);
		accountTab.click();
		WebElement element = null;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (WebElement myelement : allAccounts) {
			if (myelement.getText().contains(myAppName)) {
				Log.info("Account details for App" + myAppName + "found");
				return myelement;
			}
		}
		Log.info("failed to get account details for App" + myAppName);
		return element;
	}

	public void switchCampaign(boolean userValue, String myAppName) {
		/*
		 * INFO: This method switches Campaign status based on user input i.e
		 * considering false = 'disable' and true = 'enable' if user passes true
		 * this method will enable the campaign for given app and vice versa
		 * 
		 * INPUT: userValue => boolean, myAppName => String
		 * 
		 * RETURN: VOID
		 */
		Log.info("Trying to get current compaign status for app" + myAppName);
		boolean currentValue = checkCurrentCampaianStatus(myAppName);
		WebElement myAccount = getAccontDetails(myAppName);
		if (userValue != currentValue) {
			Log.info("Chaging the current compaign status for app" + myAppName);
			WebElement tab = myAccount.findElement(By.className("adminTable3 "));
			WebElement slider = tab.findElement(By.className("slider"));
			slider.click();
		} else {
			Log.info(myAppName + "is already in desired state. No Action needed");
		}
	}

	public boolean checkCurrentCampaianStatus(String myAppName) {
		/*
		 * INFO: This method will return current campaian status for given app
		 * 
		 * INPUT: myAppName => String
		 * 
		 * RETURN: boolean, true => 'enable', false => 'disable'
		 * 
		 */
		boolean currentValue;
		Log.info("Searching for current compaign status for app" + myAppName);
		WebElement myAccount = getAccontDetails(myAppName);
		WebElement myAccountCampaianStatus = myAccount.findElement(By.className("adminTable3"));
		String elementPageSource = myAccountCampaianStatus.getAttribute("outerHTML");
		boolean ifactive = checkAccountAction(myAppName);
		if (ifactive) {
			if (elementPageSource.contains("checked=" + "\"checked\"")) {
				Log.info(myAppName + " compaign is enabled");
				currentValue = true;
			} else {
				Log.info(myAppName + " compaign is disabled");
				currentValue = false;
			}
			return currentValue;
		} else {
			if (elementPageSource.contains("disabled=" + "\"disabled\"")) {
				Log.info(myAppName + " compaign is disabled");
				currentValue = false;
			} else {
				Log.error(myAppName + " compaign is enabled Which is a deactivatd app");
				currentValue = true;
			}
			return currentValue;
		}

	}
	
	public boolean checkCurrentOfferStatus(String myAppName) {
		/*
		 * INFO: This method will return current Offer status for given app
		 * 
		 * INPUT: myAppName => String
		 * 
		 * RETURN: boolean, true => 'enable', false => 'disable'
		 * 
		 */
		boolean currentValue;
		Log.info("Searching for current compaign status for app" + myAppName);
		WebElement myAccount = getAccontDetails(myAppName);
		WebElement myAccountOfferStatus = myAccount.findElement(By.className("adminTable4 "));
		String elementPageSource = myAccountOfferStatus.getAttribute("outerHTML");
		boolean ifactive = checkAccountAction(myAppName);
		if (ifactive) {
			if (elementPageSource.contains("checked=" + "\"checked\"")) {
				Log.info(myAppName + " offer is enabled");
				currentValue = true;
			} else {
				Log.info(myAppName + " offer is disabled");
				currentValue = false;
			}
			return currentValue;
		} else {
			if (elementPageSource.contains("disabled=" + "\"disabled\"")) {
				Log.info(myAppName + " offer is disabled");
				currentValue = false;
			} else {
				Log.error(myAppName + " offer is enabled Which is a deactivated app");
				currentValue = true;
			}
			return currentValue;
		}

	}

	public void switchAccountStatus(boolean userValue, String myAppName) {
		/*
		 * INFO: This method switches Account status based on user input i.e
		 * considering false = 'disable' and true = 'enable' if user passes true
		 * this method will enable the campaign for given app and vice versa
		 * 
		 * INPUT: myAppName => String
		 * 
		 * RETURN: boolean, true => 'enable', false => 'disable'
		 * 
		 */
		Log.info("Searching for current account status for app" + myAppName);
		boolean currentValue = checkAccountAction(myAppName);
		WebElement myAccount = getAccontDetails(myAppName);
		if (userValue != currentValue) {
			Log.info("Chaging the current account status for app" + myAppName);
			WebElement tab = myAccount.findElement(By.className("adminTable5"));
			WebElement slider = tab.findElement(By.id("navButton"));
			slider.click();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			app_confrim.click();
		} else {
			Log.info(myAppName + "is already in desired state. No Action needed");
		}
	}

	public boolean checkAccountAction(String myAppName) {
		/*
		 * INFO: This method will return current Account status (
		 * Active/Deactive ) for given app
		 * 
		 * INPUT: myAppName => String
		 * 
		 * RETURN: boolean, true => 'enable', false => 'disable'
		 * 
		 */
		Log.info("Checking current account status for app" + myAppName);
		boolean currentValue = false;
		WebElement myAccount = getAccontDetails(myAppName);
		WebElement Tab = myAccount.findElement(By.className("adminTable5"));
		String elementPageSource = Tab.getAttribute("outerHTML");
		if (elementPageSource.contains("deactivate.png")) {
			Log.info(myAppName + " account is activated");
			currentValue = true;
		} else {
			Log.info(myAppName + " account is deactivated");
			currentValue = false;
		}
		return currentValue;
	}

	public boolean checkAccountStatus(String myAppName) {
		/*
		 * INFO: This method will return current Account status active/inactive
		 * for given app
		 * 
		 * INPUT: myAppName => String
		 * 
		 * RETURN: boolean, true => 'enable', false => 'disable'
		 * 
		 */
		Log.info("Checking current account status for app" + myAppName);
		boolean currentValue;
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd MMM yyyy");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		WebElement myAccount = getAccontDetails(myAppName);
		WebElement myAccountField = myAccount.findElement(By.xpath("//*[@class='adminTable2 ']"));
		Log.info(myAccountField.getText());
		if (myAccountField.getText().contains("Inactive") && myAccountField.getText().contains(strDate)) {
			Log.info("current account status for app" + myAppName + "is inactive");
			currentValue = true;
		} else {
			Log.info("current account status for app" + myAppName + "is active");
			currentValue = false;
		}
		return currentValue;
	}
	
	public LoginPage logout() {
		/*
		 * INFO: This method will logout from home page
		 * 
		 * INPUT: NULL
		 * 
		 * RETURN: Login Page object
		 * 
		 */
		logoutLink.click();
		LoginPage loginPage = new LoginPage(driver, prop);
		return loginPage;
	}
	
	public void deleteApp(String packageName) throws SQLException {
		/*
		 * INFO: This method delete app from data base
		 * 
		 * INPUT: NULL
		 * 
		 * RETURN: VOID
		 * 
		 */
		String dburl = prop.getProperty("dburl");
		String dbuser = prop.getProperty("dbuser");
		String dbpw = prop.getProperty("dbpw");
		methods_snw.deleteAppFromServer(dburl, dbuser, dbpw, packageName);
	}

	public void updateAppVersion(String appVersion, String appName) throws SQLException {
		/*
		 * INFO: This method will reset the verion of app in Database.
		 * 
		 * INPUT: NULL
		 * 
		 * RETURN: VOID
		 * 
		 */
		String dburl = prop.getProperty("dburl");
		String dbuser = prop.getProperty("dbuser");
		String dbpw = prop.getProperty("dbpw");
		methods_snw.updateAppVersionOnWeb(dburl, dbuser, dbpw, appVersion, appName);
	}
	
	
	public void clickHistoryTab(){
		
		/**
		 * INFO: This method will click the history tab on admin portal.
		 */	
		
		History_Tab.click();
	}
	
	
	public boolean checkAppApproveStatus(String myAppName) {
		
		/*
		 * INFO: This method will return current approval status with the approval date for the given app.
		 * 
		 * 
		 * INPUT: myAppName => String
		 * 
		 * RETURN: boolean, true => 'enable', false => 'disable'
		 * 
		 */
		System.out.println("Checking current account status for app" + myAppName);
		boolean currentValue;
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd MMM yyyy");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		WebElement myAccount = getAccontDetails(myAppName);
		WebElement myAccountField = myAccount.findElement(By.xpath("//*[@class='approve_notify']//span]"));
		System.out.println(myAccountField.getText());
		if (myAccountField.getText().contains("Approve") && myAccountField.getText().contains(strDate)) {
			System.out.println("current approval status for app" + myAppName + "is approved");
			currentValue = true;
		} else {
			System.out.println("current approval status for app" + myAppName + "is pending");
			currentValue = false;
		}
		return currentValue;
	}
		
}
