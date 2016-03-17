package com.rjil.snw.webAutomation.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.rjil.snw.webAutomation.utility.Log;

public class CampaignPage {

	private WebDriver driver;

	public CampaignPage(WebDriver driver) {
		driver.manage().window().maximize();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//*[@id='content']/section/div[2]/div/div[2]/input")
	private WebElement newCampaign;
	@FindBy(how = How.XPATH, using = "//*[@id='LayoutForm:create_campaign']")
	private WebElement Create_Campaign_Button;
	@FindBy(how = How.XPATH, using = "//*[@id='LayoutForm:messageesid']/div/ul/li[1]/span")
	private WebElement Campaign_Name_ErrorMsg;
	@FindBy(how = How.XPATH, using = "//*[@id='LayoutForm:messageesid']/div/ul/li[2]/span")
	private WebElement Campaign_StartDate_ErrorMsg;
	@FindBy(how = How.XPATH, using = "//*[@id='LayoutForm:messageesid']/div/ul/li[3]/span")
	private WebElement Campaign_EndDate_ErrorMsg;
	@FindBy(how = How.XPATH, using = "//*[@id='LayoutForm:campaignname']")
	private WebElement Campaign_Name_Textbox;
	@FindBy(how = How.XPATH, using = "//*[@id='LayoutForm:dpd1']")
	private WebElement Calendar_From_Date;
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div[3]/table/tbody/tr[1]/td[1]")
	private WebElement Campaign_Start_Date;
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div[3]/table/thead/tr[1]/th[3]/i")
	private WebElement Next_Campaign_Start_Date;
	@FindBy(how = How.XPATH, using = "//*[@id='LayoutForm:dpd2']")
	private WebElement Calendar_To_Date;
	@FindBy(how = How.XPATH, using = "/html/body/div[2]/div[3]/table/tbody/tr[3]/td[1]")
	private WebElement Campaign_End_Date;
	@FindBy(how = How.XPATH, using = "/html/body/div[2]/div[3]/table/thead/tr[1]/th[3]/i")
	private WebElement Next_Campaign_End_Date;
	@FindBy(how = How.XPATH, using = "//*[@id='LayoutForm:unlimitedcheck']/tbody/tr/td/label")
	private WebElement Unlimited_Checkbox;
	@FindBy(how = How.XPATH, using = "//*[@id='LayoutForm:unlimitedcheck']/tbody/tr/td/label")
	private WebElement No_Of_installs_ErrorMsg;
	@FindBy(how = How.XPATH, using = "//*[@id='LayoutForm:offers_datatable']")
	private WebElement campaignHistory;
	@FindBy(how = How.XPATH, using = "//*[@id='LayoutForm:cities']")
	private WebElement selectCity;
	@FindBy(how = How.XPATH, using = "//*[@id='btn_remove']")
	private WebElement btnRemove;

	/**
	 * @info This Method to verify the Create Campaign button is enable
	 * @return
	 */

	public boolean isCreateNewCampaignEnabled() {
		return (this.newCampaign.isDisplayed() && this.newCampaign.isEnabled());
	}

	public boolean isCampaignHistoryVisible() {
		return this.campaignHistory.isDisplayed();
	}

	public void changeDefaultValue() {
		this.newCampaign.click();
		selectCity.click();
		Select dropdown = new Select(selectCity);
		dropdown.selectByValue("1007");
		System.out.println("ahmedabad clicked");
		System.out.println(this.btnRemove.getText());
	}

	public boolean checkDefaultValues() {
		this.newCampaign.click();
		Select dropdown = new Select(selectCity);
		String str = dropdown.getFirstSelectedOption().getText();

		return str.equals("All India");
	}

	/**
	 * @info This Method to verify the Create Campaign button action
	 */
	public void clickOnCreateNewCampaign() {
		newCampaign.click();
	}

	/**
	 * @info This method will verify whether user on correct page
	 * @return Boolean
	 */
	public boolean checkCreateCampaignPage() {
		if (driver.findElement(By.xpath("//*[@id='content']/section/div[1]/div/span")).getText()
				.contains("Create Campaign")) {
			Log.info("User is on Create New Campagin page");
			return true;
		} else {
			Log.info("User is not on Campagin page");
			return false;
		}
	}

	/**
	 * @info This method is to click on create campaign button
	 */
	public void clickOnCreateCampaignButton() {
		Create_Campaign_Button.click();
	}

	/**
	 * @info This method is to verify error messages on camapaign page
	 * @return this method will return the error message
	 */
	public boolean checkErrorMessageForCampaginName() {
		if (Campaign_Name_ErrorMsg.getText().contains("Please enter Campaign name")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @info This method is to verify error messages on campaign page
	 * @return this method will return the error message
	 */

	public boolean checkErrorMessageForStartDate() {
		if (Campaign_StartDate_ErrorMsg.getText().contains("Please enter 'Start date'")) {
			return true;
		} else {
			return false;
		}

	}
	
	/**
	 * @info This method is to verify error messages on campaign page
	 * @return this method will return the error message
	 */
	public boolean checkErrorMessageForEndDate() {
		if (Campaign_EndDate_ErrorMsg.getText().contains("Please enter 'End date'")) {
			return true;
		} else {
			return false;
		}

	}
		
	public void enterCampaginName(String CampaignName) {
		Campaign_Name_Textbox.sendKeys(CampaignName);
	}

	/**
	 * @info This method is to select the campaign start date from calendar
	 */
	public void CampaginStartDate() {
		Calendar_From_Date.click();
		Next_Campaign_Start_Date.click();
		Campaign_Start_Date.click();

	}

	/**
	 * @info This method is to select the campaign end date from calendar
	 */
	public void CampaginEndDate() {
		Calendar_To_Date.click();
		Next_Campaign_End_Date.click();
		Campaign_End_Date.click();

	}

	/**
	 * @throws InterruptedException
	 * @info This method is to select/deselect the unlimited check box
	 */
	public void clickOnUnlimitedCheckBox() throws InterruptedException {
		Unlimited_Checkbox.click();

	}

	/**
	 * @info This method is to verify error messages on camapaign page
	 * @return this method will return the error message
	 */
	public boolean checkErrorMessageForNoOfInstalls() {
		if (No_Of_installs_ErrorMsg.getText().contains("Please enter number of installs")) {
			return true;
		} else {
			return false;
		}
	}
}

