package CruiseActivityMangement;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CAM_BusinessFunctions {
	public static WebDriver driver;
	public static String baseUrl;
	public static Properties prop;

	public void CAM_BF_Login(WebDriver driver, String username, String password) {
		driver.findElement(By.name(prop.getProperty("Txt_Login_Username"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Login_Username"))).sendKeys(username);
		driver.findElement(By.name(prop.getProperty("Txt_Login_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Login_Password"))).sendKeys(password);
		try {
			Thread.sleep(1_000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id(prop.getProperty("Btn_Login_Login"))).click();
	}

	public void CAM_BF_Register(WebDriver driver, String username, String password, String firstName, String lastName,
			String phone, String email, String roomNumber, String deckNumber) {
		driver.findElement(By.name(prop.getProperty("Txt_Register_Username"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Register_Username"))).sendKeys(username);
		driver.findElement(By.name(prop.getProperty("Txt_Register_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Register_Password"))).sendKeys(password);
		driver.findElement(By.id(prop.getProperty("Txt_Register_FirstName"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_Register_FirstName"))).sendKeys(firstName);
		driver.findElement(By.id(prop.getProperty("Txt_Register_LastName"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_Register_LastName"))).sendKeys(lastName);
		driver.findElement(By.id(prop.getProperty("Txt_Register_Phone"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_Register_Phone"))).sendKeys(phone);
		driver.findElement(By.id(prop.getProperty("Txt_Register_Email"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_Register_Email"))).sendKeys(email);
		driver.findElement(By.id(prop.getProperty("Txt_Register_RoomNumber"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_Register_RoomNumber"))).sendKeys(roomNumber);
		driver.findElement(By.id(prop.getProperty("Txt_Register_DeckNumber"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_Register_DeckNumber"))).sendKeys(deckNumber);
		try {
			Thread.sleep(1_000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id(prop.getProperty("Btn_Register_Submit"))).click();
	}

	// Event Coordinator methods
	public void searchAssignedEvents(WebDriver driver, String date, String time) {
		driver.findElement(By.id(prop.getProperty("Btn_ECHomepage_EventSummary"))).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// convert hidden field to text field for searching
		js.executeScript("document.getElementById('datepicker').setAttribute('type', 'text');");
		driver.findElement(By.id(prop.getProperty("Txt_Search_Date"))).sendKeys(date);
		js.executeScript("document.getElementById('timepicker').setAttribute('type', 'text');");
		driver.findElement(By.id(prop.getProperty("Txt_Search_Time"))).sendKeys(time);
		try {
			Thread.sleep(1_000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id(prop.getProperty("Btn_EventSummaryEC_Search"))).click();
	}
	
	// Passenger search
	public void search(WebDriver driver, String date, String time) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// convert hidden field to text field for searching
		js.executeScript("document.getElementById('datepicker').setAttribute('type', 'text');");
		driver.findElement(By.id(prop.getProperty("Txt_Search_Date"))).sendKeys(date);
		js.executeScript("document.getElementById('timepicker').setAttribute('type', 'text');");
		driver.findElement(By.id(prop.getProperty("Txt_Search_Time"))).sendKeys(time);
	}
	
	public void CAM_BF_UpdateProfile(WebDriver driver, String username, String password, String firstName, String lastName,
            String phone, String email, String roomNumber, String deckNumber) {
        driver.findElement(By.name(prop.getProperty("Txt_Register_Username"))).clear();
        driver.findElement(By.name(prop.getProperty("Txt_Register_Username"))).sendKeys(username);
        driver.findElement(By.name(prop.getProperty("Txt_Register_Password"))).clear();
        driver.findElement(By.name(prop.getProperty("Txt_Register_Password"))).sendKeys(password);
        driver.findElement(By.id(prop.getProperty("Txt_Register_FirstName"))).clear();
        driver.findElement(By.id(prop.getProperty("Txt_Register_FirstName"))).sendKeys(firstName);
        driver.findElement(By.id(prop.getProperty("Txt_Register_LastName"))).clear();
        driver.findElement(By.id(prop.getProperty("Txt_Register_LastName"))).sendKeys(lastName);
        driver.findElement(By.id(prop.getProperty("Txt_Register_Phone"))).clear();
        driver.findElement(By.id(prop.getProperty("Txt_Register_Phone"))).sendKeys(phone);
        driver.findElement(By.id(prop.getProperty("Txt_Register_Email"))).clear();
        driver.findElement(By.id(prop.getProperty("Txt_Register_Email"))).sendKeys(email);
        driver.findElement(By.id(prop.getProperty("Txt_Register_RoomNumber"))).clear();
        driver.findElement(By.id(prop.getProperty("Txt_Register_RoomNumber"))).sendKeys(roomNumber);
        driver.findElement(By.id(prop.getProperty("Txt_Register_DeckNumber"))).clear();
        driver.findElement(By.id(prop.getProperty("Txt_Register_DeckNumber"))).sendKeys(deckNumber);
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        driver.findElement(By.id(prop.getProperty("Btn_Update_Submit"))).click();
       
    }
	
	public void searchReservedEvents(WebDriver driver, String date, String time) {
		driver.findElement(By.id(prop.getProperty("Btn_Home_ViewReservedEvents"))).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// convert hidden field to text field for searching
		js.executeScript("document.getElementById('datepicker').setAttribute('type', 'text');");
		driver.findElement(By.id(prop.getProperty("Txt_Search_Date"))).sendKeys(date);
		js.executeScript("document.getElementById('timepicker').setAttribute('type', 'text');");
		driver.findElement(By.id(prop.getProperty("Txt_Search_Time"))).sendKeys(time);
		try {
			Thread.sleep(1_000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id(prop.getProperty("Btn_ReserveEvents_Search"))).click();
		
	}

	// Manager functions
	public void updateEventDate(WebDriver driver, String dateEvent, String timeFrom, String timeTo) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// convert hidden field to text field for searching
		js.executeScript("document.getElementById('datepicker').setAttribute('type', 'text');");
		driver.findElement(By.id(prop.getProperty("Txt_Search_Date"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_Search_Date"))).sendKeys(dateEvent);
		js.executeScript("document.getElementsByName('timepickerFrom')[0].setAttribute('type', 'text');");
		driver.findElement(By.name(prop.getProperty("Txt_UpdateEvent_TimeFrom"))).sendKeys(timeFrom);
		js.executeScript("document.getElementsByName('timepickerTo')[0].setAttribute('type', 'text');");
		driver.findElement(By.name(prop.getProperty("Txt_UpdateEvent_TimeTo"))).sendKeys(timeTo);
	}
	
	public void takeScreenShot(WebDriver driver, String screenshotname) {
		// Take screenshot and save it in source object
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// Define path where Screenshots will be saved

		// Copy the source file at the screenshot path
		try {
			FileUtils.copyFile(source, new File("./screenShots/" + screenshotname + ".png"));
		} catch (IOException e1) {
		}
		try {
//			    Change the thread value to run test files with delay
			Thread.sleep(3_000);
		} catch (InterruptedException e) {
		}
	}
}
