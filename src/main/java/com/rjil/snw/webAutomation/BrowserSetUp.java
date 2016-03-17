package com.rjil.snw.webAutomation;

import java.io.File;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserSetUp {
	public static WebDriver driver;

	// Application setup methods..
	/**
	 * @param product this contains name of the product
	 * @param browser this is type of the browser
	 * @return WebDriver
	 * @throws MalformedURLException
	 */
	public static WebDriver appSetUp(String product, String browser, String FireFoxPath) throws MalformedURLException {
		
		/*Work on logger is still under process*/
		
		
		if (product.equalsIgnoreCase("SnW")) {
			System.out.println("SnW suite Initialized....");
		} else {
			System.out.println("no product specified..");
		}

		if (browser.equalsIgnoreCase("firefox")) {
			System.out.println("Preferred firefox....");
			System.out.println("Launching firefox......");
		/*	File pathToBinary = new File(FireFoxPath);
			FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setPreference("browser.download.dir", "C:\\");
			firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
			firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/vnd.android.package-archive" );
			firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
			firefoxProfile.setPreference("browser.helperApps.neverAsk.openFile", "application/vnd.android.package-archive");
			firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
			firefoxProfile.setPreference("browser.download.manager.alertOnEXEOpen", false);
			firefoxProfile.setPreference("browser.download.manager.focusWhenStarting", false);
			firefoxProfile.setPreference("browser.download.manager.useWindow", false);
			firefoxProfile.setPreference("browser.download.manager.showAlertOnComplete", false);
			firefoxProfile.setPreference("browser.download.manager.closeWhenDone", false);
			org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy(); 
			proxy.setSslProxy("jproxy.ril.com"+":"+8080);
			DesiredCapabilities dc = DesiredCapabilities.firefox();
			dc.setCapability(CapabilityType.PROXY, proxy); 
			driver = new FirefoxDriver(ffBinary, firefoxProfile);*/
			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("safari")) {
			System.out.println("Preferred Safari....");
			System.out.println("Launching Safari......");
			driver = new SafariDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			System.out.println("Preferred Windows Edge....");
			System.out.println("Launching Windows Edge......");
			driver = new EdgeDriver();
		} else {
			System.out.println("Browser malfunction....");
		}

		return driver;
	}
}