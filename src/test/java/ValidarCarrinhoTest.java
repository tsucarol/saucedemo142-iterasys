import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ValidarCarrinhoTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;

  @BeforeEach
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
    driver.get("https://www.saucedemo.com/");
    driver.manage().window().setSize(new Dimension(1936, 1096));
  }
  @AfterEach
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void validarCarrinho() {
    driver.findElement(By.cssSelector("*[data-test=\"username\"]")).click();
    driver.findElement(By.cssSelector("*[data-test=\"username\"]")).sendKeys("standard_user");
    driver.findElement(By.cssSelector("*[data-test=\"password\"]")).click();
    driver.findElement(By.cssSelector("*[data-test=\"password\"]")).sendKeys("secret_sauce");
    driver.findElement(By.cssSelector("*[data-test=\"login-button\"]")).click();

    driver.findElement(By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-backpack\"]")).click();

    driver.findElement(By.cssSelector("*[data-test=\"shopping-cart-link\"]")).click();

    assertEquals(driver.findElement(By.cssSelector("*[data-test=\"inventory-item-name\"]")).getText(), "Sauce Labs Backpack");
    assertEquals(driver.findElement(By.cssSelector("*[data-test=\"item-quantity\"]")).getText(), "1");
    assertEquals(driver.findElement(By.cssSelector("*[data-test=\"inventory-item-price\"]")).getText(), "$29.99");
  }
}
