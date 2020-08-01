package shoppingList.product.mappers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import shoppingList.product.domain.ProductCategory;
import shoppingList.product.domain.ProductEntity;
import shoppingList.product.dto.ProductDto;

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
        assertEquals(productDto(5L).getId(), result.getId());
        assertEquals(BigDecimal.valueOf(16.845), result.getActualPrice());
    }

    @Test
    public void productToEntity() {
        ProductEntity result = productMapper.productToEntity(productDto(5L));

        assertEquals(productEntity(), result);
        assertEquals(productEntity().getId(), result.getId());
    }

    private ProductEntity productEntity() {
        return new ProductEntity(5L, "Banana pack", BigDecimal.valueOf(22.46), ProductCategory.FRUITS,
                BigDecimal.valueOf(25.0), "Poland");
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