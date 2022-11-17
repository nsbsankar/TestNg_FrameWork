package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonUtils;



public class LoginPage extends CommonUtils{

	WebDriver driver;
	public LoginPage(WebDriver driver) {
		
		super(driver);
		//initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="userEmail")
	WebElement userName;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	
	public void loginApplication(String username,String Password) {
		userName.sendKeys(username);
		password.sendKeys(Password);
		loginButton.click();
	}
	public void launchURL() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
