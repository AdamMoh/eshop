package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {

    private WebDriver driver;

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrlGet;
    private String baseUrlList;

    @BeforeEach
    public void setUp() {
        baseUrlGet = String.format("%s:%d/product/create", testBaseUrl, serverPort);
        baseUrlList = String.format("%s:%d/product/list", testBaseUrl, serverPort);
    }

    @Test
    void testCreateProduct(ChromeDriver driver) throws Exception{
        // Open the web application
        driver.get(baseUrlGet);

        // Fill out the form with product details
        WebElement productNameInput = driver.findElement(By.id("nameInput"));
        WebElement productQuantityInput = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.tagName("button"));

        productNameInput.sendKeys("Sampo Cap Bambang");
        productQuantityInput.sendKeys("100");
        submitButton.click();

        // Go to the product list page
        driver.get(baseUrlList);

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
