package shoppingList.console;

import shoppingList.services.businessLogic.ProductCategoryChoiseService;
import shoppingList.services.businessLogic.ProductService;
import shoppingList.repository.ProductImpRepository;

import java.util.Scanner;

public class ProductMainUI {

    public void mainUI() {

        ProductService productService = new ProductService(new ProductImpRepository());
        AddProductUI addProductUI = new AddProductUI(productService, new ProductCategoryChoiseService());
        RemoveProductByIdUI removeProductByIdUI = new RemoveProductByIdUI(productService);
        ListOfAllProductsUI listOfAllProductsUI = new ListOfAllProductsUI(productService);
        FindProductByIdUI findProductByIdUI = new FindProductByIdUI(productService);
        /////////////
        //For DEVELOPMENT
        DevelopmentFiles_AddProducts devAdd = new DevelopmentFiles_AddProducts(productService);
        /////////////

        boolean continueProgram = true;
        do {
            System.out.print("Products DataBase" + "\n" +
                    "-----------------" + "\n" +
                    "Please choose your action:" + "\n" +
                    "1. Add new product to the DB;" + "\n" +
                    "2. Find product by ID;" + "\n" +
                    "3. Show all products in the DB;" + "\n" +
                    "4. Delete product by product ID" + "\n" +
                    "8. End program." + "\n" +
                    "9. [DEVELOPMENT] add 3 new products." + "\n" +
                    "");
            System.out.print("Select option: ");
            Scanner sc = new Scanner(System.in);
            try {
                String userChoiceString = sc.nextLine();
                int userChoice = Integer.parseInt(userChoiceString);
                switch (userChoice) {
                    case 1:
                        addProductUI.addProductUI();
                        break;
                    case 2:
                        findProductByIdUI.findProductByID();
                        break;
                    case 3:
                        listOfAllProductsUI.listOfAllProducts();
                        break;
                    case 4:
                        removeProductByIdUI.removeProductByIdUI();
                        break;
                    //...
                    case 8:
                        continueProgram = false;
                        break;
                    case 9:
                        devAdd.test();
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
