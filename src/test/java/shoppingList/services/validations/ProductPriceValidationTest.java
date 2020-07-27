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
        productDto.setId(1L);
        productDto.setName("Banana pack");
        productDto.setRegularPrice(BigDecimal.valueOf(price));
        productDto.setCategory(ProductCategory.FRUITS);
        productDto.setDiscount(BigDecimal.valueOf(25.0));
        productDto.setDescription("Poland");
        return productDto;
    }
}