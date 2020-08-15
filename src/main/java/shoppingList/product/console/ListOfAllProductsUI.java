package shoppingList.product.console;

import org.springframework.stereotype.Component;
import shoppingList.console.UserInterfaceUnit;
import shoppingList.product.dto.ProductDto;
import shoppingList.product.services.ProductService;

@Component
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
    public String title() {
        return "Show all products in the DB;";
    }
}
