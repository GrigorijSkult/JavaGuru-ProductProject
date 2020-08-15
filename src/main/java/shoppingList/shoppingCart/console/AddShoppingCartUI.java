package shoppingList.shoppingCart.console;

import org.springframework.stereotype.Component;
import shoppingList.console.UserInterfaceUnit;
import shoppingList.shoppingCart.domain.ShoppingCartEntity;
import shoppingList.shoppingCart.service.ShoppingCartService;

import java.util.Scanner;

@Component
public class AddShoppingCartUI implements UserInterfaceUnit {

    private final ShoppingCartService shoppingCartService;

    public AddShoppingCartUI(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public void execute() {
        System.out.print("Please enter new shopping cart description: ");
        Scanner scanner = new Scanner(System.in);
        String description = scanner.nextLine();

        ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity();
        shoppingCartEntity.setDescription(description);

        ShoppingCartEntity newShoppingCart = shoppingCartService.addShoppingCartService(shoppingCartEntity);

        System.out.println("New shopping cart: " + newShoppingCart);
    }

    @Override
    public String title() {
        return "Add new shopping cart;";
    }


}
