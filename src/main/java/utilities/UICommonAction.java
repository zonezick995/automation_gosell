package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class UICommonAction {
	
	final static Logger logger = LogManager.getLogger(UICommonAction.class);
	
	WebDriver driver;
	WebDriverWait wait;

	public UICommonAction(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void clickElement(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		} catch (StaleElementReferenceException ex) {
			logger.debug("StaleElementReferenceException caught in clickElement");
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		}
	}
	
	public void inputText(WebElement element, String text) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element)).clear();
			element.sendKeys(text);
		} catch (StaleElementReferenceException ex) {
			logger.debug("StaleElementReferenceException caught in inputText");
			wait.until(ExpectedConditions.elementToBeClickable(element)).clear();
			element.sendKeys(text);
		}
	}
	
	public String getText(WebElement element) {
		String text;
		try {
			text = wait.until(ExpectedConditions.visibilityOf(element)).getText();
		} catch (StaleElementReferenceException ex) {
			logger.debug("Catch StaleElementReferenceException caught in getText");
			text = wait.until(ExpectedConditions.visibilityOf(element)).getText();
		}
		logger.info("Text get: " + text);
		return text;
	}	

	public void uploadMultipleFile(WebElement element,String folder, String...fileNames){
		String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator +"uploadfile"+ File.separator + folder;
		String fullName = "";
		for (String fileName: fileNames) {
			fullName = fullName + filePath + fileName + "\n";
		}
		inputText(element,fullName);
	}
	public void checkTheCheckBoxOrRadio (WebElement element){
		if (!element.isSelected()){
			clickElement(element);
		}
	}
	public void uncheckTheCheckboxOrRadio (WebElement element){
		if (element.isSelected()){
			clickElement(element);
		}
	}

}
