package shoppingList.shoppingCart.console;

import org.springframework.stereotype.Component;
import shoppingList.console.UserInterfaceUnit;
import shoppingList.shoppingCart.domain.ShoppingCartEntity;
import shoppingList.shoppingCart.service.ShoppingCartService;

@Component
public class ListOfAllShoppingCartsUI implements UserInterfaceUnit {

    private final ShoppingCartService shoppingCartService;

    public ListOfAllShoppingCartsUI(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public void execute() {
        for (ShoppingCartEntity shoppingCartEntity : shoppingCartService.listOfAllShoppingCartService()) {
            System.out.println(shoppingCartEntity);
        }
    }

    @Override
    public String title() {
        return "Show all shopping carts;";
    }
}
