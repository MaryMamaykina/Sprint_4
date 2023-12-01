package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage2 {
    private final WebDriver webDriver;

    public OrderPage2(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //кнопка Заказать
    private final By buttonOrder = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    //поле Когда привезти самокат
    private final By fieldOrderDate = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //выпадающий список дат
    private final By listOfOrderDate = By.className("react-datepicker__month-container");
    //поле Срок аренды
    private final By fieldRentalPeriod = By.xpath("//div[text()='* Срок аренды']");
    //выпадающий список Сроков аренды
    private final By dropdownListRentalPeriod = By.className("Dropdown-menu");

    //Ожидание загрузки страницы
    public void waitForLoading() {
        new WebDriverWait(webDriver, Duration.ofMillis(500L))
                .until(ExpectedConditions.elementToBeClickable(buttonOrder));
    }
    //Выбор даты доставки самоката
    public void selectData(int number){
        clickField(fieldOrderDate);
        waitFieldBeClickable(listOfOrderDate);
        By locatorData = By.xpath("//div[text()='" + number + "']");
        clickField(locatorData);
    }
    //Выбор периода аренды самоката
    public void selectRentalPeriod(String option){
        clickField(fieldRentalPeriod);
        new WebDriverWait(webDriver, Duration.ofMillis(500L))
                .until(ExpectedConditions.attributeToBe(dropdownListRentalPeriod,"aria-expanded","true"));
        By locatorData = By.xpath("//div[text()='"+option+"']");
        clickField(locatorData);
    }
    //Выбор цвета
    public void selectColour(String colour){
        By locatorColour = By.id(colour);
        clickField(locatorColour);
    }
    //Нажатие кнопки Далее
    public void clickButtonOrder(){
        clickField(buttonOrder);
    }

    private void clickField(By locator) {
        webDriver.findElement(locator).click();
    }
    private void waitFieldBeClickable(By locator) {
        new WebDriverWait(webDriver, Duration.ofMillis(500L))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }


}
