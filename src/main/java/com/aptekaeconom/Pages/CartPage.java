package com.aptekaeconom.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static com.aptekaeconom.Utils.ScreenShots.takeScreenShot;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public static final String EMPTY_BASKET_TOTAL_PRICE = "0 руб.";
    public static final String ONE_ITEM_ADDED_TO_CART = "В корзине 1 товар";

    @FindBy(xpath = "(//span[@class='basket-item-price-current-text'])[2]")
    private WebElement basketTotalPricePerItem;

    public WebElement getBasketTotalPricePerItem() {
        return basketTotalPricePerItem;
    }

    @FindBy(xpath = "//div[@data-entity='basket-total-price']")
    private WebElement basketTotalPrice;
    @FindBy(xpath = "//*[@id=\"header\"]/div[2]/div[1]/div/div/div/div[4]/div[1]/a")
    private WebElement wishIcon;
    @FindBy(xpath = "(//div[contains(@class,'alert alert-warning')])[2]")
    private WebElement wishMark;
    @FindBy(xpath = "//a[@data-entity='basket-item-remove-delayed']")
    private WebElement addWishItemToCartButton;
    @FindBy(xpath = "//button[contains(@class,'btn btn-lg')]")
    private WebElement checkOutButton;

    public WebElement getCheckOutButton() {
        return checkOutButton;
    }

    @FindBy(xpath = "//a[@class='basket-items-list-header-filter-item active']")
    private WebElement itemsAmount;

    @Step("Получаем итоговую сумму корзины")
    public String checkTotalPrice() {
        takeScreenShot(getDriver());
        return basketTotalPrice.getText();
    }

    @Step("Ховер на иконку отложенных товаров на странице корзины")
    public CartPage hoverOverWishIcon() {
        CartPage cartPage = new CartPage(getDriver());
        Actions actions = new Actions(getDriver());
        actions.moveToElement(cartPage
                        .wishIcon)
                .perform();
        return new CartPage(getDriver());
    }

    @Step("Получаем сумму товаров в корзине")
    public String checkBasketTotalPricePerItem() {
        return basketTotalPricePerItem.getText();
    }

    @Step("Получаем текст по ховеру на иконку отложенных товаров на странице корзины")
    public String checkPriceByHoverWishButton() {
        hoverOverWishIcon();
        return wishIcon.getAttribute("title");
    }

    @Step("Проверка корректности текста по ховеру на иконку отложенных товаров на странице корзины")
    public boolean comparePriceToPriceByHoverWishIcon() {
        return checkPriceByHoverWishButton().equals(("В отложенных товаров на ") + checkBasketTotalPricePerItem());
    }

    @Step("Проверка что у отложенного товара в корзине отображается текст Товар отложен")
    public boolean checkWishMark() {
        takeScreenShot(getDriver());
        return wishMark.isDisplayed();
    }

    @Step("Клик по кнопке добавления отложенного товара в корзину")
    public CartPage clickAddItemToCartButton() {
        addWishItemToCartButton.click();
        return new CartPage(getDriver());
    }

    @Step("Проверка отображения количества добавленных товаров в корзину")
    public String checkItemsAmount() {
        takeScreenShot(getDriver());
        return itemsAmount.getText();
    }
}