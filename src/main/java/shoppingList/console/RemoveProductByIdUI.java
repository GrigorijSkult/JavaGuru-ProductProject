package shoppingList.console;

import org.springframework.stereotype.Component;
import shoppingList.services.businessLogic.ProductService;
import shoppingList.services.validations.exception.ProductNotFoundException;

import java.util.Scanner;

@Component
public class RemoveProductByIdUI implements UserInterfaceUnit {

    private final ProductService productService;

    public RemoveProductByIdUI(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        System.out.print("Please enter the product ID number to be deleted:");
        Scanner sc = new Scanner(System.in);
        Long id = sc.nextLong();
        try {
            System.out.println("You would like to delete: " + productService.findProductByID(id));
            System.out.print("Tap 'Y' for Yes or 'N' for No: ");
            Scanner sca = new Scanner(System.in);
            String scan = sca.nextLine();
            if (scan.startsWith("Y") || scan.startsWith("y")) {
                productService.removeProductByIDService(id);
                System.out.println("Product is deleted;");
            } else {
                System.out.println("Operation is canceled. Product wont be deleted;");
            }
        } catch (ProductNotFoundException e) {
            System.out.println("Product cant be deleted. " + e.getItemNotFoundMessage());
        }
    }

    @Override
    public String title() {
        return "Delete product by product ID;";
    }
}
