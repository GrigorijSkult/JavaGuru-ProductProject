package shoppingList.services.businessLogic;

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
import shoppingList.services.validations.ProductValidationService;
import shoppingList.services.validations.exception.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private InMemoryProductImpRepository inMemoryProductImpRepository;
    @Mock
    private ProductValidationService validationService;
    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService victim;

    @Test
    public void addProductService() {
        when(productMapper.productToEntity(any())).thenReturn(productEntity());
        when(productMapper.productToDto(any())).thenReturn(productDto(1L));
        ProductDto dto = victim.addProductService(productDto(5L));

        verify(validationService).validate(any());
        assertEquals(productDto(1L), dto);
    }

    @Test
    public void removeProductByIDService() {
        when(inMemoryProductImpRepository.existsById(anyLong())).thenReturn(true);

        assertTrue(victim.removeProductByIDService(1L));
    }

    @Test
    public void removeProductByIDServiceProductNotFoundException() {
        when(inMemoryProductImpRepository.existsById(anyLong())).thenReturn(false);

        assertThatThrownBy(() -> victim.removeProductByIDService(1L))
                .isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    public void listOfAllProductsService() {
        List<ProductDto> products = new ArrayList<>();
        List<ProductEntity> product = new ArrayList<>();
        products.add(productDto(1L));
        product.add(productEntity());

        when(productMapper.productToDto(any())).thenReturn(productDto(1L));
        when(inMemoryProductImpRepository.listOfAllProducts()).thenReturn(product);

        assertEquals(products, victim.listOfAllProductsService());
    }

    @Test
    public void listOfAllProductsServiceEmptyList() {
        when(inMemoryProductImpRepository.listOfAllProducts()).thenReturn(Collections.emptyList());

        assertEquals(Collections.emptyList(), victim.listOfAllProductsService());
    }

    @Test
    public void findProductByID() {
        when(inMemoryProductImpRepository.findProductByID(anyLong())).thenReturn(Optional.of(productEntity()));
        when(productMapper.productToDto(any())).thenReturn(productDto(1L));

        assertEquals(productDto(1L), victim.findProductByID(1L));
    }

    @Test
    public void findProductByIDProductNotFoundException() {
        when(inMemoryProductImpRepository.findProductByID(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> victim.findProductByID(1L))
                .isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    public void updateProductService() {
        when(inMemoryProductImpRepository.existsById(anyLong())).thenReturn(true);
        when(productMapper.productToEntity(any())).thenReturn(productEntity());
        when(inMemoryProductImpRepository.updateProduct(anyLong(), any())).thenReturn(productEntity());
        when(productMapper.productToDto(any())).thenReturn(productDto(1L));
        ProductDto result = victim.updateProductService(1L, productDto(5L));

        verify(validationService).validate(any());
        assertEquals(productDto(1L), result);
    }

    @Test
    public void updateProductServiceProductNotFoundException() {
        when(inMemoryProductImpRepository.existsById(anyLong())).thenReturn(false);

        assertThatThrownBy(() -> victim.updateProductService(1L, productDto(1L)))
                .isInstanceOf(ProductNotFoundException.class);
    }

    private ProductEntity productEntity() {
        return new ProductEntity(5L, "Banana pack", BigDecimal.valueOf(22.46), ProductCategory.FRUITS, BigDecimal.valueOf(25.0), "Poland");
    }

    private ProductDto productDto(Long id) {
        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        productDto.setName("Banana pack");
        productDto.setRegularPrice(BigDecimal.valueOf(22.46));
        productDto.setCategory(ProductCategory.FRUITS);
        productDto.setDiscount(BigDecimal.valueOf(25.0));
        productDto.setDescription("Poland");
        return productDto;
    }
}