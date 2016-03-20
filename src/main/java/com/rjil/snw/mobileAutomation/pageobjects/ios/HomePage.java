package com.rjil.snw.mobileAutomation.pageobjects.ios;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class HomePage {
	
	private RemoteWebDriver driver;
	SendReceiveDataPage sendReceiveDataPage;


	
	@iOSFindBy(name = "Start_Button_Send")
	private MobileElement send;
	
	@iOSFindBy(name = "Start_Button_Receive")
	private MobileElement receive;
	
	@iOSFindBy(name = "Start_Label_DeviceName")
	private MobileElement deviceName;

	@iOSFindBy(name="Start_Button_TermsBtn2")
	private MobileElement termsAndCondition;
	
	@iOSFindBy(name="Start_Label_Version")
    private MobileElement versionNumber;
	
	@iOSFindBy(name="Start_Label_AppInstallation")
	private MobileElement appInstallationDetails;
	
	@iOSFindBy(name="Back")
	private MobileElement back;
	
	@iOSFindBy(xpath="//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]")
    private MobileElement termsAndConditionsPage;
	
	public HomePage(RemoteWebDriver remoteWebDriver) {
		driver = remoteWebDriver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		sendReceiveDataPage = new SendReceiveDataPage(driver);
	}

	public boolean isHomePageVisible()
	{
		return termsAndCondition.isDisplayed();
	}
	
	public String getAppInstallationInfo()
	{
		return appInstallationDetails.getText();
	}
	
	public String getVersionNumber()
	{
		return versionNumber.getText();
	}
	
	public SendReceiveDataPage clickOnSend() {
		this.send.tap(1, 3);
		return sendReceiveDataPage;

	}
	
	public String getDeviceName()
	{
		return deviceName.getText();
	}
	
	public void clickOnBack()
	{
		back.tap(1, 3);
	}
	
	public boolean isTermsAndConditionsDisplayed()
	{
		this.termsAndCondition.tap(1, 3);
		return termsAndConditionsPage.isDisplayed();
	}
	
	public 	SendReceiveDataPage clickOnReceive()
	{
		this.receive.tap(1, 2);
		return sendReceiveDataPage;
	}
	
	
	
}
