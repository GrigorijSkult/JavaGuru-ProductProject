package shoppingList.console;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import shoppingList.config.ProductMainUI;
import shoppingList.product.console.*;
import shoppingList.shoppingCart.console.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConsoleUIConfiguration {

    private final AddProductUI addProductUI;
    private final RemoveProductByIdUI removeProductByIdUI;
    private final ListOfAllProductsUI listOfAllProductsUI;
    private final FindProductByIdUI findProductByIdUI;
    private final UpdateProductUI updateProductUI;

    private final AddShoppingCartUI addShoppingCartUI;
    private final RemoveShoppingCartByIdUI removeShoppingCartByIdUI;
    private final ListOfAllShoppingCartsUI listOfAllShoppingCartsUI;
    private final UpdateShoppingCartUI updateShoppingCartUI;

    private final AddProductToShoppingCart addProductToShoppingCart;
    private final ExitUI exitUI;

    ConsoleUIConfiguration(AddProductUI addProductUI,
                           RemoveProductByIdUI removeProductByIdUI,
                           ListOfAllProductsUI listOfAllProductsUI,
                           FindProductByIdUI findProductByIdUI,
                           UpdateProductUI updateProductUI,

                           AddShoppingCartUI addShoppingCartUI,
                           RemoveShoppingCartByIdUI removeShoppingCartByIdUI,
                           ListOfAllShoppingCartsUI listOfAllShoppingCartsUI,
                           UpdateShoppingCartUI updateShoppingCartUI,

                           AddProductToShoppingCart addProductToShoppingCart,
                           ExitUI exitUI) {
        this.addProductUI = addProductUI;
        this.removeProductByIdUI = removeProductByIdUI;
        this.listOfAllProductsUI = listOfAllProductsUI;
        this.findProductByIdUI = findProductByIdUI;
        this.updateProductUI = updateProductUI;

        this.addShoppingCartUI = addShoppingCartUI;
        this.removeShoppingCartByIdUI = removeShoppingCartByIdUI;
        this.listOfAllShoppingCartsUI = listOfAllShoppingCartsUI;
        this.updateShoppingCartUI = updateShoppingCartUI;

        this.addProductToShoppingCart = addProductToShoppingCart;
        this.exitUI = exitUI;
    }

    @Bean
    public ProductMainUI productMainUI() {
        List<UserInterfaceUnit> productMainUnitList = new ArrayList<>();
        productMainUnitList.add(addProductUI);
//        productMainUnitList.add(removeProductByIdUI);
        productMainUnitList.add(listOfAllProductsUI);
//        productMainUnitList.add(findProductByIdUI);
//        productMainUnitList.add(updateProductUI);

        productMainUnitList.add(addShoppingCartUI);
//        productMainUnitList.add(removeShoppingCartByIdUI);
        productMainUnitList.add(listOfAllShoppingCartsUI);
//        productMainUnitList.add(updateShoppingCartUI);

        productMainUnitList.add(addProductToShoppingCart);
        productMainUnitList.add(exitUI);
        return new ProductMainUI(productMainUnitList);
    }
}
