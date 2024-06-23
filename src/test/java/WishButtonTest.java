import com.aptekaeconom.Pages.CartPage;
import com.aptekaeconom.Pages.MainPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.aptekaeconom.Pages.CartPage.EMPTY_BASKET_TOTAL_PRICE;
import static com.aptekaeconom.Pages.CartPage.ONE_ITEM_ADDED_TO_CART;
import static com.aptekaeconom.Utils.Waiters.waitUntilVisible;

@Epic("Избранное")
public class WishButtonTest extends BaseTest {

    @Test
    @Feature("Работа с отложенными товарами")
    @Severity(SeverityLevel.NORMAL)
    @Story("Добавление товара в отложенные")
    @Description("Товар можно отложить, нажав на кнопку с иконкой сердечка и текстом “Отложить” на карточке товара. " +
            "Отложенный товар появляется с пометкой “Товар отложен” в корзине, перейти в которую можно по кнопке в шапке. " +
            "При наведении курсора на эту кнопку появляется корректный текст о сумме товаров в избранном." +
            " Отложенный товар не учитывается в итоговой сумме заказа.")

    public void testWishButton() {
        MainPage mainPage = new MainPage(getDriver());

        mainPage.clickSalesButton();
        waitUntilVisible(getDriver(), mainPage.getFirstItemCard());
        mainPage.checkWishButtonHoverText();
        mainPage.clickWishButton();
        mainPage.clickBasket();

        CartPage cartPage = new CartPage(getDriver());
        waitUntilVisible(getDriver(), cartPage.getBasketTotalPricePerItem());

        Assert.assertEquals(cartPage.checkTotalPrice(), EMPTY_BASKET_TOTAL_PRICE);
        Assert.assertTrue(cartPage.comparePriceToPriceByHoverWishIcon());
        Assert.assertTrue(cartPage.checkWishMark());
    }

    @Test
    @Feature("Работа с отложенными товарами")
    @Severity(SeverityLevel.NORMAL)
    @Story("Добавление товара в корзину из отложенных товаров")
    @Description("Отложенный товар можно добавить к заказу, нажав на ссылку “Добавить к заказу?” у товара в корзине. " +
            "Товар добавляется в корзину в кол-ве 1 штука с актуальной ценой.")

    public void AddItemToCartFromWishTest() {
        MainPage mainPage = new MainPage(getDriver());

        mainPage.clickSalesButton();
        waitUntilVisible(getDriver(), mainPage.getFirstItemCard());
        mainPage.hoverOverWishButton();
        mainPage.clickWishButton();
        mainPage.clickBasket();

        CartPage cartPage = new CartPage(getDriver());
        cartPage.clickAddItemToCartButton();
        waitUntilVisible(getDriver(), cartPage.getCheckOutButton());
        Assert.assertEquals(cartPage.checkBasketTotalPricePerItem(), cartPage.checkTotalPrice());
        Assert.assertEquals(cartPage.checkItemsAmount(), ONE_ITEM_ADDED_TO_CART);
    }
}