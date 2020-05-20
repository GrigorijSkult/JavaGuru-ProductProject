package shoppingList.businessLogic.validations;

import shoppingList.domain.Product;

public class ProductNameValidation implements ValidationForm<Product> {

    @Override
    public boolean isValid(Product product) {
        return product.getProductName().length() >= 3 && product.getProductName().length() <= 32;
    }

    public boolean isValid(String productName) {
        return productName.length() >= 3 && productName.length() <= 32;
    }

    @Override
    public String errorMessage() {
        return "Product name cannot be less than 3 characters and more than 32;";
    }
}
