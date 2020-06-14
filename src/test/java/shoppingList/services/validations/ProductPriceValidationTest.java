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
public class ProductPriceValidationTest {

    @InjectMocks
    private ProductPriceValidation victim;

    @Test
    public void validateExceptionLessThanLowerLimit() {
        assertThatThrownBy(() -> victim.validate(productDto(-0.01)))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product price must be greater than 0");
    }

    @Test
    public void validateLowerLimitAllowed() {
        victim.validate(productDto(0.01));
    }

    private ProductDto productDto(Double price) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(1L);
        productDto.setProductName("Banana pack");
        productDto.setProductRegularPrice(BigDecimal.valueOf(price));
        productDto.setProductCategory(ProductCategory.FRUITS);
        productDto.setProductDiscount(BigDecimal.valueOf(25.0));
        productDto.setProductDescription("Poland");
        return productDto;
    }
}