package com.rjil.snw.mobileAutomation.test;

import java.io.IOException;
import org.apache.log4j.Logger;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class LoggingClass extends TestListenerAdapter { 
	 private static Logger Log = Logger.getLogger(LoggingClass.class.getName());

	@Override
	public void onTestFailure(ITestResult tr) {
		log(tr.getName() + "--Test method failed\n");
		log(tr.getThrowable().getMessage());
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		log(tr.getName() + "--Test method skipped\n");
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		log(tr.getName() + "--Test method success\n");
	}

	public static void log(String string) {
		Log.info(string);
	}

	public static void errorLog(Exception e) {
		Log.error(e);

	}
}
