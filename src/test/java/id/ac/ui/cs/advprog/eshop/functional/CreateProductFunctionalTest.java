package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {

    }

    @Test
    void testCreateProduct(ChromeDriver driver) throws Exception{
        // Open the web application
        driver.get("http://localhost:8080/product/create");

        // Fill out the form with product details
        WebElement productNameInput = driver.findElement(By.id("nameInput"));
        WebElement productQuantityInput = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.tagName("button"));

        productNameInput.sendKeys("Sampo Cap Bambang");
        productQuantityInput.sendKeys("100");
        submitButton.click();

        // Go to the product list page
        driver.get("http://localhost:8080/product/list");

        // Verify that the newly created product appears in the product list
        WebElement productListTable = driver.findElement(By.tagName("table"));
        assertTrue(productListTable.getText().contains("Sampo Cap Bambang"));
    }

    // Close the WebDriver after all tests are finished
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
