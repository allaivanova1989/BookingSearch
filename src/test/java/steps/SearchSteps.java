package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class SearchSteps {

    String placeName;
    WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Given("Город для поиска {string}")
    public void городДляПоиска(String placeName) {
        this.placeName = placeName;
    }

    @When("Пользователь ищет отель по городу")
    public void пользовательИщетотельПоГороду() {
        driver.get("https://www.booking.com/index.ru.html");
        driver.findElement(By.cssSelector("[type='search']")).sendKeys(placeName);
        driver.findElement(By.xpath("//span[contains(text(),'Проверить цены')]")).click();
    }

    @Then("Отель с названием {string} на первой странице")
    public void отельСНазванием(String keyword) {
        final List<WebElement> hotels = driver.findElements(By.xpath("//div[@data-testid=\"title\"]"));
        assertEquals(hotels.get(0).getText(), keyword, "имя отеля не совпадает");

    }

    @And("Рейтинг должен быть {string}")
    public void рейтингДолженБыть(String ratingResult) {
        final List<WebElement> numbers = driver.findElements(By.xpath("//div[@data-testid=\"review-score\"]/div[contains(text(),*)]"));
        assertEquals(numbers.get(0).getText(), ratingResult, "имя отеля не совпадает");

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
