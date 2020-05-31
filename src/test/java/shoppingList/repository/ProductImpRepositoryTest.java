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

        assertFalse(repository.doesDbContainsSimilarProduct(dbProductEntity()));
    }

    @Test
    public void listOfAllProducts() {
        ProductEntity apple = new ProductEntity(0L, "Apple", BigDecimal.valueOf(0.25), ProductCategory.FRUITS);
        repository.addProduct(newProductEntity());
        repository.addProduct(apple);

        List<ProductEntity> entityList = new ArrayList<>();
        entityList.add(dbProductEntity());
        apple.setProductId(2L);
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

        assertTrue(repository.doesDbContainsSimilarProduct(newProductEntity()));
    }

    @Test
    public void doesDbContainsSimilarProductFalse() {
        assertFalse(repository.doesDbContainsSimilarProduct(newProductEntity()));
    }

    @Test
    public void doesDbContainsIdTrue() {
        repository.addProduct(newProductEntity());

        assertTrue(repository.doesDbContainsId(1L));
    }

    @Test
    public void doesDbContainsIdFalse() {
        assertFalse(repository.doesDbContainsId(2L));
    }

    private ProductEntity newProductEntity() {
        return new ProductEntity(0L, "Banana pack", BigDecimal.valueOf(22.46), ProductCategory.FRUITS, BigDecimal.valueOf(25.0), "Poland");
    }

    private ProductEntity dbProductEntity() {
        return new ProductEntity(1L, "Banana pack", BigDecimal.valueOf(22.46), ProductCategory.FRUITS, BigDecimal.valueOf(25.0), "Poland");
    }
}