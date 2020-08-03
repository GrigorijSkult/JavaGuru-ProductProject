package shoppingList.shoppingCart.console;

import org.springframework.stereotype.Component;
import shoppingList.console.UserInterfaceUnit;
import shoppingList.product.mappers.ProductMapper;
import shoppingList.product.services.businessLogic.ProductService;
import shoppingList.product.services.validations.exception.ProductNotFoundException;
import shoppingList.shoppingCart.domain.ShoppingCartEntity;
import shoppingList.shoppingCart.service.businessLogic.ShoppingCartService;
import shoppingList.shoppingCart.service.businessLogic.exception.ShoppingCartNotFoundException;

import java.util.Scanner;

@Component
public class AddProductToShoppingCart implements UserInterfaceUnit {

    private final ProductService productService;
    private final ShoppingCartService shoppingCartService;
    private final ProductMapper productMapper;

    public AddProductToShoppingCart(ProductService productService, ShoppingCartService shoppingCartService, ProductMapper productMapper) {
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
        this.productMapper = productMapper;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter product ID: ");
        Long productId = scanner.nextLong();
        System.out.print("Please enter shopping cart ID: ");
        Long shoppingCartId = scanner.nextLong();

        try {
            ShoppingCartEntity shoppingCartEntity = shoppingCartService.addProductEntityToShoppingCart(shoppingCartId, productMapper.productToEntity(productService.findProductByID(productId)));
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
