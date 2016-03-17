package com.rjil.cloud.selenium.fitnesse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.rjil.cloud.selenium.startup.WebAutomationBootstrap;

//DriverMethods class consist of Selenium Methods
public class DriverMethods extends WebAutomationBootstrap {

	static ElementIDsReader elemID = new ElementIDsReader();

	// private static org.apache.log4j.Logger logger = Logger.logger;

	public void clickAppElement(String sElement) {
		// get xpath of sElement from mobileAppIDs and click on that element
		this.clickLocator(this.getXpathOfElement(sElement, "mobileAppIDs"));
	}// end of clickAppElement

	public void clickWebElement(String sElement) {
		// get xpath of sElement from webElemIDs and click on that element
		this.clickLocator(this.getXpathOfElement(sElement, "webElemIDs"));
	}// end of clickWebElement

	public List<WebElement> findAllElements(String locator) {
		// find all element by getting xpath of locator and return that elemlist
		List<WebElement> lElemList = driver.findElements(By.xpath(locator));
		return lElemList;
	} // end of findAllElements

	public WebElement findElement(String locator) {
		// find element by getting xpath of locator and return that element
		WebElement wElem = driver.findElement(By.xpath(locator));
		return wElem;
	} // end of findAllElement

	public boolean isElementPresent(String locator) {
		// find element by getting xpath of locator having size
		// greater than 0 and if found returns that element
		String sLocator = this.getXpathOfElement(locator, "webElemIDs");
		boolean isPresent = driver.findElements(By.xpath(sLocator)).size() > 0;
		return isPresent;
	}// end of isElementPresent
	
	public boolean isElementDisplayed(String locator){
		//find out whether is element is displayed or not
		String sLocator = this.getXpathOfElement(locator, "webElemIDs");
		return driver.findElement(By.xpath(sLocator)).isDisplayed();
	}// end of isElementDisplayed
	
	
public boolean isElementEnabled(String locator)
{
	String sLocator = this.getXpathOfElement(locator, "webElemIDs");
	boolean isEnabled = driver.findElement(By.xpath(sLocator)).isEnabled();
	return isEnabled;

}
	public void clickElement(WebElement elem) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement elemToClick = wait.until(ExpectedConditions
				.visibilityOf(elem));
		elemToClick.click();
	}// end of clickElement
		// TODO
		// login

	public void enterTextOnWeb(String sElement, String sText) {
		String sLocator = this.getXpathOfElement(sElement, "webElemIDs");
		this.enterTextOnLocator(sLocator, sText);
	}// end of enterTextOnWeb

	public String getXpathOfElement(String sElement, String sPropertyFile) {
		String sID = elemID.getIdOfElem(sElement, sPropertyFile);
		String xPathString = "//*";
		if (sID.contains("/") || sID.contains("..")) {
			xPathString = sID;
		} else {
			xPathString = "//*[@id='" + sID + "']";
		}
		return xPathString;

	}// end of getXpathOfElement44

	public void clickLocator(String locator) {
		System.out.println(locator);
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement elemToClick = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(locator)));
		System.out.println(elemToClick.getText());
		elemToClick.click();
	}// end of clickLocator

	public void driverWait(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}// end of driverWait

	public String getTextOnElement(WebElement elem) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement elemToCheck = wait.until(ExpectedConditions
				.visibilityOf(elem));
		return elemToCheck.getText();
	}// end of getTextOnElement

	public String getDescription(WebElement elem) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement elemToCheck = wait.until(ExpectedConditions
				.visibilityOf(elem));
		return elemToCheck.getAttribute("name").toString();
	}// end of getDescription

	public void enterTextInField(WebElement elem, String sText) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement elemToCheck = wait.until(ExpectedConditions
				.visibilityOf(elem));
		elemToCheck.clear();
		elemToCheck.sendKeys(sText);
	}// end of enterTextInField

	public void enterTextOnLocator(String sElem, String sText) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement elemToCheck = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(sElem)));
		elemToCheck.clear();
		elemToCheck.sendKeys(sText);
	}// end of enterTextOnLocator

	public void executeDriverJS(String sEvent, int keycode, double startX,
			double startY, double endX, double endY, double duration,
			WebElement elem) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, Integer> intObject = new HashMap<String, Integer>();
		HashMap<String, Double> doubleObject = new HashMap<String, Double>();
		HashMap<String, String> stringObject = new HashMap<String, String>();
		switch (sEvent) {
		case "keyevent":
			intObject.put("keycode", keycode);
			js.executeScript("mobile: keyevent", intObject);
			break;
		case "swipe":
			doubleObject.put("startX", startX);
			doubleObject.put("startY", startY);
			doubleObject.put("endX", endX);
			doubleObject.put("endY", endY);
			doubleObject.put("duration", duration);
			js.executeScript("mobile: swipe", doubleObject);
			break;
		case "longClick":
			stringObject.put("element", ((RemoteWebElement) elem).getId());
			js.executeScript("mobile: longClick", stringObject);
			break;
		default:
			break;
		}
	}// end of executeDriverJS

	public void switchTab() {
		String oldTab = driver.getWindowHandle();
		ArrayList<String> newTab = new ArrayList<String>(
				driver.getWindowHandles());
		newTab.remove(oldTab);
		// change focus to new tab
		driver.switchTo().window(newTab.get(0));
		this.driverWait(5);
	} // end of switchTab

	public void waitTillElementAppears(String sElem) {
		String sLocator = this.getXpathOfElement(sElem, "webElemIDs");
		WebDriverWait wait = new WebDriverWait(driver, 300);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath(sLocator)));
	}// end of waitTillElementAppears

	public void Logout() {
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		ArrayList<String> newTab = new ArrayList<String>(
				driver.getWindowHandles());
		driver.switchTo().window(newTab.get(0));
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		driver.findElement(By.linkText("Logout")).click();
	}

	public String getIDOfElement(String sElement, String sPropertyFile) {
		String id = elemID.getIdOfElem(sElement, sPropertyFile);
		return id;
	}

	public void clickWebElement(String sElement, int number) {
		System.out.println("here *" + sElement + "*" + number);
		String xpath = this.getXpathOfElement(sElement, "webElemIDs");
		xpath = xpath.replace("<rownumber>", number + "");
		System.out.println(xpath + "*************");
		if (sElement.equalsIgnoreCase("Select Photo")) {
			// get xpath of sElement from webElemIDs and click on that element
			Actions builder = new Actions(driver);
			WebElement hoverElement3 = driver.findElement(By.xpath(xpath));
			builder.moveToElement(hoverElement3);
			builder.click();
			builder.perform();
			System.out.println("Inside click element. Clicked on ->" + xpath);
		} else {
			System.out.println("inside select music");
			WebElement elemCheckBox = findElement(xpath);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement elemToClick = wait.until(ExpectedConditions
					.visibilityOf(elemCheckBox));
			elemToClick.click();
			System.out.println("Inside click element. Clicked on ->" + xpath);

		}
	}

	// TODO
	public String clickWebElement(String albumType, String name, String product) {
		try {
			if (product.equalsIgnoreCase("jiodrive")) {
				// String xPathTable = "//*[@id='DataGrid']";
				String sID = elemID.getIdOfElem("DataGrid", "webElemIDs");
				String xPathTable = "//*";
				if (sID.contains("/")) {
					xPathTable = sID;
				} else {
					xPathTable = "//*[@id='" + sID + "']";
				}
				// String xPathTable = "//div[@id='DataGrid']/div";
				List<WebElement> lItems = findAllElements(xPathTable);
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
					// String sXpath
					// ="//*[@id='DataGrid_"+rowNumber+"_Name']/span[1]";

					// "//div[@id='DataGrid_" + rowNumber + "_Name']";
					WebElement elementToClick = findElement(sXpath);
					String sFileToCheck = elementToClick.getText();
					System.out.println("1album name-->*" + name + "*");
					System.out.println("2album name-->*" + sFileToCheck + "*");
					if (sFileToCheck.equalsIgnoreCase(name)) {
						Actions builder = new Actions(driver);
						System.out.println("inside if");
						sID = elemID.getIdOfElem("AlbumSelect", "webElemIDs");
						String imageXpath = "//*";
						if (sID.contains("/")) {
							imageXpath = sID;
						} else {
							imageXpath = "//*[@id='" + sID + "']";
						}
						imageXpath = imageXpath.replaceFirst("<rownumber>",
								rowNumber + "");
						WebElement hoverElement3 = driver.findElement(By
								.xpath(imageXpath));
						builder.moveToElement(hoverElement3);
						builder.click();
						builder.perform();
						System.out
								.println("Inside selectPhotoAlbum. Clicked on ->"
										+ imageXpath);
						break forLoops;
					}
					System.out.println("-------------------");
				}
			} else if (product.equalsIgnoreCase("ourpics")) {
				int i = 0;
				String sID = elemID.getIdOfElem("EventName", "webElemIDs");
				String xPathTable = sID.replaceAll("<rownumber>", i + "");
				while (!driver.findElement(By.xpath(xPathTable))
						.getAttribute("title").isEmpty()) {
					System.out.println(driver.findElement(By.xpath(xPathTable))
							.getAttribute("title"));

					if (driver.findElement(By.xpath(xPathTable))
							.getAttribute("title").substring(5)
							.equalsIgnoreCase(name)) {
						System.out.println("worked");
						driver.findElement(By.xpath(xPathTable)).click();
						break;
					}
					i++;
					xPathTable = sID.replaceAll("<rownumber>", i + "");
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println(name + " not found");
			// e.printStackTrace();
		} catch (Exception e) {
			System.out.println("3");
			e.printStackTrace();
		}
		return name;
	}

	public WebElement highlightElement(String locator) throws InterruptedException {
		  // find element by getting xpath of locator and return that element
		  WebElement wElem = driver.findElement(By.xpath(locator));
		  JavascriptExecutor js = (JavascriptExecutor)driver;
		     for (int iCnt = 0; iCnt < 3; iCnt++) {
		        //Execute javascrip
		        js.executeScript("arguments[0].style.border='5px groove red'", wElem);
		           Thread.sleep(1000);
		           js.executeScript("arguments[0].style.border=''", wElem);
		     }
		  return wElem;
		 }
} // end of DriverMethods
