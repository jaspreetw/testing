package com.rjil.cloud.selenium.startup;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.rjil.cloud.selenium.utility.SeleniumInventory;

public class WebAutomationBootstrap {

	protected static WebDriver driver;

	public static void appSetUp(String product, String browser, String url,String propertyFile)
			throws MalformedURLException {
		
		//To replace name of fitnesse property File
		  String fitnessePropFile=propertyFile.replace(".", "//");
		  System.out.println("Property File Name -->"+fitnessePropFile);
		  
		if (product.equalsIgnoreCase("jiodrive")) {
			try {
				System.out.println("in jio drive");
				FileUtils
						.copyFile(
								new File(
										"..//SeleniumPropertyFile//jiodrive.properties"),
								new File(
										"..//SeleniumPropertyFile//webElemIDs.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (product.equalsIgnoreCase("ourpics")) {
			try {
				System.out.println("in ourpics");
				FileUtils
						.copyFile(
								new File(
										"..//SeleniumPropertyFile//ourpics.properties"),
								new File(
										"..//SeleniumPropertyFile//webElemIDs.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (product.equalsIgnoreCase("switchNwalk")) {
			try {
				System.out.println("in switchnwalk");  
				FileUtils
				.copyFile(
						new File(SeleniumInventory.FitnesseRoot+fitnessePropFile+"//content.txt"),
						new File(
								SeleniumInventory.Web_Element_Id));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("no product specified..");
		}
		if (browser.equalsIgnoreCase("chrome")) {
			System.out.println("Launching chrome");
			System.setProperty("webdriver.chrome.driver",
					"..//SeleniumPropertyFile//chromedriver.exe");
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
			driver = new ChromeDriver(dc);
			// driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.out.println("Launching firefox");
			driver = new FirefoxDriver();
		} 
		else if (browser.equalsIgnoreCase("IE")) {
			   System.out.println("Launching Internet Explorer");
			   System.setProperty("webdriver.ie.driver",
			     "..//SeleniumPropertyFile//IEDriverServer.exe");
			   DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			   caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			   caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			   driver = new InternetExplorerDriver(caps);
			  }else if (browser.equalsIgnoreCase("grid")) {
			System.out.println("Launching selenium grid");
			driver = new RemoteWebDriver(new URL(
					"http://10.135.81.156:12444/wd/hub"),
					DesiredCapabilities.chrome());
		} else {
			System.out.println("No browser specified...");
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}
		if (!url.isEmpty()) {
			System.out.println("opening url-->" + url);
			driver.get(url);
		} else
			System.out.println("url is empty");
		driver.manage().window().maximize();
	} // end appSetUp()
	public static void teardownApp() {
		// close the app
		driver.quit();
	} // end teardownApp()


}
