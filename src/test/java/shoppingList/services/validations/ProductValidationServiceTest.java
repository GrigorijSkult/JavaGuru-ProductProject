package shoppingList.services.validations;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import shoppingList.domain.ProductCategory;
import shoppingList.dto.ProductDto;
import shoppingList.mappers.ProductMapper;
import shoppingList.repository.ProductImpRepository;
import shoppingList.services.validations.discountValidation.ProductDiscountValidation;
import shoppingList.services.validations.exception.ProductValidationException;
import shoppingList.services.validations.nameValidation.ProductNameValidation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductValidationServiceTest {

    @Mock
    private ProductImpRepository productImpRepository;
    @Mock
    private ProductMapper productMapper;

    private final List<ValidationRule<ProductDto>> validationRules = new ArrayList<>();

    @InjectMocks
    private final ProductValidationService productValidationService = new ProductValidationService(validationRules);

    @Before
    public void initialization() {
        validationRules.add(new ProductNameValidation(productImpRepository,productMapper));
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
    public void validateMassagesExceptions() {
        when(productImpRepository.existsByName(any())).thenReturn(true);

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
        expectedErrorLogs.add("[Product name cannot be less than 3 characters and more than 32");
        expectedErrorLogs.add("Product name should be unique]");
        expectedErrorLogs.add("Product price must be greater than 0");
        expectedErrorLogs.add("[Product discount value must be in the range from 0 to 100");
        expectedErrorLogs.add("A discount can be set for products with a price greater than 20.00]");
        expectedErrorLogs.add("Product`s description should be shorter then 100 symbols");
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