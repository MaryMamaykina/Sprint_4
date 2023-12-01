import org.openqa.selenium.chrome.ChromeDriver;
import page_object.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import page_object.OrderPage1;
import page_object.OrderPage2;
import page_object.OrderPopups;

public class OrderTest {
    private WebDriver webDriver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().clearDriverCache().clearResolutionCache().setup();
        webDriver = new ChromeDriver();
    }

    @Test
    //Проверка работы функции заказ самоката с помощью кнопки Заказать, расположенной сверху страницы
    public void shouldMakeOrderWithButtonOrderOnTop() {

        MainPage objMainPage = new MainPage(webDriver);
        objMainPage.openPage();
        objMainPage.agreeToCookiesIfNeeded();
        objMainPage.clickButtonOrderOnTop();

        OrderPage1 objOrderPage1 = new OrderPage1(webDriver);
        objOrderPage1.waitForLoadingPage1();
        objOrderPage1.fillName("Мария");
        objOrderPage1.fillLastName("Мамария");
        objOrderPage1.fillAddress("Новослободская");
        objOrderPage1.selectMetroStation("Университет");
        objOrderPage1.fillPhone("79086589023");
        objOrderPage1.clickNext();

        OrderPage2 objOrderPage2 = new OrderPage2(webDriver);
        objOrderPage2.waitForLoading();
        objOrderPage2.selectData(30);
        objOrderPage2.selectRentalPeriod("сутки");
        objOrderPage2.selectColour("grey");
        objOrderPage2.clickButtonOrder();

        OrderPopups objOrderPage3 = new OrderPopups(webDriver);
        objOrderPage3.agreeToMakeOrder();
        objOrderPage3.waitForSuccessPopup();
    }
    @Test
    //Проверка работы функции заказ самоката с помощью кнопки Заказать, расположенной над вопросами о важном
    public void shouldMakeOrderWithButtonOrderBellow() {

        MainPage objMainPage = new MainPage(webDriver);
        objMainPage.openPage();
        objMainPage.agreeToCookiesIfNeeded();
        objMainPage.clickButtonOrderBellow();

        OrderPage1 objOrderPage1 = new OrderPage1(webDriver);
        objOrderPage1.waitForLoadingPage1();
        objOrderPage1.fillName("Николай");
        objOrderPage1.fillLastName("Петрович");
        objOrderPage1.fillAddress("Великолепный");
        objOrderPage1.selectMetroStation("Войковская");
        objOrderPage1.fillPhone("74563721123");
        objOrderPage1.clickNext();

        OrderPage2 objOrderPage2 = new OrderPage2(webDriver);
        objOrderPage2.waitForLoading();
        objOrderPage2.selectData(1);
        objOrderPage2.selectRentalPeriod("четверо суток");
        objOrderPage2.selectColour("black");
        objOrderPage2.clickButtonOrder();

        OrderPopups objOrderPopups = new OrderPopups(webDriver);
        objOrderPopups.agreeToMakeOrder();
        objOrderPopups.waitForSuccessPopup();
    }

    @After
    public void teardown() {
        webDriver.quit();
    }
}
