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
public class EventManagerTest extends CAM_BusinessFunctions {
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
	@FileParameters("excel/M_happy_event_update.csv")
	public void testUpdateEventXHappy(String testCaseNo, String username, String password, String date, String time,
			String dateEvent, String timeFrom, String timeTo, String capacity, String location, String duration,
			String estAttendance, String successMsgExp) throws Exception {
		// Landing page
		driver.get(sAppURL);
		CAM_BF_Login(driver, username, password);

		Thread.sleep(1_000);
		driver.findElement(By.name(prop.getProperty("Btn_Home_ViewCreatedEvents"))).click();

		search(driver, date, time);
		Thread.sleep(1_000);
		driver.findElement(By.id(prop.getProperty("Btn_SearchCreatedEvents_Search"))).click();

		// Click first result
		driver.findElement(By.xpath(prop.getProperty("Lnk_ReserveEvents_DetailsOfFirstResult"))).click();

		Thread.sleep(1_000);
		driver.findElement(By.linkText("Modify")).click();

		// Edit event page
		driver.findElement(By.name(prop.getProperty("Txt_UpdateEvent_Capacity"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_UpdateEvent_Capacity"))).sendKeys(capacity);
		driver.findElement(By.name(prop.getProperty("Txt_UpdateEvent_Location"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_UpdateEvent_Location"))).sendKeys(location);

		updateEventDate(driver, dateEvent, timeFrom, timeTo);

		new Select(driver.findElement(By.name(prop.getProperty("Txt_UpdateEvent_Duration"))))
				.selectByVisibleText(duration);
		driver.findElement(By.name(prop.getProperty("Txt_UpdateEvent_EstAttendance"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_UpdateEvent_EstAttendance"))).sendKeys(estAttendance);

		Thread.sleep(1_000);
		driver.findElement(By.id(prop.getProperty("Btn_UpdateEvent_Submit"))).click();

		Alert javascriptAlert = driver.switchTo().alert();
		javascriptAlert.accept();

		try {
			assertEquals(successMsgExp, driver.findElement(By.id(prop.getProperty("Lbl_Homepage_Success"))).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		// Screenshot
		takeScreenShot(driver, new Throwable().getStackTrace()[0].getMethodName());

		driver.findElement(By.id(prop.getProperty("Btn_Homepage_Logout"))).click();
	}

	@Test
	@FileParameters("excel/M_eventUpdateValidations.csv")
	public void testUpdateEventValidations(String testCaseNo, String username, String password, String date,
			String time, String dateEvent, String timeFrom, String timeTo, String eventCoordinator,
			String estAttendance, String errorMsg, String durationError, String eventCoordinatorError,
			String estAttendanceError) throws Exception {
		// Landing page
		driver.get(sAppURL);
		CAM_BF_Login(driver, username, password);

		Thread.sleep(1_000);
		driver.findElement(By.name(prop.getProperty("Btn_Home_ViewCreatedEvents"))).click();

		search(driver, date, time);
		Thread.sleep(1_000);
		driver.findElement(By.id(prop.getProperty("Btn_SearchCreatedEvents_Search"))).click();

		// Click first result
		driver.findElement(By.xpath(prop.getProperty("Lnk_ReserveEvents_DetailsOfFirstResult"))).click();

		Thread.sleep(1_000);
		driver.findElement(By.linkText("Modify")).click();

		// Update validations here
		updateEventDate(driver, dateEvent, timeFrom, timeTo);
		
		// [DATEPICKER not working properly with selenium]
		//Thread.sleep(15_000);
		new Select(driver.findElement(By.name(prop.getProperty("Lst_UpdateEvent_EventCoordinator"))))
				.selectByVisibleText(eventCoordinator);
		driver.findElement(By.name(prop.getProperty("Txt_UpdateEvent_EstAttendance"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_UpdateEvent_EstAttendance"))).sendKeys(estAttendance);

		// Click submit
		Thread.sleep(1_000);
		driver.findElement(By.id(prop.getProperty("Btn_UpdateEvent_Submit"))).click();
		// Confirm
		Alert javascriptAlert = driver.switchTo().alert();
		javascriptAlert.accept();
		
		// Verify error msgs
		try {
			assertEquals(errorMsg, driver.findElement(By.id(prop.getProperty("Lbl_UpdateEvent_ErrorMsg"))).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals(eventCoordinatorError,
					driver.findElement(By.id(prop.getProperty("Lbl_UpdateEvent_EventCoordinatorError"))).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals(estAttendanceError, driver.findElement(By.id(prop.getProperty("Lbl_UpdateEvent_EstAttendanceError"))).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
//		try {
//			assertEquals(durationError, driver.findElement(By.id(prop.getProperty("Lbl_UpdateEvent_DurationError"))).getText());
//		} catch (Error e) {
//			verificationErrors.append(e.toString());
//		}

		// Screenshot
		takeScreenShot(driver, new Throwable().getStackTrace()[0].getMethodName());
		// Go Back
		Thread.sleep(1_000);
		driver.findElement(By.id(prop.getProperty("Btn_UpdateEvent_Back"))).click();
		Thread.sleep(1_000);
		driver.findElement(By.id(prop.getProperty("Btn_SearchCreatedEvents_Back"))).click();

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
