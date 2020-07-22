package shoppingList.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import shoppingList.domain.ProductCategory;
import shoppingList.domain.ProductEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductImpProductRepositoryTest {

    @InjectMocks
    private ProductImpRepository repository;

    @Test
    public void addProduct() {
        assertEquals(dbProductEntity(), repository.addProduct(newProductEntity()));
    }

    @Test
    public void removeProductByID() {
        repository.addProduct(newProductEntity());
        Optional<ProductEntity> product = repository.removeProductByID(1L);

        assertEquals(dbProductEntity(), product.get());
        assertFalse(repository.existsByName(dbProductEntity()));
    }

    @Test
    public void listOfAllProducts() {
        ProductEntity apple = new ProductEntity(0L, "Banana pack", BigDecimal.valueOf(22.46), ProductCategory.FRUITS, BigDecimal.valueOf(25.0), "Poland");
        repository.addProduct(newProductEntity());
        repository.addProduct(apple);

        List<ProductEntity> entityList = new ArrayList<>();
        entityList.add(dbProductEntity());
        entityList.add(apple);

        assertEquals(entityList, repository.listOfAllProducts());
    }

    @Test
    public void findProductByID() {
        repository.addProduct(newProductEntity());

        assertEquals(dbProductEntity(), repository.findProductByID(1L).get());
    }

    @Test
    public void updateProduct() {
        repository.addProduct(newProductEntity());
        ProductEntity updatedProduct = new ProductEntity(5L, "Banana", BigDecimal.valueOf(48.46), ProductCategory.MEAT_AND_MEAT_PRODUCTS, BigDecimal.valueOf(20.0), "Latvija");
        Optional<ProductEntity> result = repository.updateProduct(1L, updatedProduct);

        assertEquals(updatedProduct, result.get());
    }

    @Test
    public void existsByNameTrue() {
        repository.addProduct(newProductEntity());

        assertTrue(repository.existsByName(newProductEntity()));
    }

    @Test
    public void existsByNameFalse() {
        assertFalse(repository.existsByName(newProductEntity()));
    }

    @Test
    public void existsByIdTrue() {
        repository.addProduct(newProductEntity());

        assertTrue(repository.existsById(1L));
    }

    @Test
    public void existsByIdFalse() {
        assertFalse(repository.existsById(2L));
    }

    private ProductEntity newProductEntity() {
        return new ProductEntity(0L, "Banana pack", BigDecimal.valueOf(22.46), ProductCategory.FRUITS, BigDecimal.valueOf(25.0), "Poland");
    }

    private ProductEntity dbProductEntity() {
        return new ProductEntity(1L, "Banana pack", BigDecimal.valueOf(22.46), ProductCategory.FRUITS, BigDecimal.valueOf(25.0), "Poland");
    }
}