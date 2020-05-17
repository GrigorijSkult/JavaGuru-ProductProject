package shoppingList.UI;

import shoppingList.businessLogic.services.ProductService;

public class ListOfAllProductsUI {
    ProductService productService;

    public ListOfAllProductsUI(ProductService productService) {
        this.productService = productService;
    }

    public void ListOfAllProducts() {
        System.out.println(productService.listOfAllProductsService());
//        System.out.println("End of the product list;"); // is it necessary ?
    }
}
