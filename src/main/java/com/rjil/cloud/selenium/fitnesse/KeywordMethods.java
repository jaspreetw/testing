package com.rjil.cloud.selenium.fitnesse;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.rjil.cloud.selenium.startup.WebAutomationBootstrap;

// keywordMethods class consist of actual logic for all method in Jiodrivetest class
public class KeywordMethods extends jiowebtesting {

	static DriverMethods method = new DriverMethods();
	static ElementIDsReader elemID = new ElementIDsReader();

	// Logger logger;

	public void navToAppPage(String sPageTitle) {
		/*
		 * try { //Logger.initLogger(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		// navigates to Home on mobile apps
		method.clickAppElement("mainMenuBtn");
		this.tapTextOnApp(sPageTitle);
		
	}// end of navToAppPage

	public void tapTextOnApp(String sText) {
		/*
		 * try { //Logger.initLogger(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		// this method is used to get the common string amongs all the string
		sText = this.getCommonString(sText);
		// find all the textview name on that page and if its matches with the
		// name in passed parameter, it clicks on that textview
		List<WebElement> lTextViewList = method.findAllElements("//TextView");
		forLoop: for (WebElement textView : lTextViewList) {
			String sTextToCheck = method.getTextOnElement(textView);
			sTextToCheck = this.getCommonString(sTextToCheck);
			if (sTextToCheck.equals(sText)) {
				method.clickElement(textView);
				break forLoop;
			}
		}
		
	}// end of tapTextOnApp

	public void tapButtonOnApp(String sButtonLabel) {
		sButtonLabel = this.getCommonString(sButtonLabel);
		method.driverWait(1);
		// find all the button labels on that page and if it matches with the
		// label passed in parameter, it clicks on that Button
		List<WebElement> lTextViewList = method.findAllElements("//Button");
		forLoop: for (WebElement textView : lTextViewList) {
			String sTextToCheck = method.getTextOnElement(textView);
			sTextToCheck = this.getCommonString(sTextToCheck);
			if (sTextToCheck.equals(sButtonLabel)) {
				method.clickElement(textView);
				break forLoop;
			}
		}
		/*
		 * try { //Logger.initLogger(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		
	} // end of tapButtonOnApp

	public void tapIconOnApp(String sIconDesc) {
		sIconDesc = this.getCommonString(sIconDesc);
		method.driverWait(1);
		// find all thetextview,imageview,imagebutton,linearlayout,
		// relativelayout on the page and if its matches with the text
		// passed in parameter, it clicks on that
		List<WebElement> lListOfItems = method.findAllElements("//TextView");
		lListOfItems.addAll(method.findAllElements("//ImageView"));
		lListOfItems.addAll(method.findAllElements("//ImageButton"));
		lListOfItems.addAll(method.findAllElements("//LinearLayout"));
		lListOfItems.addAll(method.findAllElements("//RelativeLayout"));
		lListOfItems.addAll(method.findAllElements("//Button"));

		for (WebElement listItem : lListOfItems) {
			String sIconToCheck = method.getDescription(listItem);
			sIconToCheck = this.getCommonString(sIconToCheck);
			if (sIconToCheck.equals(sIconDesc)) {
				method.clickElement(listItem);
				break;
			}
		}
		/*
		 * try { //Logger.initLogger(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		

	}// end of tapIconOnApp

	public String getLabelContaingText(String sText) {
		/*
		 * try { //Logger.initLogger(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		String sValue = null;
		// find all the textview name on that page and if its matches with the
		// name in passed parameter, then it returns text on it
		List<WebElement> lTextViewList = method.findAllElements("//TextView");
		forLoop: for (WebElement textView : lTextViewList) {
			if (method.getTextOnElement(textView).contains(sText)) {
				sValue = method.getTextOnElement(textView);
				break forLoop;
			}
		}
		return sValue;
	}// end of getLabelContaingText

	public boolean checkTextOnPage(String sText) {
		/*
		 * try { //Logger.initLogger(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		boolean sValue = false;
		// find all the textview name on that page and if its matches with the
		// name in passed parameter,it returns true
		List<WebElement> lTextViewList = method.findAllElements("//TextView");
		forLoop: for (WebElement textView : lTextViewList) {
			if (method.getTextOnElement(textView).equals(sText)) {
				sValue = true;
				break forLoop;
			}
		}
		return sValue;
	}// end of checkTextOnPage

	public String getTextFromAppElement(String sElem) {
		/*
		 * try { //Logger.initLogger(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		// its gets xpath of sElem from mobileAppIDs,find the element
		// on that xpath and returns text of that element
		String xPathString = method.getXpathOfElement(sElem, "mobileAppIDs");
		String sText = driver.findElement(By.xpath(xPathString)).getText();
		return sText;
	}// end of getTextFromAppElement

	public void tapDeviceKey(String sKey) {
		/*
		 * try { //Logger.initLogger(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		// ikey specifies the inbuilt android keycodes to perform different
		// operation.
		// Based on passed sKey ,the ikey values change and respectative
		// operation is perform.
		int iKey = 0;
		switch (sKey) {
		case "back":
			iKey = 4;
			break;
		case "enter":
			iKey = 66;
			break;
		case "menu":
			iKey = 82;
			break;
		case "search":
			iKey = 84;
		default:
			iKey = 111;

		}
		method.executeDriverJS("keyevent", iKey, 0, 0, 0, 0, 0, null);
		method.driverWait(1);
	} // end of tapDeviceKey

	public void selectItemsOnApp(int iNumber) {
		// gets xpath of imageItem from mobileAppIDs with respect to value of
		// 'i'
		// and click that xpath for iNumber time
		/*
		 * try { //Logger.initLogger(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		String sXpathToModify = method.getXpathOfElement("imageItem",
				"mobileAppIDs");
		for (int i = 1; i < iNumber * 2; i = i + 2) {
			String sXpath = sXpathToModify + i + "]";
			method.clickLocator(sXpath);
		}
	}// end of selectItemsOnApp

	public void selectRowsOnApp(int iNumber) {
		// gets xpath of rowItem from mobileAppIDs based on passed
		// parameter and click that xpath
		/*
		 * try { //Logger.initLogger(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		iNumber = iNumber * 2 - 1;
		String sXpathToModify = method.getXpathOfElement("rowItem",
				"mobileAppIDs");
		String sXpath = sXpathToModify + iNumber + "]";
		method.clickLocator(sXpath);
	}// end of selectRowsOnApp

	public void selectSongInAlbum(int iNumber) {
		// gets xpath of rowItem from mobileAppIDs with respect to value of 'i'
		// and click that xpath for iNumber time
		/*
		 * try { //Logger.initLogger(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		String sXpathToModify = method.getXpathOfElement("rowItem",
				"mobileAppIDs");
		for (int i = 2; i <= iNumber * 2; i = i + 2) {
			String sXpath = sXpathToModify + i + "]";
			method.clickLocator(sXpath);
		}
	}// end of selectSongInAlbum

	public void syncAllOnApp() {
		// this method is to tap sync all icon on mobile device
		/*
		 * try { //Logger.initLogger(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		method.driverWait(1);
		method.clickAppElement("moreOptionsBtn");
		method.driverWait(1);
		this.tapTextOnApp("Sync All Now");
		method.driverWait(15);
	} // end of syncAllOnApp

	public void enterTextInFieldOnApp(String sFieldName, String sText) {
		// get xpath of sFieldName from mobileAppIDs and enter
		// text in text field on that xpath
		/*
		 * try { //Logger.initLogger(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		String xPathString = method.getXpathOfElement(sFieldName,
				"mobileAppIDs");
		method.enterTextOnLocator(xPathString, sText);
	} // end of enterTextInFieldOnApp

	public void enterTextInFieldOnDevice(String sFieldName, String sText) {
		// find all the elements in edittext, get description of that elements
		// and if it contains the passed fieldname, enter text in that field
		/*
		 * try { //Logger.initLogger(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		method.driverWait(2);
		List<WebElement> lTextViewList = method.findAllElements("//EditText");
		forLoop: for (WebElement editView : lTextViewList) {
			String sFieldToCheck = method.getDescription(editView);
			sFieldToCheck = this.getCommonString(sFieldToCheck);
			System.out.println("string: " + sFieldToCheck);
			if (sFieldToCheck.contains(sFieldName)) {
				method.enterTextInField(editView, sText);
				method.executeDriverJS("keyevent", 66, 0, 0, 0, 0, 0, null);
				method.driverWait(2);
				break forLoop;
			}
		}

	} // end of enterTextInFieldOnDevice

	public void longPressOnDevice(String sFieldName) {
		// get xpath of sfieldname from mobileAppIDs and find element on that
		// xpath and long press on that element
		/*
		 * try { //Logger.initLogger(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		String xPathString = method.getXpathOfElement(sFieldName,
				"mobileAppIDs");
		WebElement testElem = method.findElement(xPathString);
		method.executeDriverJS("longClick", 0, 0, 0, 0, 0, 0, testElem);
	}// end of longPressOnDevice

	public void longPressOnTextDevice(String sText) {
		// find all the elements in textview and if it text matches the passed
		// stext
		// long press on that text
		/*
		 * try { //Logger.initLogger(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		List<WebElement> lTextViewList = method.findAllElements("//TextView");
		forLoop: for (WebElement textView : lTextViewList) {
			if (method.getTextOnElement(textView).equals(sText)) {
				method.executeDriverJS("longClick", 0, 0, 0, 0, 0, 0, textView);
				break forLoop;
			}
		}
	}// end of longPressOnTextDevice

	public void launchAppOnDevice(String appName) {
		// this method launches the apps passed in the parameter
		appName = this.getCommonString(appName);
		method.executeDriverJS("keyevent", 3, 0, 0, 0, 0, 0, null);
		this.findTextAndClick(appName);

		method.driverWait(5);

	}// end of launchAppOnDevice

	public void findTextViewAndClickWithOptional(String sOption1,
			String sOption2, String sOption3) {
		List<WebElement> lTextViewList = method.findAllElements("//TextView");
		forLoop: for (WebElement textView : lTextViewList) {
			if (sOption2.isEmpty() && sOption3.isEmpty()) {
				if (method.getTextOnElement(textView).contains(sOption1)) {
					method.clickElement(textView);
					break forLoop;
				}
			} else {
				if (method.getTextOnElement(textView).contains(sOption1)
						|| method.getTextOnElement(textView).contains(sOption2)
						|| method.getTextOnElement(textView).contains(sOption3)) {
					method.clickElement(textView);
					break forLoop;
				}
			}
		}

	} // end of findTextViewAndClickWithOptional

	public void findTextAndClick(String sText) {
		// this method finds the textview in that page, get text on
		// that textview and click on it if its matches sText
		List<WebElement> lTextViewList = method.findAllElements("//TextView");
		forLoop: for (WebElement textView : lTextViewList) {
			String sTextToCheck = method.getTextOnElement(textView);
			sTextToCheck = this.getCommonString(sTextToCheck);
			if (sTextToCheck.contains(sText)) {
				method.clickElement(textView);
				break forLoop;
			}
		}
	} // end of findTextAndClick

	public int getCountOfItemsFromLabel(String sSection) {
		// find all textview in that page and if that textview contains
		// sSection,
		// get text of that textview and split that text and return value at
		// 0th location i.e. count of items
		String sValue = null;
		List<WebElement> lTextViewList = method.findAllElements("//TextView");
		forLoop: for (WebElement textView : lTextViewList) {
			if (method.getTextOnElement(textView).contains(sSection)) {
				sValue = method.getTextOnElement(textView);
				break forLoop;
			}
		}

		String[] sLabelDetails = sValue.split(" ");
		int sSyncedCount = Integer.parseInt(sLabelDetails[0]);
		return sSyncedCount;
	}// end of getCountOfItemsFromLabel

	public void loginToWeb(String sUserID, String sPassword) {
		// this methods enter userId and password in field on web and click
		// loginbutton
		/*
		 * try { //Logger.initLogger(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		method.enterTextOnWeb("userId", sUserID);
		method.enterTextOnWeb("password", sPassword);
		method.driverWait(5);
		method.clickWebElement("loginButton");
		method.driverWait(10);
		System.out.println("Logged in");
		// logger.info("Logged in");
	}// end of logoutOfWeb

	public void logoutOfWeb() {
		/*
		 * try { //Logger.initLogger(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		// this click on logout button on web
		method.clickWebElement("logoutButton");
		method.driverWait(5);
	}// end of logoutOfWeb

	public void navigateToWebPage(String sText) {
		/*
		 * try { //Logger.initLogger(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		// this method click on text passed in parameter and navigate to that
		// page
		if (sText.equals("Media")) {
			method.clickWebElement(sText);
			method.switchTab();
		} else if (sText.equals("Sign Out")) {
			method.clickWebElement(sText);
			method.Logout();

		} else {
			method.clickWebElement(sText);
		}

	}// end of navigateToWebPage

	public void navigateToTab(String sLinkText) {
		/*
		 * try { //Logger.initLogger(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		// this method click on linktext passed in parameter and navigate to
		// that tab
		method.clickWebElement(sLinkText);
		method.driverWait(5);
		method.clickWebElement("List View");
		method.driverWait(5);
	}// end of navigateToTab

	public void selectFilesFromLocalDrive(WebDriver driver, String webElem, String sFileType) {
		try {
			// String xPathString =
			// method.getXpathOfElement("Browse your computer", "webElemIDs");
			/*String xPathString = method
					.getXpathOfElement(webElem, "webElemIDs");*/
			String xPathString = webElem;
			// driver.findElement(By.xpath("//*[@id='SimpleUpload_Buttons']/div[2]/h1")).click();
			driver.findElement(By.xpath(xPathString)).click();
			// driver.findElement(By.id("fileupload")).click();
			System.out.println("clicked fileupload button");
			// <input id="fileupload" name="file" multiple="" type="file">

			Thread.sleep(5000);

			StringSelection ss = new StringSelection(sFileType);
			// "\"C:\\automation\\Selenium_testcases\\Images\\download2.jpg");
			Toolkit.getDefaultToolkit().getSystemClipboard()
					.setContents(ss, null);
			System.out.println("copied text to clipboard");
			Robot robot = new Robot();
			Thread.sleep(5000);
			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_V);
			Thread.sleep(1000);
			robot.keyRelease(KeyEvent.VK_V);
			Thread.sleep(1000);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			robot.keyRelease(KeyEvent.VK_ENTER);
			System.out.println("File uploaded");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * try { //Logger.initLogger(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * method.clickWebElement("Upload");
		 * method.clickWebElement("FileDropArea"); String commands = null;
		 * method.driverWait(5); PropertiesReader propertiesReader = new
		 * PropertiesReader(); commands =
		 * propertiesReader.getKeyValues(sFileType, "config"); try {
		 * Runtime.getRuntime().exec(commands); } catch (IOException e) {
		 * e.printStackTrace(); } method.driverWait(10);
		 * logger.info("Files selected"); method.clickWebElement("UploadAll");
		 * method.waitTillElementAppears("LeftContent"); method.driverWait(5);
		 */}// end of selectFilesFromLocalDrive

	public int getTotalItemsInWebTable(String sTableType) {
		/*
		 * try { //Logger.initLogger(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		String xPathTable = null;
		int iItemCount = 0;
		// if table type is List, find all the elements in xpath and if element
		// style
		// is block its count is increase by one.
		if (sTableType.equals("List")) {
			xPathTable = "//div[@id='DataList']/div";
			List<WebElement> lItems = method.findAllElements(xPathTable);
			iItemCount = 0;
			for (WebElement elem : lItems) {
				if (elem.getAttribute("style").equals("display: block;")) {
					iItemCount++;
				}
			}
		}
		// if table type is File Version, find all the elements in xpath and if
		// element style
		// is block its count is increase by one.
		if (sTableType.equals("File Versions")) {
			xPathTable = "//div[@id='ManageVersions_List']/div";
			List<WebElement> lItems = method.findAllElements(xPathTable);
			iItemCount = 0;
			for (WebElement elem : lItems) {
				iItemCount++;
				System.out.println(elem);
			}
		}
		return iItemCount;
	}// end of getTotalItemsInWebTable

	// TODO enter text

	public void enterTextInFieldOnWeb(String sFieldName, String sText) {
		// find the xpath of passed fieldname and enter the text passed
		// in parameter at that location
		System.out.println("f-->" + sFieldName + " t-->" + sText);
		String xPathString = method.getXpathOfElement(sFieldName, "webElemIDs");
		System.out.println("XPath generated: " + xPathString);
		method.enterTextOnLocator(xPathString, sText);
		System.out.println(sText + " entered in " + sFieldName + " field");
	} // end of enterTextInFieldOnWeb

	public void selectInDropDownOnWeb(String sDropDown, String sOption) {
		// find element and gets xpath of sDropDown from webElemIDs and click on
		// that dropdownlist.
		// then find element in that dropdownlist by tagName option and get text
		// on that element
		// and if its matches the passed sOption click on that option.
		WebElement dropDownList = method.findElement(method.getXpathOfElement(
				sDropDown, "webElemIDs"));
		method.clickElement(dropDownList);
		List<WebElement> options = dropDownList.findElements(By
				.tagName("option"));
		forLoop: for (WebElement option : options) {
			if (option.getText().equals(sOption)) {
				method.clickElement(option);
				break forLoop;
			}
		}
	}// end of selectInDropDownOnWeb


	public boolean checkTextInTable(String sTabName, String sText) {
		// this method returns true if passed text is present in respective web
		// table else returns false
		System.out.println("inside check text in table to verify album "
				+ sText + " is present in " + sTabName + " album");
		boolean sResult = false;
		switch (sTabName) {
		case "Pictures":
			sResult = this.verifyAlbum(sText);
			break;
		case "Shares":
			sResult = this.verifyShareFiles(sText);
			break;
		case "Contacttrash":
			sResult = this.verifyContactFiles(sText);
			break;
		default:
			sResult = this.checkTextInOtherTables(sText);
			break;
		}
		return sResult;
	}// end of checkTextInTable

	public boolean verifyContactFiles(String sContactName) {
		// this method finds all the elements in given xpath and if its
		// get text equals to sContactName it returns true
		String xPathTable = "//table[@id='trashTable']//div/a";
		boolean sValue = false;
		List<WebElement> lItems = method.findAllElements(xPathTable);
		// forLoops:
		for (WebElement elem : lItems) {
			System.out.print("In For Loop");
			if (elem.getText().equals(sContactName)) {
				sValue = true;
			}
		}
		return sValue;
	}// end of verifyContactFiles

	public boolean checkTextInOtherTables(String sText) {
		// find all element in given xpathtable, if its style is block ,it is
		// split
		// into 3 part and store in sdiv[] array.
		// then we get text on fileElement and titleElement,if its matches the
		// passed stext ,it returns true
		boolean sValue = false;
		String xPathTable = "//div[@id='DataList']/div";
		List<WebElement> lItems = method.findAllElements(xPathTable);
		forLoops: for (WebElement elem : lItems) {
			if (elem.getAttribute("style").equals("display: block;")) {
				String[] sDiv = elem.getAttribute("id").split("_", 3);
				String rowNumber = sDiv[1];
				String sXpathFile = "//div[@id='DataList_" + rowNumber
						+ "_File']";
				String sXpathTitle = "//div[@id='DataList_" + rowNumber
						+ "_Title']";
				WebElement fileElement = method.findElement(sXpathFile);
				String sFileToCheck = fileElement.getText();
				WebElement titleElement = method.findElement(sXpathTitle);
				String sTitleToCheck = titleElement.getText();
				if (sFileToCheck.equals(sText)) {
					sValue = true;
					break forLoops;
				}
				if (sTitleToCheck.equals(sText)) {
					sValue = true;
					break forLoops;
				}
			}
		}
		return sValue;
	}// end of checkTextInOtherTables

	
	public String getTextOfWebElem(String sFieldName) {
		boolean breakIt = true;
        while (true) {
        breakIt = true;
        try {
		// find element and gets xpath of sFieldName from webElemIDs and get
		// text on that xpath
		WebElement sElem = method.findElement(method.getXpathOfElement(
				sFieldName, "webElemIDs"));

		// To Verify
		// String text = method.getTextOnElement(sElem);
		// if
		// (text.contains("All Brands Htc Intex Karbonn Lava Lenovo Lg Micromax Samsung Sony Xiaomi Others"))
		// logger.info("Text is Present--------->");
		// else
		// logger.info("Text is Not Present--------->");

		return method.getTextOnElement(sElem);
        } catch (Exception e) {
            if (e.getMessage().contains("element is not attached")) {
                breakIt = false;
            }
        }
        if (breakIt) {
            break;
        }

    }
		return sFieldName;
		
	}// end of getTextOfWebElem

	public boolean verifyAlbum(String sAlbumName) {
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		// find all element in given xpathtable, if its style is block ,it is
		// split
		// into 3 part and store in sdiv[] array.
		// then we get text on fileElement and if its matches the passed album
		// name,
		// it returns true
		System.out.println("inside verify album");
		String sID = elemID.getIdOfElem("DataGrid", "webElemIDs");
		String xPathTable = "//*";
		if (sID.contains("/")) {
			xPathTable = sID;
		} else {
			xPathTable = "//*[@id='" + sID + "']";
		}
		// String xPathTable = "//div[@id='DataGrid']/div";
		System.out.println(xPathTable);
		boolean sValue = false;
		List<WebElement> lItems = method.findAllElements(xPathTable);
		forLoops: for (WebElement elem : lItems) {
			System.out.println("++++++++");
			System.out.println("elem" + elem);
			System.out.println("attri" + elem.getAttribute("id"));
			String[] sDiv = elem.getAttribute("id").split("_", 3);
			String rowNumber = sDiv[1];
			sID = elemID.getIdOfElem("AlbumXpath", "webElemIDs");
			String sXpath = "//*";
			if (sID.contains("/")) {
				sXpath = sID;
			} else {
				sXpath = "//*[@id='" + sID + "']";
			}
			sXpath = sXpath.replaceFirst("<rownumber>", rowNumber + "");
			// String sXpath ="//*[@id='DataGrid_"+rowNumber+"_Name']/span[1]";

			// "//div[@id='DataGrid_" + rowNumber + "_Name']";
			WebElement elementToClick = method.findElement(sXpath);
			String sFileToCheck = elementToClick.getText();
			System.out.println("1album name-->*" + sAlbumName + "*");
			System.out.println("2album name-->*" + sFileToCheck + "*");
			if (sFileToCheck.equalsIgnoreCase(sAlbumName)) {
				System.out.println("inside if");
				sValue = true;
				break forLoops;
			}
			System.out.println("----------&&&&&&&&&&&&&&&&&&---------");

		}
		return sValue;

		/*
		 * forLoops: for (WebElement elem : lItems) { if
		 * (elem.getAttribute("style").equals("display: block;")) { String[]
		 * sDiv = elem.getAttribute("id").split("_", 3); String rowNumber =
		 * sDiv[1]; sID = elemID.getIdOfElem("AlbumXpath", "webElemIDs"); String
		 * sXpath = "//*"; if (sID.contains("/")) { sXpath = sID; } else {
		 * sXpath = "//*[@id='" + sID + "']"; } sXpath =
		 * sXpath.replaceFirst("<rownumber>", rowNumber+""); //String sXpathFile
		 * = "//div[@id='DataGrid_" + rowNumber + "_Name']";
		 * System.out.println(sXpath); WebElement fileElement =
		 * method.findElement(sXpath); String sFileToCheck =
		 * fileElement.getText();
		 * System.out.println("#ori album"+sAlbumName+"#");
		 * System.out.println("#act album"+sFileToCheck+"#");
		 * 
		 * if (sFileToCheck.equals(sAlbumName)) { sValue = true; break forLoops;
		 * } } }
		 */

	}// end of verifyAlbum

	public boolean verifyShareFiles(String sAlbumName) {
		// find all element in given xpathtable, if its style is block ,it is
		// split
		// into 3 part and store in sdiv[] array.
		// then we get text on fileElement and if its matches the passed album
		// name,
		// it returns true
		String xPathTable = "//div[@id='DataList']/div";
		boolean sValue = false;
		List<WebElement> lItems = method.findAllElements(xPathTable);
		forLoops: for (WebElement elem : lItems) {
			if (elem.getAttribute("style").equals("display: block;")) {
				String[] sDiv = elem.getAttribute("id").split("_", 3);
				String rowNumber = sDiv[1];
				String sXpathFile = "//div[@id='DataList_" + rowNumber
						+ "_Name']";
				WebElement fileElement = method.findElement(sXpathFile);
				String sFileToCheck = fileElement.getText();
				if (sFileToCheck.equals(sAlbumName)) {
					sValue = true;
					break forLoops;
				}
			}
		}
		return sValue;
	}// end of verifyShareFiles

	public void clickOnTextWebTable(String sTabName, String sFileName) {
		switch (sTabName) {
		case "All Files":
			this.clickOnTextImageInTable(sFileName);
			break;
		case "Pictures":
			this.clickOnPhotoAlbum(sFileName);
			break;
		default:
			this.clickOnTextInTable(sFileName);
			break;
		}
	}// end of verifyShareFiles

	public void clickOnTextInTable(String sFileName) {
		// find all element in given xpathtable, if its style is block ,it is
		// split
		// into 3 part and store in sdiv[] array.
		// then we get text on elementToClick and if its matches the passed
		// filename,
		// click on that elementToClick
		String xPathTable = "//div[@id='DataList']/div";
		List<WebElement> lItems = method.findAllElements(xPathTable);
		forLoops: for (WebElement elem : lItems) {
			String[] sDiv = elem.getAttribute("id").split("_", 3);
			String rowNumber = sDiv[1];
			String sXpath = "//div[@id='DataList_" + rowNumber + "_File']";
			WebElement elementToClick = method.findElement(sXpath);
			String sFileToCheck = elementToClick.getText();
			if (sFileToCheck.equals(sFileName)) {
				elementToClick.click();
				break forLoops;
			}
		}
	}// end of clickOnTextInTable

	public void clickOnTextImageInTable(String sImageName) {
		// find all element in given xpathtable, if its style is block ,it is
		// split
		// into 3 part and store in sdiv[] array.
		// then we get text on elementToClick and if its matches the passed
		// imagename,
		// get xpath of image w.r.t row number and click on that image
		String xPathTable = "//div[@id='DataList']/div";
		List<WebElement> lItems = method.findAllElements(xPathTable);
		forLoops: for (WebElement elem : lItems) {
			String[] sDiv = elem.getAttribute("id").split("_", 3);
			String rowNumber = sDiv[1];
			String sXpath = "//div[@id='DataList_" + rowNumber + "_File']";
			WebElement elementToClick = method.findElement(sXpath);
			String sFileToCheck = elementToClick.getText();
			if (sFileToCheck.equals(sImageName)) {
				String imageXpath = "//*[@id='DataList_" + rowNumber
						+ "_Image']";
				method.clickLocator(imageXpath);
				break forLoops;

			}
		}
	}// end of clickOnTextImageInTable

	public void clickOnPhotoAlbum(String sAlbumName) {
		// String xPathTable = "//*[@id='DataGrid']";
		String sID = elemID.getIdOfElem("DataGrid", "webElemIDs");
		String xPathTable = "//*";
		if (sID.contains("/")) {
			xPathTable = sID;
		} else {
			xPathTable = "//*[@id='" + sID + "']";
		}
		// String xPathTable = "//div[@id='DataGrid']/div";
		List<WebElement> lItems = method.findAllElements(xPathTable);
		forLoops: for (WebElement elem : lItems) {
			System.out.println("++++++++");
			System.out.println("elem" + elem);
			System.out.println("attri" + elem.getAttribute("id"));
			String[] sDiv = elem.getAttribute("id").split("_", 3);
			String rowNumber = sDiv[1];
			sID = elemID.getIdOfElem("AlbumXpath", "webElemIDs");
			String sXpath = "//*";
			if (sID.contains("/")) {
				sXpath = sID;
			} else {
				sXpath = "//*[@id='" + sID + "']";
			}
			sXpath = sXpath.replaceFirst("<rownumber>", rowNumber + "");
			// String sXpath ="//*[@id='DataGrid_"+rowNumber+"_Name']/span[1]";

			// "//div[@id='DataGrid_" + rowNumber + "_Name']";
			WebElement elementToClick = method.findElement(sXpath);
			String sFileToCheck = elementToClick.getText();
			System.out.println("1album name-->*" + sAlbumName + "*");
			System.out.println("2album name-->*" + sFileToCheck + "*");
			if (sFileToCheck.equalsIgnoreCase(sAlbumName)) {
				System.out.println("inside if");
				String imageXpath = "//*[@id='DataGrid_" + rowNumber
						+ "_Image']";
				method.clickLocator(imageXpath);
				break forLoops;
			}
			System.out.println("-------------------");
		}

	}// end of clickOnPhotoAlbum

	public void selectPhotoAlbum(String sAlbumName) {
		Actions builder = new Actions(driver);
		// find all element in given xpathtable, if its style is block ,it is
		// split
		// into 3 part and store in sdiv[] array.
		// then we gets xpath of album w.r.t row number and find element at that
		// xpath,
		// if that element get text is equal to album name, find imagexpath and
		// click on it
		String xPathTable = "//div[@id='DataGrid']/div";
		List<WebElement> lItems = method.findAllElements(xPathTable);
		forLoops: for (WebElement elem : lItems) {
			String[] sDiv = elem.getAttribute("id").split("_", 3);
			String rowNumber = sDiv[1];
			String sXpath = "//div[@id='DataGrid_" + rowNumber + "_Name']";
			WebElement elementToClick = method.findElement(sXpath);
			String sFileToCheck = elementToClick.getText();
			if (sFileToCheck.equals(sAlbumName)) {
				String selectXpath = "//*[@id='DataGrid_" + rowNumber
						+ "_Select']";
				WebElement hoverElement3 = driver.findElement(By
						.xpath(selectXpath));
				builder.moveToElement(hoverElement3);
				builder.click();
				builder.perform();
				System.out.println("Inside selectPhotoAlbum. Clicked on ->"
						+ selectXpath);
				break forLoops;
			}
		}

	}// end of selectPhotoAlbum

	public void clickCheckboxWebTable(String sFileName) {
		// find all element in given xpathtable, if its style is block ,it is
		// split
		// into 3 part and store in sdiv[] array.
		// then we get text on fileElement and titleElement and if its matches
		// the
		// passed file name ,click on that respective checkbox.
		String xPathTable = "//div[@id='DataList']/div";
		List<WebElement> lItems = method.findAllElements(xPathTable);
		forLoops: for (WebElement elem : lItems) {
			if (elem.getAttribute("style").equals("display: block;")) {
				String[] sDiv = elem.getAttribute("id").split("_", 3);
				String rowNumber = sDiv[1];
				String sXpathFile = "//div[@id='DataList_" + rowNumber
						+ "_File']";
				String sXpathTitle = "//div[@id='DataList_" + rowNumber
						+ "_Title']";
				WebElement fileElement = method.findElement(sXpathFile);
				String sFileToCheck = fileElement.getText();

				WebElement titleElement = method.findElement(sXpathTitle);
				String sTitleToCheck = titleElement.getText();

				String sXpathOfCheckBox = "//div[@id='DataList_" + rowNumber
						+ "_Select']";

				WebElement elemCheckBox = method.findElement(sXpathOfCheckBox);

				if (sFileToCheck.equals(sFileName)) {
					method.clickElement(elemCheckBox);
					break forLoops;
				}
				if (sTitleToCheck.equals(sFileName)) {
					method.clickElement(elemCheckBox);
					break forLoops;
				}
			}
		}
	}// end of clickCheckboxWebTable

	public String addIntegers(String iNumA, String iNumB) {
		// this method converts two string values to integer ,add them,
		// again convert them into string and return that value.
		int total = Integer.parseInt(iNumA) + Integer.parseInt(iNumB);
		String sTotalValue = Integer.toString(total);
		return sTotalValue;
	} // end of addIntegers

	public void gotoJioDriveDownloadFolder() {
		if (this.checkTextOnPage("Jio Drive")) {
			this.tapTextOnApp("Jio Drive");
			this.tapIconOnApp("Downloads");
		} else {
			this.deviceKey("menu");
			this.tapIconOnApp("Search");
			this.findTextViewAndClickWithOptional("Search", "Find",
					"SearchFind");
			this.enterTextInFieldOnDevice("Search query", "Jio Drive");
			this.tapTextOnApp("Jio Drive");
			this.tapIconOnApp("Downloads");
		}
	} // end of gotoJioDriveDownloadFolder

	public void swipeOnDevice(String sDirection, int swipeTimes) {
		for (int i = 1; i <= swipeTimes; i++) {
			if (sDirection.equals("Left")) {
				method.executeDriverJS("swipe", 0, 0.20, 0.5, 0.80, 0.5, 4.0,
						null);
			} else if (sDirection.equals("Right")) {
				method.executeDriverJS("swipe", 0, 0.95, 0.5, 0.05, 0.5, 3.0,
						null);
			} else if (sDirection.equals("Down")) {
				method.executeDriverJS("swipe", 0, 0.5, 0.95, 0.5, 0.1, 3.0,
						null);
			} else if (sDirection.equals("Up")) {
				method.executeDriverJS("swipe", 0, 0.5, 0.3, 0.5, 0.95, 3.0,
						null);
			}

		}
	}// end of swipeOnDevice

	public void tapOnImage(int iNumber) {
		/*
		 * try { //Logger.initLogger(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		// this method find xpath of imageitem form mobileAppDIs,modify it
		// according to passed iNumber and click on that xpath
		String sXpathToModify = method.getXpathOfElement("imageItem",
				"mobileAppIDs");
		String sXpath = sXpathToModify + iNumber + "]";
		method.clickLocator(sXpath);

	}// end of tapOnImage

	public void selectItemRowOnWeb(int iNumber) {
		// this method gets xpath of row in web table w.r.t iNumber passed
		// and click on that xpath
		iNumber = iNumber - 1;
		String xPathTable = "//div[@id='DataList_" + iNumber + "_Select']";
		method.clickLocator(xPathTable);
	} // end of selectItemRowOnWeb

	public String getTextOnWeb(int iNumber) {
		// this method gets xpath of row in web table w.r.t iNumber passed,
		// find element on that xpath,get text and return it.
		iNumber = iNumber - 1;
		String xPathFile = "//div[@id='DataList_" + iNumber + "_File']";
		WebElement fileElement = method.findElement(xPathFile);
		String sFileToCheck = fileElement.getText();
		return sFileToCheck;

	} // end of getTextOnWeb

	public String getCommonString(String sText) {
		// if sText passed in parameter matches with any text in
		// "if" condition it returns that respective commonString
		String sCommonString = sText;
		if (sText.equals("More") || sText.equals("More options")
				|| sText.equals("Menu")) {
			sCommonString = "Menu";
			return sCommonString;
		}
		if (sText.equals("Save") || sText.equals("Done") || sText.equals("OK")) {
			sCommonString = "Save";
			return sCommonString;
		}
		if (sText.equals("Details") || sText.equals("Properties")) {
			sCommonString = "Details";
			return sCommonString;
		}
		if (sText.equals("Close") || sText.equals("Cancel")) {
			sCommonString = "Close";
			return sCommonString;
		}
		if (sText.equals("Create") || sText.equals("Add Contact")
				|| sText.equals("Add") || sText.equals("New")
				|| sText.equals("New contact")
				|| sText.equals("Create new contact")
				|| sText.equals("Add contact")) {
			sCommonString = "Create";
			return sCommonString;
		}
		if (sText.equals("Sync Drive")) {
			sCommonString = "SyncDrive";
			return sCommonString;
		}
		if (sText.equals("My Files") || sText.equals("File Manager")
				|| sText.equals("File")) {
			sCommonString = "My Files";
			return sCommonString;
		}
		if (sText.equals("Contacts") || sText.equals("People")
				|| sText.equals("Address")) {
			sCommonString = "Contacts";
			return sCommonString;
		}
		if (sText.equals("Jio Drive")) {
			sCommonString = "JioDrive";
			return sCommonString;
		}
		if (sText.equals("Delete") || sText.equals("Delete contact")) {
			sCommonString = "Delete";
			return sCommonString;
		}
		if (sText.equals("Name") || sText.equals("First name")
				|| sText.equals("First Name")) {
			sCommonString = "Name";
			return sCommonString;
		}
		if (sText.equals("Phone") || sText.equals("Phone no")
				|| sText.equals("Phone No")) {
			sCommonString = "Phone";
			return sCommonString;
		}

		return sCommonString;
	}// end of getCommonString

	public String assertText(String sText) {
		// Assert.IsTrue(Selenium.IsTextPresent(sText));
		// String bodyText = driver.findElement(By.tagName("body")).getText();
		// Assert.assertTrue(bodyText.contains(sText));
		String result = null;
		String bodyText = driver.findElement(By.tagName("body")).getText();
		if (bodyText.contains(sText)) {
			result = "True";
		} else
			result = "False";
		return result;
	}

	public String getTextOnWebPath(String ePath) {
		String xPathString = method.getXpathOfElement(ePath, "webElemIDs");
		// String webText=driver.findElement(By.xpath(xPathString)).getText();
		// WebDriverWait wait = new WebDriverWait(driver, 10);
		// WebElement webText =
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathString)));
		WebElement webText = method.findElement(xPathString);
		return webText.getText();
	}

	public void switchToFrameId(String frameId) {
		String Fid = method.getIDOfElement(frameId, "webElemIDs");
		driver.switchTo().frame(Fid);
	}

	public int totalPage(int noOfPage) {
		float pageCount = noOfPage / 25f;
		double tpage = Math.ceil(pageCount);
		int finalpagecount = (int) tpage;
		return finalpagecount;
	}

	public void scrollOnWeb(int x, int y) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy('" + x
				+ "','" + y + "')");
	}

	public void enterDate(int fieldId, String date) {
		// method.clickWebElement("/html/body/div[1]/div[3]/table/thead/tr[1]/th[2]");
		// //month
		// method.clickAppElement("/html/body/div[1]/div[4]/table/thead/tr/th[2]");//year
		// String date="22/10/2014";
		String date1[] = date.split("/");
		String day = date1[0];
		String month = date1[1];
		String year = date1[2];
		for (int i = 1; i <= 12; i++) {
			String xPath = "/html/body/div[" + fieldId
					+ "]/div[5]/table/tbody/tr/td/span[" + i + "]";
			String yearToSelect = driver.findElement(By.xpath(xPath)).getText();
			if (yearToSelect.equals(year)) {
				driver.findElement(By.xpath(xPath)).click();
				System.out.println("Year X path" + xPath);
				break;
			}
		}
		for (int i = 1; i <= 12; i++) {
			String xPath = "/html/body/div[" + fieldId
					+ "]/div[4]/table/tbody/tr/td/span[" + i + "]";
			String monthToSelect = driver.findElement(By.xpath(xPath))
					.getText();
			if (monthToSelect.equals(month)) {
				driver.findElement(By.xpath(xPath)).click();
				System.out.println("Month X path" + xPath);
				break;
			}
		}

		List<WebElement> dayList = new ArrayList<WebElement>();
		String xPath = "/html/body/div[" + fieldId
				+ "]/div[3]/table/tbody/tr/td[@class='day']";

		// Xpath to use position with class here we are using "and" operator in
		// xpath: /html/body/div[1]/div[3]/table/tbody/tr[1]/td[position()=1 and
		// @class='day']

		dayList = driver.findElements(By.xpath(xPath));
		for (WebElement dayElem : dayList) {
			System.out.println("Day: " + dayElem.getText());
			String dayActual = dayElem.getText();
			if (dayActual.equals(day)) {
				dayElem.click();
				System.out.println("Day selected");
				break;
			}
		}
	}

	public String assertDashBoardT(String tableName, String data) {
		String result = null;
		String[] rows = data.split(",");
		String[][] matrix = new String[rows.length][];
		int r = 0;
		int citycount = 0;
		for (String row : rows) {
			matrix[r++] = row.split(":");
		}

		String xPath1 = null;
		if (tableName.contains("INSTALLATIONS BY CITIES")) {
			xPath1 = "//*[@id='__bookmark_2']/tbody/tr[";
		} else if (tableName.contains("INSTALLATIONS BY OS VERSION")) {
			xPath1 = "//*[@id='__bookmark_4']/tbody/tr[";
		} else if (tableName.contains("INSTALLATIONS BY MODEL NAME")) {
			xPath1 = "//*[@id='__bookmark_6']/tbody/tr[";
		}

		System.out.println("Converting Text to Multi-Dimensional Array-->");
		System.out.println(Arrays.deepToString(matrix));

		forloop: for (int i = 1; i <= rows.length; i++) {
			for (int j = 1; j <= 2; j++) {
				String city = driver.findElement(
						By.xpath(xPath1 + i + "]/td[1]/div")).getText();
				String installapp = driver.findElement(
						By.xpath(xPath1 + i + "]/td[2]/div")).getText();
				if (city.equals(matrix[i - 1][j - 1])
						|| installapp.equals(matrix[i - 1][j - 1])) {
					System.out.println("Passed Element--->"
							+ matrix[i - 1][j - 1]);
					citycount = 1;
				} else {
					System.out.println("Failed Element--->"
							+ matrix[i - 1][j - 1]);
					citycount = 2;
					break forloop;
				}
			}
		}
		if (citycount == 1) {
			result = "Passed";
			System.out.println(tableName + " table Assertion Passed");
		} else {
			result = "Failed";
			System.out.println(tableName + " table Assertion Failed");
		}

		return result;
	}

	public String HighlightedElement(String elem) {
		String result = null;
		WebElement activeElements = driver.findElement(By.className("active"));
		WebElement activeLinks = activeElements.findElement(By.tagName("a"));
		String elemText = activeLinks.getText();
		System.out.println("Active Element is-" + elemText);
		if (elemText.equals(elem))
			result = "True";
		else
			result = "False";
		return result;
	}

	public String tagValue(String elemId) {
		String xPathString = method.getXpathOfElement(elemId, "webElemIDs");
		String values = driver.findElement(By.xpath(xPathString)).getAttribute(
				"value");
		System.out.println("Values IS" + values);
		return values;
	}

	public String calculateDays(String sDate, String eDate) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date d1 = null;
		Date d2 = null;
		String days = null;
		try {
			d1 = format.parse(sDate);
			d2 = format.parse(eDate);
			// in milliseconds
			long diff = d2.getTime() - d1.getTime();
			long diffDays = diff / (24 * 60 * 60 * 1000);
			days = String.valueOf(diffDays);
			System.out.print(diffDays + " days, ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return days;
	}

	public String assertReportT(String data) {
		// *[@id="__bookmark_1"]/tbody/tr[2]/td[1]/div
		// *[@id="__bookmark_1"]/tbody/tr[3]/td[1]/div
		String result = null;
		String xPath1 = "//*[@id='__bookmark_1']/tbody/tr[";
		String[] rows = data.split(",");
		for (int i = 1; i <= rows.length; i++) {
			String date = driver.findElement(
					By.xpath(xPath1 + (i + 1) + "]/td[1]/div")).getText();
			System.out.println("Actual Date-->" + date);
			if (date.equals(rows[i - 1])) {
				System.out.println("Passed at-->" + rows[i - 1]);
				result = "Passed";
			} else {
				System.out.println("Failed at-->" + rows[i - 1]);
				result = "Failed";
				break;
			}
		}
		return result;
	}

	public String checkRadioButton(String wElem) {
		String result = null;
		String xPathString = method.getXpathOfElement(wElem, "webElemIDs");
		System.out.println("XPath Is-->" + xPathString);
		String str = driver.findElement(By.xpath(xPathString)).getAttribute(
				"checked");
		System.out.println("Radio Values is-->" + str);
		if (str.equals("true") || str.equals("checked")) {
			System.out.println("Radio button selected");
			result = "True";
		} else {
			result = "False";
		}
		return result;
	}

	public void dropDownList(String webElem, String sText) {
		String xPathString = method.getXpathOfElement(webElem, "webElemIDs");
		Select oSelection = new Select(
				driver.findElement(By.xpath(xPathString)));
		oSelection.selectByVisibleText(sText);
		method.driverWait(3);
	}

	public void hoverElement(String elem) {
		String xPathString = method.getXpathOfElement(elem, "webElemIDs");
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(By.xpath(xPathString));
		action.moveToElement(we).build().perform();
	}

	public void selectRadioButton(String name, int number) {
		String xPathString = method.getIDOfElement(name, "webElemIDs");
		List<WebElement> oRadioButton = driver.findElements(By
				.name(xPathString));
		oRadioButton.get(number).click();
	}

	public String getIdOfElement(String xPath) {
		String xPathString = method.getIDOfElement(xPath, "webElemIDs");
		String id = driver.findElement(By.xpath(xPathString))
				.getAttribute("id");
		return id;
	}

	public void refreshPage() {
		driver.navigate().refresh();
	}

	public void closeTab(String xPath, String Id) {
		String xPathString = method.getIDOfElement(xPath, "webElemIDs");
		String xpathimage = xPathString.replace("<ImageId>", Id);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(xpathimage)).click();
	}

	public String getFileName(String downloadpath) {
		String filename = null;
		File appfile = new File(downloadpath);
		File[] files = appfile.listFiles();
		long currentTime = files[0].lastModified();
		System.out.println("File -->" + files[0].getName()
				+ " waslst modified on " + files[0].lastModified());
		for (File file1 : files) {
			if (file1.lastModified() > currentTime)

			{
				System.out.println("-------------------------------");
				System.out.println(file1.lastModified());
				System.out.println(file1.getName());
				System.out.println(file1.getAbsolutePath());
				System.out.println("-------------------------------");
				currentTime = file1.lastModified();

				filename = file1.getName();
			}
		}
		return filename;
	}

	public void deleteReportFiles(String downloadpath) {
		File appfile = new File(downloadpath);
		File[] files = appfile.listFiles();
		for (File file2 : files) {

			if (file2.getName().startsWith("reports")) {
				file2.delete();
			} else {
				System.out.println("File not report.txt");
			}
		}
	}

	public Boolean verifyBoxReport(String dmodelName, String appinstalled,
			String sdate, String timetaken) {// String strId
												// validate model name
		String xPathdmodel = method.getXpathOfElement("transfer_model",
				"webElemIDs");
		/*
		 * String model = (xPathdmodel.substring(0,xPathdmodel.length() )); int
		 * j=model.length(); char i= model.charAt(j-2); int i1=(int)i; String a=
		 * model.substring(0,model.length()-2 ); char c ='1'; int c1=(int)c; int
		 * ic = i1-c1;
		 */
		String mname = (driver.findElement(By.xpath(xPathdmodel))).getText();// Modelname

		// validate appname
		String xPathapp = method
				.getXpathOfElement("transfer_app", "webElemIDs");
		/*
		 * System.out.println("xpathOfApp ="+ xPathapp); int d =
		 * xPathapp.length(); char e = xPathapp.charAt(d-5); char f =
		 * xPathapp.charAt(d-6); String fe = ""+f+e; int g =
		 * Integer.parseInt(fe); int l=1; int h= g-l; String applabel1=
		 * xPathapp.substring(0,xPathapp.length()-7);
		 */
		String applabel = (driver.findElement(By.xpath(xPathapp))).getText();

		// validate storeId
		// String xPathstore = method.getXpathOfElement(strId, "webElemIDs");
		// String storeinfo =
		// driver.findElement(By.xpath(xPathstore)).getText();

		// validate date

		String xPathdate = method.getXpathOfElement("transfer_date",
				"webElemIDs");
		String dateinfo = driver.findElement(By.xpath(xPathdate)).getText();

		// validate time taken

		String xPathtime = method.getXpathOfElement("transfer_time",
				"webElemIDs");
		String timeinfo = driver.findElement(By.xpath(xPathtime)).getText();
		// System.out.println(mname+ ".. " + applabel + " .. " + dateinfo + ".."
		// + timeinfo);

		if (mname.equalsIgnoreCase(dmodelName)
				&& applabel.equalsIgnoreCase(appinstalled)
				&& dateinfo.equalsIgnoreCase("Date: " + sdate)
				&& timeinfo.equalsIgnoreCase("Time taken: " + timetaken))
			// storeinfo.equalsIgnoreCase("Store ID:" + strId)&&
			return true;
		else
			return false;
	}

	public String getReportCount(String reporttillcnt) {
		String xPathtillcnt1 = method.getXpathOfElement("reporttillcnt",
				"webElemIDs");
		String actualcnt1 = driver.findElement(By.xpath(xPathtillcnt1))
				.getText();
		System.out.println("intial report count=" + actualcnt1);
		return actualcnt1;

	}

	// getPendingreportcnt
	public String getIReportCount(String reporttillcnt) {
		String xPathtillcnt1 = method.getXpathOfElement("reporttillcnt",
				"webElemIDs");
		String actualcnt1 = driver.findElement(By.xpath(xPathtillcnt1))
				.getText();
		System.out.println("intial report count=" + actualcnt1);
		return actualcnt1;

	}

	// getFinalreportcnt

	public void keyBoardButton(String bName) {
		try {
			Robot robot = new Robot();
			if (bName.contains("control")) {
				Thread.sleep(5000);
				robot.keyPress(KeyEvent.VK_CONTROL);
			} else if (bName.contains("enter")) {
				Thread.sleep(5000);
				robot.keyPress(KeyEvent.VK_ENTER);
			} else if (bName.contains("shift")) {
				Thread.sleep(5000);
				robot.keyPress(KeyEvent.VK_SHIFT);
			} else if (bName.contains("A")) {
				Thread.sleep(5000);
				robot.keyPress(KeyEvent.VK_A);
			} else if (bName.contains("C")) {
				Thread.sleep(5000);
				robot.keyPress(KeyEvent.VK_C);
			} else if (bName.contains("H")) {
				Thread.sleep(5000);
				robot.keyPress(KeyEvent.VK_H);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}

	public void browserKey(String sText) {
		if (sText.contains("Back")) {
			driver.navigate().back();
		} else if (sText.contains("Forward")) {
		driver.navigate().forward();
		} else if (sText.contains("Refresh")) {
			driver.navigate().refresh();
		}
		
	}

	public boolean verifyTextPresent(String sText) {
		return driver.findElement(By.tagName("body")).getText().contains(sText);
	}

	public String isElemPresent(String slocator) {
		if (method.isElementDisplayed(slocator)) {
			return "True";
		} else {
			return "False";
		}
	}

	public String assertTextOnButton(String elem, String sText) {
		String elemXpath = method.getXpathOfElement(elem, "webElemIDs");
		String result = null;
		String sValue = driver.findElement(By.xpath(elemXpath)).getText();
		if (sValue.isEmpty()) {
			sValue = driver.findElement(By.xpath(elemXpath)).getAttribute(
					"value");
			// System.out.println("Value: " + sValue);
		}
		if (sValue.contains(sText)) {
			result = "True";
		} else {
			result = "False";
		}
		return result;
	}

	public String checkBoxSelected(String wElem) {
		String result = "Failed";
		String xPathString = method.getXpathOfElement(wElem, "webElemIDs");
		System.out.println("XPath Is-->" + xPathString);
		String str = driver.findElement(By.xpath(xPathString)).getAttribute(
				"checked");
		System.out.println("CheckBox Values is-->" + str);
		if (str.equals("true") || str.equals("checked")) {
			System.out.println("CheckBox selected");
			result = "True";
		} else {
			result = "False";
		}
		return result;
	}
	public void selectListElem(String wElem, String listElem) {
		  String xPathString = method.getXpathOfElement(wElem, "webElemIDs");
		  WebElement xpath = driver.findElement(By.xpath(xPathString));
		  Select se = new Select(xpath);
		  String[] city = listElem.split(",");
		  System.out.println("City---->" + city);
		  for (String name : city) {
		   se.selectByVisibleText(name);
		  }
	}

	public String SelectedListItems(String wElem, String listElem) {
		String result = "failed";
		String[] aString = listElem.split(",");
		for(int i=0;i<aString.length;i++){
			System.out.println(aString[i]);
		}
		
		String xPathString = method.getXpathOfElement(wElem, "webElemIDs");
		  List<WebElement> lWebElem = driver.findElements(By.xpath(xPathString));
		for(int i=0;i<aString.length;i++){
			result = "failed";
		  for(WebElement elem : lWebElem){
			  if(elem.getText().contains(aString[i])){
				  result = "passed";
				  System.out.println(elem.getText());
			  }
		  }
		}

		  if(result.equals("passed")){
			  return "True";
		  }else{
			  return "False";
		  }    
	}
	
	public void clickText(String tName) {
		  WebDriverWait wait = new WebDriverWait(driver, 15);
		  WebElement webElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(tName)));
		  webElem.click();

	}
		  
	public void acceptAlert(long timeout){
	      {
	        long waitForAlert= System.currentTimeMillis() + timeout;
	        boolean boolFound = false;
	        do
	        {
	          try
	          {
	            Alert alert = WebAutomationBootstrap.driver.switchTo().alert();
	            if (alert != null)
	            {
	              alert.accept();
	              boolFound = true;
	            }
	          }
	          catch (NoAlertPresentException ex) {}
	        } while ((System.currentTimeMillis() < waitForAlert) && (!boolFound));
	      }
	 }  
	public void dismisstAlert() {
		Alert alert=driver.switchTo().alert();
		alert.dismiss ();
		 }
	
	public String VerifyTextInTableCell(String tableElem, String sText, String tText){
		String result = "Failed";
		String rowXpath = method.getXpathOfElement(tableElem, "webElemIDs") + "/tr";
		System.out.println(rowXpath);
		int rowSize = driver.findElements(By.xpath(rowXpath)).size();
		System.out.println(rowSize);
		for (int i =1;i<=rowSize;i++){
			String rXpathToCheck = rowXpath + "[" + i + "]";
			System.out.println(rXpathToCheck);
			//System.out.println(rXpathToCheck);
			String textInFirstColumn = driver.findElement(By.xpath(rXpathToCheck)).getText();  
			System.out.println(textInFirstColumn);
			if(textInFirstColumn.contains(sText)){
				for(int j=1;j<9; j++){
					String tdtXpathToCheck = rXpathToCheck + "/td[" + j + "]";
					System.out.println(tdtXpathToCheck);
					String textInColumn = driver.findElement(By.xpath(tdtXpathToCheck)).getText();
					System.out.println(textInColumn);
					//System.out.println(tText);
					if(textInColumn.contains(tText)){
						System.out.println("text found");
						result = "True";
						break;
					}
					
				}
			}
		}
		return result;
	}
	
	public void ClickOnTextInTableCell(String tableElem, String sText, String tText){
		try{
		String rowXpath = method.getXpathOfElement(tableElem, "webElemIDs") + "/tr";
		int rowSize = driver.findElements(By.xpath(rowXpath)).size();
		//System.out.println(rowSize);
		for (int i =1;i<=rowSize;i++){
			String rXpathToCheck = rowXpath + "[" + i + "]";
			//System.out.println(rXpathToCheck);
			String textInFirstColumn = driver.findElement(By.xpath(rXpathToCheck)).getText();  
			//System.out.println(textInFirstColumn);
			if(textInFirstColumn.contains(sText)){
				for(int j=1;j<9; j++){
					String tdtXpathToCheck = rXpathToCheck + "/td[" + j + "]";
					System.out.println(tdtXpathToCheck);
					String textInColumn = driver.findElement(By.xpath(tdtXpathToCheck)).getText();
					//System.out.println(textInColumn);
					if(textInColumn.contains(tText)){
						System.out.println("Element to be clicked:"+tdtXpathToCheck);
						//WebDriverWait wait = new WebDriverWait(driver, 10);
						//wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.xpath(tdtXpathToCheck))));
						driver.findElement(By.xpath(tdtXpathToCheck)).click();
						System.out.println(textInColumn);
						break;

					}
					
				}
  			}
		}}
		catch (UnhandledAlertException e){
			e.printStackTrace();
		}
	}
	public String createTimeStamp() {
		long time = System.currentTimeMillis();
		String timeStamp = String.valueOf(time);
		System.out.println(timeStamp);
		return timeStamp;
	}

	public String callGet(String url, String uri, String header) throws ClientProtocolException, IOException {
	  String responseString = null;
	  HttpGet http = new HttpGet(url + uri);
	  ArrayList<String> List;
	  List = new ArrayList<String>(Arrays.asList(header.split(",")));
	  System.out.println(uri);
	  for (String headerList : List) {
	   String[] header1 = headerList.split(":");
	   System.out.println(header1[0] + ":" + header1[1]);
	   http.addHeader(header1[0], header1[1]);
	   
	   // Execute the request using httpclient and save the response

	   HttpClient httpClient = HttpClients.createDefault();
	   HttpResponse response = null;
	   response = httpClient.execute(http);

	   // Display the response

	   HttpEntity entity = response.getEntity();
	   responseString = EntityUtils.toString(entity, "UTF-8");

	   System.out.println(responseString);
	  }

	  return responseString;

	 }
	
	public void switchToPopUp() {
		//String ModTitle =method.getXpathOfElement(ModelFrameTitle, "webElemIDs");
		//driver.switchTo().activeElement();
		//String name= driver.getTitle();
		//System.out.println(name);
		//Set<String> popupHandle=driver.getWindowHandles();
		//System.out.println(popupHandle);
			 //driver.findElement(By.id("approvalConfirm")).click();
 WebElement openModal  = driver.findElement(By.xpath("//*[@id='campaignApprove']"));
openModal.click();
WebDriverWait block = new WebDriverWait(driver,10);
WebElement modal = block.until(ExpectedConditions.visibilityOfElementLocated(By.id("approvalConfirm")));
modal.findElement(By.id("approvalConfirm")).click();
System.out.println("Ok clicked");
	}

	public void selectFilesFromLocalDrive(String sFileType) {
		  try {
		   String xPathString = method.getXpathOfElement(
		     "Browse your computer", "webElemIDs");
		   // driver.findElement(By.xpath("//*[@id='SimpleUpload_Buttons']/div[2]/h1")).click();
		   driver.findElement(By.xpath(xPathString)).click();
		   // driver.findElement(By.id("fileupload")).click();
		   System.out.println("clicked fileupload button");
		   // <input id="fileupload" name="file" multiple="" type="file">

		   Thread.sleep(5000);

		   StringSelection ss = new StringSelection(sFileType);
		   // "\"C:\\automation\\Selenium_testcases\\Images\\download2.jpg");
		   Toolkit.getDefaultToolkit().getSystemClipboard()
		     .setContents(ss, null);
		   System.out.println("copied text to clipboard");
		   Robot robot = new Robot();
		   Thread.sleep(5000);
		   robot.keyPress(KeyEvent.VK_ENTER);
		   Thread.sleep(1000);
		   robot.keyRelease(KeyEvent.VK_ENTER);
		   Thread.sleep(1000);
		   robot.keyPress(KeyEvent.VK_CONTROL);
		   Thread.sleep(1000);
		   robot.keyPress(KeyEvent.VK_V);
		   Thread.sleep(1000);
		   robot.keyRelease(KeyEvent.VK_V);
		   Thread.sleep(1000);
		   robot.keyRelease(KeyEvent.VK_CONTROL);
		   Thread.sleep(1000);
		   robot.keyPress(KeyEvent.VK_ENTER);
		   Thread.sleep(1000);
		   robot.keyRelease(KeyEvent.VK_ENTER);
		   System.out.println("File uploaded");
		  } catch (InterruptedException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  } catch (AWTException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
	}
	
	public void makeRunningOffer(String dburl, String dbuser, String dbpw, String offerName) throws SQLException {
		 
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("-------- PostgreSQL " + "JDBC Connection Testing ------------");

        try {

               Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

               System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
               e.printStackTrace();
               return;

        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        Connection connection = null;

        try {

               connection = DriverManager.getConnection(dburl, dbuser, dbpw);
               connection.setAutoCommit(true);
        } catch (SQLException e) {

               System.out.println("Connection Failed! Check output console");
               e.printStackTrace();
               return;

        }
        String running_date = sdf.format(date);

        // Statement stmt = connection.createStatement();
        String sql = "UPDATE stores.consumer_offer_m SET offer_start_date=" + "'" + running_date + "'"
                     + "where offer=?";
        // stmt.executeQuery(sql);

        PreparedStatement preparedStmt = connection.prepareStatement(sql);
        preparedStmt.setString(1, offerName);
        // execute the java preparedstatement
        preparedStmt.executeUpdate();

        connection.close();

        if (connection != null) {
               System.out.println("You made it, take control your database now!");
        } else 
        {System.out.println("Failed to make connection!");
}

 }// end of makeOfferRunning
	
	public void deleteAppFromServer(String dburl, String dbuser, String dbpw, String packageName) throws SQLException {
		
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("-------- PostgreSQL " + "JDBC Connection Testing ------------");

        try {

               Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

               System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
               e.printStackTrace();
               return;

        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        Connection connection = null;

        try {

               connection = DriverManager.getConnection(dburl, dbuser, dbpw);
               connection.setAutoCommit(true);
        } catch (SQLException e) {

               System.out.println("Connection Failed! Check output console");
               e.printStackTrace();
               return;

        }
        //String running_date = sdf.format(date);
        System.out.println("PostgreSQL JDBC Driver Registered!");

        // Statement stmt = connection.createStatement();
        String sql = "UPDATE stores.server_apps set deleted=true where app_id IN (select sa.app_id from stores.app_master am,stores.server_apps sa where am.app_master_id=sa.app_master_id and sa.deleted=false and category_id=1 and am.app_package=?)";
        // stmt.executeQuery(sql);

        PreparedStatement preparedStmt = connection.prepareStatement(sql);
        preparedStmt.setString(1, packageName);
        // execute the java preparedstatement
        preparedStmt.executeUpdate();
        connection.close();

        if (connection != null) {
               System.out.println("You made it, take control your database now!");
        } else 
        {System.out.println("Failed to make connection!");
}

 }// end of deleteApp
	
	public void resetPw(String dburl, String dbuser, String dbpw, String emailId, String password) throws SQLException {
		 
        System.out.println("-------- PostgreSQL " + "JDBC Connection Testing ------------");

        try {

               Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

               System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
               e.printStackTrace();
               return;

        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        Connection connection = null;

        try {

               connection = DriverManager.getConnection(dburl, dbuser, dbpw);

        } catch (SQLException e) {

               System.out.println("Connection Failed! Check output console");
               e.printStackTrace();
               return;

        }

        // Statement stmt = connection.createStatement();
        String sql = "UPDATE security.user_register SET encrypted_password=?, is_locked= FALSE WHERE email_id=?";

        // stmt.executeQuery(sql);

        PreparedStatement preparedStmt = connection.prepareStatement(sql);
        preparedStmt.setString(1, password);
        preparedStmt.setString(2, emailId);
        // execute the java preparedstatement
        preparedStmt.executeUpdate();

        connection.close();

        if (connection != null) {
               System.out.println("You made it, take control your database now!");
        } else 
        {System.out.println("Failed to make connection!");
}

 }// end of resetpw	
	
	public void unlockPartnerAccount(String dburl, String dbuser, String dbpw, String emailId) throws SQLException {
		 
        System.out.println("-------- PostgreSQL " + "JDBC Connection Testing ------------");

        try {

               Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

               System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
               e.printStackTrace();
               return;

        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        Connection connection = null;

        try {

               connection = DriverManager.getConnection(dburl, dbuser, dbpw);
               connection.setAutoCommit(true);
        } catch (SQLException e) {

               System.out.println("Connection Failed! Check output console");
               e.printStackTrace();
               return;

        }
        //String running_date = sdf.format(date);
        System.out.println("PostgreSQL JDBC Driver Registered!");

        // Statement stmt = connection.createStatement();
        String sql = "UPDATE security.user_register Set is_active = true, is_locked = false, modified_date = now(), invalid_login_counter = 6"+"WHERE email_id =?";
        // stmt.executeQuery(sql);

        PreparedStatement preparedStmt = connection.prepareStatement(sql);
        preparedStmt.setString(1, emailId);
        // execute the java preparedstatement
        preparedStmt.executeUpdate();
        connection.close();
       

        if (connection != null) {
               System.out.println("You made it, take control your database now!");
        } else 
        {System.out.println("Failed to make connection!");
}

 }// end of unlockPartnerAccount
	
	public void updateAppVersionOnWeb(String dburl, String dbuser, String dbpw, String appVersion, String appName) throws SQLException {
		 
        System.out.println("-------- PostgreSQL " + "JDBC Connection Testing ------------");

        try {

               Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

               System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
               e.printStackTrace();
               return;

        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        Connection connection = null;

        try {

               connection = DriverManager.getConnection(dburl, dbuser, dbpw);
               connection.setAutoCommit(true);
        } catch (SQLException e) {

               System.out.println("Connection Failed! Check output console");
               e.printStackTrace();
               return;

        }
        //String running_date = sdf.format(date);
        System.out.println("PostgreSQL JDBC Driver Registered!");

        // Statement stmt = connection.createStatement();
        String sql = "update stores.server_apps set app_version=? where app_name=?";
        // stmt.executeQuery(sql);

        PreparedStatement preparedStmt = connection.prepareStatement(sql);
        preparedStmt.setString(1, appVersion);
        preparedStmt.setString(2, appName);
        // execute the java preparedstatement
        preparedStmt.executeUpdate();
        connection.close();
       

        if (connection != null) {
               System.out.println("You made it, take control your database now!");
        } else 
        {System.out.println("Failed to make connection!");
}

 }// end of updateAppversiononweb
	
	public String VerifyTextInTable(String tableElem, String sText, String tText){
		String result = "Failed";
		String rowXpath = method.getXpathOfElement(tableElem, "webElemIDs");
		System.out.println(rowXpath);
		int rowSize = driver.findElements(By.xpath(rowXpath)).size();
		System.out.println(rowSize);
		for (int i =1;i<=rowSize;i++){
			String rXpathToCheck = rowXpath + "[" + i + "]";
			System.out.println(rXpathToCheck);
			//System.out.println(rXpathToCheck);
			String textInFirstColumn = driver.findElement(By.xpath(rXpathToCheck)).getText();  
			System.out.println(textInFirstColumn);
			if(textInFirstColumn.contains(sText)){
				for(int j=1;j<9; j++){
					String tdtXpathToCheck = rXpathToCheck + "/td[" + j + "]";
					System.out.println(tdtXpathToCheck);
					String textInColumn = driver.findElement(By.xpath(tdtXpathToCheck)).getText();
					System.out.println(textInColumn);
					//System.out.println(tText);
					if(textInColumn.contains(tText)){
						System.out.println("text found");
						result = "True";
						break;
					}
					
				}
			}
		}
		return result;
	}//End of VerifyTextInTable
	
	public void ClickOnTextInTable(String tableElem, String sText, String tText){
		try{
		String rowXpath = method.getXpathOfElement(tableElem, "webElemIDs");
		int rowSize = driver.findElements(By.xpath(rowXpath)).size();
		System.out.println(rowSize);
		for (int i =1;i<=rowSize;i++){
			String rXpathToCheck = rowXpath + "[" + i + "]";
			System.out.println(rXpathToCheck);
			String textInFirstColumn = driver.findElement(By.xpath(rXpathToCheck)).getText();  
			System.out.println(textInFirstColumn);
			if(textInFirstColumn.contains(sText)){
				for(int j=1;j<9; j++){
					String tdtXpathToCheck = rXpathToCheck + "/td[" + j + "]";
					System.out.println(tdtXpathToCheck);
					String textInColumn = driver.findElement(By.xpath(tdtXpathToCheck)).getText();
					System.out.println(textInColumn);
					if(textInColumn.contains(tText)){
						System.out.println("Element to be clicked:"+tdtXpathToCheck);
						//WebDriverWait wait = new WebDriverWait(driver, 10);
						//wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.xpath(tdtXpathToCheck))));
						driver.findElement(By.xpath(tdtXpathToCheck)).click();
						System.out.println(textInColumn);
						break;
					}
					
				}
  			}
		}}
		catch (UnhandledAlertException e){
			e.printStackTrace();
		}
	}//End of clickonTextInTable
	public void SelectBtnForTextInTable(String tableElem, String buttonElem, String sText){
		boolean breakIt = true;
        while (true) {
        breakIt = true;
        try {

		
		List<WebElement> allElem = driver.findElements(By.xpath(method.getXpathOfElement(tableElem, "webElemIDs")));
	    	for (WebElement elem : allElem)
	    	{
	    		System.out.println("All Elemnents --> "+ elem.getText());
	    		if(elem.getText().contains(sText)){
	    			System.out.println("Text is --->" +elem.getText() +",is matched with --->"+sText);
	    			String xPathForBtn = method.getXpathOfElement(buttonElem, "webElemIDs");
	    			System.out.println(xPathForBtn);
	    			elem.findElement(By.xpath(xPathForBtn)).click();
	    			break;
	    		}  
	    	}
        } catch (NoSuchElementException e) {
        }
        if (breakIt) {
            break;
        }
    }
	}// End of SelectBtnForTextInTable
	
	public void clickBtnInTableCell(String tableElem, String sTextElem, String buttonElem, String sText){
		boolean breakIt = true;
        while (true) {
        breakIt = true;
        try {
		WebElement tableEl = driver.findElement(By.xpath(method.getXpathOfElement(tableElem, "webElemIDs")));
		System.out.println(tableEl);
			List<WebElement> allElem = tableEl.findElements(By.xpath(method.getXpathOfElement(sTextElem, "webElemIDs")));
		
		    	for (WebElement elem : allElem)
		    	{
		    		System.out.println("All Elemnents --> "+ elem.getText());
		    		if(elem.getText().contains(sText)){
		    			System.out.println("Text is --->" +elem.getText() +",is matched with --->"+sText);
		    			String xPathForBtn = method.getXpathOfElement(buttonElem, "webElemIDs");  
		    			System.out.println(xPathForBtn);
		    			elem.findElement(By.xpath(xPathForBtn)).click();
		    			break; 
		    		}  
		    	}
	        } catch (NoSuchElementException e) {
	        }
	        if (breakIt) {
	            break;
	        }
	    }

	    }// End of clickBtnInTableCell

}//End of Class


	
	
	  


