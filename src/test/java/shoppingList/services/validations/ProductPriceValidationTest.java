package shoppingList.services.validations;

import org.junit.Test;
import shoppingList.domain.ProductCategory;
import shoppingList.dto.ProductDto;
import shoppingList.services.validations.exception.ProductValidationException;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductPriceValidationTest {

    private final ProductPriceValidation productPriceValidation = new ProductPriceValidation();

    @Test(expected = ProductValidationException.class)
    public void validateExceptionLessThanLowerLimit() {
        productPriceValidation.validate(productDto(-0.01));
    }

    @Test
    public void validateLowerLimitAllowed() {
        productPriceValidation.validate(productDto(0.01));
    }

    private ProductDto productDto(Double price) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(1L);
        productDto.setProductName("Banana pack");
        productDto.setProductRegularPrice(BigDecimal.valueOf(price));
        productDto.setProductCategory(ProductCategory.FRUITS);
        productDto.setProductDiscount(BigDecimal.valueOf(25.0));
        productDto.setProductDescription("Poland");
        productDto.setProductActualPrice();
        return productDto;
    }
}