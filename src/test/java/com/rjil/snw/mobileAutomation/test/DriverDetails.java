package com.rjil.snw.mobileAutomation.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public enum DriverDetails {
	Andriod {
		@Override
		RemoteWebDriver getDriverDetails(String udid, String portNo, String appPackage, String appActivity, String deviceName) throws MalformedURLException {
			DesiredCapabilities caps = new DesiredCapabilities();
			RemoteWebDriver driver;
			caps.setCapability("platformName", "android");
			caps.setCapability("deviceName", "Micromax");
			caps.setCapability("appPackage", appPackage);
			caps.setCapability("appActivity", appActivity);
			caps.setCapability("udid", udid);
			driver = new AndroidDriver(new URL("http://127.0.0.1:"+portNo+"/wd/hub"), caps);
			return driver;
		}
	},
	IOS {
		@Override
		RemoteWebDriver getDriverDetails(String udid, String portNo, String appPackage, String appActivity, String deviceName) throws MalformedURLException {
			DesiredCapabilities caps = new DesiredCapabilities();
			RemoteWebDriver driver;
			caps.setCapability("platformName", "iOS");
			caps.setCapability("platformVersion", "9.2.1");
			caps.setCapability("deviceName", deviceName);
			caps.setCapability("app", appPackage);
			caps.setCapability("udid", udid);
			caps.setCapability("autoAcceptAlerts", true);
			caps.setCapability("waitForAppScript", true);
			caps.setCapability("newCommandTimeout", 100);
            //caps.setCapability("launchTimeout", 2000);
			//caps.setCapability("bundleId","com.reliance.jio.switchNwalk");
			driver = new IOSDriver(new URL("http://0.0.0.0:"+portNo+"/wd/hub"), caps);
			return driver;
		}
	},;
	
	abstract RemoteWebDriver getDriverDetails(String udid, String portNo, String appPackage, String appActivity, String deviceName) throws MalformedURLException;
}
