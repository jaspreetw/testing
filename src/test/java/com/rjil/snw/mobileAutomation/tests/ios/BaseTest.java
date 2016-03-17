package com.rjil.snw.mobileAutomation.tests.ios;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;

import com.rjil.snw.mobileAutomation.IOSCommands;
import com.rjil.snw.mobileAutomation.PropertyFileReader;
import com.rjil.snw.mobileAutomation.test.MobileDriverSetup;


public class BaseTest {
	
	 RemoteWebDriver senderDriver = null;
	 RemoteWebDriver receiverDriver = null;
	
	 PropertyFileReader properties = null;
	 
	 MobileDriverSetup mobileSetUp =new MobileDriverSetup();
	 String senderudid = null;
	 String receiverudid = null;
	 String sNwBundleId = null;
	 String senderportno = null;
	 String receiverportno = null;
	 String appFileDetails = null;
	 	
	 public BaseTest()
	 {
		properties = new PropertyFileReader();
		sNwBundleId = properties.getKeyValues("snwiospackage");
		senderudid = properties.getKeyValues("senderiosudid");
		receiverudid = properties.getKeyValues("receiveriosudid");
		senderportno  = properties.getKeyValues("SenderPortNo");
		receiverportno = properties.getKeyValues("ReceiverPortNo");
		appFileDetails = properties.getKeyValues("sNwAppName");
	 }
	 
	 
	 public void installUninstallSenderReceiverApp() throws Exception
	 {
			IOSCommands.uninstallSnwApp(sNwBundleId, senderudid);
			IOSCommands.installSnWApp(appFileDetails, senderudid);
			
			IOSCommands.uninstallSnwApp(sNwBundleId, receiverudid);
			IOSCommands.installSnWApp(appFileDetails, receiverudid);
	 }
	
	public void setupSenderAndReceiver() throws Exception 	{
		//installUninstallSenderReceiverApp();
		senderDriver = mobileSetUp.initialiseDriver("ios",senderudid, senderportno, sNwBundleId, null, null);		
		receiverDriver = mobileSetUp.initialiseDriver("ios", receiverudid, receiverportno, sNwBundleId, null, null);
		senderDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		receiverDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	
	public void setUp() throws Exception 
	{
		try {
			IOSCommands.uninstallSnwApp(sNwBundleId, senderudid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		IOSCommands.installSnWApp(appFileDetails, senderudid);
		
		senderDriver = mobileSetUp.initialiseDriver("ios", senderudid, senderportno, sNwBundleId, null, null);
		senderDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public void tearDown()
	{
		if (senderDriver!=null)
			senderDriver.quit();
		if (receiverDriver!=null)
			receiverDriver.quit();
	}
}
