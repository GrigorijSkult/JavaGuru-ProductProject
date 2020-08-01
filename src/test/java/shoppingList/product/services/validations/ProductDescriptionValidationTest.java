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
public class ProductDescriptionValidationTest {

    @InjectMocks
    private ProductDescriptionValidation victim;

    @Test
    public void validateCorrect() {
        victim.validate(productDto("Poland"));
    }

    @Test
    public void validateMiddleLimitAllowed() {
        victim.validate(productDto("Lorem Ipsum is simply dummy text of the"));
    }

    @Test
    public void validateUpperLimitAllowed() {
        victim.validate(productDto("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the"));
    }

    @Test
    public void validateExceptionMoreThanUpperLimit() {
        assertThatThrownBy(() -> victim.validate(productDto("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text.")))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product`s description should be shorter then 100 symbols");

    }

    private ProductDto productDto(String description) {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Banana pack");
        productDto.setRegularPrice(BigDecimal.valueOf(22.46));
        productDto.setCategory(ProductCategory.FRUITS);
        productDto.setDiscount(BigDecimal.valueOf(25.0));
        productDto.setDescription(description);
        return productDto;
    }
}