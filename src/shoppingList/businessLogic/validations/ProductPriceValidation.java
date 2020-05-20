package shoppingList.businessLogic.validations;

import shoppingList.domain.Product;

import java.math.BigDecimal;

public class ProductPriceValidation implements ValidationForm<Product> {

    @Override
    public boolean isValid(Product product) {
        return product.getProductRegularPrice().compareTo(BigDecimal.valueOf(0.00)) >= 0;
    }

    public boolean isValid(BigDecimal productPrice) {
        return productPrice.compareTo(BigDecimal.valueOf(0.00)) >= 0;
    }

    @Override
    public String errorMessage() {
        return "Product price must be greater than 0";
    }
}
