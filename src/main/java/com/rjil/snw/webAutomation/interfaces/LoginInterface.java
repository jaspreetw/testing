package com.rjil.snw.webAutomation.interfaces;

import com.rjil.snw.webAutomation.pageObjects.HomePage;



/**
 * @author Anand.Bajpai
 * 
 * This class is interface class for Login Page.
 * Please check respective class for more info.
 *
 */
public interface LoginInterface {
		public void clickLoginButton();
		public void enterEmail(String username);
		public void enterPassword(String password);
		public HomePage Performlogin();
}
