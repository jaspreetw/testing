package com.rjil.cloud.selenium.fitnesse;

import com.rjil.cloud.selenium.utility.PropertiesReader;


public class ElementIDsReader {

	static PropertiesReader propertiesReader = new PropertiesReader();

	public String getIdOfElem(String sElementText, String sPropertyFile) {
		return propertiesReader.getKeyValues(sElementText, sPropertyFile);
	} // end of getIdOfElem

} // end of class elementIDs
