package com.rjil.snw.mobileAutomation;

public class IOSCommands {
	
	// ideviceinstaller -U com.jiocloud.switchNwalk
	static PropertyFileReader properties = new PropertyFileReader();
	static String unintsallCommand = "ideviceinstaller -u ";
	static String installCommand = "ideviceinstaller -u ";
	static final String devicenameCommand = "idevicename -u ";

	public static void uninstallSnwApp(String bundleId, String udid) throws Exception {
		String str = null;
		str = CommandRunner.executeIOSCommand(unintsallCommand + udid + "  -U" + bundleId);
		System.out.println(str);
		System.out.println("Done");
	}

	public static void installSnWApp(String bundleId, String udid) throws Exception {
		

		String str = null;
		try
		{
			str = CommandRunner.executeIOSCommand(installCommand + udid + "  -i" + bundleId);
		}catch (Exception e) {
			if (e.getMessage().contains("WARNING"))
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			else
			{
				throw new Exception(e.getMessage());
			}
		}
		System.out.println(str);
		System.out.println("Done");

	}

	public static String getDeviceName(String udid) throws Exception {
		return (CommandRunner.executeIOSCommand(devicenameCommand + udid));
	}

}
