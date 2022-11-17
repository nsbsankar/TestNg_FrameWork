 package sankar.TestNG_FrameWork;

import org.testng.AssertJUnit;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LoginPage;

public class StandaloneTest {

	public static void main(String[] args) {
		String prodName = "ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		LoginPage loginpage = new LoginPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("nsbsankar05@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sankar@05");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product-> product.findElement(By.cssSelector("b"))
				.getText().equals(prodName)).findFirst().orElse(null);
		
		//
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
	//	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@routerlink='/dashboard/cart']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = cartProducts.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase(prodName));
		AssertJUnit.assertTrue(match);
		
		driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();
		
		Actions action = new Actions(driver);
		WebElement selectCountry = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
		action.sendKeys(selectCountry, "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollBy(0,600)");
		
		//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btnn")));

		WebElement e = driver.findElement(By.cssSelector(".btnn"));
		js.executeScript("arguments[0].click(0);",e);
		
		String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	driver.close();
		
		
	}

}
