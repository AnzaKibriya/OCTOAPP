import Pages.Pages_Handle;
import appiumSupport.AppiumBaseClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.annotations.Test;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;



public class TestSuite extends AppiumBaseClass{

    @Test(priority = 1, enabled = true)
    public void Test_LaunchApplication() throws Exception {
      //  AndroidDriver<AndroidElement> driver = capabilities();

        Pages_Handle.HandlePermissons();
        System.out.println("App Launch");
        System.out.println("Success");
        System.out.println("Success");
    }
}
