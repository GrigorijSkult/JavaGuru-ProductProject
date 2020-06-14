package shoppingList.services.businessLogic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import shoppingList.domain.ProductCategory;
import shoppingList.services.validations.ProductCategoryValidation;
import shoppingList.services.validations.exception.ProductValidationException;

import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@RunWith(MockitoJUnitRunner.class)
public class ProductCategoryChoiceServiceTest {

    @Mock
    private ProductCategoryValidation categoryValidation;

    @InjectMocks
    private ProductCategoryChoiceService productCategoryChoiceService;

    @Test
    public void productCategoryIntChoiceMoreThanUpperLimitException() {
        assertThatThrownBy(() -> productCategoryChoiceService.productCategoryIntChoice(4221))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product category number is incorrect");
    }

    @Test
    public void productCategoryIntChoiceLessThanLowerLimitException() {
        assertThatThrownBy(() -> productCategoryChoiceService.productCategoryIntChoice(-1))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product category number is incorrect");
    }

    @Test
    public void productCategoryIntDefault() {
        assertEquals(ProductCategory.NO_DATA, productCategoryChoiceService.productCategoryIntChoice(0));
    }

    @Test
    public void productCategoryIntChoiceMeat() {
        assertEquals(ProductCategory.MEAT_AND_MEAT_PRODUCTS, productCategoryChoiceService.productCategoryIntChoice(1));
    }

    @Test
    public void productCategoryIntChoiceFruits() {
        assertEquals(ProductCategory.FRUITS, productCategoryChoiceService.productCategoryIntChoice(2));
    }

    @Test
    public void productCategoryIntChoiceVegetables() {
        assertEquals(ProductCategory.VEGETABLES, productCategoryChoiceService.productCategoryIntChoice(3));
    }

    @Test
    public void productCategoryIntChoiceSeasoningGoods() {
        assertEquals(ProductCategory.SEASONING_GOODS, productCategoryChoiceService.productCategoryIntChoice(4));
    }

    @Test
    public void productCategoryIntChoiceEdibleFats() {
        assertEquals(ProductCategory.EDIBLE_FATS, productCategoryChoiceService.productCategoryIntChoice(5));
    }

    @Test
    public void productCategoryIntChoiceMilkProducts() {
        assertEquals(ProductCategory.MILK_PRODUCTS, productCategoryChoiceService.productCategoryIntChoice(6));
    }

    @Test
    public void productCategoryIntChoiceGrain() {
        assertEquals(ProductCategory.GRAIN_AND_FLOUR_PRODUCTS, productCategoryChoiceService.productCategoryIntChoice(7));
    }

    @Test
    public void productCategoryIntChoiceFish() {
        assertEquals(ProductCategory.FISH_AND_FISH_PRODUCTS, productCategoryChoiceService.productCategoryIntChoice(8));
    }

    @Test
    public void productCategoryIntChoiceEggs() {
        assertEquals(ProductCategory.EGGS_AND_EGG_PRODUCTS, productCategoryChoiceService.productCategoryIntChoice(9));
    }
}