package shoppingList.console;

import shoppingList.dto.ProductDto;
import shoppingList.services.businessLogic.ProductService;

public class ListOfAllProductsUI implements UserInterfaceUnit {

    private final ProductService productService;

    public ListOfAllProductsUI(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        for (ProductDto productDto : productService.listOfAllProductsService()) {
            System.out.println(productDto);
        }
    }

    @Override
    public String toString() {
        return "Show all products in the DB;";
    }
}
