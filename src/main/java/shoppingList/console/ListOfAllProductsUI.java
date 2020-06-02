package shoppingList.console;

import shoppingList.dto.ProductDto;
import shoppingList.services.businessLogic.ProductService;

public class ListOfAllProductsUI {
    ProductService productService;

    public ListOfAllProductsUI(ProductService productService) {
        this.productService = productService;
    }

    public void listOfAllProducts() {
        for (ProductDto productDto : productService.listOfAllProductsService()) {
            System.out.println(productDto);
        }
    }
}
