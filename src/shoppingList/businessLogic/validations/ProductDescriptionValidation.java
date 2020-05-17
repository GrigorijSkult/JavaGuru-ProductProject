package shoppingList.businessLogic.validations;

import shoppingList.domain.Product;

public class ProductDescriptionValidation implements ValidationForm<Product> {

    @Override
    public boolean isValid(Product product) {
        return product.getProductDescription().isEmpty() || product.getProductDescription().length() <= 999;
    }

    public boolean isValid(String productDescription) {
        return productDescription.isEmpty() || productDescription.length() <= 999;
    }


    @Override
    public String errorMessage() {
        return "Product`s description should be shorter then 999 symbols";
    }
}
