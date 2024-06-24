package com.aptekaeconom.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static com.aptekaeconom.Utils.ScreenShots.takeScreenShot;

public class CatalogPage extends BasePage {
    public CatalogPage(WebDriver driver) {
        super(driver);
    }
    public static final String SELECTED_SIDE_MENU_CATEGORY_COLOR = "rgba(12, 142, 147, 1)";
    public static final String SELECTED_SIDE_MENU_SUBCATEGORY_COLOR = "rgba(51, 51, 51, 1)";
    public static final List <String> EXPECTED_BREADCRUMBS = List.of("Главная", "Каталог", "Косметика", "Косметика лечебная");
    public static final String EXPECTED_HEALING_COSMETICS_TITLE = "Косметика лечебная";
    public static final String EXPECTED_COSMETICS_SIDEMENU_NAME = "Косметика";
    @FindBy(xpath = "//div[contains(@class,'catalog_item main_item_wrapper')]")
    private WebElement itemCard;
    @FindBy(xpath = "(//a[@class='icons_fa parent'])[2]")
    private WebElement selectedSideMenuCosmeticsCategory;
    @FindBy(xpath = "//li[@class='  current ']//a[1]/*")
    public WebElement selectedHealingCosmeticsSubCategoryInSideMenu;
    @FindBy(xpath = "//span[@itemprop='name']")
    private List<WebElement> breadCrumbs;

    public List<String> listBreadCrumbs(List<WebElement> elementList) {
        List<String> stringList = new ArrayList<>();
        for (WebElement element : elementList) {
            stringList.add(element.getText());
        }
        takeScreenShot(getDriver());
        return stringList;
    }
    @Step("Проверка корректности хлебных крошек")
    public List<String> getBreadCrumbs() {
        return listBreadCrumbs(breadCrumbs);
    }
    @Step("Проверка наличия карточек товара")
    public boolean getItemCard() {
        takeScreenShot(getDriver());
        return itemCard.isDisplayed();
    }
    @Step("Проверка текста и цвета выделенной категории Косметики в сайд-меню")
    public boolean getCheckSelectedSideMenuCategoryTextAndColor() {
        takeScreenShot(getDriver());
        return selectedSideMenuCosmeticsCategory.getText().equals(EXPECTED_COSMETICS_SIDEMENU_NAME)
        && selectedSideMenuCosmeticsCategory.getCssValue("background-color").equals(SELECTED_SIDE_MENU_CATEGORY_COLOR);
    }
    @Step("Проверка перехода в подкатегорию Лечебная Косметика")
    public boolean getCheckCurrentPageTitle() {
        takeScreenShot(getDriver());
        return getDriver().getTitle().equals(EXPECTED_HEALING_COSMETICS_TITLE);
    }
    @Step("Ховер по каталогу Косметика в сайд-меню")
    public CatalogPage hoverOverSideMenuCosmeticsCategory() {
        CatalogPage catalogPage = new CatalogPage(getDriver());
        Actions actions = new Actions(getDriver());
        actions.moveToElement(catalogPage
                        .selectedSideMenuCosmeticsCategory)
                .perform();
        takeScreenShot(getDriver());
        return new CatalogPage(getDriver());
    }
    @Step("Проверка текста и цвета выделенной подкатегории Лечебная Косметика в сайд-меню")
    public boolean getCheckSelectedSideMenuSubCategoryTextAndColor() {
        takeScreenShot(getDriver());
        return selectedHealingCosmeticsSubCategoryInSideMenu.getText().equals(EXPECTED_HEALING_COSMETICS_TITLE)
                && selectedHealingCosmeticsSubCategoryInSideMenu.getCssValue("color").equals(SELECTED_SIDE_MENU_SUBCATEGORY_COLOR);
    }
}