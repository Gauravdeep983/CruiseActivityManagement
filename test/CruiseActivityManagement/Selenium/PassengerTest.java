package CruiseActivityManagement.Selenium;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import CruiseActivityMangement.CAM_BusinessFunctions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PassengerTest extends CAM_BusinessFunctions {
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	public static String sAppURL, sSharedUIMapPath;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Logger.getLogger("org.openqa.selenium.remote").setLevel(Level.OFF);
	}

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "c:/ChromeDriver/chromedriver.exe");
		driver = new ChromeDriver();

		prop = new Properties();
		// Load Configuration file
		prop.load(new FileInputStream("./Configuration/CAM_Configuration.properties"));
		sAppURL = prop.getProperty("sAppURL");
		sSharedUIMapPath = prop.getProperty("SharedUIMap");

		prop.load(new FileInputStream(sSharedUIMapPath));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	@FileParameters("excel/P_available_event_summary.csv")
	public void testRequestAvailableEvent(int testCaseNumber, String username, String password, String date,
			String time, String expCapacity, String expEventDate, String expEventTime, String expType,
			String expDuration) throws Exception {
		// Landing page
		driver.get(sAppURL);

		// Login as passenger
		CAM_BF_Login(driver, username, password);

		// Click on "View Available Events" button
		Thread.sleep(1_000);
		driver.findElement(By.id(prop.getProperty("Btn_Homepage_AvailableEvents"))).click();

		// Search for events
		search(driver, date, time);
		Thread.sleep(1_000);
		driver.findElement(By.id(prop.getProperty("Btn_AvailableEvents_Search"))).click();

		// Click on first result
		Thread.sleep(1_000);		
		driver.findElement(By.xpath(prop.getProperty("Lnk_AvailableEvents_DetailsOfFirstResult"))).click();
		try {
			assertEquals(expCapacity,
					driver.findElement(By.xpath(prop.getProperty("Lbl_EventDetails_Capacity"))).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals(expEventDate,
					driver.findElement(By.xpath(prop.getProperty("Lbl_EventDetails_Date"))).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals(expEventTime,
					driver.findElement(By.xpath(prop.getProperty("Lbl_EventDetails_Time"))).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals(expType, driver.findElement(By.xpath(prop.getProperty("Lbl_EventDetails_Type"))).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals(expDuration,
					driver.findElement(By.xpath(prop.getProperty("Lbl_EventDetails_Duration"))).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		// Screenshot
		takeScreenShot(driver, new Throwable().getStackTrace()[0].getMethodName() + testCaseNumber);

		// Back to homepage
		driver.findElement(By.id(prop.getProperty("Btn_EventDetails_Back"))).click();

		// Logout
		driver.findElement(By.id(prop.getProperty("Btn_Homepage_Logout"))).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
