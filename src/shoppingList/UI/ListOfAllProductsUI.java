package shoppingList.UI;

import shoppingList.businessLogic.services.ProductService;
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
