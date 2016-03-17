package com.rjil.snw.mobileAutomation.pageobjects.ios;

import java.util.List;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class SendReceiveDataPage {
	private RemoteWebDriver driver;

	@iOSFindBy(name = "Mobile_Selection_Button_Android")
	private MobileElement andriod;
	
	@iOSFindBy(name = "Mobile_Selection_Button_iOS")
	private MobileElement iPhone;
	
	@iOSFindBy(name = "Mobile_Selection_Button_Other")
	private MobileElement otherPhone;
	
	//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]
	@iOSFindBy(name = "Mobile_Selection_Label_SendReceive")
	private MobileElement sendReceiveStaticText;
	
	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIACollectionView[1]")
	private List<MobileElement > receiverPhone;
	
	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")
	private MobileElement back;

	
	public SendReceiveDataPage(RemoteWebDriver remoteWebDriver) {
		driver = remoteWebDriver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void clickOnAndroid()
	{
		this.andriod.tap(1, 2);
	}
	
	public void clickOnBack()
	{
		this.back.tap(1, 3);
	}
	
	public void clickOniPhone()
	{
		this.iPhone.tap(1, 2);
	}
	
	public boolean isiPhoneButtonDisplayed()
	{
		return iPhone.isDisplayed();
	}
	
	public boolean isAndroidButtonDisplayed()
	{
		return andriod.isDisplayed();
	}
	
	public boolean isOtherPhoneButtonDisplayed()
	{
		return otherPhone.isDisplayed();
	}
	
	
	public int getNumberOfDeviceConnected()
	{
		
		for (MobileElement temp : receiverPhone) {
		
		System.out.println(temp.getAttribute("value"));
		}
		return receiverPhone.size();

	}
	
	
	public String getSendReceiveText()
	{
		return sendReceiveStaticText.getText();
	}
}
	////UIACollectionCell[UIAStaticText[contains(@name, Apple-iPhone)]] 

//	Use contains and pass few characters, so it will search for element with attribute name containing "Esp": 
	//UIAStaticText[contains(@name,'Esp')]


	
	//UIATableCell/UIAStaticText[contains(@name, '5'2" (157 cm)')]/..

