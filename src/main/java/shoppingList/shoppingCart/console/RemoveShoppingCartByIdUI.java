package shoppingList.shoppingCart.console;

import org.springframework.stereotype.Component;
import shoppingList.console.UserInterfaceUnit;
import shoppingList.shoppingCart.service.businessLogic.ShoppingCartService;
import shoppingList.shoppingCart.service.businessLogic.exception.ShoppingCartNotFoundException;

import java.util.Scanner;

@Component
public class RemoveShoppingCartByIdUI implements UserInterfaceUnit {

    private final ShoppingCartService shoppingCartService;

    public RemoveShoppingCartByIdUI(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public void execute() {
        System.out.print("Please enter the shopping cart ID number:");
        Scanner sc = new Scanner(System.in);
        Long id = sc.nextLong();
        try {
            System.out.println("You would like to delete: " + shoppingCartService.findShoppingCartByID(id));
            System.out.print("Tap 'Y' for Yes or 'N' for No: ");
            Scanner sca = new Scanner(System.in);
            String scan = sca.nextLine();
            if (scan.startsWith("Y") || scan.startsWith("y")) {
                shoppingCartService.removeShoppingCartByIdService(id);
                System.out.println("Shopping cart is deleted;");
            } else {
                System.out.println("Operation is canceled. Shopping cart wont be deleted;");
            }
        } catch (ShoppingCartNotFoundException e) {
            System.out.println("Shopping cart cant be deleted. " + e.getItemNotFoundMessage());
        }
    }

    @Override
    public String title() {
        return "Delete shopping cart by ID;";
    }
}
