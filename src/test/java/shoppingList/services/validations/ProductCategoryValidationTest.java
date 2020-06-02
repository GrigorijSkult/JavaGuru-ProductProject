package shoppingList.services.validations;

import org.junit.Test;
import shoppingList.domain.ProductCategory;
import shoppingList.dto.ProductDto;
import shoppingList.services.validations.exception.ProductValidationException;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductCategoryValidationTest {

    private final ProductCategoryValidation productCategoryValidation = new ProductCategoryValidation();

    @Test
    public void validateCorrect() {
        productCategoryValidation.validate(productDto());
    }

    @Test(expected = ProductValidationException.class)
    public void categoryNumberValidationExceptionLessThanLowerLimit() {
        productCategoryValidation.categoryNumberValidation(-1);
    }

    @Test
    public void categoryNumberValidationLowerAllowedLimit() {
        productCategoryValidation.categoryNumberValidation(1);
    }

    @Test
    public void categoryNumberValidationMiddleAllowedLimit() {
        productCategoryValidation.categoryNumberValidation((ProductCategory.values().length - 1) / 2);
    }

    @Test
    public void categoryNumberValidationUpperAllowedLimit() {
        productCategoryValidation.categoryNumberValidation(ProductCategory.values().length - 1);
    }

    @Test(expected = ProductValidationException.class)
    public void categoryNumberValidationExceptionMoreThanUpperLimit() {
        productCategoryValidation.categoryNumberValidation(ProductCategory.values().length);
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