package shoppingList.console;

import shoppingList.dto.ProductDto;
import shoppingList.services.businessLogic.ProductCategoryChoiseService;
import shoppingList.services.businessLogic.ProductService;
import shoppingList.services.validations.exception.DbContainsSimilarProductException;
import shoppingList.services.validations.exception.ProductNotFoundException;
import shoppingList.services.validations.exception.ProductValidationException;

import java.math.BigDecimal;
import java.util.Scanner;

public class UpdateProductUI {

    private final ProductService productService;
    private final ProductCategoryChoiseService productCategoryChoiseService;

    public UpdateProductUI(ProductService productService, ProductCategoryChoiseService productCategoryChoiseService) {
        this.productService = productService;
        this.productCategoryChoiseService = productCategoryChoiseService;
    }

    public void updateProduct() {
        System.out.print("Please enter product ID number to be updated:");
        Scanner sc = new Scanner(System.in);
        Long id = sc.nextLong();
        try {
            ProductDto updatedProductDto = productService.findProductByID(id);
            System.out.println("You would like to update: " + updatedProductDto);
            System.out.print("Tap 'Y' for Yes or 'N' for No: ");
            Scanner sca = new Scanner(System.in);
            String scan = sca.nextLine();
            if (scan.startsWith("Y") || scan.startsWith("y")) {
                Scanner scanner = new Scanner(System.in);

                System.out.print("1. Please enter updated product name: ");
                String productName = scanner.nextLine();
                if (!productName.isEmpty()) {
                    updatedProductDto.setProductName(productName);
                }

                System.out.print("2. Please enter updated product price: ");
                String productPrice = scanner.nextLine();
                if (!productPrice.isEmpty()) {
                    updatedProductDto.setProductRegularPrice(BigDecimal.valueOf(Double.parseDouble(productPrice)));
                }

                System.out.print("3. Please enter updated product category:");
                String userInputString = scanner.nextLine();
                if (!userInputString.isEmpty()) {
                    int categoryChoice = Integer.parseInt(userInputString);
                    updatedProductDto.setProductCategory(productCategoryChoiseService.productCategoryIntChoice(categoryChoice));
                }

                System.out.print("4. Please enter updated product discount: ");
                String productDiscount = scanner.nextLine();
                if (!productDiscount.isEmpty()) {
                    updatedProductDto.setProductDiscount(BigDecimal.valueOf(Double.parseDouble(productDiscount)));
                }

                System.out.print("5. Please enter new product description: ");
                String productDescription = scanner.nextLine();
                if (!productDescription.isEmpty()) {
                    updatedProductDto.setProductDescription(productDescription);
                }
                System.out.println("Product is updated : " + productService.updateProductService(id, updatedProductDto));
            } else {
                System.out.println("Operation is canceled. Product wont be updated;");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter discount number with 'dot'. [For example 15.5]");
        } catch (ProductValidationException e) {
            System.out.println("Product cant be updated: " + e.getMessage());
        } catch (DbContainsSimilarProductException e) {
            System.out.println("Product cant be updated: " + e.DbContainsSimilarProductExceptionMessage());
        } catch (ProductNotFoundException e) {
            System.out.println("Product cant be updated. " + e.getItemNotFoundMessage());
        }
    }
}
