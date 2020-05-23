package shoppingList.services.validations;

import shoppingList.domain.Product;
import shoppingList.domain.ProductCategory;
import shoppingList.services.validations.exception.ProductValidationException;

public class ProductCategoryValidation implements ValidationRule<Product> {

    @Override
    public void validate(Product product) {
        boolean isValid = false;
        for (ProductCategory value : ProductCategory.values()) {
            if (product.getProductCategory().equals(value)) {
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            throw new ProductValidationException("Product category is incorrect");
        }
    }

    public boolean categoryNumberIsValid(int productCategory) {
        if (productCategory >= 0 && productCategory < ProductCategory.values().length) {
            return true;
        } else {
            throw new ProductValidationException("Product category number is incorrect");
        }
    }
}
