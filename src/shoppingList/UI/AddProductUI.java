package shoppingList.UI;

import shoppingList.businessLogic.services.ProductCategoryChoiseService;
import shoppingList.businessLogic.services.ProductService;
import shoppingList.businessLogic.services.exception.DbContainsSimilarProductException;
import shoppingList.businessLogic.validations.*;
import shoppingList.domain.Product;
import shoppingList.domain.ProductCategory;

import java.math.BigDecimal;
import java.util.Scanner;

public class AddProductUI {

    ProductService productService;
    private ProductCategoryChoiseService productCategoryChoiseService = new ProductCategoryChoiseService();
    private ProductNameValidation nameValidation = new ProductNameValidation();
    private ProductPriceValidation priceValidation = new ProductPriceValidation();
    private ProductCategoryValidation categoryValidation = new ProductCategoryValidation();
    private ProductDiscountValidation discountValidation = new ProductDiscountValidation();
    private ProductDescriptionValidation descriptionValidation = new ProductDescriptionValidation();

    public AddProductUI(ProductService productService) {
        this.productService = productService;
    }

    public void addProductUI() {
        String newProductName = null;//field number 1
        BigDecimal newProductPrice = null;//field number 2
        ProductCategory newProductCategory = productCategoryChoiseService.productCategoryIntChoice(0);//field number 3
        BigDecimal newProductDiscount = null;//field number 4
        String newProductDescription = null;//field number 5
        Scanner sc = new Scanner(System.in);
        System.out.println("Rules for adding a new product:" + "\n" +
                " *Product name cannot be less than 3 characters and more than 32;" + "\n" +
                " *Product price and discount must be positive and be entered with 'dot' [For example 2.33 or 25.50];" + "\n" +
                " *Product discount value must be in the range from 0 to 100;" + "\n" +
                " *[OPTIONAL] fields are optional and can be skipped by clicking 'Enter';" + "\n" +
                "Below are the fields for filling out information about a new product:" +
                "");
        try {
            boolean dataIsValid = false;
            int fieldNumber = 1;
            String errorLogs = null;
            do {
                if (fieldNumber == 1) {
                    //new product name
                    System.out.print("1. Please enter new product name: ");
                    newProductName = sc.nextLine();
                    dataIsValid = nameValidation.isValid(newProductName);
                    if (!dataIsValid) {
                        errorLogs = nameValidation.errorMessage();
                    }
                }
                if (fieldNumber == 2) {
                    //new product price
                    System.out.print("2. Please enter new product price: ");
                    try {
                        newProductPrice = BigDecimal.valueOf(Double.parseDouble(sc.nextLine()));
                        dataIsValid = priceValidation.isValid(newProductPrice);
                        if (!dataIsValid) {
                            errorLogs = priceValidation.errorMessage();
                        }
                    } catch (NumberFormatException e) {
                        errorLogs = "Please enter price with 'dot'. [For example 2.33]";
                        dataIsValid = false;
                    }
                }
                if (fieldNumber == 3) {
                    //new product category
                    for (int i = 1; i < ProductCategory.values().length; i++) {
                        System.out.println(i + ". " + productCategoryChoiseService.productCategoryIntChoice(i));
                    }
                    System.out.println("Or press '0' to set 'NoData' category");
                    System.out.print("3. Please enter new product category:");
                    String userInputString = sc.nextLine();
                    dataIsValid = categoryValidation.isValid(userInputString);
                    if (dataIsValid) {
                        int categoryChoice = Integer.parseInt(userInputString);
                        newProductCategory = productCategoryChoiseService.productCategoryIntChoice(categoryChoice);
                    } else {
                        errorLogs = categoryValidation.errorMessage();
                    }
                }
                if (fieldNumber == 4) {
                    //new product discount
                    System.out.print("4. [OPTIONAL] Please enter new product discount: ");
                    try {
                        String enteredValue = sc.nextLine();
                        if (enteredValue.isEmpty()) {
                            newProductDiscount = BigDecimal.valueOf(0.00);
                            dataIsValid = true;
                        } else {
                            newProductDiscount = BigDecimal.valueOf(Double.parseDouble(enteredValue));
                            dataIsValid = discountValidation.isValid(newProductDiscount);
                        }
                        if (!dataIsValid) {
                            errorLogs = discountValidation.errorMessage();
                        }
                    } catch (NumberFormatException e) {
                        errorLogs = "Please enter discount number with 'dot'. [For example 15.5]";
                        dataIsValid = false;
                    }
                }
                if (fieldNumber == 5) {
                    //new product description
                    System.out.print("5. [OPTIONAL] Please enter new product description: ");
                    newProductDescription = sc.nextLine();
                    dataIsValid = descriptionValidation.isValid(newProductDescription);
                    if (newProductDescription.isEmpty()) {
                        newProductDescription = null;
                    }
                    if (!dataIsValid) {
                        errorLogs = descriptionValidation.errorMessage();
                    }
                }
                fieldNumber++;
            } while (dataIsValid && fieldNumber <= 5);

            if (dataIsValid) {
                Product newProduct = new Product(newProductName, newProductPrice, newProductCategory, newProductDiscount, newProductDescription);
                productService.addProductService(newProduct);
                System.out.println("New product is added to the DataBase: " + newProduct);
            } else {
                System.out.println("Product cant be added. " + errorLogs);
            }

        } catch (DbContainsSimilarProductException e) {
            System.out.println("Product cant be added. " + e.DbContainsSimilarProductExceptionMessage());
        } catch (NumberFormatException e) {
            System.out.println("Product cant be added. Please enter correct category value!");
        }
    }
}
