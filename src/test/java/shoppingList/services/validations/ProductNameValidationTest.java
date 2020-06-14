package shoppingList.services.validations;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import shoppingList.domain.ProductCategory;
import shoppingList.dto.ProductDto;
import shoppingList.mappers.ProductMapper;
import shoppingList.repository.ProductImpRepository;
import shoppingList.services.validations.exception.ProductValidationException;
import shoppingList.services.validations.nameValidation.ProductNameValidation;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductNameValidationTest {

    @Mock
    private ProductImpRepository productImpRepository;
    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductNameValidation victim;

    @Test
    public void validateExceptionLessThanLowerLimit() {
        assertThatThrownBy(() -> victim.validate(productDto("Na")))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("[Product name cannot be less than 3 characters and more than 32]");
    }

    @Test
    public void validateLowerLimitAllowed() {
        victim.validate(productDto("Nam"));
    }

    @Test
    public void validateMiddleLimitAllowed() {
        victim.validate(productDto("qwertyuiopasdfgh"));
    }

    @Test
    public void validateUpperLimitAllowed() {
        victim.validate(productDto("qwertyuiopasdfghjklzxcvbnmqqqqqq"));
    }

    @Test
    public void validateExceptionMoreThanUpperLimit() {
        assertThatThrownBy(() -> victim.validate(productDto("qwertyuiopasdfghjklzxcvbnmqqqqqqq")))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("[Product name cannot be less than 3 characters and more than 32]");
    }

    @Test
    public void validateExceptionNotUniqueName() {
        when(productImpRepository.existsByName(any())).thenReturn(true);
        assertThatThrownBy(() -> victim.validate(productDto("Potato")))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("[Product name should be unique]");
    }

    @Test
    public void validateExceptionNotUniqueNameAndMoreThanUpperLimit() {
        when(productImpRepository.existsByName(any())).thenReturn(true);
        assertThatThrownBy(() -> victim.validate(productDto("qwertyuiopasdfghjklzxcvbnmqqqqqqq")))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("[Product name cannot be less than 3 characters and more than 32, Product name should be unique]");
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