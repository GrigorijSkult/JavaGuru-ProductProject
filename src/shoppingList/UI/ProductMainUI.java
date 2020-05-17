package shoppingList.UI;

import shoppingList.businessLogic.services.ProductService;
import shoppingList.database.ProductRepositoryImp;

import java.util.Scanner;

public class ProductMainUI {

    public void mainUI() {
        //old logic
        /*Map<Long, Product> productRepository = new HashMap<>();
        Long productIdSequence = 1L;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("1. Create product");
                System.out.println("2. Find product by id");
                System.out.println("3. Exit");
                Integer userInput = Integer.valueOf(scanner.nextLine());
                switch (userInput) {
                    case 1:
                        System.out.println("Enter product name: ");
                        String name = scanner.nextLine();
                        System.out.println("Enter product price: ");
                        BigDecimal price = new BigDecimal(scanner.nextLine());
                        Product product = new Product(productIdSequence,name, price, ProductCategory.NO_DATA);
//                        product.setProductName(name);
//                        product.setProductActualPrice(price);
//                        product.setProductId(productIdSequence);
                        productRepository.put(productIdSequence, product);
                        productIdSequence++;
                        System.out.println("Result: " + product.getProductId());
                    case 2:
                        System.out.println("Enter product id: ");
                        long id = scanner.nextLong();
                        Product findProductResult = productRepository.get(id);
                        System.out.println(findProductResult);
                    case 3:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error! Please try again.");
            }
        }*/

        //new logic
        ProductService productService = new ProductService(new ProductRepositoryImp());
        AddProductUI addProductUI = new AddProductUI(productService);
        RemoveProductByIdUI removeProductByIdUI = new RemoveProductByIdUI(productService);
        ListOfAllProductsUI listOfAllProductsUI = new ListOfAllProductsUI(productService);
        FindProductByIdUI findProductByIdUI = new FindProductByIdUI(productService);

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
                        listOfAllProductsUI.ListOfAllProducts();
                        break;
                    case 4:
                        removeProductByIdUI.removeProductByIdUI();
                        break;
                    //...
                    case 8:
                        continueProgram = false;
                        break;
                    default:
                        // code block?
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter correct value!");
            }
            System.out.println("-/-/-/--/-");
        } while (continueProgram);
        System.out.println("Program is stopped");
    }
}
