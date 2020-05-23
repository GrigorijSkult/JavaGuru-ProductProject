package shoppingList.services.validations;

import shoppingList.domain.Product;
import shoppingList.services.validations.exception.ProductValidationException;

import java.math.BigDecimal;

public class ProductPriceValidation implements ValidationRule<Product> {

    @Override
    public void validate(Product product) {
        if (product.getProductRegularPrice().compareTo(BigDecimal.valueOf(0.00)) < 0){
            throw new ProductValidationException("Product price must be greater than 0");
        }
    }

}
