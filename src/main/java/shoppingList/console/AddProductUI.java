package shoppingList.console;

import org.springframework.stereotype.Component;
import shoppingList.domain.ProductCategory;
import shoppingList.dto.ProductDto;
import shoppingList.services.businessLogic.ProductCategoryChoiceService;
import shoppingList.services.businessLogic.ProductService;
import shoppingList.services.validations.exception.ProductValidationException;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class AddProductUI implements UserInterfaceUnit {

    private final ProductService productService;
    private final ProductCategoryChoiceService productCategoryChoiceService;

    public AddProductUI(ProductService productService, ProductCategoryChoiceService productCategoryChoiceService) {
        this.productService = productService;
        this.productCategoryChoiceService = productCategoryChoiceService;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Rules for adding a new product:" + "\n" +
                " *Product name cannot be less than 3 characters and more than 32;" + "\n" +
                " *Product price and discount must be positive and be entered with 'dot' [For example 2.33 or 25.50];" + "\n" +
                " *Product discount value must be in the range from 0 to 100;" + "\n" +
                " *[OPTIONAL] fields are optional and can be skipped by clicking 'Enter';" + "\n" +
                "Below are the fields for filling out information about a new product:" +
                "");
        try {
            System.out.print("1. Please enter new product name: ");
            String newProductName = sc.nextLine();

            System.out.print("2. Please enter new product price: ");
            BigDecimal newProductPrice = BigDecimal.valueOf(Double.parseDouble(sc.nextLine()));

            for (int i = 1; i < ProductCategory.values().length; i++) {
                System.out.println(i + ". " + productCategoryChoiceService.productCategoryIntChoice(i));
            }
            System.out.println("Or press '0' to set 'NoData' category");
            System.out.print("3. Please enter new product category:");
            String userInputString = sc.nextLine();
            int categoryChoice = Integer.parseInt(userInputString);
            ProductCategory newProductCategory = productCategoryChoiceService.productCategoryIntChoice(categoryChoice);

            System.out.print("4. [OPTIONAL] Please enter new product discount: ");
            String enteredValue = sc.nextLine();
            BigDecimal newProductDiscount;
            if (enteredValue.isEmpty()) {
                newProductDiscount = BigDecimal.valueOf(0.00);
            } else {
                newProductDiscount = BigDecimal.valueOf(Double.parseDouble(enteredValue));
            }

            System.out.print("5. [OPTIONAL] Please enter new product description: ");
            String newProductDescription = sc.nextLine();
            if (newProductDescription.isEmpty()) {
                newProductDescription = null;
            }

            ProductDto newProductDto = new ProductDto();
            newProductDto.setProductName(newProductName);
            newProductDto.setProductRegularPrice(newProductPrice);
            newProductDto.setProductCategory(newProductCategory);
            newProductDto.setProductDiscount(newProductDiscount);
            newProductDto.setProductDescription(newProductDescription);
            System.out.println("New product is added to the DataBase: " + productService.addProductService(newProductDto));
        } catch (NumberFormatException e) {
            System.out.println("Please enter discount number with 'dot'. [For example 15.5]");
        } catch (ProductValidationException e) {
            System.out.println("Product cant be added: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Add new product to the DB;";
    }
}
