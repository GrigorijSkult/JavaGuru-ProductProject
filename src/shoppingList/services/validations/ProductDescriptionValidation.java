package shoppingList.services.validations;

import shoppingList.domain.Product;
import shoppingList.services.validations.exception.ProductValidationException;

public class ProductDescriptionValidation implements ValidationRule<Product> {

    @Override
    public void validate(Product product) {
        if (!(product.getProductDescription() == null || product.getProductDescription().isEmpty())
                && product.getProductDescription().length() > 999){
            throw new ProductValidationException("Product`s description should be shorter then 999 symbols");
        }
    }

}
