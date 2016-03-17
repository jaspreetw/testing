package com.rjil.snw.mobileAutomation.tests.ios;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.rjil.snw.mobileAutomation.IOSCommands;
import com.rjil.snw.mobileAutomation.pageobjects.ios.HomePage;
import com.rjil.snw.mobileAutomation.pageobjects.ios.SendReceiveDataPage;

public class HomePageTest extends BaseTest {

	HomePage homePage = null;

	@BeforeClass
	public void setup() throws Exception {
		super.setUp();
		homePage = new HomePage(senderDriver);
	}
	
	@Test
	public void testDeviceName() throws Exception {
		String deviceName = homePage.getDeviceName();
		String actualDeviceName = IOSCommands.getDeviceName(senderudid);
		Assert.assertNotNull(deviceName);
		Assert.assertTrue(deviceName.contains(actualDeviceName));
	}
	
	@Test
	public void testTermsAndCondition() throws Exception {
		Assert.assertTrue(homePage.isTermsAndConditionsDisplayed());
	}
	
	@Test
	@Parameters({ "sendScreenText"}) 
	public void testSendButton(String sendScreenText) throws Exception {
		SendReceiveDataPage sendReceiveDataPage = homePage.clickOnSend();
		Assert.assertEquals(sendReceiveDataPage.getSendReceiveText(), sendScreenText);
		Assert.assertTrue(sendReceiveDataPage.isiPhoneButtonDisplayed());
		Assert.assertTrue(sendReceiveDataPage.isAndroidButtonDisplayed());
		Assert.assertTrue(sendReceiveDataPage.isOtherPhoneButtonDisplayed());
		sendReceiveDataPage.clickOnBack();
	}
	
	@Test
	@Parameters({ "receiveScreenText"}) 
	public void testReceiveButton(String receiveScreenText) throws Exception {
		SendReceiveDataPage sendReceiveDataPage = homePage.clickOnReceive();
		Assert.assertEquals(sendReceiveDataPage.getSendReceiveText(), receiveScreenText);
		Assert.assertTrue(sendReceiveDataPage.isiPhoneButtonDisplayed());
		Assert.assertTrue(sendReceiveDataPage.isAndroidButtonDisplayed());
		Assert.assertTrue(sendReceiveDataPage.isOtherPhoneButtonDisplayed());
		sendReceiveDataPage.clickOnBack();
	}
	
}
