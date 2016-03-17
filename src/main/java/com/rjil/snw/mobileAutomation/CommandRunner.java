package com.rjil.snw.mobileAutomation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.exec.OS;

public class CommandRunner {
	static PropertyFileReader properties = new PropertyFileReader();

	@SuppressWarnings("finally")
	public static BufferedReader executeAdbCommand(String cmd) throws IOException {
		BufferedReader buf = null;
		if (OS.isFamilyMac()) {
			cmd = properties.getKeyValues("MacAndroidExecutionPath").concat(cmd);
		}
		buf = executeCommand(cmd);
		return buf;
	}

	public static String executeIOSCommand(String cmd) throws Exception {
		if (OS.isFamilyMac()) {
			cmd = properties.getKeyValues("MacIOSExecutionPath").concat(cmd);
		}
		return executeCmd(cmd);
	}
	
	public static BufferedReader executeCommand(String cmd) throws IOException {
		BufferedReader buf = null;
		try {
			System.out.println("Executing command :" + cmd);
			Runtime run = Runtime.getRuntime();
			Process pr = null;
			if (OS.isFamilyMac()) {
				String[] commands = { "/bin/sh", "-c", cmd };
				pr = run.exec(commands);
			} else if (OS.isFamilyWindows()) {
				pr = run.exec(cmd);
			}
			pr.waitFor();
			String errorMessage = convertStreamToString(pr.getErrorStream());
			if (!((pr.exitValue() == 0) && errorMessage.equals(""))) {
				throw new Exception("Process Failed with Exit Code :" +pr.exitValue()+" "+errorMessage);
			}
			buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			return buf;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static String executeCmd(String cmd) throws Exception {
		CommandRunner commandRunner = new CommandRunner();
		BufferedReader buf = null;
		Runtime run = Runtime.getRuntime();
		Process pr = null;
		String commandOutPut= null;
		if (OS.isFamilyMac()) {
			String[] commands = { "/bin/sh", "-c", cmd };
			pr = run.exec(commands);
			pr.waitFor();
			String errorMessage = convertStreamToString(pr.getErrorStream());

            if ((pr.exitValue() == 0) && errorMessage.equals("")) {
				commandOutPut = convertStreamToString(pr.getInputStream());
			}
			else
			{
				throw new Exception("Process Failed with Exit Code :" +pr.exitValue()+" "+errorMessage);
			}
		} else if (OS.isFamilyWindows()) {
			pr = run.exec(cmd);
		}

		return commandOutPut;
	}
	
	
	static String convertStreamToString(java.io.InputStream inputStream) throws IOException
	{
		BufferedReader is;  // reader for output of process
		String line;
		StringBuffer strBuff = new StringBuffer();
		// getInputStream gives an Input stream connected to
		// the process standard output. Just use it to make
		// a BufferedReader to readLine() what the program writes out.
		is = new BufferedReader(new InputStreamReader(inputStream));

		while ((line = is.readLine()) != null)
		   strBuff.append(line);
		return strBuff.toString();
	}
}
