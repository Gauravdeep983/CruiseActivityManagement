package CruiseActivityManagement.Selenium;

import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.*;

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
public class RegisterTest extends CAM_BusinessFunctions {
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
	@FileParameters("excel/Registration.csv")
	public void testRegisterValidations(String testCaseNo, String username, String password, String firstName, String lastName,
			String phone, String email, String roomNumber, String deckNumber, String membershipType, String role,
			String errorMsg, String usernameError, String passwordError, String firstNameError, String lastNameError,
			String phoneError, String emailError, String roomNumberError, String deckNumberError) throws Exception {
		// Landing page
		driver.get(sAppURL);
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();

		// Register Page
		CAM_BF_Register(driver, username, password, firstName, lastName, phone, email, roomNumber, deckNumber);
		// Screenshot
		takeScreenShot(driver, new Throwable().getStackTrace()[0].getMethodName() + testCaseNo);
		// Back to homepage
		driver.findElement(By.linkText(prop.getProperty("Lnk_Register_Back"))).click();
	}
	
	@Test
	@FileParameters("excel/RegisterHappy.csv")
	public void testRegisterHappy(String testCaseNo, String username, String password, String firstName, String lastName,
			String phone, String email, String roomNumber, String deckNumber, String membershipType, String role,
			String successMessage) throws Exception {
		// Landing page
		driver.get(sAppURL);
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();

		// Register Page
		CAM_BF_Register(driver, username, password, firstName, lastName, phone, email, roomNumber, deckNumber);
		
		// Registration successful message verfication
		try {
		      assertEquals(successMessage, driver.findElement(By.id(prop.getProperty("Lbl_Login_Success"))).getText());
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		// Screenshot
		takeScreenShot(driver, new Throwable().getStackTrace()[0].getMethodName() + testCaseNo);
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
