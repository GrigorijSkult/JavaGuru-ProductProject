package shoppingList.UI;

import shoppingList.domain.Product;
import shoppingList.domain.ProductCategory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProductMainUI {

    public void mainUI(){
        Map<Long, Product> productRepository = new HashMap<>();
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
        }
    }
}
