package shoppingList.services.validations;

import shoppingList.domain.Product;
import shoppingList.services.validations.exception.ProductValidationException;

public class ProductNameValidation implements ValidationRule<Product> {

    @Override
    public void validate(Product product) {
        if (product.getProductName().length() < 3 || product.getProductName().length() > 32){
            throw new ProductValidationException("Product name cannot be less than 3 characters and more than 32;");
        }
    }

}
