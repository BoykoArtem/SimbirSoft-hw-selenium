package com.aptekaeconom.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static com.aptekaeconom.Utils.ScreenShots.takeScreenShot;
import static com.aptekaeconom.Utils.Waiters.waitUntilVisible;


public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public static final String SELECTED_TOP_MENU_CATEGORY_COLOR = "rgba(15, 168, 174, 1)";
    public static final String EXPECTED_HEALING_COSMETICS_SUBCATEGORY_NAME = "Косметика лечебная";
    public static final String EXPECTED_COSMETICS_CATEGORY_NAME = "Косметика";
    public static final String WISH_ICON_HOVER_TEXT = "Отложить";
    @FindBy(xpath = "//a[@href='/catalog/kosmetika/']")
    private WebElement cosmeticsDropDownMenu;
    @FindBy(xpath = "//a[@href='/catalog/kosmetika/kosmetika-lechebnaya/']/*[1]")
    private WebElement healingCosmetics;
    @FindBy(xpath = "//span[text()='Акция']")
    private WebElement saleCategory;
    @FindBy(xpath = "//span[@data-item='134828']")
    private WebElement firstItemCardWishButton;
    @FindBy(xpath = "//img[@class='noborder lazyloaded']")
    private WebElement firstItemCard;
    @FindBy(xpath = "(//span[@class='title dark_link'])[2]")
    private WebElement basket;

    public WebElement getFirstItemCard() {
        return firstItemCard;
    }

    public WebElement getCosmeticsDropDownMenu() {
        return cosmeticsDropDownMenu;
    }

    public WebElement getHealingCosmetics() {
        return healingCosmetics;
    }

    @Step("Переход в корзину")
    public MainPage clickBasket() {
        basket.click();
        return new MainPage(getDriver());
    }
    @Step("Клик в подкатегорию Лечебная косметика")
    public MainPage clickHealingCosmetics() {
        healingCosmetics.click();
        return new MainPage(getDriver());
    }
    @Step("Ховер на карточку товара")
    public MainPage hoverOverItemCard() {
        MainPage mainPage = new MainPage(getDriver());
        Actions actions = new Actions(getDriver());
        actions.moveToElement(mainPage
                        .firstItemCard)
                .perform();
        waitUntilVisible(getDriver(), mainPage.firstItemCardWishButton);

        return new MainPage(getDriver());
    }
    @Step("Ховер на кнопку Отложить")
    public MainPage hoverOverWishButton() {
        MainPage mainPage = new MainPage(getDriver());
        Actions actions = new Actions(getDriver());
        mainPage.hoverOverItemCard();
        actions.moveToElement(mainPage
                        .firstItemCardWishButton)
                .perform();
        return new MainPage(getDriver());
    }
    @Step("Проверка текста по ховеру на кнопку отложить")
    public boolean checkWishButtonHoverText() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.hoverOverWishButton();
        takeScreenShot(getDriver());
        return firstItemCardWishButton.getAttribute("title").equals(WISH_ICON_HOVER_TEXT);
    }
    @Step("Переход в раздел акций")
    public MainPage clickSalesButton() {
        saleCategory.click();
        return new MainPage(getDriver());
    }

    @Step("Ховер по каталогу Косметика")
    public MainPage hoverOverCosmeticsCategory() {
        MainPage mainPage = new MainPage(getDriver());
        Actions actions = new Actions(getDriver());
        actions.moveToElement(mainPage
                        .cosmeticsDropDownMenu)
                .perform();
        waitUntilVisible(getDriver(), mainPage.getHealingCosmetics());
        takeScreenShot(getDriver());
        return new MainPage(getDriver());
    }

    @Step("Проверка текста и цвета выделенной категории Косметики в верхнем меню")
    public boolean getCheckTopMenuCategoryTextAndColor() {
        return (cosmeticsDropDownMenu.getText().equals(EXPECTED_COSMETICS_CATEGORY_NAME))
                && (cosmeticsDropDownMenu.getCssValue("color").equals(SELECTED_TOP_MENU_CATEGORY_COLOR));
    }

    @Step("Проверка текста и цвета выделенной подкатегории Лечебная Косметика в дропдаун меню")
    public boolean getCheckDropDownMenuSelectedSubCategoryTextAndColor() {
        return (healingCosmetics.getText().equals(EXPECTED_HEALING_COSMETICS_SUBCATEGORY_NAME))
                && (healingCosmetics.getCssValue("color").equals(SELECTED_TOP_MENU_CATEGORY_COLOR));
    }

    @Step("Клик по кнопке Отложить")
    public MainPage clickWishButton() {
        firstItemCardWishButton.click();
        return new MainPage(getDriver());
    }
}