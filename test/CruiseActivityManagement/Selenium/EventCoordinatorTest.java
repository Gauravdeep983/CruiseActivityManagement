package CruiseActivityManagement.Selenium;

import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.*;
import org.junit.runner.RunWith;

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
public class EventCoordinatorTest extends CAM_BusinessFunctions {
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
//		Load Configuration file
		prop.load(new FileInputStream("./Configuration/CAM_Configuration.properties"));
		sAppURL = prop.getProperty("sAppURL");
		sSharedUIMapPath = prop.getProperty("SharedUIMap");

		prop.load(new FileInputStream(sSharedUIMapPath));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	@FileParameters("excel/EC_login_test.csv")
	public void testHomepageRole(int testCaseNumber, String username, String password, String roleExpected, String date,
			String time, String eventAssignedExpected, String errorMsg, String roleActual, String eventAssignedActual)
			throws Exception {
		// Landing page
		driver.get(sAppURL);

		// Login as EC
		CAM_BF_Login(driver, username, password);

		// Verify if redirected to Event Coordinator homepage
		try {
			assertEquals(roleExpected, driver.findElement(By.xpath(prop.getProperty("Lbl_ECHome_Role"))).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		// Screenshot
		takeScreenShot(driver, new Throwable().getStackTrace()[0].getMethodName());

		// Logout
		driver.findElement(By.id(prop.getProperty("Btn_Homepage_Logout"))).click();
	}

	@Test
	@FileParameters("excel/EC_no_events_assigned.csv")
	public void testNoEventsAssigned(int testCaseNumber, String username, String password, String roleExpected,
			String date, String time, String eventAssignedExpected, String errorMsg, String roleActual,
			String eventAssignedActual) throws Exception {
		// Landing page
		driver.get(sAppURL);

		// Login as EC
		CAM_BF_Login(driver, username, password);

		// Search using date, time
		searchAssignedEvents(driver, date, time);

		// Verify error message
		try {
			assertEquals(errorMsg,
					driver.findElement(By.id(prop.getProperty("Lbl_EventSummaryEC_ErrorMsg"))).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		// Screenshot
		takeScreenShot(driver, new Throwable().getStackTrace()[0].getMethodName());
		// Back button
		driver.findElement(By.id(prop.getProperty("Btn_EventSummaryEC_Back"))).click();
		// Logout
		driver.findElement(By.id(prop.getProperty("Btn_Homepage_Logout"))).click();

	}

	@Test
	@FileParameters("excel/EC_verify_assigned_event.csv")
	public void testVerifyAssignedEvent(int testCaseNumber, String username, String password, String roleExpected,
			String date, String time, String eventAssignedExpected, String errorMsg, String roleActual,
			String eventAssignedActual) throws Exception {
		// Landing page
		driver.get(sAppURL);

		// Login as EC
		CAM_BF_Login(driver, username, password);

		// Search using date, time
		searchAssignedEvents(driver, date, time);

		// Verify if assigned event can be viewed
		driver.findElement(By.xpath(prop.getProperty("Btn_EventSummaryEC_Details"))).click();
		try {
			assertEquals(eventAssignedExpected,
					driver.findElement(By.xpath(prop.getProperty("Lbl_EventDetails_EventName"))).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		// Screenshot
		takeScreenShot(driver, new Throwable().getStackTrace()[0].getMethodName());
		driver.findElement(By.id(prop.getProperty("Btn_EventDetails_Homepage"))).click();

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

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
