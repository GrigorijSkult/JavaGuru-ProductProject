package shoppingList.businessLogic.validations;

import shoppingList.domain.Product;

import java.math.BigDecimal;

public class ProductDiscountValidation implements ValidationForm<Product> {

    @Override
    public boolean isValid(Product product) {
        return product.getProductRegularPrice().compareTo(BigDecimal.valueOf(0.00)) >= 0
                && product.getProductRegularPrice().compareTo(BigDecimal.valueOf(100.00)) <= 0;
    }

    public boolean isValid(BigDecimal productDiscount) {
        return productDiscount.compareTo(BigDecimal.valueOf(0.00)) >= 0 && productDiscount.compareTo(BigDecimal.valueOf(100.00)) <= 0;
    }

    @Override
    public String errorMessage() {
        return "Product discount value must be in the range from 0 to 100;";
    }
}
