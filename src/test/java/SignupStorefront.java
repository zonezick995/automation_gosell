import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utilities.driver.InitWebdriver;

import pages.storefront.SignupPage;
import pages.Mailnesia;

import java.sql.SQLException;

public class SignupStorefront extends BaseTest{

	SignupPage signupPage;
	Mailnesia mailnesiaPage;
	WebDriver driver1;
	
    @BeforeMethod
    public void setup() throws InterruptedException {
    	super.setup();
    	signupPage = new SignupPage(driver);
    }		
	
    @Test
    public void SignupWithPhone() throws SQLException, InterruptedException {
    	String phone = "1122334455";
    	
    	signupPage.navigate()
    			.clickUserInfoIcon()
    			.clickSignupIcon()
    			.selectCountry("Andorra")
                .inputMailOrPhoneNumber(phone)
                .inputPassword("Abc@12345")
    			.inputDisplayName("Luke Thames")
    			.inputBirthday("02/02/1990")
                .clickSignupBtn()
                .inputVerificationCode(signupPage.getOTPCode("+376:" + phone));
//                .clickConfirmBtn();
        Thread.sleep(5000);
    }
    
    @Test
    public void SignupWithEmail() throws SQLException, InterruptedException {
    	String username = "tienvan3456";
    	
    	signupPage.navigate()
		.clickUserInfoIcon()
		.clickSignupIcon()
		.selectCountry("Andorra")
    	.inputMailOrPhoneNumber(username + "@mailnesia.com")
        .inputPassword("Abc@12345")
		.inputDisplayName("Luke Thames")
		.inputBirthday("02/02/1990")
        .clickSignupBtn();

    	// Get verification code from Mailnesia
    	Thread.sleep(7000);
    	driver1 = new InitWebdriver().getDriver("chrome", "false");
    	mailnesiaPage = new Mailnesia(driver1);
    	String verificationCode = mailnesiaPage.navigate(username).getVerificationCode();
    	driver1.quit();
    	
    	signupPage.inputVerificationCode(verificationCode);
//    	.clickConfirmBtn();
        Thread.sleep(3000);
    }
    
    @AfterMethod
    public void tearDown() {
    	super.tearDown();
        if (driver1 != null) {
            driver1.quit();
        }
    }   
    
}
