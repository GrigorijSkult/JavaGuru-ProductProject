package shoppingList.repository;

import org.junit.Before;
import org.junit.Test;
import shoppingList.domain.ProductCategory;
import shoppingList.domain.ProductEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ProductImpRepositoryTest {

    private ProductImpRepository repository;

    @Before
    public void initialization() {
        repository = new ProductImpRepository();
    }

    @Test
    public void addProduct() {
        assertEquals(dbProductEntity(), repository.addProduct(newProductEntity()));
    }

    @Test
    public void removeProductByID() {
        repository.addProduct(newProductEntity());
        repository.removeProductByID(1L);

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

        assertEquals(dbProductEntity(), repository.findProductByID(1L));
    }

    @Test
    public void doesDbContainsSimilarProductTrue() {
        repository.addProduct(newProductEntity());

        assertTrue(repository.existsByName(newProductEntity()));
    }

    @Test
    public void doesDbContainsSimilarProductFalse() {
        assertFalse(repository.existsByName(newProductEntity()));
    }

    @Test
    public void doesDbContainsIdTrue() {
        repository.addProduct(newProductEntity());

        assertTrue(repository.existsById(1L));
    }

    @Test
    public void doesDbContainsIdFalse() {
        assertFalse(repository.existsById(2L));
    }

    @Test
    public void updateProduct() {
        repository.addProduct(newProductEntity());
        ProductEntity updatedProduct = new ProductEntity(5L, "Banana", BigDecimal.valueOf(48.46), ProductCategory.MEAT_AND_MEAT_PRODUCTS, BigDecimal.valueOf(20.0), "Latvija");
        ProductEntity result = repository.updateProduct(1L, updatedProduct);

        assertEquals(updatedProduct, result);
    }

    private ProductEntity newProductEntity() {
        return new ProductEntity(0L, "Banana pack", BigDecimal.valueOf(22.46), ProductCategory.FRUITS, BigDecimal.valueOf(25.0), "Poland");
    }

    private ProductEntity dbProductEntity() {
        return new ProductEntity(1L, "Banana pack", BigDecimal.valueOf(22.46), ProductCategory.FRUITS, BigDecimal.valueOf(25.0), "Poland");
    }
}