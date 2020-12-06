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
	public void testAvailableEventRequest(int testCaseNumber, String username, String password, String date,
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

	@Test
	@FileParameters("excel/p_event_reserve.csv")
	public void testPassengerEventReserve(int testCaseNo, String username, String password, String date, String time,
			String eventType, String eventXpath, String capacityErrorExp, String typeErrorExp, String successmsgExp)
			throws Exception {
		// Landing page
		driver.get(sAppURL);

		// Login as passenger
		CAM_BF_Login(driver, username, password);

		// Click on available events
		driver.findElement(By.id(prop.getProperty("Btn_Homepage_AvailableEvents"))).click();

		// Search using datetime
		search(driver, date, time);
		new Select(driver.findElement(By.id(prop.getProperty("Lst_AvailableEvents_EventType"))))
				.selectByVisibleText(eventType);
		Thread.sleep(1_000);
		driver.findElement(By.id(prop.getProperty("Btn_AvailableEvents_Search"))).click();

		// Click on view event
		driver.findElement(By.xpath(eventXpath)).click();

		// Click reserve
		Thread.sleep(1_000);
		driver.findElement(By.linkText("Reserve")).click();

		if (testCaseNo != 4) {
			// Verify is error is shown
			try {
				assertEquals(capacityErrorExp,
						driver.findElement(By.id(prop.getProperty("Lbl_ReserveEvent_CapacityError"))).getText());
			} catch (Error e) {
				verificationErrors.append(e.toString());
			}
			try {
				assertEquals(typeErrorExp,
						driver.findElement(By.id(prop.getProperty("Lbl_ReserveEvent_TypeError"))).getText());
			} catch (Error e) {
				verificationErrors.append(e.toString());
			}
			// Screenshot
			takeScreenShot(driver, new Throwable().getStackTrace()[0].getMethodName() + testCaseNo);
			// Back to homepage
			Thread.sleep(1_000);
			driver.findElement(By.id(prop.getProperty("Btn_EventDetails_Back"))).click();
		}

		try {
			assertEquals(successmsgExp, driver.findElement(By.id(prop.getProperty("Lbl_Homepage_Success"))).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		// Screenshot
		takeScreenShot(driver, new Throwable().getStackTrace()[0].getMethodName());
		// Logout
		Thread.sleep(1_000);
		driver.findElement(By.id(prop.getProperty("Btn_Homepage_Logout"))).click();
	}

	@Test
	@FileParameters("excel/UpdateProfileTest.csv")
	public void testUpdateValidations(String testCaseNo, String login_username, String login_password, String username,
			String password, String firstName, String lastName, String phone, String email, String roomNumber,
			String deckNumber, String errorMsg, String usernameError, String passwordError, String firstNameError,
			String lastNameError, String phoneError, String emailError, String roomNumberError, String deckNumberError)
			throws Exception {
		// Landing page
		driver.get(sAppURL);
		CAM_BF_Login(driver, login_username, login_password);
		Thread.sleep(1_000);
		driver.findElement(By.id(prop.getProperty("Btn_Home_ViewProfile"))).click();
		Thread.sleep(1_000);
		driver.findElement(By.id(prop.getProperty("Btn_ViewProfile_Modify"))).click();

		// Update Profile
		CAM_BF_UpdateProfile(driver, username, password, firstName, lastName, phone, email, roomNumber, deckNumber);

		Thread.sleep(1_000);
		Alert javascriptAlert = driver.switchTo().alert();
		javascriptAlert.accept();
		// Screenshot
		takeScreenShot(driver, new Throwable().getStackTrace()[0].getMethodName());
		// Back to homepage
		Thread.sleep(1_000);
		driver.findElement(By.id(prop.getProperty("Btn_Update_Back"))).click();
		// driver.findElement(By.id("back_ward")).click();
		Thread.sleep(1_000);
		driver.findElement(By.id(prop.getProperty("Btn_Homepage_Logout"))).click();
	}

	@Test
	@FileParameters("excel/HappyUpdateProfile.csv")
	public void testUpdateValidationsHappy(String testCaseNo, String login_username, String login_password,
			String username, String password, String firstName, String lastName, String phone, String email,
			String roomNumber, String deckNumber, String successMsgExp) throws Exception {
		// Landing page
		driver.get(sAppURL);
		CAM_BF_Login(driver, login_username, login_password);
		driver.findElement(By.id(prop.getProperty("Btn_Home_ViewProfile"))).click();
		driver.findElement(By.id(prop.getProperty("Btn_ViewProfile_Modify"))).click();

		// Update Profile
		CAM_BF_UpdateProfile(driver, username, password, firstName, lastName, phone, email, roomNumber, deckNumber);

		Alert javascriptAlert = driver.switchTo().alert();
		javascriptAlert.accept();

		// Screenshot
		takeScreenShot(driver, new Throwable().getStackTrace()[0].getMethodName());
		try {
			assertEquals(successMsgExp, driver.findElement(By.id(prop.getProperty("Lbl_Login_Success"))).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.id(prop.getProperty("Btn_Homepage_Logout"))).click();
	}

	@Test
	@FileParameters("excel/p_verify_reserved_events.csv")
	public void testVerifyReservedEvent(int testCaseNumber, String username, String password, String roleExpected,
			String date, String time, String eventReservedExpected, String errorMsg, String role,
			String eventReservedExp) throws Exception {
		// Landing page
		driver.get(sAppURL);

		// Login as EC
		CAM_BF_Login(driver, username, password);

		// Search using date, time
		searchReservedEvents(driver, date, time);

		// Verify if assigned event can be viewed
		Thread.sleep(1_000);
		driver.findElement(By.xpath(prop.getProperty("Lnk_ReserveEvents_DetailsOfFirstResult"))).click();
		try {
			assertEquals(eventReservedExp,
					driver.findElement(By.xpath(prop.getProperty("Lbl_EventDetails_EventName"))).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		// Screenshot
		takeScreenShot(driver, new Throwable().getStackTrace()[0].getMethodName() + testCaseNumber);
		Thread.sleep(1_000);
		driver.findElement(By.id(prop.getProperty("Btn_EventDetails_Homepage"))).click();

		// Logout
		Thread.sleep(1_000);
		driver.findElement(By.id(prop.getProperty("Btn_Homepage_Logout"))).click();

	}

	@Test
	@FileParameters("excel/p_no_event_found.csv")
	public void testNoAvailableEvents(int testCaseNumber, String username, String password, String date, String time,
			String errorExp) throws Exception {
		// Landing page
		driver.get(sAppURL);

		// Login as EC
		CAM_BF_Login(driver, username, password);

		driver.findElement(By.id(prop.getProperty("Btn_Homepage_AvailableEvents"))).click();
		// Search using date, time
		search(driver, date, time);

		Thread.sleep(1_000);
		driver.findElement(By.id(prop.getProperty("Btn_AvailableEvents_Search"))).click();

		try {
			assertEquals(errorExp, driver.findElement(By.id(prop.getProperty("Lbl_AvailableEvents_Error"))).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		// Screenshot
		takeScreenShot(driver, new Throwable().getStackTrace()[0].getMethodName() + testCaseNumber);

		Thread.sleep(1_000);
//		driver.findElement(By.id(prop.getProperty("Btn_AvailableEvents_Back"))).click();
		driver.findElement(By.id("backp")).click();

		// Logout
		Thread.sleep(1_000);
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
