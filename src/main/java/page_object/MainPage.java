package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver webDriver;
    public MainPage (WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //Локатор для кнопки Заказать, расположенной сверху
    private final By buttonOrderOnTop = By.xpath("//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");
   //Локатор для кнопки Заказать, расположенной снизу
    private final By buttonOrderBellow = By.xpath("//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");
    //Локатор попапа куки
    private final By cookiesPopup = By.id("rcc-confirm-button");

    //Открытие главной страницы сайта
    public void openPage() {
        webDriver.get("https://qa-scooter.praktikum-services.ru/");
    }
    //Метод для получения текста по клику на элемент
    public String getClickedItemPanelText(int index) {
        scrollToItem(index);
        clickOnHeading(index);
        waitForHeadingClickHappen(index);
        return getPanelText(index);
    }
    //Нажатие на согласие на использование куки
    public void agreeToCookiesIfNeeded(){
        if (!webDriver.findElements(cookiesPopup).isEmpty()){
            webDriver.findElement(cookiesPopup).click();
            new WebDriverWait(webDriver, Duration.ofMillis(500L))
                    .until(ExpectedConditions.invisibilityOfElementLocated(cookiesPopup));
        }
    }
    //Метод для клика на кнопку Заказать сверху
    public void clickButtonOrderOnTop(){
        webDriver.findElement(buttonOrderOnTop).click();
    }
    //Метод для клика на кнопку Заказать снизу
    public void clickButtonOrderBellow(){
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", webDriver.findElement(buttonOrderBellow));
        webDriver.findElement(buttonOrderBellow).click();
    }
    //Получение локатор для вопроса в списке
    private By getHeadingLocator(int index){
        return By.id("accordion__heading-" + index);
    }
    //Получение локатора текста, который появляется по клику в разделе «Вопросы о важном»
    private By getPanelLocator(int index){
        return By.id("accordion__panel-" + index);
    }
    //Скролл до нужного элемента раздела «Вопросы о важном»
    private void scrollToItem(int index) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", webDriver.findElement(getHeadingLocator(index)));
    }
    //Клик на заголовок элемента
    private void clickOnHeading(int index) {
        webDriver.findElement(getHeadingLocator(index)).click();
    }
    //Ожидание появления текста
    private void waitForHeadingClickHappen(int index) {
        new WebDriverWait(webDriver, Duration.ofMillis(500L))
                .until(ExpectedConditions.attributeToBe(getHeadingLocator(index), "aria-disabled", "true"));
    }
    //Получение текста
    private String getPanelText(int index) {
        return webDriver.findElement(getPanelLocator(index)).getText();
    }
}
