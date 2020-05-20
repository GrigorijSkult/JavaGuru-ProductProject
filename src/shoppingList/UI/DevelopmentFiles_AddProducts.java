package shoppingList.UI;

import shoppingList.businessLogic.services.ProductService;
import shoppingList.domain.Product;
import shoppingList.domain.ProductCategory;

import java.math.BigDecimal;

public class DevelopmentFiles_AddProducts {
    ProductService productService;

    public DevelopmentFiles_AddProducts(ProductService productService) {
        this.productService = productService;
    }

    public void test(){
        Product testProduct = new Product("Banana", BigDecimal.valueOf(2.05), ProductCategory.MILK_PRODUCTS,  new BigDecimal(0.00), null);
        Product testProductTwo = new Product("Banana", BigDecimal.valueOf(1.00), ProductCategory.MILK_PRODUCTS,  new BigDecimal(0.00), null);
        Product testProductThree = new Product("Mango", BigDecimal.valueOf(30.00), ProductCategory.FRUITS,  new BigDecimal(50.00), null);
        productService.addProductService(testProduct);
        productService.addProductService(testProductTwo);
        productService.addProductService(testProductThree);
    }
}
