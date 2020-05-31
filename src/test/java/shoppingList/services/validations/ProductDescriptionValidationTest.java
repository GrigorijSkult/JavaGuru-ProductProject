package shoppingList.services.validations;

import org.junit.Test;
import shoppingList.domain.ProductCategory;
import shoppingList.dto.ProductDto;
import shoppingList.services.validations.exception.ProductValidationException;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductDescriptionValidationTest {

    private final ProductDescriptionValidation descriptionValidation = new ProductDescriptionValidation();

    @Test
    public void validateCorrect() {
        descriptionValidation.validate(productDto("Poland"));
    }

    @Test
    public void validateMiddleLimitAllowed() {
        descriptionValidation.validate(productDto("Lorem Ipsum is simply dummy text of the"));
    }

    @Test
    public void validateUpperLimitAllowed() {
        descriptionValidation.validate(productDto("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the"));
    }

    @Test(expected = ProductValidationException.class)
    public void validateExceptionMoreThanUpperLimit() {
        descriptionValidation.validate(productDto("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text."));
    }

    private ProductDto productDto(String description) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(1L);
        productDto.setProductName("Banana pack");
        productDto.setProductRegularPrice(BigDecimal.valueOf(22.46));
        productDto.setProductCategory(ProductCategory.FRUITS);
        productDto.setProductDiscount(BigDecimal.valueOf(25.0));
        productDto.setProductDescription(description);
        productDto.setProductActualPrice();
        return productDto;
    }
}