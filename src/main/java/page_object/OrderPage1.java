package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage1 {
    private final WebDriver webDriver;
    public OrderPage1(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //Графа имя
    private final By userName = By.xpath("//input[@placeholder='* Имя']");
    //Графа фамилия
    private final By userLastName = By.xpath("//input[@placeholder='* Фамилия']");
    //Графа адрес
    private final By userAddress = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //Графа станция метро
    private final By userMetroStation = By.xpath("//input[@placeholder='* Станция метро']");
    //Графа телефон
    private final By userPhoneNumber = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Локатор для первой видимой станции метро
    private final By firstVisibleMetroStation = By.xpath("//div[text()='Бульвар Рокоссовского']");
    //Локатор кнопки далее
    private final By buttonNext = By.xpath("//button[text()='Далее']");

    public void waitForLoadingPage1(){
        new WebDriverWait(webDriver, Duration.ofMillis(500L))
                .until(ExpectedConditions.elementToBeClickable(buttonNext));
    }
    public void fillName(String text){
        clickField(userName);
        addTextIntoField(userName, text);
    }
    public void fillLastName(String text){
        clickField(userLastName);
        addTextIntoField(userLastName, text);
    }
    public void fillAddress(String text){
        clickField(userAddress);
        addTextIntoField(userAddress, text);
    }
    public void selectMetroStation(String stationName){

        clickField(userMetroStation);
        checkLoadingOptions(firstVisibleMetroStation);
        By metroStationLocator = getMetroStationLocator(stationName);
        scrollToNeededLocator(metroStationLocator);
        clickField(metroStationLocator);
    }
    public void fillPhone(String text){
        clickField(userPhoneNumber);
        addTextIntoField(userPhoneNumber, text);
    }
    public void clickNext(){
        clickField(buttonNext);
    }

    private void clickField(By locator){
        webDriver.findElement(locator).click();
    }
    private void addTextIntoField(By locatorField, String text){
        webDriver.findElement(locatorField).sendKeys(text);
    }
    private void checkLoadingOptions(By locator){
        new WebDriverWait(webDriver, Duration.ofMillis(500L))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    private By getMetroStationLocator(String stationName){
        return By.xpath("//div[text()='"+stationName+"']");
    }
    private void scrollToNeededLocator(By locator){
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", webDriver.findElement(locator));
    }


}
