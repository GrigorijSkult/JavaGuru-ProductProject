package shoppingList.console;

import org.springframework.stereotype.Component;
import shoppingList.product.services.validations.exception.ProductNotFoundException;
import shoppingList.service.businessLogic.ProductShoppingCartService;
import shoppingList.shoppingCart.domain.ShoppingCartEntity;
import shoppingList.shoppingCart.service.businessLogic.exception.ShoppingCartNotFoundException;

import java.util.Scanner;

@Component
public class AddProductToShoppingCart implements UserInterfaceUnit {

    private final ProductShoppingCartService productShoppingCartService;

    public AddProductToShoppingCart(ProductShoppingCartService productShoppingCartService) {
        this.productShoppingCartService = productShoppingCartService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter product ID: ");
        Long productId = scanner.nextLong();
        System.out.print("Please enter shopping cart ID: ");
        Long shoppingCartId = scanner.nextLong();

        try {
            ShoppingCartEntity shoppingCartEntity = productShoppingCartService.addProductEntityToShoppingCart(shoppingCartId, productId);
            System.out.println("Product is added to shopping cart: " + shoppingCartEntity);
        } catch (ProductNotFoundException e) {
            System.out.println("Product wont be added: " + e.getItemNotFoundMessage());
        }catch (ShoppingCartNotFoundException e){
            System.out.println("Shopping cart wont be added: "+ e.getItemNotFoundMessage());
        }
    }

    @Override
    public String title() {
        return "Add product to shopping cart;";
    }
}
