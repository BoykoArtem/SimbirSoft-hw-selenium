import com.aptekaeconom.Pages.CartPage;
import com.aptekaeconom.Pages.MainPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.aptekaeconom.Pages.CartPage.EMPTY_BASKET_TOTAL_PRICE;
import static com.aptekaeconom.Pages.CartPage.ONE_ITEM_ADDED_TO_CART;
import static com.aptekaeconom.Utils.Waiters.waitUntilVisible;

@Epic("Избранное")
@Feature("Работа с отложенными товарами")
public class WishButtonTest extends BaseTest {
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Добавление товара в отложенные")
    @Description("Проверка что отложенный товар появляется в корзине с пометкой “Товар отложен”")
    public void testPendingItemIsDisplayedInCart() {
        MainPage mainPage = new MainPage(getDriver());

        mainPage.clickSalesButton();
        waitUntilVisible(getDriver(), mainPage.getFirstItemCard());
        mainPage.hoverOverItemCard();
        mainPage.clickWishButton();
        mainPage.clickBasket();

        CartPage cartPage = new CartPage(getDriver());
        waitUntilVisible(getDriver(), cartPage.getBasketTotalPricePerItem());

        Assert.assertTrue(cartPage.checkWishMark());
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Story("Добавление товара в отложенные")
    @Description("Проверка текста по ховеру на кнопку Отложить в карточке товара")
    public void testWishButtonHoverTextAtItemCard() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.clickSalesButton();
        waitUntilVisible(getDriver(), mainPage.getFirstItemCard());

        Assert.assertTrue(mainPage.checkWishButtonHoverText());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Добавление товара в отложенные")
    @Description("Проверка корректности суммы по ховеру на кнопку Отложить в корзине")
    public void testWishButtonHoverTextInCart() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.clickSalesButton();
        waitUntilVisible(getDriver(), mainPage.getFirstItemCard());
        mainPage.hoverOverItemCard();
        mainPage.clickWishButton();
        mainPage.clickBasket();

        CartPage cartPage = new CartPage(getDriver());
        waitUntilVisible(getDriver(), cartPage.getBasketTotalPricePerItem());

        Assert.assertTrue(cartPage.comparePriceToPriceByHoverWishIcon());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Добавление товара в отложенные")
    @Description("Проверка что отложенный товар не учитывается в итоговой сумме заказа.")
    public void testAddedItemFromWishListPriceIgnoredInTotalPrice() {
        MainPage mainPage = new MainPage(getDriver());

        mainPage.clickSalesButton();
        waitUntilVisible(getDriver(), mainPage.getFirstItemCard());
        mainPage.hoverOverItemCard();
        mainPage.clickWishButton();
        mainPage.clickBasket();

        CartPage cartPage = new CartPage(getDriver());
        waitUntilVisible(getDriver(), cartPage.getBasketTotalPricePerItem());

        Assert.assertEquals(cartPage.checkTotalPrice(), EMPTY_BASKET_TOTAL_PRICE);
    }

    @Test
    @Feature("Работа с отложенными товарами")
    @Severity(SeverityLevel.NORMAL)
    @Story("Добавление товара в корзину из отложенных товаров")
    @Description("Проверка что отложенный товар добавляется в корзину в кол-ве 1 штука")
    public void testAddedItemToCartFromWishListAmount() {
        MainPage mainPage = new MainPage(getDriver());

        mainPage.clickSalesButton();
        waitUntilVisible(getDriver(), mainPage.getFirstItemCard());
        mainPage.hoverOverWishButton();
        mainPage.clickWishButton();
        mainPage.clickBasket();

        CartPage cartPage = new CartPage(getDriver());
        cartPage.clickAddItemToCartButton();
        waitUntilVisible(getDriver(), cartPage.getCheckOutButton());

        Assert.assertEquals(cartPage.checkItemsAmount(), ONE_ITEM_ADDED_TO_CART);
    }

    @Test
    @Feature("Работа с отложенными товарами")
    @Severity(SeverityLevel.NORMAL)
    @Story("Добавление товара в корзину из отложенных товаров")
    @Description("Проверка что отложенный товар добавляется в корзину с актуальной ценой.")
    public void testAddedItemToCartFromWishListPrice() {
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
    }
}