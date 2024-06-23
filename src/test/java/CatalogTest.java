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
    @Description("Переходим в подкатегорию, видим страницу с минимум одним товаром. " +
            "Хлебные крошки отображаются корректно, " +
            "в каталогах в левой и верхней части страницы текущая подкатегория отображается корректно")

    public void testSubCategorySelect() {

        MainPage mainPage = new MainPage(getDriver());
        waitUntilVisible(getDriver(), mainPage.getCosmeticsDropDownMenu());

        mainPage.hoverOverCosmeticsCategory();

        mainPage.clickHealingCosmetics();

        CatalogPage catalogPage = new CatalogPage(getDriver());
        List<String> actualBreadCrumbs = catalogPage.getBreadCrumbs();

        Assert.assertTrue(catalogPage.getCheckCurrentPageTitle());
        Assert.assertTrue(catalogPage.getItemCard());
        Assert.assertTrue(catalogPage.getSelectedSideMenuCategoryDisplayed());
        Assert.assertEquals(actualBreadCrumbs, EXPECTED_BREADCRUMBS);
        Assert.assertTrue(catalogPage.getCheckSelectedSideMenuCategoryTextAndColor());
        Assert.assertTrue(mainPage.getCheckTopMenuCategoryTextAndColor());

        catalogPage.hoverOverSideMenuCosmeticsCategory();
        Assert.assertTrue(catalogPage.getCheckSelectedSideMenuSubCategoryTextAndColor());

        mainPage.hoverOverCosmeticsCategory();
        Assert.assertTrue(mainPage.getCheckDropDownMenuSelectedSubCategoryTextAndColor());
    }
}