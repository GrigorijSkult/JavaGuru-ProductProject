package shoppingList.product.console;

import org.springframework.stereotype.Component;
import shoppingList.console.UserInterfaceUnit;
import shoppingList.product.dto.ProductDto;
import shoppingList.product.mappers.ProductMapper;
import shoppingList.product.services.businessLogic.ProductCategoryChoiceService;
import shoppingList.product.services.businessLogic.ProductService;
import shoppingList.product.services.validations.exception.ProductNotFoundException;
import shoppingList.product.services.validations.exception.ProductValidationException;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class UpdateProductUI implements UserInterfaceUnit {

    private final ProductService productService;
    private final ProductCategoryChoiceService productCategoryChoiceService;
    private final ProductMapper productMapper;

    public UpdateProductUI(ProductService productService, ProductCategoryChoiceService productCategoryChoiceService, ProductMapper productMapper) {
        this.productService = productService;
        this.productCategoryChoiceService = productCategoryChoiceService;
        this.productMapper = productMapper;
    }

    @Override
    public void execute() {
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
                    updatedProductDto.setName(productName);
                }

                System.out.print("2. Please enter updated product price: ");
                String productPrice = scanner.nextLine();
                if (!productPrice.isEmpty()) {
                    updatedProductDto.setRegularPrice(BigDecimal.valueOf(Double.parseDouble(productPrice)));
                }

                System.out.print("3. Please enter updated product category:");
                String userInputString = scanner.nextLine();
                if (!userInputString.isEmpty()) {
                    int categoryChoice = Integer.parseInt(userInputString);
                    updatedProductDto.setCategory(productCategoryChoiceService.productCategoryIntChoice(categoryChoice));
                }

                System.out.print("4. Please enter updated product discount: ");
                String productDiscount = scanner.nextLine();
                if (!productDiscount.isEmpty()) {
                    updatedProductDto.setDiscount(BigDecimal.valueOf(Double.parseDouble(productDiscount)));
                }

                System.out.print("5. Please enter new product description: ");
                String productDescription = scanner.nextLine();
                if (!productDescription.isEmpty()) {
                    updatedProductDto.setDescription(productDescription);
                }
                System.out.println("Product is updated : " + productService.updateProductService(id, productMapper.productToEntity(updatedProductDto)));
            } else {
                System.out.println("Operation is canceled. Product wont be updated;");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter discount number with 'dot'. [For example 15.5]");
        } catch (ProductValidationException e) {
            System.out.println("Product cant be updated: " + e.getMessage());
        } catch (ProductNotFoundException e) {
            System.out.println("Product cant be updated. " + e.getItemNotFoundMessage());
        }
    }

    @Override
    public String title() {
        return "Update product by product ID;";
    }
}
