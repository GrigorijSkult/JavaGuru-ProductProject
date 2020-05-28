package shoppingList.console;

import shoppingList.services.businessLogic.ProductService;
import shoppingList.domain.Product;

public class ListOfAllProductsUI {
    ProductService productService;

    public ListOfAllProductsUI(ProductService productService) {
        this.productService = productService;
    }

    public void listOfAllProducts() {
        for (Product product : productService.listOfAllProductsService()) {
            System.out.println(product);
        }
    }
}
