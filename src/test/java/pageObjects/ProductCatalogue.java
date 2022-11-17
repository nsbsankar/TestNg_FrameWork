package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonUtils;

public class ProductCatalogue extends CommonUtils{

	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		
		super(driver);
		//initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id=".mb-3")
	List<WebElement> products;
	
	By productsBy = By.cssSelector(".mb-3");
	
	public List<WebElement> getProductsList() {
		
		waitForElementToAppear(productsBy);
		return products;
	}
}
