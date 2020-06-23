package shoppingList.services.businessLogic;

import org.springframework.stereotype.Service;
import shoppingList.domain.ProductCategory;
import shoppingList.services.validations.ProductCategoryValidation;
import shoppingList.services.validations.exception.ProductValidationException;

@Service
public class ProductCategoryChoiceService {

    private final ProductCategoryValidation categoryValidation = new ProductCategoryValidation();

    public ProductCategory productCategoryIntChoice(int productCategoryNumber) throws ProductValidationException {
        ProductCategory productCategory;
        categoryValidation.categoryNumberValidation(productCategoryNumber);
        switch (productCategoryNumber) {
            case 1:
                productCategory = ProductCategory.MEAT_AND_MEAT_PRODUCTS;
                break;
            case 2:
                productCategory = ProductCategory.FRUITS;
                break;
            case 3:
                productCategory = ProductCategory.VEGETABLES;
                break;
            case 4:
                productCategory = ProductCategory.SEASONING_GOODS;
                break;
            case 5:
                productCategory = ProductCategory.EDIBLE_FATS;
                break;
            case 6:
                productCategory = ProductCategory.MILK_PRODUCTS;
                break;
            case 7:
                productCategory = ProductCategory.GRAIN_AND_FLOUR_PRODUCTS;
                break;
            case 8:
                productCategory = ProductCategory.FISH_AND_FISH_PRODUCTS;
                break;
            case 9:
                productCategory = ProductCategory.EGGS_AND_EGG_PRODUCTS;
                break;
            default:
                productCategory = ProductCategory.NO_DATA;
        }
        return productCategory;
    }
}
