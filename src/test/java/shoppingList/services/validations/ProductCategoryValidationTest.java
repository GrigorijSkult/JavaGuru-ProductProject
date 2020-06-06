package shoppingList.services.validations;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import shoppingList.domain.ProductCategory;
import shoppingList.dto.ProductDto;
import shoppingList.services.validations.exception.ProductValidationException;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@RunWith(MockitoJUnitRunner.class)
public class ProductCategoryValidationTest {

    @InjectMocks
    private ProductCategoryValidation victim;

    @Test
    public void validateCorrect() {
        victim.validate(productDto());
    }

    @Test
    public void categoryNumberValidationExceptionLessThanLowerLimit() {
        assertThatThrownBy(() -> victim.categoryNumberValidation(-1))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product category number is incorrect");
    }

    @Test
    public void categoryNumberValidationLowerAllowedLimit() {
        victim.categoryNumberValidation(1);
    }

    @Test
    public void categoryNumberValidationMiddleAllowedLimit() {
        victim.categoryNumberValidation((ProductCategory.values().length - 1) / 2);
    }

    @Test
    public void categoryNumberValidationUpperAllowedLimit() {
        victim.categoryNumberValidation(ProductCategory.values().length - 1);
    }

    @Test
    public void categoryNumberValidationExceptionMoreThanUpperLimit() {
        assertThatThrownBy(() -> victim.categoryNumberValidation(ProductCategory.values().length))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product category number is incorrect");
    }

    private ProductDto productDto() {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(1L);
        productDto.setProductName("Banana pack");
        productDto.setProductRegularPrice(BigDecimal.valueOf(22.46));
        productDto.setProductCategory(ProductCategory.FRUITS);
        productDto.setProductDiscount(BigDecimal.valueOf(25.0));
        productDto.setProductDescription("Poland");
        return productDto;
    }
}