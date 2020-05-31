package shoppingList.services.validations;

import org.junit.Test;
import shoppingList.domain.ProductCategory;
import shoppingList.dto.ProductDto;
import shoppingList.services.validations.exception.ProductValidationException;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductDiscountValidationTest {

    private final ProductDiscountValidation discountValidation = new ProductDiscountValidation();

    @Test(expected = ProductValidationException.class)
    public void validateExceptionLessThanLowerLimit() {
        discountValidation.validate(productDto(20.00, -0.01));
    }

    @Test
    public void validateCorrect() {
        discountValidation.validate(productDto(21.00, 25.00));
    }

    @Test(expected = ProductValidationException.class)
    public void validateExceptionMoreThanUpperLimit() {
        discountValidation.validate(productDto(20.00, 100.01));
    }

    @Test(expected = ProductValidationException.class)
    public void validateExceptionProductPrice() {
        discountValidation.validate(productDto(19.85, 25.05));
    }

    private ProductDto productDto(Double price, Double discount) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(1L);
        productDto.setProductName("Banana pack");
        productDto.setProductRegularPrice(BigDecimal.valueOf(price));
        productDto.setProductCategory(ProductCategory.FRUITS);
        productDto.setProductDiscount(BigDecimal.valueOf(discount));
        productDto.setProductDescription("Poland");
        productDto.setProductActualPrice();
        return productDto;
    }
}