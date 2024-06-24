import com.aptekaeconom.Pages.CatalogPage;
import com.aptekaeconom.Pages.MainPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.aptekaeconom.Pages.CatalogPage.EXPECTED_BREADCRUMBS;
import static com.aptekaeconom.Utils.Waiters.waitUntilVisible;

@Epic("Каталог товаров")
@Feature("Переходы в подкатегорию")
public class CatalogTest extends BaseTest {
    @Test
    @Story("Переход в подкатегорию из верхнего меню")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка что после перехода в подкатегорию отображается минимум один товар")
    public void testSubCategorySelectItemIsDisplayed() {

        MainPage mainPage = new MainPage(getDriver());
        waitUntilVisible(getDriver(), mainPage.getCosmeticsDropDownMenu());

        mainPage.hoverOverCosmeticsCategory();
        mainPage.clickHealingCosmetics();

        CatalogPage catalogPage = new CatalogPage(getDriver());

        Assert.assertTrue(catalogPage.getItemCard());
    }

    @Test
    @Story("Переход в подкатегорию из верхнего меню")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка что после перехода в подкатегорию корректно отображаются хлебные крошки")
    public void testSubCategoryBreadCrumbs() {

        MainPage mainPage = new MainPage(getDriver());
        waitUntilVisible(getDriver(), mainPage.getCosmeticsDropDownMenu());

        mainPage.hoverOverCosmeticsCategory();
        mainPage.clickHealingCosmetics();

        CatalogPage catalogPage = new CatalogPage(getDriver());

        List<String> actualBreadCrumbs = catalogPage.getBreadCrumbs();
        Assert.assertEquals(actualBreadCrumbs, EXPECTED_BREADCRUMBS);
    }

    @Test
    @Story("Переход в подкатегорию из верхнего меню")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка что после перехода в подкатегорию в верхнем меню выбранная категория отображается корректно")
    public void testCategoryTopMenu() {

        MainPage mainPage = new MainPage(getDriver());
        waitUntilVisible(getDriver(), mainPage.getCosmeticsDropDownMenu());

        mainPage.hoverOverCosmeticsCategory();
        mainPage.clickHealingCosmetics();

        mainPage.hoverOverCosmeticsCategory();

        Assert.assertTrue(mainPage.getCheckTopMenuCategoryTextAndColor());
    }

    @Test
    @Story("Переход в подкатегорию из верхнего меню")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка что после перехода в подкатегорию в верхнем меню выбранная подкатегория отображается корректно")
    public void testSubCategoryTopMenu() {

        MainPage mainPage = new MainPage(getDriver());
        waitUntilVisible(getDriver(), mainPage.getCosmeticsDropDownMenu());

        mainPage.hoverOverCosmeticsCategory();
        mainPage.clickHealingCosmetics();

        mainPage.hoverOverCosmeticsCategory();

        Assert.assertTrue(mainPage.getCheckDropDownMenuSelectedSubCategoryTextAndColor());
    }

    @Test
    @Story("Переход в подкатегорию из верхнего меню")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка что после перехода в подкатегорию в сайд меню выбранная категория отображается корректно")
    public void testCategorySideMenu() {

        MainPage mainPage = new MainPage(getDriver());
        waitUntilVisible(getDriver(), mainPage.getCosmeticsDropDownMenu());

        mainPage.hoverOverCosmeticsCategory();
        mainPage.clickHealingCosmetics();

        CatalogPage catalogPage = new CatalogPage(getDriver());

        Assert.assertTrue(catalogPage.getCheckSelectedSideMenuCategoryTextAndColor());
    }

    @Test
    @Story("Переход в подкатегорию из верхнего меню")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка что после перехода в подкатегорию в сайд меню выбранная подкатегория отображается корректно")
    public void testSubCategorySideMenu() {

        MainPage mainPage = new MainPage(getDriver());
        waitUntilVisible(getDriver(), mainPage.getCosmeticsDropDownMenu());

        mainPage.hoverOverCosmeticsCategory();
        mainPage.clickHealingCosmetics();

        CatalogPage catalogPage = new CatalogPage(getDriver());
        catalogPage.hoverOverSideMenuCosmeticsCategory();

        Assert.assertTrue(catalogPage.getCheckSelectedSideMenuSubCategoryTextAndColor());
    }

    @Test
    @Story("Переход в подкатегорию из верхнего меню")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка что после перехода в подкатегорию в тайтле страницы отображается выбранная подкатегория")
    public void testSubCategoryInPageTitle() {

        MainPage mainPage = new MainPage(getDriver());
        waitUntilVisible(getDriver(), mainPage.getCosmeticsDropDownMenu());

        mainPage.hoverOverCosmeticsCategory();
        mainPage.clickHealingCosmetics();

        CatalogPage catalogPage = new CatalogPage(getDriver());

        Assert.assertTrue(catalogPage.getCheckCurrentPageTitle());
    }
}