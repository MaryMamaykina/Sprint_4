package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class OrderPopups {
    private final WebDriver webDriver;
    public OrderPopups(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //
    private final By titleThirdPage = By.xpath("//div[text()='Хотите оформить заказ?']");
    private final By buttonYes = By.xpath("//div[@class='Order_Modal__YZ-d3']/div[@class='Order_Buttons__1xGrp']/button[text()='Да']");
    private final By titleFourthPage = By.xpath("//div[text()='Заказ оформлен']");

    //Нажатие на согласие сделать заказ
    public void agreeToMakeOrder(){
        new WebDriverWait(webDriver, Duration.ofMillis(3000L))
                .until(ExpectedConditions.visibilityOfElementLocated(titleThirdPage));
        webDriver.findElement(buttonYes).click();
    }
    //Ожидание появления попапа с заголовком "Заказ оформлен"
    public void waitForSuccessPopup(){
        new WebDriverWait(webDriver, Duration.ofMillis(3000L))
                .until(ExpectedConditions.visibilityOfElementLocated(titleFourthPage));
    }
}
