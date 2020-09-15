package appiumSupport;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class AppiumBaseClass {
	
	public static final String rootDir = System.getProperty("user.dir");

	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;

	public AppiumDriverLocalService startServer() {
		boolean isServerRunning = checkIfServerIsRunning(4723);
		if (!isServerRunning) {
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;
	}

	private static boolean checkIfServerIsRunning(int port) {
		boolean isServerRunning = false;
		ServerSocket serverSocket;

		try {
			// close the server at given port
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			// if execution enters here, it means server is already running at the given port
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}

		return isServerRunning;
	}
	@BeforeSuite
	public void setup() throws IOException {
		if(driver != null){
			driver.closeApp();
			driver.quit();
		}

		//Properties props = getGlobalProps();

		//String appName = (String) props.get("KinzooApp");
		String appName = "app-debug.apk";
				File appDir = new File(rootDir + "/appFiles");
		File app = new File(appDir, appName);

	//	String deviceName = (String) props.get("deviceName");
		String deviceName ="HUAWEI Y7 Prime 2018";
	//	String deviceUDID = System.getProperty("deviceUDID");
		String deviceUDID ="26EBB18413201814";// provided while executing mvn test command
		// If deviceUDID not provided, get it from global.properties file
		if (deviceUDID == null) {
			//deviceUDID = props.getProperty("deviceUDID");
			deviceUDID ="26EBB18413201814";
		}

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		cap.setCapability(MobileCapabilityType.UDID, deviceUDID);
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 35);
		cap.setCapability("appPackage", "de.zeigewas.octosmartcontrol");
		//cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

		driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
	}
	
//	public static Properties getGlobalProps() throws IOException {
//		FileInputStream globalProps = new FileInputStream(
//				rootDir + "/src/main/java/utilities/global.properties");
//		Properties props = new Properties();
//		props.load(globalProps);
//
//		return props;
//	}
	
	public static String getScreenshot(String testName) throws IOException{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String imgPath = rootDir + "/reports/screenshots/" + testName + ".png";
		FileUtils.copyFile(scrFile, new File(imgPath));
		return imgPath;
	}
	@AfterSuite
	public void TearDown() {
//		if(driver!=null){
//			driver.quit();
	}
}
