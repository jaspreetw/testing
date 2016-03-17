package com.rjil.snw.mobileAutomation.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.rjil.snw.mobileAutomation.PropertyFileReader;

import io.appium.java_client.ios.IOSDriver;

public class MobileDriverSetup {
	public RemoteWebDriver initialiseDriver(String device, String udid, String portNo, String appPackage, String appActivity, String deviceName) {
		 RemoteWebDriver driver = null;
		 try {
			if (device.equalsIgnoreCase("IOS")) {
				PropertyFileReader properties = new PropertyFileReader();
				driver = DriverDetails.IOS.getDriverDetails(udid, portNo, appPackage, appActivity, null);
			} else if (device.equalsIgnoreCase("Android")) {
				driver = DriverDetails.Andriod.getDriverDetails(udid, portNo, appPackage, appActivity, deviceName);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}
}
