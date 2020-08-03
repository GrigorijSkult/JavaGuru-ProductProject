package shoppingList.shoppingCart.console;

import org.springframework.stereotype.Component;
import shoppingList.console.UserInterfaceUnit;
import shoppingList.shoppingCart.domain.ShoppingCartEntity;
import shoppingList.shoppingCart.service.businessLogic.ShoppingCartService;
import shoppingList.shoppingCart.service.businessLogic.exception.ShoppingCartNotFoundException;

import java.util.Scanner;

@Component
public class UpdateShoppingCartUI implements UserInterfaceUnit {

    private final ShoppingCartService shoppingCartService;

    public UpdateShoppingCartUI(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public void execute() {
        System.out.print("Please enter shopping cart ID number to be updated:");
        Scanner sc = new Scanner(System.in);
        Long id = sc.nextLong();

        try {
            ShoppingCartEntity updatedShoppingCartEntity = shoppingCartService.findShoppingCartByID(id);
            System.out.println("You would like to update: " + updatedShoppingCartEntity);
            System.out.print("Tap 'Y' for Yes or 'N' for No: ");
            Scanner sca = new Scanner(System.in);
            String scan = sca.nextLine();
            if (scan.startsWith("Y") || scan.startsWith("y")) {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Please enter updated shopping cart description: ");
                String productDescription = scanner.nextLine();
                if (!productDescription.isEmpty()) {
                    updatedShoppingCartEntity.setDescription(productDescription);
                }
                System.out.println("Shopping cart is updated : " + shoppingCartService.updatedShoppingCartService(id, updatedShoppingCartEntity));
            } else {
                System.out.println("Operation is canceled. Shopping cart wont be updated;");
            }
        } catch (ShoppingCartNotFoundException e) {
            System.out.println("Shopping cart cant be updated. " + e.getItemNotFoundMessage());
        }
    }

    @Override
    public String title() {
        return "Update shopping cart by ID;";
    }
}
