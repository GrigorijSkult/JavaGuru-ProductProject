package shoppingList.services.validations;

import org.junit.Before;
import org.junit.Test;
import shoppingList.domain.ProductCategory;
import shoppingList.dto.ProductDto;
import shoppingList.services.validations.exception.ProductValidationException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProductValidationServiceTest {

    private final List<ValidationRule<ProductDto>> validationRules = new ArrayList<>();
    private final ProductValidationService productValidationService = new ProductValidationService(validationRules);

    @Before
    public void initialization() {
        validationRules.add(new ProductNameValidation());
        validationRules.add(new ProductPriceValidation());
        validationRules.add(new ProductCategoryValidation());
        validationRules.add(new ProductDiscountValidation());
        validationRules.add(new ProductDescriptionValidation());
    }

    @Test
    public void validateCorrect() {
        productValidationService.validate(productDto());
    }

    @Test
    public void validateNameExceptions() {
        List<String> actualErrorLogs = new ArrayList<>();
        try {
            productValidationService.validate(productDtoIncorrect());
        } catch (ProductValidationException e) {
            actualErrorLogs.add(e.getMessage());
        }
        assertEquals("[" + expectedErrorLogs() + "]", actualErrorLogs.toString());
    }

    private List<String> expectedErrorLogs() {
        List<String> expectedErrorLogs = new ArrayList<>();
        expectedErrorLogs.add(new ProductNameValidation().errorMassage);
        expectedErrorLogs.add(new ProductPriceValidation().errorMessage);
        expectedErrorLogs.add(new ProductDiscountValidation().discountRangeErrorMessage);
        expectedErrorLogs.add(new ProductDiscountValidation().discountPriceErrorMessage);
        expectedErrorLogs.add(new ProductDescriptionValidation().errorMessage);
        return expectedErrorLogs;
    }

    private ProductDto productDto() {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(1L);
        productDto.setProductName("Banana package");
        productDto.setProductRegularPrice(BigDecimal.valueOf(22.46));
        productDto.setProductCategory(ProductCategory.FRUITS);
        productDto.setProductDiscount(BigDecimal.valueOf(25.0));
        productDto.setProductDescription("Poland");
        return productDto;
    }

    private ProductDto productDtoIncorrect() {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(1L);
        productDto.setProductName("B");
        productDto.setProductRegularPrice(BigDecimal.valueOf(-1.2));
        productDto.setProductCategory(ProductCategory.FRUITS);
        productDto.setProductDiscount(BigDecimal.valueOf(125.0));
        productDto.setProductDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum " +
                "has been the industry's standard dummy text.");
        return productDto;
    }
}