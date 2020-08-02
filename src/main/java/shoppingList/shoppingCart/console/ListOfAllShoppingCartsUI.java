package shoppingList.shoppingCart.console;

import org.springframework.stereotype.Component;
import shoppingList.console.UserInterfaceUnit;
import shoppingList.shoppingCart.service.businessLogic.ShoppingCartService;

@Component
public class ListOfAllShoppingCartsUI implements UserInterfaceUnit {

    private final ShoppingCartService shoppingCartService;

    public ListOfAllShoppingCartsUI(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public void execute() {
//        shoppingCartService.listOfAllShoppingCartService()
//                .forEach(System.out::println);
        for (Object o : shoppingCartService.listOfAllShoppingCartService()) {
            System.out.println(o);
        }
    }

    @Override
    public String title() {
        return "Show all shopping carts;";
    }
}
