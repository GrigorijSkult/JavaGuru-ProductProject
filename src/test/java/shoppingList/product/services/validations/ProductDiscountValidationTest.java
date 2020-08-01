package shoppingList.product.services.validations;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import shoppingList.product.domain.ProductCategory;
import shoppingList.product.dto.ProductDto;
import shoppingList.product.services.validations.discountValidation.ProductDiscountValidation;
import shoppingList.product.services.validations.exception.ProductValidationException;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@RunWith(MockitoJUnitRunner.class)
public class ProductDiscountValidationTest {

    @InjectMocks
    private ProductDiscountValidation discountValidation;

    @Test
    public void validateExceptionLessThanLowerLimit() {
        assertThatThrownBy(() -> discountValidation.validate(productDto(20.00, -0.01)))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("[Product discount value must be in the range from 0.0 to 100]");
    }

    @Test
    public void validateCorrect() {
        discountValidation.validate(productDto(21.00, 25.00));
    }

    @Test
    public void validateExceptionMoreThanUpperLimit() {
        assertThatThrownBy(() -> discountValidation.validate(productDto(20.00, 100.01)))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("[Product discount value must be in the range from 0.0 to 100]");
    }

    @Test
    public void validateExceptionProductPrice() {
        assertThatThrownBy(() -> discountValidation.validate(productDto(19.85, 25.05)))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("[A discount can be set for products with a price greater than 20.0]");
    }

    private ProductDto productDto(Double price, Double discount) {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Banana pack");
        productDto.setRegularPrice(BigDecimal.valueOf(price));
        productDto.setCategory(ProductCategory.FRUITS);
        productDto.setDiscount(BigDecimal.valueOf(discount));
        productDto.setDescription("Poland");
        return productDto;
    }
}