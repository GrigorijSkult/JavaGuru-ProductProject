package shoppingList.mappers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import shoppingList.domain.ProductCategory;
import shoppingList.domain.ProductEntity;
import shoppingList.dto.ProductDto;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ProductMapperTest {

    @InjectMocks
    private ProductMapper productMapper;

    @Test
    public void productToDto() {
        ProductDto result = productMapper.productToDto(productEntity());

        assertEquals(productDto(5L), result);
        assertEquals(productDto(5L).getProductId(), result.getProductId());
        assertEquals(BigDecimal.valueOf(16.845), result.getProductActualPrice());
    }

    @Test
    public void productToEntity() {
        ProductEntity result = productMapper.productToEntity(productDto(5L));

        assertEquals(productEntity(), result);
        assertEquals(productEntity().getProductId(), result.getProductId());
    }

    private ProductEntity productEntity() {
        return new ProductEntity(5L, "Banana pack", BigDecimal.valueOf(22.46), ProductCategory.FRUITS,
                BigDecimal.valueOf(25.0), "Poland");
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