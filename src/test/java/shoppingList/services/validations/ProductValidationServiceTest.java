package shoppingList.services.validations;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import shoppingList.domain.ProductCategory;
import shoppingList.domain.ProductEntity;
import shoppingList.dto.ProductDto;
import shoppingList.mappers.ProductMapper;
import shoppingList.repository.InMemoryProductImpRepository;
import shoppingList.services.validations.discountValidation.ProductDiscountValidation;
import shoppingList.services.validations.exception.ProductValidationException;
import shoppingList.services.validations.nameValidation.ProductNameValidation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductValidationServiceTest {

    private final List<ValidationRule<ProductDto>> validationRules = new ArrayList<>();
    @InjectMocks
    private final ProductValidationService productValidationService = new ProductValidationService(validationRules);
    @Mock
    private InMemoryProductImpRepository inMemoryProductImpRepository;
    @Mock
    private ProductMapper productMapper;

    @Before
    public void initialization() {
        validationRules.add(new ProductNameValidation(inMemoryProductImpRepository, productMapper));
        validationRules.add(new ProductPriceValidation());
        validationRules.add(new ProductCategoryValidation());
        validationRules.add(new ProductDiscountValidation());
        validationRules.add(new ProductDescriptionValidation());
    }

    @Test
    public void validateCorrect() {
        when(productMapper.productToEntity(any())).thenReturn(productEntity(1L));

        productValidationService.validate(productDto());
    }

    @Test
    public void validateMassagesExceptions() {
        when(productMapper.productToEntity(any())).thenReturn(productEntity(1L));
        when(inMemoryProductImpRepository.findProductByName(anyString())).thenReturn(Optional.of(productEntity(2L)));

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
        productDto.setId(1L);
        productDto.setName("Banana package");
        productDto.setRegularPrice(BigDecimal.valueOf(22.46));
        productDto.setCategory(ProductCategory.FRUITS);
        productDto.setDiscount(BigDecimal.valueOf(25.0));
        productDto.setDescription("Poland");
        return productDto;
    }

    private ProductDto productDtoIncorrect() {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("B");
        productDto.setRegularPrice(BigDecimal.valueOf(-1.2));
        productDto.setCategory(ProductCategory.FRUITS);
        productDto.setDiscount(BigDecimal.valueOf(125.0));
        productDto.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum " +
                "has been the industry's standard dummy text.");
        return productDto;
    }

    private ProductEntity productEntity(Long id) {
        ProductEntity productDto = new ProductEntity();
        productDto.setId(id);
        productDto.setName("Bananas");
        productDto.setRegularPrice(BigDecimal.valueOf(22.46));
        productDto.setCategory(ProductCategory.FRUITS);
        productDto.setDiscount(BigDecimal.valueOf(25.0));
        productDto.setDescription("Poland");
        return productDto;
    }
}