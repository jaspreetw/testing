package com.rjil.snw.mobileAutomation.tests.ios;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rjil.snw.mobileAutomation.pageobjects.ios.HomePage;
import com.rjil.snw.mobileAutomation.pageobjects.ios.SendReceiveDataPage;

public class PicturePairTest extends BaseTest {
	SendReceiveDataPage senderDataPage; 
	SendReceiveDataPage receiverDataPage; 
	HomePage homePageSender;
	HomePage homePageReceiver;


	@BeforeClass
	public void setup() throws Exception {
		super.setupSenderAndReceiver();
		homePageSender = new HomePage(senderDriver);
	    homePageReceiver = new HomePage(receiverDriver);
		senderDataPage = new SendReceiveDataPage(senderDriver);
		receiverDataPage = new SendReceiveDataPage(receiverDriver);
	}
	
	@Test
   public void testSendReceive()
	{
		homePageSender.clickOnSend();
		homePageReceiver.clickOnReceive();
		
		senderDataPage.clickOniPhone();
		receiverDataPage.clickOniPhone();
		
		System.out.println(senderDataPage.getNumberOfDeviceConnected());
	}

}
