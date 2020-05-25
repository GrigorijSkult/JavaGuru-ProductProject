package shoppingList.console;

import shoppingList.services.businessLogic.ProductService;
import shoppingList.services.validations.exception.ProductNotFoundException;

import java.util.Scanner;

public class RemoveProductByIdUI {

    ProductService productService;

    public RemoveProductByIdUI(ProductService productService) {
        this.productService = productService;
    }

    public void removeProductByIdUI() {
        System.out.print("Please enter the product ID number to be deleted:");
        Scanner sc = new Scanner(System.in);
        Long id = sc.nextLong();
        try {
            if (productService.doesDbContainsIdService(id)) {
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
            } else {
                throw new ProductNotFoundException(id);
            }
        } catch (ProductNotFoundException e) {
            System.out.println("Product cant be deleted. " + e.getItemNotFoundMessage());
        }
    }
}
