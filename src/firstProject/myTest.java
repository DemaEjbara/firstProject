package firstProject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class myTest {
	//this is first project 
	WebDriver driver = new EdgeDriver();
	String userName = "standard_user";
	String passsword = "secret_sauce";
	String theWebSite = "https://www.saucedemo.com/";

	@BeforeTest
	public void beforeTest() {
		driver.manage().window().maximize();
		driver.get(theWebSite);
	}

	@Test(priority = 1)
	public void loginTest() throws InterruptedException {
		WebElement inputUsername = driver.findElement(By.id("user-name"));
		inputUsername.sendKeys(userName);
		WebElement inputPassword = driver.findElement(By.id("password"));
		inputPassword.sendKeys(passsword);
		Thread.sleep(2000);
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();
	}

	@Test(priority = 2)
	public void addAllItems() throws InterruptedException {
		List<WebElement> addElement = driver.findElements(By.className("btn"));
		for (int i = 0; i < addElement.size(); i++) {
			addElement.get(i).click();
			Thread.sleep(1000);

		}
	}

	@Test(priority = 3)
	public void removeAllItems() throws InterruptedException {
		Thread.sleep(2000);
		List<WebElement> removeElement = driver.findElements(By.className("btn_secondary"));
		for (int i = 0; i < removeElement.size(); i++) {
			removeElement.get(i).click();

		}
	}

	@Test(priority = 4)
	public void printFirstLetter() {
		List<WebElement> s = driver.findElements(By.className("inventory_item_name"));
		for (int i = 0; i < s.size(); i++) {
			System.out.println(s.get(i).getText().charAt(0));
		}
	}

	@Test(priority = 5)
	public void isLastLetterIsT() {
		List<WebElement> nameOfItems = driver.findElements(By.className("inventory_item_name"));
		for (int i = 0; i < nameOfItems.size(); i++) {
			if (nameOfItems.get(i).getText().endsWith("t")) {
				continue;
			} else
				System.out.println(nameOfItems.get(i).getText());
		}
	}

	@Test(priority = 6)
	public void reArrangeSalary() {

		WebElement sortDrop = driver.findElement(By.className("product_sort_container"));
		sortDrop.click();
		WebElement sortPricesAsc = driver
				.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select/option[3]"));
		sortPricesAsc.click();
		List<WebElement> nameOfItems = driver.findElements(By.className("inventory_item_name"));
		List<String> name = new ArrayList<>();
		List<WebElement> pricesElement = driver.findElements(By.className("inventory_item_price"));
		List<Double> newPricesElement = new ArrayList<>();
		for (WebElement e : pricesElement) {
			String price = e.getText().replace("$", "").trim();
			Double priceWithoutDollar = Double.parseDouble(price);
			newPricesElement.add(priceWithoutDollar);
		}
		Collections.sort(newPricesElement);
		for (int i = 0; i < nameOfItems.size(); i++) {
			System.out.println(nameOfItems.get(i).getText() + " has price a " + newPricesElement.get(i));

		}

	}

	@Test(priority = 7)
	public void printOfNameItemsNotIncludeDigits() {
		System.out.println("test 7 ");
		List<WebElement> descItems = driver.findElements(By.className("inventory_item_desc"));
		boolean b = false;
		for (int i = 0; i < descItems.size(); i++) {
			b=false;
			char[] k = descItems.get(i).getText().toCharArray();
			for (char r : k) {
				if (Character.isDigit(r)) {
					b = true;
					break;
				}
			}
			if (!b) {
				System.out.println(descItems.get(i).getText());
				System.out.println();

			} 
		}
	}


	@Test(priority = 3, enabled = false)
	public void removeItemFromCart() throws InterruptedException {
		driver.findElement(By.id("remove-sauce-labs-backpack")).click();
		Thread.sleep(2000);
	}

	@Test(priority = 4, enabled = false)
	public void logOutButton() throws InterruptedException {
		driver.findElement(By.id("react-burger-menu-btn")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("logout_sidebar_link")).click();
		Thread.sleep(2000);
		WebElement MainLogo = driver.findElement(By.className("login_logo"));
		System.out.println(MainLogo.getText());
	}

	@AfterTest
	public void AfterTest() {
		// driver.quit();

	}

}
