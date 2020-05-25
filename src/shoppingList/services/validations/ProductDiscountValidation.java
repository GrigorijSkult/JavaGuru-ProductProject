package shoppingList.services.validations;

import shoppingList.domain.Product;
import shoppingList.services.validations.exception.ProductValidationException;

import java.math.BigDecimal;

public class ProductDiscountValidation implements ValidationRule<Product> {

    @Override
    public void validate(Product product) {
        if (product.getProductDiscount().compareTo(BigDecimal.valueOf(0.00)) < 0
               || product.getProductDiscount().compareTo(BigDecimal.valueOf(100.00)) > 0){
            throw new ProductValidationException("Product discount value must be in the range from 0 to 100;");
        }
    }

}
