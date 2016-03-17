package com.rjil.cloud.selenium.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

	Properties webElemID = new Properties();
	Properties mobileAppIDs = new Properties();
	Properties classesOfDropDown = new Properties();
	Properties idsOfInputFields = new Properties();
	Properties config = new Properties();

	public String getKeyValues(String key, String fileName) {

		try {
			InputStream input = null;
			String value = "testing";
			switch (fileName) {
			case "webElemIDs":
				System.out.println("Current Path-->"+System.getProperty("user.dir"));
				System.out.println("*********"+SeleniumInventory.Web_Element_Id);
				input = //new FileInputStream("//usrdata//apps//SeleniumGrid//SeleniumPropertyFile//webElemIDs.properties");
						new FileInputStream(SeleniumInventory.Web_Element_Id);
										//"C://Eclipse Projects//JioDriveAppAndWebTest//properties//webElemIDs.properties");
						//"C://mydata/automation/Workspace/JioDriveAppAndWebTest/properties/webElemIDs.properties");
				webElemID.load(input);
				value = webElemID.getProperty(key);
				break;
			case "mobileAppIDs":
				input = //new FileInputStream("//usrdata//apps//SeleniumGrid//SeleniumPropertyFile//mobileAppIDs.properties");
						new FileInputStream("C://Eclipse Projects//JioDriveAppAndWebTest//properties//mobileAppIDs.properties");
						//"C://mydata/automation/Workspace/JioDriveAppAndWebTest/properties/mobileAppIDs.properties");
				mobileAppIDs.load(input);
				value = mobileAppIDs.getProperty(key);
//				break;
//			case "config":
//				input = new FileInputStream(
//						"C://Eclipse Projects//JioDriveAppAndWebTest//properties//config.properties");
//						//"C://mydata/automation/Workspace/JioDriveAppAndWebTest/properties/config.properties");
//				config.load(input);
//				value = config.getProperty(key);
//				break;
			default:
				value = "Noelement";
				break;
			}
			return value;
		} catch (IOException e) {
			e.printStackTrace();
			return "No ID captured";
		}
	}//end of getKeyValues
}
