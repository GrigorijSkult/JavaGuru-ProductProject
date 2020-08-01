package shoppingList.product.services.validations;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import shoppingList.product.domain.ProductCategory;
import shoppingList.product.dto.ProductDto;
import shoppingList.product.services.validations.exception.ProductValidationException;

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
        productDto.setId(1L);
        productDto.setName("Banana pack");
        productDto.setRegularPrice(BigDecimal.valueOf(22.46));
        productDto.setCategory(ProductCategory.FRUITS);
        productDto.setDiscount(BigDecimal.valueOf(25.0));
        productDto.setDescription("Poland");
        return productDto;
    }
}