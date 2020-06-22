package shoppingList.console;

import shoppingList.services.businessLogic.ProductService;
import shoppingList.services.validations.exception.ProductNotFoundException;

import java.util.Scanner;

public class FindProductByIdUI implements UserInterfaceUnit {

    private final ProductService productService;

    public FindProductByIdUI(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        System.out.print("Please enter product ID number:");
        Scanner sc = new Scanner(System.in);
        Long id = sc.nextLong();
        try {
            System.out.println("Your product is:" + productService.findProductByID(id));
        } catch (ProductNotFoundException e) {
            System.out.println(e.getItemNotFoundMessage());
        }
    }

    @Override
    public String toString() {
        return "Find product by ID;";
    }
}
