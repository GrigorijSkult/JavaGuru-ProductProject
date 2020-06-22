package shoppingList.console;

import shoppingList.services.businessLogic.ProductCategoryChoiceService;
import shoppingList.services.businessLogic.ProductService;

import java.util.Scanner;

public class ProductMainUI {

    private final ProductService productService;
    private final ProductCategoryChoiceService productCategoryChoiceService;

    public ProductMainUI(ProductService productService, ProductCategoryChoiceService productCategoryChoiceService) {
        this.productService = productService;
        this.productCategoryChoiceService = productCategoryChoiceService;
    }

    public void mainUI() {
        AddProductUI addProductUI = new AddProductUI(productService, productCategoryChoiceService);
        RemoveProductByIdUI removeProductByIdUI = new RemoveProductByIdUI(productService);
        ListOfAllProductsUI listOfAllProductsUI = new ListOfAllProductsUI(productService);
        FindProductByIdUI findProductByIdUI = new FindProductByIdUI(productService);
        UpdateProductUI updateProductUI = new UpdateProductUI(productService, productCategoryChoiceService);

        boolean continueProgram = true;
        do {
            System.out.print("Products DataBase" + "\n" +
                    "-----------------" + "\n" +
                    "Please choose your action:" + "\n" +
                    "1. Add new product to the DB;" + "\n" +
                    "2. Find product by ID;" + "\n" +
                    "3. Show all products in the DB;" + "\n" +
                    "4. Delete product by product ID" + "\n" +
                    "5. Update product by product ID" + "\n" +
                    "6. End program." + "\n" +
                    "");
            System.out.print("Select option: ");
            Scanner sc = new Scanner(System.in);
            try {
                String userChoiceString = sc.nextLine();
                int userChoice = Integer.parseInt(userChoiceString);
                switch (userChoice) {
                    case 1:
                        addProductUI.execute();
                        break;
                    case 2:
                        findProductByIdUI.execute();
                        break;
                    case 3:
                        listOfAllProductsUI.execute();
                        break;
                    case 4:
                        removeProductByIdUI.execute();
                        break;
                    case 5:
                        updateProductUI.execute();
                        break;
                    case 6:
                        continueProgram = false;
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter correct value!");
            }
            System.out.println("-/-/-/--/-");
        } while (continueProgram);
        System.out.println("Program is stopped");
    }
}
