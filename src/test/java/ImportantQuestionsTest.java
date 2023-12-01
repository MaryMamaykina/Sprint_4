import org.openqa.selenium.chrome.ChromeDriver;
import page_object.MainPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.junit.Test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ImportantQuestionsTest {
    private final int accordionItemIndex;
    private final String expectedText;
    public ImportantQuestionsTest(int idAccordionItem, String expectedText) {
        this.accordionItemIndex = idAccordionItem;
        this.expectedText = expectedText;
    }

    @Parameterized.Parameters
    public static Object[][] checkTextInAccordion() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }
    private WebDriver webDriver;

    @Before
    public void setup() {

        WebDriverManager.chromedriver().clearDriverCache().clearResolutionCache().setup();
        webDriver = new ChromeDriver();
    }

    @Test
    //Проверка выпадающего списка в разделе «Вопросы о важном».
    //Проверка того, что когда нажимаешь на стрелочку, открывается соответствующий текст.
    public void accordionItemTest() {
        MainPage objMainPage = new MainPage(webDriver);
        objMainPage.openPage();
        String textFromAccordionItem = objMainPage.getClickedItemPanelText(accordionItemIndex);
        Assert.assertEquals("Accordion items has wrong text", expectedText, textFromAccordionItem);
    }

    @After
    public void teardown() {
        webDriver.quit();
    }
}
