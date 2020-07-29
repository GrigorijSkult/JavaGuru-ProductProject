package shoppingList.services.validations;

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
import shoppingList.services.validations.exception.ProductValidationException;
import shoppingList.services.validations.nameValidation.ProductNameValidation;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductNameValidationTest {

    @Mock
    private InMemoryProductImpRepository inMemoryProductImpRepository;
    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductNameValidation victim;

    @Test
    public void validateExceptionLessThanLowerLimit() {
        when(productMapper.productToEntity(any())).thenReturn(productEntity(1L));

        assertThatThrownBy(() -> victim.validate(productDto("Na")))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("[Product name cannot be less than 3 characters and more than 32]");
    }

    @Test
    public void validateLowerLimitAllowed() {
        when(productMapper.productToEntity(any())).thenReturn(productEntity(1L));

        victim.validate(productDto("Nam"));
    }

    @Test
    public void validateMiddleLimitAllowed() {
        when(productMapper.productToEntity(any())).thenReturn(productEntity(1L));

        victim.validate(productDto("qwertyuiopasdfgh"));
    }

    @Test
    public void validateUpperLimitAllowed() {
        when(productMapper.productToEntity(any())).thenReturn(productEntity(1L));

        victim.validate(productDto("qwertyuiopasdfghjklzxcvbnmqqqqqq"));
    }

    @Test
    public void validateExceptionMoreThanUpperLimit() {
        when(productMapper.productToEntity(any())).thenReturn(productEntity(1L));

        assertThatThrownBy(() -> victim.validate(productDto("qwertyuiopasdfghjklzxcvbnmqqqqqqq")))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("[Product name cannot be less than 3 characters and more than 32]");
    }

    @Test
    public void validateExceptionNotUniqueName() {
        when(productMapper.productToEntity(any())).thenReturn(productEntity(1L));
        when(inMemoryProductImpRepository.findProductByName(anyString())).thenReturn(Optional.of(productEntity(2L)));

        assertThatThrownBy(() -> victim.validate(productDto("Potato")))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("[Product name should be unique]");
    }

    @Test
    public void validateExceptionNotUniqueNameAndMoreThanUpperLimit() {
        when(productMapper.productToEntity(any())).thenReturn(productEntity(1L));
        when(inMemoryProductImpRepository.findProductByName(anyString())).thenReturn(Optional.of(productEntity(2L)));

        assertThatThrownBy(() -> victim.validate(productDto("qwertyuiopasdfghjklzxcvbnmqqqqqqq")))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("[Product name cannot be less than 3 characters and more than 32, Product name should be unique]");
    }

    private ProductDto productDto(String name) {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName(name);
        productDto.setRegularPrice(BigDecimal.valueOf(22.46));
        productDto.setCategory(ProductCategory.FRUITS);
        productDto.setDiscount(BigDecimal.valueOf(25.0));
        productDto.setDescription("Poland");
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