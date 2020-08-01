package shoppingList.console;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import shoppingList.product.console.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConsoleUIConfiguration {

    private final AddProductUI addProductUI;
    private final RemoveProductByIdUI removeProductByIdUI;
    private final ListOfAllProductsUI listOfAllProductsUI;
    private final FindProductByIdUI findProductByIdUI;
    private final UpdateProductUI updateProductUI;
    private final ExitUI exitUI;

    ConsoleUIConfiguration(AddProductUI addProductUI,
                           RemoveProductByIdUI removeProductByIdUI,
                           ListOfAllProductsUI listOfAllProductsUI,
                           FindProductByIdUI findProductByIdUI,
                           UpdateProductUI updateProductUI,
                           ExitUI exitUI) {
        this.addProductUI = addProductUI;
        this.removeProductByIdUI = removeProductByIdUI;
        this.listOfAllProductsUI = listOfAllProductsUI;
        this.findProductByIdUI = findProductByIdUI;
        this.updateProductUI = updateProductUI;
        this.exitUI = exitUI;
    }

    @Bean
    public ProductMainUI productMainUI() {
        List<UserInterfaceUnit> productMainUnitList = new ArrayList<>();
        productMainUnitList.add(addProductUI);
        productMainUnitList.add(removeProductByIdUI);
        productMainUnitList.add(listOfAllProductsUI);
        productMainUnitList.add(findProductByIdUI);
        productMainUnitList.add(updateProductUI);
        productMainUnitList.add(exitUI);
        return new ProductMainUI(productMainUnitList);
    }
}
