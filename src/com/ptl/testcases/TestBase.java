package com.ptl.testcases;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import com.ptl.DOC.pages.CitizenTopMenu;
import com.ptl.DOC.pages.TopMenu;
import com.ptl.util.Constants;
import com.ptl.util.ReadXLS;

public class TestBase {

	public static Logger APPLICATION_LOGS = null;
	public static Properties CONFIG = null;
	public static WebDriver driver = null;
	public static TopMenu topMenu = null;
	public static CitizenTopMenu citizenTopMenu = null;

	public static boolean isLoggedIn = false;
	public static boolean isTestExporterRegistered = false;

	public static boolean isLoggedIn_CitizenApp = false;

	public ReadXLS xls = new ReadXLS(System.getProperty("user.dir")
			+ "\\src\\com\\ptl\\data\\TestData Excel.xlsx");

	public void initConfiguration() {

		System.setProperty("org.apache.commons.logging.Log",
				"org.apache.commons.logging.impl.Jdk14Logger");

		if (CONFIG == null) {
			// initialize loggin & properties files
			APPLICATION_LOGS = Logger.getLogger("devpinoyLogger");

			// initialize Properties file
			CONFIG = new Properties();
			FileInputStream fs;
			try {
				fs = new FileInputStream(Constants.CONFIG_FILE_PATH);
				CONFIG.load(fs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void initDriver() {
		if (driver == null) {
			if (CONFIG.getProperty("browser").equalsIgnoreCase("Firefox")) {
				/*System.setProperty("webdriver.firefox.bin",
						"C:\\Program Files (x86)\\Mozilla Firefox17\\firefox.exe");*/
				ProfilesIni allProfiles = new ProfilesIni();
				FirefoxProfile profile = allProfiles.getProfile("Selenium");

				profile.setAcceptUntrustedCertificates(true);
				/*FirefoxBinary binary = new FirefoxBinary(new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe"));
				driver = new FirefoxDriver(binary, profile);*/
				driver = new FirefoxDriver(profile);
				
				
			}else if (CONFIG.getProperty("browser").equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//drivers//chromedriver.exe");
				DesiredCapabilities capability = DesiredCapabilities.chrome();
				capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				
				//ChromeOptions options = new ChromeOptions();				
				//options.setBinary(new File("C://Program Files (x86)//Google//Chrome//Application//chrome.exe"));
				driver = new ChromeDriver(capability);
				//driver = new ChromeDriver();
			} else if (CONFIG.getProperty("browser").equalsIgnoreCase("IE")) {
				/*File file = new File("C:\\IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());*/
				
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"//drivers//IEDriverServer.exe");
				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				driver = new InternetExplorerDriver(caps);
				
				//System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"//drivers//IEDriverServer.exe");
				//System.setProperty("webdriver.ie.driver", "E:\\Selenium\\workspace\\IEDriverServer.exe");
				//driver = new InternetExplorerDriver();
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
		}
	}

	public void quitDriver() {

		driver.quit();
		driver = null;

	}

	public TopMenu getTopMenu() {
		if (topMenu == null) {
			topMenu = PageFactory.initElements(driver, TopMenu.class);
		}
		return topMenu;
	}

}
