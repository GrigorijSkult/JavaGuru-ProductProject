package shoppingList.UI;

import shoppingList.businessLogic.services.ProductCategoryChoiseService;
import shoppingList.businessLogic.services.ProductService;
import shoppingList.businessLogic.services.exception.DbContainsSimilarProductException;
import shoppingList.domain.Product;
import shoppingList.domain.ProductCategory;

import java.math.BigDecimal;
import java.util.Scanner;

public class AddProductUI {

    ProductService productService;
    ProductCategoryChoiseService productCategoryChoiseService = new ProductCategoryChoiseService();

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
/*        System.out.println("Rules for adding a new product:" + "\n" +
                " *Product Name should be longer than 0 symbol;" + "\n" +
                " *Product Price should be positive and be entered with 'dot'. For example 2.33;" + "\n" +
                " *Product Discount should be entered with 'dot'. For example 15.55;" + "\n" +
                " *[OPTIONAL] fields are optional and can be skipped by clicking 'Enter';" + "\n" +
                "Below are the fields for filling out information about a new product:" +
                "");*/

        try {
            //new product name
            System.out.print("1. Please enter new product name: ");
            newProductName = sc.nextLine();

            //new product price
            System.out.print("2. Please enter new product price: ");
            try {
                newProductPrice = BigDecimal.valueOf(Double.parseDouble(sc.nextLine()));
            } catch (NumberFormatException e) {
                System.out.println("Please enter price with 'dot'. For example 2.33");
            }

            //new product category
            for (int i = 1; i <= ProductCategory.values().length - 1; i++) {
                System.out.println(i + ". " + productCategoryChoiseService.productCategoryIntChoice(i));
            }
            System.out.println("Or press '0' to set 'NoData' category");
            System.out.print("3. Please enter new product category:");
            String userInputString = sc.nextLine();
            int categoryChoice = Integer.parseInt(userInputString);//dlaja posledujushej validacii
            newProductCategory = productCategoryChoiseService.productCategoryIntChoice(categoryChoice);

            //new product discount
            System.out.print("4. [OPTIONAL] Please enter new product discount: ");
            try {
                String enteredValue = sc.nextLine();
                if (enteredValue.isEmpty()) {
                    newProductDiscount = BigDecimal.valueOf(0.00);
                } else {
                    newProductDiscount = BigDecimal.valueOf(Double.parseDouble(enteredValue));
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter discount with 'dot'. For example 15.5]");
            }

            //new product description
            System.out.print("5. [OPTIONAL] Please enter new product description: ");
            newProductDescription = sc.nextLine();
            if (newProductDescription.isEmpty()) {
                newProductDescription = null;
            }

            Product newProduct = new Product(newProductName, newProductPrice, newProductCategory, newProductDiscount, newProductDescription);
            productService.addProductService(newProduct);
            System.out.println("New product is added to the DataBase: " + newProduct);
        }catch (DbContainsSimilarProductException e){
            System.out.println("Product cant be added. " + e.DbContainsSimilarProductExceptionMessage());
        } catch (NumberFormatException e) {
            System.out.println("Please enter correct value!");
        }
    }
}
