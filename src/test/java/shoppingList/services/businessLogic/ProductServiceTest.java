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
import shoppingList.repository.ProductImpRepository;
import shoppingList.services.validations.ProductValidationService;
import shoppingList.services.validations.exception.DbContainsSimilarProductException;
import shoppingList.services.validations.exception.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductImpRepository productImpRepository;
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
        when(productImpRepository.existsByName(any())).thenReturn(false);
        ProductDto dto = victim.addProductService(productDto(5L));

        verify(validationService).validate(any());
        assertEquals(productDto(1L), dto);
    }

    @Test(expected = DbContainsSimilarProductException.class)
    public void addProductServiceDbContainsSimilarProductException() {
        when(productImpRepository.existsByName(any())).thenReturn(true);
        victim.addProductService(productDto(null));

        verify(validationService).validate(any());
    }

    @Test
    public void removeProductByIDService() {
        when(productImpRepository.existsById(anyLong())).thenReturn(true);

        assertTrue(victim.removeProductByIDService(1L));
    }

    @Test(expected = ProductNotFoundException.class)
    public void removeProductByIDServiceProductNotFoundException() {
        when(productImpRepository.existsById(anyLong())).thenReturn(false);
        victim.removeProductByIDService(1L);
    }

    @Test
    public void listOfAllProductsService() {
        List<ProductDto> products = new ArrayList<>();
        List<ProductEntity> product = new ArrayList<>();
        products.add(productDto(1L));
        product.add(productEntity());

        when(productMapper.productToDto(any())).thenReturn(productDto(1L));
        when(productImpRepository.listOfAllProducts()).thenReturn(product);

        assertEquals(products, victim.listOfAllProductsService());
    }

    @Test
    public void listOfAllProductsServiceEmptyList() {
        when(productImpRepository.listOfAllProducts()).thenReturn(Collections.emptyList());

        assertEquals(Collections.emptyList(), victim.listOfAllProductsService());
    }

    @Test
    public void findProductByID() {
        when(productImpRepository.findProductByID(anyLong())).thenReturn(productEntity());
        when(productMapper.productToDto(any())).thenReturn(productDto(1L));

        assertEquals(productDto(1L), victim.findProductByID(1L));
    }

    @Test(expected = ProductNotFoundException.class)
    public void findProductByIDProductNotFoundException() {
        when(productImpRepository.findProductByID(anyLong())).thenReturn(null);
        victim.findProductByID(1L);
    }

    @Test(expected = ProductNotFoundException.class)
    public void updateProductServiceProductNotFoundException() {
        when(productImpRepository.existsById(anyLong())).thenReturn(false);
        victim.updateProductService(1L, productDto(1L));
    }

    @Test(expected = DbContainsSimilarProductException.class)
    public void updateProductServiceDbContainsSimilarProductException() {
        when(productImpRepository.existsById(anyLong())).thenReturn(true);
        when(productImpRepository.existsByName(any())).thenReturn(true);
        victim.updateProductService(1L, productDto(1L));

        verify(validationService).validate(any());
    }

    @Test
    public void updateProductService() {
        when(productImpRepository.existsById(anyLong())).thenReturn(true);
        when(productImpRepository.existsByName(any())).thenReturn(false);
        when(productMapper.productToDto(any())).thenReturn(productDto(1L));
        ProductDto result = victim.updateProductService(1L, productDto(5L));

        verify(validationService).validate(any());
        assertEquals(productDto(1L), result);
    }

    private ProductEntity productEntity() {
        return new ProductEntity(5L, "Banana pack", BigDecimal.valueOf(22.46), ProductCategory.FRUITS, BigDecimal.valueOf(25.0), "Poland");
    }

    private ProductDto productDto(Long id) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(id);
        productDto.setProductName("Banana pack");
        productDto.setProductRegularPrice(BigDecimal.valueOf(22.46));
        productDto.setProductCategory(ProductCategory.FRUITS);
        productDto.setProductDiscount(BigDecimal.valueOf(25.0));
        productDto.setProductDescription("Poland");
        return productDto;
    }
}