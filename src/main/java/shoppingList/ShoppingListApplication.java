package shoppingList;

import shoppingList.console.ProductMainUI;
import shoppingList.dto.ProductDto;
import shoppingList.mappers.ProductMapper;
import shoppingList.repository.ProductImpRepository;
import shoppingList.services.businessLogic.ProductCategoryChoiseService;
import shoppingList.services.businessLogic.ProductService;
import shoppingList.services.validations.*;

import java.util.ArrayList;
import java.util.List;

class ShoppingListApplication {

    public static void main(String[] args) {

        ProductImpRepository productImpRepository = new ProductImpRepository();
        ProductMapper productMapper = new ProductMapper();
        ProductCategoryChoiseService productCategoryChoiseService = new ProductCategoryChoiseService();

        List<ValidationRule<ProductDto>> validationRules = new ArrayList<>();
        validationRules.add(new ProductNameValidation());
        validationRules.add(new ProductPriceValidation());
        validationRules.add(new ProductCategoryValidation());
        validationRules.add(new ProductDiscountValidation());
        validationRules.add(new ProductDescriptionValidation());
        ProductValidationService productValidationService = new ProductValidationService(validationRules);

        ProductService productService = new ProductService(productImpRepository, productValidationService, productMapper);
        ProductMainUI productMainUI = new ProductMainUI(productService, productCategoryChoiseService);
        productMainUI.mainUI();
    }
}
