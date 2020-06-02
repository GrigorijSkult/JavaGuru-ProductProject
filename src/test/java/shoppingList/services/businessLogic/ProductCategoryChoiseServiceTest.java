package shoppingList.services.businessLogic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import shoppingList.domain.ProductCategory;
import shoppingList.services.validations.ProductCategoryValidation;
import shoppingList.services.validations.exception.ProductValidationException;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductCategoryChoiseServiceTest {

    private final ProductCategoryChoiseService productCategoryChoiseService = new ProductCategoryChoiseService();

    @Mock
    private ProductCategoryValidation categoryValidation;

    @Test(expected = ProductValidationException.class)
    public void productCategoryIntChoiceProductValidationException() {
        productCategoryChoiseService.productCategoryIntChoice(4221);
        verify(categoryValidation).categoryNumberValidation(anyInt());
    }

    @Test(expected = ProductValidationException.class)
    public void productCategoryIntChoiceProductValidationExceptionII() {
        productCategoryChoiseService.productCategoryIntChoice(-1);
        verify(categoryValidation).categoryNumberValidation(anyInt());
    }

    @Test
    public void productCategoryIntDefault() {
        assertEquals(ProductCategory.NO_DATA, productCategoryChoiseService.productCategoryIntChoice(0));
    }

    @Test
    public void productCategoryIntChoiceMeat() {
        assertEquals(ProductCategory.MEAT_AND_MEAT_PRODUCTS, productCategoryChoiseService.productCategoryIntChoice(1));
    }

    @Test
    public void productCategoryIntChoiceFruits() {
        assertEquals(ProductCategory.FRUITS, productCategoryChoiseService.productCategoryIntChoice(2));
    }

    @Test
    public void productCategoryIntChoiceVegetables() {
        assertEquals(ProductCategory.VEGETABLES, productCategoryChoiseService.productCategoryIntChoice(3));
    }

    @Test
    public void productCategoryIntChoiceSeasoningGoods() {
        assertEquals(ProductCategory.SEASONING_GOODS, productCategoryChoiseService.productCategoryIntChoice(4));
    }

    @Test
    public void productCategoryIntChoiceEdibleFats() {
        assertEquals(ProductCategory.EDIBLE_FATS, productCategoryChoiseService.productCategoryIntChoice(5));
    }

    @Test
    public void productCategoryIntChoiceMilkProducts() {
        assertEquals(ProductCategory.MILK_PRODUCTS, productCategoryChoiseService.productCategoryIntChoice(6));
    }

    @Test
    public void productCategoryIntChoiceGrain() {
        assertEquals(ProductCategory.GRAIN_AND_FLOUR_PRODUCTS, productCategoryChoiseService.productCategoryIntChoice(7));
    }

    @Test
    public void productCategoryIntChoiceFish() {
        assertEquals(ProductCategory.FISH_AND_FISH_PRODUCTS, productCategoryChoiseService.productCategoryIntChoice(8));
    }

    @Test
    public void productCategoryIntChoiceEggs() {
        assertEquals(ProductCategory.EGGS_AND_EGG_PRODUCTS, productCategoryChoiseService.productCategoryIntChoice(9));
    }
}