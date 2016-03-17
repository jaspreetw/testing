package com.rjil.cloud.selenium.fitnesse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.client.ClientProtocolException;
import org.openqa.selenium.Alert;

import com.rjil.cloud.selenium.startup.WebAutomationBootstrap;

public class jiowebtesting extends WebAutomationBootstrap {

	static KeywordMethods keyword = new KeywordMethods();
	static DriverMethods method = new DriverMethods();

	// Logger logger;

	public static void main(String[] args) {
		
	}

	// String oldTab = driver.getWindowHandle();
	// starttest is use to launch application in either mobile or browser
	public void startTest(String product, String browser, String url,String propertyFile)
			throws InterruptedException, MalformedURLException {
		super.appSetUp(product, browser, url,propertyFile);
		this.waitFor(10);
		System.out.println("Test started");
		// logger.info("Test started");
	} // end startTest()
		// https://book.jiodrive.com/
		// endTest is use to end test case or close an application

	public void endTest() throws InterruptedException, MalformedURLException {
		WebAutomationBootstrap.teardownApp();
		/*
		 * try { FileUtils.deleteDirectory(new
		 * File("..//SeleniumPropertyFile//webElemIDs.properties")); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */
		System.out.println("Test ended");
		// logger.info("Test ended");
	} // end of endTest()

	// navigateToAppPage is use to navigate to page and Tab Name should be use
	// as parameter
	public void navigateToAppPage(String sPageTitle) {
		keyword.navToAppPage(sPageTitle);
	} // end of navigateToAppPage

	// refresh the page
	public void refresh() {
		keyword.refreshPage();
	}

	// waitFor is use introduce wait between two operation
	public void waitFor(int iSec) {
		method.driverWait(iSec * 1);
	} // end of waitFor

	// tapOnText is use to tap on any text on mobile App
	public void tapOnText(String sText) {
		keyword.tapTextOnApp(sText);
	} // end of tapOnText

	// tapOnButton is use to tap on any button on mobile App by passing button
	// label as parameter
	public void tapOnButton(String sButtonLabel) {
		keyword.tapButtonOnApp(sButtonLabel);
	} // end of tapOnButton

	// tapOnIcon is use to tap on any icon on mobile App by passing icon text as
	// parameter
	public void tapOnIcon(String sIconDesc) {
		keyword.tapIconOnApp(sIconDesc);
	} // end of tapOnIcon

	// verifyLabelOnApp is use to get label from button,link etc and verify it
	// with parameter passed
	public String verifyLabelOnApp(String sSection) {
		return keyword.getLabelContaingText(sSection);
	} // end of verifyLabelOnApp

	// verifyTextOnApp is use to check text on App page and verify it with
	// parameter passed
	public boolean verifyTextOnApp(String sText) {
		return keyword.checkTextOnPage(sText);
	} // end of verifyTextOnApp

	// getTextOnApp is use to return name of file on App
	public String getTextOnApp(String sElem) throws InterruptedException,
			IOException {
		return keyword.getTextFromAppElement(sElem);
	} // end of getTextOnApp

	// deviceKey is use to tap on icon,button on mobile phone like
	// menu,back,search
	public void deviceKey(String sKey) {
		keyword.tapDeviceKey(sKey);
	} // end of deviceKey

	// WebbrowserKey is use to tap on icon,button on browser
	public void webBrowserKey(String sText) {
		keyword.browserKey(sText);
	}

	// selectItemsOnApp is use to select number of items passed in parameter
	public void selectItemsOnApp(int iNumber) {
		keyword.selectItemsOnApp(iNumber);
	} // end of selectItemsOnApp

	// selectRowsOnApp is use to select number of rows passed in parameter
	public void selectRowsOnApp(int iNumber) {
		keyword.selectRowsOnApp(iNumber);
	} // end of selectRowsOnApp

	// selectSongInAlbum is use to select number of songs passed in parameter
	public void selectSongInAlbum(int iNumber) {
		keyword.selectSongInAlbum(iNumber);
	} // end of selectRowsOnApp

	// syncAllOnApp is use to do Sync on mobile phone
	public void syncAllOnApp() {
		keyword.syncAllOnApp();
	} // end of syncAllOnApp

	// enterTextInFieldOnApp is use to enter any string in input field of mobile
	// App
	public void enterTextInFieldOnApp(String sFieldName, String sText) {
		keyword.enterTextInFieldOnApp(sFieldName, sText);
	} // end of enterTextInFieldOnApp

	// enterTextInFieldOnApp is use to enter any string in input field on device
	public void enterTextInDeviceField(String sFieldName, String sText) {
		keyword.enterTextInFieldOnDevice(sFieldName, sText);
	} // end of enterTextInDeviceField

	// longPressOnField is use to long press on any field on the device.
	public void longPressOnField(String sFieldName) throws IOException {
		keyword.longPressOnDevice(sFieldName);
	} // end of longPressOnField

	// longPressOnField is use to long press on any text on the device.
	public void longPressOnText(String sText) {
		keyword.longPressOnTextDevice(sText);
	}// end of longPressOnText

	// tapOnElement is use to tap on element on mobile App
	public void tapOnElement(String sElem) {
		method.clickAppElement(sElem);
	}

	// launchApp is use to launch App on mobile device
	public void launchApp(String appName) {
		keyword.launchAppOnDevice(appName);
	} // end of launchFileManager

	// getCountOfSyncedItems is use to return total number of sync item like
	// photo,music,video
	public int getCountOfSyncedItems(String sSection) {
		return keyword.getCountOfItemsFromLabel(sSection);
	}// end of getCountOfSyncedItems

	// login is use to login to web portal
	public void login(String sUserID, String sPassword)
			throws InterruptedException, MalformedURLException {
		keyword.loginToWeb(sUserID, sPassword);

	} // end login()

	// logout is use to logout from web portal
	public void logout() {
		keyword.logoutOfWeb();
	} // end of logout

	// navigateToWebPage is use to navigate to web page passed in parameter
	public void navigateToWebPage(String sLinkText) {
		keyword.navigateToWebPage(sLinkText);
	} // end of navigateToWebPage

	// navigateToTab is use to navigate to tab passed in parameter
	public void navigateToTab(String sLinkText) {
		keyword.navigateToTab(sLinkText);
	} // end of navigateToTab

	// clickOnWebElement is use click on web element passed in parameter
	public void clickOnWebElement(String sLinkText) {
		method.clickWebElement(sLinkText);
	} // end of clickOnWebElement

	// uploadFilesFromLocalDrive is use to upload files from local drive
	public void uploadFilesFromLocalDrive(String webElem, String sFileType) {
	} // end of selectFilesFromLocalDrive

	// getTotalItemsInWeb returns total items form web table like
	// photo,music,video
	public int getTotalItemsInWeb(String sTableType) {
		return keyword.getTotalItemsInWebTable(sTableType);
	} // end of getTotalItemsInWeb

	// enterTextInFieldOnWeb is use to enter text in field of web table
	public void enterTextInFieldOnWeb(String sFieldName, String sText) {
		keyword.enterTextInFieldOnWeb(sFieldName, sText);
	} // end of enterTextInFieldOnWeb

	// selectInDropDownOnWeb is use to select option from dropdown list in web
	// table
	public void selectInDropDownOnWeb(String sDropDown, String sOption) {
		keyword.selectInDropDownOnWeb(sDropDown, sOption);
	} // end of selectInDropDownOnWeb

	// getTextOfWebElem returns text on element passed in parameter
	public String getTextOfWebElem(String sElemName)
			throws InterruptedException, IOException {
		return keyword.getTextOfWebElem(sElemName);
	} // end of getTextOnWebElem

	// verifyTextInTable returns true if text passed in parameter is present in
	// web
	// table
	public boolean verifyTextInTable(String sTabName, String sText)
			throws InterruptedException, IOException {
		return keyword.checkTextInTable(sTabName, sText);
	} // end of verifyTextInTable

	// clickOnTextInWebTable is use to click on text passed in parameter
	public void clickOnTextInWebTable(String sTabName, String sName) {
		System.out.println("clicking web table");
		keyword.clickOnTextWebTable(sTabName, sName);
	} // end of clickOnTextInWebTable

	// clickOnTextInWebTable is use to click on text passed in parameter
	public void clickOnPhotoAlbum(String sName) {
		keyword.selectPhotoAlbum(sName);
	} // end of clickOnTextInWebTable

	// selectCheckboxInWebTable is use to select check box in web table
	public void selectCheckboxInWebTable(String sFileName) {
		keyword.clickCheckboxWebTable(sFileName);
	} // end of selectCheckboxInWebTable

	// gotoJioDriveDownloadFolder navigate to Jio Drive folder on mobile device
	public void gotoJioDriveDownloadFolder() {
		keyword.gotoJioDriveDownloadFolder();
	}

	// mathAddTwoValues is use to add two values
	public String mathAddTwoValues(String iValueA, String iValueB) {
		return keyword.addIntegers(iValueA, iValueB);
	} // end of mathAddTwoValues

	// swipeOnDevice is use to swipe to different direction on mobile App
	public void swipeOnDevice(String sDirection, int swipeTimes) {
		keyword.swipeOnDevice(sDirection, swipeTimes);
	} // end of swipeOnDevice

	// tapOnImage is use to tab on particular image passed in parameter
	public void tapOnImage(int iNumber) {
		keyword.tapOnImage(iNumber);
	}// end of tapOnImage

	// selectItemOnWeb is use to select item passed in parameter
	public void selectItemOnWeb(int iNumber) {
		keyword.selectItemRowOnWeb(iNumber);

	}// end selectItemRowOnWeb

	// getTextOfItemOnWeb returns text on particular item passed in parameter
	public String getTextOfItemOnWeb(int iNumber) {
		String fileName = keyword.getTextOnWeb(iNumber);
		return fileName;
	} // end of getTextOfItemOnWeb

	public String CurrentTime() {
		String eventName = "A" + System.currentTimeMillis();
		System.out.println(eventName);
		return eventName;
	}

	public String assertTextOnWebPage(String sText) {
		String result = keyword.assertText(sText);
		return result;
	}
	
	public String getTextOfWebElement(String ePath) {
		String sText = keyword.getTextOnWebPath(ePath);
		return sText;
	}

	public void switchToFrame(String frameId) {
		keyword.switchToFrameId(frameId);
	}

	public void switchToParentWindow() {
		driver.switchTo().defaultContent();
	}

	public int pageCountInReportTab(int noOfPage) {
		int pageCount = keyword.totalPage(noOfPage);
		return pageCount;
	}

	public void scrollOnWebPage(int x, int y) {
		keyword.scrollOnWeb(x, y);
	}

	public void enterDateInWebField(int fieldId, String date) {
		keyword.enterDate(fieldId, date);
	}

	public String assertDashBoard(String tableName, String data) {
		String result = keyword.assertDashBoardT(tableName, data);
		return result;
	}

	public void clickOnWebElement(String sLinkText, int number) {
		method.clickWebElement(sLinkText, number);
	}

	public void clickOnWebElement(String sLinkText, String name,
			String productname) {
		method.clickWebElement(sLinkText, name, productname);
	}

	public String checkHighlightedElement(String wElement) {
		String result = keyword.HighlightedElement(wElement);
		return result;
	}

	public String getValueOfWebtag(String webElemID) {
		String result = keyword.tagValue(webElemID);
		return result;
	}

	public String calculateDaysByDate(String sDate, String eDate) {
		String days = keyword.calculateDays(sDate, eDate);
		return days;

	}

	public String assertReportTable(String data) {
		String result = keyword.assertReportT(data);
		return result;
	}

	public String checkSelectedRadioButton(String webElem) {
		String result = keyword.checkRadioButton(webElem);
		return result;
	}

	public void selectFromDropDownList(String webElem, String sText) {
		keyword.dropDownList(webElem, sText);
	}

	public void mouseHover(String xPath) {
		keyword.hoverElement(xPath);
	}

	// selectradiobutton
	public void selectRadioButton(String name, int number) {
		keyword.selectRadioButton(name, number);
	}

	public String getIdOfClassElement(String xPath) {
		String id = keyword.getIdOfElement(xPath);
		return id;
	}

	public void closeImageTab(String xPath, String Id) {
		keyword.closeTab(xPath, Id);
	}

	public void pressKeyboardButton(String buttonName) {
		keyword.keyBoardButton(buttonName);
	}

	public boolean isAnElementPresent(String locator) {
		Boolean isPresent = method.isElementPresent(locator);
		return isPresent;
	}


	public boolean isAnElementEnabled(String locator) {
		Boolean isEnabled = method.isElementEnabled(locator);
		return isEnabled;
	}
	public String getNameofFile(String path) {
		String filename = keyword.getFileName(path);
		return filename;
	}

	public void testCase(String tcid, String tc) {

	}
	public boolean verifyTextPresentOnWeb(String sText) {
		return keyword.verifyTextPresent(sText);
	}

	public String presentElement(String xPath) {
		return keyword.isElemPresent(xPath);
	}

	public String verifySelectedCheckBox(String webElem) {
		String result = keyword.checkBoxSelected(webElem);
		return result;
	}
	public String assertTextOnButton(String elem, String sText) {
		String result = keyword.assertTextOnButton(elem, sText);
		return result;
	}	
	public void selectListElement(String wElem,String listElem) {
		  keyword.selectListElem(wElem,listElem);
		 }
	public String verifySelectedListItems(String wElem,String listElem) {
		 return keyword.SelectedListItems(wElem,listElem);
		 
	}
	
	public void clickBtnInWebTableCell(String tableElem, String sTextElem, String buttonElem, String sText) {
		 keyword.clickBtnInTableCell(tableElem, sTextElem, buttonElem, sText);
	}
	
	public void selectBtnForTextInWebTable(String tableElem, String buttonElem, String sText) {
		 keyword.SelectBtnForTextInTable(tableElem, buttonElem, sText);
	}
	public void clickOnText(String tName) {
		keyword.clickText(tName);
		 }
	public void acceptAlertOnPage(){
	      keyword.acceptAlert(0);
	}
	
	public void dismisstAlertOnPage(){
		keyword.dismisstAlert();
	}	  
	
	public String verifyTextInWebTableCell(String tableElem, String sText, String tText) {
		return keyword.VerifyTextInTableCell(tableElem,sText,tText);
	}
	
	public void clickOnTextInWebTableCell(String tableElem, String sText, String tText) {
		keyword.ClickOnTextInTableCell(tableElem,sText,tText);	  
		}
	public String generateTimeStamp() {
		return keyword.createTimeStamp();
	}
	public void switchToPopUpWindow (){
	keyword.switchToPopUp();
	}
	public String getApiResponse(String url, String uri, String header) throws ClientProtocolException, IOException{
		return keyword.callGet(url,uri,header);	
	}
	
	// uploadFilesFromLocalDrive is use to upload files from local drive
	 public void uploadFilesFromLocalDrive(String sFileType) {
	  keyword.selectFilesFromLocalDrive(sFileType);
	 } // end of selectFilesFromLocalDrive// uploadFilesFromLocalDrive is use to upload files from local drive
	
	 
	public void makeOfferRunning(String dburl, String dbuser, String dbpw, String offerName) throws SQLException {
		keyword. makeRunningOffer(dburl,dbuser, dbpw, offerName);
	}
	public void deleteApp(String dburl, String dbuser, String dbpw, String packageName) throws SQLException {
		keyword. deleteAppFromServer(dburl, dbuser, dbpw, packageName);
	}	
	public void resetPassword(String dburl, String dbuser, String dbpw, String emailId, String password) throws SQLException {
	keyword. resetPw(dburl, dbuser, dbpw, emailId, password);
	}
	
	public void unlockAccount(String dburl, String dbuser, String dbpw, String emailId) throws SQLException {
		keyword. unlockPartnerAccount(dburl, dbuser, dbpw, emailId);
		}
	
	public void updateAppVersion(String dburl, String dbuser, String dbpw, String appVersion, String appName) throws SQLException {
		keyword. updateAppVersionOnWeb(dburl, dbuser, dbpw, appVersion, appName);
		}
	
	public String verifyTextInWebTable(String tableElem, String sText, String tText) {
		return keyword.VerifyTextInTable(tableElem,sText,tText);
	}
	
	public void clickOnTextInWebTable(String tableElem, String sText, String tText) {
		keyword.ClickOnTextInTable(tableElem,sText,tText);	  
		}

}//End of Class