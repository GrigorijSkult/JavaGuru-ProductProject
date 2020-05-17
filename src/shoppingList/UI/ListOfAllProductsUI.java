package shoppingList.UI;

import shoppingList.businessLogic.services.ProductService;
import shoppingList.domain.Product;

public class ListOfAllProductsUI {
    ProductService productService;

    public ListOfAllProductsUI(ProductService productService) {
        this.productService = productService;
    }

    public void ListOfAllProducts() {
        for (Product product : productService.listOfAllProductsService()) {
            System.out.println(product);
        }
//        System.out.println("End of the product list;"); // is it necessary ?
    }
}
