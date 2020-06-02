package shoppingList.services.validations;

import org.junit.Test;
import shoppingList.domain.ProductCategory;
import shoppingList.dto.ProductDto;
import shoppingList.services.validations.exception.ProductValidationException;

import java.math.BigDecimal;


public class ProductNameValidationTest {

    private final ProductNameValidation productNameValidation = new ProductNameValidation();

    @Test(expected = ProductValidationException.class)
    public void validateExceptionLessThanLowerLimit() {
        productNameValidation.validate(productDto("Na"));
    }

    @Test
    public void validateLowerLimitAllowed() {
        productNameValidation.validate(productDto("Nam"));
    }

    @Test
    public void validateMiddleLimitAllowed() {
        productNameValidation.validate(productDto("qwertyuiopasdfgh"));
    }

    @Test
    public void validateUpperLimitAllowed() {
        productNameValidation.validate(productDto("qwertyuiopasdfghjklzxcvbnmqqqqqq"));
    }

    @Test(expected = ProductValidationException.class)
    public void validateExceptionMoreThanUpperLimit() {
        productNameValidation.validate(productDto("qwertyuiopasdfghjklzxcvbnmqqqqqqq"));
    }

    private ProductDto productDto(String name) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(1L);
        productDto.setProductName(name);
        productDto.setProductRegularPrice(BigDecimal.valueOf(22.46));
        productDto.setProductCategory(ProductCategory.FRUITS);
        productDto.setProductDiscount(BigDecimal.valueOf(25.0));
        productDto.setProductDescription("Poland");
        return productDto;
    }
}