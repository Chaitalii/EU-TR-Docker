package pages;

import org.openqa.selenium.By;

import utilities.PropertyLoader;
import utilities.Utils;

public class LoginPage {

    By loginUserName= By.xpath("//input[contains(@placeholder, 'Enter your Email or Username')]");
    By loginpwd=By.xpath("//input[contains(@placeholder, 'Enter password')]");
    By loginButton= By.xpath("//span[text()='Log in']");
    By profileLink= By.xpath("//a[@class='profile-link']");
    By userEmail=By.xpath("//p[@class='ao-profile-top__profile-details-email']");
    By errorMessage= By.xpath("//div[@id='message-sign-in']");
    Utils util= new Utils();
    String wrongLoginMessage= PropertyLoader.loadProperty("errorMessage");
    
	public void loginToTourRader(String userName, String pwd){
		util.sendText(loginUserName, userName);
		util.sendText(loginpwd, pwd);
		util.click(loginButton);
		util.waitForPageLoad();
	}
	public void verifyLoggedInUser() throws InterruptedException{
	
		util.verify_Element_displayed(profileLink);
		util.click(profileLink);
		String user=util.get_Element_Text(userEmail);
		org.testng.Assert.assertEquals(user, PropertyLoader.loadProperty("login.username"));
			
		}
	public boolean checkErrorMessage() throws InterruptedException{
		util.verify_Element_displayed(errorMessage);
//		util.verify_Element_Not_displayed(errorMessage);
		String actMessage=util.get_Element_Text(errorMessage);
		boolean errorDisplayed=	actMessage.contentEquals(PropertyLoader.loadProperty("invalid.login"));
		return errorDisplayed;
		}
	
	}
